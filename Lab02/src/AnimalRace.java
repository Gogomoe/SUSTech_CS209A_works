import java.util.ArrayList;
import java.util.List;

public class AnimalRace {

    static int raceHeight = 200;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(CFG.DEFAULT_CANVAS_WIDTH, CFG.DEFAULT_CANVAS_HEIGHT);
        StdDraw.setScale(CFG.DEFAULT_SCALE_MIN, CFG.DEFAULT_SCALE_MAX);

        StdDraw.enableDoubleBuffering();

        List<Animal> animals = new ArrayList<>();
        animals.add(new Rabbit(80, "icon/rabbit.png"));
        animals.add(new Turtle(1, "icon/turtle.png"));
        animals.add(new Pig(55, "icon/pig.png"));

        raceHeight = (CFG.DEFAULT_SCALE_MAX - CFG.DEFAULT_SCALE_MIN) / animals.size();
        int start = (int) (CFG.DEFAULT_SCALE_MIN + 0.5 * raceHeight);
        int time = 0;
        while (true) {
            StdDraw.clear();
            for (int i = 0; i < animals.size(); i++) {
                animals.get(i).raceDraw(start, time, -animals.size() / 2 + i, raceHeight);
            }
            StdDraw.show();
            time += 100;
            StdDraw.pause(100);
        }

    }
}
