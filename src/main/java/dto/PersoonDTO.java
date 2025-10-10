// dto/PersoonDTO.java
package dto;

public class PersoonDTO {
    private String naam;
    private int leeftijd;

    public PersoonDTO(String naam, int leeftijd) {
        this.naam = naam;
        this.leeftijd = leeftijd;
    }

    public String getNaam() { return naam; }
    public int getLeeftijd() { return leeftijd; }
}