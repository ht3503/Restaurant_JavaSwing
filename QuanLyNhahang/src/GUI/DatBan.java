/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import DAO.BanDAO;
import DAO.HoaDonDao;
import DAO.KhachHangDao;
import DAO.KhoDao;
import DAO.MonAnDao;
import DAO.OrdersDao;
import POJO.Ban;
import POJO.ChiTietHoaDon;
import POJO.KhachHang;
import POJO.MonAn;
import POJO.HoaDon;
import POJO.Kho;
import POJO.Orders;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author quang    
 */
public class DatBan extends javax.swing.JFrame {
    private int currentTableId; // Variable to hold the current table ID
    private JComboBox<Integer> cbbMaKhachHang;
    String loaiMonAn;

    /**
     * Creates new form DatBan
     */
    public DatBan() {
        initComponents();
        loadBanTable();
        loadTrangThaiLabel();
        loadKhachHangComboBox();
        loadLoaiMonAnComboBox();
        loadMonAnComboBox(loaiMonAn);
        refreshOrderTable();
       
    }
    
    public void loadBanTable() {
    ArrayList<Ban> listBan = BanDAO.LayDanhSachBan();
    DefaultTableModel model = (DefaultTableModel) tblBan.getModel();
    model.setRowCount(0); // Clear existing rows
    for (Ban ban : listBan) {
        model.addRow(new Object[]{ban.getMaBan(), ban.getTenBan(), ban.getTrangthai()});
    }
}
  public void loadTrangThaiLabel(){
     tblBan.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    public void valueChanged(ListSelectionEvent event) {
        // Kiểm tra xem đã chọn một dòng mới hay chưa
        if (!event.getValueIsAdjusting()) {
            int selectedRow = tblBan.getSelectedRow();
            if (selectedRow != -1) { // Đảm bảo đã chọn một dòng
                String tenBan = (String) tblBan.getValueAt(selectedRow, 1); // Lấy tên bàn
                String trangThai = (String) tblBan.getValueAt(selectedRow, 2); // Lấy trạng thái bàn
                labelTrangThai.setText("Tên bàn: " + tenBan + ", Trạng thái: " + trangThai);
            }
        }
    }
});
}
 private void capNhatTrangThaiBanDangSuDung(int maBan) {
    BanDAO.capNhatTrangThaiBan(maBan, "Đang sử dụng");
    // Cập nhật lại giao diện hiển thị trạng thái của bàn (nếu cần)
    // Ví dụ: load lại danh sách bàn và hiển thị trạng thái mới của bàn
}

    private void capNhatTrangThaiBanVeTrong(int maBan) {
    BanDAO.capNhatTrangThaiBan(maBan, "Trống");
    // Cập nhật lại giao diện hiển thị trạng thái của bàn (nếu cần)
    // Ví dụ: load lại danh sách bàn và hiển thị trạng thái mới của bàn
}
 
 // Phương thức kiểm tra xem đơn hàng của bàn hiện tại có còn món nào không
private boolean kiemTraDonHangConMon(int maBan) {
    ArrayList<Orders> ordersList = OrdersDao.LayDanhSachOrdersByTableId(maBan);
    return !ordersList.isEmpty();
}

       
   public void loadKhachHangComboBox() {
    ArrayList<Integer> listMaKhachHang = KhachHangDao.layDanhSachMaKhachHang();
    cbbKhachHang.removeAllItems(); // Clear existing items
    for (Integer maKhachHang : listMaKhachHang) {
        cbbKhachHang.addItem(maKhachHang.toString());
    }
}


    
    
    public void loadLoaiMonAnComboBox() {
    ArrayList<MonAn> listLoaiMonAn = MonAnDao.layDanhSachLoaiMonAn();
    cbbLoaiMonAn.removeAllItems(); // Clear existing items
    for (MonAn loaiMonAn : listLoaiMonAn) {
        cbbLoaiMonAn.addItem(loaiMonAn.getLoaiMonAn());
    }
        }
   
    public void loadMonAnComboBox(String loaiMonAn) {
    ArrayList<MonAn> listMonAn = MonAnDao.layDanhSachMonAnTheoLoai(loaiMonAn);
    cbbTenMonAn.removeAllItems(); // Clear existing items
    for (MonAn monAn : listMonAn) {
        cbbTenMonAn.addItem(monAn.getTenMonAn());
    }
}
    
    // Phương thức refreshOrderTable để cập nhật bảng đơn hàng
