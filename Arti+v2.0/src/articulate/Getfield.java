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
      public static String phoneme="";
      public static String position="";
      public static String path="";
       public static String wavename="";
     public static int E_lang=0;
     public static String eposition_i="",eposition_m="",eposition_f="",eposition="";
     public static String ephoneme="";
     public static int one=0,two=0,three=0;
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
    
        public void setPhoneme(String Path){
       phoneme=Path;  
     }
     public String getPhoneme()
     {
         return phoneme;
     }
    
       public void setPosition(String Path){
       position=Path;  
     }
     public String getPosition()
     {
         return position;
     }
       public void setPath(String Path){
       path=Path;  
     }
     public String getPath()
     {
         return path;
     }
      void setwavefilename(String name){
    wavename=name;   
   }
   
   String getwavefilename(){
    return wavename;   
   }

       public void setEPosition_1(String Path){
          eposition_i = Path;  
           System.out.println("p1"+eposition_i);
     }
     public String getEPosition_1()
     {
         return eposition_i;
     }

     
       public void setEPosition_2(String Path){
          eposition_m = Path;
          System.out.println("p2"+eposition_m);
     }
     public String getEPosition_2()
     {
         return eposition_m;
     }
     
       public void setEPosition_3(String Path){
          eposition_f = Path;  
          System.out.println("p3"+eposition_f);
     }
     public String getEPosition_3()
     {
         return eposition_f;
     }
 public void setePhoneme(String Path){
       ephoneme=Path;  
     }
     public String getePhoneme()
     {
         return ephoneme;
     }
    

       public void setEPosition(String Path){
          eposition = Path;  
     }
     public String getEPosition()
     {
         return eposition;
     }



}

