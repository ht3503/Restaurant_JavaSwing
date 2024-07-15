/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import POJO.NguoiDung;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NguoiDungDao {
    public static ArrayList<NguoiDung> LayDanhSachNguoiDung() {
        ArrayList<NguoiDung> dsPB = new ArrayList<NguoiDung>();
        try {
            String sql = "SELECT * FROM TaiKhoan";
            SQLServerDataProvider provider = new SQLServerDataProvider();
            provider.open();
            ResultSet rs = provider.executeQuery(sql);
            while (rs.next()) {                
                NguoiDung pb = new NguoiDung();
                pb.setMaTaiKhoan(rs.getInt("MaTaiKhoan"));
                pb.setTaiKhoan(rs.getString("TaiKhoan"));
                pb.setMatKhau(rs.getString("MatKhau"));
                pb.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
                pb.setMaNhanVien(rs.getString("MaNhanVien"));
                dsPB.add(pb);
            }
            provider.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    return dsPB;    
    }
     
    public static boolean ThemNguoiDung(NguoiDung nguoiDung) {

        boolean ketQua = false;
    SQLServerDataProvider provider = new SQLServerDataProvider();
    provider.open();

    try {
        // Kiểm tra xem tên tài khoản đã tồn tại chưa
        String sqlKiemTraTaiKhoan = String.format("SELECT COUNT(*) AS SoLuong FROM TaiKhoan WHERE TaiKhoan = N'%s'", nguoiDung.getTaiKhoan());
        ResultSet rsTaiKhoan = provider.executeQuery(sqlKiemTraTaiKhoan);

        if (rsTaiKhoan.next()) {
            int soLuongTaiKhoan = rsTaiKhoan.getInt("SoLuong");
            if (soLuongTaiKhoan > 0) {
                // Tên tài khoản đã tồn tại
                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // Kiểm tra xem mã nhân viên tồn tại trong cơ sở dữ liệu hay không
        String sqlKiemTraMaNhanVien = String.format("SELECT COUNT(*) AS SoLuong FROM NhanVien WHERE MaNhanVien = N'%s'", nguoiDung.getMaNhanVien());
        ResultSet rsMaNhanVien = provider.executeQuery(sqlKiemTraMaNhanVien);

        if (rsMaNhanVien.next()) {
            int soLuongMaNhanVien = rsMaNhanVien.getInt("SoLuong");
            if (soLuongMaNhanVien == 0) {
                // Mã nhân viên không tồn tại
                JOptionPane.showMessageDialog(null, "Mã nhân viên không tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        // Thêm người dùng mới vào cơ sở dữ liệu
        String sqlThem = String.format("INSERT INTO TaiKhoan (Taikhoan, Matkhau, LoaiTaiKhoan, MaNhanVien) VALUES (N'%s', N'%s', N'%s', N'%s')",
                nguoiDung.getTaiKhoan(), nguoiDung.getMatKhau(), nguoiDung.getLoaiTaiKhoan(), nguoiDung.getMaNhanVien());
        int soLuongThem = provider.executeUpdate(sqlThem);
        if (soLuongThem == 1) {
            ketQua = true;
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    } finally {
        provider.close();
    }
    return ketQua;
    }


}