private void refreshOrderTable() {
    DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
    model.setRowCount(0); // Xóa các hàng cũ

    // Lấy danh sách đơn hàng theo ID bàn hiện tại
    ArrayList<Orders> ordersList = OrdersDao.LayDanhSachOrdersByTableId(currentTableId);

    for (Orders order : ordersList) {
        model.addRow(new Object[]{
            order.getMaOrder(),
            order.getMaBan(),
            order.getMaKhachHang(),
            order.getMaMonAn(),
            order.getTenMonAn(),
            order.getLoaiMon(),
            order.getGia(),
            order.getTongTien(),
            order.getSoLuong()
        });
    }
}







private void updateTotalPrice() {
    double tongTien = OrdersDao.tinhTongTien(currentTableId);
    // Chuyển đổi tổng tiền sang chuỗi và cập nhật vào JTextField
    txtTongTien.setText(String.format("  %.2f VND", tongTien));
}



private void resetOrdersTable() {
    DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
    model.setRowCount(0); // Xóa hết các hàng
    
    // Cập nhật lại tổng tiền
    updateTotalPrice();
}

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        cbbLoaiMonAn = new javax.swing.JComboBox<>();
        cbbTenMonAn = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        btnThemMon = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnXoaMon = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBan = new javax.swing.JTable();
        cbbKhachHang = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnDatBan = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        labelTrangThai = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JLabel();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 204, 204));

        cbbLoaiMonAn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbLoaiMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiMonAnActionPerformed(evt);
            }
        });

        cbbTenMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTenMonAnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setText("ĐẶT BÀN ");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("ĐẶT MÓN");

        btnThanhToan.setBackground(new java.awt.Color(204, 204, 255));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnThanhToan.setText("THANH TOÁN");
        btnThanhToan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnThemMon.setBackground(new java.awt.Color(204, 204, 255));
        btnThemMon.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnThemMon.setText("THÊM MÓN");
        btnThemMon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThemMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel3.setText("TỔNG TIỀN");

        txtTongTien.setBackground(new java.awt.Color(204, 204, 204));

        btnXoaMon.setBackground(new java.awt.Color(204, 204, 255));
        btnXoaMon.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnXoaMon.setText("XÓA MÓN");
        btnXoaMon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnXoaMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaMonActionPerformed(evt);
            }
        });

        tblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã bàn", "Tên bàn", "Trạng thái"
            }
        ));
        tblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblBan);

        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã orders", "Mã bàn", "Mã khách hàng", "Mã món ăn", "Tên món ăn", "Loại món", "Giá", "Tổng tiền", "Số lượng"
            }
        ));
        jScrollPane4.setViewportView(tblOrders);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setText("Chọn mã khách hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setText("Chọn loại món ăn");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel6.setText("Chọn món ăn");

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel7.setText("Chọn số lượng");

        btnDatBan.setBackground(new java.awt.Color(204, 204, 255));
        btnDatBan.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnDatBan.setText("ĐẶT BÀN");
        btnDatBan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatBanActionPerformed(evt);
            }
        });

        labelTrangThai.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        labelTrangThai.setText("Tên bàn");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTrangThai))
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(btnThemMon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(btnXoaMon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbbTenMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(153, 204, 255));

        jLabel107.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Remove-bg.ai_1716824396907.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

        btnTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangChujLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChujLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChujLabel6MouseClicked
        TrangChuQL login = new TrangChuQL();
        login.setVisible(true);
        this.dispose(); // Đóng MainFrame hiện tại
    }//GEN-LAST:event_btnTrangChujLabel6MouseClicked

    private void btnXoaMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaMonActionPerformed
        // TODO add your handling code here:
       int selectedRow = tblOrders.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    int maOrder = (int) tblOrders.getValueAt(selectedRow, 0);
    
    // Thực hiện xóa món ăn khỏi đơn hàng
    boolean result = OrdersDao.xoaMonAnKhoiDonHang(maOrder);
    if (result) {
        JOptionPane.showMessageDialog(this, "Xóa món thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        // Sau khi xóa thành công, cập nhật lại bảng tblOrders
        refreshOrderTable();
        // Cập nhật lại tổng tiền
        updateTotalPrice();
        
        // Kiểm tra xem đơn hàng còn món nào không
        if (!kiemTraDonHangConMon(currentTableId)) {
            // Nếu đơn hàng không còn món nào, cập nhật trạng thái của bàn về "Trống"
            BanDAO.capNhatTrangThaiBan(currentTableId, "Trống");
            // Cập nhật lại giao diện hiển thị trạng thái của bàn (nếu cần)
                    loadBanTable();

        }
    } else {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xóa món. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnXoaMonActionPerformed

    private void btnThemMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMonActionPerformed
  // Lấy thông tin từ các thành phần giao diện người dùng
    int maBan = currentTableId; // Sử dụng mã bàn đã được chọn từ trước
    int maKhachHang = Integer.parseInt(cbbKhachHang.getSelectedItem().toString()); // Lấy mã khách hàng từ combobox
    String tenMonAn = cbbTenMonAn.getSelectedItem().toString(); // Lấy tên món ăn từ combobox
    String loaiMonAn = cbbLoaiMonAn.getSelectedItem().toString(); // Lấy loại món ăn từ combobox
    int soLuong = (int) jSpinner1.getValue(); // Lấy số lượng từ spinner

    // Kiểm tra tính hợp lệ của dữ liệu
    if (maBan == -1 || maKhachHang == -1 || tenMonAn.isEmpty() || loaiMonAn.isEmpty() || soLuong <= 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin món ăn và số lượng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        return; // Dừng lại nếu dữ liệu không hợp lệ
    }

    // Lấy mã món ăn từ tên món ăn
    int maMonAn = MonAnDao.layMaMonAnTuTen(tenMonAn);
    if (maMonAn == -1) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy mã món ăn cho " + tenMonAn, "Lỗi", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Kiểm tra xem món ăn đã tồn tại trong đơn hàng hay chưa
    boolean monAnDaTonTai = OrdersDao.kiemTraMonAnTonTaiTrongDonHang(maBan, maMonAn);

    if (monAnDaTonTai) {
        // Nếu món ăn đã tồn tại trong đơn hàng, cập nhật số lượng
        boolean capNhatSoLuong = OrdersDao.capNhatSoLuongMonAnTrongDonHang(maBan, maMonAn, soLuong);

        if (capNhatSoLuong) {
            // Cập nhật hiển thị của bảng đơn hàng và tổng tiền
            refreshOrderTable(); // Cập nhật bảng đơn hàng
            updateTotalPrice(); // Cập nhật tổng tiền
            JOptionPane.showMessageDialog(this, "Cập nhật số lượng món thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật số lượng món. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Nếu món ăn chưa tồn tại trong đơn hàng, thêm mới món vào đơn hàng
        boolean result = OrdersDao.themMonAnVaoDonHang(maBan, maKhachHang, maMonAn, tenMonAn, loaiMonAn, soLuong);

        if (result) {
                        capNhatTrangThaiBanDangSuDung(maBan);
        loadBanTable();

            // Cập nhật hiển thị của bảng đơn hàng và tổng tiền
            refreshOrderTable(); // Cập nhật bảng đơn hàng
            updateTotalPrice(); // Cập nhật tổng tiền
            JOptionPane.showMessageDialog(this, "Thêm món thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thêm món. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    }//GEN-LAST:event_btnThemMonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
   int maBan = currentTableId; // Sử dụng mã bàn hiện tại
    int maKhachHang = Integer.parseInt(cbbKhachHang.getSelectedItem().toString()); // Lấy mã khách hàng từ combobox
    int maNhanVien = 1; // Giả sử bạn có thông tin nhân viên hiện tại

    // Tính tổng tiền của bàn
    double tongTien = OrdersDao.tinhTongTien(maBan);
    
    // Tạo mới hóa đơn
    boolean hoaDonTaoMoi = HoaDonDao.taoMoiHoaDon(maKhachHang, maBan, maNhanVien, tongTien);
    
    if (hoaDonTaoMoi) {
        // Lấy mã hóa đơn vừa tạo
        int maHoaDon = HoaDonDao.layHoaDonMoiNhat();

        // Lấy danh sách orders theo mã bàn
        ArrayList<Orders> ordersList = OrdersDao.LayDanhSachOrdersByTableId(maBan);

        // Thêm chi tiết hóa đơn
        for (Orders order : ordersList) {
            HoaDonDao.themChiTietHoaDon(maHoaDon, order.getMaMonAn(), (int) order.getSoLuong(), order.getGia());
        }

   // Get all items from the Kho table
ArrayList<Kho> khoItems = KhoDao.LayDanhSachKho();

    boolean check = true;
// Update inventory for each item in the Kho table
for (Kho khoItem : khoItems) {
    // Get the total quantity to deduct for the current item
    int quantityToDeduct = 0;
    for (Orders order : ordersList) {
            quantityToDeduct += order.getSoLuong(); // Use int here as SoLuong might be integer
        
    }
    
    // Update inventory using XuatKho (assuming it's modified to handle int)
    if (quantityToDeduct > 0) {
        if (KhoDao.XuatKho(khoItem.getMaNguyenLieu(), quantityToDeduct *1.0)) {
            // Inventory update successful for the item
          
      
        } else {
            // Failed to update inventory for the item
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật kho cho món " + khoItem.getTenNguyenLieu(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            check = false;
            break;
        }
    }
}
        if (check) {
         HoaDonDao.capNhatTrangThaiHoaDon(maHoaDon, "Đã thanh toán");

        // Xóa các đơn hàng liên quan trong bảng Orders (nếu cần)
        OrdersDao.xoaOrdersByTableId(maBan);

        JOptionPane.showMessageDialog(this, "Thanh toán thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        
         capNhatTrangThaiBanVeTrong(currentTableId);
        }
        
              // Cập nhật trạng thái hóa đơn nếu cần
       
    } else {
        JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thanh toán. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }
            
       
        loadBanTable();

    
        resetOrdersTable();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void cbbLoaiMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiMonAnActionPerformed
        // TODO add your handling code here:
        String selectedLoaiMonAn = (String) cbbLoaiMonAn.getSelectedItem();
        loadMonAnComboBox(selectedLoaiMonAn);
    
    }//GEN-LAST:event_cbbLoaiMonAnActionPerformed

    private void tblBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanMouseClicked
         
     int selectedRow = tblBan.getSelectedRow();
    if (selectedRow != -1) {
        int maBan = (int) tblBan.getValueAt(selectedRow, 0);
        // Save selected MaBan to use later
        currentTableId = maBan;
        System.out.println("Selected MaBan: " + maBan);
        refreshOrderTable(); // Refresh order table when a table is selected
    }
        
    }//GEN-LAST:event_tblBanMouseClicked

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
        // TODO add your handling code here:
        
         // Lấy chỉ số hàng được chọn trong bảng tblBan
    int selectedRow = tblBan.getSelectedRow();
    
    if (selectedRow != -1) { // Kiểm tra xem có hàng nào được chọn không
        // Lấy mã bàn từ bảng tblBan (giả sử mã bàn ở cột 0)
        int maBan = (int) tblBan.getValueAt(selectedRow, 0);

        // Cập nhật trạng thái bàn trong cơ sở dữ liệu
        boolean capNhatTrangThai = BanDAO.capNhatTrangThaiBan(maBan, "Đang sử dụng");

        if (capNhatTrangThai) {
            // Cập nhật giao diện người dùng
            tblBan.setValueAt("Đang sử dụng", selectedRow, tblBan.getColumn("Trạng thái").getModelIndex());
            JOptionPane.showMessageDialog(this, "Bàn đã được đặt thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi cập nhật trạng thái bàn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn một bàn để đặt.", "Thông báo", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void cbbTenMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTenMonAnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTenMonAnActionPerformed

    private void loadHoaDonTable(int maBan) {
   
}
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatBan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatBan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemMon;
    private javax.swing.JLabel btnTrangChu;
    private javax.swing.JButton btnXoaMon;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JComboBox<String> cbbLoaiMonAn;
    private javax.swing.JComboBox<String> cbbTenMonAn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelTrangThai;
    private javax.swing.JTable tblBan;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
