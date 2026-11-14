package widget;

import java.awt.Color;
import javax.swing.JCheckBox;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;

/**
 *
 * @author dosen3
 */
public class CekBox extends JCheckBox{

    private static final long serialVersionUID = 1L;

    public CekBox(){
        super();
        setFont(new java.awt.Font("Tahoma", 0, 11));
        //setBackground(new Color(209,209,209));
        //setForeground(new Color(90,90,90));
        setBackground(new Color(250,255,245));
        setForeground(Color.BLACK);
        setFocusPainted(false);
        
        this.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(240,245,235)));
        setOpaque(true);
        setSize(WIDTH,23);
        Icon ikonCek = new IkonCek();
        setIcon(ikonCek);
        setSelectedIcon(ikonCek);
        setDisabledIcon(ikonCek);
        setDisabledSelectedIcon(ikonCek);
    }

    private static final class IkonCek implements Icon {

        private static final int UKURAN = 13;
        private static final int LENGKUNG_SUDUT = 4;
        private static final Color WARNA_BORDER = new Color(0x14B8A6);

        @Override
        public int getIconWidth() {
            return UKURAN;
        }

        @Override
        public int getIconHeight() {
            return UKURAN;
        }

        @Override
        public void paintIcon(Component komponen, Graphics g, int x, int y) {
            Graphics2D gambar = (Graphics2D) g.create();
            try {
                gambar.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                gambar.setColor(Color.WHITE);
                gambar.fillRoundRect(x, y, UKURAN - 1, UKURAN - 1,
                        LENGKUNG_SUDUT, LENGKUNG_SUDUT);

                gambar.setColor(WARNA_BORDER);
                gambar.drawRoundRect(x, y, UKURAN - 1, UKURAN - 1,
                        LENGKUNG_SUDUT, LENGKUNG_SUDUT);

                if (((JCheckBox) komponen).isSelected()) {
                    gambar.setColor(Color.BLACK);
                    gambar.setStroke(new BasicStroke(
                            1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND
                    ));
                    gambar.drawPolyline(
                            new int[]{x + 3, x + 5, x + 9},
                            new int[]{y + 6, y + 8, y + 4},
                            3
                    );
                }
            } finally {
                gambar.dispose();
            }
        }
    }
}
