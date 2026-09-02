/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package widget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.awt.image.BufferedImage;

public class PanelSiteMarkingOdontogram extends JPanel {

    private Image gambarTemplate;

    private ArrayList<ArrayList<Point>> semuaCoretan = new ArrayList<>();
    private ArrayList<Point> coretanAktif;

    public PanelSiteMarkingOdontogram() {
        gambarTemplate = new ImageIcon(
                getClass().getResource("/picture/site_marking_odontogram.jpg")
        ).getImage();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                coretanAktif = new ArrayList<>();
                coretanAktif.add(e.getPoint());
                semuaCoretan.add(coretanAktif);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (coretanAktif != null) {
                    coretanAktif.add(e.getPoint());
                    repaint();
                }
            }
        });
    }
    
    public void hapusCoretan() {
        semuaCoretan.clear();
        repaint();
    }
    
    public String getJsonCoretan() {
        Gson gson = new Gson();
        return gson.toJson(semuaCoretan);
    }
    
    public void setJsonCoretan(String json) {
        try {
            semuaCoretan.clear();

            if (json == null || json.trim().equals("") || json.equals("[]")) {
                repaint();
                return;
            }

            Gson gson = new Gson();

            Type tipeData = new TypeToken<ArrayList<ArrayList<Point>>>() {
            }.getType();

            semuaCoretan = gson.fromJson(json, tipeData);

            repaint();

        } catch (Exception e) {
            System.out.println("Gagal load coretan site marking : " + e);
            semuaCoretan.clear();
            repaint();
        }
    }

    public BufferedImage getBufferedImage() {
        int w = getWidth() > 0 ? getWidth() : 830;
        int h = getHeight() > 0 ? getHeight() : 1020;

        BufferedImage image = new BufferedImage(
                w,
                h,
                BufferedImage.TYPE_INT_RGB
        );

        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, w, h);

        if (gambarTemplate != null) {
            g2.drawImage(gambarTemplate, 0, 0, w, h, null);
        }

        //g2.setColor(Color.RED);
        g2.setColor(new Color(55, 0, 100));
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        for (ArrayList<Point> coretan : semuaCoretan) {
            for (int i = 1; i < coretan.size(); i++) {
                Point p1 = coretan.get(i - 1);
                Point p2 = coretan.get(i);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }

        g2.dispose();
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gambarTemplate != null) {
            g.drawImage(gambarTemplate, 0, 0, getWidth(), getHeight(), this);
        }

        Graphics2D g2 = (Graphics2D) g;
//        g2.setColor(Color.RED);
        g2.setColor(new Color(55, 0, 100));
        g2.setStroke(new BasicStroke(3));
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        for (ArrayList<Point> coretan : semuaCoretan) {
            for (int i = 1; i < coretan.size(); i++) {
                Point p1 = coretan.get(i - 1);
                Point p2 = coretan.get(i);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}
