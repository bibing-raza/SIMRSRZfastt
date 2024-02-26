/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author khanzasoft
 */
public final class koneksiDB {
    public koneksiDB() {
    }
    private static Connection connection = null;
    private static final Properties prop = new Properties();
    private static final MysqlDataSource dataSource = new MysqlDataSource();
    private static String caricepat = "", host = "", database = "", port = "", user = "", pas = "";
    private static sekuel Sequel = new sekuel();
    private static String var="";

    public static Connection condb() {
        if (connection == null) {
            try {
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                host = Sequel.decXML2(prop.getProperty("HOST"), prop.getProperty("KEY"));
                database = Sequel.decXML2(prop.getProperty("DATABASE"), prop.getProperty("KEY"));
                port = Sequel.decXML2(prop.getProperty("PORT"), prop.getProperty("KEY"));
                user = Sequel.decXML2(prop.getProperty("USER"), prop.getProperty("KEY"));
                pas = Sequel.decXML2(prop.getProperty("PAS"), prop.getProperty("KEY"));
                //dataSource.setURL("jdbc:mysql://"+prop.getProperty("HOST")+":"+prop.getProperty("PORT")+"/"+prop.getProperty("DATABASE")+"?zeroDateTimeBehavior=convertToNull");
                dataSource.setURL("jdbc:mysql://" + host + ":" + port + "/" + database + "?zeroDateTimeBehavior=convertToNull");
                dataSource.setUser(user);
                dataSource.setPassword(pas);
                connection = dataSource.getConnection();
                System.out.println("Koneksi Berhasil. Sedang verifikasi data, silakan tunggu sebentar.... \n\n"
                        + "    ____  ___  __  __  ____   ____   _   _  ____      ____                    \n"
                        + "   / ___||_ _||  \\/  ||  _ \\ / ___| | | | ||  _ \\    |  _ \\   __ _  ____ __ _  \n"
                        + "   \\___ \\ | | | |\\/| || |_) |\\___ \\ | | | || | \\ |   | |_) | / _` ||_  // _` | \n"
                        + "    ___) || | | |  | ||  _ <  ___) || |_| || |_/ |   |  _ < | (_| | / /| (_| | \n"
                        + "   |____/|___||_|  |_||_| \\_\\|____/  \\___/ |____/    |_| \\_\\ \\__,_|/___|\\__,_| \n"
                        + "                                                                            \n\n"
                        + "    POWERED BY. UNIT SIMRS RSUD Ratu Zalecha                                   \n"
                        + "                                                                           ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Koneksi Putus : " + e);
            }            
        }
        return connection;
    }
    
    public static String cariCepat(){
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            caricepat = prop.getProperty("CARICEPAT");
        } catch (Exception e) {
            caricepat = "tidak aktif";
        }
        return caricepat;
    }
    
    public static String AKTIFKANTRACKSQL(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("AKTIFKANTRACKSQL");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String HOSTWSADAM(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("HOSTWSADAM");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }  
    
    public static String PASSCORONA(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=Sequel.decXML2(prop.getProperty("PASSCORONA"), prop.getProperty("KEY"));
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String URLAPICORONA(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("URLAPICORONA");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String IDCORONA(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=Sequel.decXML2(prop.getProperty("IDCORONA"), prop.getProperty("KEY"));
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String HOSTWEBAPPS(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=Sequel.decXML2(prop.getProperty("HOSTWEBAPPS"), prop.getProperty("KEY"));
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String HYBRIDWEB(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("HYBRIDWEB");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String PORTWEB(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("PORTWEB");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String URLAPIBPJS(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("URLAPIBPJS");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String CONSIDAPIBPJS(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY"));
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String SECRETKEYAPIBPJS(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("SECRETKEYAPIBPJS"), prop.getProperty("KEY"));
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String URL_EKLAIM_INACBG(){
        try{
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var=prop.getProperty("URLEKLAIMINACBG");
        }catch(Exception e){
            var=""; 
        }
        return var;
    }
    
    public static String USERKEYAPIBPJS(){
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("USERKEYAPIBPJS"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIMOBILEJKN");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String SECRETKEYAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("SECRETKEYAPIMOBILEJKN"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CONSIDAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("CONSIDAPIMOBILEJKN"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERKEYAPIMOBILEJKN() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("USERKEYAPIMOBILEJKN"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String IDSITT(){
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("IDSITT"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String PASSSITT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("PASSSITT"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPISITT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPISITT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String URLAPIKANKER() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIKANKER");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String KODERSKANKER() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("KODERSKANKER"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String PASSKANKER() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("PASSKANKER"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String NAMAPRINTER1() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("NAMAPRINTER1");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String NAMAPRINTER2() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("NAMAPRINTER2");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String NAMAPRINTER3() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("NAMAPRINTER3");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String URLAPIICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAPIICARE");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String SECRETKEYAPIICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("SECRETKEYAPIICARE"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String CONSIDAPIICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("CONSIDAPIICARE"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String USERKEYAPIICARE() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("USERKEYAPIICARE"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String SECRETKEYSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("SECRETKEYSATUSEHAT"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String CLIENTIDSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("CLIENTIDSATUSEHAT"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String IDSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = Sequel.decXML2(prop.getProperty("IDSATUSEHAT"), prop.getProperty("KEY"));
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String URLAUTHSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLAUTHSATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String URLFHIRSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("URLFHIRSATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KELURAHANSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KELURAHANSATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KECAMATANSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KECAMATANSATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KABUPATENSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KABUPATENSATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String PROPINSISATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("PROPINSISATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }

    public static String KODEPOSSATUSEHAT() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = prop.getProperty("KODEPOSSATUSEHAT");
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
    
    public static String HOSTport() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            var = "http://" + Sequel.decXML2(prop.getProperty("HOSTport"), prop.getProperty("KEY")) + "/";
        } catch (Exception e) {
            var = "";
        }
        return var;
    }
}