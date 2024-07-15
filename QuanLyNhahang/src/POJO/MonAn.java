/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author AVITA
 */
public class MonAn {
    private int MaMonAn;
    private String TenMonAn;
    private Double Gia;
    private String LoaiMonAn;

    public MonAn() {
    }

    public MonAn(int MaMonAn, String TenMonAn, Double Gia, String LoaiMonAn) {
        this.MaMonAn = MaMonAn;
        this.TenMonAn = TenMonAn;
        this.Gia = Gia;
        this.LoaiMonAn = LoaiMonAn;
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

    public Double getGia() {
        return Gia;
    }

    public void setGia(Double Gia) {
        this.Gia = Gia;
    }

    public String getLoaiMonAn() {
        return LoaiMonAn;
    }

    public void setLoaiMonAn(String LoaiMonAn) {
        this.LoaiMonAn = LoaiMonAn;
    }

   
}
