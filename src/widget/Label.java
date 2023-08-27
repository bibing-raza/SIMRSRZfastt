package widget;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

/**
 *
 * @author usu
 */
public class Label extends usu.widget.Label {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

    public Label() {
        super();
        //setForeground(new Color(90,90,90));
        setForeground(new Color(90,120,80));
        setFont(new java.awt.Font("Tahoma", 0, 11));

        setHorizontalAlignment(RIGHT);
        setVerticalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
    }

    public void setText(LocalDate habisBerlaku) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
