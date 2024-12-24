package kg.Kinopoisk.Task2;

public class Cast {
    private final String fullName;
    private final String role;

    public Cast(String fullName, String role) {
        this.fullName = fullName;
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return fullName + " - " + role;
    }
}