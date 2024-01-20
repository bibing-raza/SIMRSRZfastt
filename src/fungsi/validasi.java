/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import uz.ncipro.calendar.JDateTimePicker;
import widget.Tanggal;
import java.sql.*;
import java.time.LocalDate;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import net.sf.jasperreports.engine.JasperExportManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import widget.ComboBox;
import widget.TextArea;

/**
 *
 * @author Owner
 */
public final class validasi {
    private int a, j, i, result = 0,cekPrint=0;
    private String s, s1, auto, host = "", PEMBULATANHARGAOBAT = "";
    private final Connection connect = koneksiDB.condb();
    private final sekuel sek = new sekuel();
    private final java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
    private final DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###");
    private final DecimalFormat df4 = new DecimalFormat("###,###,###,###,###,###,###.#################");
    private final DecimalFormat df5 = new DecimalFormat("###,###,###,###,###,###,###.##");
    private final DecimalFormat df3 = new DecimalFormat("######");
    private final DecimalFormat df7 = new DecimalFormat("######.#");
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private final Calendar now = Calendar.getInstance();
    private final int year = (now.get(Calendar.YEAR));
    private static final Properties prop = new Properties();
    private final sekuel Sequel = new sekuel();
    private static String cekNmPrwtn = "", cekNmPtgs = "", cekKdPtgs = "", cek_string = "", ipPrint = "";
    private File file;
    private boolean status = true;

