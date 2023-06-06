public class gasStation extends Location {
    private double gasPrice;

    gasStation() {

    }

    /**
     * Constructor pentru initializarea unei banzinarii
     *
     * @param x        coordonata x a benzinariei(numar real)
     * @param y        coordonata y a benzinariei(numar real)
     * @param name     numele benzinariei
     * @param gasPrice pretul combustibilului la benzinaria respectiva
     */
    gasStation(double x, double y, String name, double gasPrice) {
        super(x, y, name);
        this.type = "Gas_Station";
        this.gasPrice = gasPrice;
    }

    /**
     * Returneaza pretul combustibilului la benzinaria respectiva
     *
     * @return gasPrice (numar real, exprimat in RON)
     */
    public double getGasPrice() {
        return gasPrice;
    }

    /**
     * Seteaza pretul combustibilului la benzinaria respectiva
     *
     * @param gasPrice (numar real, exprimat in RON)
     */
    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    /**
     * Formeaza un String care contine toate datele unei benzinarii
     *
     * @return String
     */
    @Override
    public String toString() {
        return "gasStation{" +
                "gasPrice=" + gasPrice +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
