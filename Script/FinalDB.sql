-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for hieusachdb
CREATE DATABASE IF NOT EXISTS `hieusachdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `hieusachdb`;

-- Dumping structure for table hieusachdb.chatlieu
CREATE TABLE IF NOT EXISTS `chatlieu` (
  `maChatLieu` varchar(255) NOT NULL,
  `tenChatLieu` varchar(255) NOT NULL,
  PRIMARY KEY (`maChatLieu`),
  UNIQUE KEY `UK_th5eu4uskciv3km3lfrt68b6g` (`tenChatLieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.chatlieu: ~5 rows (approximately)
INSERT INTO `chatlieu` (`maChatLieu`, `tenChatLieu`) VALUES
	('CL002', 'Giấy'),
	('CL004', 'Màu'),
	('CL001', 'Nhựa'),
	('CL003', 'Tẩy'),
	('CL005', 'Vải');

-- Dumping structure for table hieusachdb.chitiethoadon
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `donGia` bigint(20) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `maHoaDon` varchar(255) NOT NULL,
  `maSanPham` varchar(255) NOT NULL,
  PRIMARY KEY (`maHoaDon`,`maSanPham`),
  KEY `FKjrhk50rw9gnc8yk1epve0uc7s` (`maSanPham`),
  CONSTRAINT `FK48lko1mwj5apm7o0w68jp3ugw` FOREIGN KEY (`maHoaDon`) REFERENCES `hoadon` (`maHoaDon`),
  CONSTRAINT `FKjrhk50rw9gnc8yk1epve0uc7s` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.chitiethoadon: ~15 rows (approximately)
INSERT INTO `chitiethoadon` (`donGia`, `soLuong`, `maHoaDon`, `maSanPham`) VALUES
	(50000, 2, 'HD1300001', 'SP00001'),
	(75000, 3, 'HD1300001', 'SP00002'),
	(25000, 2, 'HD1400002', 'SP00003'),
	(20000, 2, 'HD1400002', 'SP00004'),
	(200000, 2, 'HD1400003', 'SP00002'),
	(150000, 2, 'HD1500004', 'SP00001'),
	(42000, 1, 'HD2300010', 'SP00006'),
	(108000, 10, 'HD2300011', 'SP00007'),
	(24000, 10, 'HD2300012', 'SP00021'),
	(72000, 4, 'HD2300013', 'SP00010'),
	(32400, 10, 'HD2300014', 'SP00017'),
	(32400, 3, 'HD2300015', 'SP00017'),
	(32400, 10, 'HD2300016', 'SP00027'),
	(42000, 1, 'HD2300017', 'SP00006'),
	(108000, 1, 'HD2300018', 'SP00007'),
	(6000, 1, 'HD2300019', 'SP00011');

