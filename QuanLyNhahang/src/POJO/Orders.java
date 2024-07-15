/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author AnnKiz
 */
public class Orders {
   private int MaOrder;
      private int MaBan;
   private int MaKhachHang;
   private int MaMonAn;
   private String TenMonAn;
   private String LoaiMon;
    private double Gia;
   private double TongTien;
      private int SoLuong;

    public Orders(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getMaOrder() {
        return MaOrder;
    }

    public void setMaOrder(int MaOrder) {
        this.MaOrder = MaOrder;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(int MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public int getMaMonAn() {
        return MaMonAn;
    }

    public void setMaMonAn(int MaMonAn) {
        this.MaMonAn = MaMonAn;
    }

    public String getTenMonAn() {
        return TenMonAn;
    }

    public void setTenMonAn(String TenMonAn) {
        this.TenMonAn = TenMonAn;
    }

    public String getLoaiMon() {
        return LoaiMon;
    }

    public void setLoaiMon(String LoaiMon) {
        this.LoaiMon = LoaiMon;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Orders() {
    }

    public Orders(int MaOrder, int MaBan, int MaKhachHang, int MaMonAn, String TenMonAn, String LoaiMon, double Gia, double TongTien) {
        this.MaOrder = MaOrder;
        this.MaBan = MaBan;
        this.MaKhachHang = MaKhachHang;
        this.MaMonAn = MaMonAn;
        this.TenMonAn = TenMonAn;
        this.LoaiMon = LoaiMon;
        this.Gia = Gia;
        this.TongTien = TongTien;
    }
    

}
