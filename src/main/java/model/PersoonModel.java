package model;

public class PersoonModel {
    private int id;
    private String naam;
    private int leeftijd;
    private String wachtwoord;

    public PersoonModel(int id, String naam, int leeftijd, String wachtwoord) {
        System.out.println("[PersoonModel] Constructor called");
        this.id = id;
        this.naam = naam;
        this.leeftijd = leeftijd;
        this.wachtwoord = wachtwoord;
        System.out.println("[PersoonModel] Created: " + this);
    }

    public int getId() { return id; }
    public String getNaam() { return naam; }
    public int getLeeftijd() { return leeftijd; }
    public String getWachtwoord() { return wachtwoord; }

    public void setNaam(String naam) {
        System.out.println("[PersoonModel] setNaam called with: " + naam);
        this.naam = naam;
    }

    public void setLeeftijd(int leeftijd) {
        System.out.println("[PersoonModel] setLeeftijd called with: " + leeftijd);
        this.leeftijd = leeftijd;
    }

    public void setWachtwoord(String wachtwoord) {
        System.out.println("[PersoonModel] setWachtwoord called with: " + wachtwoord);
        this.wachtwoord = wachtwoord;
    }

    @Override
    public String toString() {
        return "PersoonModel{id=" + id + ", naam='" + naam + "', leeftijd=" + leeftijd + ", wachtwoord='" + wachtwoord + "'}";
    }
}
