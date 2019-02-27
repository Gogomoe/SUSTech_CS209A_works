import static java.lang.Math.min;

public class Pig extends Animal {

    public Pig(int speed, String imageFileName) {
        super(speed, imageFileName);
    }

    @Override
    public void raceDraw(int s, int time, int raceNo, int raceHeight) {
        int runTime = time / 1500 * 500 + min((time % 1500), 500);
        draw(s, raceNo, raceHeight, runTime);
    }
}
