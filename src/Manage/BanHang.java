/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;

import CheckData.CheckData;
import CheckData.TienTe;
import Objects.GioHang;
import Objects.KhachHang;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Sang Kaito
 */
public class BanHang {

    TienTe tien = new TienTe();
    private String filename1 = "GioHang.txt";
    private String filename = "HoaDon.txt";
    ArrayList gioHang = new ArrayList();
    ArrayList<GioHang> listGH = new ArrayList<>();
    QuanLyKhachHang qlkh = new QuanLyKhachHang();
    QuanLySanPham qlsp = new QuanLySanPham();
    CheckData check = new CheckData();
    String IDKH = null;
//    ArrayList<KhachHang> list = new ArrayList<>();

    public BanHang() {

    }

    public void BH(String IDNV) throws ParseException {
        resetGioHang();
        Scanner sc = new Scanner(System.in);
        KhachHang kh1 = new KhachHang();
        qlkh.readFile();
        int chon = 0;
        do {
            Scanner sc1 = new Scanner(System.in);
            System.out.print("Nhập ID khách hàng : ");
            IDKH = sc1.nextLine().toUpperCase();
            if (check.CheckExistKH(IDKH)) {
                chonSP(IDNV);
            } else {
                try {
                    System.err.println("=>> Không tìm thấy khách hàng");
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("1. Thêm mới khách hàng ");
                System.out.println("2. Nhập lại mã khách hàng");
                System.out.print("Nhập lựa chọn của bạn : ");
                chon = sc.nextInt();
                if (chon == 1) {
                    qlkh.add();
                }
            }
        } while (chon == 2 || chon == 4);
    }

    int chon = 0;

    public void chonSP(String IDNV) throws ParseException {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập mã sản phẩm muốn mua : ");
            Scanner sc1 = new Scanner(System.in);
            String IDSP = sc1.nextLine();
            qlsp.readFile();
            if (check.CheckExistSP(IDSP)) {
                for (int i = 0; i < qlsp.list.size(); i++) {
                    if (IDSP.equalsIgnoreCase(qlsp.list.get(i).getID())) {
                        qlsp.list.get(i).inTT();
                        System.out.print("Nhập số lượng sản phẩm muốn mua : ");
                        int sl = sc.nextInt();
                        Chon(sl, i, IDNV);
                    }
                }
            } else {
                System.err.println("==> Không có sản phẩm mà bạn chọn !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("1. Chọn lại sản phẩm");
                System.out.println("2. Hoàn thành mua hàng");
                System.out.print("Nhập lựa chọn của bạn : ");
                chon = sc.nextInt();
            }
        } while (chon == 1);
    }

    public void writeGioHang(String gioHang) {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename1, true);
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(gioHang);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeGioHang2() {
        try {
            Writer wr = null;
            wr = new FileWriter(this.filename1);
            BufferedWriter bw = new BufferedWriter(wr);
            for (int i = 0; i < listGH.size(); i++) {
                bw.write(listGH.get(i).getID() + "\t" + listGH.get(i).getSL());
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetGioHang() {
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

    public void readGioHang() {
        listGH = new ArrayList<>();
        try {
            FileReader fr = new FileReader(this.filename1);
            BufferedReader br = new BufferedReader(fr);
            String s = br.readLine();
            while (s != null) {
                GioHang gh = new GioHang();
                String[] thongTin = s.split("\t");
                gh.setID(thongTin[0]);
                gh.setSL(Integer.parseInt(thongTin[1]));
                s = br.readLine();
                listGH.add(gh);
            }
            br.close();

        } catch (IOException ex) {
            System.err.println("Fail : " + ex.getMessage());
        }
    }

    public void Chon(int sl, int i, String IDNV) throws ParseException {
        Scanner sc = new Scanner(System.in);
        if (Chon1(sl, i) == 1) {
            System.err.println("Sản phẩm được thêm vào giỏ hàng thành công !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
            String gioHang = qlsp.list.get(i).getID() + "\t" + sl;
            writeGioHang(gioHang);
            do {
                System.err.println("");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("1. Tiếp tục mua hàng ");
                System.out.println("2. Xóa sản phẩm khỏi giỏ hàng ");
                System.out.println("3. Hoàn thành mua hàng ");
                System.out.println("4. Xóa đơn hàng");
                System.out.println("5. Xem đơn hàng");
                System.out.print("Nhập lựa chọn của bạn : ");
                chon = sc.nextInt();
                Chon2(chon, IDNV);
            } while (chon == 2 || chon == 5);
        } else if (Chon1(sl, i) == 2) {
            try {
                System.err.println("Số lượng sản phẩm trong kho không đủ !");
                Thread.sleep(200);
                chon = 1;
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Chon1(sl, i) == 3) {
            try {
                System.err.println("Bạn phải nhập số lượng lớn hơn 0 !");
                Thread.sleep(200);
                chon = 1;
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int Chon1(int SL, int i) {
        int a = 0;
        if (SL <= qlsp.list.get(i).getSoLuong() && SL > 0) {
            a = 1;
        } else if (SL > qlsp.list.get(i).getSoLuong()) {
            a = 2;
        } else if (SL <= 0) {
            a = 3;
        }
        return a;
    }

    public void Chon2(int chon, String IDNV) throws ParseException {
        try {
            Scanner sc = new Scanner(System.in);
            switch (chon) {
                case 1:
                    break;
                case 2:
                    System.out.print("Nhập ID sản phẩm muốn xóa : ");
                    String ID = sc.nextLine();
                    readGioHang();
                    for (int i = 0; i < listGH.size(); i++) {
                        if (ID.equalsIgnoreCase(listGH.get(i).getID())) {
                            try {
                                listGH.remove(i);
                                writeGioHang2();
                                System.err.println("Xóa sản phẩm thành công khỏi giỏ hàng !");
                                Thread.sleep(200);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;
                case 3:
                    inHoaDon(IDNV);
                    break;
                case 4:
                    resetGioHang();
                    System.err.println("Xóa giỏ hàng thành công !");
                    Thread.sleep(200);
                    
                    break;
                case 5:
                    danhSachGH();
                    break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void danhSachGH() {
        System.out.println(IDKH);
        int dem = 1;
        readGioHang();
        QuanLySanPham ql = new QuanLySanPham();
        ql.readFile();
        if (listGH.size() == 0) {
            try {
                System.err.println("KHÔNG CÓ SẢN PHẨM NÀO !");
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                                                    DANH SÁCH SẢN PHẨM                                                                        |");
            System.out.println("+-----+--------+-----------------------------+-----------------+----------------+-----------------------+---------------+----------------------+");
            System.out.println("| STT |   ID   |         Tên sản phẩm        |  Hãng sản xuất  |  Số lượng mua  |        Đơn giá        |  Đơn vị tính  |      Thành tiền      |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            for (int j = 0; j < listGH.size(); j++) {
                for (int i = 0; i < ql.list.size(); i++) {
                    if (listGH.get(j).getID().equalsIgnoreCase(ql.list.get(i).getID())) {
                        System.out.printf("|%5d|%8s|%29s|%17s|%16s|%23s|%15s|%18s VNĐ|", dem++, ql.list.get(i).getID(), ql.list.get(i).getTenSP(), ql.list.get(i).getHangSP(), listGH.get(j).getSL(), tien.tienTe(ql.list.get(i).getDonGia()), ql.list.get(i).getDonViTinh(), tien.tienTe(listGH.get(j).getSL() * ql.list.get(i).getDonGia()));
                        System.out.println("");
                    }
                }
            }
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("");
        }
    }

    public void inHoaDon(String IDNV) throws ParseException {
        TienTe tien = new TienTe();
        Scanner sc = new Scanner(System.in);
        String tenKH = null, mailKH = null;
        String tenNV = null;
        QuanLyKhachHang qlkh = new QuanLyKhachHang();
        qlkh.readFile();
        for (int i = 0; i < qlkh.list.size(); i++) {
            if (IDKH.equalsIgnoreCase(qlkh.list.get(i).getID())) {
                tenKH = qlkh.list.get(i).getHoTen();
                mailKH = qlkh.list.get(i).getEmail();
            }
        }
        QuanLyNhanVien qlnv = new QuanLyNhanVien();
        qlnv.readFile();
        for (int i = 0; i < qlnv.list.size(); i++) {
            if (IDNV.equalsIgnoreCase(qlnv.list.get(i).getID())) {
                tenNV = qlnv.list.get(i).getHoTen();
            }
        }
        int dem = 1, tongTien = 0, VAT = 0, thanhToan = 0, traTien;
        readGioHang();
        QuanLySanPham ql = new QuanLySanPham();
        ql.readFile();
        InTien chu = new InTien();
        readGioHang();
        for (int j = 0; j < listGH.size(); j++) {
            for (int i = 0; i < ql.list.size(); i++) {
                if (listGH.get(j).getID().equalsIgnoreCase(ql.list.get(i).getID())) {
                    tongTien = tongTien + (listGH.get(j).getSL() * ql.list.get(i).getDonGia());
                }
            }
        }
        VAT = tongTien * 10 / 100;
        thanhToan = tongTien + VAT;
        String IDHD = IDKH + maHoaDon();
        System.out.println("Tổng tiền phải thanh toán : " + tien.tienTe(thanhToan));
        do {
            int chon;
            System.out.print("Số tiền khách trả : ");
            traTien = sc.nextInt();
            if (traTien < thanhToan) {
                System.err.println("Không đủ để thanh toán !");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("1. Nhập lại tiền ");
                System.out.println("2. Xóa sản phẩm ");
                System.out.println("3. Hủy thanh toán ");
                chon = sc.nextInt();
                if (chon == 2) {
                    Chon2(chon, IDNV);
                } else if ( chon == 3){
                    Chon2(4, IDNV);
                }
            }
        } while (chon == 1 || traTien < thanhToan);
        if (listGH.size() == 0) {
            System.err.println("KHÔNG CÓ SẢN PHẨM NÀO !");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|Tel : 0888.500.798    Mail : htqlsangkaito@gmai.com                                                                                           |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("|                                                             SIÊU THỊ KAITO MART                                                              |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("|                                                               HÓA ĐƠN MUA HÀNG                                                               |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("| Mã hóa đơn : %-128s|", IDHD);
            System.out.println("");
            System.out.printf("| Mã nhân viên  : %-13sNhân viên bán hàng : %-91s|", IDNV, tenNV);
            System.out.println("");
            System.out.printf("| Mã khách hàng : %-13sKhách hàng : %-99s|", IDKH, tenKH);
            System.out.println("");
            System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+--------------------------+");
            System.out.println("| STT |   ID   |         Tên sản phẩm        |  Hãng sản xuất  |  Số lượng  |        Đơn giá        |  Đơn vị tính  |        Thành tiền        |");
            System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+--------------------------+");
            for (int j = 0; j < listGH.size(); j++) {
                for (int i = 0; i < ql.list.size(); i++) {
                    if (listGH.get(j).getID().equalsIgnoreCase(ql.list.get(i).getID())) {
                        System.out.printf("|%5d|%8s|%29s|%17s|%12s|%23s|%15s|%22s VNĐ|", dem++, ql.list.get(i).getID(), ql.list.get(i).getTenSP(), ql.list.get(i).getHangSP(), listGH.get(j).getSL(), tien.tienTe(ql.list.get(i).getDonGia()), ql.list.get(i).getDonViTinh(), tien.tienTe(listGH.get(j).getSL() * ql.list.get(i).getDonGia()));
                        System.out.println("");
                    }
                }
            }
            System.out.println("+-----+--------+-----------------------------+-----------------+------------+-----------------------+---------------+--------------------------+");
            System.out.printf("|                                                                                                     Tổng tiền     |%22s VNĐ|", tien.tienTe(tongTien));
            System.out.println("");
            System.out.printf("|                                                                                                      Thuế VAT     |%22s VNĐ|", tien.tienTe(VAT));
            System.out.println("");
            System.out.printf("|                                                                                                       Cộng        |%22s VNĐ|", tien.tienTe(thanhToan));
            System.out.println("");
            System.out.println("+-------------------------------------------------------------------------------------------------------------------+--------------------------+");
            System.out.printf("|%142s|", chu.main(tongTien * (110 / 100)));
            System.out.println("");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("|                                                                                                     Tiền khách trả: %25s|", tien.tienTe(traTien));
            System.out.println("");
            System.out.printf("|                                                                                                 Số tiền thanh toán: %25s|", tien.tienTe(thanhToan));
            System.out.println("");
            System.out.printf("|                                                                                                            Trả lại: %25s|", tien.tienTe(traTien - thanhToan));
            System.out.println("");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("|                       Khách hàng                                                                Nhân viên bán hàng                           |");
            System.out.println("|                  ( Ký, ghi rõ họ tên )                                                         ( Ký, ghi rõ họ tên )                         |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("|                                                                                                                                              |");
            System.out.println("+----------------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.println("Cảm ơn " + tenKH + " đã mua hàng tại cửa hàng !");
            writeHoaDon(IDHD, IDNV, IDKH);
            qlsp.writeFileSauKhiBanHang();
            sendMailHD(mailKH, tenKH, tien.tienTe(thanhToan));
        }
    }

    public void sendMailHD(String mail, String tenKH, String thanhToan) {
        try {
            Email email = new SimpleEmail();

            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("htqlsangkaito@gmail.com", "sanggkaitoo"));

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom("htqlsangkaito@gmail.com", "Sang Kaito");

            // Tiêu đề
            email.setSubject("Cảm ơn " + tenKH + " đã mua hàng tại Kaito Mart"); //Tiêu đề khi gửi email

            // Nội dung email
            email.setMsg("Chào " + tenKH + "\n"
                    + "Hy vọng bạn đã có trải nghiệm tốt ở cửa hàng của chúng tôi!" + "\n"
                    + "Tổng cộng hóa đơn : " + thanhToan + "\n"
                    + "Mọi vấn đề cần hỗ trợ bạn có thể gửi yêu cầu tới : htqlsangkaito@gmail.com"); //Nội dung email bạn muốn gửi.

            // Người nhận
            email.addTo(mail); //Đia chỉ email người nhận
            email.send(); //Thực hiện gửi.
            System.err.println("Gửi thành công mail !");
            System.err.println("");
            Thread.sleep(200);
        } catch (Exception e) {
            System.out.println("Gửi không thành công !");
            System.err.println("");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void writeHoaDon(String IDHD, String IDNV, String IDKH) {
        try {
            readGioHang();
            qlsp.readFile();
            Writer wr = null;
            wr = new FileWriter(this.filename, true);
            BufferedWriter bw = new BufferedWriter(wr);
            for (int j = 0; j < listGH.size(); j++) {
                for (int i = 0; i < qlsp.list.size(); i++) {
                    if (listGH.get(j).getID().equalsIgnoreCase(qlsp.list.get(i).getID())) {
                        bw.write(IDHD + "\t" + IDNV.toUpperCase() + "\t" + IDKH.toUpperCase() + "\t" + qlsp.list.get(i).getID() + "\t" + listGH.get(j).getSL() + "\t" + qlsp.list.get(i).getDonGia() + "\t" + (listGH.get(j).getSL() * qlsp.list.get(i).getDonGia()));
                        bw.newLine();
                    }

                }
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String maHoaDon() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("_yyyy_MM_dd_hh_mm_ss");
        return ft.format(dNow);
    }
}
