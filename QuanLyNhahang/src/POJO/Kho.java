/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author AVITA
 */
public class Kho {
    private int MaNguyenLieu;
    private String TenNguyenLieu;
    private String DonViTinh;
    private Double SoLuongTon;

    public Kho() {
    }

    public Kho(int MaNguyenLieu, String TenNguyenLieu, String DonViTinh, Double SoLuongTon) {
        this.MaNguyenLieu = MaNguyenLieu;
        this.TenNguyenLieu = TenNguyenLieu;
        this.DonViTinh = DonViTinh;
        this.SoLuongTon = SoLuongTon;
    }

    public int getMaNguyenLieu() {
        return MaNguyenLieu;
    }

    public void setMaNguyenLieu(int MaNguyenLieu) {
        this.MaNguyenLieu = MaNguyenLieu;
    }

    public String getTenNguyenLieu() {
        return TenNguyenLieu;
    }

    public void setTenNguyenLieu(String TenNguyenLieu) {
        this.TenNguyenLieu = TenNguyenLieu;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public Double getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(Double SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    
    
}
