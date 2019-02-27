public class Turtle extends Animal {

    public Turtle(int speed, String imageFileName) {
        super(speed, imageFileName);
    }

    @Override
    public void raceDraw(int s, int time, int raceNo, int raceHeight) {
        draw(s, raceNo, raceHeight, time);
    }
}
