package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author usu
 */
public class Button extends usu.widget.ButtonGlass {

    private static final long serialVersionUID = 1L;
    private static final int DIAMETER_SUDUT = 8;

    public Button() {
        super();
        setFont(new Font("Tahoma", Font.BOLD, 11));
        setForeground(Color.BLACK);
        setGlassColor(new Color(184, 239, 239));
        setMargin(new Insets(2, 7, 2, 7));
        setIconTextGap(4);
        setRoundRect(false);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D gambar = (Graphics2D) g.create();
        try {
            gambar.clip(new RoundRectangle2D.Double(
                    0, 0, getWidth(), getHeight(),
                    DIAMETER_SUDUT, DIAMETER_SUDUT
            ));
            super.paint(gambar);
        } finally {
            gambar.dispose();
        }
    }
}
