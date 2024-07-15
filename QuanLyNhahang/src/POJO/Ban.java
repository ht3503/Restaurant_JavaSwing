/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author quang
 * 
 */
public class Ban {
    private int MaBan ;
    private String TenBan ;
    private String Trangthai ;

    public Ban() {
    }

    public Ban(int MaBan, String TenBan, String Trangthai) {
        this.MaBan = MaBan;
        this.TenBan = TenBan;
        this.Trangthai = Trangthai;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int MaBan) {
        this.MaBan = MaBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String Trangthai) {
        this.Trangthai = Trangthai;
    }

       
}
