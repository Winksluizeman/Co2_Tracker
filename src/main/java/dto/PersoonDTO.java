package dto;

public class PersoonDTO {
    private String username;
    private String password;
    private int age;

    public PersoonDTO() {} // nodig voor JSON deserialisatie

    public PersoonDTO(String username, int age, String password) {
        this.username = username;
        this.age = age;
        this.password = password;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "PersoonDTO{username='" + username + "', age=" + age + ", password='" + password + "'}";
    }
}
