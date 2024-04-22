/**
 * @ (#) Server.java 1.0 21-Apr-24
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved.
 */
package server;

import service.*;
import service.impl.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;


/**
 * @description:
 * @author: Le Hoang Nam
 * @date: 21-Apr-24
 * @version: 1.0
 */
public class Server {
    private static final String URL = "rmi://172.20.35.98:8080/";
    public static void main(String[] args) throws Exception, NamingException {

        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put("java.security.policy", "rmi/policy.policy");
        System.out.println("Server started on port 8080.");
        Context ctx = new InitialContext(env);
        LocateRegistry.createRegistry(8080);
        //
        ChatLieuService chatLieuService = new ChatLieuServiceImpl();
        ChiTietHoaDonDoiTraService chiTietHoaDonDoiTraService = new ChiTietHoaDonDoiTraServiceImpl();
        ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl();
        HoaDonDoiTraService hoaDonDoiTraService = new HoaDonDoiTraServiceImpl();
        HoaDonService hoaDonService = new HoaDonServiceImpl();
        KhachHangService khachHangService = new KhachHangServiceImpl();
        MauSacService mauSacService = new MauSacServiceImpl();
        NhaCungCapService nhaCungCapService = new NhaCungCapServiceImpl();
        NhanVienService nhanVienService = new NhanVienServiceImpl();
        NhaXuatBanService nhaXuatBanService = new NhaXuatBanServiceImpl();
        SachLoiService sachLoiService = new SachLoiServiceImpl();
        SanPhamService sanPhamService = new SanPhamServiceImpl();
        TacGiaService tacGiaService = new TacGiaServiceImpl();
        TaiKhoanService taiKhoanService = new TaiKhoanServiceImpl();
        TheLoaiService theLoaiService = new TheLoaiServiceImpl();
        XuatXuService xuatXuService = new XuatXuServiceImpl();
        // ThieuThongKe
        ctx.bind(URL + "chatLieu", chatLieuService);
        ctx.bind(URL + "chiTietHoaDonDoiTra", chiTietHoaDonDoiTraService);
        ctx.bind(URL + "chiTietHoaDon", chiTietHoaDonService);
        ctx.bind(URL + "hoaDonDoiTra", hoaDonDoiTraService);
        ctx.bind(URL + "hoaDon", hoaDonService);
        ctx.bind(URL + "khachHang", khachHangService);
        ctx.bind(URL + "mauSac", mauSacService);
        ctx.bind(URL + "nhaCungCap", nhaCungCapService);
        ctx.bind(URL + "nhanVien", nhanVienService);
        ctx.bind(URL + "nhaXuatBan", nhaXuatBanService);
        ctx.bind(URL + "sachLoi", sachLoiService);
        ctx.bind(URL + "sanPham", sanPhamService);
        ctx.bind(URL + "tacGia", tacGiaService);
        ctx.bind(URL + "taiKhoan", taiKhoanService);
        ctx.bind(URL + "theLoai", theLoaiService);
        ctx.bind(URL + "xuatXu", xuatXuService);

        System.out.println("Connected to server");
    }
}
