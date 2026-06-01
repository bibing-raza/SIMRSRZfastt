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
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
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
import java.net.InetAddress;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.Base64;
import javax.imageio.ImageIO;
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
    private Statement st;
    private int angka = 0;
    private static int angka3 = 0;
    private double angka2 = 0;
    private String dicari = "", output = "", inputan = "", bulan = "", hari = "", romawi = "", ipAddresKomputer = "", user = "", 
            umur = "", sttsumur = "", umurOK = "", foldernya = "";
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
                int totalBerhasil = ps.executeUpdate();
                System.out.println("Affected rows : " + totalBerhasil);
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
    
    public void SimpanHistoriRekamMedis(String norawat, String dokumen, String proses) {
        ipAddresKomputer = "";
        user = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            ipAddresKomputer = ip.getHostAddress();
            
            if (akses.getadmin() == true) {
                user = "-";
            } else {
                user = akses.getkode();
            }
            
            try {
                ps = connect.prepareStatement("insert into histori_petugas_erm values(?,?,?,?,?,now())");
                try {
                    ps.setString(1, norawat);
                    ps.setString(2, dokumen);
                    ps.setString(3, proses);
                    ps.setString(4, user);
                    ps.setString(5, ipAddresKomputer);
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
        } catch (Exception e) {
            System.out.println("Gagal mendapatkan alamat IP host : " + e.getMessage());
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

    public void menyimpan2logo(String table, String value, String sama, JTextField AlmGb,
            JTextField AlmPhoto, JTextField AlmPhoto1, JTextField AlmPhoto2, JTextField AlmPhoto3, String setKomputer) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?,?,?,?," + setKomputer + ")");
            try {
                ps.setBinaryStream(1, new FileInputStream(AlmGb.getText()), new File(AlmGb.getText()).length());
                ps.setBinaryStream(2, new FileInputStream(AlmPhoto.getText()), new File(AlmPhoto.getText()).length());
                ps.setBinaryStream(3, new FileInputStream(AlmPhoto1.getText()), new File(AlmPhoto1.getText()).length());
                ps.setBinaryStream(4, new FileInputStream(AlmPhoto2.getText()), new File(AlmPhoto2.getText()).length());
                ps.setBinaryStream(5, new FileInputStream(AlmPhoto3.getText()), new File(AlmPhoto3.getText()).length());
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
                int totalBerhasil = ps.executeUpdate();
                System.out.println("Affected rows : " + totalBerhasil);
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
    
    public void queryuBuilder(String qry1, String qry2, String qry3, String qry4, String pesan) {
        /**
           -- untuk penerapan query seperti ini (contoh)
           -- Tahap 1: ubah ke kode sementara biar tidak bentrok
        SET @urut := 0;
        UPDATE master_indikator_nasional_mutu
        JOIN (
            SELECT kd_indikator, (@urut := @urut + 1) AS new_no
            FROM master_indikator_nasional_mutu
            WHERE kd_indikator LIKE 'IMU%'
            ORDER BY no_urut
        ) t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator
        SET master_indikator_nasional_mutu.kd_indikator = CONCAT('TMP', LPAD(t.new_no, 6, '0'));

        -- Tahap 2: ubah dari TMP ke IMU
        SET @urut := 0;
        UPDATE master_indikator_nasional_mutu
        JOIN (
            SELECT kd_indikator, (@urut := @urut + 1) AS new_no
            FROM master_indikator_nasional_mutu
            WHERE kd_indikator LIKE 'TMP%'
            ORDER BY no_urut
        ) t ON master_indikator_nasional_mutu.kd_indikator = t.kd_indikator
        SET master_indikator_nasional_mutu.kd_indikator = CONCAT('IMU', LPAD(t.new_no, 6, '0'));
        **/
        
        try {
            StringBuilder sb = new StringBuilder();
            if (qry1 != null && !qry1.trim().isEmpty()) sb.append(qry1.trim()).append(";");
            if (qry2 != null && !qry2.trim().isEmpty()) sb.append(qry2.trim()).append(";");
            if (qry3 != null && !qry3.trim().isEmpty()) sb.append(qry3.trim()).append(";");
            if (qry4 != null && !qry4.trim().isEmpty()) sb.append(qry4.trim()).append(";");

            String query = sb.toString();
            String[] queries = query.split(";");

            st = connect.createStatement();
            int totalBerhasil = 0;

            try {
                for (String q : queries) {
                    q = q.trim();
                    if (!q.isEmpty()) {
                        int hasil = st.executeUpdate(q);
                        totalBerhasil += hasil;
                        System.out.println("Affected rows : " + hasil + " " + pesan);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Query tidak bisa dijalankan...!!!!");
            } finally {
                if (st != null) {
                    st.close();
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

    public int cekRekamMedisIGD(String norawat) {
        angka = 0;
        int rmigd1 = 0, rmigd2 = 0, rmigd3 = 0, rmigd4 = 0, rmigd5 = 0, rmigd6 = 0, rmigd7 = 0, rmigd8 = 0, rmigd9 = 0, rmigd10 = 0, rmigd11 = 0,
                rmigd12 = 0, rmigd13 = 0, rmigd14 = 0, rmigd15 = 0, rmigd16 = 0, rmigd17 = 0, rmigd18 = 0, rmigd19 = 0, rmigd20 = 0, rmigd21 = 0,
                rmigd22 = 0, rmigd23 = 0, rmigd24 = 0, rmigd25 = 0, rmigd26 = 0, rmigd27 = 0, rmigd28 = 0, rmigd29 = 0, rmigd30 = 0, rmigd31 = 0;
        
        String tbl1 = "", tbl2 = "", tbl3 = "", tbl4 = "", tbl5 = "", tbl6 = "", tbl7 = "", tbl8 = "", tbl9 = "", tbl10 = "", tbl11 = "", tbl12 = "", 
                tbl13 = "", tbl14 = "", tbl15 = "", tbl16 = "", tbl17 = "", tbl18 = "", tbl19 = "", tbl20 = "", tbl21 = "", tbl22 = "", tbl23 = "",
                tbl24 = "", tbl25 = "", tbl26 = "", tbl27 = "", tbl28 = "", tbl29 = "", tbl30 = "", tbl31 = "";
        
        rmigd1 = cariInteger("select count(-1) from triase_igd where no_rawat='" + norawat + "'");
        rmigd2 = cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + norawat + "'");
        rmigd3 = cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + norawat + "'");
        rmigd4 = cariInteger("select count(-1) from penilaian_awal_keperawatan_igd_resiko where no_rawat='" + norawat + "'");
        rmigd5 = cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "' and status='Ralan'");
        rmigd6 = cariInteger("select count(-1) from pemberian_obat where no_rawat='" + norawat + "' and status='Ralan'");
        rmigd7 = cariInteger("select count(-1) from pelaksana_pemberian_obat where no_rawat='" + norawat + "' and nm_unit like '%igd%'");
        rmigd8 = cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + norawat + "'");
        rmigd9 = cariInteger("select count(-1) from cppt where no_rawat='" + norawat + "' and flag_hapus='tidak' and status='Ralan'");
        rmigd10 = cariInteger("select count(-1) from cppt_konfirmasi_terapi where no_rawat='" + norawat + "'");
        rmigd11 = cariInteger("select count(-1) from surat_tindakan_kedokteran where no_rawat='" + norawat + "' and kasus_tindakan='Ralan'");
        rmigd12 = cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + norawat + "'");
        rmigd13 = cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + norawat + "'");
        rmigd14 = cariInteger("select count(-1) from surat_istirahat_sakit where no_rawat='" + norawat + "'");
        rmigd15 = cariInteger("select count(-1) from surat_keterangan_sakit where no_rawat='" + norawat + "'");
        rmigd16 = cariInteger("select count(-1) from catatan_resep where no_rawat='" + norawat + "'");
        rmigd17 = cariInteger("select count(-1) from lembar_observasi where no_rawat='" + norawat + "'");
        rmigd18 = cariInteger("select count(-1) from detail_lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat like '%igd%'");
        rmigd19 = cariInteger("select count(-1) from asesmen_pra_sedasi where no_rawat='" + norawat + "' and ruang_rawat like '%igd%'");
        rmigd20 = cariInteger("select count(-1) from triase_pediatrik where no_rawat='" + norawat + "'");
        rmigd21 = cariInteger("select count(-1) from triase_ponek where no_rawat='" + norawat + "'");
        rmigd22 = cariInteger("select count(-1) from asesmen_awal_kebidanan1 where no_rawat='" + norawat + "'");
        rmigd23 = cariInteger("select count(-1) from asesmen_awal_kebidanan2 where no_rawat='" + norawat + "'");
        rmigd24 = cariInteger("select count(-1) from inspeksi_ginekologi_awal_kebidanan where no_rawat='" + norawat + "'");
        rmigd25 = cariInteger("select count(-1) from riwayat_kehamilan_asesmen_awal_kebidanan where no_rawat='" + norawat + "'");
        rmigd26 = cariInteger("select count(-1) from asesmen_medik_kebidanan where no_rawat='" + norawat + "'");
        rmigd27 = cariInteger("select count(-1) from laporan_operasi where no_rawat='" + norawat + "'");
        rmigd28 = cariInteger("select count(-1) from laporan_operasi_obs_ttv where no_rawat='" + norawat + "'");
        rmigd29 = cariInteger("select count(-1) from catatan_ruang_pemulihan where no_rawat='" + norawat + "'");
        rmigd30 = cariInteger("select count(-1) from catatan_ruang_pemulihan_obs_ttv where no_rawat='" + norawat + "'");
        rmigd31 = cariInteger("select count(-1) from formulir_site_marking_operasi where no_rawat='" + norawat + "'");

        if (rmigd1 > 0) {
            tbl1 = "triase_igd\n";
        } else {
            tbl1 = "";
        }
        
        if (rmigd2 > 0) {
            tbl2 = "penilaian_awal_medis_igd\n";
        } else {
            tbl2 = "";
        }
        
        if (rmigd3 > 0) {
            tbl3 = "penilaian_awal_keperawatan_igdrz\n";
        } else {
            tbl3 = "";
        }
        
        if (rmigd4 > 0) {
            tbl4 = "penilaian_awal_keperawatan_igd_resiko\n";
        } else {
            tbl4 = "";
        }
        
        if (rmigd5 > 0) {
            tbl5 = "transfer_serah_terima_pasien_igd\n";
        } else {
            tbl5 = "";
        }
        
        if (rmigd6 > 0) {
            tbl6 = "pemberian_obat\n";
        } else {
            tbl6 = "";
        }
        
        if (rmigd7 > 0) {
            tbl7 = "pelaksana_pemberian_obat\n";
        } else {
            tbl7 = "";
        }
        
        if (rmigd8 > 0) {
            tbl8 = "penilaian_awal_medis_obstetri_ralan\n";
        } else {
            tbl8 = "";
        }
        
        if (rmigd9 > 0) {
            tbl9 = "cppt\n";
        } else {
            tbl9 = "";
        }
        
        if (rmigd10 > 0) {
            tbl10 = "cppt_konfirmasi_terapi\n";
        } else {
            tbl10 = "";
        }
        
        if (rmigd11 > 0) {
            tbl11 = "surat_tindakan_kedokteran\n";
        } else {
            tbl11 = "";
        }
        
        if (rmigd12 > 0) {
            tbl12 = "permintaan_lab_raza\n";
        } else {
            tbl12 = "";
        }
        
        if (rmigd13 > 0) {
            tbl13 = "permintaan_radiologi\n";
        } else {
            tbl13 = "";
        }
        
        if (rmigd14 > 0) {
            tbl14 = "surat_istirahat_sakit\n";
        } else {
            tbl14 = "";
        }
        
        if (rmigd15 > 0) {
            tbl15 = "surat_keterangan_sakit\n";
        } else {
            tbl15 = "";
        }
        
        if (rmigd16 > 0) {
            tbl16 = "catatan_resep\n";
        } else {
            tbl16 = "";
        }
        
        if (rmigd17 > 0) {
            tbl17 = "lembar_observasi\n";
        } else {
            tbl17 = "";
        }
        
        if (rmigd18 > 0) {
            tbl18 = "detail_lembar_observasi\n";
        } else {
            tbl18 = "";
        }
        
        if (rmigd19 > 0) {
            tbl19 = "asesmen_pra_sedasi\n";
        } else {
            tbl19 = "";
        }
        
        if (rmigd20 > 0) {
            tbl20 = "triase_pediatrik\n";
        } else {
            tbl20 = "";
        }
        
        if (rmigd21 > 0) {
            tbl21 = "triase_ponek\n";
        } else {
            tbl21 = "";
        }        
        
        if (rmigd22 > 0) {
            tbl22 = "asesmen_awal_kebidanan1\n";
        } else {
            tbl22 = "";
        }
        
        if (rmigd23 > 0) {
            tbl23 = "asesmen_awal_kebidanan2\n";
        } else {
            tbl23 = "";
        }
        
        if (rmigd24 > 0) {
            tbl24 = "inspeksi_ginekologi_awal_kebidanan\n";
        } else {
            tbl24 = "";
        }
        
        if (rmigd25 > 0) {
            tbl25 = "riwayat_kehamilan_asesmen_awal_kebidanan\n";
        } else {
            tbl25 = "";
        }
        
        if (rmigd26 > 0) {
            tbl26 = "asesmen_medik_kebidanan\n";
        } else {
            tbl26 = "";
        }
        
        if (rmigd27 > 0) {
            tbl27 = "laporan_operasi\n";
        } else {
            tbl27 = "";
        }
        
        if (rmigd28 > 0) {
            tbl28 = "laporan_operasi_obs_ttv\n";
        } else {
            tbl28 = "";
        }
        
        if (rmigd29 > 0) {
            tbl29 = "catatan_ruang_pemulihan\n";
        } else {
            tbl29 = "";
        }
        
        if (rmigd30 > 0) {
            tbl30 = "catatan_ruang_pemulihan_obs_ttv\n";
        } else {
            tbl30 = "";
        }
        
        if (rmigd31 > 0) {
            tbl31 = "formulir_site_marking_operasi\n";
        } else {
            tbl31 = "";
        }

        angka = rmigd1 + rmigd2 + rmigd3 + rmigd4 + rmigd5 + rmigd6 + rmigd7 + rmigd8 + rmigd9 + rmigd10 + rmigd11 + rmigd12 + rmigd13 + rmigd14 + rmigd15
                + rmigd16 + rmigd17 + rmigd18 + rmigd19 + rmigd20 + rmigd21 + rmigd22 + rmigd23 + rmigd24 + rmigd25 + rmigd26 + rmigd27 + rmigd28 + rmigd29 
                + rmigd30 + rmigd31;

        if (angka > 0) {
            System.out.println("\nPesan  : no. rawat " + norawat + " utk. data e-RM nya masih ada tersimpan ditabel berikut ini :\n"
                    + tbl1 + tbl2 + tbl3 + tbl4 + tbl5 + tbl6 + tbl7 + tbl8 + tbl9 + tbl10 + tbl11 + tbl12 + tbl13 + tbl14 + tbl15 + tbl16 + tbl17 + tbl18
                    + tbl19 + tbl20 + tbl21 + tbl22 + tbl23 + tbl24 + tbl25 + tbl26 + tbl27 + tbl28 + tbl29 + tbl30 + tbl31
            );
        }
        
        return angka;
    }
    
    public int cekRekamMedisRanap(String norawat) {
        angka = 0;
        int rm1 = 0, rm2 = 0, rm3 = 0, rm4 = 0, rm5 = 0, rm6 = 0, rm7 = 0, rm8 = 0, rm9 = 0, rm10 = 0, rm11 = 0, rm12 = 0, rm13 = 0,
                rm14 = 0, rm15 = 0, rm16 = 0, rm17 = 0, rm18 = 0, rm19 = 0, rm20 = 0, rm21 = 0, rm22 = 0, rm23 = 0, rm24 = 0, rm25 = 0, rm26 = 0,
                rm27 = 0, rm28 = 0, rm29 = 0, rm30 = 0, rm31 = 0, rm32 = 0, rm33 = 0, rm34 = 0, rm35 = 0, rm36 = 0, rm37 = 0, rm38 = 0, rm39 = 0,
                rm40 = 0, rm41 = 0, rm42 = 0, rm43 = 0, rm44 = 0, rm45 = 0, rm46 = 0, rm47 = 0, rm48 = 0, rm49 = 0, rm50 = 0, rm51 = 0, rm52 = 0,
                rm53 = 0, rm54 = 0, rm55 = 0, rm56 = 0, rm57 = 0, rm58 = 0, rm59 = 0, rm60 = 0, rm61 = 0, rm62 = 0, rm63 = 0, rm64 = 0, rm65 = 0,
                rm66 = 0, rm67 = 0, rm68 = 0, rm69 = 0, rm70 = 0, rm71 = 0, rm72 = 0, rm73 = 0, rm74 = 0, rm75 = 0, rm76 = 0, rm77 = 0, rm78 = 0, 
                rm79 = 0, rm80 = 0, rm81 = 0, rm82 = 0, rm83 = 0, rm84 = 0, rm85 = 0, rm86 = 0, rm87 = 0, rm88 = 0, rm89 = 0, rm90 = 0, rm91 = 0,
                rm92 = 0;

        String tbl1 = "", tbl2 = "", tbl3 = "", tbl4 = "", tbl5 = "", tbl6 = "", tbl7 = "", tbl8 = "", tbl9 = "", tbl10 = "", tbl11 = "", tbl12 = "",
                tbl13 = "", tbl14 = "", tbl15 = "", tbl16 = "", tbl17 = "", tbl18 = "", tbl19 = "", tbl20 = "", tbl21 = "", tbl22 = "", tbl23 = "",
                tbl24 = "", tbl25 = "", tbl26 = "", tbl27 = "", tbl28 = "", tbl29 = "", tbl30 = "", tbl31 = "", tbl32 = "", tbl33 = "", tbl34 = "",
                tbl35 = "", tbl36 = "", tbl37 = "", tbl38 = "", tbl39 = "", tbl40 = "", tbl41 = "", tbl42 = "", tbl43 = "", tbl44 = "", tbl45 = "",
                tbl46 = "", tbl47 = "", tbl48 = "", tbl49 = "", tbl50 = "", tbl51 = "", tbl52 = "", tbl53 = "", tbl54 = "", tbl55 = "", tbl56 = "",
                tbl57 = "", tbl58 = "", tbl59 = "", tbl60 = "", tbl61 = "", tbl62 = "", tbl63 = "", tbl64 = "", tbl65 = "", tbl66 = "", tbl67 = "",
                tbl68 = "", tbl69 = "", tbl70 = "", tbl71 = "", tbl72 = "", tbl73 = "", tbl74 = "", tbl75 = "", tbl76 = "", tbl77 = "", tbl78 = "",
                tbl79 = "", tbl80 = "", tbl81 = "", tbl82 = "", tbl83 = "", tbl84 = "", tbl85 = "", tbl86 = "", tbl87 = "", tbl88 = "", tbl89 = "",
                tbl90 = "", tbl91 = "", tbl92 = "";

        rm1 = cariInteger("select count(-1) from catatan_tindakan_keperawatan where no_rawat='" + norawat + "'");
        rm2 = cariInteger("select count(-1) from evaluasi_catatan_tindakan_keperawatan where no_rawat='" + norawat + "'");
        rm3 = cariInteger("select count(-1) from manajemen_catatan_tindakan_keperawatan where no_rawat='" + norawat + "'");
        rm4 = cariInteger("select count(-1) from surat_konsul_unit_ranap where no_rawat='" + norawat + "'");
        rm5 = cariInteger("select count(-1) from asesmen_medik_dewasa_ranap where no_rawat='" + norawat + "'");
        rm6 = cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + norawat + "'");
        rm7 = cariInteger("select count(-1) from cppt where no_rawat='" + norawat + "' and status='Ranap' and flag_hapus='tidak'");
        rm8 = cariInteger("select count(-1) from cppt_konfirmasi_terapi where no_rawat='" + norawat + "'");
        rm9 = cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + norawat + "' and status='Ranap'");
        rm10 = cariInteger("select count(-1) from pemberian_obat where no_rawat='" + norawat + "' and nm_unit not like '%igd%' and status='Ranap'");
        rm11 = cariInteger("select count(-1) from pelaksana_pemberian_obat where no_rawat='" + norawat + "' and nm_unit not like '%igd%'");
        rm12 = cariInteger("select count(-1) from surat_tindakan_kedokteran where no_rawat='" + norawat + "' and kasus_tindakan='Ranap'");
        rm13 = cariInteger("select count(-1) from spirometri where no_rawat='" + norawat + "'");
        rm14 = cariInteger("select count(-1) from data_persalinan where no_rawat='" + norawat + "'");
        rm15 = cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + norawat + "' and status_rawat='Ranap'");
        rm16 = cariInteger("select count(-1) from permintaan_radiologi where no_rawat='" + norawat + "' and dari_unit not like '%igd%'");
        rm17 = cariInteger("select count(-1) from surat_istirahat_sakit where no_rawat='" + norawat + "'");
        rm18 = cariInteger("select count(-1) from surat_keterangan_sakit where no_rawat='" + norawat + "'");
        rm19 = cariInteger("select count(-1) from catatan_resep_ranap where no_rawat='" + norawat + "'");
        rm20 = cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norawat + "'");
        rm21 = cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap_resiko where no_rawat='" + norawat + "'");
        rm22 = cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap_decubitus where no_rawat='" + norawat + "'");
        rm23 = cariInteger("select count(-1) from pemantauan_harian_parental where no_rawat='" + norawat + "'");
        rm24 = cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + norawat + "'");
        rm25 = cariInteger("select count(-1) from protokol_kemoterapi where no_rawat='" + norawat + "'");
        rm26 = cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "'");
        rm27 = cariInteger("select count(-1) from detail_asesmen_ulang_resiko_jatuh where no_rawat='" + norawat + "'");
        rm28 = cariInteger("select count(-1) from pengelolaan_transfusi_darah where no_rawat='" + norawat + "'");
        rm29 = cariInteger("select count(-1) from monitoring_ews_dewasa where no_rawat='" + norawat + "'");
        rm30 = cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + norawat + "'");
        rm31 = cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap_resiko where no_rawat='" + norawat + "'");
        rm32 = cariInteger("select count(-1) from asesmen_medik_anak_ranap where no_rawat='" + norawat + "'");
        rm33 = cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "'");
        rm34 = cariInteger("select count(-1) from detail_asesmen_ulang_resiko_jatuh_anak where no_rawat='" + norawat + "'");
        rm35 = cariInteger("select count(-1) from monitoring_pews_anak where no_rawat='" + norawat + "'");
        rm36 = cariInteger("select count(-1) from asesmen_restrain where no_rawat='" + norawat + "'");
        rm37 = cariInteger("select count(-1) from observasi_restrain where no_rawat='" + norawat + "'");
        rm38 = cariInteger("select count(-1) from skrining_gizi_ulang where no_rawat='" + norawat + "'");
        rm39 = cariInteger("select count(-1) from monev_asuhan_gizi where no_rawat='" + norawat + "'");
        rm40 = cariInteger("select count(-1) from assesmen_gizi_ulang where no_rawat='" + norawat + "'");
        rm41 = cariInteger("select count(-1) from lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'");
        rm42 = cariInteger("select count(-1) from detail_lembar_observasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'");
        rm43 = cariInteger("select count(-1) from transfer_sebelum_tindakan where no_rawat='" + norawat + "' and ruang_rawat not in ('IGD','PONEK - VK BERSALIN')");
        rm44 = cariInteger("select count(-1) from transfer_sesudah_tindakan where no_rawat='" + norawat + "' and ruang_rawat not in ('IGD','PONEK - VK BERSALIN')");
        rm45 = cariInteger("select count(-1) from asesmen_pra_sedasi where no_rawat='" + norawat + "' and ruang_rawat not like '%igd%'");
        rm46 = cariInteger("select count(-1) from ceklis_pra_operasi where no_rawat='" + norawat + "'");
        rm47 = cariInteger("select count(-1) from ceklis_kesiapan_anestesi where no_rawat='" + norawat + "'");
        rm48 = cariInteger("select count(-1) from asesmen_pre_induksi where no_rawat='" + norawat + "'");
        rm49 = cariInteger("select count(-1) from asesmen_keperawatan_perioperatif where no_rawat='" + norawat + "'");
        rm50 = cariInteger("select count(-1) from hitungan_asesmen_keperawatan_perioperatif where no_rawat='" + norawat + "'");
        rm51 = cariInteger("select count(-1) from perencanaan_pulang_ranap where no_rawat='" + norawat + "'");
        rm52 = cariInteger("select count(-1) from ceklis_keselamatan_operasi1 where no_rawat='" + norawat + "'");
        rm53 = cariInteger("select count(-1) from ceklis_keselamatan_operasi2 where no_rawat='" + norawat + "'");
        rm54 = cariInteger("select count(-1) from ceklis_keselamatan_operasi3 where no_rawat='" + norawat + "'");
        rm55 = cariInteger("select count(-1) from catatan_material_operasi where no_rawat='" + norawat + "'");
        rm56 = cariInteger("select count(-1) from asesmen_medik_bedah_ranap where no_rawat='" + norawat + "'");
        rm57 = cariInteger("select count(-1) from asesmen_medik_perinatologi where no_rawat='" + norawat + "'");
        rm58 = cariInteger("select count(-1) from skor_apgar_downe_cap_jari_perinatologi where no_rawat='" + norawat + "'");
        rm59 = cariInteger("select count(-1) from pengamatan_menyusui_perinatologi where no_rawat='" + norawat + "'");        
        rm60 = cariInteger("select count(-1) from rekonsiliasi_obat where no_rawat='" + norawat + "'");
        rm61 = cariInteger("select count(-1) from rekonsiliasi_obat_igd where no_rawat='" + norawat + "'");
        rm62 = cariInteger("select count(-1) from serah_terima_bayi_pulang_perinatologi where no_rawat='" + norawat + "'");
        rm63 = cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + norawat + "'");
        rm64 = cariInteger("select count(-1) from penilaian_informasi_edukasi where no_rawat='" + norawat + "'");
        rm65 = cariInteger("select count(-1) from monitoring_ews_obsgyn where no_rawat='" + norawat + "'");
        rm66 = cariInteger("select count(-1) from general_consent where no_rawat='" + norawat + "'");
        rm67 = cariInteger("select count(-1) from persetujuan_ranap where no_rawat='" + norawat + "'");
        rm68 = cariInteger("select count(-1) from surat_pernyataan_ranap_bpjs where no_rawat='" + norawat + "'");
        rm69 = cariInteger("select count(-1) from surat_pernyataan_naik_kelas_bpjs where no_rawat='" + norawat + "'");
        rm70 = cariInteger("select count(-1) from surat_pernyataan_bukan_kll where no_rawat='" + norawat + "'");
        rm71 = cariInteger("select count(-1) from surat_pernyataan_bayar_denda where no_rawat='" + norawat + "'");
        rm72 = cariInteger("select count(-1) from peserta_bpjs_bayar_denda where no_rawat='" + norawat + "'");
        rm73 = cariInteger("select count(-1) from surat_pernyataan_ranap_non_bpjs where no_rawat='" + norawat + "'");
        rm74 = cariInteger("select count(-1) from observasi_kala1_kebidanan where no_rawat='" + norawat + "'");
        rm75 = cariInteger("select count(-1) from partograf_air_ketuban where no_rawat='" + norawat + "'");
        rm76 = cariInteger("select count(-1) from partograf_catatan_persalinan where no_rawat='" + norawat + "'");
        rm77 = cariInteger("select count(-1) from partograf_djj where no_rawat='" + norawat + "'");
        rm78 = cariInteger("select count(-1) from partograf_kala_4 where no_rawat='" + norawat + "'");
        rm79 = cariInteger("select count(-1) from partograf_kontraksi where no_rawat='" + norawat + "'");
        rm80 = cariInteger("select count(-1) from partograf_nadi_tensi where no_rawat='" + norawat + "'");
        rm81 = cariInteger("select count(-1) from partograf_obat_cairan where no_rawat='" + norawat + "'");
        rm82 = cariInteger("select count(-1) from partograf_oksitosin where no_rawat='" + norawat + "'");
        rm83 = cariInteger("select count(-1) from partograf_pemantauan_kala4 where no_rawat='" + norawat + "'");
        rm84 = cariInteger("select count(-1) from partograf_pembukaan_serviks where no_rawat='" + norawat + "'");
        rm85 = cariInteger("select count(-1) from partograf_persalinan where no_rawat='" + norawat + "'");
        rm86 = cariInteger("select count(-1) from partograf_suhu where no_rawat='" + norawat + "'");
        rm87 = cariInteger("select count(-1) from partograf_urin where no_rawat='" + norawat + "'");
        rm88 = cariInteger("select count(-1) from laporan_operasi where no_rawat='" + norawat + "'");
        rm89 = cariInteger("select count(-1) from laporan_operasi_obs_ttv where no_rawat='" + norawat + "'");
        rm90 = cariInteger("select count(-1) from catatan_ruang_pemulihan where no_rawat='" + norawat + "'");
        rm91 = cariInteger("select count(-1) from catatan_ruang_pemulihan_obs_ttv where no_rawat='" + norawat + "'");
        rm92 = cariInteger("select count(-1) from formulir_site_marking_operasi where no_rawat='" + norawat + "'");
        
        if (rm1 > 0) {
            tbl1 = "catatan_tindakan_keperawatan\n";
        } else {
            tbl1 = "";
        }
        
        if (rm2 > 0) {
            tbl2 = "evaluasi_catatan_tindakan_keperawatan\n";
        } else {
            tbl2 = "";
        }
        
        if (rm3 > 0) {
            tbl3 = "manajemen_catatan_tindakan_keperawatan\n";
        } else {
            tbl3 = "";
        }
        
        if (rm4 > 0) {
            tbl4 = "surat_konsul_unit_ranap\n";
        } else {
            tbl4 = "";
        }
        
        if (rm5 > 0) {
            tbl5 = "asesmen_medik_dewasa_ranap\n";
        } else {
            tbl5 = "";
        }
        
        if (rm6 > 0) {
            tbl6 = "ringkasan_pulang_ranap\n";
        } else {
            tbl6 = "";
        }
        
        if (rm7 > 0) {
            tbl7 = "cppt\n";
        } else {
            tbl7 = "";
        }
        
        if (rm8 > 0) {
            tbl8 = "cppt_konfirmasi_terapi\n";
        } else {
            tbl8 = "";
        }
        
        if (rm9 > 0) {
            tbl9 = "transfer_serah_terima_pasien_igd\n";
        } else {
            tbl9 = "";
        }
        
        if (rm10 > 0) {
            tbl10 = "pemberian_obat\n";
        } else {
            tbl10 = "";
        }
        
        if (rm11 > 0) {
            tbl11 = "pelaksana_pemberian_obat\n";
        } else {
            tbl11 = "";
        }
        
        if (rm12 > 0) {
            tbl12 = "surat_tindakan_kedokteran\n";
        } else {
            tbl12 = "";
        }
        
        if (rm13 > 0) {
            tbl13 = "spirometri\n";
        } else {
            tbl13 = "";
        }
        
        if (rm14 > 0) {
            tbl14 = "data_persalinan\n";
        } else {
            tbl14 = "";
        }
        
        if (rm15 > 0) {
            tbl15 = "permintaan_lab_raza\n";
        } else {
            tbl15 = "";
        }
        
        if (rm16 > 0) {
            tbl16 = "permintaan_radiologi\n";
        } else {
            tbl16 = "";
        }
        
        if (rm17 > 0) {
            tbl17 = "surat_istirahat_sakit\n";
        } else {
            tbl17 = "";
        }
        
        if (rm18 > 0) {
            tbl18 = "surat_keterangan_sakit\n";
        } else {
            tbl18 = "";
        }
        
        if (rm19 > 0) {
            tbl19 = "catatan_resep_ranap\n";
        } else {
            tbl19 = "";
        }
        
        if (rm20 > 0) {
            tbl20 = "penilaian_awal_keperawatan_dewasa_ranap\n";
        } else {
            tbl20 = "";
        }
        
        if (rm21 > 0) {
            tbl21 = "penilaian_awal_keperawatan_dewasa_ranap_resiko\n";
        } else {
            tbl21 = "";
        }
        
        if (rm22 > 0) {
            tbl22 = "penilaian_awal_keperawatan_dewasa_ranap_decubitus\n";
        } else {
            tbl22 = "";
        }
        
        if (rm23 > 0) {
            tbl23 = "pemantauan_harian_parental\n";
        } else {
            tbl23 = "";
        }
        
        if (rm24 > 0) {
            tbl24 = "pemantauan_harian_24jam\n";
        } else {
            tbl24 = "";
        }
        
        if (rm25 > 0) {
            tbl25 = "protokol_kemoterapi\n";
        } else {
            tbl25 = "";
        }
        
        if (rm26 > 0) {
            tbl26 = "asesmen_ulang_resiko_jatuh\n";
        } else {
            tbl26 = "";
        }
        
        if (rm27 > 0) {
            tbl27 = "detail_asesmen_ulang_resiko_jatuh\n";
        } else {
            tbl27 = "";
        }
        
        if (rm28 > 0) {
            tbl28 = "pengelolaan_transfusi_darah\n";
        } else {
            tbl28 = "";
        }
        
        if (rm29 > 0) {
            tbl29 = "monitoring_ews_dewasa\n";
        } else {
            tbl29 = "";
        }
        
        if (rm30 > 0) {
            tbl30 = "penilaian_awal_keperawatan_anak_ranap\n";
        } else {
            tbl30 = "";
        }
        
        if (rm31 > 0) {
            tbl31 = "penilaian_awal_keperawatan_anak_ranap_resiko\n";
        } else {
            tbl31 = "";
        }
        
        if (rm32 > 0) {
            tbl32 = "asesmen_medik_anak_ranap\n";
        } else {
            tbl32 = "";
        }
        
        if (rm33 > 0) {
            tbl33 = "asesmen_ulang_resiko_jatuh_anak\n";
        } else {
            tbl33 = "";
        }
        
        if (rm34 > 0) {
            tbl34 = "detail_asesmen_ulang_resiko_jatuh_anak\n";
        } else {
            tbl34 = "";
        }
        
        if (rm35 > 0) {
            tbl35 = "monitoring_pews_anak\n";
        } else {
            tbl35 = "";
        }
        
        if (rm36 > 0) {
            tbl36 = "asesmen_restrain\n";
        } else {
            tbl36 = "";
        }
        
        if (rm37 > 0) {
            tbl37 = "observasi_restrain\n";
        } else {
            tbl37 = "";
        }
        
        if (rm38 > 0) {
            tbl38 = "skrining_gizi_ulang\n";
        } else {
            tbl38 = "";
        }
        
        if (rm39 > 0) {
            tbl39 = "monev_asuhan_gizi\n";
        } else {
            tbl39 = "";
        }
        
        if (rm40 > 0) {
            tbl40 = "assesmen_gizi_ulang\n";
        } else {
            tbl40 = "";
        }
        
        if (rm41 > 0) {
            tbl41 = "lembar_observasi\n";
        } else {
            tbl41 = "";
        }
        
        if (rm42 > 0) {
            tbl42 = "detail_lembar_observasi\n";
        } else {
            tbl42 = "";
        }
        
        if (rm43 > 0) {
            tbl43 = "transfer_sebelum_tindakan\n";
        } else {
            tbl43 = "";
        }
        
        if (rm44 > 0) {
            tbl44 = "transfer_sesudah_tindakan\n";
        } else {
            tbl44 = "";
        }
        
        if (rm45 > 0) {
            tbl45 = "asesmen_pra_sedasi\n";
        } else {
            tbl45 = "";
        }
        
        if (rm46 > 0) {
            tbl46 = "ceklis_pra_operasi\n";
        } else {
            tbl46 = "";
        }
        
        if (rm47 > 0) {
            tbl47 = "ceklis_kesiapan_anestesi\n";
        } else {
            tbl47 = "";
        }
        
        if (rm48 > 0) {
            tbl48 = "asesmen_pre_induksi\n";
        } else {
            tbl48 = "";
        }
        
        if (rm49 > 0) {
            tbl49 = "asesmen_keperawatan_perioperatif\n";
        } else {
            tbl49 = "";
        }
        
        if (rm50 > 0) {
            tbl50 = "hitungan_asesmen_keperawatan_perioperatif\n";
        } else {
            tbl50 = "";
        }
        
        if (rm51 > 0) {
            tbl51 = "perencanaan_pulang_ranap\n";
        } else {
            tbl51 = "";
        }
        
        if (rm52 > 0) {
            tbl52 = "ceklis_keselamatan_operasi1\n";
        } else {
            tbl52 = "";
        }
        
        if (rm53 > 0) {
            tbl53 = "ceklis_keselamatan_operasi2\n";
        } else {
            tbl53 = "";
        }
        
        if (rm54 > 0) {
            tbl54 = "ceklis_keselamatan_operasi3\n";
        } else {
            tbl54 = "";
        }
        
        if (rm55 > 0) {
            tbl55 = "catatan_material_operasi\n";
        } else {
            tbl55 = "";
        }
        
        if (rm56 > 0) {
            tbl56 = "asesmen_medik_bedah_ranap\n";
        } else {
            tbl56 = "";
        }
        
        if (rm57 > 0) {
            tbl57 = "asesmen_medik_perinatologi\n";
        } else {
            tbl57 = "";
        }
        
        if (rm58 > 0) {
            tbl58 = "skor_apgar_downe_cap_jari_perinatologi\n";
        } else {
            tbl58 = "";
        }
        
        if (rm59 > 0) {
            tbl59 = "pengamatan_menyusui_perinatologi\n";
        } else {
            tbl59 = "";
        }
        
        if (rm60 > 0) {
            tbl60= "rekonsiliasi_obat\n";
        } else {
            tbl60 = "";
        }
        
        if (rm61 > 0) {
            tbl61= "rekonsiliasi_obat_igd\n";
        } else {
            tbl61 = "";
        }
        
        if (rm62 > 0) {
            tbl62= "serah_terima_bayi_pulang_perinatologi\n";
        } else {
            tbl62 = "";
        }
        
        if (rm63 > 0) {
            tbl63= "pemberian_informasi_edukasi\n";
        } else {
            tbl63 = "";
        }
        
        if (rm64 > 0) {
            tbl64= "penilaian_informasi_edukasi\n";
        } else {
            tbl64 = "";
        }
        
        if (rm65 > 0) {
            tbl65= "monitoring_ews_obsgyn\n";
        } else {
            tbl65 = "";
        }
        
        if (rm66 > 0) {
            tbl66= "general_consent\n";
        } else {
            tbl66 = "";
        }
        
        if (rm67 > 0) {
            tbl67= "persetujuan_ranap\n";
        } else {
            tbl67 = "";
        }
        
        if (rm68 > 0) {
            tbl68= "surat_pernyataan_ranap_bpjs\n";
        } else {
            tbl68 = "";
        }
        
        if (rm69 > 0) {
            tbl69= "surat_pernyataan_naik_kelas_bpjs\n";
        } else {
            tbl69 = "";
        }
        
        if (rm70 > 0) {
            tbl70= "surat_pernyataan_bukan_kll\n";
        } else {
            tbl70 = "";
        }
        
        if (rm71 > 0) {
            tbl71= "surat_pernyataan_bayar_denda\n";
        } else {
            tbl71 = "";
        }
        
        if (rm72 > 0) {
            tbl72= "peserta_bpjs_bayar_denda\n";
        } else {
            tbl72 = "";
        }
        
        if (rm73 > 0) {
            tbl73= "surat_pernyataan_ranap_non_bpjs\n";
        } else {
            tbl73 = "";
        }
        
        if (rm74 > 0) {
            tbl74= "observasi_kala1_kebidanan\n";
        } else {
            tbl74 = "";
        }

        if (rm75 > 0) {
            tbl75 = "partograf_air_ketuban\n";
        } else {
            tbl75 = "";
        }

        if (rm76 > 0) {
            tbl76 = "partograf_catatan_persalinan\n";
        } else {
            tbl76 = "";
        }

        if (rm77 > 0) {
            tbl77 = "partograf_djj\n";
        } else {
            tbl77 = "";
        }

        if (rm78 > 0) {
            tbl78 = "partograf_kala_4\n";
        } else {
            tbl78 = "";
        }

        if (rm79 > 0) {
            tbl79 = "partograf_kontraksi\n";
        } else {
            tbl79 = "";
        }

        if (rm80 > 0) {
            tbl80 = "partograf_nadi_tensi\n";
        } else {
            tbl80 = "";
        }

        if (rm81 > 0) {
            tbl81 = "partograf_obat_cairan\n";
        } else {
            tbl81 = "";
        }

        if (rm82 > 0) {
            tbl82 = "partograf_oksitosin\n";
        } else {
            tbl82 = "";
        }

        if (rm83 > 0) {
            tbl83 = "partograf_pemantauan_kala4\n";
        } else {
            tbl83 = "";
        }

        if (rm84 > 0) {
            tbl84 = "partograf_pembukaan_serviks\n";
        } else {
            tbl84 = "";
        }

        if (rm85 > 0) {
            tbl85 = "partograf_persalinan\n";
        } else {
            tbl85 = "";
        }

        if (rm86 > 0) {
            tbl86 = "partograf_suhu\n";
        } else {
            tbl86 = "";
        }

        if (rm87 > 0) {
            tbl87 = "partograf_urin\n";
        } else {
            tbl87 = "";
        }
        
        if (rm88 > 0) {
            tbl88 = "laporan_operasi\n";
        } else {
            tbl88 = "";
        }
        
        if (rm89 > 0) {
            tbl89 = "laporan_operasi_obs_ttv\n";
        } else {
            tbl89 = "";
        }
        
        if (rm90 > 0) {
            tbl90 = "catatan_ruang_pemulihan\n";
        } else {
            tbl90 = "";
        }
        
        if (rm91 > 0) {
            tbl91 = "catatan_ruang_pemulihan_obs_ttv\n";
        } else {
            tbl91 = "";
        }
        
        if (rm92 > 0) {
            tbl92 = "formulir_site_marking_operasi\n";
        } else {
            tbl92 = "";
        }
        
        angka = rm1 + rm2 + rm3 + rm4 + rm5 + rm6 + rm7 + rm8 + rm9 + rm10 + rm11 + rm12 + rm13 + rm14 + rm15 + rm16 + rm17 + rm18 + rm19
                + rm20 + rm21 + rm22 + rm23 + rm24 + rm25 + rm26 + rm27 + rm28 + rm29 + rm30 + rm31 + rm32 + rm33 + rm34 + rm35 + rm36 + rm37
                + rm38 + rm39 + rm40 + rm41 + rm42 + rm43 + rm44 + rm45 + rm46 + rm47 + rm48 + rm49 + rm50 + rm51 + rm52 + rm53 + rm54 + rm55 + rm56
                + rm57 + rm58 + rm59 + rm60 + rm61 + rm62 + rm63 + rm64 + rm65 + rm66 + rm67 + rm68 + rm69 + rm70 + rm71 + rm72 + rm73 + rm74
                + rm75 + rm76 + rm77 + rm78 + rm79 + rm80 + rm81 + rm82 + rm83 + rm84 + rm85 + rm86 + rm87 + rm88 + rm89 + rm90 + rm91 + rm92;
        
        if (angka > 0) {
            System.out.println("\nPesan  : no. rawat " + norawat + " utk. data e-RM nya masih ada tersimpan ditabel berikut ini :\n"
                    + tbl1 + tbl2 + tbl3 + tbl4 + tbl5 + tbl6 + tbl7 + tbl8 + tbl9 + tbl10 + tbl11 + tbl12 + tbl13 + tbl14 + tbl15 + tbl16 + tbl17 + tbl18
                    + tbl19 + tbl20 + tbl21 + tbl22 + tbl23 + tbl24 + tbl25 + tbl26 + tbl27 + tbl28 + tbl29 + tbl30 + tbl31 + tbl32 + tbl33 + tbl34 + tbl35
                    + tbl36 + tbl37 + tbl38 + tbl39 + tbl40 + tbl41 + tbl42 + tbl43 + tbl44 + tbl45 + tbl46 + tbl47 + tbl48 + tbl49 + tbl50 + tbl51 + tbl52
                    + tbl53 + tbl54 + tbl55 + tbl56 + tbl57 + tbl58 + tbl59 + tbl60 + tbl61 + tbl62 + tbl63 + tbl64 + tbl65 + tbl66 + tbl67 + tbl68 + tbl69
                    + tbl70 + tbl71 + tbl72 + tbl73 + tbl74 + tbl75 + tbl76 + tbl77 + tbl78 + tbl79 + tbl80 + tbl81 + tbl82 + tbl83 + tbl84 + tbl85 + tbl86
                    + tbl87 + tbl88 + tbl89 + tbl90 + tbl91 + tbl92
            );
        }

        return angka;
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
    
    public int cekPernahBayarLunas(String norwt, String sttsRwt, String kdpolinya) {
        angka = 0;
        try {
            ps = connect.prepareStatement("select count(-1) from reg_periksa rp inner join tagihan_sadewa ts on ts.no_nota =rp.no_rawat where "
                    + "rp.kd_pj ='U01' and rp.status_lanjut='" + sttsRwt + "' and rp.kd_poli='" + kdpolinya + "' and ts.jenis_bayar ='Pelunasan' "
                    + "and ts.status='Sudah' and rp.no_rawat='" + norwt + "'");
            try {
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
    
    public int cariRealCostPiutang(String norawat) {
        angka = 0;
        try {
            ps = connect.prepareStatement("select count(-1) from piutang_pasien where no_rawat=?");
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
    
    public int cariSelisihTarifInacbg(String norawat) {
        angka = 0;
        try {
            ps = connect.prepareStatement("select count(-1) from biaya_naik_kelas_bpjs where no_rawat=?");
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
    
    public String cekUmurPasien(String norm) {
        umurOK = "";
        umur = "0";
        sttsumur = "Th";
        try {
            ps = connect.prepareStatement("select TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari "
                    + "FROM pasien WHERE no_rkm_medis ='" + norm + "'");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("tahun") > 0) {
                        umur = rs.getString("tahun");
                        sttsumur = "Th";
                    } else if (rs.getInt("tahun") == 0) {
                        if (rs.getInt("bulan") > 0) {
                            umur = rs.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rs.getInt("bulan") == 0) {
                            umur = rs.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                }
                umurOK = umur + " " + sttsumur;
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
        
        return umurOK;
    }
    
    public String eksekusiQuery(String sql) {
        dicari = "";
        try {
            ps = connect.prepareStatement(sql);
            try {
                rs = ps.executeQuery();
                if (rs.next()) {                    
                    System.out.println("Query Sql yang diterapkan sudah benar..!!");
                    dicari = "ok";
                } else {
                    System.out.println("Query Sql yang diterapkan masih salah, periksa lagi penulisanya..!!");
                    dicari = "gagal";
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Query Sql yang diterapkan masih salah, periksa lagi penulisanya berikut erornya : \n" + e);
                System.out.println("Error Query Sql Salah : " + e);
                dicari = "gagal";
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Query Sql yang diterapkan masih salah, periksa lagi penulisanya berikut erornya : \n" + e);
            System.out.println("Error Query Sql Salah : " + e);
            dicari = "gagal";
        }
        return dicari;
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
    
    public String cariFolderVersi() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            //cek jenis os dulu
            String os = System.getProperty("os.name").toLowerCase();
            
            if (os.contains("win")) {
                dicari = prop.getProperty("LOKASIFILEVERSIWINDOWS").toString();
            } else if (os.contains("linux") || os.contains("unix") || os.contains("mac")) {
                String home = System.getProperty("user.home");
                Path path = Paths.get(home, prop.getProperty("LOKASIFILEVERSILINUXMAC").toString());
                dicari = Files.createDirectories(path).toString();
            } else {
                System.out.println("Sistem operasi tidak dikenali: " + os);                
            }
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
    
    public void menyimpanQrTte(String table, String value, String sama, String AlmGb) {
        try {
            ps = connect.prepareStatement("insert into " + table + " values(" + value + ",?)");
            try {
                //jika file gambar tidak ada
                if (AlmGb == null || AlmGb.trim().equals("")) {
                    ps.setNull(1, java.sql.Types.BLOB);
                } else {
                    File file = new File(AlmGb);
                    if (!file.exists()) {
                        // file tidak ada → tetap kirim NULL
                        ps.setNull(1, java.sql.Types.BLOB);
                    } else {
                        FileInputStream fis = new FileInputStream(file);
                        ps.setBinaryStream(1, fis, file.length());
                    }
//                    ps.setBinaryStream(1, new FileInputStream(AlmGb), new File(AlmGb).length());
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
                System.out.println("Maaf, gagal menyimpan data. Kemungkinan ada " + sama + " yang sama dimasukkan sebelumnya...!");
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
                        hari = "Jumat";
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
    
    public String hariINDONESIAnamaHari(String namahari) {
        hari = "";        
        if (namahari.equals("Monday")) {
            hari = "Senin";
        }

        if (namahari.equals("Tuesday")) {
            hari = "Selasa";
        }

        if (namahari.equals("Wednesday")) {
            hari = "Rabu";
        }

        if (namahari.equals("Thursday")) {
            hari = "Kamis";
        }

        if (namahari.equals("Friday")) {
            hari = "Jumat";
        }

        if (namahari.equals("Saturday")) {
            hari = "Sabtu";
        }

        if (namahari.equals("Sunday")) {
            hari = "Minggu";
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
    
    public void hapusIisiFolder(String foldernya) {
        String folderPath = foldernya;

        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                        System.out.println("Deleted file : " + file.getName());
                    }
                }
                System.out.println("Semua file berhasil dihapus!");
            }
        } else {
            System.out.println("Folder tidak ditemukan!");
        }
    }

    public String cariFolderTte() {
        dicari = "";
        foldernya = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            //cek jenis os dulu
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                foldernya = "C:\\QRTte"; // tentukan nama folder
                File folder = new File(foldernya);
                if (!folder.exists()) {     // cek apakah folder sudah ada
                    if (folder.mkdirs()) {  // bikin folder jika belum ada
                        System.out.println("Folder diwindows berhasil dibikin karena belum ada : " + foldernya);
                    } else {
                        System.out.println("Gagal membuat folder!");
                    }
                } else {
                    System.out.println("Folder diwindows sudah ada, tidak perlu membuat folder C:\\QRTte.");
                }

                dicari = prop.getProperty("FOLDERQRTTEWIN").toString();
            } else if (os.contains("linux") || os.contains("unix") || os.contains("mac")) {
                String userHome = System.getProperty("user.home");
                foldernya = prop.getProperty("FOLDERQRTTELINUXMAC");
                Path path = Paths.get(userHome, foldernya);

                if (Files.notExists(path)) {  // lebih jelas daripada !exists
                    // bikin folder
                    Files.createDirectories(path);
                    System.out.println("Folder dilinux/mac berhasil dibikin karena belum ada : " + path);
                } else {
                    System.out.println("Folder dilinux/mac sudah ada : " + path);
                }

                dicari = Files.createDirectories(path).toString();
            } else {
                System.out.println("Sistem operasi tidak dikenali: " + os);
            }            

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public String cariFolderKodeResepIter() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERQRITER").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public String cariFolderPrintTte() {
        dicari = "";
        foldernya = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String os = System.getProperty("os.name").toLowerCase();            
            
            if (os.contains("win")) {
                dicari = prop.getProperty("FOLDERPRINTQRTTEWIN").toString();
            } else if (os.contains("linux") || os.contains("unix") || os.contains("mac")) {
                String userHome = System.getProperty("user.home");
                foldernya = prop.getProperty("FOLDERQRTTELINUXMAC");
                Path path = Paths.get(userHome, foldernya);
                Files.createDirectories(path);
                dicari = path.toString() + File.separator + prop.getProperty("FOLDERPRINTQRTTELINUXMAC").toString();
            } else {
                System.out.println("Sistem operasi tidak dikenali: " + os);
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public String cariFolderPrintKodeIter() {
        dicari = "";
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            dicari = prop.getProperty("FOLDERPRINTQRITER").toString();

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        return dicari;
    }
    
    public static String convertPdfToBase64(String filePath) {
        File pdfFile = new File(filePath);
        FileInputStream fis = null;
        byte[] bytes = null;
        try {
            fis = new FileInputStream(pdfFile);
            bytes = new byte[(int) pdfFile.length()];
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Base64.getEncoder().encodeToString(bytes);
    }
    
    public boolean hapusFileTTD(String idFilenya) {
        bool = false;
        String ipGambar = "";
        try {
            //cek atau ping ip addres
            ipGambar = "192.168.0.230";
            InetAddress inet = InetAddress.getByName(ipGambar);

            //ping sukses timeout 100 ms (0.1 detik)
            if (inet.isReachable(100)) {
                String idFile = idFilenya;
                String urlHapus = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/hapus";
                URL url = new URL(urlHapus);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                String body = "id_file=" + URLEncoder.encode(idFile, "UTF-8");

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(body.getBytes(StandardCharsets.UTF_8));
                }

                int responseCode = conn.getResponseCode();

                if (responseCode == 200) {
                    // sukses hapus
                    String urlPreview = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + URLEncoder.encode(idFile, "UTF-8");
                    queryu("delete from ttd_erm_keluarga_pasien where id_file='" + idFilenya + "'");
                    bool = true;                    
                } else {
                    bool = false;
                    JOptionPane.showMessageDialog(null, "Gagal menghapus TTD..!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan koneksi");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        return bool;
    }
    
    public void hapusSemuaTtd(String kodeFile) {
        String ipGambar = "";
        try {
            //cek atau ping ip addres
            ipGambar = "192.168.0.230";
            InetAddress inet = InetAddress.getByName(ipGambar);

            //ping sukses timeout 100 ms (0.1 detik)
            if (inet.isReachable(100)) {
                try {
                    String idFile = kodeFile;
                    String urlHapus = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/hapus";
                    URL url = new URL(urlHapus);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    String body = "id_file=" + URLEncoder.encode(idFile, "UTF-8");

                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(body.getBytes(StandardCharsets.UTF_8));
                    }

                    int responseCode = conn.getResponseCode();

                    if (responseCode == 200) {
                        // sukses → refresh preview
                        String urlPreview = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + URLEncoder.encode(idFile, "UTF-8");
                        queryu("delete from ttd_erm_keluarga_pasien where id_file='" + kodeFile + "'");
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus TTD..!!");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan koneksi");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
