package com.company;

import java.sql.*;
import java.util.Scanner;

import static java.lang.Class.forName;

public class Main {
    //0.准备4个常量
    final static String driver = "com.mysql.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost:3306/mydb";
    final static String username = "root";
    final static String password = "1225";

    //简单更新数据库操作
    public static void updateMysql(){



        try {
            //1.加载驱动
            Class.forName(driver);
            //2.获取数据库驱动
            Connection connection = DriverManager.getConnection(url,username,password);
            //3,执行数据库驱动(即向数据库发送SQL语句)
//            String sql = "UPDATE t_user SET age = '20' WHERE id = '2'";
            String sql = "INSERT INTO t_user(id,username,age,password) VALUES('5','tommy','12','123456')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            //4.释放
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //简单查询数据库操作
    public static void selectMysql(){
        try {
            //1.加载驱动
            Class.forName(driver);
            //2.获取数据库驱动
            Connection connection = DriverManager.getConnection(url,username,password);
            //3.执行数据库驱动
            String sql = "SELECT * FROM t_user";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                int age = resultSet.getInt("age");
                String password = resultSet.getString("password");

                System.out.println("id->" + id + " username->" + username + " age->" + age + " password->" + password);
            }

            //4.释放
            resultSet.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //简单查询语句（带参）
    public static void loginApp(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入账号！");
        String inputUsername = scanner.nextLine();
        System.out.println("请输入密码！");
        String inputPassword = scanner.nextLine();

        try {
            //1.加载驱动
            Class.forName(driver);
            //2.获取数据库驱动
            Connection connection = DriverManager.getConnection(url,username,password);
            //3.执行数据库驱动
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            //注意含参时的变化
            PreparedStatement statement = connection.prepareStatement(sql);
            //给参数赋值
            statement.setString(1,inputUsername);
            statement.setString(2,inputPassword);

            //注意executeQuery()不传sql,前面已传过sql
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                System.out.println("登陆成功！");
            } else {
                System.out.println("账号或密码错误！");
            }

            //4.释放
            resultSet.close();
            statement.close();
            connection.close();



        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//        updateMysql();
//        selectMysql();
        loginApp();
    }
}
