public class City extends Location {
    private int population;

    City() {

    }

    /**
     * Constructor pentru initializarea unui oras
     *
     * @param x          coordonata x a orasului(numar real)
     * @param y          coordonata y a orasului(numar real)
     * @param name       numele orasului
     * @param population Numarul de locuitori ai orasului
     */
    City(double x, double y, String name, int population) {
        super(x, y, name);
        this.type = "City";
        this.population = population;
    }

    /**
     * Returneaza numarul de locuitori ai unui oras
     *
     * @return population (numar intreg)
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Seteaza numarul de locuitori ai unui oras
     *
     * @param population (numar intreg)
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Formeaza un String care contine toate datele unui oras
     *
     * @return String
     */
    @Override
    public String toString() {
        return "City{" +
                "population=" + population +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
