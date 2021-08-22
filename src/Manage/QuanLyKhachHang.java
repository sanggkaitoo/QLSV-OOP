package Manage;

import CheckData.CheckData;
import Objects.KhachHang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.imageio.ImageIO.read;
import org.omg.CORBA.NVList;

public class QuanLyKhachHang {

    public ArrayList<KhachHang> list = new ArrayList<>();
    private String filename = "KhachHang.txt";

    public QuanLyKhachHang() {
    }

    public void writeFile() {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename);
            BufferedWriter bw = new BufferedWriter(wr);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).getID() + "\t" + list.get(i).getHoTen() + "\t" + list.get(i).getSdt() + "\t" + list.get(i).getEmail() + "\t" + list.get(i).getAddress());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
        list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                KhachHang kh = new KhachHang();
                String[] thongTin = s.split("\t");
                kh.setID(thongTin[0]);
                kh.setHoTen(thongTin[1]);
                kh.setSdt(thongTin[2]);
                kh.setEmail(thongTin[3]);
                kh.setAddress(thongTin[4]);
                s = br.readLine();
                list.add(kh);
            }
            br.close();

        } catch (IOException ex) {
            System.err.println("Fail : " + ex.getMessage());
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex1) {
                Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void danhSach() {
        if (list.size() == 0) {
            System.out.println("KHÔNG CÓ KHÁCH HÀNG NÀO !");
        } else {
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|                                        DANH SÁCH KHÁCH HÀNG                                       |");
            System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
            System.out.println("| STT |   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("|%5d|%8s|%26s|%15s|%21s|%19s|", (i + 1), list.get(i).getID(), list.get(i).getHoTen(), list.get(i).getSdt(), list.get(i).getEmail(), list.get(i).getAddress());
                System.out.println("");
            }
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
    }

    public void find() {
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("|                     TÌM KIẾM KHÁCH HÀNG                    |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("| 1 |                     THEO ID                            |");
            System.out.println("| 2 |                     THEO SĐT                           |");
            System.out.println("| 3 |                     THEO EMAIL                         |");
            System.out.println("| 4 |                     THEO TỪ KHÓA                       |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("");
            System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
            chon = sc.nextInt();
            if (chon < 1 || chon > 4) {
                System.err.println("Bạn chỉ có thể nhập từ 1 đến 4 !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (chon < 1 || chon > 4);
        int dem = 0;
        switch (chon) {
            case 1:
                Scanner sc1 = new Scanner(System.in);
                System.out.print("Nhập ID : ");
                String ID = sc1.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (ID.equalsIgnoreCase(list.get(i).getID())) {
                        dem++;
                    }
                }
                System.out.println("Tìm thấy " + dem + " kết quả !");
                do {
                    System.out.println("Bạn có muốn in ra kết quả :");
                    System.out.println("1. CÓ");
                    System.out.println("2. KHÔNG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH SÁCH KHÁCH HÀNG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (ID.equalsIgnoreCase(list.get(i).getID())) {
                                System.out.printf("|%5d|%8s|%26s|%15s|%21s|%19s|", (i + 1), list.get(i).getID(), list.get(i).getHoTen(), list.get(i).getSdt(), list.get(i).getEmail(), list.get(i).getAddress());
                                System.out.println("");
                            }
                        }
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Chỉ có thể nhập 1 hoặc 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 2:
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Nhập số điện thoại hoặc các số liền nhau nằm trong 1 số điện thoại : ");
                String sdt = sc2.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSdt().contains(sdt)) {
                        dem++;
                    }
                }
                System.out.println("Tìm thấy " + dem + " kết quả !");
                do {
                    System.out.println("Bạn có muốn in ra kết quả :");
                    System.out.println("1. CÓ");
                    System.out.println("2. KHÔNG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH SÁCH KHÁCH HÀNG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getSdt().contains(sdt)) {
                                System.out.printf("|%5d|%8s|%26s|%15s|%21s|%19s|", (i + 1), list.get(i).getID(), list.get(i).getHoTen(), list.get(i).getSdt(), list.get(i).getEmail(), list.get(i).getAddress());
                                System.out.println("");
                            }
                        }
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Chỉ có thể nhập 1 hoặc 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 3:
                System.out.print("Nhập email hoặc từ khóa có trong email : ");
                String email = sc.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSdt().contains(email)) {
                        dem++;
                    }
                }
                System.out.println("Tìm thấy " + dem + " kết quả !");
                do {
                    System.out.println("Bạn có muốn in ra kết quả :");
                    System.out.println("1. CÓ");
                    System.out.println("2. KHÔNG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH SÁCH KHÁCH HÀNG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getSdt().contains(email)) {
                                System.out.printf("|%5d|%8s|%26s|%15s|%21s|%19s|", (i + 1), list.get(i).getID(), list.get(i).getHoTen(), list.get(i).getSdt(), list.get(i).getEmail(), list.get(i).getAddress());
                                System.out.println("");
                            }
                        }
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Chỉ có thể nhập 1 hoặc 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 4:
                Scanner sc3 = new Scanner(System.in);
                System.out.print("Nhập từ khóa cần tìm : ");
                String key = sc3.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSdt().contains(key) || list.get(i).getID().contains(key) || list.get(i).getHoTen().contains(key) || list.get(i).getAddress().contains(key) || list.get(i).getEmail().contains(key)) {
                        dem++;
                    }
                }
                System.out.println("Tìm thấy " + dem + " kết quả !");
                do {
                    System.out.println("Bạn có muốn in ra kết quả :");
                    System.out.println("1. CÓ");
                    System.out.println("2. KHÔNG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH SÁCH KHÁCH HÀNG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         Họ và tên        | Số điện thoại |        Email        |      Địa chỉ      |");
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getSdt().contains(key) || list.get(i).getID().contains(key) || list.get(i).getHoTen().contains(key) || list.get(i).getAddress().contains(key) || list.get(i).getEmail().contains(key)) {
                                System.out.printf("|%5d|%8s|%26s|%15s|%21s|%19s|", (i + 1), list.get(i).getID(), list.get(i).getHoTen(), list.get(i).getSdt(), list.get(i).getEmail(), list.get(i).getAddress());
                                System.out.println("");
                            }
                        }
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Chỉ có thể nhập 1 hoặc 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
        }
    }

    public void add() {
        KhachHang sp = new KhachHang();
        System.out.println("");
        System.out.println("<--------------Thêm khách hàng mới-------------->");
        sp.nhapTT();
        list.add(sp);
        writeFile();
        System.err.println("Thêm khách hàng thành công !");
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void repair() {
        String ID;
        CheckData check = new CheckData();
        KhachHang kh = new KhachHang();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã khách hàng cần sửa thông tin : ");
            ID = sc.nextLine();
            if (!check.CheckExistKH(ID)) {
                System.err.println("Không có khách hàng mà bạn nhập !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!check.CheckExistKH(ID));
        for (int i = 0; i < list.size(); i++) {
            int chon = 0;
            int sua = 0;
            if (ID.equalsIgnoreCase(list.get(i).getID())) {
                sua = i;
                do {
                    System.out.println("");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("|                   THÔNG TIN BẠN MUỐN SỬA                   |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("| 1 |                   HỌ TÊN                               |");
                    System.out.println("| 2 |                   SỐ ĐIỆN THOẠI                        |");
                    System.out.println("| 3 |                   EMAIL                                |");
                    System.out.println("| 4 |                   ĐỊA CHỈ                              |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 4) {
                        System.err.println("Bạn chỉ có thể nhập từ 1 đến 4 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon < 1 || chon > 4);
                switch (chon) {
                    case 1:
                        Scanner sc1 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setSdt(list.get(sua).getSdt());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setAddress(list.get(sua).getAddress());
                        System.out.println("Họ tên cũ : " + list.get(sua).getHoTen());
                        System.out.print("Nhập họ tên mới : ");
                        kh.setHoTen(sc1.nextLine());
                        list.set(sua, kh);
                        break;
                    case 2:
                        Scanner sc2 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setHoTen(list.get(sua).getHoTen());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setAddress(list.get(sua).getAddress());
                        System.out.println("Số điện thoại cũ : " + list.get(sua).getSdt());
                        System.out.print("Nhập số điện thoại mới : ");
                        kh.setSdt(sc2.nextLine());
                        list.set(sua, kh);
                        break;
                    case 3:
                        Scanner sc3 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setSdt(list.get(sua).getSdt());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setAddress(list.get(sua).getAddress());
                        System.out.println("Email cũ : " + list.get(sua).getEmail());
                        System.out.print("Nhập email mới : ");
                        kh.setEmail(sc3.nextLine());
                        list.set(sua, kh);
                        break;
                    case 4:
                        Scanner sc4 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setSdt(list.get(sua).getSdt());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setHoTen(list.get(sua).getHoTen());
                        System.out.println("Địa chỉ cũ : " + list.get(sua).getAddress());
                        System.out.print("Nhập địa chỉ mới : ");
                        kh.setAddress(sc4.nextLine());
                        list.set(sua, kh);
                        break;
                }
                System.err.println("Sửa thông tin khách hàng thành công !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        writeFile();
    }

    public void remove() {
        CheckData check = new CheckData();
        String xoa;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã khách hàng cần sửa thông tin : ");
            xoa = sc.nextLine();
            if (!check.CheckExistKH(xoa)) {
                System.err.println("Không có khách hàng mà bạn nhập !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!check.CheckExistKH(xoa));
        for (int i = 0; i < list.size(); i++) {
            if (xoa.equalsIgnoreCase(list.get(i).getID())) {
                if (list.remove(list.get(i))) {
                    System.err.println("Xóa khách hàng thành công !");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
        writeFile();
    }

}
