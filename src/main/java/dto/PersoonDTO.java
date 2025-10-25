package dto;

public class PersoonDTO {
    private String username;
    private String password;
    private int age;
    private String email;

    public PersoonDTO() {
        System.out.println("[PersoonDTO] Default constructor called (JSON deserialisatie)");
    }

    public PersoonDTO(String username, int age, String password, String email) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.email = email;
        System.out.println("[PersoonDTO] Constructor called with values: " + this);
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "PersoonDTO{username='" + username + "', email='" + email + "', age=" + age + ", password='" + password + "'}";
    }
}
