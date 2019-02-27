import static java.lang.Math.min;

public class Rabbit extends Animal {

    public Rabbit(int speed, String imageFileName) {
        super(speed, imageFileName);
    }

    @Override
    public void raceDraw(int s, int time, int raceNo, int raceHeight) {
        int runTime = (time / 4000) * 2000 + min((time % 4000), 2000);
        draw(s, raceNo, raceHeight, runTime);
    }


}
