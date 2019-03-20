总时长：约 10 小时

# 环境配置

教材用的是 Java FX，先安装 Java FX 环境。

参考了 https://openjfx.io/openjfx-docs/ ，使用以下两种方法配置 Java FX 开发环境。

### 使用构建工具 Gradle

由于我并不是很想下载 JavaFX SDK (虽然最后还是下了)，于是首先使用 gradle 进行构建。

需要在 gradle 脚本中对 javafxplugin 进行配置。配置完成后直接使用 gradle run 运行 JavaFX程序。

`build.gradle` 配置文件如下

```groovy
plugins {
    id 'application'
    id 'org.openjfx.javafxplugin' version '0.0.7'
}

group 'moe.gogo'
version '0.0.0'

repositories {
    mavenCentral()
}

javafx {
    modules = ['javafx.controls']
}
mainClassName = "HelloWorldFX"

dependencies {
}

```

### 使用 JavaFX SDK 

首先下载 JavaFX SDK https://gluonhq.com/products/javafx/ 并配置环境变量 
`PATH_TO_FX="path\to\javafx-sdk-11.0.2\lib"`

参考文档，需要使用如下命令进行编译和运行 JavaFX 程序
```shell
javac --module-path %PATH_TO_FX% --add-modules=javafx.controls HelloFX.java
```

```shell
java --module-path %PATH_TO_FX% --add-modules=javafx.controls HelloFX
```

因此需要在 Idea 的 Run Configuration 中添加 VM options 
`--module-path "path\to\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls`

由于对 Java9 新增的 module 了解甚少，接着去查了一些 module 相关资料，理解了 module 大概是个什么东西，以及一些常见的用法。

### 遗留问题

若使用 gradle 工具运行 JavaFX 程序，则每次更换程序时都需要修改 `mainClassName` 参数以切换主函数。

若不使用 gradle ，则每个新的运行项都需要配置一遍 Run Configuration 添加 VM options 。

两种方法都比较麻烦，希望找到简化的方法，使每个 JavaFX 程序不需要额外配置就能运行。

网上查到可以使用 gradle 的插件 application ，通过配置 `applicationDefaultJvmArgs ` 添加默认运行参数。
继续往下看发现 application 插件本身便是使用 `gradle run` 运行 `mainClassName` 。
推断 javafxplugin 插件只是对 application 进行补充，添加了 javafx 默认配置项而已。

所以还是没找到简便的方法，看来模块化带来的不只有好事。

-----

# JavaFX

### JavaFX Applications

使用 JavaFX，首先要继承 `Application` 对象，并实现 `start()` 方法。在主函数中使用 `launch()` 加载应用。

使用 `launch()` 时，会创建一个 JavaFX application thread， `start()` 方法会在这个线程中被调用。

`init()` 方法会在 `start()` 之前调用

`stop()` 方法会在系统退出前调用，可以使用 `Platform.exit()` 会使程序退出，并在调用 `stop()`

### Stage, Scene, and SceneGraph

`Stage` 表示一个 windows 窗口。

场景 `Scene` 表示窗口内的场景，其中可以添加**组件 GUI components**。

容器`Container` 是窗口中可以添加其他组件的区域。

所有的组件的包含关系形成了一个 **场景图 scene graph**。

### Nodes and Layout

可以在场景图中的东西被称为 节点`Node`，容器是 `Parent` 的子类，`Parent`是 `Node`的子类 ，被包含在 `Parent` 中的节点被称为 **children**。

在控制 children 行为的容器被称为布局 `Layout` 。`Layout` 会控制内部组件的大小和位置。

### Events and Event Handlers

实现 `EventHandler` 并提供 `handle()` 方法，然后**注册 register** 该处理器到生成事件的对象上上，事件触发时便可以处理事件。程序通过处理事件来响应用户输入。

# Basic Classes

### Color and Paint

`Color` 类用来表示颜色。 `Color`中除了 RGB 外，还包含一个 **不透明度分量 alpha color component** ，用来表示颜色的不透明度。

使用静态工厂方法 ` Color.color(r, g, b)` 获取 `Color`，可以使 `Color`对象复用。其中 rbg 是  0.0 ~ 1.0 的 double。

也可以使用 `Color.rgb(r, g, b)` 其中的 rgb 为 0 ~ 255 之间的整数。

另一种方法是用 HSB 颜色系统表示颜色，HSB 使用 色调、饱和度、亮度 来表示颜色。色调的范围是 0.0 ~ 360.0，饱和度、亮度的范围是 0.0 ~ 1.0。可以使用 ` Color.hsb(h,s,b)` 产生 HSB 表示的颜色。

`Color` 类是  `Paint` 的子类。`Paint` 表示可以用来填充和描边的东西。

### Font

`Font` 表示字体，影响文本大小和文本样式。

可以使用 `Font.font(family, weight, posture, size)` 获取字体， family 表示字体名称，weight 表示字体粗细，例如 `FontWeight.BOLD`、 `FontWeight.NORMAL`，posture 表示字体形态，如 ` FontPosture.ITALIC`、`FontPosture.REGULAR`。

### Image

`Image` 表示图像。

