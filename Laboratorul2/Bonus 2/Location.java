public abstract class Location {
    protected String name;
    protected double x;
    protected double y;
    protected String type;
    private int Index = -1;

    Location() {

    }

    /**
     * Constructor pentru initializarea unei locatii
     *
     * @param x    coordonata x a locatiei(numar real)
     * @param y    coordonata y a locatiei(numar real)
     * @param name numele locatiei
     */
    Location(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    /**
     * Returneaza coordonata X a locatiei din sistemul cartezian
     *
     * @return x numar real
     */
    public double getX() {
        return x;
    }

    /**
     * Returneaza coordonata Y a locatiei din sistemul cartezian
     *
     * @return y numar real
     */
    public double getY() {
        return y;
    }

    /**
     * Returneaza numele locatiei
     *
     * @return name Numele locatiei
     */
    public String getName() {
        return name;
    }

    /**
     * Seteaza numele unei locatii (in caz ca se schimba)
     *
     * @param name Numele locatiei (String)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returneaza tipul locatiei
     *
     * @return type Tipul locatiei (String)
     */
    public String getType() {
        return type;
    }
    /**
     * Returneaza indexul locatiei in array
     *
     * @return Index Indexul locatiei (intreg)
     */
    public int getIndex() {
        return Index;
    }

    /**
     * Seteaza Indexul locatiei in array
     * @param Index Indexul locatiei (intreg)
     */
    public void setIndex(int Index) {
        this.Index = Index;
    }

    /**
     * Formeaza un String care contine toate datele unei locatii
     *
     * @return String
     */
    @Override
    public String toString() {
        return ("Nume locatie: " + this.getName() + "\n" +
                "Coordonata x: " + this.getX() + "\n" +
                "Coordonata y:" + this.getY() + "\n" +
                "Tip Locatie:" + this.getType());
    }

    /**
     * Verifica daca doua locatii sunt egale (au acelasi nume/adresa)
     *
     * @param obj of type Object
     * @return true daca sunt egale, false daca nu sunt egale
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        return name.equals(other.name) && x == other.x && y == other.y;
    }


}
