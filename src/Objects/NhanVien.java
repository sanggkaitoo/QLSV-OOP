/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import CheckData.CheckData;
import CheckData.MaHoaPass;
import java.util.Scanner;

/**
 *
 * @author Sang Kaito
 */
public class NhanVien {

    private String ID;
    private String hoTen;
    private String sdt;
    private String email;
    private String address;
    private String pass;

    public NhanVien() {
    }

    public NhanVien(String ID, String hoTen, String sdt, String email, String address) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void nhapTT() {
        CheckData check = new CheckData();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập ID nhân viên : ");
            setID(sc.nextLine());
            if (check.CheckExistNV(getID())) {
                System.err.println("Đã tồn tại mã nhân viên này !");
            }
        } while (check.CheckExistNV(getID()));

        do {
            System.out.print("Nhập họ và tên nhân viên : ");
            setHoTen(sc.nextLine());
        } while (check.CheckHoTen(getHoTen()));

        do {
            System.out.print("Nhập số điện thoại nhân viên : ");
            setSdt(sc.nextLine());
        } while (check.CheckSDT(sdt) || check.CheckExistSDTNV(sdt));

        do {
            System.out.print("Nhập email nhân viên : ");
            setEmail(sc.nextLine());
        } while (check.CheckEmail(getEmail()) || check.CheckExistMailNV(getEmail()));

        System.out.print("Nhập địa chỉ nhân viên : ");
        setAddress(sc.nextLine());
        
        String[] pass = getEmail().split("@");
        MaHoaPass mh = new MaHoaPass();
        setPass(mh.md5(pass[0]));

    }

    public void inTT() {
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.println("|                                     THÔNG TIN NHÂN VIÊN                                     |");
        System.out.println("+--------+--------------------------+---------------+---------------------+-------------------+");
        System.out.println("|   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.printf("|%8s|%26s|%15s|%21s|%19s|", getID(), getHoTen(), getSdt(), getEmail(), getAddress());
        System.out.println("");
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.println("");
    }

}
