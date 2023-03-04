public class Location {
    private String name;
    private int x;
    private int y;
    private LocationType type;

    /**
     * Constructor pentru initializarea unei locatii
     * @param x
     * @param y
     * @param type
     * @param name
     */
    Location(int x,int y,LocationType type, String name){
        this.x=x;
        this.y=y;
        this.type=type;
        this.name = name;
    }

    /**
     * Returneaza coordonata X a locatiei din sistemul cartezian
     * @return x as integer
     */
    public int getX() {
        return x;
    }
    /**
     * Returneaza coordonata Y a locatiei din sistemul cartezian
     * @return y as integer
     */
    public int getY() {
        return y;
    }
    /**
     * Returneaza numele locatiei ca string
     * @return name as String
     */
    public String getName() {
        return name;
    }
    /**
     * Returneaza tipul locatiei din cele predefinite in clasa LocationType
     * @return type
     */
    public LocationType getType() {
        return type;
    }

    /**
     * Seteaza numele unei locatii (in caz ca se schimba)
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    

    /**
     * Formeaza un String care contine toate datele unei locatii
     * @return String
     */
    @Override
    public String toString(){
        return ("Nume locatie: " + this.getName() + "\n" +
                "Coordonata x: " + this.getX() +"\n"+
                "Coordonata y:" + this.getY() +"\n"+
                "Tip Locatie:" +this.getType());
    }

}
