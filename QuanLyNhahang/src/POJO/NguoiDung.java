/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author Administrator
 */
public class NguoiDung {

    public int getMaTaiKhoan() {
        return MaTaiKhoan;
    }

    public void setMaTaiKhoan(int MaTaiKhoan) {
        this.MaTaiKhoan = MaTaiKhoan;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getLoaiTaiKhoan() {
        return LoaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String LoaiTaiKhoan) {
        this.LoaiTaiKhoan = LoaiTaiKhoan;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }
    private int MaTaiKhoan;
    private String TaiKhoan;
    private String MatKhau;
    private String LoaiTaiKhoan;
    private String MaNhanVien;

    public NguoiDung() {
    }

    public NguoiDung(int MaTaiKhoan, String TaiKhoan, String MatKhau, String LoaiTaiKhoan, String MaNhanVien) {
        this.MaTaiKhoan = MaTaiKhoan;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.LoaiTaiKhoan = LoaiTaiKhoan;
        this.MaNhanVien = MaNhanVien;
    }

   
}
