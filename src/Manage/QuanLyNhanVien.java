package Manage;

import CheckData.CheckData;
import CheckData.MaHoaPass;
import Objects.NhanVien;
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

public class QuanLyNhanVien {

    public ArrayList<NhanVien> list = new ArrayList<>();
    private String filename = "NhanVien.txt";

    public QuanLyNhanVien() {
    }

    public void writeFile() {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename);
            BufferedWriter bw = new BufferedWriter(wr);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).getID() + "\t" + list.get(i).getHoTen() + "\t" + list.get(i).getSdt() + "\t" + list.get(i).getEmail() + "\t" + list.get(i).getAddress() + "\t" + list.get(i).getPass());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(NhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
        list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                NhanVien nv = new NhanVien();
                String[] thongTin = s.split("\t");
                nv.setID(thongTin[0]);
                nv.setHoTen(thongTin[1]);
                nv.setSdt(thongTin[2]);
                nv.setEmail(thongTin[3]);
                nv.setAddress(thongTin[4]);
                nv.setPass(thongTin[5]);
                s = br.readLine();
                list.add(nv);
            }
            br.close();

        } catch (IOException ex) {
            System.err.println("Fail : " + ex.getMessage());
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex1) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void danhSach() {
        if (list.size() == 0) {
            System.err.println("KHÔNG CÓ NHÂN VIÊN NÀO !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|                                        DANH SÁCH NHÂN VIÊN                                        |");
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

    public void thongTinNV(String ID) {
        readFile();
        for (int i = 0; i < list.size(); i++) {
            if (ID.equalsIgnoreCase(list.get(i).getID())) {
                list.get(i).inTT();
            }
        }
    }

    public void find() {
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("|                     TÌM KIẾM NHÂN VIÊN                     |");
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
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH SÁCH NHÂN VIÊN                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH SÁCH NHÂN VIÊN                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH SÁCH NHÂN VIÊN                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH SÁCH NHÂN VIÊN                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
        }
    }

    public void add() {
        NhanVien nv = new NhanVien();
        System.out.println("<--------------Thêm nhân viên mới-------------->");
        nv.nhapTT();
        list.add(nv);
        writeFile();
        System.err.println("Thêm nhân viên thành công !");
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void repairAD() {
        CheckData check = new CheckData();
        String ID;
        NhanVien nv = new NhanVien();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã nhân viên cần sửa thông tin : ");
            ID = sc.nextLine();
            if (!check.CheckExistNV(ID)) {
                System.err.println("Không có nhân viên mà bạn nhập !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!check.CheckExistNV(ID));
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
                    System.out.println("| 1 |                   ID                                   |");
                    System.out.println("| 2 |                   MẬT KHẨU                             |");
                    System.out.println("| 3 |                   HỌ TÊN                               |");
                    System.out.println("| 4 |                   SỐ ĐIỆN THOẠI                        |");
                    System.out.println("| 5 |                   EMAIL                                |");
                    System.out.println("| 6 |                   ĐỊA CHỈ                              |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 6) {
                        System.err.println("Bạn chỉ có thể nhập từ 1 đến 6 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon < 1 || chon > 6);
                switch (chon) {
                    case 1:
                        Scanner sc5 = new Scanner(System.in);
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        nv.setHoTen(list.get(sua).getHoTen());
                        System.out.println("Họ ID cũ : " + list.get(sua).getHoTen());
                        System.out.print("Nhập ID mới : ");
                        nv.setID(sc5.nextLine());
                        list.set(sua, nv);
                        break;
                    case 2:
                        MaHoaPass mh = new MaHoaPass();
                        Scanner sc6 = new Scanner(System.in);
                        String mk;
                        nv.setID(list.get(sua).getID());
                        nv.setHoTen(list.get(i).getHoTen());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());

                        do {
                            System.out.print("Nhập mật khẩu cũ : ");
                            mk = sc6.nextLine();
                            if (!check.CheckPass(list.get(i).getID(), mk)) {
                                System.err.println("Bạn đã nhập sai mật khẩu cũ !");
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } while (!check.CheckPass(list.get(i).getID(), mk));

                        System.out.print("Nhập mật khẩu mới : ");
                        nv.setPass(mh.md5(sc6.nextLine()));
                        list.set(sua, nv);
                        break;
                    case 3:
                        Scanner sc1 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        System.out.println("Họ tên cũ : " + list.get(sua).getHoTen());
                        System.out.print("Nhập họ tên mới : ");
                        nv.setHoTen(sc1.nextLine());
                        list.set(sua, nv);
                        break;
                    case 4:
                        Scanner sc2 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setHoTen(list.get(sua).getHoTen());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        System.out.println("Số điện thoại cũ : " + list.get(sua).getSdt());
                        System.out.print("Nhập số điện thoại mới : ");
                        nv.setSdt(sc2.nextLine());
                        list.set(sua, nv);
                        break;
                    case 5:
                        Scanner sc3 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        System.out.println("Email cũ : " + list.get(sua).getEmail());
                        System.out.print("Nhập email mới : ");
                        nv.setEmail(sc3.nextLine());
                        list.set(sua, nv);
                        break;
                    case 6:
                        Scanner sc4 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setHoTen(list.get(sua).getHoTen());
                        System.out.println("Địa chỉ cũ : " + list.get(sua).getAddress());
                        System.out.print("Nhập địa chỉ mới : ");
                        nv.setAddress(sc4.nextLine());
                        list.set(sua, nv);
                        break;
                }
                System.err.println("Sửa thông tin thành công !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        writeFile();
    }

    public void repairNV(String ID) {
        readFile();
        MaHoaPass mh = new MaHoaPass();
        NhanVien nv = new NhanVien();
        Scanner sc = new Scanner(System.in);
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
                    System.out.println("| 1 |                   MẬT KHẨU                             |");
                    System.out.println("| 2 |                   HỌ TÊN                               |");
                    System.out.println("| 3 |                   SỐ ĐIỆN THOẠI                        |");
                    System.out.println("| 4 |                   EMAIL                                |");
                    System.out.println("| 5 |                   ĐỊA CHỈ                              |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NHẬP LỰA CHỌN CỦA BẠN : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 5) {
                        System.err.println("Bạn chỉ có thể nhập từ 1 đến 5 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon < 1 || chon > 5);
                switch (chon) {
                    case 1:
                        CheckData check = new CheckData();
                        Scanner sc6 = new Scanner(System.in);
                        String mk;
                        nv.setID(list.get(sua).getID());
                        nv.setHoTen(list.get(i).getHoTen());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());

                        do {
                            System.out.print("Nhập mật khẩu cũ : ");
                            mk = sc6.nextLine();
                            if (!check.CheckPass(list.get(i).getID(), mk)) {
                                System.err.println("Bạn đã nhập sai mật khẩu cũ !");
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } while (!check.CheckPass(list.get(i).getID(), mk));

                        System.out.print("Nhập mật khẩu mới : ");
                        nv.setPass(mh.md5(sc6.nextLine()));
                        list.set(sua, nv);
                        break;
                    case 2:
                        Scanner sc1 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        System.out.println("Họ tên cũ : " + list.get(sua).getHoTen());
                        System.out.print("Nhập họ tên mới : ");
                        nv.setHoTen(sc1.nextLine());
                        list.set(sua, nv);
                        break;
                    case 3:
                        Scanner sc2 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setHoTen(list.get(sua).getHoTen());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        System.out.println("Số điện thoại cũ : " + list.get(sua).getSdt());
                        System.out.print("Nhập số điện thoại mới : ");
                        nv.setSdt(sc2.nextLine());
                        list.set(sua, nv);
                        break;
                    case 4:
                        Scanner sc3 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setAddress(list.get(sua).getAddress());
                        System.out.println("Email cũ : " + list.get(sua).getEmail());
                        System.out.print("Nhập email mới : ");
                        nv.setEmail(sc3.nextLine());
                        list.set(sua, nv);
                        break;
                    case 5:
                        Scanner sc4 = new Scanner(System.in);
                        nv.setID(list.get(sua).getID());
                        nv.setPass(list.get(sua).getPass());
                        nv.setSdt(list.get(sua).getSdt());
                        nv.setEmail(list.get(sua).getEmail());
                        nv.setHoTen(list.get(sua).getHoTen());
                        System.out.println("Địa chỉ cũ : " + list.get(sua).getAddress());
                        System.out.print("Nhập địa chỉ mới : ");
                        nv.setAddress(sc4.nextLine());
                        list.set(sua, nv);
                        break;
                }
                System.err.println("Sửa thông tin thành công !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        writeFile();
    }

    public void repairPass(String ID) {
        readFile();
        MaHoaPass mh = new MaHoaPass();
        NhanVien nv = new NhanVien();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < list.size(); i++) {
            if (ID.equalsIgnoreCase(list.get(i).getID())) {
                nv.setID(list.get(i).getID());
                nv.setSdt(list.get(i).getSdt());
                nv.setEmail(list.get(i).getEmail());
                nv.setAddress(list.get(i).getAddress());
                nv.setHoTen(list.get(i).getHoTen());
                System.out.print("Nhập mật khẩu mới : ");
                nv.setPass(mh.md5(sc.nextLine()));
                list.set(i, nv);

            }
        }
        writeFile();
    }

    public void remove() {
        CheckData check = new CheckData();
        String xoa;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã nhân viên cần sửa thông tin : ");
            xoa = sc.nextLine();
            if (!check.CheckExistNV(xoa)) {
                System.err.println("Không có nhân viên mà bạn nhập !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!check.CheckExistNV(xoa));
        for (int i = 0; i < list.size(); i++) {
            if (xoa.equalsIgnoreCase(list.get(i).getID())) {
                if (list.remove(list.get(i))) {
                    System.err.println("Xóa nhân viên thành công !");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
        writeFile();
    }

}
