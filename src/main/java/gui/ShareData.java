package gui;

import java.util.ArrayList;
import java.util.HashMap;

import entity.SanPham;

public class ShareData {
	private static HashMap<String, ArrayList<SanPham>> dsHoaDonCho;
	private static ArrayList<SanPham> dsSanPhamThanhToanTiep;
	private static boolean thanhToan ;
	private static boolean xoa ;
	private static String sdtThanhToan;

	public ShareData(HashMap<String, ArrayList<SanPham>> dsHoaDonCho) {
		ShareData.dsHoaDonCho = dsHoaDonCho;
		thanhToan = false;
		xoa = false;
	}
	public static HashMap<String, ArrayList<SanPham>> getDsHoaDonCho() {
		return dsHoaDonCho;
	}
	public static void setDsHoaDonCho(HashMap<String, ArrayList<SanPham>> dsHoaDonCho) {
		ShareData.dsHoaDonCho = dsHoaDonCho;
	}
	public static boolean isThanhToan() {
		return thanhToan;
	}
	public static void setThanhToan(boolean thanhToan) {
		ShareData.thanhToan = thanhToan;
	}
	public static boolean isXoa() {
		return xoa;
	}
	public static void setXoa(boolean xoa) {
		ShareData.xoa = xoa;
	}
	public static String getSdtThanhToan() {
		return sdtThanhToan;
	}
	public static void setSdtThanhToan(String sdtThanhToan) {
		ShareData.sdtThanhToan = sdtThanhToan;
	}
	public static ArrayList<SanPham> getDsSanPhamThanhToanTiep() {
		return dsSanPhamThanhToanTiep;
	}
	public static void setDsSanPhamThanhToanTiep(ArrayList<SanPham> dsSanPhamThanhToanTiep) {
		ShareData.dsSanPhamThanhToanTiep = dsSanPhamThanhToanTiep;
	}
	
}
