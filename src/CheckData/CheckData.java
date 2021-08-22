/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckData;

import Manage.QuanLyKhachHang;
import Manage.QuanLyNhanVien;
import Manage.QuanLySanPham;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sang Kaito
 */
public class CheckData {

    public boolean CheckHoTen(String hoTen) {
        try {
            for (int i = 0; i < 10; i++) {
                if (hoTen.contains(i + "")) {
                    System.err.println("Họ và tên không được chứa số");
                    Thread.sleep(200);
                    return true;
                }
            }
            if (hoTen.isEmpty()) {
                System.err.println("Họ tên không được để khoảng trắng !");
                Thread.sleep(200);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Fail " + e.getMessage());
            return false;
        }

        return false;
    }

    public boolean CheckSDT(String sdt) {
        String dinhDangSDT = "^[0]{1}[0-9]{9,10}$";
        boolean ktSDT = sdt.matches(dinhDangSDT);
        if (ktSDT == false) {
            System.err.println("Số điện thoại sai, nhập lại theo dạng 0123456789 ( 10 hoặc 11 số )");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    public boolean CheckExistSDTNV(String sdt) {
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (sdt.equalsIgnoreCase(ql.list.get(i).getSdt())) {
                System.err.println("Số điện thoại đã tồn tại !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean CheckExistMailNV(String mail){
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (mail.equalsIgnoreCase(ql.list.get(i).getEmail())) {
                System.err.println("Mail đã tồn tại !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean CheckExistSDTKH(String sdt){
        QuanLyKhachHang ql = new QuanLyKhachHang();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (sdt.equalsIgnoreCase(ql.list.get(i).getSdt())) {
                System.err.println("Số điện thoại đã tồn tại !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean CheckExistMailKH(String mail){
        QuanLyKhachHang ql = new QuanLyKhachHang();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (mail.equalsIgnoreCase(ql.list.get(i).getEmail())) {
                System.err.println("Mail đã tồn tại !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }
        }
        return false;
    }

    public boolean CheckEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        if (ktEmail == false) {
            System.err.println("Email sai, nhập lại theo dạng abc@domain.com");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    public boolean CheckExistNV(String ID) {
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (ID.equalsIgnoreCase(ql.list.get(i).getID())) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckPass(String ID, String Pass) {
        MaHoaPass mh = new MaHoaPass();
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (ID.equalsIgnoreCase(ql.list.get(i).getID())) {
                if (mh.md5(Pass).equals(ql.list.get(i).getPass())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean CheckExistMail(String ID, String mail) {
        MaHoaPass mh = new MaHoaPass();
        QuanLyNhanVien ql = new QuanLyNhanVien();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (ID.equalsIgnoreCase(ql.list.get(i).getID())) {
                if (mail.equalsIgnoreCase(ql.list.get(i).getEmail())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean CheckExistKH(String ID) {
        QuanLyKhachHang ql = new QuanLyKhachHang();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (ID.equalsIgnoreCase(ql.list.get(i).getID())) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckExistSP(String ID) {
        QuanLySanPham ql = new QuanLySanPham();
        ql.readFile();
        for (int i = 0; i < ql.list.size(); i++) {
            if (ID.equalsIgnoreCase(ql.list.get(i).getID())) {
                return true;
            }
        }
        return false;
    }

    public boolean CheckInt(int soNguyen) {
        if (soNguyen < 0) {
            System.err.println("Bạn phải nhập số lớn hơn 0 !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

}
