/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author AVITA
 */
public class ChiTietHoaDon {
    private int MaChiTietHoaDon;
    private int MaHoaDon;
    private int MaMonAn;
    private int SoLuong;
    private double Gia;

    public int getMaChiTietHoaDon() {
        return MaChiTietHoaDon;
    }

    public void setMaChiTietHoaDon(int MaChiTietHoaDon) {
        this.MaChiTietHoaDon = MaChiTietHoaDon;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int MaMonAn) {
        this.MaMonAn = MaMonAn;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int MaChiTietHoaDon, int MaHoaDon, int MaMonAn, int SoLuong, double Gia) {
        this.MaChiTietHoaDon = MaChiTietHoaDon;
        this.MaHoaDon = MaHoaDon;
        this.MaMonAn = MaMonAn;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
    }

    
    
    
}
