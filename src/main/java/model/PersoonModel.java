package model;

public class PersoonModel {
    private int id;
    private String username;
    private int age;
    private String password;
    private String email;

    public PersoonModel(int id, String username, int age, String password, String email) {
        System.out.println("[PersoonModel] Constructor called");
        this.id = id;
        this.username = username;
        this.age = age;
        this.password = password;
        this.email = email;
        System.out.println("[PersoonModel] Created: " + this);
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    public void setUsername(String username) {
        System.out.println("[PersoonModel] setNaam called with: " + username);
        this.username = username;
    }

    public void setAge(int age) {
        System.out.println("[PersoonModel] setLeeftijd called with: " + age);
        this.age = age;
    }

    public void setPassword(String password) {
        System.out.println("[PersoonModel] setWachtwoord called with: " + password);
        this.password = password;
    }

    public void setEmail(String email) {
        System.out.println("[PersoonModel] setEmail called with: " + email);
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersoonDTO{username='" + username + "', email='" + email + "', age=" + age + ", password='" + password + "'}";

    }
}
