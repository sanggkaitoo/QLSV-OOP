package Manage;

import CheckData.CheckData;
import CheckData.TienTe;
import Objects.GioHang;
import Objects.SanPham;
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

public class QuanLySanPham {

    TienTe tien = new TienTe();
    public ArrayList<SanPham> list = new ArrayList<>();
    private String filename = "SanPham.txt";

    public QuanLySanPham() {
    }

    public void writeFileSauKhiBanHang() {
        readFile();
        try {
            BanHang bh = new BanHang();
            bh.readGioHang();
            Writer wr = null;
            wr = new FileWriter(this.filename);
            BufferedWriter bw = new BufferedWriter(wr);
            for (int j = 0; j < bh.listGH.size(); j++) {
                for (int i = 0; i < list.size(); i++) {
                    if (bh.listGH.get(j).getID().equalsIgnoreCase(list.get(i).getID())) {
                        bw.write(list.get(i).getID() + "\t" + list.get(i).getTenSP() + "\t" + list.get(i).getHangSP() + "\t" + (list.get(i).getSoLuong() - bh.listGH.get(j).getSL()) + "\t" + list.get(i).getDonGia() + "\t" + list.get(i).getDonViTinh());
                        bw.newLine();
                    } else {
                        bw.write(list.get(i).getID() + "\t" + list.get(i).getTenSP() + "\t" + list.get(i).getHangSP() + "\t" + list.get(i).getSoLuong() + "\t" + list.get(i).getDonGia() + "\t" + list.get(i).getDonViTinh());
                        bw.newLine();
                    }
                }
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeFile() {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename);
            BufferedWriter bw = new BufferedWriter(wr);
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).getID() + "\t" + list.get(i).getTenSP() + "\t" + list.get(i).getHangSP() + "\t" + list.get(i).getSoLuong() + "\t" + list.get(i).getDonGia() + "\t" + list.get(i).getDonViTinh());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(SanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
        list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                SanPham sp = new SanPham();
                String[] thongTin = s.split("\t");
                sp.setID(thongTin[0]);
                sp.setTenSP(thongTin[1]);
                sp.setHangSP(thongTin[2]);
                sp.setSoLuong(Integer.parseInt(thongTin[3]));
                sp.setDonGia(Integer.parseInt(thongTin[4]));
                sp.setDonViTinh(thongTin[5]);
                s = br.readLine();
                list.add(sp);
            }
            br.close();

        } catch (IOException ex) {
            System.err.println("Fail : " + ex.getMessage());
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex1) {
                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void danhSach() {
        if (list.size() == 0) {
            System.err.println("KH??NG C?? S???N PH???M N??O !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
            System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
            System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                System.out.println("");
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
    }

    public void find() {
        Scanner sc = new Scanner(System.in);
        int chon;
        do {
            System.out.println("");
            System.out.println("+-------------------------------------------------------------+");
            System.out.println("|                      T??M KI???M S???N PH???M                      |");
            System.out.println("+-------------------------------------------------------------+");
            System.out.println("| 1 |                     THEO ID                             |");
            System.out.println("| 2 |                     THEO T??N                            |");
            System.out.println("| 3 |                     THEO S??? L?????NG                       |");
            System.out.println("| 4 |                     THEO ????N GI??                        |");
            System.out.println("| 5 |                     THEO H??NG S???N PH???M                  |");
            System.out.println("+-------------------------------------------------------------+");
            System.out.println("");
            System.out.print("==> NH???P L???A CH???N C???A B???N : ");
            chon = sc.nextInt();
            if (chon < 1 || chon > 4) {
                System.err.println("B???n ch??? c?? th??? nh???p t??? 1 ?????n 4 !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (chon < 1 || chon > 4);
        int dem = 0;
        switch (chon) {
            case 1:
                Scanner sc1 = new Scanner(System.in);
                System.out.print("Nh???p ID : ");
                String key = sc1.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (key.equalsIgnoreCase(list.get(i).getID())) {
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
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
                        System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
                        System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (key.equalsIgnoreCase(list.get(i).getID())) {
                                System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                                System.out.println("");
                            }
                        }
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 2:
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Nh???p t??n s???n ph???m : ");
                String key1 = sc2.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getTenSP().contains(key1)) {
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
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
                        System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
                        System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getTenSP().contains(key1)) {
                                System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                                System.out.println("");
                            }
                        }
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 3:
                System.out.print("Nh???p s??? l?????ng c???a s???n ph???m c???n t??m : ");
                int key2 = sc.nextInt();
                for (int i = 0; i < list.size(); i++) {
                    if (key2 == list.get(i).getSoLuong()) {
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
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
                        System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
                        System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (key2 == list.get(i).getSoLuong()) {
                                System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                                System.out.println("");
                            }
                        }
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 4:
                Scanner sc3 = new Scanner(System.in);
                System.out.print("Nh???p gi?? c???a s???n ph???m c???n t??m : ");
                int key3 = sc3.nextInt();
                for (int i = 0; i < list.size(); i++) {
                    if (key3 == list.get(i).getDonGia()) {
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
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
                        System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
                        System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (key3 == list.get(i).getDonGia()) {
                                System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                                System.out.println("");
                            }
                        }
                        System.out.println("+--------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
            case 5:
                Scanner sc4 = new Scanner(System.in);
                System.out.print("Nh???p h??ng c???a s???n ph???m c???n t??m : ");
                String key4 = sc4.nextLine();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getHangSP().contains(key4)) {
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
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
                        System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
                        System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
                        System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getHangSP().contains(key4)) {
                                System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                                System.out.println("");
                            }
                        }
                        System.out.println("+--------------------------------------------------------------------------------+");
                        System.out.println("");
                        break;
                    } else if (chon == 2) {
                        break;
                    } else if (chon != 1 | chon != 2) {
                        System.err.println("Ch??? c?? th??? nh???p 1 ho???c 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon != 1 | chon != 2);
                break;
        }
    }

    public void add() {
        SanPham sp = new SanPham();
        System.out.println("<--------------Th??m s???n ph???m m???i-------------->");
        sp.nhapTT();
        list.add(sp);
        writeFile();
        System.err.println("Th??m s???n ph???m th??nh c??ng !");
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void repair() {
        SanPham sp = new SanPham();
        Scanner sc = new Scanner(System.in);
        CheckData check = new CheckData();
        String ID;
        do {
            System.out.print("Nh???p m?? s???n ph???m c???n s???a th??ng tin : ");
            ID = sc.nextLine();
            if (!check.CheckExistSP(ID)) {
                System.err.println("Kh??ng c?? s???n ph???m m?? b???n nh???p !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!check.CheckExistSP(ID));

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
                    System.out.println("| 1 |                   T??N S???N PH???M                         |");
                    System.out.println("| 2 |                   S??? L?????NG S???N PH???M                    |");
                    System.out.println("| 3 |                   ????N GI?? S???N PH???M                     |");
                    System.out.println("+------------------------------------------------------------+");
                    System.out.println("");
                    System.out.print("==> NH???P L???A CH???N C???A B???N : ");
                    chon = sc.nextInt();
                    if (chon < 1 || chon > 4) {
                        System.err.println("B???n ch??? c?? th??? nh???p t??? 1 ?????n 4 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } while (chon < 1 || chon > 4);
                switch (chon) {
                    case 1:
                        Scanner sc1 = new Scanner(System.in);
                        sp.setID(list.get(sua).getID());
                        sp.setSoLuong(list.get(sua).getSoLuong());
                        sp.setDonGia(list.get(sua).getDonGia());
                        sp.setHangSP(list.get(sua).getHangSP());
                        sp.setDonViTinh(list.get(sua).getDonViTinh());
                        System.out.println("T??n s???n ph???m c?? : " + list.get(sua).getTenSP());
                        System.out.print("Nh???p s???n ph???m m???i : ");
                        sp.setTenSP(sc1.nextLine());
                        list.set(sua, sp);
                        break;
                    case 2:
                        Scanner sc2 = new Scanner(System.in);
                        sp.setID(list.get(sua).getID());
                        sp.setTenSP(list.get(sua).getTenSP());
                        sp.setDonGia(list.get(sua).getDonGia());
                        sp.setHangSP(list.get(sua).getHangSP());
                        sp.setDonViTinh(list.get(sua).getDonViTinh());
                        System.out.println("S??? l?????ng s???n ph???m c?? : " + list.get(sua).getSoLuong());
                        System.out.print("S??? l?????ng s???n ph???m m???i : ");
                        sp.setSoLuong(sc2.nextInt());
                        list.set(sua, sp);
                        break;
                    case 3:
                        Scanner sc3 = new Scanner(System.in);
                        sp.setID(list.get(sua).getID());
                        sp.setTenSP(list.get(sua).getTenSP());
                        sp.setSoLuong(list.get(sua).getSoLuong());
                        sp.setHangSP(list.get(sua).getHangSP());
                        sp.setDonViTinh(list.get(sua).getDonViTinh());
                        System.out.println("????n gi?? s???n ph???m c?? : " + list.get(sua).getDonGia());
                        System.out.print("????n gi?? s???n ph???m m???i : ");
                        sp.setDonGia(sc3.nextInt());
                        list.set(sua, sp);
                        break;
                }
                System.err.println("S???a th??ng tin s???n ph???m th??nh c??ng !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.print("Nh???p m?? s???n ph???m c???n s???a th??ng tin : ");
            xoa = sc.nextLine();
            if (!check.CheckExistSP(xoa)) {
                System.err.println("Kh??ng c?? s???n ph???m m?? b???n nh???p !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (!check.CheckExistSP(xoa));
        for (int i = 0; i < list.size(); i++) {
            if (xoa.equalsIgnoreCase(list.get(i).getID())) {
                if (list.remove(list.get(i))) {
                    System.err.println("X??a s???n ph???m th??nh c??ng !");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        }
        writeFile();
    }

    public void tonKho() {
        int dem = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSoLuong() >= 100) {
                dem++;
            }
        }
        if (dem == 0) {
            System.err.println("KH??NG C?? S???N PH???M N??O !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
            System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
            System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSoLuong() >= 100) {
                    System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                    System.out.println("");
                }
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
    }

    public void hetHang() {
        int dem = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSoLuong() < 10) {
                dem++;
            }
        }
        if (dem == 0) {
            try {
                System.err.println("KH??NG C?? S???N PH???M N??O !");
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                DANH S??CH S???N PH???M                                                 |");
            System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+");
            System.out.println("| STT |   ID   |         T??n s???n ph???m        |  H??ng s???n xu???t  |  S??? l?????ng  |        ????n gi??        |  ????n v??? t??nh  |");
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSoLuong() <= 10) {
                    System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|", (i + 1), list.get(i).getID(), list.get(i).getTenSP(), list.get(i).getHangSP(), list.get(i).getSoLuong(), tien.tienTe(list.get(i).getDonGia()), list.get(i).getDonViTinh());
                    System.out.println("");
                }
            }
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
    }

}
