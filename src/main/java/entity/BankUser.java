package entity;

import javax.xml.crypto.Data;
import java.sql.Date;

public class BankUser {
    private int id ;
    private String userName;//用户名
    private String password;//密码

    private int userId;//用户id
    private Date birthdate;//出生日期
    private String gender;//性别
    private String profession;//职业

    private String money;//余额

    private String grade;//信誉度



    public BankUser(String userName, String password, int userId, Date birthdate, String gender, String profession, String money, String grade) {
        this.userName = userName;
        this.password = password;
        this.userId = userId;
        this.birthdate = birthdate;
        this.gender = gender;
        this.profession = profession;
        this.money = money;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getProfession() {
        return profession;
    }

    public String getMoney() {
        return money;
    }

    public String getGrade() {
        return grade;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "BankUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", profession='" + profession + '\'' +
                ", money='" + money + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
