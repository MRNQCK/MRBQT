package at.shadowbot.sql;

public class Manager {
    public void createIntTable(String table) {
        (new SqlManager()).createIntTable(table);
    }

    public void createStringTable(String table) {
        (new SqlManager()).createStringTable(table);
    }

    public void createUser(String userID, String table, String type) {
        (new SqlManager()).createUser(userID, table, type);
    }

    public void createUserString(String userID, String table, String type) {
        (new SqlManager()).createUserString(userID, table, type);
    }

    public void addInt(String userID, String table, String type, int value) {
        (new SqlManager()).addInt(userID, table, type, value);
    }

    public void removeInt(String userID, String table, String type, int value) {
        (new SqlManager()).removeInt(userID, table, type, value);
    }

    public void setInt(String userID, String table, String type, int value) {
        (new SqlManager()).setInt(userID, table, type, value);
    }

    public void setString(String userID, String table, String type, String value) {
        (new SqlManager()).setString(userID, table, type, value);
    }

    public Integer getInt(String userID, String table, String type) {
        return (new SqlManager()).getInt(userID, table, type);
    }

    public String getString(String userID, String table, String type) {
        return (new SqlManager()).getString(userID, table, type);
    }
}