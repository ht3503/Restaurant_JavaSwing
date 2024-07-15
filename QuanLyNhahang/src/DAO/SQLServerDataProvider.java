/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author quang
 */
public class SQLServerDataProvider {
     Connection connection = null;
//    Statement statement = null;
//    ResultSet resultSet = null;
    String strSever = "DESKTOP-TOE0LMC";
    String strDatabase = "QuanLyNhaHang1";
    public void open () {
    try {
            String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
             String connectionURL="jdbc:sqlserver://"+strSever
                    +":1433;databaseName="+strDatabase
                     + ";integratedSecurity=true;encrypt=true;trustServerCertificate=true;";       
            connection=DriverManager.getConnection(connectionURL);
            if (connection!=null) {
                System.out.println("Ket noi thanh cong");
            } else {
                System.out.println("Ket noi that bai!!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}

public void close() {
    try {
        this.connection.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}

public ResultSet executeQuery(String sql) {
    ResultSet rs = null;
    try {
        Statement sm = connection.createStatement();
        rs = sm.executeQuery(sql);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return rs;
}

public int executeUpdate(String sql) {
    int n = -1;
    try {
        Statement sm = connection.createStatement();
        n = sm.executeUpdate(sql);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return n;
}

public static void main(String[] args) {
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        // Thực hiện các thao tác với cơ sở dữ liệu
        provider.close();
    }

    
}
