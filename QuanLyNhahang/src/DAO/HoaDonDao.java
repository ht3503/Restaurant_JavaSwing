/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import java.sql.ResultSet;
import java.util.ArrayList;
import POJO.KhachHang;
import POJO.MonAn;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnnKiz
 */
public class HoaDonDao {
    
    
    public static DefaultTableModel LayDanhSachHoaDon() {
        DefaultTableModel model = new DefaultTableModel();
        // Define columns for the table model
        model.addColumn("Mã Hóa Đơn");
        model.addColumn("Mã Khách Hàng");
        model.addColumn("Tên Khách Hàng");
        model.addColumn("Ngày Lập Hóa Đơn");
        model.addColumn("Mã Món Ăn");
        model.addColumn("Tên Món Ăn");
        model.addColumn("Số Lượng");
        model.addColumn("Giá");
        model.addColumn("Tổng Tiền");
        model.addColumn("Trạng Thái");

        try {
            String sql = "SELECT "
                    + "hd.MaHoaDon, "
                    + "kh.MaKhachHang, "
                    + "kh.HoTen AS TenKhachHang, "
                    + "hd.NgayLapHoaDon, "
                    + "cthd.MaMonAn, "
                    + "ma.TenMonAn, "
                    + "cthd.SoLuong, "
                    + "cthd.Gia, "
                    + "hd.TongTien, "
                    + "hd.TrangThai " // Thêm cột TrangThai vào truy vấn
                    + "FROM "
                    + "HoaDon hd "
                    + "JOIN ChiTietHoaDon cthd ON hd.MaHoaDon = cthd.MaHoaDon "
                    + "JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang "
                    + "JOIN MonAn ma ON cthd.MaMonAn = ma.MaMonAn";

            
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                // Retrieve data from the result set and add it to the table model
                Object[] rowData = {
                    rs.getInt("MaHoaDon"),
                    rs.getInt("MaKhachHang"),
                    rs.getString("TenKhachHang"),
                    rs.getDate("NgayLapHoaDon"),
                    rs.getInt("MaMonAn"),
                    rs.getString("TenMonAn"),
                    rs.getInt("SoLuong"),
                    rs.getDouble("Gia"),
                    rs.getDouble("TongTien"),
                    rs.getString("TrangThai")

                };
                model.addRow(rowData);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return model;
    }
     public static HoaDon layHoaDonTheoBan(int maBan) {
        HoaDon hoaDon = null;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        try {
            String sql = "SELECT * FROM HoaDon WHERE MaBan = " + maBan + " AND TrangThai = 'Chua Thanh Toan'";
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getInt("MaHoaDon"));
                hoaDon.setMaBan(rs.getInt("MaBan"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return hoaDon;
    }

    public static ArrayList<ChiTietHoaDon> layChiTietHoaDon(int maBan) {
        ArrayList<ChiTietHoaDon> dsChiTiet = new ArrayList<>();
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        try {
            String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon IN (SELECT MaHoaDon FROM HoaDon WHERE MaBan = " + maBan + " AND TrangThai = 'Chua Thanh Toan')";
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {
                ChiTietHoaDon chiTiet = new ChiTietHoaDon();
                chiTiet.setMaHoaDon(rs.getInt("MaHoaDon"));
                chiTiet.setMaMonAn(rs.getInt("MaMonAn"));
                chiTiet.setSoLuong(rs.getInt("SoLuong"));
                chiTiet.setGia(rs.getDouble("Gia"));
                dsChiTiet.add(chiTiet);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return dsChiTiet;
    }

    public static boolean themChiTietHoaDon(ChiTietHoaDon chiTiet) {
        boolean result = false;
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        try {
            String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaMonAn, SoLuong, Gia) VALUES (" 
                        + chiTiet.getMaHoaDon() + ", " 
                        + chiTiet.getMaMonAn() + ", " 
                        + chiTiet.getSoLuong() + ", " 
                        + chiTiet.getGia() + ")";
            int n = provider.executeUpdate(sql);
            if (n > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        return result;
    }

public static boolean taoMoiHoaDon(int maKhachHang, int maBan, int maNhanVien, double tongTien) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = String.format(
            "INSERT INTO HoaDon (MaKhachHang, MaBan, MaNhanVien, NgayLapHoaDon, TongTien) VALUES (%d, %d, %d, GETDATE(), %f)",
            maKhachHang, maBan, maNhanVien, tongTien
        );
        int result = provider.executeUpdate(sql);
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}
public static boolean themChiTietHoaDon(int maHoaDon, int maMonAn, int soLuong, double gia) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = String.format(
            "INSERT INTO ChiTietHoaDon (MaHoaDon, MaMonAn, SoLuong, Gia) VALUES (%d, %d, %d, %f)",
            maHoaDon, maMonAn, soLuong, gia
        );
        int result = provider.executeUpdate(sql);
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}

      
 public static int layHoaDonMoiNhat() {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = "SELECT TOP 1 MaHoaDon FROM HoaDon ORDER BY MaHoaDon DESC";
        ResultSet rs = provider.executeQuery(sql);
        
        if (rs.next()) {
            return rs.getInt("MaHoaDon");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return -1;
}

public static boolean capNhatTrangThaiHoaDon(int maHoaDon, String trangThai) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = String.format("UPDATE HoaDon SET TrangThai = N'%s' WHERE MaHoaDon = %d", trangThai, maHoaDon);
        int result = provider.executeUpdate(sql);
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}
  public static DefaultTableModel layHoaDonTheoKhachHang(String tenKhachHang) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Mã Hóa Đơn");
    model.addColumn("Mã Khách Hàng");
    model.addColumn("Tên Khách Hàng");
    model.addColumn("Ngày Lập Hóa Đơn");
    model.addColumn("Mã Món Ăn");
    model.addColumn("Tên Món Ăn");
    model.addColumn("Số Lượng");
    model.addColumn("Giá");
    model.addColumn("Tổng Tiền");
    model.addColumn("Trạng Thái");

    try {
        // Construct the SQL query by concatenating the customer's name
        String sql = "SELECT hd.MaHoaDon, kh.MaKhachHang, kh.HoTen AS TenKhachHang, hd.NgayLapHoaDon, " +
                     "cthd.MaMonAn, ma.TenMonAn, cthd.SoLuong, cthd.Gia, hd.TongTien, hd.TrangThai " +
                     "FROM HoaDon hd " +
                     "JOIN ChiTietHoaDon cthd ON hd.MaHoaDon = cthd.MaHoaDon " +
                     "JOIN KhachHang kh ON hd.MaKhachHang = kh.MaKhachHang " +
                     "JOIN MonAn ma ON cthd.MaMonAn = ma.MaMonAn " +
                     "WHERE kh.HoTen = N'" + tenKhachHang + "'";

        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        
        while (rs.next()) {
            Object[] rowData = {
                rs.getInt("MaHoaDon"),
                rs.getInt("MaKhachHang"),
                rs.getString("TenKhachHang"),
                rs.getDate("NgayLapHoaDon"),
                rs.getInt("MaMonAn"),
                rs.getString("TenMonAn"),
                rs.getInt("SoLuong"),
                rs.getDouble("Gia"),
                rs.getDouble("TongTien"),
                rs.getString("TrangThai")
            };
            model.addRow(rowData);
        }
        rs.close();
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return model;
}


}
