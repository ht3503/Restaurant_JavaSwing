CREATE DATABASE QuanLyNhaHang1;
GO
USE QuanLyNhaHang1;
drop database QuanLyNhaHang1;
-- Tạo lại bảng Ban nếu cần
CREATE TABLE Ban (
    MaBan INT PRIMARY KEY IDENTITY(1,1),
    TenBan NVARCHAR(50),
    TrangThai NVARCHAR(50) -- Trạng thái của bàn: Trống, Đặt, Đang sử dụng
);

-- Các bảng khác vẫn giữ nguyên
CREATE TABLE KhachHang (
    MaKhachHang INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(50),
    SoDienThoai NVARCHAR(15),
    Email NVARCHAR(50),
    DiaChi NVARCHAR(50)
);

CREATE TABLE NhanVien (
    MaNhanVien INT PRIMARY KEY IDENTITY(1,1),
    HoTen NVARCHAR(50),
    SoDienThoai NVARCHAR(15),
    Email NVARCHAR(50),
    ChucVu NVARCHAR(50),
    NgayVaoLam DATE
);

CREATE TABLE MonAn (
    MaMonAn INT PRIMARY KEY IDENTITY(1,1),
    TenMonAn NVARCHAR(100),
    Gia DECIMAL(18, 2),
    LoaiMonAn NVARCHAR(50)
);

