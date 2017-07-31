package tools;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 自定义连接池
 */
public class MyDBPool {
    //存储连接池
    private static List<Connection> list;

    static {
        //获取dbinfo.properties文件中的配置信息
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dbinfo");
        String driverClassName = resourceBundle.getString("driverClassName");
        String url = resourceBundle.getString("url");
        String userName = resourceBundle.getString("userName");
        String password = resourceBundle.getString("password");
        //加载驱动
        try {
            Class.forName(driverClassName);
            //将获取到的连接对象存入到list中
            list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(DriverManager.getConnection(url, userName, password));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //获取连接对象
    public static Connection getConnection() {
        if (list.size() > 0) {
            return list.remove(0);
        } else {
            return null;
        }

    }

    //关闭连接
    public static void destory(Connection connection) {
        list.add(list.size(), connection);
    }
}
