//AreaChartExample

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class AreaChartExample extends Application {
    @Override
    public void start(Stage stage) {
        //Defining the X axis
        CategoryAxis xAxis = new CategoryAxis();

        //defining the y Axis
        NumberAxis yAxis = new NumberAxis(0, 15, 2.5);
        yAxis.setLabel("Fruit units");

        //Creating the Area chart
        AreaChart<String, Number> areaChart = new AreaChart<>(xAxis, yAxis);
        areaChart.setTitle("Average fruit consumption during one week");

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("John");
        series1.getData().add(new XYChart.Data<>("Monday", 3));
        series1.getData().add(new XYChart.Data<>("Tuesday", 4));
        series1.getData().add(new XYChart.Data<>("Wednesday", 3));
        series1.getData().add(new XYChart.Data<>("Thursday", 5));
        series1.getData().add(new XYChart.Data<>("Friday", 4));
        series1.getData().add(new XYChart.Data<>("Saturday", 10));
        series1.getData().add(new XYChart.Data<>("Sunday", 12));

        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Jane");
        series2.getData().add(new XYChart.Data<>("Monday", 1));
        series2.getData().add(new XYChart.Data<>("Tuesday", 3));
        series2.getData().add(new XYChart.Data<>("Wednesday", 4));
        series2.getData().add(new XYChart.Data<>("Thursday", 3));
        series2.getData().add(new XYChart.Data<>("Friday", 3));
        series2.getData().add(new XYChart.Data<>("Saturday", 5));
        series2.getData().add(new XYChart.Data<>("Sunday", 4));

        //Setting the XYChart.Series objects to area chart
        areaChart.getData().addAll(series1, series2);

        //Creating a Group object
        Group root = new Group(areaChart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Area Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

