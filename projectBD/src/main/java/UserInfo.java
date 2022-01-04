import java.util.Objects;

public class UserInfo {
    private int id;
    private String username;
    private String password;

    public UserInfo(String username, String password){
        super();
        this.username = username;
        this.password = password;
    }

    public UserInfo(int id, String username, String password){
        super();
        this.id       = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id && Objects.equals(username, userInfo.username) && Objects.equals(password, userInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
