/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import CheckData.CheckData;
import CheckData.MaHoaPass;
import Manage.BanHang;
import Manage.QuanLyHoaDon;
import Manage.QuanLyKhachHang;
import Manage.QuanLyNhanVien;
import Manage.QuanLySanPham;
import Manage.ResetPass;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sang Kaito
 */
public class Menu {

    public void MenuMain() throws ParseException {
        Scanner sc = new Scanner(System.in);
        int chon = 0;
        do {
            System.out.println("");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 1 |                   ĐĂNG NHẬP HỆ THỐNG                   |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 2 |                   DỪNG CHƯƠNG TRÌNH                    |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("");
            System.out.print("==> Mời bạn nhập lựa chọn : ");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    MenuLogin();
                    break;
                case 2:
                    
                    
                    System.err.println("Design by Sang Kaito");
                    System.err.println("sanggkaitoo@gmail.com");
                    
                    
                    break;
            }
            if (chon != 1 && chon != 2) {
                try {
                    System.err.println("Bạn chỉ được chọn 1 hoặc 2 !");
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (chon != 1 && chon != 2);

    }

    public void MenuLogin() throws ParseException {
        MaHoaPass mh = new MaHoaPass();
        CheckData check = new CheckData();
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.readFile();
        String ID, pass = null;
        do {
            System.out.println("");
            System.out.println("<---------------ĐĂNG NHẬP--------------->");
            System.out.print(" TÀI KHOẢN : ");
            Scanner sc = new Scanner(System.in);
            ID = sc.nextLine();
            if (check.CheckExistNV(ID)) {
                Scanner sc1 = new Scanner(System.in);
                System.out.print(" MẬT KHẨU : ");
                pass = sc1.nextLine();
                if (check.CheckPass(ID, pass)) {
                    if (ID.toUpperCase().contains("AD")) {
                        System.out.println("Tài khoản Admin : " + ID.toUpperCase());
                        MenuADM();
                    } else if (ID.toUpperCase().contains("NV")) {
                        System.out.println("Tài khoản Nhân viên : " + ID.toUpperCase());
                        MenuNV(ID);
                    }
                } else {
                    try {
                        System.err.println("Bạn nhập mật khẩu không đúng !");
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Bạn có muốn đặt lại mật khẩu ?");
                    System.out.println("1. Có");
                    System.out.println("2. Không");
                    System.out.print("==> Lựa chọn của bạn : ");
                    int chon = sc.nextInt();
                    if (chon == 1) {
                        ResetPass rs = new ResetPass();
                        rs.Reset();
                    }
                }
            } else {
                try {
                    System.err.println("Bạn nhập ID không tồn tại !");
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        } while (check.CheckExistNV(ID) == false || check.CheckPass(ID, pass) == false);

    }

    public void MenuADM() throws ParseException {
        Scanner sc = new Scanner(System.in);
        int nhap = 0;
        do {
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("|                 HỆ THỐNG QUẢN LÝ BÁN HÀNG                 |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 1 |                 QUẢN LÝ NHÂN VIÊN                     |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 2 |                 QUẢN LÝ KHÁCH HÀNG                    |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 3 |                 QUẢN LÝ SẢN PHẨM                      |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 4 |                 BÁO CÁO THỐNG KÊ                      |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 5 |                 ĐĂNG XUẤT                             |");
            System.out.println("+---+-------------------------------------------------------+");
            do {
                System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                nhap = sc.nextInt();
                if (nhap < 0 || nhap > 5) {
                    try {
                        System.err.println("Bạn chỉ được nhập số trong chỉ mục!");
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } while (nhap < 0 || nhap > 5);

            switch (nhap) {
                case 1:
                    MenuQLNV();
                    break;
                case 2:
                    MenuQLKH();
                    break;
                case 3:
                    MenuQLSP();
                    break;
                case 4:
                    MenuQLHD();
                    break;
                case 5:
                    MenuMain();
                    break;
            }
        } while (nhap == 1 || nhap == 2 || nhap == 3 || nhap == 4);
    }

    public void MenuNV(String ID) throws ParseException {
        QuanLyNhanVien ql = new QuanLyNhanVien();
        Scanner sc = new Scanner(System.in);
        int nhap = 0;
        do {
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("|                    TÀI KHOẢN NHÂN VIÊN                    |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 1 |                 BÁN HÀNG                              |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 2 |                 QUẢN LÝ KHÁCH HÀNG                    |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 3 |                 QUẢN LÝ SẢN PHẨM                      |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 4 |                 XEM THÔNG TIN CÁ NHÂN                 |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 5 |                 SỬA THÔNG TIN CÁ NHÂN                 |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 6 |                 ĐĂNG XUẤT                             |");
            System.out.println("+---+-------------------------------------------------------+");
            do {
                System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                nhap = sc.nextInt();
                if (nhap < 0 || nhap > 6) {
                    try {
                        System.err.println("Bạn phải chỉ được nhập số từ 1 đến 6 !");
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } while (nhap < 0 || nhap > 6);

            switch (nhap) {
                case 1:
                    BanHang banhang = new BanHang();
                    banhang.BH(ID);
                    break;
                case 2:
                    MenuQLKH();
                    break;
                case 3:
                    MenuQLSP();
                    break;
                case 4:
                    ql.thongTinNV(ID);
                    break;
                case 5:
                    ql.repairNV(ID);
                    break;
                case 6:
                    MenuMain();
            }
        } while (nhap == 1 || nhap == 2 || nhap == 3 || nhap == 4 || nhap == 5);
    }

    public void MenuQLNV() {
        int nhap = 0;
        QuanLyNhanVien ql = new QuanLyNhanVien();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("|                     QUẢN LÝ NHÂN VIÊN                     |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 1 |              XEM DANH SÁCH NHÂN VIÊN                  |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 2 |              TÌM KIẾM NHÂN VIÊN(ID/SĐT/EMAIL)         |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 3 |              THÊM MỚI NHÂN VIÊN                       |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 4 |              SỬA THÔNG TIN NHÂN VIÊN                  |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 5 |              XÓA THÔNG TIN NHÂN VIÊN                  |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 6 |              QUAY LẠI MENU QUẢN LÝ                    |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("");
            do {
                System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                nhap = sc.nextInt();
            } while (Check(nhap, 6));
            ql.readFile();
            switch (nhap) {
                case 1:
                    ql.danhSach();
                    break;
                case 2:
                    ql.find();
                    break;
                case 3:
                    ql.add();
                    break;
                case 4:
                    ql.repairAD();
                    break;
                case 5:
                    ql.remove();
                    break;
            }
        } while (nhap == 1 || nhap == 2 || nhap == 3 || nhap == 4 || nhap == 5);

    }

    public void MenuQLKH() {
        int nhap = 0;
        QuanLyKhachHang ql = new QuanLyKhachHang();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("+-----------------------------------------------------------+");
            System.out.println("|                     QUẢN LÝ KHÁCH HÀNG                    |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 1 |              XEM DANH SÁCH KHÁCH HÀNG                 |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 2 |              TÌM KIẾM KHÁCH HÀNG(ID/SĐT/EMAIL)        |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 3 |              THÊM MỚI KHÁCH HÀNG                      |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 4 |              SỬA THÔNG TIN KHÁCH HÀNG                 |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 5 |              XÓA THÔNG TIN KHÁCH HÀNG                 |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("| 6 |              QUAY LẠI MENU QUẢN LÝ                    |");
            System.out.println("+---+-------------------------------------------------------+");
            System.out.println("");
            do {
                System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                nhap = sc.nextInt();
            } while (Check(nhap, 6));
            ql.readFile();
            switch (nhap) {
                case 1:
                    ql.danhSach();
                    break;
                case 2:
                    ql.find();
                    break;
                case 3:
                    ql.add();
                    break;
                case 4:
                    ql.repair();
                    break;
                case 5:
                    ql.remove();
                    break;
            }
        } while (nhap == 1 || nhap == 2 || nhap == 3 || nhap == 4 || nhap == 5);

    }

    public void MenuQLSP() {
        int nhap = 0;
        QuanLySanPham ql = new QuanLySanPham();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("|                      QUẢN LÝ SẢN PHẨM                      |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 1 |              XEM DANH SÁCH SẢN PHẨM                    |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 2 |              TÌM KIẾM SẢN PHẨM(ID/TÊN/SỐ LƯỢNG/ĐƠN GIÁ)|");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 3 |              THÊM MỚI SẢN PHẨM                         |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 4 |              SỬA THÔNG TIN SẢN PHẨM                    |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 5 |              XÓA THÔNG TIN SẢN PHẨM                    |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 6 |              QUAY LẠI MENU QUẢN LÝ                     |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("");
            do {
                System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                nhap = sc.nextInt();
            } while (Check(nhap, 6));
            ql.readFile();
            switch (nhap) {
                case 1:
                    ql.danhSach();
                    break;
                case 2:
                    ql.find();
                    break;
                case 3:
                    ql.add();
                    break;
                case 4:
                    ql.repair();
                    break;
                case 5:
                    ql.remove();
                    break;
            }
        } while (nhap == 1 || nhap == 2 || nhap == 3 || nhap == 4 || nhap == 5);

    }

    public void MenuQLHD() {
        int nhap = 0;
        QuanLyHoaDon qlhd = new QuanLyHoaDon();
        QuanLySanPham qlsp = new QuanLySanPham();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("|                      BÁO CÁO THỐNG KÊ                      |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 1 |              XEM HÓA ĐƠN THEO NGÀY                     |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 2 |              XEM HÓA ĐƠN THEO THÁNG                    |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 3 |              TÌM KIẾM HÓA ĐƠN THEO SẢN PHẨM            |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 4 |              DOANH THU THEO NHÂN VIÊN                  |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 5 |              DANH SÁCH SẢN PHẨM SẮP HẾT                |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 6 |              DANH SÁCH SẢN PHẨM TỒN KHO                |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("| 7 |              QUAY LẠI MENU QUẢN LÝ                     |");
            System.out.println("+---+--------------------------------------------------------+");
            System.out.println("");
            do {
                System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                nhap = sc.nextInt();
            } while (Check(nhap, 7));
            switch (nhap) {
                case 1:
                    qlhd.hoaDonTheoNgay();
                    break;
                case 2:
                    qlhd.hoaDonTheoThang();
                    break;
                case 3:
                    qlhd.hoaDonTheoSanPham();
                    break;
                case 4:
                    qlhd.doanhThuNV();
                    break;
                case 5:
                    qlsp.readFile();
                    qlsp.hetHang();
                    break;
                case 6:
                    qlsp.readFile();
                    qlsp.tonKho();
                    break;
                case 7:
                    break;
            }
        } while (nhap == 1 || nhap == 2 || nhap == 3 || nhap == 4 || nhap == 5 || nhap == 6);

    }

    public boolean Check(int nhap, int last) {
        Scanner sc = new Scanner(System.in);
        try {
            if (nhap < 1 || nhap > last) {
                System.err.println("Bạn phải chỉ được số trong các chỉ mục!");
                Thread.sleep(200);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Fail " + e.getMessage());
            return true;
        }
        return false;
    }

}