    public validasi() {
        super();
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            PEMBULATANHARGAOBAT = prop.getProperty("PEMBULATANHARGAOBAT");
            ipPrint = prop.getProperty("IPPRINTER");
        } catch (Exception e) {
            PEMBULATANHARGAOBAT = "no";
        }
    };

    public void autoNomer(DefaultTableModel tabMode, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        s = Integer.toString(tabMode.getRowCount() + 1);
        j = s.length();
        s1 = "";
        for (i = 1; i <= pnj - j; i++) {
            s1 = s1 + "0";
        }
        teks.setText(strAwal + s1 + s);
    }
    
    public void autoNomerDokter(String tabel, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement("select * from " + tabel);
            try {
                rs = ps.executeQuery();
                rs.last();
                s = Integer.toString(rs.getRow() + 2);
                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText(strAwal + s1 + s);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer(String tabel, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement("select * from " + tabel);
            try {
                rs = ps.executeQuery();
                rs.last();
                s = Integer.toString(rs.getRow() + 1);
                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText(strAwal + s1 + s);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer2(String sql, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                rs.last();
                s = Integer.toString(rs.getRow() + 1);
                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText(strAwal + s1 + s);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer3(String sql, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                s = "1";
                while (rs.next()) {
                    s = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
                }

                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText(strAwal + s1 + s);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer4(String sql, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                s = "1";
                while (rs.next()) {
                    s = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
                }

                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText((strAwal + s1 + s).substring(4, 6) + (strAwal + s1 + s).substring(2, 4) + (strAwal + s1 + s).substring(0, 2));
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer5(String sql, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                s = "1";
                while (rs.next()) {
                    s = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
                }

                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText((strAwal + s1 + s).substring(2, 4) + (strAwal + s1 + s).substring(0, 2) + (strAwal + s1 + s).substring(4, 6));
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer6(String sql, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                s = "1";
                while (rs.next()) {
                    s = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
                }

                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText(s1 + s + strAwal);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void autoNomer7(String sql, String strAwal, Integer pnj, javax.swing.JTextField teks) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                s = "1";
                while (rs.next()) {
                    s = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
                }

                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                teks.setText(s1 + s + strAwal);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public String autoNomer(String tabel, String strAwal, Integer pnj) {
        try {
            auto = "";
            ps = connect.prepareStatement("select * from " + tabel);
            try {
                rs = ps.executeQuery();
                rs.last();
                s = Integer.toString(rs.getRow() + 1);
                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                auto = strAwal + s1 + s;
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return auto;
    }

    public String autoNomer3(String sql, String strAwal, Integer pnj) {
        try {
            auto = "";
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                s = "1";
                while (rs.next()) {
                    s = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
                }

                j = s.length();
                s1 = "";
                for (i = 1; i <= pnj - j; i++) {
                    s1 = s1 + "0";
                }
                auto = strAwal + s1 + s;
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return auto;
    }

    public void editTable(DefaultTableModel tabMode, String table, String field_acuan, JTextField nilai_field, String update) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        } else if (nilai_field.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        } else if (!nilai_field.getText().trim().equals("")) {
            sek.mengedit(table, field_acuan + "='" + nilai_field.getText() + "'", update);
        }
    }

    public void editTable(DefaultTableModel tabMode, String table, String field_acuan, String nilai_field, String update, int i, String[] a) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        } else if (nilai_field.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        } else if (!nilai_field.trim().equals("")) {
            sek.mengedit(table, field_acuan + "=" + nilai_field, update, i, a);
        }
    }

    public void editTable(DefaultTableModel tabMode, String table, String field_acuan, JComboBox nilai_field, String update) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        } else if (nilai_field.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        } else if (nilai_field.getSelectedItem() != "") {
            sek.mengedit(table, field_acuan + "='" + nilai_field.getSelectedItem() + "'", update);

        }
    }

    public void editTable(DefaultTableModel tabMode, String table, String field_acuan, JLabel nilai_field, String update) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        } else if (nilai_field.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal mengedit. Pilih dulu data yang mau diedit.\nKlik data pada table untuk memilih...!!!!");
        } else if (!nilai_field.getText().trim().equals("")) {
            sek.mengedit(table, field_acuan + "='" + nilai_field.getText() + "'", update);

        }
    }

    public void fillData(DefaultTableModel model, JTable table, File file) {
        try {
            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0);
            model = (DefaultTableModel) table.getModel();

            for (i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            for (i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 1,
                            model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (IOException | WriteException ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    public void hapusTable(DefaultTableModel tabMode, JTextField nilai_field, String table, String field) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        } else if (nilai_field.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        } else if (!nilai_field.getText().trim().equals("")) {
            sek.meghapus(table, field, nilai_field.getText());

        }
    }

    public void hapusTable(DefaultTableModel tabMode, JComboBox nilai_field, String table, String field) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        } else if (nilai_field.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        } else if (nilai_field.getSelectedItem() != "") {
            String data = nilai_field.getSelectedItem().toString();
            sek.meghapus(table, field, data);
        }
    }

    public void loadCombo(JComboBox cmb, String field, String table) {
        cmb.removeAllItems();
        try {
            ps = connect.prepareStatement("select " + field + " from " + table + " order by " + field);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    String item = rs.getString(1);
                    cmb.addItem(item);
                    a++;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void LoadTahun(JComboBox cmb) {
        cmb.removeAllItems();
        for (i = year; i >= 1980; i--) {
            cmb.addItem(i);
        }
        cmb.setSelectedItem(year);
    }

    public void LoadTahunAkd(JComboBox cmb) {
        cmb.removeAllItems();
        for (i = 1950; i <= year; i++) {
            cmb.addItem(i + "1");
            cmb.addItem(i + "2");
        }
        cmb.setSelectedItem(year + "1");
    }

    @SuppressWarnings("empty-statement")
    public void MyReport(String reportName, String reportDirName, String judul, String qry) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jasper berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];

        try {
            try (Statement stm = connect.createStatement()) {
                Map<String, Object> parameters = new HashMap<>();
                ps = connect.prepareStatement(qry);
                try {

//                    String namafile = "./" + reportDirName + "/" + reportName;
//                    File reportfile = new File(namafile);
//
//                    JRDesignQuery newQuery = new JRDesignQuery();
//                    newQuery.setText(qry);
//                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
//                    jasperDesign.setQuery(newQuery);
//
//                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
//                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
//
//                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                    jasperViewer.setTitle(judul);
//                    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//                    jasperViewer.setSize(screen.width - 50, screen.height - 50);
//                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//                    jasperViewer.setLocationRelativeTo(null);
//                    jasperViewer.setVisible(true);
                    String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width - 50, screen.height - 50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("empty-statement")
    public void MyReport2(String reportName, String reportDirName, String judul, String qry) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jasper berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];

        try {
            try (Statement stm = connect.createStatement()) {
                Map<String, Object> parameters = new HashMap<>();
                ps = connect.prepareStatement(qry);
                try {

//                    String namafile = "./" + reportDirName + "/" + reportName;
//                    File reportfile = new File(namafile);
//
//                    JRDesignQuery newQuery = new JRDesignQuery();
//                    newQuery.setText(qry);
//                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
//                    jasperDesign.setQuery(newQuery);
//
//                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
//                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
//
//                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                    jasperViewer.setTitle(judul);
//                    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//                    jasperViewer.setSize(screen.width - 50, screen.height - 50);
//                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//                    jasperViewer.setLocationRelativeTo(null);
//                    jasperViewer.setVisible(true);
                    String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width - 50, screen.height - 50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void MyReport(String reportName, String reportDirName, String judul, String qry, Map parameters) {
//        Properties systemProp = System.getProperties();
//
//        // Ambil current dir
//        String currentDir = systemProp.getProperty("user.dir");
//
//        File dir = new File(currentDir);
//
//        File fileRpt;
//        String fullPath = "";
//        if (dir.isDirectory()) {
//            String[] isiDir = dir.list();
//            for (String iDir : isiDir) {
//                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
//                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
//                    fullPath = fileRpt.toString();
//                    System.out.println("Found Report File at : " + fullPath);
//                } // end if
//            } // end for i
//        } // end if
//
//        // Ambil Direktori tempat file RptMaster.jasper berada
//        String[] subRptDir = fullPath.split(reportName);
//        String reportDir = subRptDir[0];
//
//        try {
//            try {
//                String namafile = "./" + reportDirName + "/" + reportName;
//                File reportfile = new File(namafile);
//
//                JRDesignQuery newQuery = new JRDesignQuery();
//                newQuery.setText(qry);
//                JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
//                jasperDesign.setQuery(newQuery);
//
//                JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
//                JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
//
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle(judul);
//                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//                jasperViewer.setSize(screen.width - 50, screen.height - 50);
//                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//                jasperViewer.setLocationRelativeTo(null);
//                jasperViewer.setVisible(true);
//            } catch (Exception rptexcpt) {
//                System.out.println("Report Can't view because : " + rptexcpt);
//                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            ps = connect.prepareStatement(qry);
            try {
                String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width - 50, screen.height - 50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void MyReport2(String reportName, String reportDirName, String judul, String qry, Map parameters) {
//        Properties systemProp = System.getProperties();
//
//        // Ambil current dir
//        String currentDir = systemProp.getProperty("user.dir");
//
//        File dir = new File(currentDir);
//
//        File fileRpt;
//        String fullPath = "";
//        if (dir.isDirectory()) {
//            String[] isiDir = dir.list();
//            for (String iDir : isiDir) {
//                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
//                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
//                    fullPath = fileRpt.toString();
//                    System.out.println("Found Report File at : " + fullPath);
//                } // end if
//            } // end for i
//        } // end if
//
//        // Ambil Direktori tempat file RptMaster.jasper berada
//        String[] subRptDir = fullPath.split(reportName);
//        String reportDir = subRptDir[0];
//
//        try {
//            try {
//                String namafile = "./" + reportDirName + "/" + reportName;
//                File reportfile = new File(namafile);
//
//                JRDesignQuery newQuery = new JRDesignQuery();
//                newQuery.setText(qry);
//                JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
//                jasperDesign.setQuery(newQuery);
//
//                JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
//                JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
//
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle(judul);
//                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//                jasperViewer.setSize(screen.width - 50, screen.height - 50);
//                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//                jasperViewer.setLocationRelativeTo(null);
//                jasperViewer.setVisible(true);
//            } catch (Exception rptexcpt) {
//                System.out.println("Report Can't view because : " + rptexcpt);
//                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }

        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            ps = connect.prepareStatement(qry);
            try {
                String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width - 50, screen.height - 50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void MyReport(String reportName, Map parameters, String title) {
        try {
            JasperViewer jasperViewer = new JasperViewer(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/" + reportName), parameters, connect), false);
            jasperViewer.setTitle(title);
            jasperViewer.setLocationRelativeTo(null);
            jasperViewer.setVisible(true);
            //JasperViewer.viewReport(JasperFillManager.fillReport(JasperCompileManager.compileReport("./report/"+reportName),parameters,connect),false);
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextField kiri, JTextField kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextField kiri, JTextField kanan, JTextField bawah) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            bawah.requestFocus();
        }
    }

    public void pindah2(java.awt.event.KeyEvent evt, JTextField kiri, JTextField kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah2(java.awt.event.KeyEvent evt, JTextField kiri, JComboBox kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextField kiri, JTextArea kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextArea kiri, JTextArea kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void textKosong(JButton teks, String pesan) {
        JOptionPane.showMessageDialog(null, "Maaf, " + pesan + " tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public void pindah(KeyEvent evt, JComboBox kiri, JTextArea kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextField kiri, JButton kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextField kiri, JButton kanan, JTextField bawah) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            bawah.requestFocus();
        }
    }

    public void pindah2(java.awt.event.KeyEvent evt, JTextField kiri, JButton kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JButton kiri, JTextField kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JButton kiri, JButton kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextField kiri, JComboBox kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JTextArea kiri, JComboBox kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JComboBox kiri, JTextField kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JComboBox kiri, JDateTimePicker kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JDateTimePicker kiri, JTextField kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }
    
    public void pindah2(KeyEvent evt, TextArea kiri, ComboBox kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JComboBox kiri, JComboBox kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(java.awt.event.KeyEvent evt, JComboBox kiri, JButton kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JButton kiri, JComboBox kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JTextArea kiri, JButton kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void pindah(KeyEvent evt, JTextArea kiri, JTextField kanan) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            kanan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kiri.requestFocus();
        }
    }

    public void panggilUrl(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
            if (os.contains("win")) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/" + url);
            } else if (os.contains("mac")) {
                rt.exec("open " + "http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/" + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                String[] browsers = {"x-www-browser", "epiphany", "firefox", "mozilla", "konqueror", "chrome", "chromium", "netscape", "opera", "links", "lynx", "midori"};
                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuilder cmd = new StringBuilder();
                for (i = 0; i < browsers.length; i++) {
                    cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append("http://").append((host) + ":" + prop.getProperty("PORTWEB")).append("/").append(prop.getProperty("HYBRIDWEB")).append("/").append(url).append("\" ");
                }
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            }
        } catch (Exception e) {
            System.out.println("Notif Browser : " + e);
        }
    }
    
    public void panggilUrlRAZA(String url) {
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();
        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            host = Sequel.decXML(prop.getProperty("HOSTraza"), prop.getProperty("KEY"));
            if (os.contains("win")) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://" + host + "/" + url);
            } else if (os.contains("mac")) {
                rt.exec("open " + "http://" + host + "/" + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                String[] browsers = {"x-www-browser", "epiphany", "firefox", "mozilla", "konqueror", "chrome", "chromium", "netscape", "opera", "links", "lynx", "midori"};
                // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                StringBuilder cmd = new StringBuilder();
                for (i = 0; i < browsers.length; i++) {
                    cmd.append(i == 0 ? "" : " || ").append(browsers[i]).append(" \"").append("http://").append(host).append(url).append("\" ");
                }
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            }
        } catch (Exception e) {
            System.out.println("Notif Browser : " + e);
        }
    }

    public void printUrl(String url) throws URISyntaxException {
        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
            desktop.print(new File(new java.net.URI("http://" + host + "/" + url)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void SetTgl(DefaultTableModel tabMode, JTable table, JDateTimePicker dtp, int i) {
        j = table.getSelectedRow();
        try {
            Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tabMode.getValueAt(j, i).toString());
            dtp.setDate(dtpa);
        } catch (ParseException ex) {
            dtp.setDate(new Date());
        }
    }

    public String SetTgl(String original) {
        s = "";
        try {
            s = original.substring(6, 10) + "-" + original.substring(3, 5) + "-" + original.substring(0, 2);
        } catch (Exception e) {
        }
        return s;
    }

    public String SetTgl3(String original) {
        s = "";
        try {
            s = original.substring(8, 10) + "-" + original.substring(5, 7) + "-" + original.substring(0, 4);
        } catch (Exception e) {
        }
        return s;
    }

    public void SetTgl(JDateTimePicker dtp, String tgl) {
        try {
            Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
            dtp.setDate(dtpa);
        } catch (ParseException ex) {
            dtp.setDate(new Date());
        }
    }

    public void SetTgl2(JDateTimePicker dtp, String tgl) {
        try {
            Date dtpa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tgl);
            dtp.setDate(dtpa);
        } catch (ParseException ex) {
            dtp.setDate(new Date());
        }
    }

    public Date SetTgl2(String tgl) {
        try {
            Date dtpa = new SimpleDateFormat("yyyy-MM-dd").parse(tgl);
            return dtpa;
        } catch (ParseException ex) {
            return new Date();
        }
    }

    public void textKosong(JTextField teks, String pesan) {
        JOptionPane.showMessageDialog(null, "Maaf, " + pesan + " tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public void textKosong(JTextArea teks, String pesan) {
        JOptionPane.showMessageDialog(null, "Maaf, " + pesan + " tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public void tabelKosong(DefaultTableModel tabMode) {
        j = tabMode.getRowCount();
        for (i = 0; i < j; i++) {
            tabMode.removeRow(0);
        }
    }

    public void textKosong(JComboBox teks, String pesan) {
        JOptionPane.showMessageDialog(null, "Maaf, " + pesan + " tidak boleh kosong...!!!");
        teks.requestFocus();
    }

    public String SetAngka(double nilai) {
        return df2.format(nilai);
    }

    public String SetAngka3(double nilai) {
        return df4.format(nilai);
    }

    public String SetAngka4(double nilai) {
        return df5.format(nilai);
    }

    public String SetAngka2(double nilai) {
        return df3.format(nilai);
    }

    public double SetAngka(String txt) {
        double x;
        if (txt.equals("")) {
            x = 0;
        } else {
            x = Double.parseDouble(txt);
        }
        return x;
    }

    public double roundUp(double number, int multiple) {
        if (PEMBULATANHARGAOBAT.equals("yes")) {
            result = multiple;
            if (number % multiple == 0) {
                return (int) number;
            }

            if (number % multiple != 0) {
                int division = (int) ((number / multiple) + 1);
                result = division * multiple;
            }
            return result;
        } else {
            return Math.round(number);
        }

    }

    public String SetAngka3(String angka1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String MaxTeks(String original, int max) {
        if (original.length() >= max) {
            s = original.substring(0, (max - 1));
        } else {
            s = original;
        }
        return original;
    }

    @SuppressWarnings("empty-statement")
    public void AutoPrintToImage(String reportName, String reportDirName, String judul, String qry, Map parameters, String folder, String nmFile) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jasper berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];

//        try {
//            try (Statement stm = connect.createStatement()) {
//                try {
//
//                    String namafile = "./" + reportDirName + "/" + reportName;
//                    File reportfile = new File(namafile);
//
//                    JRDesignQuery newQuery = new JRDesignQuery();
//                    newQuery.setText(qry);
//                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
//                    jasperDesign.setQuery(newQuery);
//
//                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
//                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
//
//                    //untuk langsung mencetak tanpa print preview
////                    JasperPrintManager.printReport(jasperPrint, false);
////                    JasperPrintManager.printPageToImage(jasperPrint, 0, 1);
////                    Image image = JasperPrintManager.printPageToImage(jasperPrint, 0, 2.0f);
//                    final String extension = "bmp";
//                    final float zoom = 5f;
//                    String fileName = nmFile;
//
//                    OutputStream out = new FileOutputStream(folder + fileName + "_p" + "." + extension);
//                    BufferedImage image = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, zoom);
//                    ImageIO.write(image, extension, out); //write image to file\
//                    JOptionPane.showMessageDialog(null, "Report Berhasil Disimpan");
//
//                } catch (Exception rptexcpt) {
//                    System.out.println("Report Can't view because : " + rptexcpt);
//                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        
        try {
            ps = connect.prepareStatement(qry);
            try {
                String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);
                
                final String extension = "bmp";
                    final float zoom = 5f;
                    String fileName = nmFile;

                    OutputStream out = new FileOutputStream(folder + fileName + "_p" + "." + extension);
                    BufferedImage image = (BufferedImage) JasperPrintManager.printPageToImage(jasperPrint, 0, zoom);
                    ImageIO.write(image, extension, out); //write image to file\
                    JOptionPane.showMessageDialog(null, "Report Berhasil Disimpan");
                
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void AutoPrint(String reportName, String reportDirName, String judul, String qry, Map parameters) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jasper berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];

//        try {
//            try (Statement stm = connect.createStatement()) {
//
//                try {
//
//                    String namafile = "./" + reportDirName + "/" + reportName;
//                    File reportfile = new File(namafile);
//
//                    JRDesignQuery newQuery = new JRDesignQuery();
//                    newQuery.setText(qry);
//                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
//                    jasperDesign.setQuery(newQuery);
//
//                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
//                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
//
//                    //untuk langsung mencetak tanpa print preview
//                    JasperPrintManager.printReport(jasperPrint, false);
//                    System.out.println("Sukses ");
////                    JOptionPane.showMessageDialog(null, "Report Berhasil Dicetak");
//
//                } catch (Exception rptexcpt) {
//                    System.out.println("Report Can't view because : " + rptexcpt);
////                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        
        try {
            ps = connect.prepareStatement(qry);
            try {
                String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);
                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);
                
                //untuk langsung mencetak tanpa print preview
                    JasperPrintManager.printReport(jasperPrint, false);
                    System.out.println("Sukses ");
//                    JOptionPane.showMessageDialog(null, "Report Berhasil Dicetak");

                
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void cetakQr(String txt, String folder, String fileName) {
        String myCodeText = txt;
        String filePath = folder + fileName;
        int size = 250;
        String fileType = "jpg";
        File myFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1);
            /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            try {
                ImageIO.write(image, fileType, myFile);
                System.out.println("QR Code sudah berhasil di generate..!!!");
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Files.createDirectories(Paths.get(folder));
                    cetakQr(myCodeText, folder, fileName);
                } catch (Exception f) {
                }
                
            }

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    
    public void SetTgl(Tanggal DTPCari3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void MyReportqry(String reportName, String reportDirName, String judul, String qry, Map parameters) {
//        Properties systemProp = System.getProperties();
//
//        // Ambil current dir
//        String currentDir = systemProp.getProperty("user.dir");
//
//        File dir = new File(currentDir);
//
//        File fileRpt;
//        String fullPath = "";
//        if (dir.isDirectory()) {
//            String[] isiDir = dir.list();
//            for (String iDir : isiDir) {
//                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
//                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
//                    fullPath = fileRpt.toString();
//                    System.out.println("Found Report File at : " + fullPath);
//                } // end if
//            } // end for i
//        } // end if
//
//        try {
//            ps = connect.prepareStatement(qry);
//            try {
//                String namafile = "./" + reportDirName + "/" + reportName;
//                rs = ps.executeQuery();
//                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);
//
//                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);
//
//                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
//                jasperViewer.setTitle(judul);
//                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
//                jasperViewer.setSize(screen.width - 50, screen.height - 50);
//                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
//                jasperViewer.setLocationRelativeTo(null);
//                jasperViewer.setVisible(true);
//            } catch (Exception rptexcpt) {
//                System.out.println("Report Can't view because : " + rptexcpt);
//                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
//            } finally {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (ps != null) {
//                    ps.close();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            ps = connect.prepareStatement(qry);
            try {
                String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);

                JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                jasperViewer.setTitle(judul);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                jasperViewer.setSize(screen.width - 50, screen.height - 50);
                jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                jasperViewer.setLocationRelativeTo(null);
                jasperViewer.setVisible(true);
            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("empty-statement")
    public void MyReport(String reportName, String reportDirName, String judul, Map parameters) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            try (Statement stm = connect.createStatement()) {
                try {

                    String namafile = "./" + reportDirName + "/" + reportName;
                    JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, connect);

                    JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
                    jasperViewer.setTitle(judul);
                    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                    jasperViewer.setSize(screen.width - 50, screen.height - 50);
                    jasperViewer.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
                    jasperViewer.setLocationRelativeTo(null);
                    jasperViewer.setVisible(true);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @SuppressWarnings("empty-statement")
    public void MyReportPDF(String reportName, String reportDirName, String judul, Map parameters) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        try {
            try (Statement stm = connect.createStatement()) {
                try {
                    File f = new File("./" + reportDirName + "/" + reportName.replaceAll("jasper", "pdf"));
                    String namafile = "./" + reportDirName + "/" + reportName;
                    JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, connect);
                    JasperExportManager.exportReportToPdfFile(jasperPrint, "./" + reportDirName + "/" + reportName.replaceAll("jasper", "pdf"));
                    Desktop.getDesktop().open(f);
                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void MyReportToExcel(String qry1, String nm_filenya) {
        try {
            ps1 = connect.prepareStatement(qry1);
            rs1 = ps1.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            writeHeaderLine(rs1, sheet);

            writeDataLines(rs1, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(nm_filenya + ".xlsx");
            workbook.write(outputStream);
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
    }
    

    private void writeHeaderLine(ResultSet result, XSSFSheet sheet) throws SQLException {
        // write header line containing column names
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        // exclude the first column which is the ID field
        for (int i = 1; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnLabel(i);
            Cell headerCell = headerRow.createCell(i - 1);
            headerCell.setCellValue(columnName);
        }
    }

    private void writeDataLines(ResultSet result, XSSFWorkbook workbook, XSSFSheet sheet)
            throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        int rowCount = 1;

        while (result.next()) {
            Row row = sheet.createRow(rowCount++);

            for (int i = 1; i <= numberOfColumns; i++) {
                Object valueObject = result.getObject(i);

                Cell cell = row.createCell(i - 1);

                if (valueObject instanceof Boolean) {
                    cell.setCellValue((Boolean) valueObject);
                } else if (valueObject instanceof Double) {
                    cell.setCellValue((double) valueObject);
                } else if (valueObject instanceof Float) {
                    cell.setCellValue((float) valueObject);
                } else if (valueObject instanceof Long) {
                    cell.setCellValue((long) valueObject);
                } else if (valueObject instanceof Date) {
                    cell.setCellValue((Date) valueObject);
                    formatDateCell(workbook, cell);
                } else {
                    cell.setCellValue((String) valueObject);
                }

            }

        }
    }

    private void formatDateCell(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }
    
    public void AutoPrintMulti(String reportName, String reportDirName, String judul, String qry, Map parameters, String printernama) {
         cekPrint = 0;
        Properties systemProp = System.getProperties();
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        
        for (PrintService printer : printServices)
        if(printernama.equals(printer.getName())){
            cekPrint = 1;
        }
        

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");
        File dir = new File(currentDir);
        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jasper berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];
        try {
            InetAddress iAddress = InetAddress.getLocalHost();
                String currentIp = iAddress.getHostAddress();
                
                if(!currentIp.equals(ipPrint)){
                    if(cekPrint == 0){
                        printernama =  "\\\\"+ipPrint+"\\"+printernama;
                    }
                   
                }
                
            ps = connect.prepareStatement(qry);
            try {
                String namafile = "./" + reportDirName + "/" + reportName;
                rs = ps.executeQuery();
                JRResultSetDataSource rsdt = new JRResultSetDataSource(rs);

                JasperPrint jasperPrint = JasperFillManager.fillReport(namafile, parameters, rsdt);

                //untuk langsung mencetak tanpa print preview
                PrintReportToPrinter(jasperPrint, printernama);
                System.out.println("Sukses ");
//                    JOptionPane.showMessageDialog(null, "Report Berhasil Dicetak");

            } catch (Exception rptexcpt) {
                System.out.println("Report Can't view because : " + rptexcpt);
                JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void PrintReportToPrinter(JasperPrint jp, String pName) throws JRException {
        // TODO Auto-generated method stub
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
//    printRequestAttributeSet.add(new Copies(1));

        PrinterName printerName = new PrinterName(pName, null); //gets printer 

        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
        printServiceAttributeSet.add(printerName);

        JRPrintServiceExporter exporter = new JRPrintServiceExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
        exporter.exportReport();
    }
    
    public String openDialog(){
        String s;
        s = "";
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        j.setMultiSelectionEnabled(true);
        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            // get the selelcted files 
            File files[] = j.getSelectedFiles();            
            
            s = j.getSelectedFile().getAbsolutePath();
           
        } else {
            s = "the user cancelled the operation";
        }
        return s ;
    }
    
    public String SetTglINDONESIA(String original) {
        s = "";
        try {
            if (original.substring(5, 7).equals("1") || original.substring(5, 7).equals("01")) {
                s = original.substring(8, 10) + " Januari " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("2") || original.substring(5, 7).equals("02")) {
                s = original.substring(8, 10) + " Februari " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("3") || original.substring(5, 7).equals("03")) {
                s = original.substring(8, 10) + " Maret " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("4") || original.substring(5, 7).equals("04")) {
                s = original.substring(8, 10) + " April " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("5") || original.substring(5, 7).equals("05")) {
                s = original.substring(8, 10) + " Mei " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("6") || original.substring(5, 7).equals("06")) {
                s = original.substring(8, 10) + " Juni " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("7") || original.substring(5, 7).equals("07")) {
                s = original.substring(8, 10) + " Juli " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("8") || original.substring(5, 7).equals("08")) {
                s = original.substring(8, 10) + " Agustus " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("9") || original.substring(5, 7).equals("09")) {
                s = original.substring(8, 10) + " September " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("10")) {
                s = original.substring(8, 10) + " Oktober " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("11")) {
                s = original.substring(8, 10) + " Nopember " + original.substring(0, 4);
            }

            if (original.substring(5, 7).equals("12")) {
                s = original.substring(8, 10) + " Desember " + original.substring(0, 4);
            }
        } catch (Exception e) {
        }
        return s;
    }
    
    public void isiNmPrwtn(String a){
        validasi.cekNmPrwtn = a;
    }
    
    public static String getNmPrwtn() {
        return validasi.cekNmPrwtn;
    }
    
    public void isiNmPtgs(String a){
        validasi.cekNmPtgs = a;
    }
    
    public static String getNmPtgs() {
        return validasi.cekNmPtgs;
    }
    
    public void isiKdPtgs(String a){
        validasi.cekKdPtgs = a;
    }
    
    public static String getKdPtgs() {
        return validasi.cekKdPtgs;
    }
    
    public static String mysql_real_escape_string(String stringnya) throws Exception {
        if (stringnya == null) {
            return null;
        }

        if (stringnya.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]", "").length() < 1) {
            return stringnya;
        }

        cek_string = stringnya;
        cek_string = cek_string.replaceAll("\\\\", "\\\\\\\\");
        cek_string = cek_string.replaceAll("\\n", "\\\\n");
        cek_string = cek_string.replaceAll("\\r", "\\\\r");
        cek_string = cek_string.replaceAll("\\t", "\\\\t");
        cek_string = cek_string.replaceAll("\\00", "\\\\0");
        cek_string = cek_string.replaceAll("'", "\\\\'");
        cek_string = cek_string.replaceAll("\\\"", "\\\\\"");

        if (cek_string.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/?\\\\\"' ]", "").length() < 1) {
            return cek_string;
        }

        return cek_string;
    }
    
    public static String mysql_real_escape_stringERM(String stringnya) throws Exception {
        if (stringnya == null) {
            return null;
        }

        if (stringnya.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/? ]", "").length() < 1) {
            return stringnya;
        }

        cek_string = stringnya;
        cek_string = cek_string.replaceAll("\\\\", "\\\\\\\\");
//        cek_string = cek_string.replaceAll("\\n", "\\\\n");
        cek_string = cek_string.replaceAll("\\r", "\\\\r");
        cek_string = cek_string.replaceAll("\\t", "\\\\t");
        cek_string = cek_string.replaceAll("\\00", "\\\\0");
        cek_string = cek_string.replaceAll("'", "\\\\'");
        cek_string = cek_string.replaceAll("\\\"", "\\\\\"");

        if (cek_string.replaceAll("[a-zA-Z0-9_!@#$%^&*()-=+~.;:,\\Q[\\E\\Q]\\E<>{}\\/?\\\\\"' ]", "").length() < 1) {
            return cek_string;
        }

        return cek_string;
    }
    
    public boolean MyReportToExcelBoolean(String qry1, String nm_filenya) {
        try {
            ps1 = connect.prepareStatement(qry1);
            rs1 = ps1.executeQuery();

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            writeHeaderLine(rs1, sheet);

            writeDataLines(rs1, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(nm_filenya + ".xlsx");
            workbook.write(outputStream);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public String SetTgl4(String original){
        original=original.replaceAll("'","");
        s = "";
        try {
            s=original.substring(6,10)+original.substring(3,5)+original.substring(0,2);
        }catch (Exception e) {
        }   
        return s;
    }
    
    public String SetTglMiring(String original) {
        s = "";
        try {
            s = original.substring(6, 10) + "/" + original.substring(3, 5) + "/" + original.substring(0, 2);
        } catch (Exception e) {
        }
        return s;
    }

    public Object SetTglINDONESIA(LocalDate tgl) {
        s = "";
        String nilai = tgl.toString();
        try {
            if (nilai.substring(5, 7).equals("1") || nilai.substring(5, 7).equals("01")) {
                s = nilai.substring(8, 10) + " Januari " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("2") || nilai.substring(5, 7).equals("02")) {
                s = nilai.substring(8, 10) + " Februari " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("3") || nilai.substring(5, 7).equals("03")) {
                s = nilai.substring(8, 10) + " Maret " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("4") || nilai.substring(5, 7).equals("04")) {
                s = nilai.substring(8, 10) + " April " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("5") || nilai.substring(5, 7).equals("05")) {
                s = nilai.substring(8, 10) + " Mei " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("6") || nilai.substring(5, 7).equals("06")) {
                s = nilai.substring(8, 10) + " Juni " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("7") || nilai.substring(5, 7).equals("07")) {
                s = nilai.substring(8, 10) + " Juli " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("8") || nilai.substring(5, 7).equals("08")) {
                s = nilai.substring(8, 10) + " Agustus " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("9") || nilai.substring(5, 7).equals("09")) {
                s = nilai.substring(8, 10) + " September " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("10")) {
                s = nilai.substring(8, 10) + " Oktober " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("11")) {
                s = nilai.substring(8, 10) + " Nopember " + nilai.substring(0, 4);
            }

            if (nilai.substring(5, 7).equals("12")) {
                s = nilai.substring(8, 10) + " Desember " + nilai.substring(0, 4);
            }
        } catch (Exception e) {
        }
        return s;
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextField kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextArea kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextArea kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JTextArea kiri,JButton kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JComboBox kiri,JComboBox kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JComboBox kiri,JTextField kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public void pindah2(java.awt.event.KeyEvent evt,JComboBox kiri,JTextArea kanan){
        if(evt.getKeyCode()==KeyEvent.VK_TAB){
            kanan.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            kiri.requestFocus();
        }
    }
    
    public double SetAngka7(double nilai){        
       return Double.parseDouble(df7.format(nilai));
    }
    
    public void AutoMultiPrint(String reportName, String reportDirName, String judul, String qry, Map parameters, String printernama) {
        Properties systemProp = System.getProperties();

        // Ambil current dir
        String currentDir = systemProp.getProperty("user.dir");

        File dir = new File(currentDir);

        File fileRpt;
        String fullPath = "";
        if (dir.isDirectory()) {
            String[] isiDir = dir.list();
            for (String iDir : isiDir) {
                fileRpt = new File(currentDir + File.separatorChar + iDir + File.separatorChar + reportDirName + File.separatorChar + reportName);
                if (fileRpt.isFile()) { // Cek apakah file RptMaster.jasper ada
                    fullPath = fileRpt.toString();
                    System.out.println("Found Report File at : " + fullPath);
                } // end if
            } // end for i
        } // end if

        // Ambil Direktori tempat file RptMaster.jasper berada
        String[] subRptDir = fullPath.split(reportName);
        String reportDir = subRptDir[0];

        try {
            try (Statement stm = connect.createStatement()) {

                try {

                    String namafile = "./" + reportDirName + "/" + reportName;
                    File reportfile = new File(namafile);

                    JRDesignQuery newQuery = new JRDesignQuery();
                    newQuery.setText(qry);
                    JasperDesign jasperDesign = JRXmlLoader.load(reportfile);
                    jasperDesign.setQuery(newQuery);

                    JasperReport JRpt = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(JRpt, parameters, connect);
                    
                    //untuk langsung mencetak tanpa print preview
//                    JasperPrintManager.printReport(jasperPrint, false);
                    PrintReportToPrinter(jasperPrint, printernama);
                    System.out.println("Sukses ");
//                    JOptionPane.showMessageDialog(null, "Report Berhasil Dicetak");

                } catch (Exception rptexcpt) {
                    System.out.println("Report Can't view because : " + rptexcpt);
//                    JOptionPane.showMessageDialog(null, "Report Can't view because : " + rptexcpt);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public boolean MyReportToExcelBooleanRs(ResultSet rs2, String nm_filenya) {
        try {            

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");

            writeHeaderLine(rs2, sheet);

            writeDataLines(rs2, workbook, sheet);

            FileOutputStream outputStream = new FileOutputStream(nm_filenya + ".xlsx");
            workbook.write(outputStream);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    
    public int daysOld(String path) {
        file=new File(path);
        if (file.lastModified() < 1)
            return 0;
        return milliToDay(Calendar.getInstance().getTimeInMillis() - file.lastModified());
    }
    
    public static int milliToDay(long milli) {
        return (int) ((double) milli / (1000 * 24 * 60 * 60));
    }
    
    public boolean hapusTabletf(DefaultTableModel tabMode, JTextField nilai_field, String table, String field) {
        status = true;
        if (tabMode.getRowCount() == 0) {
            status = false;
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            nilai_field.requestFocus();
        } else if (nilai_field.getText().trim().equals("")) {
            status = false;
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus.\nKlik data pada table untuk memilih...!!!!");
        } else if (!nilai_field.getText().trim().equals("")) {
            status = sek.meghapustf(table, field, nilai_field.getText());
        }
        return status;
    }
}
  