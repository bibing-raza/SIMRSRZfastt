
package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import usu.widget.glass.TextBoxGlass;

/**
 *
 * @author usu
 */
public class TextBox extends TextBoxGlass {

    private static final long serialVersionUID = 1L;
    private static final int DIAMETER_SUDUT = 8;

    public TextBox() {
        super();
        setFont(new Font("Tahoma", Font.PLAIN, 11));
        setSelectionColor(Color.WHITE);
        setSelectedTextColor(Color.RED);
        setForeground(Color.BLACK);
        setBackground(new Color(255, 255, 250));
        setHorizontalAlignment(LEFT);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 6));
        setSize(WIDTH, 23);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (isOpaque()) {
            Graphics2D latar = (Graphics2D) g.create();
            try {
                latar.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );
                latar.setColor(getBackground());
                latar.fillRoundRect(
                        0, 0, getWidth(), getHeight(),
                        DIAMETER_SUDUT, DIAMETER_SUDUT
                );
            } finally {
                latar.dispose();
            }
        }

        Graphics teks = g.create();
        try {
            getUI().paint(teks, this);
        } finally {
            teks.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D garis = (Graphics2D) g.create();
        try {
            garis.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            
            garis.setColor(new Color(0x14B8A6));
//            garis.setColor(isEnabled() ? Color.BLACK : Color.GRAY);
            garis.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1,
                    DIAMETER_SUDUT, DIAMETER_SUDUT);
        } finally {
            garis.dispose();
        }
    }
}
