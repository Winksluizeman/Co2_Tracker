package model;

public class PersoonModel {
    private int id;
    private String naam;
    private int leeftijd;
    private String wachtwoord;

    public PersoonModel(int id, String naam, int leeftijd, String wachtwoord) {
        this.id = id;
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.wachtwoord = wachtwoord;
        System.out.println("PersoonModel created: " + this);
    }

    public int getId() { return id; }
    public String getNaam() { return naam; }
    public int getLeeftijd() { return leeftijd; }
    public String getWachtwoord() { return wachtwoord; }

    public void setNaam(String naam) { this.naam = naam; }
    public void setLeeftijd(int leeftijd) { this.leeftijd = leeftijd; }
    public void setWachtwoord(String wachtwoord) { this.wachtwoord = wachtwoord; }

    @Override
    public String toString() {
        return "PersoonModel{id=" + id + ", naam='" + naam + "', leeftijd=" + leeftijd + ", wachtwoord='" + wachtwoord + "'}";

    }
}
