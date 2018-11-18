/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.users.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hakushuu
 */
@ManagedBean
@RequestScoped
public class Kkn {

    /**
     * Creates a new instance of KKN
     */
    
    private String NIM;
    public void setNIM(String NIM) {
        this.NIM = NIM;
    }
    public String getNIM() {
        return NIM;
    }

    private String PROGRAM;
    public void setPROGRAM(String PROGRAM) {
        this.PROGRAM= PROGRAM;
    }
    public String getPROGRAM() {
        return PROGRAM;
    }
    
    private String LOKASI;
    public void setLOKASI(String LOKASI) {
        this.LOKASI = LOKASI;
    }
    public String getLOKASI() {
        return LOKASI;
    }
   
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

public String Edit_KKN(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String > params = fc.getExternalContext().getRequestParameterMap();
     String Field_NIM = params.get("action");
     try {
          Koneksi obj_koneksi = new Koneksi();
          Connection connection = obj_koneksi.get_connection();
          Statement st = connection.createStatement();
          ResultSet rs = st.executeQuery("select * from kkn where NIM="+Field_NIM);
          Kkn obj_KKN = new Kkn();
          rs.next();
          obj_KKN.setNIM(rs.getString("NIM"));
          obj_KKN.setPROGRAM(rs.getString("Program"));
          obj_KKN.setLOKASI(rs.getString("Lokasi"));
          sessionMap.put("EditKKN", obj_KKN);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/Edit_KKN.xhtml?faces-redirect=true";   
}

public String Delete_KKN(){
      FacesContext fc = FacesContext.getCurrentInstance();
      Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
      String Field_NIM = params.get("action");
      try {
         Koneksi obj_koneksi = new Koneksi();
         Connection connection = obj_koneksi.get_connection();
         PreparedStatement ps = connection.prepareStatement("delete from kkn where NIM=?");
         ps.setString(1, Field_NIM);
         System.out.println(ps);
         ps.executeUpdate();
        } catch (Exception e) {
         System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}

public String Update_KKN(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String Update_NIM= params.get("Update_NIM");
        try {
            Koneksi obj_koneksi = new Koneksi();
            Connection connection = obj_koneksi.get_connection();
            PreparedStatement ps = connection.prepareStatement("update kkn set NIM=?, Program=?, Lokasi=? where NIM=?");
            ps.setString(1, NIM);
            ps.setString(2, PROGRAM);
            ps.setString(3, LOKASI);
            ps.setString(4, Update_NIM);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
}
    
    public ArrayList getGet_all_kkn() throws Exception{
        ArrayList list_of_kkn = new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from kkn");
            while(rs.next()){
                Kkn obj_KKN = new Kkn();
                obj_KKN.setNIM(rs.getString("NIM"));
                obj_KKN.setPROGRAM(rs.getString("Program"));
                obj_KKN.setLOKASI(rs.getString("Lokasi"));
                list_of_kkn.add(obj_KKN);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_kkn;
}
    
public String Tambah_KKN(){
        try {
            Connection connection=null;
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            PreparedStatement ps=connection.prepareStatement("insert into kkn (NIM, Program, Lokasi) value('"+NIM+"','"+PROGRAM+"','"+LOKASI+"')");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/index.xhtml?faces-redirect=true";
    }
    
    public Kkn() {
    }
    
}
