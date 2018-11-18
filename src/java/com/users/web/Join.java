package com.users.web;

import com.users.web.Koneksi;
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
 * @author 
 */
@ManagedBean
@RequestScoped
public class Join {

    
    private String NIM;
    public void setNIM(String NIM) {
        this.NIM = NIM;
    }
    public String getNIM() {
        return NIM;
    }

    private String NAMA;
    public void setNAMA(String NAMA) {
        this.NAMA= NAMA;
    }
    public String getNAMA() {
        return NAMA;
    }
    
    private String PROGRAM;
    public void setPROGRAM(String PROGRAM) {
        this.PROGRAM= PROGRAM;
    }
    public String getPROGRAM() {
        return PROGRAM;
    }
    
      
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 

    public ArrayList getGet_all_join() throws Exception{
        ArrayList list_of_join=new ArrayList();
             Connection connection=null;
        try {
            Koneksi obj_koneksi = new Koneksi();
            connection = obj_koneksi.get_connection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select mahasiswa.*,kkn.Program from mahasiswa left join kkn on mahasiswa.NIM = kkn.NIM order by mahasiswa.NIM asc");
            while(rs.next()){
                Join obj_Join = new Join();
                obj_Join.setNIM(rs.getString("NIM"));
                obj_Join.setNAMA(rs.getString("Nama"));
                obj_Join.setPROGRAM(rs.getString("Program"));
                list_of_join.add(obj_Join);
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(connection!=null){
                connection.close();
            }
        }
        return list_of_join;
}
    
    public Join() {
    }
    
}