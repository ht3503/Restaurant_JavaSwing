/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

import java.util.Date;

/**
 *
 * @author AnnKiz
 */
public class HoaDon {
     private int MaHoaDon;
     private int MaKhachHang;
     private int MaBan;
     private int MaNhanVien;
     private Date NgayLapHoaDon;
     private String TrangThai;

    public HoaDon(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
     private double TongTien;

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public Date getNgayLapHoaDon() {
        return NgayLapHoaDon;
    }

    public void setNgayLapHoaDon(Date NgayLapHoaDon) {
        this.NgayLapHoaDon = NgayLapHoaDon;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public HoaDon() {
    }

    public HoaDon(int MaHoaDon, int MaKhachHang, int MaBan, int MaNhanVien, Date NgayLapHoaDon, double TongTien) {
        this.MaHoaDon = MaHoaDon;
        this.MaKhachHang = MaKhachHang;
        this.MaBan = MaBan;
        this.MaNhanVien = MaNhanVien;
        this.NgayLapHoaDon = NgayLapHoaDon;
        this.TongTien = TongTien;
    }

    
    
}
