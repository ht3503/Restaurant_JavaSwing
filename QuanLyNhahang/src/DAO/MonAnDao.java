/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import POJO.MonAn;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author AVITA
 */
public class MonAnDao {
    public static ArrayList<MonAn> LayDanhSachMonAn() {
        ArrayList<MonAn> dsPB = new ArrayList<MonAn>();
        try {
            String sql = "SELECT * FROM MonAn";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                MonAn pb = new MonAn();
                pb.setMaMonAn(rs.getInt("MaMonAn"));
                pb.setTenMonAn(rs.getString("TenMonAn"));
                pb.setGia(rs.getDouble("Gia"));
                pb.setLoaiMonAn(rs.getString("LoaiMonAn"));
                dsPB.add(pb);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
     
    
    public static ArrayList<MonAn> layDanhSachLoaiMonAn() {
    ArrayList<MonAn> danhSachLoaiMonAn = new ArrayList<>();
    try {
        String sql = "SELECT DISTINCT LoaiMonAn FROM MonAn"; // Query for distinct food types
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        while (rs.next()) {
            MonAn loaiMonAn = new MonAn();
            loaiMonAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
            danhSachLoaiMonAn.add(loaiMonAn);
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return danhSachLoaiMonAn;
}  
public static ArrayList<MonAn> layDanhSachMonAnTheoLoai(String loaiMonAn) {
    ArrayList<MonAn> danhSachMonAn = new ArrayList<>();
    try {
        // Trực tiếp xây dựng câu lệnh SQL với biến loaiMonAn
        String sql = "SELECT * FROM MonAn WHERE LoaiMonAn = '" + loaiMonAn + "'";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        while (rs.next()) {
            MonAn monAn = new MonAn();
            monAn.setMaMonAn(rs.getInt("MaMonAn"));
            monAn.setTenMonAn(rs.getString("TenMonAn"));
            monAn.setGia(rs.getDouble("Gia"));
            monAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
            danhSachMonAn.add(monAn);
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return danhSachMonAn;
}
public static MonAn layThongTinMonAnTheoTen(String TenMonAn) {
    MonAn monAn = null;
    try {
        String sql = "SELECT * FROM MonAn WHERE TenMonAn = '" + TenMonAn + "'";
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        if (rs.next()) {
            monAn = new MonAn();
            monAn.setMaMonAn(rs.getInt("MaMonAn"));
            monAn.setTenMonAn(rs.getString("TenMonAn"));
            monAn.setGia(rs.getDouble("Gia"));
            monAn.setLoaiMonAn(rs.getString("LoaiMonAn"));
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return monAn;
}
  
 
     public static boolean ThemMonAN(MonAn monAn) {

        boolean ketQua = false;

        String sql = String.format("INSERT INTO MonAn (TenMonAn, Gia, LoaiMonAn) VALUES (N'%s', '%f', N'%s');",

                monAn.getTenMonAn(), monAn.getGia(), monAn.getLoaiMonAn());

        SQLServerDataProvider provider = new SQLServerDataProvider();

        provider.open();

        int soLuong = provider.executeUpdate(sql);

        if (soLuong == 1) {

            ketQua = true;
        }

        provider.close();

        return ketQua;

    }

   public static boolean XoaMonAn(String monAn) {

     boolean ketQua = false;

    // Câu lệnh SQL để xóa các chi tiết hóa đơn liên quan đến mã món ăn trước
    String sqlDeleteChiTietHoaDon = String.format("DELETE FROM ChiTietHoaDon WHERE MaMonAn = N'%s';", monAn);

    // Câu lệnh SQL để xóa món ăn
    String sqlDeleteMonAn = String.format("DELETE FROM MonAn WHERE MaMonAn = N'%s';", monAn);

    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();

        // Thực hiện xóa các chi tiết hóa đơn trước
        int soLuongChiTietHoaDon = provider.executeUpdate(sqlDeleteChiTietHoaDon);
        
        // Sau đó thực hiện xóa món ăn
        int soLuongMonAn = provider.executeUpdate(sqlDeleteMonAn);

        if (soLuongMonAn == 1) {
            ketQua = true;
        }
   
         provider.close();
    return ketQua;
}
   
   public static boolean suaThongTinMonAn(MonAn monAn) {
    boolean ketQua = false;
    try {
        String sql = String.format("UPDATE MonAn SET TenMonAn=N'%s', Gia=%.2f, LoaiMonAn=N'%s' WHERE MaMonAn=%d;",
                monAn.getTenMonAn(), monAn.getGia(), monAn.getLoaiMonAn(), monAn.getMaMonAn());

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
   
    public static int layMaMonAnTuTen(String tenMonAn) {
        int maMonAn = -1; // Giả sử không tìm thấy mã món ăn

        // Thực hiện truy vấn để lấy mã món ăn từ tên món ăn
        try {
            String sql = "SELECT MaMonAn FROM MonAn WHERE TenMonAn = '" + tenMonAn + "'";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                maMonAn = rs.getInt("MaMonAn");
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return maMonAn;
    }

}
