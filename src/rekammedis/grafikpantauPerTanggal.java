/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rekammedis;
/**
 *
 * @author Via
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Via
 */
public class grafikpantauPerTanggal extends JDialog {
      sekuel Sequel = new sekuel();
      validasi Valid = new validasi();
      
      public grafikpantauPerTanggal(String title, String symbol) {
        // super(title);
          setTitle(title);
         JPanel chartPanel = createDemoPanel(symbol);
         
         chartPanel.setSize(screen.width,screen.height);
         setContentPane(chartPanel);       
         
         //setSize(screen.width,screen.height);
         setModal(true);
         //setUndecorated(true);
         setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
         pack();
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);         
    }
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    //nadi jam 6
    public static CategoryDataset Nadi6(String symbol) { //data grafik nilai K dan D
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, nadi from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='6' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");
                double field = rs.getDouble("nadi");
                result.addValue(field, "Nadi Jam 6", tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }
    
    //nadi jam 12
    public static CategoryDataset Nadi12(String symbol) { //data grafik nilai K dan D
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, nadi from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='12' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");
                double field = rs.getDouble("nadi");
                result.addValue(field, "Nadi Jam 12", tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }
    
    //nadi jam 18
    public static CategoryDataset Nadi18(String symbol) { //data grafik nilai K dan D
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, nadi from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='18' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");
                double field = rs.getDouble("nadi");
                result.addValue(field, "Nadi Jam 18", tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }
    
    //nadi jam 24
    public static CategoryDataset Nadi24(String symbol) { //data grafik nilai K dan D
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, nadi from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='24' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");
                double field = rs.getDouble("nadi");
                result.addValue(field, "Nadi Jam 24", tksbr);
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }

    //suhu 6
    public static CategoryDataset Suhu6(String symbol) {//grafik volume
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();            
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, suhu from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='6' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");                
                double field = rs.getDouble("suhu");
                result.addValue(field, "Suhu Jam 6", tksbr);                
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }
    
    //suhu 12
    public static CategoryDataset Suhu12(String symbol) {//grafik volume
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();            
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, suhu from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='12' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");                
                double field = rs.getDouble("suhu");
                result.addValue(field, "Suhu Jam 12", tksbr);                
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }
    
    //suhu 18
    public static CategoryDataset Suhu18(String symbol) {//grafik volume
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();            
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, suhu from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='18' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");                
                double field = rs.getDouble("suhu");
                result.addValue(field, "Suhu Jam 18", tksbr);                
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }
    
    //suhu 24
    public static CategoryDataset Suhu24(String symbol) {//grafik volume
        DefaultCategoryDataset result = new DefaultCategoryDataset();
        try {
            Statement stat = koneksiDB.condb().createStatement();            
            ResultSet rs = stat.executeQuery("select date_format(tgl_pantau,'%d/%m/%Y') tgl, suhu from pemantauan_harian_24jam "
                    + "where " + symbol + " and jam='24' order by tgl_pantau");
            while (rs.next()) {
                String tksbr = rs.getString("tgl");                
                double field = rs.getDouble("suhu");
                result.addValue(field, "Suhu Jam 24", tksbr);                
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        return result;
    }

         private static JFreeChart createChart(String symbol) {
            //DATA NADI ------------------------------------------------
            //nadi 6
             CategoryDataset nadi_ds6 = Nadi6(symbol);
             NumberAxis nadi_ra6 = new NumberAxis("Nadi Jam 6");
             nadi_ra6.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             
             LineAndShapeRenderer nadi_r6 = new LineAndShapeRenderer();
             nadi_r6.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot nadi_s6 = new CategoryPlot(nadi_ds6, null, nadi_ra6, nadi_r6);
             nadi_s6.setDomainGridlinesVisible(true);
             
             //nadi 12
             CategoryDataset nadi_ds12 = Nadi12(symbol);
             NumberAxis nadi_ra12 = new NumberAxis("Nadi Jam 12");
             nadi_ra12.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             
             LineAndShapeRenderer nadi_r12 = new LineAndShapeRenderer();
             nadi_r12.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot nadi_s12 = new CategoryPlot(nadi_ds12, null, nadi_ra12, nadi_r12);
             nadi_s12.setDomainGridlinesVisible(true);
             
             //nadi 18
             CategoryDataset nadi_ds18 = Nadi18(symbol);
             NumberAxis nadi_ra18 = new NumberAxis("Nadi Jam 18");
             nadi_ra18.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             
             LineAndShapeRenderer nadi_r18 = new LineAndShapeRenderer();
             nadi_r18.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot nadi_s18 = new CategoryPlot(nadi_ds18, null, nadi_ra18, nadi_r18);
             nadi_s18.setDomainGridlinesVisible(true);
             
             //nadi 24
             CategoryDataset nadi_ds24 = Nadi24(symbol);
             NumberAxis nadi_ra24 = new NumberAxis("Nadi Jam 24");
             nadi_ra24.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             
             LineAndShapeRenderer nadi_r24 = new LineAndShapeRenderer();
             nadi_r24.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot nadi_s24 = new CategoryPlot(nadi_ds24, null, nadi_ra24, nadi_r24);
             nadi_s24.setDomainGridlinesVisible(true);
             //DATA NADI SAMPAI SINI ------------------------------------------------
             
             //DATA SUHU ------------------------------------------------
             //suhu 6
             CategoryDataset suhu_ds6 = Suhu6(symbol);
             NumberAxis suhu_ra6 = new NumberAxis("Suhu Jam 6");
             suhu_ra6.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

             LineAndShapeRenderer suhu_r6 = new LineAndShapeRenderer();
             suhu_r6.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot suhu_s6 = new CategoryPlot(suhu_ds6, null, suhu_ra6, suhu_r6);
             suhu_s6.setDomainGridlinesVisible(true);
             
             //suhu 12
             CategoryDataset suhu_ds12 = Suhu12(symbol);
             NumberAxis suhu_ra12 = new NumberAxis("Suhu Jam 12");
             suhu_ra12.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

             LineAndShapeRenderer suhu_r12 = new LineAndShapeRenderer();
             suhu_r12.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot suhu_s12 = new CategoryPlot(suhu_ds12, null, suhu_ra12, suhu_r12);
             suhu_s12.setDomainGridlinesVisible(true);
             
             //suhu 18
             CategoryDataset suhu_ds18 = Suhu18(symbol);
             NumberAxis suhu_ra18 = new NumberAxis("Suhu Jam 18");
             suhu_ra18.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

             LineAndShapeRenderer suhu_r18 = new LineAndShapeRenderer();
             suhu_r18.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot suhu_s18 = new CategoryPlot(suhu_ds18, null, suhu_ra18, suhu_r18);
             suhu_s18.setDomainGridlinesVisible(true);
             
             //suhu 24
             CategoryDataset suhu_ds24 = Suhu24(symbol);
             NumberAxis suhu_ra24 = new NumberAxis("Suhu Jam 24");
             suhu_ra24.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

             LineAndShapeRenderer suhu_r24 = new LineAndShapeRenderer();
             suhu_r24.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
             
             CategoryPlot suhu_s24 = new CategoryPlot(suhu_ds24, null, suhu_ra24, suhu_r24);
             suhu_s24.setDomainGridlinesVisible(true);
             //DATA SUHU SAMPAI SINI ------------------------------------------------

             CategoryAxis domainAxis = new CategoryAxis("Grafik PerTanggal");
             CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
//             CombinedCategoryPlot plot = new CombinedCategoryPlot(
//                     domainAxis, new NumberAxis("Range"));
             plot.add(nadi_s6, 1);
             plot.add(nadi_s12, 1);
             plot.add(nadi_s18, 1);
             plot.add(nadi_s24, 1);
             plot.add(suhu_s6, 1);
             plot.add(suhu_s12, 1);
             plot.add(suhu_s18, 1);
             plot.add(suhu_s24, 1);

             JFreeChart result = new JFreeChart(
                     "",
                     new Font("Tahoma", Font.PLAIN,7 ), plot, true);
             return result;
         }

         public static JPanel createDemoPanel(String symbol) {
             JFreeChart chart = createChart(symbol);
             return new ChartPanel(chart);
         }

         /**
          * Starting point for the demonstration application.
          *
          * @param args  ignored.
          */

//        public static void main(String args[]) {
//        //        String title = "test Combined Category Plot Demo 1";
//        cocografik demo = new cocografik("aali");
//        JFrame v = new JFrame(title);
//        v.add(demo);
//        v.setBackground(Color.BLUE);
//        v.setSize(new Dimension(1200, 700));
//        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
//        v.setVisible(true);
//    }
//           public static void main(String[] args) {
//             String title = "Combined Category Plot Demo ";
//             CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
//             demo.pack();
////             RefineryUtilities.centerFrameOnScreen(demo);
//             demo.setVisible(true);
//
//         }
}

