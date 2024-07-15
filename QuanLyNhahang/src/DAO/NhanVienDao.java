/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.NhanVien;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author AVITA
 */
public class NhanVienDao {
//    ID_NV, TenNV, NgayVL, SDT, Chucvu, ID_ND, ID_NQL, Tinhtrang
    public static ArrayList<NhanVien> LayDanhSachNhanVien() {
        ArrayList<NhanVien> dsPB = new ArrayList<NhanVien>();
        try {
            String sql = "SELECT * FROM NhanVien";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getInt("MaNhanVien"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setSoDienThoai(rs.getString("SoDienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
              
                dsPB.add(nv);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
    
    public static boolean ThemNhanVien(NhanVien nhanvien) {

        
        boolean ketQua = false;

         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayVaoLamFormatted = dateFormat.format(nhanvien.getNgayVaoLam());

        
        
        String sql = String.format("INSERT INTO NhanVien (HoTen, SoDienThoai, Email, ChucVu ,NgayVaoLam) VALUES (N'%s', N'%s', N'%s', N'%s', N'%s');",

                nhanvien.getHoTen(), nhanvien.getSoDienThoai(), nhanvien.getEmail(),nhanvien.getChucVu(),ngayVaoLamFormatted);

        SQLServerDataProvider provider = new SQLServerDataProvider();

        provider.open();

        int soLuong = provider.executeUpdate(sql);

        if (soLuong == 1) {

            ketQua = true;
        }

        provider.close();

        return ketQua;

    }
    public static ArrayList<NhanVien> timKiemNhanVienTheoID(int manv) {
        ArrayList<NhanVien> dsPB = new ArrayList<NhanVien>();
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNhanVien LIKE '%" + manv + "%'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                NhanVien pb = new NhanVien();
                pb.setMaNhanVien(rs.getInt("MaNhanVien"));
                pb.setHoTen(rs.getString("HoTen"));
                pb.setSoDienThoai(rs.getString("SoDienThoai"));
                pb.setEmail(rs.getString("Email"));
                pb.setChucVu(rs.getString("ChucVu"));
                pb.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                dsPB.add(pb);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
public static boolean xoaNhanVien (String manv) {
    boolean kq = false;
    String sql = String.format("DELETE FROM NhanVien WHERE MaNhanVien=N'%s'", manv);
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    int n = provider.executeUpdate(sql);
    if (n == 1) {
        kq = true;
    }
    provider.close();
    return kq;
}
public static boolean suaThongTinNhanVien(NhanVien nhanvien) {
    boolean ketQua = false;
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngayVaoLamFormatted = dateFormat.format(nhanvien.getNgayVaoLam());

        String sql = String.format("UPDATE NhanVien SET HoTen=N'%s', SoDienThoai=N'%s', Email=N'%s', ChucVu=N'%s', NgayVaoLam=N'%s' WHERE MaNhanVien=N'%s';",
                nhanvien.getHoTen(), nhanvien.getSoDienThoai(), nhanvien.getEmail(), nhanvien.getChucVu(), ngayVaoLamFormatted, nhanvien.getMaNhanVien());

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
