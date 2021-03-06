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
            System.out.println("KH??NG C?? KH??CH H??NG N??O !");
        } else {
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|                                        DANH S??CH KH??CH H??NG                                       |");
            System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
            System.out.println("| STT |   ID   |         H??? v?? t??n        | S??? ??i???n tho???i |        Email        |      ?????a ch???      |");
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
            System.out.println("|                     T??M KI???M KH??CH H??NG                    |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("| 1 |                     THEO ID                            |");
            System.out.println("| 2 |                     THEO S??T                           |");
            System.out.println("| 3 |                     THEO EMAIL                         |");
            System.out.println("| 4 |                     THEO T??? KH??A                       |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("");
            System.out.print("==> NH???P L???A CH???N C???A B???N : ");
            chon = sc.nextInt();
            if (chon < 1 || chon > 4) {
                System.err.println("B???n ch??? c?? th??? nh???p t??? 1 ?????n 4 !");
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
                System.out.print("Nh???p ID : ");
                String ID = sc1.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (ID.equalsIgnoreCase(list.get(i).getID())) {
                        dem++;
                    }
                }
                System.out.println("T??m th???y " + dem + " k???t qu??? !");
                do {
                    System.out.println("B???n c?? mu???n in ra k???t qu??? :");
                    System.out.println("1. C??");
                    System.out.println("2. KH??NG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH S??CH KH??CH H??NG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         H??? v?? t??n        | S??? ??i???n tho???i |        Email        |      ?????a ch???      |");
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
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
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
                System.out.print("Nh???p s??? ??i???n tho???i ho???c c??c s??? li???n nhau n???m trong 1 s??? ??i???n tho???i : ");
                String sdt = sc2.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSdt().contains(sdt)) {
                        dem++;
                    }
                }
                System.out.println("T??m th???y " + dem + " k???t qu??? !");
                do {
                    System.out.println("B???n c?? mu???n in ra k???t qu??? :");
                    System.out.println("1. C??");
                    System.out.println("2. KH??NG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH S??CH KH??CH H??NG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         H??? v?? t??n        | S??? ??i???n tho???i |        Email        |      ?????a ch???      |");
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
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyKhachHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 3:
                System.out.print("Nh???p email ho???c t??? kh??a c?? trong email : ");
                String email = sc.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSdt().contains(email)) {
                        dem++;
                    }
                }
                System.out.println("T??m th???y " + dem + " k???t qu??? !");
                do {
                    System.out.println("B???n c?? mu???n in ra k???t qu??? :");
                    System.out.println("1. C??");
                    System.out.println("2. KH??NG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH S??CH KH??CH H??NG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         H??? v?? t??n        | S??? ??i???n tho???i |        Email        |      ?????a ch???      |");
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
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
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
                System.out.print("Nh???p t??? kh??a c???n t??m : ");
                String key = sc3.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getSdt().contains(key) || list.get(i).getID().contains(key) || list.get(i).getHoTen().contains(key) || list.get(i).getAddress().contains(key) || list.get(i).getEmail().contains(key)) {
                        dem++;
                    }
                }
                System.out.println("T??m th???y " + dem + " k???t qu??? !");
                do {
                    System.out.println("B???n c?? mu???n in ra k???t qu??? :");
                    System.out.println("1. C??");
                    System.out.println("2. KH??NG");
                    System.out.print("==> ");
                    chon = sc.nextInt();
                    if (chon == 1) {
                        System.out.println("+---------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                        DANH S??CH KH??CH H??NG                                       |");
                        System.out.println("+-----+--------+--------------------------+---------------+---------------------+-------------------+");
                        System.out.println("| STT |   ID   |         H??? v?? t??n        | S??? ??i???n tho???i |        Email        |      ?????a ch???      |");
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
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
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
        System.out.println("<--------------Th??m kh??ch h??ng m???i-------------->");
        sp.nhapTT();
        list.add(sp);
        writeFile();
        System.err.println("Th??m kh??ch h??ng th??nh c??ng !");
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
            System.out.print("Nh???p m?? kh??ch h??ng c???n s???a th??ng tin : ");
            ID = sc.nextLine();
            if (!check.CheckExistKH(ID)) {
                System.err.println("Kh??ng c?? kh??ch h??ng m?? b???n nh???p !");
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
                    System.out.println("|                   TH??NG TIN B???N MU???N S???A                   |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("| 1 |                   H??? T??N                               |");
                    System.out.println("| 2 |                   S??? ??I???N THO???I                        |");
                    System.out.println("| 3 |                   EMAIL                                |");
                    System.out.println("| 4 |                   ?????A CH???                              |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NH???P L???A CH???N C???A B???N : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 4) {
                        System.err.println("B???n ch??? c?? th??? nh???p t??? 1 ?????n 4 !");
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
                        System.out.println("H??? t??n c?? : " + list.get(sua).getHoTen());
                        System.out.print("Nh???p h??? t??n m???i : ");
                        kh.setHoTen(sc1.nextLine());
                        list.set(sua, kh);
                        break;
                    case 2:
                        Scanner sc2 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setHoTen(list.get(sua).getHoTen());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setAddress(list.get(sua).getAddress());
                        System.out.println("S??? ??i???n tho???i c?? : " + list.get(sua).getSdt());
                        System.out.print("Nh???p s??? ??i???n tho???i m???i : ");
                        kh.setSdt(sc2.nextLine());
                        list.set(sua, kh);
                        break;
                    case 3:
                        Scanner sc3 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setSdt(list.get(sua).getSdt());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setAddress(list.get(sua).getAddress());
                        System.out.println("Email c?? : " + list.get(sua).getEmail());
                        System.out.print("Nh???p email m???i : ");
                        kh.setEmail(sc3.nextLine());
                        list.set(sua, kh);
                        break;
                    case 4:
                        Scanner sc4 = new Scanner(System.in);
                        kh.setID(list.get(sua).getID());
                        kh.setSdt(list.get(sua).getSdt());
                        kh.setEmail(list.get(sua).getEmail());
                        kh.setHoTen(list.get(sua).getHoTen());
                        System.out.println("?????a ch??? c?? : " + list.get(sua).getAddress());
                        System.out.print("Nh???p ?????a ch??? m???i : ");
                        kh.setAddress(sc4.nextLine());
                        list.set(sua, kh);
                        break;
                }
                System.err.println("S???a th??ng tin kh??ch h??ng th??nh c??ng !");
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
            System.out.print("Nh???p m?? kh??ch h??ng c???n s???a th??ng tin : ");
            xoa = sc.nextLine();
            if (!check.CheckExistKH(xoa)) {
                System.err.println("Kh??ng c?? kh??ch h??ng m?? b???n nh???p !");
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
                    System.err.println("X??a kh??ch h??ng th??nh c??ng !");
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
