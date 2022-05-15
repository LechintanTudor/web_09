package ro.ubb.imgur.model;

public class Account {
    public long id;
    public String username;
    public String password;

    public Account(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("Account { id: %d, username: \"%s\", password: \"%s\" }", id, username, password);
    }
}
