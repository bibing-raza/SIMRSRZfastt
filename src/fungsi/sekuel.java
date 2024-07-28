/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package fungsi;

import fungsi.koneksiDB;
import fungsi.akses;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import org.apache.poi.hssf.record.formula.functions.Len;
//import org.apache.poi.hssf.record.formula.functions.Mid;
import uz.ncipro.calendar.JDateTimePicker;

/**
 *
 * @author Owner
 */
public final class sekuel {

    private javax.swing.ImageIcon icon = null;
    private javax.swing.ImageIcon iconThumbnail = null;
    private String folder, AKTIFKANTRACKSQL = koneksiDB.AKTIFKANTRACKSQL();
    private final Connection connect = koneksiDB.condb();
    private PreparedStatement ps, ps1;
    private ResultSet rs;
    private int angka = 0;
    private static int angka3 = 0;
    private double angka2 = 0;
    private String dicari = "", output = "", inputan = "", bulan = "", hari = "", romawi = "";
    private static String dicari2 = "", output2 = "", inputan2 = "";
    private char enkrip;
    private static char enkrip2;
    private Integer Panjang_Input, panjangKey, cekData = 0;
    private static Integer Panjang_Input2, panjangKey2;
    private Date tanggal = new Date();
    private boolean bool = false;
    private DecimalFormat df2 = new DecimalFormat("####");
    private static final Properties prop = new Properties();
    private ResultSet rs1;
    String[] nominal = {"", "Satu", "Dua", "Tiga", "Empat", "Lima", "Enam",
        "Tujuh", "Delapan", "Sembilan", "Sepuluh", "Sebelas"};

    public sekuel() {
        super();
    }

    public void menyimpan(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void simpanReplaceInto(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("replace into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean menyimpantf(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            return false;
        }
    }

    public boolean menyimpantf2(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            return false;
        }
    }

