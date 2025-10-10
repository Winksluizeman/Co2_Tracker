package model;

public class PersoonModel {
    private int id;
    private String naam;
    private int leeftijd;

    public PersoonModel(int id, String naam, int leeftijd) {
        this.id = id;
        this.naam = naam;
        this.leeftijd = leeftijd;
    }

    public int getId() { return id; }
    public String getNaam() { return naam; }
    public int getLeeftijd() { return leeftijd; }

    public void setNaam(String naam) { this.naam = naam; }
    public void setLeeftijd(int leeftijd) { this.leeftijd = leeftijd; }

    @Override
    public String toString() {
        return "Persoon{id=" + id + ", naam='" + naam + "', leeftijd=" + leeftijd + "}";
    }
}
