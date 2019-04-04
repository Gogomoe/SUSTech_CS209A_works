public class Country {

    private String name;
    private int population;
    private int area;

    public static Country parse(String line) {
        String[] fields = line.split("\\|");
        Country country = new Country();
        country.name = fields[0];
        country.area = Integer.parseInt(fields[1]);
        country.population = Integer.parseInt(fields[2]);
        return country;
    }

    public String getName() {
        return name;
    }

    public int getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public String toString() {
        return name + "|" + area + "|" + population;
    }
}

