/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.HoaDonDao.capNhatTrangThaiHoaDon;
import static DAO.HoaDonDao.layHoaDonTheoBan;
import static DAO.HoaDonDao.taoMoiHoaDon;
import static DAO.HoaDonDao.themChiTietHoaDon;
import POJO.ChiTietHoaDon;
import POJO.HoaDon;
import POJO.MonAn;
import POJO.Orders;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnnKiz
 */
public class OrdersDao {
public static boolean themMonAnVaoDonHang(int maBan, int maKhachHang, int maMonAn, String tenMonAn, String loaiMonAn, int soLuong) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        // Lấy giá món ăn từ cơ sở dữ liệu
        String sqlGia = "SELECT Gia FROM MonAn WHERE MaMonAn = " + maMonAn;
        ResultSet rsGia = provider.executeQuery(sqlGia);
        
        if (rsGia.next()) {
            double gia = rsGia.getDouble("Gia");
            
            // Tính tổng tiền cho món ăn
            double tongTien = gia * soLuong;

            // Chèn thông tin vào bảng Orders
            String sql = String.format(
                "INSERT INTO Orders (MaBan, MaKhachHang, MaMonAn, TenMonAn, LoaiMon, Gia, TongTien, SoLuong) VALUES (%d, %d, %d, N'%s', N'%s', %f, %f, %d)",
                maBan, maKhachHang, maMonAn, tenMonAn, loaiMonAn, gia, tongTien, soLuong
            );
            
            int result = provider.executeUpdate(sql);
            return result > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}



    // Hàm truy xuất giá từ bảng MonAn dựa trên mã món ăn
    private static double layGiaMonAn(int maMonAn) {
        double gia = 0.0;
        try {
            String sql = "SELECT Gia FROM MonAn WHERE MaMonAn = " + maMonAn;
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            if (rs.next()) {
                gia = rs.getDouble("Gia");
            }
            rs.close();
            provider.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gia;
    }
public static double tinhTongTien(int tableId) {
    double tongTien = 0;
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = "SELECT SUM(TongTien) AS TongTien FROM Orders WHERE MaBan = " + tableId;
        ResultSet rs = provider.executeQuery(sql);
        
        if (rs.next()) {
            tongTien = rs.getDouble("TongTien");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return tongTien;
}

      // Phương thức để lấy danh sách đơn hàng từ CSDL
    public static DefaultTableModel LayDanhSachOrders() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"MaBan", "MaKhachHang", "MaMonAn", "TenMonAn", "LoaiMon", "Gia", "SoLuong"}, 0);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        
        try {
            String sql = "SELECT MaBan, MaKhachHang, MaMonAn, TenMonAn, LoaiMon, Gia, SoLuong FROM Orders";
            ResultSet rs = provider.executeQuery(sql);
            
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("MaBan");
                row[1] = rs.getInt("MaKhachHang");
                row[2] = rs.getInt("MaMonAn");
                row[3] = rs.getString("TenMonAn");
                row[4] = rs.getString("LoaiMon");
                row[5] = rs.getDouble("Gia");
                row[6] = rs.getInt("SoLuong");
                
                model.addRow(row);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            provider.close();
        }
        
        return model;
    }
    public static ArrayList<Orders> LayDanhSachOrdersByTableId(int tableId) {
    ArrayList<Orders> ordersList = new ArrayList<>();
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = "SELECT * FROM Orders WHERE MaBan = " + tableId;
        ResultSet rs = provider.executeQuery(sql);
        
        while (rs.next()) {
            Orders order = new Orders();
            order.setMaOrder(rs.getInt("MaOrder"));
            order.setMaBan(rs.getInt("MaBan"));
            order.setMaKhachHang(rs.getInt("MaKhachHang"));
            order.setMaMonAn(rs.getInt("MaMonAn"));
            order.setTenMonAn(rs.getString("TenMonAn"));
            order.setLoaiMon(rs.getString("LoaiMon"));
            order.setGia(rs.getDouble("Gia"));
            order.setTongTien(rs.getDouble("TongTien"));
            order.setSoLuong(rs.getInt("SoLuong"));
            ordersList.add(order);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return ordersList;
}

public static boolean xoaOrdersByTableId(int tableId) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = "DELETE FROM Orders WHERE MaBan = " + tableId;
        int result = provider.executeUpdate(sql);
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}
public static boolean kiemTraMonAnTonTaiTrongDonHang(int maBan, int maMonAn) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = String.format(
            "SELECT COUNT(*) AS Count FROM Orders WHERE MaBan = %d AND MaMonAn = %d",
            maBan, maMonAn
        );
        ResultSet rs = provider.executeQuery(sql);
        
        if (rs.next()) {
            int count = rs.getInt("Count");
            return count > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}

public static boolean capNhatSoLuongMonAnTrongDonHang(int maBan, int maMonAn, int soLuong) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        // Cập nhật số lượng món ăn trong đơn hàng
        String sql = String.format(
            "UPDATE Orders SET SoLuong = SoLuong + %d WHERE MaBan = %d AND MaMonAn = %d",
            soLuong, maBan, maMonAn
        );
        int result = provider.executeUpdate(sql);
        
        if (result > 0) {
            // Sau khi cập nhật số lượng món ăn, cập nhật lại tổng tiền của đơn hàng
            String sqlTongTien = String.format(
                "UPDATE Orders SET TongTien = SoLuong * Gia WHERE MaBan = %d AND MaMonAn = %d",
                maBan, maMonAn
            );
            provider.executeUpdate(sqlTongTien);
            return true;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}

public static boolean xoaMonAnKhoiDonHang(int maOrder) {
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();
    
    try {
        String sql = "DELETE FROM Orders WHERE MaOrder = " + maOrder;
        int result = provider.executeUpdate(sql);
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        provider.close();
    }
    return false;
}


}

