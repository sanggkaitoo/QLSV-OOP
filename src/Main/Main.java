/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sang Kaito
 */
public class Main {

    public static void main(String[] args) {
        try {
            Menu inMenu = new Menu();
            inMenu.MenuMain();
        } catch (Exception e) {
            System.err.println("Fail " + e.getMessage());
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
