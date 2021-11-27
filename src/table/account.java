package table;

public class account {
    String username, pass;

    public int getBan_unban() {
        return ban_unban;
    }

    public void setBan_unban(int ban_unban) {
        this.ban_unban = ban_unban;
    }

    int user_role, ban_unban;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }
}