-- Tạo lại bảng HoaDon để thêm MaBan và MaNhanVien
CREATE TABLE HoaDon (
    MaHoaDon INT PRIMARY KEY IDENTITY(1,1),
    MaKhachHang INT,
    MaBan INT, -- Thêm cột MaBan để ghi nhận bàn nào được sử dụng
    MaNhanVien INT, -- Thêm cột MaNhanVien để ghi nhận nhân viên thực hiện đặt bàn
    NgayLapHoaDon DATE,
    TrangThai NVARCHAR(100) DEFAULT N'Chưa thanh toán',
    TongTien DECIMAL(18, 2),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

CREATE TABLE ChiTietHoaDon (
    MaChiTietHoaDon INT PRIMARY KEY IDENTITY(1,1),
    MaHoaDon INT,
    MaMonAn INT,
    SoLuong INT,
    Gia DECIMAL(18, 2),
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    FOREIGN KEY (MaMonAn) REFERENCES MonAn(MaMonAn)
);

CREATE TABLE TaiKhoan (
    MaTaiKhoan INT PRIMARY KEY IDENTITY(1,1),
    TaiKhoan NVARCHAR(50) NOT NULL,
    MatKhau NVARCHAR(255) NOT NULL,
    LoaiTaiKhoan NVARCHAR(50) NOT NULL,
    MaNhanVien INT NOT NULL,
    CONSTRAINT FK_TaiKhoan_NhanVien FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

CREATE TABLE Kho (
    MaNguyenLieu INT PRIMARY KEY IDENTITY(1,1),
    TenNguyenLieu NVARCHAR(100),
    DonViTinh NVARCHAR(50),
    SoLuongTon DECIMAL(18, 2)
);

CREATE TABLE NhapKho (
    MaNhapKho INT PRIMARY KEY IDENTITY(1,1),
    MaNguyenLieu INT,
    NgayNhap DATE,
    SoLuongNhap DECIMAL(18, 2),
    FOREIGN KEY (MaNguyenLieu) REFERENCES Kho(MaNguyenLieu)
);

CREATE TABLE XuatKho (
    MaXuatKho INT PRIMARY KEY IDENTITY(1,1),
    MaNguyenLieu INT,
    NgayXuat DATE,
    SoLuongXuat DECIMAL(18, 2),
    FOREIGN KEY (MaNguyenLieu) REFERENCES Kho(MaNguyenLieu)
);

-- Tạo bảng Order
CREATE TABLE Orders (
    MaOrder INT PRIMARY KEY IDENTITY(1,1),
    MaBan INT,
    MaKhachHang INT,
    MaMonAn INT,
    TenMonAn NVARCHAR(100),
    LoaiMon NVARCHAR(50),
    Gia DECIMAL(18, 2),
    TongTien DECIMAL(18, 2),
	SoLuong INT,
    FOREIGN KEY (MaBan) REFERENCES Ban(MaBan),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaMonAn) REFERENCES MonAn(MaMonAn)
);
	select * from Orders
		select * from HoaDon
				select * from Ban
						select * from ChiTietHoaDon



-- Insert sample data into Ban
INSERT INTO Ban (TenBan, TrangThai)
VALUES 
    (N'Ban 1', N'Trống'),
    (N'Ban 2', N'Đặt'),
    (N'Ban 3', N'Đang sử dụng'),
    (N'Ban 4', N'Trống'),
    (N'Ban 5', N'Đặt');

-- Insert sample data into KhachHang
INSERT INTO KhachHang (HoTen, SoDienThoai, Email, DiaChi)
VALUES 
    (N'Nguyen Van A', '0123456789', 'nguyenvana@example.com', N'Hanoi'),
    (N'Tran Thi B', '0987654321', 'tranthib@example.com', N'HCM'),
    (N'Le Van C', '0912345678', 'levanc@example.com', N'Da Nang'),
    (N'Pham Thi D', '0908765432', 'phamthid@example.com', N'Hue'),
    (N'Hoang Van E', '0981234567', 'hoangvane@example.com', N'Quang Ninh');

-- Insert sample data into NhanVien
INSERT INTO NhanVien (HoTen, SoDienThoai, Email, ChucVu, NgayVaoLam)
VALUES 
    (N'Nguyen Van X', '0123456789', 'nguyenvanx@example.com', N'Quan ly', '2020-01-01'),
    (N'Tran Thi Y', '0987654321', 'tranthiy@example.com', N'Phuc vu', '2021-02-15'),
    (N'Le Van Z', '0912345678', 'levanz@example.com', N'Bep truong', '2019-03-10'),
    (N'Pham Thi M', '0908765432', 'phamthim@example.com', N'Thu ngan', '2022-04-05'),
    (N'Hoang Van N', '0981234567', 'hoangvann@example.com', N'Phuc vu', '2023-05-20');

-- Insert sample data into MonAn
INSERT INTO MonAn (TenMonAn, Gia, LoaiMonAn)
VALUES 
    (N'Pho Bo', 30000, N'Pho'),
    (N'Bun Cha', 40000, N'Bun'),
    (N'Com Tam', 50000, N'Com'),
    (N'Goi Cuon', 20000, N'Mon Khai Vi'),
    (N'Ca Kho To', 60000, N'Mon Chinh');

-- Insert sample data into HoaDon
INSERT INTO HoaDon (MaKhachHang, MaBan, MaNhanVien, NgayLapHoaDon, TrangThai, TongTien)
VALUES 
    (1, 3, 1, '2024-05-01', N'Chưa thanh toán', 150000),
    (2, 2, 2, '2024-05-02', N'Đã thanh toán', 200000),
    (3, 1, 3, '2024-05-03', N'Chưa thanh toán', 300000),
    (4, 4, 4, '2024-05-04', N'Đã thanh toán', 120000),
    (5, 5, 5, '2024-05-05', N'Chưa thanh toán', 250000);

-- Insert sample data into ChiTietHoaDon
INSERT INTO ChiTietHoaDon (MaHoaDon, MaMonAn, SoLuong, Gia)
VALUES 
    (1, 1, 2, 30000),
    (1, 2, 1, 40000),
    (2, 3, 4, 50000),
    (2, 4, 2, 20000),
    (3, 5, 1, 60000),
    (3, 1, 3, 30000),
    (4, 2, 2, 40000),
    (4, 3, 1, 50000),
    (5, 4, 4, 20000),
    (5, 5, 2, 60000);

-- Insert sample data into TaiKhoan
INSERT INTO TaiKhoan (TaiKhoan, MatKhau, LoaiTaiKhoan, MaNhanVien)
VALUES 
    (N'admin', N'admin123', N'Quan ly', 1),
    (N'thanhvien1', N'pass123', N'Phuc vu', 2),
    (N'thanhvien2', N'pass456', N'Bep truong', 3),
    (N'thanhvien3', N'pass789', N'Thu ngan', 4),
    (N'thanhvien4', N'pass012', N'Phuc vu', 5);

-- Insert sample data into Kho
INSERT INTO Kho (TenNguyenLieu, DonViTinh, SoLuongTon)
VALUES 
    (N'Ga', N'kg', 50),
    (N'Bo', N'kg', 30),
    (N'Rau', N'kg', 20),
    (N'Ga vi', N'chai', 10),
    (N'Duong', N'kg', 100);

-- Insert sample data into NhapKho
INSERT INTO NhapKho (MaNguyenLieu, NgayNhap, SoLuongNhap)
VALUES 
    (1, '2024-05-01', 10),
    (2, '2024-05-02', 5),
    (3, '2024-05-03', 15),
    (4, '2024-05-04', 7),
    (5, '2024-05-05', 20);

-- Insert sample data into XuatKho
INSERT INTO XuatKho (MaNguyenLieu, NgayXuat, SoLuongXuat)
VALUES 
    (1, '2024-05-06', 5),
    (2, '2024-05-07', 3),
    (3, '2024-05-08', 10),
    (4, '2024-05-09', 4),
    (5, '2024-05-10', 15);