可以使用 `new Image(path)` 从资源文件中加载图片。

###  Canvas and GraphicsContext

`Canvas` 类可以在上面绘制自定义的内容。`Canvas`是 `Node` 的子类。`Canvas`的坐标系原点在左上角。

使用 `new Canvas(width, height)`创建画布， width、height 单位都是像素。

画布的默认颜色是完全透明的黑色。

可以使用 `canvas.getGraphicsContext2D()` 获取 `GraphicsContext` 对象，可以使用 `GraphicsContext` 在画布上绘制。

# Basic Events

事件是 GUI 编程的核心。用户的操作会生成对应的事件，事件会包括相关的信息。事件构建后，会交给 **事件处理器 event handler **进行处理。

GUI 程序存在一个**事件循环 event loop**，会不断等待事件的发生，并处理事件。

### Event Handling

**事件监听器 event listener**  负责  **监听 listen** 事件，在 JavaFX 里，监听器会实现 `EventHandler`接口并提供 `handle(event)`方法。

一个事件可能会被多个组件接收，被注册了监听器的对象被称为 **事件源 source**，可以通过 `evt.consume()`消费掉一个事件，避免被其他监听器接收。 

### Mouse Events

鼠标事件的类型是 `MouseEvent`，操作鼠标时会产生鼠标按下、释放、单击三种事件。移动鼠标时也会产生一系列鼠标移动事件。可以在组件上 `setOnMousePressed(handler)`、`setOnMouseMoved(handler)` 设置对应的监听器。

发生鼠标事件时，可以使用 ` evt.getX()`、`evt.getY()` 获取鼠标事件的位置，坐标以组件的左上角为原点。

可以在发生鼠标时用`evt.isShiftDown()`、`evt.isControlDown()`、`evt.isAltDown()`判断某些按键是否按下。用 `evt.getButton()` 获取鼠标按下的是哪个按钮，也可以用 `evt.isPrimaryButtonDown()`判断该按钮是否按下。

### Dragging

处理拖动事件需要对 `MousePressed`、`MouseDragged`、`MouseReleased`进行处理。可能需要记录`prevX`、`prevY`以及按下时的事件`MousePressed `等信息。另外设置 `dragging` 变量判断是否在拖动中，可以避免重复进入拖动的情况。

拖动有一些很常见的应用，例如允许用户拖动鼠标绘制线条。

### Key Events

GUI组件使用 `输入焦点 input focus`决定键盘事件的目标。同一时间屏幕上只有一个元素有输入焦点。可以使用  `comp.requestFocus()`获取焦点，用 `comp.isFocused()`判断是否有焦点。对 `stage` 使用该方法，可以使windows窗口变为活动窗口，并移到屏幕最前。

键盘事件的类型是 `KeyEvent`。也有按下、释放、键入三种事件。

使用 `evt.getCode()` 可以获取按下的是哪个键。` evt.getCharacter()`获取输入键对应的字符。

### AnimationTimer

可以使用 `AnimationTimer` 来编写动画。

使用 `animator.start()`、`animator.stop()`来控制动画，理想情况下 JavaFX application thread 会每 1/60 秒调用一次 `handle()`来进行动画。

### State Machines

存储在对象的实例变量中的信息被称为表示该对象的**状态 state**。对象的行为可能受状态的影响。在计算机科学中，存在**状态机 state machine**的概念，可以响应事件，从一种状态改变到另一种状态。

### Observable Values

当 **observable value** 改变时，会产生一个事件。可以注册 `ChangeListener`监听值的改变。

# Basic Controls

`Control` 类定义了可控制的组件，含有以下方法。

```java
control.setDisable(isDisable) 
control.isDisabled() 
control.setToolTipText(string) 
control.setStyle(cssString) 
```

### ImageView

`ImageView` 可以用来显示图片。`Image` 不是 `Node` ，无法直接添加到场景图中，`ImageView` 是 `Image` 一个简单的包装。

### Label and Button

即将看到的几个组件都只能显示字符串或图像，而不能编辑，这几个类都继承于 `Labeled`。`Labeled`含有这些方法：

```java
setText(string)
setGraphic(node) 
setFont(font)
setTextFill(color)
setGraphicTextGap(size)
setContentDisplay(displayCode)
setBackground(background)
```

`Label ` 是最简单的这种组件，只能显示不能编辑的文本和图形。

`Button` 被点击以后会触发 `ActionEvent`，可以通过 `setDefaultButton(true)` 将按钮设为该窗口默认按钮。

### CheckBox and RadioButton

`CheckBox` 代表复选框。使用 `setSelected(isSelected)`、`isSelected()` 设置、获取该复选框是否被选中。用户改变复选框状态时会触发  `ActionEvent`，而`setSelected(isSelected)`并不会触发这个事件，可以使用 `fire()` 方法模拟用户点击。

`RadioButton`代表单选框。单选框通常被放在 `ToggleGroup` 中，一个 Group 中只能有一个单选框处于选中状态。 `ToggleGroup` 并不是一个组件，并不会显示。通过 `radioButton.setToggleGroup(toggleGroup)` 将一个单选框加入一个 Group。

