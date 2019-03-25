2019/03/25	17:20-19:00  总计 100 分钟

### 泛型

代码中存在大量泛型确实形成的 warning。例如在 `AreaChartExample`  中，以下泛型缺失

```java
AreaChart<String, Number>
XYChart.Series<String, Number>
XYChart.Data<String, Number>
```

将这些类型补上以后可以解决大部分的 warning。然而 `areaChart.getData().addAll()` 方法仍然会有泛型警告，查询得知，`addAll` 接收的是 vararg 参数，本质上是一个数组，然而数组会擦除泛型，导致这个 warning。

其中这些泛型类型决定于创建 `AreaChart` 时的两条坐标轴 `Axis`的泛型类型 。

另外在 `ScatterChartExample` 中，存在这样一条声明。

```java
ScatterChart<String, Number> scatterChart = new ScatterChart(xAxis, yAxis);
```

然而传入的两个坐标轴均为 `NumberAxis` ，也就是说正确类型应该是 `ScatterChart<Number, Number> ` ，这玩意是怎么编译过的。

### Axis

`Axis<T>` 的子类主要有 `CategoryAxis`、`NumberAxis` ，`CategoryAxis`是以 `String` 作为泛型类型。

### Series

`XYChart.Series<X,Y>` 用来表示一个系列。同一个系列图形的颜色通常是相同的。用工厂模式类比，位于一个系列中的元素处于相同的产品等级结构，而非产品族。

### Data

`XYChart.Data<X,Y>` 是一个简单的数据类型，用于储存 XY 坐标轴对应的值。另外有个 `extraValue` 属性可以设置，用于 `BubbleChart` 等图表类型中需要的额外的值。

### CSS

fxcss 一点都不优雅，与 css 相差较多，许多属性、值不支持。因此编写 fxcss 时还得经常查阅文档，查询属性、值是否被支持。