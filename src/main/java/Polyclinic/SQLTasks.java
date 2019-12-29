package Polyclinic;

import java.sql.*;
import java.util.logging.Logger;

public class SQLTasks {
    private static Connection connection;

    public static void connect() {
        String serverName = "localhost:1433";
        String dbName = "Polyclinic";
        String connectionString = "jdbc:sqlserver://" + serverName
                + ";databaseName=" + dbName
                + ";IntegratedSecurity=true";

        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void test () {
        try {
            Statement stmt = connection.createStatement();
            String sql = "select * from Staff";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t"
                        + rs.getString("Name") + "\t"
                        + rs.getString("Position"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String[] verify(String login, String pass) {
        String[] result = {"-1", "-1"};
        try {
            CallableStatement cst = connection.prepareCall("{CALL getName(?, ?, ?, ?)}");
            cst.setString(1, login);
            cst.setString(2, pass);
            cst.registerOutParameter(3, Types.NVARCHAR);
            cst.registerOutParameter(4, Types.NVARCHAR);
            cst.execute();
            result[0] = cst.getString(3);
            result[1] = cst.getString(4);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return result;
        }
    }

    public static ResultSet getStaff() throws Exception {
        CallableStatement stmt = connection.prepareCall("{CALL getStaff()}");
        ResultSet rs = stmt.executeQuery();
        return rs;
    }
}
