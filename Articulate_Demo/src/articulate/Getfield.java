/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulate;

import static articulate.Articulate.filenamere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static javax.management.Query.gt;
import loginmodule.LoginModule;

/**
 *
 * @author AMW Design PC 2
 */
public class Getfield {
    
     public  String fname="";
     public static String mname="";
     public static String lname="";
     public static String address="";
     public static String pid="";
     
     
     
     
     private PreparedStatement pst1;
      public void setFname(String Path){
       fname=Path;  
     }
     public String getFname() 
     {  /*
         
          String query = "SELECT * FROM Patient_Register_Table ";
          try {
            Connection conn = LoginModule.connect();
            pst1 = conn.prepareStatement(query);
        } catch (SQLException ex) {
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
           // conn = SqliteJDBCconnection.connect();
        
      
        ResultSet rs = pst1.executeQuery();
        
        while(rs.next()){
            fname=rs.getString("Fname");
           
        }
        } catch (SQLException e){
        e.printStackTrace();
        }
         
         return fname;
      */
         return fname;
     }
     
      public void setMname(String Path){
          //System.out.println("mname"+mname);
       mname=Path;  
     }
     public String getMname()
     {
         return mname;
     }
     
      public void setLname(String Path){
       lname=Path;  
     }
     public String getLname()
     {
         return lname;
     }
     
   
     
      public void setADD(String Path){
       address=Path;  
     }
     public String getADD()
     {
         return address;
     }

    public void setpatientId(String text) {
       pid=text;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      public String getpatientId() {
       return pid;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
}