-- Dumping structure for table hieusachdb.chitiethoadondoitra
CREATE TABLE IF NOT EXISTS `chitiethoadondoitra` (
  `donGia` bigint(20) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `maSanPham` varchar(255) NOT NULL,
  `maHoaDonDoiTra` varchar(255) NOT NULL,
  PRIMARY KEY (`maHoaDonDoiTra`,`maSanPham`),
  KEY `FK8bp8o3lbhpfkbqymfyrvn0p8t` (`maSanPham`),
  CONSTRAINT `FK8bp8o3lbhpfkbqymfyrvn0p8t` FOREIGN KEY (`maSanPham`) REFERENCES `sanpham` (`maSanPham`),
  CONSTRAINT `FKrf62x83bv0fj1xbdupcfv9qva` FOREIGN KEY (`maHoaDonDoiTra`) REFERENCES `hoadondoitra` (`maHoaDonDoiTra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.chitiethoadondoitra: ~3 rows (approximately)
INSERT INTO `chitiethoadondoitra` (`donGia`, `soLuong`, `maSanPham`, `maHoaDonDoiTra`) VALUES
	(484522, 2, 'SP00001', 'HDDT2300007'),
	(35000, 1, 'SP00006', 'HDDT2300008'),
	(484522, 1, 'SP00001', 'HDDT2300009');

-- Dumping structure for table hieusachdb.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `maHoaDon` varchar(255) NOT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  `ngayLapHoaDon` date DEFAULT NULL,
  `tienKhachDua` double NOT NULL,
  `tinhTrang` bit(1) NOT NULL,
  `maKhachHang` varchar(255) DEFAULT NULL,
  `maNhanVien` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maHoaDon`),
  KEY `FK9d827nxw1jb7c2n7k6lma13fo` (`maKhachHang`),
  KEY `FKlr1g5d8b2338kpln7dlergfjg` (`maNhanVien`),
  CONSTRAINT `FK9d827nxw1jb7c2n7k6lma13fo` FOREIGN KEY (`maKhachHang`) REFERENCES `khachhang` (`maKhachHang`),
  CONSTRAINT `FKlr1g5d8b2338kpln7dlergfjg` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.hoadon: ~18 rows (approximately)
INSERT INTO `hoadon` (`maHoaDon`, `ghiChu`, `ngayLapHoaDon`, `tienKhachDua`, `tinhTrang`, `maKhachHang`, `maNhanVien`) VALUES
	('HD1300001', 'Không', '2022-10-30', 500000, b'1', 'KH110001', 'NV000001'),
	('HD1400002', 'Không', '2022-10-28', 300000, b'0', 'KH120002', 'NV000001'),
	('HD1400003', 'Không', '2022-10-29', 400000, b'0', 'KH120002', 'NV000002'),
	('HD1500004', 'Không', '2022-09-18', 300000, b'1', 'KH140004', 'QL000003'),
	('HD2300005', 'Không', '2024-04-23', 800000, b'1', 'KH180010', 'QL000003'),
	('HD2300006', 'Không', '2024-04-23', 3260000, b'1', 'KH220022', 'QL000003'),
	('HD2300007', 'Không', '2024-04-23', 500000, b'1', 'KH220022', 'QL000003'),
	('HD2300008', 'Không', '2024-04-23', 900000, b'1', 'KH220022', 'QL000003'),
	('HD2300009', 'Không', '2024-04-23', 300000, b'1', 'KH220022', 'QL000003'),
	('HD2300010', 'Không', '2024-04-23', 500000, b'1', 'KH220022', 'QL000003'),
	('HD2300011', 'Không', '2024-04-23', 1300000, b'1', 'KH220022', 'QL000003'),
	('HD2300012', 'Không', '2024-04-23', 300000, b'1', 'KH200012', 'QL000003'),
	('HD2300013', 'Không', '2024-04-23', 350000, b'1', 'KH180010', 'QL000003'),
	('HD2300014', 'Không', '2024-04-23', 400000, b'1', 'KH150007', 'QL000003'),
	('HD2300015', 'Không', '2024-04-23', 200000, b'1', 'KH200016', 'QL000003'),
	('HD2300016', 'Không', '2024-04-23', 500000, b'1', 'KH180014', 'QL000003'),
	('HD2300017', 'Không', '2024-04-23', 200000, b'1', 'KH220022', 'QL000003'),
	('HD2300018', 'Không', '2024-04-23', 200000, b'1', 'KH200017', 'QL000003'),
	('HD2300019', 'Không', '2024-04-23', 2345678, b'1', 'KH220022', 'QL000003');

-- Dumping structure for table hieusachdb.hoadondoitra
CREATE TABLE IF NOT EXISTS `hoadondoitra` (
  `maHoaDonDoiTra` varchar(255) NOT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  `ngayLapHoaDon` date NOT NULL,
  `tienKhachDua` double NOT NULL,
  `tienPhaiTru` double NOT NULL,
  `maHoaDon` varchar(255) DEFAULT NULL,
  `maKhachHang` varchar(255) DEFAULT NULL,
  `maNhanVien` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maHoaDonDoiTra`),
  KEY `FKs41oo71ohfc2mfkgrx8ihjog` (`maHoaDon`),
  KEY `FK6kkvnmrae7481g8xt6q28htyd` (`maKhachHang`),
  KEY `FKspc4bqtmofcbjup251v3047ki` (`maNhanVien`),
  CONSTRAINT `FK6kkvnmrae7481g8xt6q28htyd` FOREIGN KEY (`maKhachHang`) REFERENCES `khachhang` (`maKhachHang`),
  CONSTRAINT `FKs41oo71ohfc2mfkgrx8ihjog` FOREIGN KEY (`maHoaDon`) REFERENCES `hoadon` (`maHoaDon`),
  CONSTRAINT `FKspc4bqtmofcbjup251v3047ki` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.hoadondoitra: ~9 rows (approximately)
INSERT INTO `hoadondoitra` (`maHoaDonDoiTra`, `ghiChu`, `ngayLapHoaDon`, `tienKhachDua`, `tienPhaiTru`, `maHoaDon`, `maKhachHang`, `maNhanVien`) VALUES
	('HDDT2300001', 'Không', '2024-04-23', 0, 0, 'HD2300010', 'KH220022', 'QL000003'),
	('HDDT2300002', 'Không', '2024-04-23', 0, 0, 'HD2300011', 'KH220022', 'QL000003'),
	('HDDT2300003', 'Không', '2024-04-23', 10000, 0, 'HD2300012', 'KH200012', 'QL000003'),
	('HDDT2300004', 'Không', '2024-04-23', 20000, 0, 'HD2300013', 'KH180010', 'QL000003'),
	('HDDT2300005', 'Không', '2024-04-23', 0, 0, 'HD2300014', 'KH150007', 'QL000003'),
	('HDDT2300006', 'Không', '2024-04-23', 0, 0, 'HD2300015', 'KH200016', 'QL000003'),
	('HDDT2300007', 'Không', '2024-04-23', 24000000, 64800, 'HD2300016', 'KH180014', 'QL000003'),
	('HDDT2300008', 'Không', '2024-04-23', 50000, 42000, 'HD2300017', 'KH220022', 'QL000003'),
	('HDDT2300009', 'Không', '2024-04-23', 600000, 108000, 'HD2300018', 'KH200017', 'QL000003');

-- Dumping structure for table hieusachdb.khachhang
CREATE TABLE IF NOT EXISTS `khachhang` (
  `maKhachHang` varchar(255) NOT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `gioiTinh` bit(1) NOT NULL,
  `hoTenKhachHang` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  PRIMARY KEY (`maKhachHang`),
  UNIQUE KEY `UK_a27hlq3dffaeyndfkoeuilcix` (`sdt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.khachhang: ~24 rows (approximately)
INSERT INTO `khachhang` (`maKhachHang`, `diaChi`, `gioiTinh`, `hoTenKhachHang`, `sdt`) VALUES
	('KH110001', 'Hóc Môn', b'1', 'Nguyễn Van An', '0337098734'),
	('KH120002', 'Gò Vấp', b'0', 'Nguyễn Thị Khánh', '0914712039'),
	('KH120003', 'Bình Thạnh', b'0', 'Lê Thị Hồng', '0961410277'),
	('KH140004', 'Gò Vấp', b'0', 'Bùi Ngọc Dươnng', '0366365911'),
	('KH140005', 'Gò Vấp', b'1', 'Ðinh Huy Tùng', '0909393964'),
	('KH150006', 'Bình Chánh', b'1', 'Phạm Ngọc Minh', '0366387511'),
	('KH150007', 'Quận 7', b'0', 'Ðinh Thị Tôn', '0909354542'),
	('KH150008', 'Quận 4', b'1', 'Phạm Hoàng Sơn', '0323575911'),
	('KH170009', 'Quận 2', b'0', 'Lê Thị Linh', '0909393543'),
	('KH180010', 'Quận 1', b'0', 'Bùi Thị Nhật Vy', '0366355478'),
	('KH180013', 'Hóc Môn', b'1', 'Nguyễn Tấn Vũ', '0155858651'),
	('KH180014', 'Gò Vấp', b'1', 'Nguyễn Anh Tuấn', '015584351'),
	('KH190011', 'Quận 12', b'1', 'Nguyễn Phạm Công Nguyên', '0909411616'),
	('KH190014', 'Quận 12', b'1', 'Nguyễn Tuấn Dũng', '0944861616'),
	('KH200012', 'Thủ Đức', b'1', 'Trần Thanh Phát', '0916161515'),
	('KH200015', 'Quận 12', b'1', 'Phan Nguyễn Trung Thành', '0955551616'),
	('KH200016', 'Quận 7', b'1', 'Nguyễn Việt Hoàng', '0955448816'),
	('KH200017', 'Quận 7', b'1', 'Trần Minh Trí', '0955551617'),
	('KH200018', 'Gò Vấp', b'1', 'Vũ Thái Dương', '0944558484'),
	('KH200019', 'Tân Bình', b'0', 'Nguyễn Huyền Nhi', '096868686'),
	('KH200021', 'Quận 12', b'1', 'Nguyễn Thái Cường', '093525164'),
	('KH220022', 'Gò Vấp', b'1', 'Lê Hoàng Nam', '0858484522'),
	('KH220023', 'Gò Vấp', b'1', 'Nguyễn Hồng Đức', '0929438043'),
	('KH230024', 'Thủ Đức', b'1', 'VinhLOL', '0929438049');

-- Dumping structure for table hieusachdb.loaivanphongpham
CREATE TABLE IF NOT EXISTS `loaivanphongpham` (
  `maLoaiVanPhongPham` varchar(255) NOT NULL,
  `tenTheLoai` varchar(255) NOT NULL,
  PRIMARY KEY (`maLoaiVanPhongPham`),
  UNIQUE KEY `UK_qvwofj5ujb0o0d6mhoyieutq4` (`tenTheLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.loaivanphongpham: ~3 rows (approximately)
INSERT INTO `loaivanphongpham` (`maLoaiVanPhongPham`, `tenTheLoai`) VALUES
	('TL001', 'Dụng cụ học sinh'),
	('TL003', 'Thiết bị trường học'),
	('TL002', 'Thiết bị văn phòng');

-- Dumping structure for table hieusachdb.mausac
CREATE TABLE IF NOT EXISTS `mausac` (
  `maMauSac` varchar(255) NOT NULL,
  `tenMau` varchar(255) NOT NULL,
  PRIMARY KEY (`maMauSac`),
  UNIQUE KEY `UK_3cyk9wcs4mrb5a1sh9iua7m46` (`tenMau`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.mausac: ~7 rows (approximately)
INSERT INTO `mausac` (`maMauSac`, `tenMau`) VALUES
	('MS007', 'Hồng'),
	('MS001', 'Màu trắng'),
	('MS002', 'Màu xanh'),
	('MS005', 'Trắng'),
	('MS006', 'Xanh'),
	('MS003', 'Đen'),
	('MS004', 'Đỏ');

-- Dumping structure for table hieusachdb.nhacungcap
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `maNCC` varchar(255) NOT NULL,
  `diaChi` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `tenNCC` varchar(255) NOT NULL,
  PRIMARY KEY (`maNCC`),
  UNIQUE KEY `UK_7nqpt5bqif022nmpmi92lu8an` (`email`),
  UNIQUE KEY `UK_rgkd1co2j3d9ea40hpbed8qg8` (`sdt`),
  UNIQUE KEY `UK_pagyi7ckd52afiecf0af5irma` (`tenNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.nhacungcap: ~8 rows (approximately)
INSERT INTO `nhacungcap` (`maNCC`, `diaChi`, `email`, `sdt`, `tenNCC`) VALUES
	('NCC001', 'HCM', 'nccxanhxanh@gmail.com', '0858484522', 'NCC xanh xanh'),
	('NCC002', 'GV-HCM', 'nccDoDo@gmail.com', '0858484523', 'NCC đỏ đỏ'),
	('NCC003', 'GV-HCM', 'nccBeo@gmail.com', '0858484525', 'NCC đỏ đen'),
	('NCC004', 'K14 Đường Ngọc Hồi, Thanh Trì, Hà Nội', 'infokhangthinh@gmail.com', '02438235235', 'Công Ty TNHH SX Và TM Khang Thịnh'),
	('NCC005', 'Ðông Lường, TP Ðông Hà,  Quảng Trị', 'viethongchinh@gmail.com', '02333571777', 'Công Ty TCPTM & XNK Việt Hồng Chinh'),
	('NCC006', 'Tiểu khu Phú Mỹ,Thị trấn Phú Xuyên, Huyện Phú Xuyên, Hà Nội', 'tnhhhuongtuyet@gmail.com', '0500462073', 'Công Ty TNHH Hướng Tuyết'),
	('NCC007', 'Lô 8A, Đường Đồng Khởi, P.Tân Hiệp, Tp.Biên Hòa, Ðồng Nai', 'dhp_honda@gmail.com', '0613675522', 'Công Ty TNHH Hoa Bình Minh'),
	('NCC008', ' 76 Ðiện Biên Phủ, P. Chính Gian, Q. Thanh Khê, Tp. Ðà Nẵng', 'hiepgiaphat2012@gmail.com', '05113649993', 'Công Ty TNHH thương mại và dịch vụ Hiệp Gia Phát');

-- Dumping structure for table hieusachdb.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `maNhanVien` varchar(255) NOT NULL,
  `OTP` varchar(255) DEFAULT NULL,
  `cCCD` varchar(255) NOT NULL,
  `caLamViec` bit(1) NOT NULL,
  `chucVu` bit(1) NOT NULL,
  `diaChi` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `gioiTinh` bit(1) NOT NULL,
  `hanOTP` datetime(6) DEFAULT NULL,
  `hinhAnh` varchar(255) DEFAULT NULL,
  `hoTenNhanVien` varchar(255) NOT NULL,
  `ngaySinh` date NOT NULL,
  `sdt` varchar(255) NOT NULL,
  PRIMARY KEY (`maNhanVien`),
  UNIQUE KEY `UK_42u7m73aepvdylkyfr0qf24yd` (`cCCD`),
  UNIQUE KEY `UK_fkvqc0pmghgic1n0ldxheu0ns` (`email`),
  UNIQUE KEY `UK_89db22bia72okfxo94fi5f7uv` (`sdt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.nhanvien: ~5 rows (approximately)
INSERT INTO `nhanvien` (`maNhanVien`, `OTP`, `cCCD`, `caLamViec`, `chucVu`, `diaChi`, `email`, `gioiTinh`, `hanOTP`, `hinhAnh`, `hoTenNhanVien`, `ngaySinh`, `sdt`) VALUES
	('NV000001', NULL, '02012320462', b'1', b'0', 'TP. Hồ Chí Minh', 'ngduc0106@gmail.com', b'1', NULL, 'nvtri.jpg', 'Hà Hữu Vinh', '2002-12-12', '0967628711'),
	('NV000002', NULL, '02120120462', b'0', b'0', 'Ho Chi Minh thanh pho khong ngu', 'leminhbao@gmail.com', b'1', NULL, 'nvduy.jpg', 'Jack 5 củ', '2002-11-09', '0969828711'),
	('NV006', NULL, '096203000821', b'0', b'1', '416 23 10 Dương Châu Chấu', 'lehoangnam.3112003@gmail.com', b'0', NULL, 'defaul', 'Le Hoang Nam', '2003-04-30', '0858484521'),
	('QL000003', NULL, '02120120463', b'1', b'1', 'TP. Hồng Ngự', 'avcas@gmail.com', b'1', NULL, 'nvduc.jpg', 'Nguyễn Hồng Đức', '2003-06-01', '0929438043'),
	('QL23405', NULL, '096203000560', b'1', b'1', 'Dương Quảng Hàm', 'bac@gmail.com', b'1', NULL, '', 'Le Hoang Bac', '2003-04-30', '0848383532');

-- Dumping structure for table hieusachdb.nhaxuatban
CREATE TABLE IF NOT EXISTS `nhaxuatban` (
  `maNXB` varchar(255) NOT NULL,
  `tenNXB` varchar(255) NOT NULL,
  PRIMARY KEY (`maNXB`),
  UNIQUE KEY `UK_6to1slse00ua8y8nsj8mu6r6j` (`tenNXB`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.nhaxuatban: ~5 rows (approximately)
INSERT INTO `nhaxuatban` (`maNXB`, `tenNXB`) VALUES
	('NXB005', 'Dân Trí'),
	('NXB004', 'Giáo dục'),
	('NXB003', 'Kim Ðồng'),
	('NXB002', 'Nhà xuất bản Bèo'),
	('NXB001', 'Nhà xuất bản Tuổi Trẻ'),
	('NXB006', 'NXB Bèo');

-- Dumping structure for table hieusachdb.sachloi
CREATE TABLE IF NOT EXISTS `sachloi` (
  `loiSanPham` varchar(255) NOT NULL,
  `soLuong` int(11) NOT NULL,
  `maSach` varchar(255) NOT NULL,
  PRIMARY KEY (`maSach`),
  CONSTRAINT `FKa0yupkofys6pdvorjm6afr5li` FOREIGN KEY (`maSach`) REFERENCES `sanpham` (`maSanPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.sachloi: ~4 rows (approximately)
INSERT INTO `sachloi` (`loiSanPham`, `soLuong`, `maSach`) VALUES
	('Bị Dính Trang', 5, 'SP00001'),
	('Dính bìa', 1, 'SP00006'),
	('Toi Muon Doi', 1, 'SP00007'),
	('kkkk', 2, 'SP00027');

-- Dumping structure for table hieusachdb.sanpham
CREATE TABLE IF NOT EXISTS `sanpham` (
  `phanBietSanPham` varchar(31) NOT NULL,
  `maSanPham` varchar(255) NOT NULL,
  `donViSanPham` varchar(255) DEFAULT NULL,
  `ghiChu` varchar(255) DEFAULT NULL,
  `giaNhap` bigint(20) NOT NULL,
  `hinhAnh` varchar(255) DEFAULT NULL,
  `loaiSanPham` varchar(255) NOT NULL,
  `soLuongTon` int(11) NOT NULL,
  `trongLuong` double NOT NULL,
  `namXB` int(11) DEFAULT NULL,
  `soTrang` int(11) DEFAULT NULL,
  `tenSach` varchar(255) DEFAULT NULL,
  `tenVanPhongPham` varchar(255) DEFAULT NULL,
  `maNCC` varchar(255) DEFAULT NULL,
  `maNXB` varchar(255) DEFAULT NULL,
  `maTacGia` varchar(255) DEFAULT NULL,
  `maTheLoai` varchar(255) DEFAULT NULL,
  `maChatLieu` varchar(255) DEFAULT NULL,
  `maLoaiVanPhongPham` varchar(255) DEFAULT NULL,
  `maMauSac` varchar(255) DEFAULT NULL,
  `maXuatXu` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`maSanPham`),
  KEY `FK88w6u2of9fyt7eqoqwso1creb` (`maNCC`),
  KEY `FKnv1g0147hbfhbibby4yohvh0o` (`maNXB`),
  KEY `FKhuv9cxmk3ns1aqwouh7wb6hr4` (`maTacGia`),
  KEY `FKqqirxyugbl4vesmhdl0joided` (`maTheLoai`),
  KEY `FKh8xh26veeyj285rcpware8uop` (`maChatLieu`),
  KEY `FKtmr8a1rd0ttqyacxhba9losaw` (`maLoaiVanPhongPham`),
  KEY `FK849lu6yj6kcdwqirmn91w6ovp` (`maMauSac`),
  KEY `FKq777624trfeydiq5ab9aarr22` (`maXuatXu`),
  CONSTRAINT `FK849lu6yj6kcdwqirmn91w6ovp` FOREIGN KEY (`maMauSac`) REFERENCES `mausac` (`maMauSac`),
  CONSTRAINT `FK88w6u2of9fyt7eqoqwso1creb` FOREIGN KEY (`maNCC`) REFERENCES `nhacungcap` (`maNCC`),
  CONSTRAINT `FKh8xh26veeyj285rcpware8uop` FOREIGN KEY (`maChatLieu`) REFERENCES `chatlieu` (`maChatLieu`),
  CONSTRAINT `FKhuv9cxmk3ns1aqwouh7wb6hr4` FOREIGN KEY (`maTacGia`) REFERENCES `tacgia` (`maTacGia`),
  CONSTRAINT `FKnv1g0147hbfhbibby4yohvh0o` FOREIGN KEY (`maNXB`) REFERENCES `nhaxuatban` (`maNXB`),
  CONSTRAINT `FKq777624trfeydiq5ab9aarr22` FOREIGN KEY (`maXuatXu`) REFERENCES `xuatxu` (`maXuatXu`),
  CONSTRAINT `FKqqirxyugbl4vesmhdl0joided` FOREIGN KEY (`maTheLoai`) REFERENCES `theloaisach` (`maTheLoai`),
  CONSTRAINT `FKtmr8a1rd0ttqyacxhba9losaw` FOREIGN KEY (`maLoaiVanPhongPham`) REFERENCES `loaivanphongpham` (`maLoaiVanPhongPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.sanpham: ~28 rows (approximately)
INSERT INTO `sanpham` (`phanBietSanPham`, `maSanPham`, `donViSanPham`, `ghiChu`, `giaNhap`, `hinhAnh`, `loaiSanPham`, `soLuongTon`, `trongLuong`, `namXB`, `soTrang`, `tenSach`, `tenVanPhongPham`, `maNCC`, `maNXB`, `maTacGia`, `maTheLoai`, `maChatLieu`, `maLoaiVanPhongPham`, `maMauSac`, `maXuatXu`) VALUES
	('Sach', 'SP00001', 'Cuốn', 'Không', 484522, NULL, 'Sách', 97, 0.5, 1990, 100, 'Làm bạn với bầu trời', NULL, 'NCC001', 'NXB001', 'TG001', 'L003', NULL, NULL, NULL, NULL),
	('Sach', 'SP00002', 'Cuốn', 'Không', 23000, NULL, 'Sách', 53, 0.5, 2000, 100, 'Vị tu sĩ bán chiếc ferrari', NULL, 'NCC002', 'NXB003', 'TG002', 'L003', NULL, NULL, NULL, NULL),
	('VanPhongPham', 'SP00003', 'Cái', 'Không', 3000, 'sanpham3.jpg', 'Văn phòng phẩm', 60, 0.5, NULL, NULL, NULL, 'Bút bi', 'NCC001', NULL, NULL, NULL, 'CL001', 'TL001', 'MS001', 'XX001'),
	('VanPhongPham', 'SP00004', 'Cái', 'Không', 4000, 'sanpham4.jpg', 'Văn phòng phẩm', 54, 0.5, NULL, NULL, NULL, 'Cục tẩy', 'NCC001', NULL, NULL, NULL, 'CL003', 'TL001', 'MS002', 'XX001'),
	('Sach', 'SP00006', 'Cuốn', 'Không', 35000, NULL, 'Sách', 64, 0.5, 2012, 100, 'Sách Letsgo 1', NULL, 'NCC003', 'NXB002', 'TG004', 'L001', NULL, NULL, NULL, NULL),
	('Sach', 'SP00007', 'Cuốn', 'Không', 90000, NULL, 'Sách', 43, 0.5, 2011, 129, 'Bậc thầy kinh doanh', NULL, 'NCC005', 'NXB003', 'TG005', 'L005', NULL, NULL, NULL, NULL),
	('VanPhongPham', 'SP00009', 'Cái', 'Không', 10000, 'sanpham9.jpg', 'Văn phòng phẩm', 65, 0.5, NULL, NULL, NULL, 'Bút chì ngòi', 'NCC005', NULL, NULL, NULL, 'CL001', 'TL001', 'MS001', 'XX001'),
	('Sach', 'SP00010', 'Cuốn', 'Không', 60000, NULL, 'Sách', 100, 0.5, 2022, 100, 'Nghịch lý cuộc đời', NULL, 'NCC002', 'NXB003', 'TG006', 'L004', NULL, NULL, NULL, NULL),
	('Sach', 'SP00011', 'Cuốn', 'Không', 5000, NULL, 'Sách', 54, 0.5, 2009, 143, 'Muôn kiếp nhân sinh', NULL, 'NCC003', 'NXB003', 'TG007', 'L004', NULL, NULL, NULL, NULL),
	('VanPhongPham', 'SP00013', 'Cái', 'Không', 15000, 'sanpham13.jpg', 'Văn phòng phẩm', 87, 0.5, NULL, NULL, NULL, 'Compa', 'NCC001', NULL, NULL, NULL, 'CL001', 'TL002', 'MS004', 'XX001'),
	('VanPhongPham', 'SP00014', 'Cái', 'Không', 10000, 'sanpham14.jpg', 'Văn phòng phẩm', 43, 0.5, NULL, NULL, NULL, 'Nhãn vở', 'NCC001', NULL, NULL, NULL, 'CL002', 'TL001', 'MS005', 'XX001'),
	('Sach', 'SP00015', 'Cuốn', 'Không', 150000, 'sanpham15.jpg', 'Sách', 43, 0.5, 2000, 132, 'Tư duy sâu', NULL, 'NCC004', 'NXB003', 'TG009', 'L004', NULL, NULL, NULL, NULL),
	('Sach', 'SP00016', 'Cuốn', 'Không', 20000, 'sanpham16.jpg', 'Sách', 65, 0.5, 2007, 154, 'Bé làm họa sĩ', NULL, 'NCC004', 'NXB002', NULL, 'L006', NULL, NULL, NULL, NULL),
	('Sach', 'SP00017', 'Cuốn', 'Không', 27000, 'sanpham17.jpg', 'Sách', 43, 0.5, 1999, 100, 'Bé tập tô', NULL, 'NCC001', 'NXB002', NULL, 'L006', NULL, NULL, NULL, NULL),
	('VanPhongPham', 'SP00018', 'Cái', 'Không', 15000, 'sanpham18.jpg', 'Văn phòng phẩm', 76, 0.5, NULL, NULL, NULL, 'Bao bì', 'NCC002', NULL, NULL, NULL, 'CL002', 'TL001', 'MS005', 'XX001'),
	('VanPhongPham', 'SP00019', 'Hộp', 'Không', 20000, 'sanpham19.jpg', 'Văn phòng phẩm', 65, 0.5, NULL, NULL, NULL, 'Bút chì màu', 'NCC002', NULL, NULL, NULL, 'CL004', 'TL003', 'MS004', 'XX001'),
	('Sach', 'SP00020', 'Cuốn', 'Không', 15000, 'sanpham20.jpg', 'Sách', 54, 0.5, 2009, 129, 'Bộ sách giáo khoa lớp 6', NULL, 'NCC002', 'NXB002', NULL, 'L001', NULL, NULL, NULL, NULL),
	('Sach', 'SP00021', 'Cuốn', 'Không', 20000, 'sanpham21.jpg', 'Sách', 87, 0.5, 2012, 129, 'Sách toán nâng cao lớp 7', NULL, 'NCC002', 'NXB002', NULL, 'L001', NULL, NULL, NULL, NULL),
	('Sach', 'SP00022', 'Cuốn', 'Không', 27000, 'sanpham22.jpg', 'Sách', 54, 0.5, 2013, 200, 'Sách văn mẫu tiểu học', NULL, 'NCC003', 'NXB002', NULL, 'L001', NULL, NULL, NULL, NULL),
	('VanPhongPham', 'SP00023', 'Cái', 'Không', 150000, 'sanpham23.jpg', 'Văn phòng phẩm', 43, 0.5, NULL, NULL, NULL, 'Cặp', 'NCC002', NULL, NULL, NULL, 'CL005', 'TL003', 'MS003', 'XX001'),
	('VanPhongPham', 'SP00024', 'Cái', 'Không', 10000, 'sanpham24.jpg', 'Văn phòng phẩm', 76, 0.5, NULL, NULL, NULL, 'Khăn quàng', 'NCC003', NULL, NULL, NULL, 'CL005', 'TL001', 'MS002', 'XX001'),
	('Sach', 'SP00025', 'Cuốn', 'Không', 15000, 'sanpham25.jpg', 'Sách', 12, 0.5, 2014, 132, 'Bí quyết hóa rồng', NULL, 'NCC004', 'NXB003', 'TG012', 'L004', NULL, NULL, NULL, NULL),
	('Sach', 'SP00026', 'Cuốn', 'Không', 25000, 'sanpham26.jpg', 'Sách', 45, 0.5, 2015, 203, 'Câm lặng', NULL, 'NCC004', 'NXB003', 'TG013', 'L002', NULL, NULL, NULL, NULL),
	('Sach', 'SP00027', 'Cuốn', 'Không', 27000, 'sanpham27.jpg', 'Sách', 54, 0.5, 2017, 400, 'Thần thoại Hy Lạp', NULL, 'NCC005', 'NXB003', 'TG014', 'L002', NULL, NULL, NULL, NULL),
	('Sach', 'SP00031', 'Quyển', 'An trong', 100000, 'Hinh anh', 'Sách', 156, 0.5, 0, 0, 'Dua An Tiem', NULL, 'NCC005', 'NXB006', 'TG007', 'L001', NULL, NULL, NULL, NULL),
	('Sach', 'SP00033', 'Quyển', 'Ghi chú', 100000, 'Hinh anh', 'Sách', 100, 0.5, 0, 0, 'Ten sach', NULL, 'NCC005', 'NXB006', 'TG007', 'L001', NULL, NULL, NULL, NULL),
	('VanPhongPham', 'SP00044', 'Cái', 'An trong', 100000, 'Hinh anh', 'Văn phòng phẩm', 155, 0.5, NULL, NULL, NULL, 'Ten văn phòng phẩm', 'NCC005', NULL, NULL, NULL, 'CL001', 'TL001', 'MS001', 'XX001'),
	('Sach', 'SP00045', 'Cuốn', '', 50000, 'bookUnknow.jpg', 'Sách', 300, 3, 2024, 1200, 'Vinh', NULL, 'NCC004', 'NXB005', 'TG012', 'L004', NULL, NULL, NULL, NULL);

-- Dumping structure for table hieusachdb.tacgia
CREATE TABLE IF NOT EXISTS `tacgia` (
  `maTacGia` varchar(255) NOT NULL,
  `tenTacGia` varchar(255) NOT NULL,
  PRIMARY KEY (`maTacGia`),
  UNIQUE KEY `UK_sff9tbylxchradwkl25kq1bb4` (`tenTacGia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.tacgia: ~13 rows (approximately)
INSERT INTO `tacgia` (`maTacGia`, `tenTacGia`) VALUES
	('TG014', 'Actéon Ariane'),
	('TG013', 'Eric Rickstad'),
	('TG007', 'John Vu'),
	('TG015', 'Kateo Hearn'),
	('TG006', 'Kent Keith'),
	('TG012', 'Lý Quang Diệu'),
	('TG001', 'Nguyễn Nhật Ánh'),
	('TG009', 'Nguyễn Thanh Hương'),
	('TG003', 'Nguyễn Văn Thiện'),
	('TG004', 'Reberacca Weerasekera'),
	('TG002', 'Robin Sharma'),
	('TG008', 'The Windy'),
	('TG005', 'Trương Ðông Triết');

-- Dumping structure for table hieusachdb.taikhoan
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `tenDangNhap` varchar(255) NOT NULL,
  `matKhau` varchar(255) NOT NULL,
  `quyen` bit(1) NOT NULL,
  `maNhanVien` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tenDangNhap`),
  UNIQUE KEY `UK_frj67v085t8svpf59pu7jwggf` (`maNhanVien`),
  CONSTRAINT `FK9lupyi9pufop3bkwjm4ig97u` FOREIGN KEY (`maNhanVien`) REFERENCES `nhanvien` (`maNhanVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.taikhoan: ~4 rows (approximately)
INSERT INTO `taikhoan` (`tenDangNhap`, `matKhau`, `quyen`, `maNhanVien`) VALUES
	('bac', '123456', b'1', 'QL23405'),
	('Duc', '123456', b'1', 'QL000003'),
	('Nam', '123456', b'1', 'NV000001'),
	('Vinh', '123456', b'1', 'NV000002');

-- Dumping structure for table hieusachdb.theloaisach
CREATE TABLE IF NOT EXISTS `theloaisach` (
  `maTheLoai` varchar(255) NOT NULL,
  `tenTheLoai` varchar(255) NOT NULL,
  PRIMARY KEY (`maTheLoai`),
  UNIQUE KEY `UK_gmcw3sjl80ws4q8u8flnmu1wp` (`tenTheLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.theloaisach: ~6 rows (approximately)
INSERT INTO `theloaisach` (`maTheLoai`, `tenTheLoai`) VALUES
	('L004', 'Kĩ năng sống'),
	('L005', 'Kinh doanh'),
	('L001', 'SGK'),
	('L006', 'Thiếu nhi'),
	('L003', 'Tiểu thuyết'),
	('L002', 'Truyện');

-- Dumping structure for table hieusachdb.xuatxu
CREATE TABLE IF NOT EXISTS `xuatxu` (
  `maXuatXu` varchar(255) NOT NULL,
  `tenXuatXu` varchar(255) NOT NULL,
  PRIMARY KEY (`maXuatXu`),
  UNIQUE KEY `UK_ycj737900c3on5hg0dhbnbph` (`tenXuatXu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table hieusachdb.xuatxu: ~3 rows (approximately)
INSERT INTO `xuatxu` (`maXuatXu`, `tenXuatXu`) VALUES
	('XX002', 'Hoa Kì'),
	('XX003', 'Trung Quốc'),
	('XX001', 'Việt Nam');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
