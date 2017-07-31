package test;

import tools.DBCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbCPTest {
    public static void main(String[] args) {
        Connection connection= DBCP.getConnection();
        String sql = "SELECT * FROM db_gg.account";
        try {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("ID:"+resultSet.getInt(1)+"\tname:"+resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
