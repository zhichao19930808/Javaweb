package tools;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCP {
    //数据库连接池对象
    private static DataSource source;
    static{
        //操作属性集的类
        Properties properties = new Properties();
        //加载配置文件
        try {
            properties.load(new FileInputStream("dbcpconfig.properties"));
            source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        try {
            return source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
