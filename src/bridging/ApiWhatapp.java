package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.akses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.Component;
import java.awt.Cursor;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author khanzasoft
 */
public class ApiWhatapp {
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private String URLtext = "", URLfile = "", requestJson1 = "", requestJson2 = "", stringbalik = "";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private sekuel Sequel = new sekuel();
    private boolean x;
    private int i;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();

    public ApiWhatapp() {
        super();
        try {
//            URLtext = koneksiDB.URLWHATSAPPTEXT();
            URLfile = koneksiDB.URLWHATSAPPFILE();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
    
//    public boolean ngirimTeks(String pesanWA, String nohp) {
//        try {
//            headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.add("Content-Type", "application/json;charset=UTF-8");
//            requestJson1
//                    = "{"
//                    + "\"metadata\": {"
//                    + "\"type\": \"teks\""
//                    + "},"
//                    + "\"data\":{"
//                    + "\"pesan\":\"" + pesanWA + "\","
//                    + "\"no_wa_tujuan\":\"" + nohp + "\""
//                    + "}"
//                    + "}";
//
//            System.out.println("JSON : " + requestJson1);
//            requestEntity = new HttpEntity(requestJson1, headers);
//            stringbalik = getRest().exchange(URLtext, HttpMethod.POST, requestEntity, String.class).getBody();
//            System.out.println("Output : " + stringbalik);
//            root = mapper.readTree(stringbalik);
//
//            if (root.path("status").asText().equals("true")) {
//                System.out.println(root.path("msg").asText());
//                x = true;
//            } else {
//                JOptionPane.showMessageDialog(null, root.path("msg").asText());
//                x = false;
//            }
//        } catch (Exception ex) {
//            System.out.println("Notifikasi : " + ex);
//            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
//                JOptionPane.showMessageDialog(null, ex);
//            }
//        }
//        return x;
//    }
    
    public boolean ngirimFile(String nohp, String nmFile, String nmPasien, String dataBase64) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson2
                    = "{"
                    + "\"metadata\": {"
                    + "\"type\": \"file\""
                    + "},"
                    + "\"data\":{"
                    + "\"no_wa_tujuan\":\"" + nohp + "\","
                    + "\"nama_pasien\":\"" + nmPasien + "\","
                    + "\"nama_file\":\"" + nmFile + "\","
                    + "\"file_type\":\"pdf\","
                    + "\"file_to_base64\":\"" + dataBase64 + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson2);
            requestEntity = new HttpEntity(requestJson2, headers);
            stringbalik = getRest().exchange(URLfile, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("status").asText().equals("true")) {
                JOptionPane.showMessageDialog(null, "Data/file berhasil terkirim ke no. whatsapp " + nohp + " ....");
                System.out.println(root.path("msg").asText());
                x = true;
            } else {
                JOptionPane.showMessageDialog(null, root.path("msg").asText());
                x = false;
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return x;
    }
    
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }
}