    public void menyimpan(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void menyimpanPesanGagalnyaDiTerminal(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                System.out.println("Gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean menyimpantf(String table, String value, String sama, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();

            if (ps != null) {
                ps.close();
            }
            return true;
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            if (e.toString().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
            }
            return false;
        }
    }

    public boolean menyimpantf3(String table, String value, String pesan, int i, String[] a) {
        bool = true;
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                if (e.toString().contains("Duplicate")) {
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + pesan + " yang sama dimasukkan sebelumnya...!");
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
                }
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
            if (e.toString().contains("Duplicate")) {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + pesan + " yang sama dimasukkan sebelumnya...!");
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Ada kesalahan Query...!");
            }
        }
        return bool;
    }

    public boolean menyimpantf2(String table, String value, String sama, int i, String[] a) {
        bool = true;
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void menyimpan(String table, String value, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2(String table, String value, int i, String[] a) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi " + table + " : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
            if (AKTIFKANTRACKSQL.equals("yes")) {
                dicari = "";
                for (angka = 1; angka <= i; angka++) {
                    dicari = dicari + "|" + a[angka - 1];
                }
            }
            SimpanTrack("insert into " + table + " values(" + dicari + ")");
        } catch (Exception e) {
        }
    }

    private void SimpanTrack(String sql) {
        if (AKTIFKANTRACKSQL.equals("yes")) {
            try {
                ps = connect.prepareStatement("insert into trackersql values(now(),?,?)");
                try {
                    ps.setString(1, sql);
                    ps.setString(2, akses.getkode());
                    ps.executeUpdate();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    public void Commit() {
        try {
            connect.commit();
        } catch (Exception e) {
        }
    }

    public void menyimpan(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();

            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
                for (angka = 1; angka <= j; angka++) {
                    ps.setString(angka, b[angka - 1]);
                }
                ps.executeUpdate();

                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                System.out.println("Notifikasi : " + e2);
            }
        }
    }

    public void menyimpan2(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
        try {
            dicari = "";
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                dicari = dicari + ", " + a[angka - 1];
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();
            ps = connect.prepareStatement("insert into trackersql values( now(),'insert into " + table + " values(" + dicari + ")','','jadwal pegawai " + akses.getuser() + "','simpan')");
            ps.executeUpdate();

            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                dicari = "";
                ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
                for (angka = 1; angka <= j; angka++) {
                    dicari = dicari + ", " + a[angka - 1];
                    ps.setString(angka, b[angka - 1]);
                }
                ps.executeUpdate();
                ps = connect.prepareStatement("insert into trackersql values( now(),'update " + table + " set " + update.replaceAll("'", "") + " where " + acuan_field.replaceAll("'", "") + " " + dicari + "','" + e.toString().replaceAll("'", "") + "','jadwal pegawai " + akses.getuser() + "','update')");
                ps.executeUpdate();

                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                System.out.println("Notifikasi : " + e2);
                dicari = "";
                for (angka = 1; angka <= j; angka++) {
                    dicari = dicari + ", " + a[angka - 1];
                }
                menyimpan("trackersql", "now(),'update " + table + " set " + update.replaceAll("'", "") + " where " + acuan_field.replaceAll("'", "") + " " + dicari + "','" + e.toString().replaceAll("'", "") + "','jadwal pegawai " + akses.getuser() + "','update'");
            }

        }
    }

    public void menyimpan3(String table, String value, int i, String[] a, String acuan_field, String update, int j, String[] b) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            for (angka = 1; angka <= i; angka++) {
                ps.setString(angka, a[angka - 1]);
            }
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Proses simpan berhasil..!!");
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
                for (angka = 1; angka <= j; angka++) {
                    ps.setString(angka, b[angka - 1]);
                }
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Proses simpan berhasil..!!");
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e2) {
                System.out.println("Notifikasi : " + e2);
            }
        }
    }

    public void menyimpan(String table, String value) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan(String table, String isisimpan, String isiedit, String acuan_field) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + isisimpan + ")");
            ps.executeUpdate();
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            try {
                ps = connect.prepareStatement("update " + table + " set " + isiedit + " where " + acuan_field);
                ps.executeUpdate();
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Edit : " + ex);
            }
        }
    }

    public void menyimpan(String table, String value, String sama, JTextField AlmGb) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

    }

    public void menyimpan(String table, String value, String sama, JTextField AlmGb, JTextField AlmPhoto) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?,?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.setBinaryStream(2, new FileInputStream(AlmPhoto.getText()), new File(AlmPhoto.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpan2logo(String table, String value, String sama, JTextField AlmGb, JTextField AlmPhoto, JTextField AlmPhoto1, JTextField AlmPhoto2) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?,?,?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.setBinaryStream(2, new FileInputStream(AlmPhoto.getText()), new File(AlmPhoto.getText()).length());
                ps.setBinaryStream(3, new FileInputStream(AlmPhoto1.getText()), new File(AlmPhoto1.getText()).length());
                ps.setBinaryStream(4, new FileInputStream(AlmPhoto2.getText()), new File(AlmPhoto2.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void meghapus(String table, String field, String nilai_field) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void mengedit(String table, String acuan_field, String update) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Mungkin kode sudah digunakan sebelumnya...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void meghapus2(String table, String field, String nilai_field) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.executeUpdate();
                System.out.println("Proses hapus data pada tabel " + table + " berhasil...!!!!");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean mengedittf(String table, String acuan_field, String update) {
        bool = true;
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Mungkin kode sudah digunakan sebelumnya...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void mengedit(String table, String acuan_field, String update, int i, String[] a) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Periksa kembali data...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void mengedit2(String table, String acuan_field, String update, int i, String[] a) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Proses edit berhasil...!!!!");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal mengedit. Periksa kembali data...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void mengedit3(String table, String acuan_field, String update, int i, String[] a) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean mengedittf(String table, String acuan_field, String update, int i, String[] a) {
        bool = true;
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Gagal Mengedit. Periksa kembali data...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void mengedit(String table, String acuan_field, String update, JTextField AlmGb) {
        try {
            ps = connect.prepareStatement("update " + table + " set " + update + " where " + acuan_field);
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Pilih dulu data yang mau anda edit...\n Klik data pada table untuk memilih...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void query(String qry) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeQuery();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void queryu(String qry) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean queryutf(String qry) {
        bool = false;
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            bool = false;
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void queryu(String qry, String parameter) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.setString(1, parameter);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void queryu2(String qry) {
        try {
            ps = connect.prepareStatement(qry);
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void queryu2(String qry, int i, String[] a) {
        try {
            try {
                ps = connect.prepareStatement(qry);
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public boolean queryu2tf(String qry, int i, String[] a) {
        bool = false;
        try {
            try {
                ps = connect.prepareStatement(qry);
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
                bool = true;
            } catch (Exception e) {
                bool = false;
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return bool;
    }

    public void queryu3(String qry, int i, String[] a) {
        try {
            try {
                ps = connect.prepareStatement(qry);
                for (angka = 1; angka <= i; angka++) {
                    ps.setString(angka, a[angka - 1]);
                }
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void AutoComitFalse() {
        try {
            connect.setAutoCommit(false);
        } catch (Exception e) {
        }
    }

    public void AutoComitTrue() {
        try {
            connect.setAutoCommit(true);
        } catch (Exception e) {
        }
    }

    public void cariIsi(String sql, JComboBox cmb) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    String dicari = rs.getString(1);
                    cmb.setSelectedItem(dicari);
                } else {
                    cmb.setSelectedItem("");
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

    public void cariIsi(String sql, JDateTimePicker dtp) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    try {
                        dtp.setDisplayFormat("yyyy-MM-dd");
                        dtp.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString(1)));
                        dtp.setDisplayFormat("dd-MM-yyyy");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
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

    public void cariIsi(String sql, JTextField txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
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

    public int cariRegistrasi(String norawat) {
        angka = 0;
        try {
            ps = connect.prepareStatement(
                    "select count(billing.no_rawat) from billing where billing.no_rawat=?");
            try {
                ps.setString(1, norawat);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
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
            System.out.println(e);
        }
        return angka;
    }

    public void cariIsi(String sql, JTextField txt, String kunci) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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

    public void cariIsi(String sql, JTextArea txt, String kunci) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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

    public void cariIsi(String sql, JLabel txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
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

    public String cariIsi(String sql) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    dicari = rs.getString(1);
                } else {
                    dicari = "";
                }
            } catch (Exception e) {
                dicari = "";
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

        return dicari;
    }

    public ByteArrayInputStream cariGambar(String sql) {
        ByteArrayInputStream inputStream = null;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    inputStream = new ByteArrayInputStream(rs.getBytes(1));
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

        return inputStream;
    }

    public String cariIsi(String sql, String data) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dicari = rs.getString(1);
                } else {
                    dicari = "";
                }
            } catch (Exception e) {
                dicari = "";
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

        return dicari;
    }

    public Date cariIsi2(String sql) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    tanggal = rs.getDate(1);
                } else {
                    tanggal = new Date();
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
        return tanggal;
    }

    public Integer cariInteger(String sql) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
                } else {
                    angka = 0;
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

        return angka;
    }

    public Integer cariIntegerCount(String sql) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    angka = angka + rs.getInt(1);
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

        return angka;
    }

    public Integer cariInteger(String sql, String data) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
                } else {
                    angka = 0;
                }
            } catch (Exception e) {
                angka = 0;
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

        return angka;
    }

    public Integer cariInteger2(String sql) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                rs.last();
                angka = rs.getRow();
                if (angka < 1) {
                    angka = 0;
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

        return angka;
    }

    public void cariIsiAngka(String sql, JTextField txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(df2.format(rs.getDouble(1)));
                } else {
                    txt.setText("0");
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

    public void cariIsiAngka(String sql, JLabel txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(df2.format(rs.getDouble(1)));
                } else {
                    txt.setText("0");
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

    public double cariIsiAngka(String sql) {
        angka2 = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka2 = rs.getDouble(1);
                } else {
                    angka2 = 0;
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

        return angka2;
    }

    public double cariIsiAngka(String sql, String data) {
        angka2 = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka2 = rs.getDouble(1);
                } else {
                    angka2 = 0;
                }
                //rs.close();
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

        return angka2;
    }

    public void cariGambar(String sql, JLabel txt) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    icon = new javax.swing.ImageIcon(rs.getBlob(1).getBytes(1L, (int) rs.getBlob(1).length()));
                    createThumbnail();
                    txt.setIcon(icon);
                } else {
                    txt.setText(null);
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

    public void cariGambar(String sql, java.awt.Canvas txt, String text) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                for (int I = 0; rs.next(); I++) {
                    ((Painter) txt).setImage(gambar(text));
                    Blob blob = rs.getBlob(5);
                    ((Painter) txt).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                }
            } catch (Exception ex) {
                cetak(ex.toString());
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

    public String cariString(String sql) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    dicari = rs.getString(1);
                } else {
                    dicari = "";
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

        return dicari;
    }

    private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

    public void Tabel(javax.swing.JTable tb, int lebar[]) {
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        angka = tb.getColumnCount();
        for (int i = 0; i < angka; i++) {
            javax.swing.table.TableColumn tbc = tb.getColumnModel().getColumn(i);
            tbc.setPreferredWidth(lebar[i]);
            //tb.setRowHeight(17);
        }
    }

    private void createThumbnail() {
        int maxDim = 150;
        try {
            Image inImage = icon.getImage();

            double scale = (double) maxDim / (double) inImage.getHeight(null);
            if (inImage.getWidth(null) > inImage.getHeight(null)) {
                scale = (double) maxDim / (double) inImage.getWidth(null);
            }

            int scaledW = (int) (scale * inImage.getWidth(null));
            int scaledH = (int) (scale * inImage.getHeight(null));

            BufferedImage outImage = new BufferedImage(scaledW, scaledH,
                    BufferedImage.TYPE_INT_RGB);

            AffineTransform tx = new AffineTransform();

            if (scale < 1.0d) {
                tx.scale(scale, scale);
            }

            Graphics2D g2d = outImage.createGraphics();
            g2d.drawImage(inImage, tx, null);
            g2d.dispose();

            iconThumbnail = new javax.swing.ImageIcon(outImage);
        } catch (Exception e) {
        }
    }

    private void cetak(String str) {
        System.out.println(str);
    }

    public void mengedit(String rujuk_masuk, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class Painter extends Canvas {

        Image image;

        private void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }

        private void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            double d = image.getHeight(this) / this.getHeight();
            double w = image.getWidth(this) / d;
            double x = this.getWidth() / 2 - w / 2;
            g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
        }

        private void cetak(String str) {
            System.out.println(str);
        }
    }

    public class NIOCopier {

        public NIOCopier(String asal, String tujuan) throws IOException {
            FileOutputStream outFile;
            try (FileInputStream inFile = new FileInputStream(asal)) {
                outFile = new FileOutputStream(tujuan);
                FileChannel outChannel;
                try (FileChannel inChannel = inFile.getChannel()) {
                    outChannel = outFile.getChannel();
                    for (ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                            inChannel.read(buffer) != -1;
                            buffer.clear()) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            outChannel.write(buffer);
                        }
                    }
                }
                outChannel.close();
            }
            outFile.close();
        }
    }

    public void cariIsi(String sql, JTextField txt, String kunci, String kunci2) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                ps.setString(2, kunci2);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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

    public void cariIsi(String sql, String txt, String kunci) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt = rs.getString(1);
                } else {
                    txt = "";
                }
            } catch (SQLException e) {
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

    public String cariApotek() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("APOTEK").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public String decXML(String input, String k) {
        dicari = "";
        try {
            //prop.loadFromXML(new FileInputStream("setting/database.xml"));
            inputan = input;
            Panjang_Input = inputan.length();
            panjangKey = k.length();
            for (int i = 0; i < Panjang_Input; i++) {
//                    enkrip = inputan.substring(i, 1);
                angka = inputan.charAt(i);
                angka = (angka - panjangKey) + 10;
                enkrip = (char) angka;
                dicari = dicari + enkrip;
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari;
    }

    public static String decXML2(String input, String k) {
        dicari2 = "";
        try {
            //prop.loadFromXML(new FileInputStream("setting/database.xml"));
            inputan2 = input;
            Panjang_Input2 = inputan2.length();
            panjangKey2 = k.length();
            for (int i = 0; i < Panjang_Input2; i++) {
//                    enkrip = inputan.substring(i, 1);
                angka3 = inputan2.charAt(i);
                angka3 = (angka3 - panjangKey2) + 10;
                enkrip2 = (char) angka3;
                dicari2 = dicari2 + enkrip2;
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari2;
    }

    public String cariApotek2() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("APOTEK_IGD").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public void cariIsiResep(String sql, JTextField txt, String kunci, String kunci2, String kunci3) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                ps.setString(2, kunci2);
                ps.setString(3, kunci3);
                ps.setString(4, kunci);
                ps.setString(5, kunci2);
                ps.setString(6, kunci3);
                rs = ps.executeQuery();
                if (rs.next()) {
                    txt.setText(rs.getString(1));
                } else {
                    txt.setText("");
                }
            } catch (SQLException e) {
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

    public double cekIGD(String sql, String kunci, String kunci2, String kunci3, String kunci4, String kunci5) {
        angka2 = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, kunci);
                ps.setString(2, kunci2);
                ps.setString(3, kunci3);
                ps.setString(4, kunci4);
                ps.setString(5, kunci5);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka2 = rs.getDouble(1);
                } else {
                    angka2 = 0;
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

        return angka2;
    }

    public int cariObat(String norawat) {
        angka = 0;
        try {
            ps = connect.prepareStatement(
                    "select count(detail_pemberian_obat.no_rawat) from detail_pemberian_obat where detail_pemberian_obat.no_rawat=?");
            try {
                ps.setString(1, norawat);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
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
            System.out.println(e);
        }
        return angka;
    }

    public Integer cekForm(String s) {
        angka = 0;
        try {
            prop.loadFromXML(new FileInputStream("setting/noname.xml"));
            angka = Integer.parseInt(prop.getProperty(s));

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return angka;
    }

    public Integer cariInteger(String sql, String data, String data2) {
        angka = 0;
        try {
            ps = connect.prepareStatement(sql);
            try {
                ps.setString(1, data);
                ps.setString(2, data2);
                rs = ps.executeQuery();
                if (rs.next()) {
                    angka = rs.getInt(1);
                } else {
                    angka = 0;
                }
            } catch (Exception e) {
                angka = 0;
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

        return angka;
    }

    public void meghapus3(String table, String field, String nilai_field) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public String FolderQRresep() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERQRRESEP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari;
    }
    
    public String FolderQRresepRanap() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERQRRESEPRANAP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        return dicari;
    }

    public String cariFolder() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDER").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public String cariFolderPrint() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINT").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public String cariFolderPrintResep() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINTQRRESEP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public String cariFolderPrintResepRanap() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINTQRRESEPRANAP").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }

    public void menyimpanQr(String table, String value, String sama, String AlmGb) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?)");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb), new File(AlmGb).length());
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan lokasi folder utk. meletakkan " + sama + " belum ditentukan..!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void insertClosingStok() {
        cekData = cariInteger("select count(-1) from stok_bulanan where periode = DATE_FORMAT(now(),'%Y-%m')");
        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Closing Stok Bulanan Belum dilakukan, proses closing stok, klik OK");
            try {
                ps = connect.prepareStatement("select kode_brng,DATE_FORMAT(SUBDATE(now(), INTERVAL 1 MONTH),'%Y-%m') 'periode',kd_bangsal,stok as 'stok_awal',DATE_FORMAT(now(),'%Y-%m-%d') 'tgl_input',0 as 'stok_akhir','0000-00-00' as 'tgl_akhir' from gudangbarang");
                rs1 = ps.executeQuery();
                while (rs1.next()) {
                    menyimpan("stok_bulanan", "'" + rs1.getString("kode_brng") + "','" + rs1.getString("periode") + "','" + rs1.getString("kd_bangsal") + "',0,"
                            + "'" + rs1.getString("tgl_input") + "','" + rs1.getString("stok_awal") + "','" + rs1.getString("tgl_input") + "'", "stok_akhir = '" + rs1.getString("stok_awal") + "',tgl_input_akhir = DATE_FORMAT(now(),'%Y-%m-%d')", "kode_brng = '" + rs1.getString("kode_brng") + "' and kd_bangsal = '" + rs1.getString("kd_bangsal") + "' and periode = '" + rs1.getString("periode") + "'");
                }

                ps1 = connect.prepareStatement("insert into stok_bulanan (select kode_brng,DATE_FORMAT(now(),'%Y-%m'),kd_bangsal,stok,DATE_FORMAT(now(),'%Y-%m-%d'),0,'0000-00-00' from gudangbarang)");
                try {
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil menyimpan data closing bulanan");
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data closing bulanan. Hubungi ADMIN");
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

        }
        insertClosingStokHarian();
    }

    public void updateClosingStok() {
        try {
            ps = connect.prepareStatement("UPDATE stok_bulanan s,gudangbarang g SET s.stok_akhir = g.stok,tgl_input_akhir = DATE_FORMAT(now(),'%Y-%m-%d') WHERE g.kode_brng = s.kode_brng and g.kd_bangsal = s.kd_bangsal and s.periode = DATE_FORMAT(SUBDATE(now(), INTERVAL 1 MONTH),'%Y-%m')");
            try {
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil mengupdate data closing bulanan");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal mengupdate data closing bulanan. Hubungi ADMIN");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void menyimpanInsertIgnore(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert ignore into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void cariIsiComboDB(String sql, JComboBox cmb) {
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    String dicari = rs.getString(1);
                    cmb.addItem(dicari);
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

    public String Terbilang(double angka) {
        if (angka < 12) {
            return nominal[(int) angka];
        }

        if (angka >= 12 && angka <= 19) {
            return nominal[(int) angka % 10] + " Belas ";
        }

        if (angka >= 20 && angka <= 99) {
            return nominal[(int) angka / 10] + " Puluh " + nominal[(int) angka % 10];
        }

        if (angka >= 100 && angka <= 199) {
            return "Seratus " + Terbilang(angka % 100);
        }

        if (angka >= 200 && angka <= 999) {
            return nominal[(int) angka / 100] + " Ratus " + Terbilang(angka % 100);
        }

        if (angka >= 1000 && angka <= 1999) {
            return "Seribu " + Terbilang(angka % 1000);
        }

        if (angka >= 2000 && angka <= 999999) {
            return Terbilang((int) angka / 1000) + " Ribu " + Terbilang(angka % 1000);
        }

        if (angka >= 1000000 && angka <= 999999999) {
            return Terbilang((int) angka / 1000000) + " Juta " + Terbilang(angka % 1000000);
        }

        if (angka > 999999999) {
            return Terbilang((Double) angka / 1000000000) + " Milyar " + Terbilang(angka % 1000000000);
        }
        return "";
    }

    public String bulanINDONESIA(String sql) {
        bulan = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    bulan = rs.getString(1);
                    if (bulan.equals("January") || bulan.equals("1") || bulan.equals("01")) {
                        bulan = "Januari";
                    }

                    if (bulan.equals("February") || bulan.equals("2") || bulan.equals("02")) {
                        bulan = "Februari";
                    }

                    if (bulan.equals("March") || bulan.equals("3") || bulan.equals("03")) {
                        bulan = "Maret";
                    }

                    if (bulan.equals("April") || bulan.equals("4") || bulan.equals("04")) {
                        bulan = "April";
                    }

                    if (bulan.equals("May") || bulan.equals("5") || bulan.equals("05")) {
                        bulan = "Mei";
                    }

                    if (bulan.equals("June") || bulan.equals("6") || bulan.equals("06")) {
                        bulan = "Juni";
                    }

                    if (bulan.equals("July") || bulan.equals("7") || bulan.equals("07")) {
                        bulan = "Juli";
                    }

                    if (bulan.equals("August") || bulan.equals("8") || bulan.equals("08")) {
                        bulan = "Agustus";
                    }

                    if (bulan.equals("September") || bulan.equals("9") || bulan.equals("09")) {
                        bulan = "September";
                    }

                    if (bulan.equals("October") || bulan.equals("10")) {
                        bulan = "Oktober";
                    }

                    if (bulan.equals("November") || bulan.equals("11")) {
                        bulan = "Nopember";
                    }

                    if (bulan.equals("December") || bulan.equals("12")) {
                        bulan = "Desember";
                    }
                } else {
                    bulan = "";
                }
            } catch (Exception e) {
                bulan = "";
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
        return bulan;
    }

    public String bulanINDONESIAkata(String darikata) {
        bulan = darikata;
        if (bulan.equals("January") || bulan.equals("1")) {
            bulan = "Januari";
        }

        if (bulan.equals("February") || bulan.equals("2")) {
            bulan = "Februari";
        }

        if (bulan.equals("March") || bulan.equals("3")) {
            bulan = "Maret";
        }

        if (bulan.equals("April") || bulan.equals("4")) {
            bulan = "April";
        }

        if (bulan.equals("May") || bulan.equals("5")) {
            bulan = "Mei";
        }

        if (bulan.equals("June") || bulan.equals("6")) {
            bulan = "Juni";
        }

        if (bulan.equals("July") || bulan.equals("7")) {
            bulan = "Juli";
        }

        if (bulan.equals("August") || bulan.equals("8")) {
            bulan = "Agustus";
        }

        if (bulan.equals("September") || bulan.equals("9")) {
            bulan = "September";
        }

        if (bulan.equals("October") || bulan.equals("10")) {
            bulan = "Oktober";
        }

        if (bulan.equals("November") || bulan.equals("11")) {
            bulan = "Nopember";
        }

        if (bulan.equals("December") || bulan.equals("12")) {
            bulan = "Desember";
        }
        return bulan = "";
    }

    public String hariINDONESIA(String sql) {
        hari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    hari = rs.getString(1);
                    if (hari.equals("Monday")) {
                        hari = "Senin";
                    }

                    if (hari.equals("Tuesday")) {
                        hari = "Selasa";
                    }

                    if (hari.equals("Wednesday")) {
                        hari = "Rabu";
                    }

                    if (hari.equals("Thursday")) {
                        hari = "Kamis";
                    }

                    if (hari.equals("Friday")) {
                        hari = "Jum'at";
                    }

                    if (hari.equals("Saturday")) {
                        hari = "Sabtu";
                    }

                    if (hari.equals("Sunday")) {
                        hari = "Minggu";
                    }
                } else {
                    hari = "";
                }
            } catch (Exception e) {
                hari = "";
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
        return hari;
    }

    public String bulanRomawi(String sql) {
        romawi = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {
                    romawi = rs.getString(1);
                    if (romawi.equals("January") || romawi.equals("1") || romawi.equals("01")) {
                        romawi = "I";
                    }

                    if (romawi.equals("February") || romawi.equals("2") || romawi.equals("02")) {
                        romawi = "II";
                    }

                    if (romawi.equals("March") || romawi.equals("3") || romawi.equals("03")) {
                        romawi = "III";
                    }

                    if (romawi.equals("April") || romawi.equals("4") || romawi.equals("04")) {
                        romawi = "IV";
                    }

                    if (romawi.equals("May") || romawi.equals("5") || romawi.equals("05")) {
                        romawi = "V";
                    }

                    if (romawi.equals("June") || romawi.equals("6") || romawi.equals("06")) {
                        romawi = "VI";
                    }

                    if (romawi.equals("July") || romawi.equals("7") || romawi.equals("07")) {
                        romawi = "VII";
                    }

                    if (romawi.equals("August") || romawi.equals("8") || romawi.equals("08")) {
                        romawi = "VIII";
                    }

                    if (romawi.equals("September") || romawi.equals("9") || romawi.equals("09")) {
                        romawi = "IX";
                    }

                    if (romawi.equals("October") || romawi.equals("10")) {
                        romawi = "X";
                    }

                    if (romawi.equals("November") || romawi.equals("11")) {
                        romawi = "XI";
                    }

                    if (romawi.equals("December") || romawi.equals("12")) {
                        romawi = "XII";
                    }
                } else {
                    romawi = "";
                }
            } catch (Exception e) {
                romawi = "";
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
        return romawi;
    }

    public void menghapus(String table, String field, String field2, String nilai_field, String nilai_field2) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=? and " + field2 + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.setString(2, nilai_field2);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
            SimpanTrack("delete from " + table + " where " + field + "='" + nilai_field + "' and " + field2 + "='" + nilai_field2 + "'");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void meghapus(String table, String field, String field2, String nilai_field, String nilai_field2) {
        try {
            ps = connect.prepareStatement("delete from " + table + " where " + field + "=? and " + field2 + "=?");
            try {
                ps.setString(1, nilai_field);
                ps.setString(2, nilai_field2);
                ps.executeUpdate();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
            SimpanTrack("delete from " + table + " where " + field + "='" + nilai_field + "' and " + field2 + "='" + nilai_field2 + "'");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void insertClosingStokHarian() {
        cekData = cariInteger("select count(-1) from stok_harian where tanggal = DATE_FORMAT(now(),'%Y-%m-%d')");
        if (cekData == 0) {
            JOptionPane.showMessageDialog(null, "Closing Stok Harian Belum dilakukan, proses closing stok, klik OK");
            try {
                ps = connect.prepareStatement("select kode_brng,DATE_FORMAT(SUBDATE(now(), INTERVAL 1 DAY),'%Y-%m-%d') 'tanggal',kd_bangsal,stok as 'stok_awal',DATE_FORMAT(now(),'%Y-%m-%d') 'tgl_input',0 as 'stok_akhir','0000-00-00' as 'tgl_akhir' from gudangbarang");
                rs1 = ps.executeQuery();
                while (rs1.next()) {
                    menyimpan("stok_harian", "'" + rs1.getString("kode_brng") + "','" + rs1.getString("tanggal") + "','" + rs1.getString("kd_bangsal") + "',0,"
                            + "'" + rs1.getString("tgl_input") + "','" + rs1.getString("stok_awal") + "','" + rs1.getString("tgl_input") + "'", "stok_akhir = '" + rs1.getString("stok_awal") + "',tgl_input_akhir = DATE_FORMAT(now(),'%Y-%m-%d')", "kode_brng = '" + rs1.getString("kode_brng") + "' and kd_bangsal = '" + rs1.getString("kd_bangsal") + "' and tanggal = '" + rs1.getString("tanggal") + "'");
                }

                ps1 = connect.prepareStatement("insert into stok_harian (select kode_brng,DATE_FORMAT(now(),'%Y-%m-%d'),kd_bangsal,stok,DATE_FORMAT(now(),'%Y-%m-%d'),0,'0000-00-00' from gudangbarang)");
                try {
                    ps1.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Berhasil menyimpan data closing harian");
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    JOptionPane.showMessageDialog(null, "Maaf, gagal menyimpan data closing harian. Hubungi ADMIN");
                } finally {
                    if (ps != null) {
                        ps.close();
                    }
                }
//                updateClosingStok();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    public void menyimpanIgnore(String table, String value, String sama) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ")");
            try {
                ps.executeUpdate();
            } catch (Exception e) {
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public boolean meghapustf(String table,String field,String nilai_field) {
        bool=true;
        try {
            ps=connect.prepareStatement("delete from "+table+" where "+field+"=?");
            try{       
                ps.setString(1,nilai_field);
                ps.executeUpdate();
                bool=true;
             }catch(Exception e){
                bool=false;
                System.out.println("Notifikasi : "+e);
                JOptionPane.showMessageDialog(null,"Maaf, data gagal dihapus. Kemungkinan data tersebut masih dipakai di table lain...!!!!");
             }finally{
                if(ps != null){
                    ps.close();
                }
            }
            SimpanTrack("delete from "+table+" where "+field+"='"+nilai_field+"'");
        } catch (Exception e) {
            bool=false;
            System.out.println("Notifikasi : "+e);
        }
        return bool;
    }
}
