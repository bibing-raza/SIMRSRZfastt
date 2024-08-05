/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrskhanza;

import fungsi.sekuel;
import java.awt.Toolkit;
import usu.widget.util.WidgetUtilities;


/**
 *
 * @author khanzasoft
 */
public class SIMRSKhanza {
    private static sekuel Sequel = new sekuel();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WidgetUtilities.invokeLater(() -> {
           frmUtama utama=frmUtama.getInstance();
           utama.isWall();
           //utama.setIconImage(new javax.swing.ImageIcon( getClass(). getResource("/picture/home.PNG")).getImage());
           utama.setVisible(true);
           utama.lbl_update.setText("Modified by. UNIT SIMRS RAZA - Vs. " + Sequel.cariIsi("select versi_update from history_update order by kode desc limit 1") + " [Activated]");
           utama.footer_lbl_update.setText(" Didesain & dibuat oleh Khanza.Soft Media - vs. " + Sequel.cariIsi("select versi_update from history_update order by kode desc limit 1") + "");
       });

//        DlgKiosK utama =new DlgKiosK(null, true);
//        utama.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//        utama.setVisible(true);
//        utama.cekBooking();
//        utama.empttext();
//        utama.updateBooking();
        
    }
    
}
