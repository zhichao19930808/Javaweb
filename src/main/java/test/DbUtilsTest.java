package test;

import org.apache.commons.dbutils.QueryRunner;
import org.testng.annotations.Test;
import entity.User;
import tools.DbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtilsTest {

        @Test
        public void sel() {
            QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
            String sql = "select * from db_gg.account";
                try {
                    List<User> list =queryRunner.query(sql, rs -> {
                        List<User> list1 = new ArrayList<>();
                        while (rs.next()) {
                            User user = new User(rs.getString("name"), rs.getString("password"));
                            list1.add(user);
                        }
                        return list1;
                    });
                    for (User user : list) {
                        System.out.println(user);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
}
