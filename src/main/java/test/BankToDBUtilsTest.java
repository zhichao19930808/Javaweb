package test;

import entity.BankUser;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.testng.annotations.Test;
import tools.BankToDBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankToDBUtilsTest {
    @Test
    public void addUser() {

    }

    @Test
    public void selUser() {
        //获取数据源
        QueryRunner queryRunner = new QueryRunner(BankToDBUtils.getDataSource());
        String sql = "SELECT d.userId,u.userName,u.password,d.birthdate,d.gender,d.profession,p.money,r.grade\n" +
                "FROM db_bank.user u INNER JOIN db_bank.data d INNER JOIN db_bank.purse p INNER JOIN db_bank.reputation r\n" +
                "ON u.id = d.userId =p.userId=r.userId;";
        try {
            List<BankUser> list = queryRunner.query(sql, new ResultSetHandler<List<BankUser>>() {
                @Override
                public List<BankUser> handle(ResultSet rs) throws SQLException {
                    List<BankUser> list1 = new ArrayList<>();
                    while (rs.next()) {
                        BankUser bankUser = new BankUser(rs.getString("userName"),
                                rs.getString("password"),
                                rs.getInt("userId"),
                                rs.getDate("birthdate"),
                                rs.getString("gender"),
                                rs.getString("profession"),
                                rs.getString("money"),
                                rs.getString("grade")
                                );
                        list1.add(bankUser);
                    }
                    return list1;
                }
            });
            for (BankUser bankUser : list) {
                System.out.println(bankUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