### Memu

`MenuItem `  表示菜单栏中的一项。`MenuItem ` 的使用与`Button` 差不多。`MenuItem` 可以出现在 `Menu` 中。可以通过`menu.add(menuItem)`添加 `MenuItem ` 。菜单应该被添加进菜单栏中 `MenuBar`，`menuBar.getMenus().add(menu)`。

菜单栏通常会被放在页面的顶部，可以使用 `BorderPane` 进行布局。

`MenuItem` 还有一些子类，例如 `SeparatorMenuItem` 可以添加另一个菜单作为菜单项。`CheckMenuItem `和 `RadioMenuItem` 则类似于 `CheckBox` 和 `RadioButton`。

### TextField and TextArea

`TextField`、 `TextArea` 都是用户可以编辑的文本框。`TextField`只能输入一行文本，而`TextArea` 可以输入多行。这两个类都是 `TextInputControl` 的子类。它们的方法如下：

```java
 setText(text)
 appendText(text)
 setFont(font)
 setEditable(isEditable)
 selectAll()
```

另外，使用 `setPrefColumnCount(n)`可以设置输入框的列数（宽度），对 `TextArea` 可以设置 `setPrefRowCount(n)` 行数，`setWrapText(isWarp)`设置文本超过宽度后是否自动换行。

### Slider

`Slider ` 允许用户拖动滑块选择一个值。

创建 `Slider` 对象时可以调整值的范围。可以通过 `setOrientation(Orientation.VERTICAL)` 将滑块设为垂直方向。使用 `setValue(val)` 设置滑块的值。

` setMajorTickSpacing(x)` 表示在滑条上每隔 x 长度显示一个主刻度。`setMinorTickCount(n)` 表示在滑块上一个设置多少个次刻度。`setShowTickMarks(true)` 显示刻度。

`setSnapToTicks(true)` 可以设置只能拖到最近的刻度，这不会对  `setValue(val)` 进行限制。`adjustValue(x)` 可以让滑块到最接近 x 的刻度。

# Basic Layout

`Layout` 是用于安排 children 大小和位置的容器。

每个组件都有最小长宽、最大长宽、首选长宽。布局时可能会参考这些信息。可以通过 `setMinSize(w,h)`、 `setMaxSize(w,h)`、 `setPrefSize(w,h)`等方法进行设置

在 JavaFX 中进行布局的是 `Pane` 及其子类。

###  Do Your Own Layout

使用 `node.relocate(x, y)` 设置组件的位置，坐标系以包含它的容器的左上角为原点。

`node.resize(width, height)`设置组件的大小，对不可调节大小的组件没有影响。

若组件位于进行布局的容器中，这两个方法可能不会起作用。使用 `node.setManaged(false)` 可以让布局对这个组件失去作用。

若进行复杂的布局管理，可能需要继承 `Pane` 并覆盖 `layoutChildren()` 方法。

### BorderPane

`BorderPane` 的中间是个大组件，四周是可以包含四个小组件。可以如下设置，传入 `null` 则会移除组件。

```java
pane.setCenter(node);
pane.setTop(node);
pane.setRight(node);
pane.setBottom(node);
pane.setLeft(node);
```

在 `BorderPane` 中，可以对子项设置外边距 `BorderPane.setMargin(child, insets)`，外边距的对象是`Insets`，使用 `new Insets(top,right,bottom,left)` 来创建。

### HBox and VBox

`HBox`可以将一些组件放在一个水平行中，`VBos` 可以将组件放在垂直列中。可以通过 `hbox.getChildren().add(child) ` 添加一个组件。

使用 `hbox.setSpacing(gapAmount)`可以设置间距。

在 `HBox` 足够大时，可以使用 `HBox.setHgrow(child, priority)` 给 chirdren 优先级，让它们根据优先级扩大大小。

可以使用 `hbox.setAlignment(position)` 设置 children 在 `HBox` 里的位置，默认是 `Pos.TOP_LEFT`。

### GridPane and TilePane

`GridPane` 是可以设置行列数的网格布局，行号、列号从 0 开始。用 `grid.add(child, column, row)` 添加一个组件。每个组件可以跨多行多列，`grid.add(child, column, row, columnCount, rowCount)`。并且可以用如下方法设置行列的间距。

```java
grid.setHGap(gapSize)
gris.setVGap(gapSize)
```

可以用如下方法设置行列的默认大小

```java
gridpane.getRowConstraints().addAll(
     new RowConstraints(100), // row 0 has height 100 pixels 
     new RowConstraints(150), // row 1 has height 150 pixels 
     new RowConstraints(100), // row 2 has height 100 pixels 
     new RowConstraints(200), // row 3 has height 200 pixels 
);
```

也可以使用百分比作为大小

```java
 ColumnConstraints constraints = new ColumnConstraints();
 constraints.setPercentWidth(20); // (there is no constructor that does this)
 gridpane.getColumnConstraints().add(constraints);
```

`TilePane` 是所有矩形大小相同的网格布局。使用 `tpane.setPrefColumns(cols)` 设置列数，在这种情况下，`TilePane` 的行数会随组件的添加而增加。

