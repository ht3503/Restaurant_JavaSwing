/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Ban;
import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author quang
 */
public class BanDAO {
    public static ArrayList<Ban> LayDanhSachBan() {
        ArrayList<Ban> dsPB = new ArrayList<Ban>();
        try {
            String sql = "SELECT * FROM Ban";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                Ban pb = new Ban();
                pb.setMaBan(rs.getInt("MaBan"));
                pb.setTenBan(rs.getString("TenBan"));
                pb.setTrangthai(rs.getString("Trangthai"));
                dsPB.add(pb);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
    
     public static boolean ThemBan(Ban ban) {

        boolean ketQua = false;

        String sql = String.format("INSERT INTO Ban (TenBan , Trangthai) VALUES (N'%s', N'%s');",

                ban.getTenBan(),ban.getTrangthai());

        SQLServerDataProvider provider = new SQLServerDataProvider();

        provider.open();

        int soLuong = provider.executeUpdate(sql);

        if (soLuong == 1) {

            ketQua = true;
        }

        provider.close();

        return ketQua;

    }
     
    public static boolean XoaBan(String maBan) {
    boolean ketQua = false;

    // Câu lệnh SQL để xóa một bàn dựa trên mã bàn
    String sql = String.format("DELETE FROM Ban WHERE MaBan = N'%s';", maBan);

    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();

    int soLuong = provider.executeUpdate(sql);

    if (soLuong == 1) {
        ketQua = true;
    }

    provider.close();

    return ketQua;
}
    public static boolean suaThongTinBan(Ban ban) {
    boolean ketQua = false;
    try {
       

        String sql = String.format("UPDATE Ban SET TenBan=N'%s', TrangThai=N'%s' WHERE MaBan=N'%s';",
                ban.getTenBan(),ban.getTrangthai(),ban.getMaBan());

        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        int soLuong = provider.executeUpdate(sql);
        if (soLuong == 1) {
            ketQua = true;
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return ketQua;
}
    
    public static Ban layThongTinBan(int maBan) {
    Ban ban = null;
    try {
        String sql = "SELECT * FROM Ban WHERE MaBan = " + maBan;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        if (rs.next()) {
            ban = new Ban();
            ban.setMaBan(rs.getInt("MaBan"));
            ban.setTenBan(rs.getString("TenBan"));
            ban.setTrangthai(rs.getString("Trangthai"));
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return ban;
}
public static boolean capNhatTrangThaiBan(int maBan, String trangThai) {
        boolean ketQua = false;
        try {
            // Xây dựng câu lệnh SQL trực tiếp
            String sql = String.format("UPDATE Ban SET Trangthai = N'%s' WHERE MaBan = %d", trangThai, maBan);
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            int soLuong = provider.executeUpdate(sql);
            if (soLuong == 1) {
                ketQua = true;
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ketQua;
    }
}

