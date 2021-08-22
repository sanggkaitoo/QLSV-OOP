/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;

import CheckData.CheckData;
import CheckData.TienTe;
import Objects.HoaDon;
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

/**
 *
 * @author Sang Kaito
 */
public class QuanLyHoaDon {

    private String filename1 = "DoanhThuNV.txt";
    private String filename2 = "BaoCaoHD.txt";
    CheckData check = new CheckData();
    ArrayList<HoaDon> list = new ArrayList<>();
    private String filename = "HoaDon.txt";

    public void readFile() {
        list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filename);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                HoaDon hd = new HoaDon();
                String[] thongTin = s.split("\t");
                String[] ngayThang = thongTin[0].split("_");
                hd.setIDHD(thongTin[0]);
                hd.setIDNV(thongTin[1]);
                hd.setIDKH(thongTin[2]);
                hd.setIDSP(thongTin[3]);
                hd.setSoLuong(Integer.parseInt(thongTin[4]));
                hd.setDonGia(Integer.parseInt(thongTin[5]));
                hd.setThanhTien(Integer.parseInt(thongTin[6]));
                hd.setDay(Integer.parseInt(ngayThang[3]));
                hd.setMonth(Integer.parseInt(ngayThang[2]));
                hd.setYear(Integer.parseInt(ngayThang[1]));
                s = br.readLine();
                list.add(hd);
            }
            br.close();

        } catch (IOException ex) {
            System.err.println("Fail : " + ex.getMessage());
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex1) {
                Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public void hoaDonTheoNgay() {
        resetBaoCao();
        Scanner sc = new Scanner(System.in);
        int tongTien = 0, ngay, thang, nam, dem = 0, stt = 1;
        do {
            System.out.print("Nhập ngày : ");
            ngay = sc.nextInt();
        } while (check.CheckInt(ngay));

        do {
            System.out.print("Nhập tháng : ");
            thang = sc.nextInt();
        } while (check.CheckInt(thang));

        do {
            System.out.print("Nhập năm : ");
            nam = sc.nextInt();
        } while (check.CheckInt(nam));

        TienTe tien = new TienTe();
        readFile();
        for (int i = 0; i < list.size(); i++) {
            if (ngay == list.get(i).getDay() && thang == list.get(i).getMonth() && nam == list.get(i).getYear()) {
                dem++;
            }
        }
        if (list.size() == 0 || dem == 0) {
            System.err.println("KHÔNG CÓ HÓA ĐƠN NÀO !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dem != 0) {
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("|                                     DANH SÁCH HÓA ĐƠN                                     |");
            System.out.printf("|Ngày : %-3s / %-3s / %-72s|", String.valueOf(ngay), String.valueOf(thang), String.valueOf(nam));
            System.out.println("");
            System.out.println("+-----+--------------------------+----------+----------+----------+-------------------------+");
            System.out.println("| STT |           IDHD           |   IDNV   |   IDKH   |   IDSP   |        TỔNG TIỀN        |");
            System.out.println("+-----+--------------------------+----------+----------+----------+-------------------------+");
            for (int i = 0; i < list.size(); i++) {
                if (ngay == list.get(i).getDay() && thang == list.get(i).getMonth() && nam == list.get(i).getYear()) {
                    tongTien = tongTien + list.get(i).getThanhTien();
                    System.out.printf("|%5d|%26s|%10s|%10s|%10s|%21s VNĐ|", stt++, list.get(i).getIDHD(), list.get(i).getIDNV(), list.get(i).getIDKH(), list.get(i).getIDSP(), tien.tienTe(list.get(i).getThanhTien()));
                    System.out.println("");
                }
            }
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.printf("|                                              TỔNG TIỀN BÁN ĐƯỢC |%21s VNĐ|", tien.tienTe(tongTien));
            System.out.println("");
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("");
            int chon = 0;
            if (dem != 0) {
                do {
                    System.out.println("Bạn có muốn xuất file báo cáo ?");
                    System.out.println("1. Có");
                    System.out.println("2. Không");
                    System.out.print("Nhập lựa chọn của bạn : ");
                    chon = sc.nextInt();
                    if (chon < 0 || chon > 2) {
                        System.err.println("Bạn chỉ được nhập 1 hoặc 2 !");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (chon == 1) {
                        for (int i = 0; i < list.size(); i++) {
                            if (thang == list.get(i).getMonth() && nam == list.get(i).getYear()) {
                                tongTien = tongTien + list.get(i).getThanhTien();
                                baoCaoHD(list.get(i).getIDHD(), list.get(i).getIDNV().toUpperCase(), list.get(i).getIDKH(), list.get(i).getIDSP(), list.get(i).getThanhTien());
                            }
                        }
                        System.out.println("File báo cáo được lưu tại : C:\\Users\\dell\\Documents\\NetBeansProjects\\BTL_Cuoi_Khoa\\BaoCaoHD.txt");
                    }
                } while (chon < 0 || chon > 2);
            }
        }
    }

    public void hoaDonTheoThang() {
        resetBaoCao();
        Scanner sc = new Scanner(System.in);
        int tongTien = 0, ngay, thang, nam, dem = 0, stt = 1;
        do {
            System.out.print("Nhập tháng : ");
            thang = sc.nextInt();
        } while (check.CheckInt(thang));

        do {
            System.out.print("Nhập năm : ");
            nam = sc.nextInt();
        } while (check.CheckInt(nam));
        TienTe tien = new TienTe();
        readFile();
        for (int i = 0; i < list.size(); i++) {
            if (thang == list.get(i).getMonth() && nam == list.get(i).getYear()) {
                dem++;
            }
        }
        if (list.size() == 0 || dem == 0) {
            System.err.println("KHÔNG CÓ HÓA ĐƠN NÀO !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (dem != 0) {
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("|                                     DANH SÁCH HÓA ĐƠN                                     |");
            System.out.printf("|Tháng : %-3s / %-77s|", String.valueOf(thang), String.valueOf(nam));
            System.out.println("");
            System.out.println("+-----+--------------------------+----------+----------+----------+-------------------------+");
            System.out.println("| STT |           IDHD           |   IDNV   |   IDKH   |   IDSP   |        TỔNG TIỀN        |");
            System.out.println("+-----+--------------------------+----------+----------+----------+-------------------------+");
            for (int i = 0; i < list.size(); i++) {
                if (thang == list.get(i).getMonth() && nam == list.get(i).getYear()) {
                    tongTien = tongTien + list.get(i).getThanhTien();
                    System.out.printf("|%5d|%26s|%10s|%10s|%10s|%21s VNĐ|", stt++, list.get(i).getIDHD(), list.get(i).getIDNV(), list.get(i).getIDKH(), list.get(i).getIDSP(), tien.tienTe(list.get(i).getThanhTien()));
                    System.out.println("");
                }
            }
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.printf("|                                              TỔNG TIỀN BÁN ĐƯỢC |%21s VNĐ|", tien.tienTe(tongTien));
            System.out.println("");
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
        int chon = 0;
        if (dem != 0) {
            do {
                System.out.println("Bạn có muốn xuất file báo cáo ?");
                System.out.println("1. Có");
                System.out.println("2. Không");
                System.out.print("Nhập lựa chọn của bạn : ");
                chon = sc.nextInt();
                if (chon < 0 || chon > 2) {
                    System.err.println("Bạn chỉ được nhập 1 hoặc 2 !");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (chon == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        if (thang == list.get(i).getMonth() && nam == list.get(i).getYear()) {
                            tongTien = tongTien + list.get(i).getThanhTien();
                            baoCaoHD(list.get(i).getIDHD(), list.get(i).getIDNV().toUpperCase(), list.get(i).getIDKH(), list.get(i).getIDSP(), list.get(i).getThanhTien());
                        }
                    }
                    System.out.println("File báo cáo được lưu tại : C:\\Users\\dell\\Documents\\NetBeansProjects\\BTL_Cuoi_Khoa\\BaoCaoHD.txt");
                }
            } while (chon < 0 || chon > 2);
        }
    }

    public void hoaDonTheoSanPham() {
        resetBaoCao();
        Scanner sc = new Scanner(System.in);
        int tongTien = 0, dem = 0, stt = 1;
        String ID;
        System.out.print("Nhập ID sản phẩm : ");
        ID = sc.nextLine();
        TienTe tien = new TienTe();
        readFile();
        for (int i = 0; i < list.size(); i++) {
            if (ID.equalsIgnoreCase(list.get(i).getIDSP())) {
                dem++;
            }
        }
        if (list.size() == 0 || dem == 0) {
            System.err.println("KHÔNG CÓ HÓA ĐƠN NÀO !");
        } else if (dem != 0) {
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("|                                     DANH SÁCH HÓA ĐƠN                                     |");
            System.out.printf("|ID sản phẩm : %-77s|", ID.toUpperCase());
            System.out.println("");
            System.out.println("+-----+--------------------------+----------+----------+----------+-------------------------+");
            System.out.println("| STT |           IDHD           |   IDNV   |   IDKH   |   IDSP   |        TỔNG TIỀN        |");
            System.out.println("+-----+--------------------------+----------+----------+----------+-------------------------+");
            for (int i = 0; i < list.size(); i++) {
                if (ID.equalsIgnoreCase(list.get(i).getIDSP())) {
                    tongTien = tongTien + list.get(i).getThanhTien();
                    System.out.printf("|%5d|%26s|%10s|%10s|%10s|%21s VNĐ|", stt++, list.get(i).getIDHD(), list.get(i).getIDNV().toUpperCase(), list.get(i).getIDKH(), list.get(i).getIDSP(), tien.tienTe(list.get(i).getThanhTien()));
                    System.out.println("");
                }
            }
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.printf("|                                              TỔNG TIỀN BÁN ĐƯỢC |%21s VNĐ|", tien.tienTe(tongTien));
            System.out.println("");
            System.out.println("+-------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
        int chon = 0;
        if (dem != 0) {
            do {
                System.out.println("Bạn có muốn xuất file báo cáo ?");
                System.out.println("1. Có");
                System.out.println("2. Không");
                System.out.print("Nhập lựa chọn của bạn : ");
                chon = sc.nextInt();
                if (chon < 0 || chon > 2) {
                    System.err.println("Bạn chỉ được nhập 1 hoặc 2 !");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (chon == 1) {
                    for (int i = 0; i < list.size(); i++) {
                        if (ID.equalsIgnoreCase(list.get(i).getIDSP())) {
                            baoCaoHD(list.get(i).getIDHD(), list.get(i).getIDNV().toUpperCase(), list.get(i).getIDKH(), list.get(i).getIDSP(), list.get(i).getThanhTien());
                        }
                    }
                    System.out.println("File báo cáo được lưu tại : C:\\Users\\dell\\Documents\\NetBeansProjects\\BTL_Cuoi_Khoa\\BaoCaoHD.txt");
                }
            } while (chon < 0 || chon > 2);
        }
    }

    public void doanhThuNV() {
        resetDoanhThu();
        Scanner sc = new Scanner(System.in);
        QuanLyNhanVien qlnv = new QuanLyNhanVien();
        qlnv.readFile();
        int thang, nam, banDuoc = 0, dem = 0, temp = 0;
        System.out.print("Nhập tháng : ");
        thang = sc.nextInt();
        System.out.print("Nhập năm : ");
        nam = sc.nextInt();
        TienTe tien = new TienTe();
        readFile();

        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("|                DOANH THU CỦA NHÂN VIÊN TRONG THÁNG                  |");
        System.out.printf("|Tháng : %-3s / %-55s|", thang, nam);
        System.out.println("");
        System.out.println("+-----+----------+--------------------------+-------------------------+");
        System.out.println("| STT |   IDNV   |     HỌ TÊN NHÂN VIÊN     |        TỔNG TIỀN        |");
        System.out.println("+-----+----------+--------------------------+-------------------------+");
        for (int i = 0; i < qlnv.list.size(); i++) {
            int tongTien = 0;
            for (int j = 0; j < list.size(); j++) {
                if (qlnv.list.get(i).getID().equalsIgnoreCase(list.get(j).getIDNV())) {
                    temp = j;
                    tongTien = tongTien + list.get(j).getThanhTien();
                }
            }
            if (qlnv.list.get(i).getID().equalsIgnoreCase(list.get(temp).getIDNV())) {
                System.out.printf("|%5d|%10s|%26s|%25s|", dem = dem + 1, qlnv.list.get(i).getID(), qlnv.list.get(i).getHoTen(), tien.tienTe(tongTien));
                System.out.println("");
                banDuoc = banDuoc + tongTien;
            }
        }
        System.out.println("+---------------------------------------------------------------------+");
        System.out.printf("|                        TỔNG TIỀN BÁN ĐƯỢC |%21s VNĐ|", tien.tienTe(banDuoc));
        System.out.println("");
        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("");
        int chon = 0;
        do {
            System.out.println("Bạn có muốn xuất file báo cáo ?");
            System.out.println("1. Có");
            System.out.println("2. Không");
            System.out.print("Nhập lựa chọn của bạn : ");
            chon = sc.nextInt();
            if (chon < 0 || chon > 2) {
                System.err.println("Bạn chỉ được nhập 1 hoặc 2 !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (chon == 1) {
                for (int i = 0; i < qlnv.list.size(); i++) {
                    int tongTien = 0;
                    for (int j = 0; j < list.size(); j++) {
                        if (qlnv.list.get(i).getID().equalsIgnoreCase(list.get(j).getIDNV())) {
                            temp = j;
                            tongTien = tongTien + list.get(j).getThanhTien();
                        }
                    }
                    if (qlnv.list.get(i).getID().equalsIgnoreCase(list.get(temp).getIDNV())) {
                        banDuoc = banDuoc + tongTien;
                        wirteDoanhThu(dem, qlnv.list.get(i).getID(), qlnv.list.get(i).getHoTen(), tongTien);
                    }
                }
                System.out.println("File báo cáo được lưu tại : C:\\Users\\dell\\Documents\\NetBeansProjects\\BTL_Cuoi_Khoa\\DoanhThuNV.txt");
            }
        } while (chon < 0 || chon > 2);

    }

    public void wirteDoanhThu(int dem, String IDNV, String TenNV, int TongTien) {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename1, true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(IDNV + "\t" + TenNV + "\t" + TongTien);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetDoanhThu() {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename1);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write("");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void baoCaoHD(String IDHD, String IDVN, String IDKH, String IDSP, int tongTien) {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename2, true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(IDHD + "\t" + IDVN + "\t" + IDKH + "\t" + IDSP + "\t" + tongTien);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void resetBaoCao(){
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename2);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write("");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
