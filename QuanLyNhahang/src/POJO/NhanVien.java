/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class NhanVien {
    private int MaNhanVien; 
    private String HoTen;
    private Date NgayVaoLam;
    private String SoDienThoai; 
    private String ChucVu;
    private String Email;

    public NhanVien(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public int getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(int MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(Date NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public NhanVien() {
    }

    public NhanVien(int MaNhanVien, String HoTen, Date NgayVaoLam, String SoDienThoai, String ChucVu) {
        this.MaNhanVien = MaNhanVien;
        this.HoTen = HoTen;
        this.NgayVaoLam = NgayVaoLam;
        this.SoDienThoai = SoDienThoai;
        this.ChucVu = ChucVu;
        
    }

    
   

    
}
