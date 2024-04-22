package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.rmi.Naming;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import lombok.SneakyThrows;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;
import entities.KhachHang;
import entities.NhanVien;
import entities.SanPham;
import service.*;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamServiceImpl;
import util.Constants;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Pnl_ThongKeQuanLy extends JPanel implements MouseListener, ActionListener {
    private static DefaultCategoryDataset dataset;
    /**
     * Create the panel.
     */

    private JScrollPane scrTop10KH;
    private JTable tblTop10KH;
    private DefaultTableModel modelTop10KH;
    private JTabbedPane tabThongKe;
    private JPanel pnlThongKeDoanhThu;
    private JLabel lblFrom;
    private JLabel lblTo;
    private JLabel lblLocTheo;
    private JDateChooser dateChooserFromDoanhThu;
    private JDateChooser dateChooserToDoanhThu;
    private JComboBox cmbTieuChiDoanhThu;
    private JButton btnLocDT;
    private JLabel lblTongSoHoaDon;
    private JLabel lblDoanhThu;
    private ChartPanel chartPanel;
    private JLabel lblconCount;
    private JLabel lblIconMoney;
    private JLabel lblGiaTriTongHoaDon;
    private JLabel lblGiaTriDoanhThu;
    private JPanel pnlThongKeSanPham;
    private JPanel pnlTongSoLuongSach;
    private JLabel lblTongSoLuongSach;
    private JPanel pnlTongSoLuongVanPhongPham;
    private JLabel lblTongSoVanPhongPham;
    private JPanel pnlTongSoSachLoi;
    private JLabel lblTongSoSachLoi;
    private JPanel pnlLocSPBanChay;
    private JPanel pnlSachBanChayNhat;
    private JLabel lblSanPhamBanChayNhat;
    private JButton btnLocSP;
    private JLabel lblFromThongKeSP;
    private JDateChooser dateChooserFromThongKeSP;
    private JDateChooser dateChooserToThongKeSP;
    private JLabel lblToThongKeSP;
    private JLabel lblBoLocSPBanChay;
    private JPanel pnlThongKeKhachHang;
    private JPanel pnlTop10;
    private JLabel lblTitleDSKHThanThiet;
    private JPanel pnlLoc;
    private JLabel lblFromThongKeKH;
    private JLabel lblToThongKeKH;
    private JDateChooser dateChooserFromKH;
    private JDateChooser dateChooserToKH;
    private JLabel lblTenKHMuaNhieuNhat;
    private JButton btnLocKH;
    private JLabel lblSoTienKhachDaMua;
    private JLabel lblTieuDe;
    private JLabel lblLocKH;

    private static final String URL = "rmi://" + Constants.IPV4 + ":" + Constants.PORT + "/";
    private SanPhamService sanPhamService = (SanPhamService) Naming.lookup(URL + "sanPham");
    private HoaDonService hoaDonService = (HoaDonService) Naming.lookup(URL + "hoaDon");
    private NhanVienService nhanVienService = (NhanVienService) Naming.lookup(URL + "nhanVien");
    private KhachHangService khachHangService = (KhachHangService) Naming.lookup(URL + "khachHang");

    private JLabel lblValueSoLuongSach;
    private JLabel lblValueSoLuongVPP;
    private JLabel lblValueSoLuongSachLoi;
    private List<NhanVien> dsNhanVien;
    private List<SanPham> dsSanPham;
    private List<KhachHang> dsKhachHang;
    private JLabel lblValueTop1NV;
    private JLabel lblTop1NV;
    private JLabel lblTenKHValue;
    private JLabel lblSoTienDaMuaValue;
    private JLabel lblmaSPTop1;
    private JLabel lblloaiSpTop1;
    private JLabel lblGiaBanTop1;
    private JLabel lblSoLuongBanTop1;
    private JPanel pnlSanPhamTop1;
    private JLabel lblHinhAnhTop1;
    private JLabel lblMaSP;
    private JLabel lblTenSPTop1;
    private JLabel lblTenSanPham;
    private JLabel lblTenSP;
    private JLabel lblGiaBan;
    private JLabel lblSoLuongDaBan;
    private JPanel pnlThongKeNhanVien;
    private JLabel lblFromThongKeSP_1;
    private JDateChooser dateChooserFromThongKeNV;
    private JDateChooser dateChooserToThongKeNV;
    private JLabel lblToThongKeSP_1;
    private DefaultTableModel tableModel_NV;
    private JTable tblNhanVien;
    private Component componentSp_TblNhanVien;
    private JButton btnLocNV;

    public static void main(String[] args) throws Exception {
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));

        Pnl_ThongKeQuanLy pnl_ThongKeQuanLy = new Pnl_ThongKeQuanLy();
        pnl_ThongKeQuanLy.add(chartPanel);

        pnl_ThongKeQuanLy.setVisible(true);
    }

    public static JFreeChart createChart() {
        JFreeChart barChart = ChartFactory.createBarChart("BIỂU ĐỒ DOANH THU", "Tháng", "Doanh thu", createDataset(),
                PlotOrientation.VERTICAL, false, false, false);
        return barChart;
    }

    private static CategoryDataset createDataset() {
        dataset = new DefaultCategoryDataset();
        dataset.addValue(0, "Doanh thu", "1");
        dataset.addValue(0, "Doanh thu", "2");
        dataset.addValue(0, "Doanh thu", "3");
        dataset.addValue(0, "Doanh thu", "4");
        dataset.addValue(0, "Doanh thu", "5");
        dataset.addValue(0, "Doanh thu", "6");
        dataset.addValue(0, "Doanh thu", "7");
        dataset.addValue(0, "Doanh thu", "8");
        dataset.addValue(0, "Doanh thu", "9");
        dataset.addValue(0, "Doanh thu", "10");
        dataset.addValue(0, "Doanh thu", "11");
        dataset.addValue(0, "Doanh thu", "12");
        return dataset;
    }

    public Pnl_ThongKeQuanLy() throws Exception {

        setLayout(null);
        setSize(1900, 900);
        tabThongKe = new JTabbedPane(JTabbedPane.TOP);
        tabThongKe.setFont(new Font("Tahoma", Font.BOLD, 14));
        tabThongKe.setBounds(27, 79, 1450, 610);
        add(tabThongKe);

        pnlThongKeDoanhThu = new JPanel();
        pnlThongKeDoanhThu.setBackground(new Color(0, 206, 209));
        tabThongKe.addTab("Thống kê doanh thu", null, pnlThongKeDoanhThu, null);
        pnlThongKeDoanhThu.setLayout(null);
        pnlThongKeDoanhThu.setSize(1900, 900);

        lblFrom = new JLabel("Từ ngày: ");
        lblFrom.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFrom.setBounds(10, 52, 77, 32);
        pnlThongKeDoanhThu.add(lblFrom);

        lblTo = new JLabel("Đến ngày: ");
        lblTo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTo.setBounds(10, 133, 92, 32);
        pnlThongKeDoanhThu.add(lblTo);

        lblLocTheo = new JLabel("Lọc theo: ");
        lblLocTheo.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblLocTheo.setBounds(10, 207, 77, 32);
        pnlThongKeDoanhThu.add(lblLocTheo);

        dateChooserFromDoanhThu = new JDateChooser();
        dateChooserFromDoanhThu.setBounds(97, 58, 154, 26);
        pnlThongKeDoanhThu.add(dateChooserFromDoanhThu);

        dateChooserToDoanhThu = new JDateChooser();
        dateChooserToDoanhThu.setBounds(97, 133, 154, 26);
        pnlThongKeDoanhThu.add(dateChooserToDoanhThu);

        cmbTieuChiDoanhThu = new JComboBox();
        cmbTieuChiDoanhThu.setBounds(97, 208, 154, 32);
        cmbTieuChiDoanhThu.addItem("3 tháng gần nhất");
        cmbTieuChiDoanhThu.addItem("6 tháng gần nhất");
        cmbTieuChiDoanhThu.addItem("9 tháng gần nhất");
        pnlThongKeDoanhThu.add(cmbTieuChiDoanhThu);

        btnLocDT = new JButton("Lọc");
        btnLocDT.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
        btnLocDT.setBounds(261, 208, 88, 32);
        pnlThongKeDoanhThu.add(btnLocDT);

        lblTongSoHoaDon = new JLabel("Tổng số hóa đơn: ");
        lblTongSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTongSoHoaDon.setBounds(10, 389, 115, 14);
        pnlThongKeDoanhThu.add(lblTongSoHoaDon);

        lblDoanhThu = new JLabel("Doanh thu: ");
        lblDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDoanhThu.setBounds(10, 470, 77, 14);
        pnlThongKeDoanhThu.add(lblDoanhThu);

        chartPanel = new ChartPanel(createChart());
        chartPanel.setBackground(new Color(0, 206, 209));
        chartPanel.setBounds(359, 33, 1065, 490);
        pnlThongKeDoanhThu.add(chartPanel);

        lblconCount = new JLabel("");
        lblconCount.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/count.png")));
        lblconCount.setBounds(10, 414, 48, 45);
        pnlThongKeDoanhThu.add(lblconCount);

        lblIconMoney = new JLabel("");
        lblIconMoney.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/money.png")));
        lblIconMoney.setBounds(10, 495, 36, 34);
        pnlThongKeDoanhThu.add(lblIconMoney);

        lblGiaTriTongHoaDon = new JLabel("0");
        lblGiaTriTongHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGiaTriTongHoaDon.setBounds(56, 427, 46, 14);
        pnlThongKeDoanhThu.add(lblGiaTriTongHoaDon);

        lblGiaTriDoanhThu = new JLabel("0");
        lblGiaTriDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGiaTriDoanhThu.setBounds(56, 497, 257, 14);
        pnlThongKeDoanhThu.add(lblGiaTriDoanhThu);

        lblTop1NV = new JLabel("Nhân viên bán được nhiều nhất: ");
        lblTop1NV.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTop1NV.setBounds(10, 282, 218, 14);
        pnlThongKeDoanhThu.add(lblTop1NV);

        lblValueTop1NV = new JLabel("");
        lblValueTop1NV.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblValueTop1NV.setBounds(10, 319, 218, 14);
        pnlThongKeDoanhThu.add(lblValueTop1NV);

        // ----------------------------------------------------
        pnlThongKeKhachHang = new JPanel();
        pnlThongKeKhachHang.setBackground(new Color(0, 206, 209));
        tabThongKe.addTab("Thống kê khách hàng", null, pnlThongKeKhachHang, null);
        pnlThongKeKhachHang.setLayout(null);

        pnlTop10 = new JPanel();
        pnlTop10.setBounds(798, 57, 547, 477);
        pnlThongKeKhachHang.add(pnlTop10);
        pnlTop10.setLayout(null);

        lblTitleDSKHThanThiet = new JLabel("Danh sách top 10 khách hàng thân thiết:");
        lblTitleDSKHThanThiet.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitleDSKHThanThiet.setBounds(0, 0, 547, 34);
        pnlTop10.add(lblTitleDSKHThanThiet);
        lblTitleDSKHThanThiet.setFont(new Font("Tahoma", Font.BOLD, 13));

        String header_top10KH[] = {"STT", "Mã khách hàng", "Tên khách hàng", "Số tiền đã mua", "Số hóa đơn đã mua"};
        modelTop10KH = new DefaultTableModel(header_top10KH, 0);

        tblTop10KH = new JTable(modelTop10KH);
        scrTop10KH = new JScrollPane(tblTop10KH, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrTop10KH.setBounds(0, 45, 547, 421);
        pnlTop10.add(scrTop10KH);
        tblTop10KH.getColumnModel().getColumn(0).setPreferredWidth(20);

        tblTop10KH.getColumnModel().getColumn(0).setPreferredWidth(20);
        pnlLoc = new JPanel();
        pnlLoc.setBounds(54, 57, 673, 477);
        pnlThongKeKhachHang.add(pnlLoc);
        pnlLoc.setLayout(null);

        lblLocKH = new JLabel("Lọc khách hàng mua nhiều nhất theo thời gian");
        lblLocKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblLocKH.setHorizontalAlignment(SwingConstants.CENTER);
        lblLocKH.setBounds(0, 25, 673, 32);
        pnlLoc.add(lblLocKH);

        lblFromThongKeKH = new JLabel("Từ: ");
        lblFromThongKeKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblFromThongKeKH.setBounds(41, 84, 46, 14);
        pnlLoc.add(lblFromThongKeKH);

        lblToThongKeKH = new JLabel("Đến: ");
        lblToThongKeKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblToThongKeKH.setBounds(41, 146, 46, 14);
        pnlLoc.add(lblToThongKeKH);

        dateChooserFromKH = new JDateChooser();
        dateChooserFromKH.setBounds(196, 84, 151, 32);
        pnlLoc.add(dateChooserFromKH);

        dateChooserToKH = new JDateChooser();
        dateChooserToKH.setBounds(195, 146, 152, 32);
        pnlLoc.add(dateChooserToKH);

        lblTenKHMuaNhieuNhat = new JLabel("Tên Khách Hàng: ");
        lblTenKHMuaNhieuNhat.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTenKHMuaNhieuNhat.setBounds(41, 270, 125, 32);
        pnlLoc.add(lblTenKHMuaNhieuNhat);

        btnLocKH = new JButton("Lọc");
        btnLocKH.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnLocKH.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
        btnLocKH.setBounds(389, 146, 89, 32);
        pnlLoc.add(btnLocKH);

        lblSoTienKhachDaMua = new JLabel("Số tiền đã mua: ");
        lblSoTienKhachDaMua.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSoTienKhachDaMua.setBounds(41, 348, 125, 14);
        pnlLoc.add(lblSoTienKhachDaMua);

        lblTenKHValue = new JLabel("...");
        lblTenKHValue.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTenKHValue.setBounds(196, 275, 236, 23);
        pnlLoc.add(lblTenKHValue);

        lblSoTienDaMuaValue = new JLabel("0");
        lblSoTienDaMuaValue.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSoTienDaMuaValue.setBounds(196, 348, 243, 14);
        pnlLoc.add(lblSoTienDaMuaValue);

        // -----------------------------------------------

        pnlThongKeSanPham = new JPanel();
        pnlThongKeSanPham.setBackground(new Color(0, 206, 209));
        tabThongKe.addTab("Thống kê sách", null, pnlThongKeSanPham, null); // 1
        pnlThongKeSanPham.setLayout(null);

        pnlTongSoLuongSach = new JPanel();
        pnlTongSoLuongSach.setBounds(925, 23, 253, 152);
        pnlThongKeSanPham.add(pnlTongSoLuongSach);
        pnlTongSoLuongSach.setLayout(null);

        lblTongSoLuongSach = new JLabel("    Tổng số lượng sách: \r\n");
        lblTongSoLuongSach.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/books.png")));
        lblTongSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
        lblTongSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTongSoLuongSach.setBounds(0, 0, 253, 36);
        pnlTongSoLuongSach.add(lblTongSoLuongSach);

        lblValueSoLuongSach = new JLabel("New label");
        lblValueSoLuongSach.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblValueSoLuongSach.setHorizontalAlignment(SwingConstants.CENTER);
        lblValueSoLuongSach.setBounds(0, 46, 253, 83);
        pnlTongSoLuongSach.add(lblValueSoLuongSach);

        pnlTongSoLuongVanPhongPham = new JPanel();
        pnlTongSoLuongVanPhongPham.setBounds(925, 376, 253, 152);
        pnlThongKeSanPham.add(pnlTongSoLuongVanPhongPham);
        pnlTongSoLuongVanPhongPham.setLayout(null);

        lblTongSoVanPhongPham = new JLabel("    Tổng số văn phòng phẩm: ");
        lblTongSoVanPhongPham.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/stationery.png")));
        lblTongSoVanPhongPham.setHorizontalAlignment(SwingConstants.CENTER);
        lblTongSoVanPhongPham.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTongSoVanPhongPham.setBounds(0, 0, 263, 37);
        pnlTongSoLuongVanPhongPham.add(lblTongSoVanPhongPham);

        lblValueSoLuongVPP = new JLabel("New label");
        lblValueSoLuongVPP.setHorizontalAlignment(SwingConstants.CENTER);
        lblValueSoLuongVPP.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblValueSoLuongVPP.setBounds(0, 48, 253, 83);
        pnlTongSoLuongVanPhongPham.add(lblValueSoLuongVPP);

        pnlTongSoSachLoi = new JPanel();
        pnlTongSoSachLoi.setBounds(925, 196, 253, 152);
        pnlThongKeSanPham.add(pnlTongSoSachLoi);
        pnlTongSoSachLoi.setLayout(null);

        lblTongSoSachLoi = new JLabel("    Tổng số sách lỗi: ");
        lblTongSoSachLoi.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/sachloi.png")));
        lblTongSoSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
        lblTongSoSachLoi.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTongSoSachLoi.setBounds(0, 0, 263, 40);
        pnlTongSoSachLoi.add(lblTongSoSachLoi);

        lblValueSoLuongSachLoi = new JLabel("New label");
        lblValueSoLuongSachLoi.setHorizontalAlignment(SwingConstants.CENTER);
        lblValueSoLuongSachLoi.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblValueSoLuongSachLoi.setBounds(0, 44, 253, 83);
        pnlTongSoSachLoi.add(lblValueSoLuongSachLoi);

        pnlLocSPBanChay = new JPanel();
        pnlLocSPBanChay.setBackground(new Color(173, 255, 47));
        pnlLocSPBanChay.setBounds(144, 23, 551, 523);
        pnlThongKeSanPham.add(pnlLocSPBanChay);
        pnlLocSPBanChay.setLayout(null);

        pnlSachBanChayNhat = new JPanel();
        pnlSachBanChayNhat.setBounds(38, 261, 472, 219);
        pnlLocSPBanChay.add(pnlSachBanChayNhat);
        pnlSachBanChayNhat.setLayout(null);

        lblSanPhamBanChayNhat = new JLabel("    Sản phẩm bán chạy nhất: ");
        lblSanPhamBanChayNhat.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/sachchay.png")));
        lblSanPhamBanChayNhat.setHorizontalAlignment(SwingConstants.CENTER);
        lblSanPhamBanChayNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblSanPhamBanChayNhat.setBounds(0, 0, 472, 42);
        pnlSachBanChayNhat.add(lblSanPhamBanChayNhat);

        lblmaSPTop1 = new JLabel("...");
        lblmaSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblmaSPTop1.setBounds(343, 42, 71, 24);
        pnlSachBanChayNhat.add(lblmaSPTop1);

        lblloaiSpTop1 = new JLabel("...");
        lblloaiSpTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblloaiSpTop1.setBounds(343, 67, 111, 24);
        pnlSachBanChayNhat.add(lblloaiSpTop1);

        lblGiaBanTop1 = new JLabel("...");
        lblGiaBanTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGiaBanTop1.setBounds(343, 132, 129, 14);
        pnlSachBanChayNhat.add(lblGiaBanTop1);

        lblSoLuongBanTop1 = new JLabel("...");
        lblSoLuongBanTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSoLuongBanTop1.setBounds(343, 168, 121, 14);
        pnlSachBanChayNhat.add(lblSoLuongBanTop1);

        pnlSanPhamTop1 = new JPanel();
        pnlSanPhamTop1.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlSanPhamTop1.setBounds(10, 42, 159, 150);
        pnlSachBanChayNhat.add(pnlSanPhamTop1);
        pnlSanPhamTop1.setLayout(null);

        lblHinhAnhTop1 = new JLabel("Hình ảnh");
        lblHinhAnhTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblHinhAnhTop1.setHorizontalAlignment(SwingConstants.CENTER);
        lblHinhAnhTop1.setBounds(0, 0, 159, 150);
        pnlSanPhamTop1.add(lblHinhAnhTop1);

        lblMaSP = new JLabel("Mã sản phẩm: ");
        lblMaSP.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMaSP.setBounds(205, 47, 89, 14);
        pnlSachBanChayNhat.add(lblMaSP);

        lblTenSPTop1 = new JLabel("...");
        lblTenSPTop1.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTenSPTop1.setBounds(343, 105, 129, 19);
        pnlSachBanChayNhat.add(lblTenSPTop1);

        lblTenSanPham = new JLabel("Loại sản phẩm: ");
        lblTenSanPham.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTenSanPham.setBounds(205, 72, 89, 14);
        pnlSachBanChayNhat.add(lblTenSanPham);

        lblTenSP = new JLabel("Tên sản phẩm: ");
        lblTenSP.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTenSP.setBounds(205, 107, 89, 14);
        pnlSachBanChayNhat.add(lblTenSP);

        lblGiaBan = new JLabel("Giá bán: ");
        lblGiaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGiaBan.setBounds(205, 132, 89, 14);
        pnlSachBanChayNhat.add(lblGiaBan);

        lblSoLuongDaBan = new JLabel("Số lượng đã bán: ");
        lblSoLuongDaBan.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblSoLuongDaBan.setBounds(205, 168, 98, 14);
        pnlSachBanChayNhat.add(lblSoLuongDaBan);

        btnLocSP = new JButton("Lọc");
        btnLocSP.setBounds(364, 138, 92, 35);
        pnlLocSPBanChay.add(btnLocSP);
        btnLocSP.setHorizontalAlignment(SwingConstants.LEFT);
        btnLocSP.setIcon(new ImageIcon(Pnl_ThongKeQuanLy.class.getResource("/gui/icon/filter.png")));
        btnLocSP.setFont(new Font("Tahoma", Font.BOLD, 13));

        lblFromThongKeSP = new JLabel("Từ: ");
        lblFromThongKeSP.setBounds(36, 89, 35, 14);
        pnlLocSPBanChay.add(lblFromThongKeSP);
        lblFromThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

        dateChooserFromThongKeSP = new JDateChooser();
        dateChooserFromThongKeSP.setBounds(157, 82, 164, 33);
        pnlLocSPBanChay.add(dateChooserFromThongKeSP);

        dateChooserToThongKeSP = new JDateChooser();
        dateChooserToThongKeSP.setBounds(157, 140, 164, 33);
        pnlLocSPBanChay.add(dateChooserToThongKeSP);

        lblToThongKeSP = new JLabel("Đến: ");
        lblToThongKeSP.setBounds(37, 152, 46, 14);
        pnlLocSPBanChay.add(lblToThongKeSP);
        lblToThongKeSP.setFont(new Font("Tahoma", Font.BOLD, 13));

        lblBoLocSPBanChay = new JLabel("Sản phẩm bán chạy nhất");
        lblBoLocSPBanChay.setHorizontalAlignment(SwingConstants.CENTER);
        lblBoLocSPBanChay.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBoLocSPBanChay.setForeground(new Color(255, 0, 0));
        lblBoLocSPBanChay.setBackground(new Color(255, 0, 0));
        lblBoLocSPBanChay.setBounds(0, 33, 537, 26);
        pnlLocSPBanChay.add(lblBoLocSPBanChay);

        pnlThongKeNhanVien = new JPanel();
        pnlThongKeNhanVien.setBackground(new Color(0, 206, 209));
        tabThongKe.addTab("Thống kê nhân viên", null, pnlThongKeNhanVien, null);
        pnlThongKeNhanVien.setLayout(null);

        dateChooserToThongKeNV = new JDateChooser();
        dateChooserToThongKeNV.setBounds(138, 201, 164, 33);
        pnlThongKeNhanVien.add(dateChooserToThongKeNV);

        String header_NhanVien[] = {"STT", "Mã nhân viên", "Tên nhân viên", "Số tiền đã bán", "Số hóa đơn đã bán"};
        tableModel_NV = new DefaultTableModel(header_NhanVien, 0);

        tblNhanVien = new JTable(tableModel_NV);
        componentSp_TblNhanVien = new JScrollPane(tblNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        componentSp_TblNhanVien.setBounds(397, 92, 746, 394);
        pnlThongKeNhanVien.add(componentSp_TblNhanVien);

        btnLocNV = new JButton("Lọc");
        btnLocNV.setBounds(52, 294, 92, 35);
        pnlThongKeNhanVien.add(btnLocNV);
        btnLocNV.setFont(new Font("Tahoma", Font.BOLD, 13));

        dateChooserFromThongKeNV = new JDateChooser();
        dateChooserFromThongKeNV.setBounds(138, 115, 164, 33);
        pnlThongKeNhanVien.add(dateChooserFromThongKeNV);

        lblFromThongKeSP_1 = new JLabel("Từ: ");
        lblFromThongKeSP_1.setBounds(52, 127, 35, 14);
        pnlThongKeNhanVien.add(lblFromThongKeSP_1);
        lblFromThongKeSP_1.setFont(new Font("Tahoma", Font.BOLD, 13));

        lblToThongKeSP_1 = new JLabel("Đến: ");
        lblToThongKeSP_1.setBounds(52, 206, 46, 14);
        pnlThongKeNhanVien.add(lblToThongKeSP_1);
        lblToThongKeSP_1.setFont(new Font("Tahoma", Font.BOLD, 13));

        JLabel lblTop10NVBanNhieuNhat = new JLabel("TOP 10 NHÂN VIÊN BÁN ĐƯỢC NHIỀU NHẤT");
        lblTop10NVBanNhieuNhat.setForeground(new Color(255, 69, 0));
        lblTop10NVBanNhieuNhat.setHorizontalAlignment(SwingConstants.CENTER);
        lblTop10NVBanNhieuNhat.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblTop10NVBanNhieuNhat.setBounds(397, 44, 746, 37);
        pnlThongKeNhanVien.add(lblTop10NVBanNhieuNhat);
        btnLocSP.addActionListener(this);

        lblTieuDe = new JLabel("THỐNG KÊ");
        lblTieuDe.setForeground(new Color(0, 191, 255));
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTieuDe.setBounds(0, 11, 1490, 51);
        add(lblTieuDe);

        btnLocDT.addActionListener(this);
        btnLocKH.addActionListener(this);
        btnLocSP.addActionListener(this);
        btnLocNV.addActionListener(this);

        setChart();

        cmbTieuChiDoanhThu.addItemListener(new ItemListener() {

            @SneakyThrows
            @Override
            public void itemStateChanged(ItemEvent e) {
                // TODO Auto-generated method stub
                setBieuDoVe0();
                setChart();
            }
        });


        try {
            if (sanPhamService.getSoLuongSachLoi() > 0) {
                lblValueSoLuongSachLoi.setText(String.valueOf(sanPhamService.getSoLuongSachLoi()));
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (sanPhamService.getSoLuongSachTon() > 0) {
                lblValueSoLuongSach.setText(String.valueOf(sanPhamService.getSoLuongSachTon()));
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (sanPhamService.getSoLuongVPPTon() > 0) {
                lblValueSoLuongVPP.setText(String.valueOf(sanPhamService.getSoLuongVPPTon()));
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object obj = e.getSource();
        if (obj.equals(btnLocSP)) {

            if (getNgayFromJDateChooser(dateChooserFromThongKeSP) != null
                    && getNgayFromJDateChooser(dateChooserToThongKeSP) != null) {
                if (sanPhamService.getSanPhamBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromThongKeSP),
                        getNgayFromJDateChooser(dateChooserToThongKeSP)) != null) {
                    dsSanPham = sanPhamService.getSanPhamBanNhieuNhatTheoNgayTuChon(
                            getNgayFromJDateChooser(dateChooserFromThongKeSP),
                            getNgayFromJDateChooser(dateChooserToThongKeSP));
                    for (SanPham sp : dsSanPham) {
                        File file = new File("");

                        try {
                            lblmaSPTop1.setText(sanPhamService.timSanPhamTheoMa(sp.getMaSanPham()).getMaSanPham());
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        try {
                            lblloaiSpTop1.setText(sanPhamService.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham());
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        try {
                            lblGiaBanTop1.setText(String
                                    .valueOf(sanPhamService.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap()
                                            + sanPhamService.timSanPhamTheoMa(sp.getMaSanPham()).getGiaNhap() * 10 / 100)
                                    + "đ");
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        try {
                            lblSoLuongBanTop1.setText(String.valueOf(sanPhamService.getSoLuongBanCuaSanPhamChayNhat(
                                    getNgayFromJDateChooser(dateChooserFromThongKeSP),
                                    getNgayFromJDateChooser(dateChooserToThongKeSP))));
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        try {
                            if (sanPhamService.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham().equals("Sách")) {
                                // System.out.println("dc");
                                lblTenSPTop1.setText(sanPhamService.getSachTheoMaSP(sp.getMaSanPham()).getTenSach());
                            } else if (sanPhamService.timSanPhamTheoMa(sp.getMaSanPham()).getLoaiSanPham()
                                    .equals("Văn phòng phẩm")) {
                                // System.out.println("dc");
                                lblTenSPTop1.setText(sanPhamService.getVPPTheoMaSP(sp.getMaSanPham()).getTenVanPhongPham());
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                        System.out.println(sp.getHinhAnh());
                        String hinhAnh;
                        try {
                            hinhAnh = file.getAbsolutePath() + "\\hinhAnhHieuSach\\"
                                    + sanPhamService.getSachTheoMaSP(sp.getMaSanPham()).getHinhAnh();
                            lblHinhAnhTop1.setIcon(setSizeImageIconString(hinhAnh, lblHinhAnhTop1.getWidth(),
                                    lblHinhAnhTop1.getHeight()));
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }
                }
            }
        }
        if (obj.equals(btnLocDT)) {

            if (getNgayFromJDateChooser(dateChooserFromDoanhThu) != null
                    && getNgayFromJDateChooser(dateChooserToDoanhThu) != null) {
                if (nhanVienService.getNhanVienBanNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
                        getNgayFromJDateChooser(dateChooserToDoanhThu)) != null) {
                    dsNhanVien = nhanVienService.getNhanVienBanNhieuNhatTheoNgayTuChon(
                            getNgayFromJDateChooser(dateChooserFromDoanhThu),
                            getNgayFromJDateChooser(dateChooserToDoanhThu));
                    for (NhanVien nv : dsNhanVien) {
                        nhanVienService = new NhanVienServiceImpl();
                        try {
                            lblValueTop1NV.setText(nhanVienService.timNhanVienTheoMa(nv.getMaNhanVien()).getHoTenNhanVien());
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }
                }
                try {
                    if (hoaDonService.getDoanhThu(getNgayFromJDateChooser(dateChooserFromDoanhThu),
                            getNgayFromJDateChooser(dateChooserToDoanhThu)) > 0) {
                        lblGiaTriDoanhThu.setText(
                                String.valueOf(hoaDonService.getDoanhThu(getNgayFromJDateChooser(dateChooserFromDoanhThu),
                                        getNgayFromJDateChooser(dateChooserToDoanhThu))) + "đ");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    if (hoaDonService.getSoLuongHoaDon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
                            getNgayFromJDateChooser(dateChooserToDoanhThu)) > 0) {
                        lblGiaTriTongHoaDon.setText(String
                                .valueOf(hoaDonService.getSoLuongHoaDon(getNgayFromJDateChooser(dateChooserFromDoanhThu),
                                        getNgayFromJDateChooser(dateChooserToDoanhThu))));
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        if (obj.equals(btnLocKH)) {
            if (getNgayFromJDateChooser(dateChooserFromKH) != null
                    && getNgayFromJDateChooser(dateChooserToKH) != null) {
                khachHangService = new KhachHangServiceImpl();
                if (khachHangService.getKhachHangMuaNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromKH),
                        getNgayFromJDateChooser(dateChooserToKH)) != null) {
                    dsKhachHang = khachHangService.getKhachHangMuaNhieuNhatTheoNgayTuChon(getNgayFromJDateChooser(dateChooserFromKH),
                            getNgayFromJDateChooser(dateChooserToKH));
                    for (KhachHang kh : dsKhachHang) {
                        khachHangService = new KhachHangServiceImpl();
                        try {
                            lblTenKHValue
                                    .setText(khachHangService.timKhachHangTheoMa(kh.getMaKhachHang()).getHoTenKhachHang());
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        try {
                            lblSoTienDaMuaValue.setText(String.valueOf(
                                    khachHangService.getTongTienCuaKhachHangTop1(getNgayFromJDateChooser(dateChooserFromKH),
                                            getNgayFromJDateChooser(dateChooserToKH)))
                                    + "đ");
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    }
                }
                xoaHetDuLieuTableTop10KH();
                try {
                    docDuLieuTuArrayListTop10VaoModel();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        if (obj.equals(btnLocNV)) {
            xoaHetDuLieuTableTop10NV();
            if (getNgayFromJDateChooser(dateChooserFromThongKeNV) != null
                    && getNgayFromJDateChooser(dateChooserToThongKeNV) != null) {
//                nhanVienService = new NhanVienServiceImpl();
//                hoaDonSerrviceImpl = new HoaDonServiceImpl();
                docDuLieuTuArrayListTop10NVVaoModel();
            }
        }

    }

    public LocalDate getNgayFromJDateChooser(JDateChooser ngay) {
        if (ngay.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày");
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayKT = sdf.format(ngay.getDate());
        String dateKT[] = dayKT.split("-");
        int namKT = Integer.parseInt(dateKT[0]);
        int thangKT = Integer.parseInt(dateKT[1]);
        int ngayKT = Integer.parseInt(dateKT[2]);
        LocalDate lcDateKT = LocalDate.of(namKT, thangKT, ngayKT);
        return lcDateKT;
    }

    public void xoaHetDuLieuTableTop10KH() {
        DefaultTableModel dtm = (DefaultTableModel) tblTop10KH.getModel();
        dtm.getDataVector().removeAllElements();
    }

    public void docDuLieuTuArrayListTop10VaoModel() throws Exception {

        dsKhachHang = khachHangService.getTop10KHThanThiet(getNgayFromJDateChooser(dateChooserFromKH),
                getNgayFromJDateChooser(dateChooserToKH));
        int i = 1;
        for (KhachHang kh : dsKhachHang) {
            // System.out.println(kh.getHoTenKhachHang());
            modelTop10KH.addRow(new Object[]{i++, kh.getMaKhachHang(),
                    khachHangService.timKhachHangTheoMa(kh.getMaKhachHang()).getHoTenKhachHang(),
                    khachHangService.getTongTienCuaKhachHangTheoMa(getNgayFromJDateChooser(dateChooserFromKH),
                            getNgayFromJDateChooser(dateChooserToKH), kh.getMaKhachHang()),
                    khachHangService.getSoLuongHoaDonCuaKhachHangTheoMa(getNgayFromJDateChooser(dateChooserFromKH),
                            getNgayFromJDateChooser(dateChooserToKH), kh.getMaKhachHang())});
        }
    }

    // Thong ke top 10 nv
    public void docDuLieuTuArrayListTop10NVVaoModel() throws Exception {

        dsNhanVien = nhanVienService.thongKeDoanhThu10NVBanNhieuNhat(getNgayFromJDateChooser(dateChooserFromThongKeNV),
                getNgayFromJDateChooser(dateChooserToThongKeNV));
        int i = 1;
        for (NhanVien nv : dsNhanVien) {
            try {
                tableModel_NV.addRow(new Object[]{i++, nv.getMaNhanVien(),
                        nhanVienService.timNhanVienTheoMa(nv.getMaNhanVien()).getHoTenNhanVien(),
                        hoaDonService.getDoanhThuTheoMaNhanVien(getNgayFromJDateChooser(dateChooserFromThongKeNV),
                                getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien()),
                        hoaDonService.getSoLuongHoaDonTheoMaNV(getNgayFromJDateChooser(dateChooserFromThongKeNV),
                                getNgayFromJDateChooser(dateChooserToThongKeNV), nv.getMaNhanVien())});
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void xoaHetDuLieuTableTop10NV() {
        DefaultTableModel dtm = (DefaultTableModel) tblNhanVien.getModel();
        dtm.getDataVector().removeAllElements();
    }

    public LocalDate getNgayHienTai() {
        LocalDate lt = LocalDate.now();
        return lt;
    }

    public void setChart() throws Exception {
        int count = 0;
        LocalDate nowMinus = null;
        LocalDate now = getNgayHienTai();
        if (cmbTieuChiDoanhThu.getSelectedIndex() == 0) {
            count = 3;
            while (count > 0) {
                nowMinus = now.minusMonths(1);
                try {
                    dataset.setValue(hoaDonService.getDoanhThu(nowMinus, now), "Doanh thu",
                            String.valueOf(nowMinus.getMonthValue()));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                now = nowMinus;
                count--;
            }
        } else if (cmbTieuChiDoanhThu.getSelectedIndex() == 1) {
            count = 6;
            while (count > 0) {
                nowMinus = now.minusMonths(1);
                try {
                    dataset.setValue(hoaDonService.getDoanhThu(nowMinus, now), "Doanh thu",
                            String.valueOf(nowMinus.getMonthValue()));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                now = nowMinus;
                count--;
            }
        } else {
            count = 9;
            while (count > 0) {
                nowMinus = now.minusMonths(1);
                try {
                    dataset.setValue(hoaDonService.getDoanhThu(nowMinus, now), "Doanh thu",
                            String.valueOf(nowMinus.getMonthValue()));
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                now = nowMinus;
                count--;
            }
        }

    }

    public void setBieuDoVe0() {
        dataset.setValue(0, "Doanh thu", "1");
        dataset.setValue(0, "Doanh thu", "2");
        dataset.setValue(0, "Doanh thu", "3");
        dataset.setValue(0, "Doanh thu", "4");
        dataset.setValue(0, "Doanh thu", "5");
        dataset.setValue(0, "Doanh thu", "6");
        dataset.setValue(0, "Doanh thu", "7");
        dataset.setValue(0, "Doanh thu", "8");
        dataset.setValue(0, "Doanh thu", "9");
        dataset.setValue(0, "Doanh thu", "10");
        dataset.setValue(0, "Doanh thu", "11");
        dataset.setValue(0, "Doanh thu", "12");
    }

    public ImageIcon setSizeImageIconString(String s, int width, int height) {
        ImageIcon image = new ImageIcon(s);
        Image imageSet = image.getImage();
        imageSet = imageSet.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageSet);
        return image;
    }
}
