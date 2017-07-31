package tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class BankToDBUtils {
    //生成数据源函数
    private static DataSource dataSource = new ComboPooledDataSource();

    //获取连接对象
    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
