# Properties and Bindings

### Observable Values

JavaFX 中许多的实例变量是可观察值 `observable values`，它们可以产生事件。

例如，宽高是 `DoubleProperty`，文本内容是 `StringProperty`，子节点是 `ObservableList<Node>`。

处理值变化的事件可以实现接口 `ChangeListener<T>`，并覆盖 `changed(target,oldValue,newValue)`。

另外，还有一种无效值事件，只在值无效时发生，会强制重新计算该值。实现`InvalidationListener `并覆盖` invalidated(obv)` 可以处理无效值。

### Bindable Properties

许多可观察值可以与另一个同类型的值绑定。

使用 `bind()` 方法可以将一个值绑定在另一个上，绑定时会立刻更新该值。可以使用 `unbind()`来解除绑定。

以下方法可以将值进行运算，形成新的值

```Java
asString()
greaterThan()
lessThanOrEqual()
isNotEqualTo()
multiply()
```

`When`类类似于 `?:` 运算符，可以使用 `new When(boolProp).then(trueVal).otherwise(falseVal)` 产生值。

使用  `bindBidirectional()` 实现双向绑定，任意一个值变化时，另一个同时发生变化。

# Fancier Graphics

`GraphicsContext` 使用于在 `Canvas` 上绘制的对象。

可以使用 `save()`方法保存`GraphicsContext` 当前状态，使用`restore()`弹出一个保存的状态。

### Fancier Strokes

`StrokeLineCap` 决定了线条终端的形状，可以使用 `setLineCap()`来设置，常见的值有 `StrokeLineCap.BUTT`、`StrokeLineCap.ROUND`、`StrokeLineCap.SQUARE`。`SQUARE`是默认值。

`StrokeLineJoin` 决定了线条顶点的形状，使用 `setLineJoin()` 设置，常见的值有 `StrokeLineJoin.MITER`、`StrokeLineJoin.ROUND`、`StrokeLineJoin.BEVEL`。`MITER` 是默认值。

使用 `setLineDashes(dash1, gap1, dash2, gap2, ...)` 可以设置虚线和空白交替的长度。

### Fancier Paints

`Paint` 表示填充颜色的涂料，`Color` 是 `Paint` 的一种。

`ImagePattern` 表示用图片作为填充颜色。使用 `new ImagePattern(pict, x, y, width, height, proportional)` 构造该对象。图片会被缩放到 width 及 height ，然后在水平和数值方向上重复。proportional  则是指定宽高是根据图片缩放还是根据Canvas缩放。

`LinearGradient` 表示线性渐变。通常用一个方向和每步对应的颜色表示。

```java
new LinearGradient(x1, y1, x2, y2,
                   proportional, cycleMethod,
                   stop1, stop2...);
```

`RadialGradient` 表示中心渐变。根据焦点方向、焦距、中心位置、半径等表示。

```java
new RadialGradient(focalAngle, focalDistance,
                   centerX, centerY, radius,
                   proportional, cycleMethod,
                   stop1, stop2...);
```

### Transforms

可以使用这些方法进行变换

```
translate
scale
rotation
shear
```

对于 shear 剪切，JavaFX并没有原生的方法进行变化。但是可以使用 `g.transform(new Affine(1, a, 0, 0, 1, 0));` 后面相当于是一个矩阵的线性变换。

### Pixel Manipulation

使用 `getPixelWriter()` 可以用来直接操作像素，例如 `pixWriter.setColor(x, y, color)`

另外，可以使用如下方法获取快照 `WritableImage nodePic = node.snapshot(null,null)` 。 快照是一个 `WritableImage ` ，使用 `getPixelReader()`方法读取像素。

### Image I/O

读取图片可以使用 `new Image(fileURL)`

为了保存图片，可以使用 `SwingFXUtils.fromFXImage()` 将 `Image` 转化为 `BufferedImage`，然后使用 `ImageIO.write()`保存图片。

# Complex Components and MVC

### MVC pattern

model 层控制数据，view 层负责展示数据，controller 层负责在两层之间传递事件。

### ListView and ComboBox

`ListView<T>` 允许用户在很多项中选择一项。它的类型可以直接是 `String` 或 `Node` 。另外，可以使用 `setSelectionMode(SelectionMode.MULTIPLE)` 将 List 设为多选。使用 `getSelectionModel().select(index)` 和 `getSelectionModel().getSelectedIndex()` 设置或获取选择项。

`setEditable(true)` 可以将List设为可编辑的。`setCellFactory(TextFieldListCell.forListView())`可以设置单元格工厂来自定义产生的单元格。若数据类型不是String，需要在工厂中提供`fromString`和`toString`方法。

`ComboBox`表示的是选择框，只有被选中的会显示，用法与 `ListView` 有些相似。

### TableView

`TableView<T>` 表示的是表格，`T` 表示每一行的数据结构，通过 `getItems()` 可以添加或删除行。

`TableColumn<T,S>`表示的是表格的列，`T `是表格的类型一致， `S` 是本列单元格的数据类型。通过 `setCellValueFactory(new PropertyValueFactory< T , String>("field name"))` 设置单元格值的工厂。通过 `setCellFactory(TextFieldTableCell.forTableColumn(myConverter))`设置单元格类型。 然后用 `table.getColumns().add()` 将列添加至表格。

# Mostly Windows and Dialogs

### Dialog Boxes

对话框是一个窗口的子窗口，如果父窗口关闭，对话框也会关闭。

对话框分 modal  和 modeless，modal  对话框会阻止你和父窗口交互。使用 `showAndWait()`显示对话框时，是 modal 的，会等待对话框关闭时才返回；使用 `show()` 时是 modeless 的，会立刻返回。

`Dialog<T>` 的泛型表示对话框的返回类型，`ButtonType`是一种常见的返回类型，包括`ButtonType.OK`、`ButtonType.CANCEL`、`ButtonType.YES`、`ButtonType.NO`等。

`Alert ` 是 `Dialog<ButtonType>` 的子类。常用的方法有

```java
Alert alert = new Alert(alertType, message);
alert.setTitle( windowTitle );
alert.setGraphic( node );
alert.setHeaderText( headerText );
```

另外，还有 `TextInputDialog ` 包含着一个 `TextField ` 可以由用户输入字符串。

### WebView and WebEngine

`WebView` 是JavaFX内置的一套浏览器引擎。使用 `getEngine()` 方法可以获得 `WebEngine` 类。

`WebEngine` 有如下方法

```java
setOnAlert();
setPromptHandler();
setConfirmHandler();
```

### Managing Multiple Windows

` Screen.getPrimary() ` 可以获得一个屏幕对象，然后通过 `getVisualBounds()` 获得屏幕大小。