/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import POJO.Kho;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author AVITA
 */
public class KhoDao {
     public static ArrayList<Kho> LayDanhSachKho() {
        ArrayList<Kho> dsPB = new ArrayList<Kho>();
        try {
            String sql = "SELECT * FROM Kho";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                Kho pb = new Kho();
                pb.setMaNguyenLieu(rs.getInt("MaNguyenLieu"));
                pb.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
                pb.setDonViTinh(rs.getString("DonViTinh"));
                pb.setSoLuongTon(rs.getDouble("SoLuongTon"));
                dsPB.add(pb);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
     
    public static boolean ThemKho(Kho kho) {

        boolean ketQua = false;

        String sql = String.format("INSERT INTO Kho (TenNguyenLieu, DonViTinh, SoLuongTon) VALUES (N'%s', N'%s', N'%f');",

                kho.getTenNguyenLieu(), kho.getDonViTinh(), kho.getSoLuongTon());

        SQLServerDataProvider provider = new SQLServerDataProvider();

        provider.open();

        int soLuong = provider.executeUpdate(sql);

        if (soLuong == 1) {

            ketQua = true;
        }

        provider.close();

        return ketQua;

    }
     
     public static boolean XoaKho(int maNguyenLieu) {
    boolean ketQua = false;

    // Câu lệnh SQL để xóa các phiếu nhập kho liên quan đến mã nguyên liệu trước
    String sqlDeleteNhapKho = String.format("DELETE FROM NhapKho WHERE MaNguyenLieu = %d;", maNguyenLieu);

    // Câu lệnh SQL để xóa các phiếu xuất kho liên quan đến mã nguyên liệu trước
    String sqlDeleteXuatKho = String.format("DELETE FROM XuatKho WHERE MaNguyenLieu = %d;", maNguyenLieu);

    // Câu lệnh SQL để xóa nguyên liệu
    String sqlDeleteNguyenLieu = String.format("DELETE FROM Kho WHERE MaNguyenLieu = %d;", maNguyenLieu);

    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();

    try {
        // Thực hiện xóa các phiếu nhập kho trước
        int soLuongNhapKho = provider.executeUpdate(sqlDeleteNhapKho);

        // Thực hiện xóa các phiếu xuất kho sau
        int soLuongXuatKho = provider.executeUpdate(sqlDeleteXuatKho);

        // Thực hiện xóa nguyên liệu cuối cùng
        int soLuongNguyenLieu = provider.executeUpdate(sqlDeleteNguyenLieu);

        if (soLuongNguyenLieu == 1) {
            ketQua = true;
        }
    } catch (Exception e) {
        e.printStackTrace(); // Xử lý hoặc ghi log lỗi tại đây
    } finally {
        provider.close();
    }

    return ketQua;
      
}

     
     public static boolean suaThongTinKho(Kho kho) {
    boolean ketQua = false;
    try {

        String sql = String.format("UPDATE Kho SET TenNguyenLieu=N'%s', DonViTinh=N'%s', SoLuongTon=N'%f' WHERE MaNguyenLieu='%d';",
                           kho.getTenNguyenLieu(), kho.getDonViTinh(), kho.getSoLuongTon(), kho.getMaNguyenLieu());


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
     public static Kho layKhoTheoMa(int maNguyenLieu) {
    Kho kho = null;
    try {
        String sql = String.format("SELECT * FROM Kho WHERE MaNguyenLieu = %d", maNguyenLieu);
        SQLServerDataProvider provider = new SQLServerDataProvider();
        provider.open();
        ResultSet rs = provider.executeQuery(sql);
        if (rs.next()) {
            kho = new Kho();
            kho.setMaNguyenLieu(rs.getInt("MaNguyenLieu"));
            kho.setTenNguyenLieu(rs.getString("TenNguyenLieu"));
            kho.setDonViTinh(rs.getString("DonViTinh"));
            kho.setSoLuongTon(rs.getDouble("SoLuongTon"));
        }
        provider.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return kho;
}

     public static boolean capNhatKho(Kho kho) {
    boolean ketQua = false;
    try {
        String sql = String.format("UPDATE Kho SET TenNguyenLieu = N'%s', DonViTinh = N'%s', SoLuongTon = %f WHERE MaNguyenLieu = %d",
                kho.getTenNguyenLieu(), kho.getDonViTinh(), kho.getSoLuongTon(), kho.getMaNguyenLieu());
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
     
   public static boolean XuatKho(int maNguyenLieu, double soLuongXuat) {
    boolean ketQua = false;
    try {
        // Lấy thông tin kho theo mã nguyên liệu
        Kho kho = layKhoTheoMa(maNguyenLieu);
        if (kho != null) {
            // Kiểm tra số lượng tồn có đủ để xuất không
            if (kho.getSoLuongTon() >= soLuongXuat) {
                // Cập nhật số lượng tồn
                double soLuongTonMoi = kho.getSoLuongTon() - soLuongXuat;
                String sql = String.format("UPDATE Kho SET SoLuongTon = %f WHERE MaNguyenLieu = %d;",
                                            soLuongTonMoi, maNguyenLieu);
                 
                SQLServerDataProvider provider = new SQLServerDataProvider();
                provider.open();
                int soLuong = provider.executeUpdate(sql);
                if (soLuong == 1) {
                    ketQua = true;
                }
                provider.close();
            } else {
                System.out.println("Số lượng xuất lớn hơn số lượng tồn!");
            }
        } else {
            System.out.println("Không tìm thấy nguyên liệu!");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return ketQua;
}
   
   
  public static boolean XuatKhoTatCa(int[] maNguyenLieux, double soLuongXuat) {
    boolean ketQua = false;
    try {
        // Update inventory for each item in the maNguyenLieux array
        for (int maNguyenLieu : maNguyenLieux) {
            // Get inventory information for the current item
            Kho kho = layKhoTheoMa(maNguyenLieu);
            if (kho != null) {
                if (kho.getSoLuongTon() >= soLuongXuat) {
                    double soLuongTonMoi = kho.getSoLuongTon() - soLuongXuat;
                    String sql = String.format("UPDATE Kho SET SoLuongTon = %f WHERE MaNguyenLieu = %d;",
                            soLuongTonMoi, maNguyenLieu);

                    SQLServerDataProvider provider = new SQLServerDataProvider();
                    provider.open();
                    int rowsAffected = provider.executeUpdate(sql);
                    provider.close();

                    if (rowsAffected == 1) {
                        ketQua = true; // Update successful for at least one item
                    } else {
                        System.err.println("Lỗi khi cập nhật kho cho món " + maNguyenLieu + "."); // Use maNguyenLieu for identification
                    }
                } else {
                    System.err.println("Số lượng xuất lớn hơn số lượng tồn cho món (mã " + maNguyenLieu + ")!");
                }
            } else {
                System.err.println("Không tìm thấy nguyên liệu với mã " + maNguyenLieu + ".");
            }
        }

    } catch (Exception ex) {
        System.err.println("Lỗi nghiêm trọng khi cập nhật kho: " + ex.getMessage());
    }
    return ketQua;
}
   
  

     

   public static double getSoLuongTon(int maNguyenLieu) {
    double soLuongTon = 0;
    try {
        Kho kho = layKhoTheoMa(maNguyenLieu);
        if (kho != null) {
            soLuongTon = kho.getSoLuongTon();
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return soLuongTon;
}

}
