package test;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.testng.annotations.Test;
import entity.User;
import tools.DbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbUtilsTest {

        @Test
        public void sel() {
            QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
            String sql = "select * from db_gg.account";
                try {
                    List<User> list =queryRunner.query(sql, rs -> {
                        List<User> list1 = new ArrayList<>();
                        while (rs.next()) {
                            User user = new User(rs.getInt("id"),rs.getString("name"), rs.getString("password"));
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

    @Test
    public void query() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "select * from db_gg.account";
        try {
            List<User> list = queryRunner.query(sql, new BeanListHandler<User>(User.class));
            for (User user : list) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void insert() {
        String sql = "insert into db_gg.account(name,password)values(?,?)";
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        try {
            queryRunner.update(sql,"tom","aaa");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        String sql = "update db_gg.account set name = ? where id = 1";
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        try {
            queryRunner.update(sql, "jack");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        String sql = "delete from db_gg.account where id=?";
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        try {
            queryRunner.update(sql, 3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void batch() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "insert into db_gg.account(name,password)values(?,?)";
        Object[][] params = new Object[10][];
        for (int i = 0; i < params.length; i++) {
            params[i] = new Object[]{"test"+i, "aaa"};
        }
        try {
            queryRunner.batch(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //ArrayHander 适合取一条记录，会把这个记录的每个列的值封装在一个Object数组中
    @Test
    public void arrayHandler() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "select * from db_gg.account";
        try {
            Object[] objects = queryRunner.query(sql, new ArrayHandler());
            for (Object object : objects) {
                System.out.print(object+"\t");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //arrayListHander 适合取多条数据 ， 会把每个记录的值 封装在一个object[]中，然后将这个数据封装到集合中
    @Test
    public void arrayListHandler() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "select * from db_gg.account";
        try {
            List<Object[]> list = queryRunner.query(sql, new ArrayListHandler());
            for (Object[] objects : list) {
                System.out.println("");
                for (Object object : objects) {
                    System.out.print(object+"\t");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //columnListHandler 适合取某一列的数据，会把这一列的数据封装在list集合中
    @Test
    public void columnListHandler() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "select * from db_gg.account";
        try {
            List<Object> list =queryRunner.query(sql, new ColumnListHandler<>("name"));
            for (Object o : list) {
                System.out.print(o + "\t");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //mapHandler 适合取一条记录，把当前数据的列名和列值放到map中
    @Test
    public void mapHandler() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "select * from db_gg.account where id = ?";
        try {
            Map<String, Object> map = queryRunner.query(sql, new MapHandler(),1);
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                System.out.println(stringObjectEntry.getKey()+"-->"+stringObjectEntry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //mapListHandler 适合取多条记录 把每条数据的列名 和  列值放到map中，并将map放到list中
    @Test
    public void mapListHandler() {
        QueryRunner queryRunner = new QueryRunner(DbUtils.getDataSoure());
        String sql = "select * from db_gg.account";
        try {
            List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler());
            for (Map<String, Object> map : list) {
                System.out.println("");
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    System.out.print(entry.getKey()+"-->"+entry.getValue()+"\t");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //BeanListHandler：适合取多条数据，把每条数据的列值封装在实体对象中，然后把实体对象封装在List集合中   List<实体对象>

}

