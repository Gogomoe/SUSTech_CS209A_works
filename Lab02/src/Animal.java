abstract public class Animal {

    protected int speed;
    protected String imageFileName;

    public Animal(int speed, String imageFileName) {
        this.speed = speed;
        this.imageFileName = imageFileName;
    }

    abstract public void raceDraw(int s, int time, int raceNo, int raceHeight);

    protected void draw(int s, int raceNo, int raceHeight, int runTime) {
        double distance = runTime * speed * CFG.SPEED_SCALE;

        Picture picture = new Picture(imageFileName);
        int scaledHeight = (int) (AnimalRace.raceHeight * 0.8);
        int scaledWidth = picture.width() * scaledHeight / picture.height();

        StdDraw.picture(s + distance, raceNo * raceHeight, imageFileName, scaledWidth, scaledHeight);
    }
}
