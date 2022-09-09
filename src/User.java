public class User {
    private final String userName;
    private final String email;

    // private static HashMap<String, String> users = new HashMap<>(); // TODO Public static final -> Skapa Users Class istället

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
