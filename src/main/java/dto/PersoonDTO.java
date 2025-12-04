package dto;

public class PersoonDTO {
    private String username;
    private int age;
    private String password;
    private String email;

    public PersoonDTO() {}

    public PersoonDTO(String username, int age, String password, String email) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.email = email;
    }

    public String getUsername() { return username; }
    public int getAge() { return age; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
