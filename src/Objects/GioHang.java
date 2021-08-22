/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Sang Kaito
 */
public class GioHang {
    private String ID;
    private int SL;

    public GioHang() {
    }

    public GioHang(String ID, int SL) {
        this.ID = ID;
        this.SL = SL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getSL() {
        return SL;
    }

    public void setSL(int SL) {
        this.SL = SL;
    }
    
    
}
