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
            System.err.println("KH??NG C?? NH??N VI??N N??O !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            System.out.println("|                                        DANH S??CH NH??N VI??N                                        |");
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
            System.out.println("|                     T??M KI???M NH??N VI??N                     |");
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
                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH S??CH NH??N VI??N                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH S??CH NH??N VI??N                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH S??CH NH??N VI??N                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println("|                                        DANH S??CH NH??N VI??N                                        |");
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
                            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
        }
    }

    public void add() {
        NhanVien nv = new NhanVien();
        System.out.println("<--------------Th??m nh??n vi??n m???i-------------->");
        nv.nhapTT();
        list.add(nv);
        writeFile();
        System.err.println("Th??m nh??n vi??n th??nh c??ng !");
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
            System.out.print("Nh???p m?? nh??n vi??n c???n s???a th??ng tin : ");
            ID = sc.nextLine();
            if (!check.CheckExistNV(ID)) {
                System.err.println("Kh??ng c?? nh??n vi??n m?? b???n nh???p !");
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
                    System.out.println("|                   TH??NG TIN B???N MU???N S???A                   |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("| 1 |                   ID                                   |");
                    System.out.println("| 2 |                   M???T KH???U                             |");
                    System.out.println("| 3 |                   H??? T??N                               |");
                    System.out.println("| 4 |                   S??? ??I???N THO???I                        |");
                    System.out.println("| 5 |                   EMAIL                                |");
                    System.out.println("| 6 |                   ?????A CH???                              |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NH???P L???A CH???N C???A B???N : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 6) {
                        System.err.println("B???n ch??? c?? th??? nh???p t??? 1 ?????n 6 !");
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
                        System.out.println("H??? ID c?? : " + list.get(sua).getHoTen());
                        System.out.print("Nh???p ID m???i : ");
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
                            System.out.print("Nh???p m???t kh???u c?? : ");
                            mk = sc6.nextLine();
                            if (!check.CheckPass(list.get(i).getID(), mk)) {
                                System.err.println("B???n ???? nh???p sai m???t kh???u c?? !");
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } while (!check.CheckPass(list.get(i).getID(), mk));

                        System.out.print("Nh???p m???t kh???u m???i : ");
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
                        System.out.println("H??? t??n c?? : " + list.get(sua).getHoTen());
                        System.out.print("Nh???p h??? t??n m???i : ");
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
                        System.out.println("S??? ??i???n tho???i c?? : " + list.get(sua).getSdt());
                        System.out.print("Nh???p s??? ??i???n tho???i m???i : ");
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
                        System.out.println("Email c?? : " + list.get(sua).getEmail());
                        System.out.print("Nh???p email m???i : ");
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
                        System.out.println("?????a ch??? c?? : " + list.get(sua).getAddress());
                        System.out.print("Nh???p ?????a ch??? m???i : ");
                        nv.setAddress(sc4.nextLine());
                        list.set(sua, nv);
                        break;
                }
                System.err.println("S???a th??ng tin th??nh c??ng !");
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
                    System.out.println("|                   TH??NG TIN B???N MU???N S???A                   |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("| 1 |                   M???T KH???U                             |");
                    System.out.println("| 2 |                   H??? T??N                               |");
                    System.out.println("| 3 |                   S??? ??I???N THO???I                        |");
                    System.out.println("| 4 |                   EMAIL                                |");
                    System.out.println("| 5 |                   ?????A CH???                              |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NH???P L???A CH???N C???A B???N : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 5) {
                        System.err.println("B???n ch??? c?? th??? nh???p t??? 1 ?????n 5 !");
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
                            System.out.print("Nh???p m???t kh???u c?? : ");
                            mk = sc6.nextLine();
                            if (!check.CheckPass(list.get(i).getID(), mk)) {
                                System.err.println("B???n ???? nh???p sai m???t kh???u c?? !");
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } while (!check.CheckPass(list.get(i).getID(), mk));

                        System.out.print("Nh???p m???t kh???u m???i : ");
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
                        System.out.println("H??? t??n c?? : " + list.get(sua).getHoTen());
                        System.out.print("Nh???p h??? t??n m???i : ");
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
                        System.out.println("S??? ??i???n tho???i c?? : " + list.get(sua).getSdt());
                        System.out.print("Nh???p s??? ??i???n tho???i m???i : ");
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
                        System.out.println("Email c?? : " + list.get(sua).getEmail());
                        System.out.print("Nh???p email m???i : ");
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
                        System.out.println("?????a ch??? c?? : " + list.get(sua).getAddress());
                        System.out.print("Nh???p ?????a ch??? m???i : ");
                        nv.setAddress(sc4.nextLine());
                        list.set(sua, nv);
                        break;
                }
                System.err.println("S???a th??ng tin th??nh c??ng !");
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
                System.out.print("Nh???p m???t kh???u m???i : ");
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
            System.out.print("Nh???p m?? nh??n vi??n c???n s???a th??ng tin : ");
            xoa = sc.nextLine();
            if (!check.CheckExistNV(xoa)) {
                System.err.println("Kh??ng c?? nh??n vi??n m?? b???n nh???p !");
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
                    System.err.println("X??a nh??n vi??n th??nh c??ng !");
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
