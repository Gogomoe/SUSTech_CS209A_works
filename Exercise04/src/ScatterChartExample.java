//ScatterChartExample

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ScatterChartExample extends Application {
    @Override
    public void start(Stage stage) {
        //Defining the axes
        NumberAxis xAxis = new NumberAxis(0, 12, 3);
        xAxis.setLabel("Area");

        NumberAxis yAxis = new NumberAxis(0, 16, 4);
        yAxis.setLabel("Weight");

        //Creating the Scatter chart
        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(8, 12));
        series.getData().add(new XYChart.Data<>(4, 5.5));
        series.getData().add(new XYChart.Data<>(11, 14));
        series.getData().add(new XYChart.Data<>(4, 5));
        series.getData().add(new XYChart.Data<>(3, 3.5));
        series.getData().add(new XYChart.Data<>(6.5, 7));

        //Setting the data to scatter chart
        scatterChart.getData().addAll(series);

        //Creating a Group object
        Group root = new Group(scatterChart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Scatter Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
