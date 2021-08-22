package Objects;

import CheckData.CheckData;
import CheckData.TienTe;
import java.util.Scanner;

public class SanPham {

    TienTe tien = new TienTe();
    private String ID;
    private String tenSP;
    private String hangSP;
    private int soLuong;
    private int donGia;
    private String donViTinh;

    public SanPham() {
    }

    public SanPham(String ID, String tenSP, String hangSP, int soLuong, int donGia, String donViTinh) {
        this.ID = ID;
        this.tenSP = tenSP;
        this.hangSP = hangSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHangSP() {
        return hangSP;
    }

    public void setHangSP(String hangSP) {
        this.hangSP = hangSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public void nhapTT() {
        CheckData check = new CheckData();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập ID sản phẩm : ");
            setID(sc.nextLine());
            if (check.CheckExistSP(ID)) {
                System.err.println("Đã tồn tại mã sản phẩm này !");
            }
        } while (check.CheckExistSP(getID()));

        System.out.print("Nhập tên sản phẩm : ");
        setTenSP(sc.nextLine());

        System.out.print("Nhập hãng sản phẩm : ");
        setHangSP(sc.nextLine());

        System.out.print("Nhập đơn vị tính : ");
        setDonViTinh(sc.nextLine());
        do {
            System.out.print("Nhập số lượng sản phẩm : ");
            setSoLuong(sc.nextInt());
        } while (check.CheckInt(getSoLuong()));

        do {
            System.out.print("Nhập giá sản phẩm : ");
            setDonGia(sc.nextInt());
        } while (check.CheckInt(getDonGia()));

    }

    public void inTT() {
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
        System.out.println("|                                             THÔNG TIN SẢN PHẨM                                              |");
        System.out.println("+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
        System.out.println("|   ID   |         Tên sản phẩm        |  Hãng sản xuất  |  Số lượng  |        Đơn giá        |  Đơn vị tính  |");
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
        System.out.printf("|%8s|%29s|%17s|%12s|%23s|%15s|", getID(), getTenSP(), getHangSP(), getSoLuong(), tien.tienTe(getDonGia()), getDonViTinh());
        System.out.println("");
        System.out.println("+-------------------------------------------------------------------------------------------------------------+");
        System.out.println("");
    }
}
