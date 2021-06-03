package at.shadowbot.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlManager {
    public void createIntTable(String table) {
        SQLAPI.getInstance().getMySQL().updateMySQL("CREATE TABLE IF NOT EXISTS " + table.toLowerCase() + " (UID VARCHAR(100), TYPE TEXT, VALUE INT)");
    }

    public void createStringTable(String table) {
        SQLAPI.getInstance().getMySQL().updateMySQL("CREATE TABLE IF NOT EXISTS " + table.toLowerCase() + " (UID VARCHAR(100), TYPE TEXT, VALUE TEXT)");
    }

    public boolean isExistUser(String userID, String table, String type) {
        try {
            PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("SELECT * FROM " + table.toLowerCase() + " WHERE UID = ? AND TYPE = ?");
            ps.setString(1, userID);
            ps.setString(2, type.toUpperCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return (rs.getString("TYPE") != null);
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void createUser(String userID, String table, String type) {
        if (!isExistUser(userID, table, type))
            try {
                PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("INSERT INTO " + table.toLowerCase() + " (UID, TYPE, VALUE) VALUES (?, ?, ?)");
                ps.setString(1, userID);
                ps.setString(2, type.toUpperCase());
                ps.setInt(3, 0);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public void createUserString(String userID, String table, String type) {
        if (!isExistUser(userID, table, type))
            try {
                PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("INSERT INTO " + table.toLowerCase() + " (UID, TYPE, VALUE) VALUES (?, ?, ?)");
                ps.setString(1, userID);
                ps.setString(2, type.toUpperCase());
                ps.setString(3, "");
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public Integer getInt(String userID, String table, String type) {
        try {
            PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("SELECT VALUE FROM " + table.toLowerCase() + " WHERE UID = ? AND TYPE = ?");
            ps.setString(1, userID);
            ps.setString(2, type.toUpperCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return Integer.valueOf(rs.getInt("VALUE"));
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Integer.valueOf(0);
    }

    public String getString(String serverID, String table, String type) {
        try {
            PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("SELECT VALUE FROM " + table.toLowerCase() + " WHERE UID = ? AND TYPE = ?");
            ps.setString(1, serverID);
            ps.setString(2, type.toUpperCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getString("VALUE");
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void setInt(String userID, String table, String type, int value) {
        if (isExistUser(userID, table, type))
            try {
                PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("UPDATE " + table.toLowerCase() + " SET VALUE = ? WHERE UID = ? AND TYPE = ?");
                ps.setInt(1, value);
                ps.setString(2, userID);
                ps.setString(3, type.toUpperCase());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public void setString(String serverID, String table, String type, String value) {
        if (isExistUser(serverID, table, type))
            try {
                PreparedStatement ps = SQLAPI.getInstance().getMySQL().getConnection().prepareStatement("UPDATE " + table.toLowerCase() + " SET VALUE = ? WHERE UID = ? AND TYPE = ?");
                ps.setString(1, value);
                ps.setString(2, serverID);
                ps.setString(3, type.toUpperCase());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
    }

    public void addInt(String userID, String table, String type, int value) {
        if (isExistUser(userID, table, type)) {
            setInt(userID, table, type, getInt(userID, table, type).intValue() + value);
        } else {
            createUser(userID, table, type);
        }
    }

    public void removeInt(String userID, String table, String type, int value) {
        if (isExistUser(userID, table, type)) {
            setInt(userID, table, type, getInt(userID, table, type).intValue() - value);
        } else {
            createUser(userID, table, type);
        }
    }
}