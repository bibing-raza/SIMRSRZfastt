package widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import usu.widget.glass.ViewPortGlass;

/**
 *
 * @author usu
 */
public class ScrollPane extends JScrollPane {

    private static final Color WARNA_BORDER = new Color(0x14B8A6);
    private static final int DIAMETER_SUDUT = 8;

    public ScrollPane() {
        super();
        setViewport(new ViewPortGlass());
        setOpaque(false);
        setBackground(Color.WHITE);

        setBorder(new LineBorder(WARNA_BORDER, 1, true) {
            @Override
            public void paintBorder(Component komponen, Graphics g,
                    int x, int y, int lebar, int tinggi) {
                Graphics2D garis = (Graphics2D) g.create();
                try {
                    garis.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON
                    );
                    garis.setColor(WARNA_BORDER);
                    garis.drawRoundRect(
                            x, y, lebar - 1, tinggi - 1,
                            DIAMETER_SUDUT, DIAMETER_SUDUT
                    );
                } finally {
                    garis.dispose();
                }
            }
        });
    }
}
