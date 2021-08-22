/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckData;

import java.util.Scanner;

/**
 *
 * @author Sang Kaito
 */
public class TienTe {

    public String tienTe(int n) {
        String a = "", s = "", c = String.valueOf(n);
        int dem = 0;
        for (int i = c.length() - 1; i >= 0; i--) {
            a += c.charAt(i);
            dem++;
            if (dem == 3 && i != 0) {
                a += ",";
                dem = 0;
            }
        }
        for (int i = a.length() - 1; i >= 0; i--) {
            s = s + a.charAt(i);
        }
        return s;
    }
}
