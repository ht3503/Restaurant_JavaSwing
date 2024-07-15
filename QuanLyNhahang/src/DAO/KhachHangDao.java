/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.KhachHang;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author AVITA
 */
public class KhachHangDao {
    public static ArrayList<KhachHang> LayDanhSachKhachHang() {
        ArrayList<KhachHang> dsPB = new ArrayList<KhachHang>();
        try {
            String sql = "SELECT * FROM KhachHang";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                KhachHang pb = new KhachHang();
                pb.setMaKhachHang(rs.getInt("MaKhachHang"));
                pb.setHoTen(rs.getString("HoTen"));
                pb.setSoDienThoai(rs.getString("SoDienThoai"));
                pb.setEmail(rs.getString("Email"));
                pb.setDiaChi(rs.getString("DiaChi"));
                dsPB.add(pb);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
    
     public static boolean ThemKhachHang(KhachHang khachHang) {
        boolean ketQua = false;
        try {
            String sql = String.format("INSERT INTO KhachHang (HoTen, SoDienThoai, Email, DiaChi) VALUES (N'%s', N'%s', N'%s', N'%s');",
                                       khachHang.getHoTen(), khachHang.getSoDienThoai(), khachHang.getEmail(), khachHang.getDiaChi());
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

    public static boolean XoaKhachHang(int maKhachHang) {
        boolean ketQua = false;
        try {
            String sql = String.format("DELETE FROM KhachHang WHERE MaKhachHang = %d;", maKhachHang);
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

    public static boolean SuaThongTinKhachHang(KhachHang khachHang) {
        boolean ketQua = false;
        try {
            String sql = String.format("UPDATE KhachHang SET HoTen=N'%s', SoDienThoai=N'%s', Email=N'%s', DiaChi=N'%s' WHERE MaKhachHang=%d;",
                                       khachHang.getHoTen(), khachHang.getSoDienThoai(), khachHang.getEmail(), khachHang.getDiaChi(), khachHang.getMaKhachHang());
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
    
     public static ArrayList<Integer> layDanhSachMaKhachHang() {
    ArrayList<Integer> dsMaKhachHang = new ArrayList<>();
    try {
        String sql = "SELECT MaKhachHang FROM KhachHang";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        while (rs.next()) {
            dsMaKhachHang.add(rs.getInt("MaKhachHang"));
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return dsMaKhachHang;
}
     public static ArrayList<String> layDanhSachTenKhachHang() {
    ArrayList<String> dsTenKhachHang = new ArrayList<>();
    try {
        String sql = "SELECT HoTen FROM KhachHang";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        while (rs.next()) {
            dsTenKhachHang.add(rs.getString("HoTen"));
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return dsTenKhachHang;
}

}
