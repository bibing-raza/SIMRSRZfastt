/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory;

import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author khanzamedia
 */
public class riwayatobat {
    private final sekuel Sequel=new sekuel();
    private final validasi Valid=new validasi();
    private final Connection koneksi=koneksiDB.condb(); 
    private ResultSet rs,rsawal;
    private PreparedStatement ps,psawal;
    private double stokawal=0,stokakhir=0;
    public void catatRiwayat(String kodebarang,double masuk,double keluar,String posisi,String petugas,String kdbangsal,String status,String Tgl, String Jam){        
        try {
            stokakhir=0;stokawal=0;            
            psawal=koneksi.prepareStatement("select stok from gudangbarang where kode_brng=? and kd_bangsal=?");
            try {
                psawal.setString(1,kodebarang);
                psawal.setString(2,kdbangsal);
                rs=psawal.executeQuery();
                if(rs.next()){
                    stokawal=rs.getDouble("stok");
                    stokakhir=stokawal+masuk-keluar;
                }else{
                    stokawal=0;
                    stokakhir=stokawal+masuk-keluar;
                }
            } catch (Exception e) {
                System.out.println("Notif Stok : "+e);
            } finally{
                if(rsawal!=null){
                    rsawal.close();
                }
                if(psawal!=null){
                    psawal.close();
                }
            }
                     
            ps=koneksi.prepareStatement("insert into riwayat_barang_medis values(?,?,?,?,?,?,?,?,?,?,?,current_timestamp)");
            try {
                if(posisi.equals("Opname")){
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,0);
                    ps.setDouble(5,masuk);
                    ps.setString(6,posisi);
                    ps.setString(7,Tgl);
                    ps.setString(8,Jam);
                    ps.setString(9,petugas);
                    ps.setString(10,kdbangsal);
                    ps.setString(11,status);
                    ps.executeUpdate();
                }else{
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,keluar);
                    ps.setDouble(5,stokakhir);
                    ps.setString(6,posisi);
                    ps.setString(7,Tgl);
                    ps.setString(8,Jam);
                    ps.setString(9,petugas);
                    ps.setString(10,kdbangsal);
                    ps.setString(11,status);
                    ps.executeUpdate();
                }                    
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(ps!=null){
                    ps.close();
                }
            }          
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);  
        }            
   }
    
    public void catatRiwayat2(String kodebarang,double masuk,double keluar,String posisi,String petugas,String kdbangsal,String status){        
        try {
            stokakhir=0;stokawal=0;            
            psawal=koneksi.prepareStatement("select stok from gudangbarang where kode_brng=? and kd_bangsal=?");
            try {
                psawal.setString(1,kodebarang);
                psawal.setString(2,kdbangsal);
                rs=psawal.executeQuery();
                if(rs.next()){
                    stokawal=rs.getDouble("stok");
                    stokakhir=stokawal+masuk-keluar;
                }else{
                    stokawal=0;
                    stokakhir=stokawal+masuk-keluar;
                }
            } catch (Exception e) {
                System.out.println("Notif Stok : "+e);
            } finally{
                if(rsawal!=null){
                    rsawal.close();
                }
                if(psawal!=null){
                    psawal.close();
                }
            }
                     
            ps=koneksi.prepareStatement("insert into riwayat_barang_medis values(?,?,?,?,?,?,current_date(),current_time(),?,?,?,current_timestamp)");
            try {
                if(posisi.equals("Opname")){
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,stokawal);
                    ps.setDouble(5,masuk);
                    ps.setString(6,posisi);
                    ps.setString(7,petugas);
                    ps.setString(8,kdbangsal);
                    ps.setString(9,status);
                    ps.executeUpdate();
                }else{
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,keluar);
                    ps.setDouble(5,stokakhir);
                    ps.setString(6,posisi);
                    ps.setString(7,petugas);
                    ps.setString(8,kdbangsal);
                    ps.setString(9,status);
                    ps.executeUpdate();
                }                   
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(ps!=null){
                    ps.close();
                }
            }          
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);  
        }            
   }
    
    
    public void catatRiwayatObat(String kodebarang,double masuk,double keluar,String posisi,String petugas,String kdbangsal,String status, String noRM, String noPerawatan, String Tgl, String Jam){        
        try {
            stokakhir=0;stokawal=0;            
            psawal=koneksi.prepareStatement("select stok from gudangbarang where kode_brng=? and kd_bangsal=?");
            try {
                psawal.setString(1,kodebarang);
                psawal.setString(2,kdbangsal);
                rs=psawal.executeQuery();
                if(rs.next()){
                    stokawal=rs.getDouble("stok");
                    stokakhir=stokawal+masuk-keluar;
                }else{
                    stokawal=0;
                    stokakhir=stokawal+masuk-keluar;
                }
            } catch (Exception e) {
                System.out.println("Notif Stok : "+e);
            } finally{
                if(rsawal!=null){
                    rsawal.close();
                }
                if(psawal!=null){
                    psawal.close();
                }
            }
                     
            ps=koneksi.prepareStatement("insert into riwayat_obat_pasien values(?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp)");
            try {
                if(posisi.equals("Opname")){
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,0);
                    ps.setDouble(5,masuk);
                    ps.setString(6,posisi);
                    ps.setString(7,Tgl);
                    ps.setString(8,Jam);
                    ps.setString(9,petugas);
                    ps.setString(10,kdbangsal);
                    ps.setString(11,status);
                    ps.setString(12,noRM);
                    ps.setString(13,noPerawatan);
                    ps.executeUpdate();
                }else{
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,keluar);
                    ps.setDouble(5,stokakhir);
                    ps.setString(6,posisi);
                    ps.setString(7,Tgl);
                    ps.setString(8,Jam);
                    ps.setString(9,petugas);
                    ps.setString(10,kdbangsal);
                    ps.setString(11,status);
                    ps.setString(12,noRM);
                    ps.setString(13,noPerawatan);
                    ps.executeUpdate();
                }                    
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(ps!=null){
                    ps.close();
                }
            }          
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);  
        }            
   }
    
    public void catatRiwayatObat2(String kodebarang,double masuk,double keluar,String posisi,String petugas,String kdbangsal,String status, String noRM, String noPerawatan){        
        try {
            stokakhir=0;stokawal=0;            
            psawal=koneksi.prepareStatement("select stok from gudangbarang where kode_brng=? and kd_bangsal=?");
            try {
                psawal.setString(1,kodebarang);
                psawal.setString(2,kdbangsal);
                rs=psawal.executeQuery();
                if(rs.next()){
                    stokawal=rs.getDouble("stok");
                    stokakhir=stokawal+masuk-keluar;
                }else{
                    stokawal=0;
                    stokakhir=stokawal+masuk-keluar;
                }
            } catch (Exception e) {
                System.out.println("Notif Stok : "+e);
            } finally{
                if(rsawal!=null){
                    rsawal.close();
                }
                if(psawal!=null){
                    psawal.close();
                }
            }
                     
            ps=koneksi.prepareStatement("insert into riwayat_obat_pasien values(?,?,?,?,?,?,current_date(),current_time(),?,?,?,?,?,current_timestamp)");
            try {
                if(posisi.equals("Opname")){
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,0);
                    ps.setDouble(5,masuk);
                    ps.setString(6,posisi);
                    ps.setString(7,petugas);
                    ps.setString(8,kdbangsal);
                    ps.setString(9,status);
                    ps.setString(10,noRM);
                    ps.setString(11,noPerawatan);
                    ps.executeUpdate();
                }else{
                    ps.setString(1,kodebarang);
                    ps.setDouble(2,stokawal);
                    ps.setDouble(3,masuk);
                    ps.setDouble(4,keluar);
                    ps.setDouble(5,stokakhir);
                    ps.setString(6,posisi);
                    ps.setString(7,petugas);
                    ps.setString(8,kdbangsal);
                    ps.setString(9,status);
                    ps.setString(10,noRM);
                    ps.setString(11,noPerawatan);
                    ps.executeUpdate();
                }                    
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(ps!=null){
                    ps.close();
                }
            }          
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);  
        }            
   }
}
    

