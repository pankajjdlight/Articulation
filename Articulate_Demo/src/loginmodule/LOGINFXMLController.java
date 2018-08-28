package loginmodule;

import static articulate.Articulate.classStage;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class LOGINFXMLController {
   
    Scene scene;
      
     FXMLLoader primaryLoader;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginbtn;

    @FXML
    private Label forgotpwd;

    @FXML
    private Label createaccount;

    @FXML
    private TextField unametxt;

    @FXML
    private  PasswordField pwdtxt;
      @FXML
    private Label unamelbl;

    @FXML
    private Label pwdlbl;
     @FXML
    private Label sendmepass;
   
    @FXML
    void createaccount(MouseEvent event) throws IOException {

     Stage stage = (Stage) loginbtn.getScene().getWindow();
     stage.close();
     
     FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("/loginmodule/FXMLDocument.fxml"));  
     Parent textAreaHolder = (Parent) primaryLoader.load();
     Stage classStage = new Stage();
     classStage.setResizable(false);
     classStage.setMaxWidth(800);//722,550
     classStage.setMaxHeight(550);
     classStage.setTitle(" Articulate+");
     Scene scene = new Scene(textAreaHolder);
     classStage.setScene(scene);
     classStage.centerOnScreen();
     classStage.show();
        
        
    }

    @FXML
    void forgotpassword(MouseEvent event) {
        pwdtxt.setVisible(false);
        createaccount.setVisible(false);
        unametxt.setStyle("-fx-border-color:  transparent;");
        unamelbl.setStyle("-fx-text-fill: blue;");
        unamelbl.setText("Enter your registered email-id");
        loginbtn.setVisible(false);
        sendmepass.setStyle("-fx-text-fill: blue;");
        forgotpwd.setVisible(false);
        sendmepass.setText("Send my password");
     }

    
    @FXML
    void retrivepassword(MouseEvent event) throws AddressException, MessagingException {
        pwdtxt.setVisible(true);
        createaccount.setVisible(true);
        forgotpwd.setVisible(true);
        loginbtn.setVisible(true);
        unametxt.setStyle("-fx-border-color:  transparent;");
        unamelbl.setStyle("-fx-text-fill: blue;");
        unamelbl.setText(" ");
        sendmepass.setVisible(false);
        //sendmepass.setText("Send my password");
        System.out.println("username"+unametxt.getText());
         Connection conn = LoginModule.connect();
        String retrievedEmail = "";
        String retrievedPassword = "";
         PreparedStatement ps;
        if(unametxt.getText().equals("")){
            
        //JOptionPane.showMessageDialog(null, "Please enter your username");
            System.out.println("Please enter your username");
        }
        
        else{
            
            try {
                 ps = conn.prepareStatement( "SELECT * FROM Therapist_Master_Table WHERE email = ?" );
                ps.setString(1, unametxt.getText());
                ResultSet result = ps.executeQuery();
                while( result.next() ) {
                   retrievedEmail = result.getString( "email" );
                   retrievedPassword = result.getString("password");
            }   
                
                
                if( retrievedEmail.equals( unametxt.getText() ) ) {
              System.out.println("Email exists");
                
          /*    final String username = "nasospeech@gmail.com"; // enter your mail id
		final String password = "pass123nasospeech";// enter ur password
                 final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("proxySet","true");
                props.setProperty("socksProxyHost","202.141.80.30");
                props.setProperty("socksProxyPort","3128");
                props.setProperty("mail.smtp.host", "smtp.gmail.com");
                props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
                props.setProperty("mail.smtp.socketFactory.fallback", "false");
                props.setProperty("mail.smtp.port", "465");
                props.setProperty("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.debug", "true");
                props.put("mail.store.protocol", "pop3");
                props.put("mail.transport.protocol", "smtp");
                
         Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
                               @Override
                               protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                               return new javax.mail.PasswordAuthentication(username, password);
			}
		  });
         
          try {

                                Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress("nasospeech@gmail.com"));
                            message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse(enteruntxt.getText()));
                            message.setSubject("Testing Subject");
                        message.setText("Dear Friends This is your Password," +
                                    retrievedPassword);

                    Transport.send(message);

                        System.out.println("Done");

                        } catch (MessagingException e) {
                            throw new RuntimeException(e);
                                }
                */
         
      /*    String host = "202.141.80.30"; // proxy server address 
     String from = "roshanchettri@iitg.ernet.in"; 
     String to = enteruntxt.getText(); 
     Properties props = System.getProperties(); 
     props.put("mail.smtp.host", host); 
     props.put("mail.smtp.port", "3128");
     Session session = Session.getDefaultInstance(props,null); 
     MimeMessage message = new MimeMessage(session); 
     message.setFrom(new InternetAddress(from)); 
     message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); 
     System.out.println(" setting subject and message ");
     message.setSubject("just testing my mail prog");
     message.setText("Welcome to My mailing service \n This is an auto generated mail pls do not reply to it "); 
     Transport.send(message); 
     System.out.println(" sent the message ");
     System.out.println(" thanks ");  */
         
      
 /*Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
// Get a Properties object
Properties props = System.getProperties();
props.setProperty("proxySet","true");
props.setProperty("socksProxyHost","202.141.80.30");
props.setProperty("socksProxyPort","3128");
props.setProperty("mail.smtp.host", "smtp.gmail.com");
props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
props.setProperty("mail.smtp.socketFactory.fallback", "true");
props.setProperty("mail.smtp.port", "465");
props.setProperty("mail.smtp.socketFactory.port", "465");
//props.put("mail.smtp.ssl.enable", "true");
props.put("mail.smtp.auth", "true");
props.put("mail.debug", "true");
props.put("mail.store.protocol", "pop3");
props.put("mail.transport.protocol", "smtp");
final String username = "nasospeech@gmail.com";
final String password = "pass123nasospeech";
Session session = Session.getDefaultInstance(props,
 new javax.mail.Authenticator() {
                               @Override
                               protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                               return new javax.mail.PasswordAuthentication(username, password);
			}});

// -- Create a new message --
Message msg = new MimeMessage(session);

// -- Set the FROM and TO fields --
msg.setFrom(new InternetAddress("nasospeech@gmail.com"));
msg.setRecipients(Message.RecipientType.TO,
InternetAddress.parse("erroshanchettri89@gmail.com",false));
msg.setSubject("Hello");
msg.setText("How are you");

Transport.send(msg);
System.out.println("Message sent."); */
                final String username = "nasospeech@gmail.com"; // enter your mail id
		final String password = "pass123nasospeech";// enter ur password

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session;
                     session = Session.getInstance(props,
                             new javax.mail.Authenticator() {
                                 @Override
                                 protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
                                     return new javax.mail.PasswordAuthentication(username, password);
                                 }
                             });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("nasospeech@gmail.com")); // same email id
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(unametxt.getText()));// whome u have to send mails that person id
			message.setSubject("Password Recovery");
			message.setText("Dear User,"
				+ "\n\t Your Password is " +retrievedPassword +
                                "\n\n Regards\n Team Nasospeech");

			Transport.send(message);

			System.out.println("Done");
                }
                        catch (MessagingException e) {
			throw new RuntimeException(e);
		}
      
 
 
      

            }

                
                
                
                
                
                
            else {
                    
                    
                Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Warning Dialog");
alert.setHeaderText("Alert!");
alert.setContentText("Invalid Email");

alert.showAndWait();
                //JOptionPane.showMessageDialog(null,"Invalid Email");
            }
                System.out.println("The retrieved password is  "+ retrievedPassword);

                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(LOGINFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        
        
        
        }
        
        
      
        
        
       
       
    }

    
    
    
    @FXML
    void initialize() {
        assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'LOGINFXML.fxml'.";
        assert forgotpwd != null : "fx:id=\"forgotpwd\" was not injected: check your FXML file 'LOGINFXML.fxml'.";
        assert createaccount != null : "fx:id=\"createaccount\" was not injected: check your FXML file 'LOGINFXML.fxml'.";
        assert unametxt != null : "fx:id=\"unametxt\" was not injected: check your FXML file 'LOGINFXML.fxml'.";
        assert pwdtxt != null : "fx:id=\"pwdtxt\" was not injected: check your FXML file 'LOGINFXML.fxml'.";

    }
    
    @FXML
    private Button signupbtn;

    @FXML
    void signupclick(ActionEvent event) throws IOException {
    
      signupbtn.setCursor(Cursor.HAND);
        
     Stage stage1 = new Stage();
     Parent myPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    Scene scene = new Scene(myPane);
    stage1.setScene(scene);
    stage1.show();
    }
    
     private PreparedStatement pst;
    
  
   @FXML
    void sme1(MouseEvent event) {
        signupbtn.setCursor(Cursor.HAND);

    }
    
      @FXML
    void lme2(MouseEvent event) {
        loginbtn.setCursor(Cursor.HAND);
    }

    
    
      @FXML
    void usernamecheck(MouseEvent event) {
           pwdtxt.setStyle("-fx-border-color: transparent;");
           pwdlbl.setText(" ");
        if(unametxt.getText().equals("")){
            unametxt.setStyle("-fx-border-color: red;");
            unamelbl.setStyle("-fx-text-fill: red;");
            unamelbl.setText("You cant leave this empty!");
            }
        else{
        Connection conn = LoginModule.connect();
        String query = "SELECT * FROM Therapist_Login_Table WHERE Username = ?";
        try {
            pst = conn.prepareStatement(query);
        } catch (SQLException ex) {
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            pst.setString(1, unametxt.getText());
        
      
        ResultSet rs = pst.executeQuery();
        int count =0;
         while(rs.next()){
            count++;
        }
         if(count==1){
              
           unametxt.setStyle("-fx-border-color:  transparent;");
           unamelbl.setStyle("-fx-text-fill: blue;");
           unamelbl.setText("Username available");
          
           
           
           
           
          }
          else{
           unametxt.setStyle("-fx-border-color: transparent;");
          unamelbl.setStyle("-fx-text-fill: red;");
           unamelbl.setText("Username not available");
         
          }
        
        
        
        }
        catch (SQLException e){
        e.printStackTrace();
        
        
        }
        
        }
       
         
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    @FXML
    void loginclick(ActionEvent event) throws IOException {
        
          
        
       
         String query = "SELECT * FROM Therapist_Login_Table WHERE Username = ? and Password = ?";
        try {
            Connection conn = LoginModule.connect();
            pst = conn.prepareStatement(query);
        } catch (SQLException ex) {
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
           // conn = SqliteJDBCconnection.connect();
        
        pst.setString(1, unametxt.getText());
        pst.setString(2, pwdtxt.getText());
      
        ResultSet rs = pst.executeQuery();
        int count =0;
        while(rs.next()){
            count++;
        }
          if(count==1){
              System.out.println("Login Successfull");
           unametxt.setStyle("-fx-border-color:  transparent;");
           unamelbl.setStyle("-fx-text-fill: blue;");
           unamelbl.setText("          ");
          
              pwdlbl.setStyle("-fx-text-fill: blue;");
              
              pwdlbl.setText("Login Successfull");
       
         
       
      FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("/loginmodule/PatientRegistration.fxml"));  
      Parent textAreaHolder = (Parent) primaryLoader.load();
      Stage classStage = new Stage();
      classStage.setResizable(false);
      classStage.setMaxWidth(800);
      classStage.setMaxHeight(650);
      classStage.setTitle(" Articulate+");
      Scene scene = new Scene(textAreaHolder);
      classStage.setScene(scene);
      classStage.centerOnScreen();
       
       classStage.show();
              
        Stage stage = (Stage) loginbtn.getScene().getWindow();
      stage.close();       
              
              
              
      /*        
              
      primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
      Parent textAreaHolder = (Parent) primaryLoader.load();
      classStage.setResizable(false);
      classStage.setMaxWidth(663);
      classStage.setMaxHeight(629);
      classStage.setTitle(" Articulate+");
      scene = new Scene(textAreaHolder);
      classStage.setScene(scene);
      classStage.centerOnScreen();
      classStage.show();
              
      */        
              
                 
          }
          else{
              
           unametxt.setStyle("-fx-border-color: red;");
           unamelbl.setStyle("-fx-text-fill: red;");
           unamelbl.setText("                     ");
           pwdtxt.setStyle("-fx-border-color: red;");
           pwdlbl.setStyle("-fx-text-fill: red;");
           pwdlbl.setText("Invalid login !try again");
          
              
              
              
    /*          
       FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("/loginmodule/LOGINFXML.fxml"));  
      Parent textAreaHolder = (Parent) primaryLoader.load();
      classStage.setResizable(false);
      classStage.setMaxWidth(522);
      classStage.setMaxHeight(550);
      classStage.setTitle(" Articulate+");
      scene = new Scene(textAreaHolder);
      classStage.setScene(scene);
      classStage.centerOnScreen();
      classStage.show();
              
              
        */      
              
          }
        }
        catch (SQLException e){
        e.printStackTrace();
        }

    }
    }
    
    
    

