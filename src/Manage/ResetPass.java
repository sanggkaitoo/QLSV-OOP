/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manage;

import CheckData.CheckData;
import java.util.Random;
import java.util.Scanner;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Sang Kaito
 */
public class ResetPass {

    public void Reset() {
        QuanLyNhanVien ql = new QuanLyNhanVien();
        int ma = random();
        String ID, mail = null;
        CheckData check = new CheckData();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập id bạn muốn đặt lại mật khẩu : ");
            ID = sc.nextLine();
            if (check.CheckExistNV(ID)) {

                do {
                    System.out.print("Nhập email của " + ID.toUpperCase() + ": ");
                    mail = sc.nextLine();

                    if (check.CheckExistMail(ID, mail)) {
                        sendmail(mail, ma);
                        int ma1;
                        do {
                            System.out.print("Nhập mã gồm 6 chữ số trong email mà bạn nhận được : ");
                            ma1 = sc.nextInt();
                            if (ma1 == ma) {
                                ql.repairPass(ID);
                            } else {
                                System.out.println("");
                                System.err.println("Bạn đã nhập sai mã !");
                                System.out.println("");
                            }
                        } while (ma1 != ma);

                    }
                } while (check.CheckEmail(mail) == true || !check.CheckExistMail(ID, mail));
            }
        } while (!check.CheckExistNV(ID));

    }

    public void sendmail(String mail, int ma) {
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
            email.setSubject("ĐẶT LẠI MẬT KHẨU"); //Tiêu đề khi gửi email

            // Nội dung email
            email.setMsg("Chào " + mail + "\n" +
                         ma + " là mã xác nhận quên mật khẩu của bạn." + "\n" +
                         "Nếu không phải bạn yêu cầu thay đổi mật khẩu, vui lòng bỏ qua mail này." + "\n" +
                         "Mọi vấn đề cần hỗ trợ bạn có thể gửi yêu cầu tới : sanggkaitoo@gmail.com"); //Nội dung email bạn muốn gửi.

            // Người nhận
            email.addTo(mail); //Đia chỉ email người nhận
            email.send(); //Thực hiện gửi.
            System.err.println("Gửi thành công ! Vui lòng kiểm tra email.");
            System.err.println("");
        } catch (Exception e) {
            System.out.println("Gửi không thành công !");
            System.err.println("");
        }
    }

    public static int random() {
        Random rd = new Random();
        int number = (100000 + rd.nextInt(99999));
        return number;
    }
}
