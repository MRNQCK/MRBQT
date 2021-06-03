package at.shadowbot.sql;

public class SQLAPI {
    private static SQLAPI instance;

    Manager statsManager;

    SqlConnector mySQL;

    public static SQLAPI getInstance() {
        return instance;
    }

    public Manager getStatsManager() {
        return this.statsManager;
    }

    public SqlConnector getMySQL() {
        return this.mySQL;
    }

    public SQLAPI() {
        instance = this;
        this.statsManager = new Manager();
        connectToMySQL("localhost", "mrnqck", "Baguette1337", "bot", 3306);
    }

    private void connectToMySQL(String host, String user, String password, String database, int port) {
        this.mySQL = new SqlConnector(host, user, password, database, port);
    }
}
