//BubbleChartExample

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BubbleChartExample extends Application {
    @Override
    public void start(Stage stage) {
        //Defining the axes
        NumberAxis xAxis = new NumberAxis(0, 100, 10);
        xAxis.setLabel("Age");

        NumberAxis yAxis = new NumberAxis(20, 100, 10);
        yAxis.setLabel("Weight");

        //Creating the Bubble chart
        BubbleChart<Number, Number> bubbleChart = new BubbleChart<>(xAxis, yAxis);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("work");

        series.getData().add(new XYChart.Data<>(10, 30, 4));
        series.getData().add(new XYChart.Data<>(25, 40, 5));
        series.getData().add(new XYChart.Data<>(40, 50, 9));
        series.getData().add(new XYChart.Data<>(55, 60, 7));
        series.getData().add(new XYChart.Data<>(70, 70, 9));
        series.getData().add(new XYChart.Data<>(85, 80, 6));

        //Setting the data to bar chart
        bubbleChart.getData().add(series);

        //Creating a Group object
        Group root = new Group(bubbleChart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Bubble Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
