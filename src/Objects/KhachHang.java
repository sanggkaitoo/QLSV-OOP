package Objects;

import CheckData.CheckData;
import java.util.Scanner;

public class KhachHang {

    private String ID;
    private String hoTen;
    private String sdt;
    private String email;
    private String address;

    public KhachHang() {
    }

    public KhachHang(String ID, String hoTen, String sdt, String email, String address) {
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

    public void nhapTT() {
        CheckData check = new CheckData();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập ID khách hàng : ");
            setID(sc.nextLine());
            if (check.CheckExistKH(getID())) {
                System.err.println("Đã tồn tại mã khách hàng này !");
            }
        } while (check.CheckExistKH(getID()));

        do {
            System.out.print("Nhập họ và tên khách hàng : ");
            setHoTen(sc.nextLine());
        } while (check.CheckHoTen(getHoTen()));
        
        do {
            System.out.print("Nhập số điện thoại khách hàng : ");
            setSdt(sc.nextLine());
        } while (check.CheckSDT(sdt) || check.CheckExistSDTKH(sdt));

        do {
            System.out.print("Nhập email khách hàng : ");
            setEmail(sc.nextLine());
        } while (check.CheckEmail(getEmail()) || check.CheckExistMailKH(getEmail()));

        System.out.print("Nhập địa chỉ khách hàng : ");
        setAddress(sc.nextLine());

    }

    public void inTT() {
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.println("|                                    THÔNG TIN KHÁCH HÀNG                                     |");
        System.out.println("+--------+--------------------------+---------------+---------------------+-------------------+");
        System.out.println("|   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.printf("|%8s|%26s|%15s|%21s|%19s|", getID(), getHoTen(), getSdt(), getEmail(), getAddress());
        System.out.println("");
        System.out.println("+---------------------------------------------------------------------------------------------+");
        System.out.println("");
    }

}
