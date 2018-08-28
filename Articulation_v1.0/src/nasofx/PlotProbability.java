/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author user
 */
public class PlotProbability  {
  
    
     final SwingNode swingNode = new SwingNode();
    double valuec;    
    String file="xxx";
    Stage classstage=new Stage();
            
    //public String file1="xxx";
    
   // public PlotWave pWave;
    
  /*  public PlotProbability() 
    {
        this.valuec = RightClickEvent.getvaluefromc();
      //  this.file=
        System.out.print("got the value"+this.valuec);
    }
*/
     public PlotProbability(NasoFX naso) throws IOException 
     { // 
         String file,file1;
      //  this.file= pWave.ReturnFilename();
        file1=this.file;
        System.out.print("got pankaj\n"+this.file);
        //System.out.print("got pankaj\n"+this.file);
        this.valuec=naso.getvaluefromc();
         System.out.print("got the value"+this.valuec);
      }

   /* PlotProbability() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  */
     
    // public void getfille(PlotWave pWave)
     //{  
       //  System.out.print("got the new file\n"+pWave.ReturnFilename());
    // }
//     PlotProbability plot=new  PlotProbability(pWave);
   public void plotfunction(AnchorPane resultpane,Stage stage)
   {     classstage=stage;
      //PlotProbability object=new PlotProbability(pWave); 
       //System.out.print("object printed"+object.file);
       JFrame window = new JFrame();
        window.repaint();
        window.revalidate();
        //JPanel panel = new JPanel();
        //panel.setBackground(Color.BLACK);
       //llllwindow.getContentPane().add(panel);
     //   getfille(pWave);
     
        window.setSize(900,900);
        window.setResizable(false);
       window.setTitle("Hypernasality");
       window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       window.setLocationRelativeTo(null);
        //window.setBackground(Color.WHITE);
        //String file;
       // file = PlotProbability.this.file;
       // System.out.print("file name printed in plotprobability"+file);
        Prob newProb = new  Prob();
        newProb.setProb(this.valuec);
        JPanel panel1=new JPanel();
        // SpringLayout layout = new SpringLayout();
       
     //   panel1.setLayout(new FlowLayout());
       // layout.setAutoCreateGaps(true);
       // layout.setAutoCreateContainerGaps(true);
    //   panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
       // JPanel panel2=new JPanel();
        JTabbedPane tab = new JTabbedPane();
        drawingComponent DC = new drawingComponent(); 
        DC.revalidate();
        DC.repaint();
       // JButton button=new JButton("Continue");
        /////////Patient details//////////
        JTextField fn=new JTextField("",100);
        // fn.setBounds(50,70,80,30);
     //   fn.setPreferredSize(new Dimension(50,100));//width,height
        JTextField mn=new JTextField("",100);
      //  mn.setPreferredSize(new Dimension(10,10));
        JTextField ln=new JTextField("",100);
      //   ln.setPreferredSize(new Dimension(10,10));
        JTextField DOB=new JTextField("",100);
       //  DOB.setPreferredSize(new Dimension(10,10));
         
        JTextField ADD=new JTextField("",100);
      //   ADD.setPreferredSize(new Dimension(10,10));
        JButton button=new JButton("Preview");
  /*      layout.setHorizontalGroup(
   layout.createSequentialGroup()
      .addComponent(fn)
      .addComponent(mn)
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
           .addComponent(DOB)
           .addComponent(ADD))
);
    */    
       GroupLayout layout = new GroupLayout(panel1);
        layout.setVerticalGroup(
   layout.createSequentialGroup()
      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
           .addComponent(fn, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
           .addComponent(mn, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
           .addComponent(ln, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            )
      .addComponent(button)
      
);
      layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
  .addComponent(DOB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
  .addComponent(ADD, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);  
        
        
        
        
      //////////////////////////////////////  
        panel1.add(fn);
        panel1.add(mn);
        panel1.add(ln);
        panel1.add(DOB);
        panel1.add(ADD);
        panel1.add(button);
        
       // panel2.add(DC);
        //window.setBackground(Color.WHITE);
        
  /*   Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse hypernasality");

alert.showAndWait();*/
tab.addTab("Result", DC);
tab.addTab("Details", panel1);
//panel1.add(DC);
//tab.add(DC);
//tab.add(panel1);



        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.contentAreaColor", Color.WHITE);
UIManager.put("TabbedPane.light", ColorUIResource.WHITE);
UIManager.put("TabbedPane.highlight", ColorUIResource.WHITE);
UIManager.put("TabbedPane.shadow", ColorUIResource.WHITE);
UIManager.put("TabbedPane.darkShadow", ColorUIResource.WHITE);
UIManager.put("TabbedPane.selected", ColorUIResource.WHITE);
UIManager.put("TabbedPane.borderHightlightColor", ColorUIResource.WHITE);      

//  tab.setBackground(Color.red);
       // tab.setBackground(Color.WHITE);
        window.add(DC);
        window.setVisible(true);/////key step to show the component 
        //without resizing
        

//VBox vb=new VBox();
        
        //Scene scene=new Scene(window);
       // window.add(button);
      //  window.setDefaultCloseOperation(1);
       window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    window.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            System.out.println("Uncomment following to open another window!");
            //MainPage m = new MainPage();
            //m.setVisible(true);

            e.getWindow().dispose();
    
            
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Do you want to save the result?");
//alert.setContentText("Are you ok with this?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    AlertAction(stage);
   // stage.close();
} else {
    // ... user chose CANCEL or closed the dialog
}
                   
                    

// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              
                
             
                }
            });
            




System.out.println("JFrame Closed!");
        }
    });
        
        
        
        
      // swingNode.setContent((JComponent) window.getContentPane());
      //  swingNode.setContent(DC);
        Pane pane=new Pane();
        pane.getChildren().add(swingNode);
        pane.setVisible(true);
        
        Platform.runLater(new Runnable(){
           @Override
           public void run() {
           // resultpane.getChildren().add(pane); 
           
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       }); 
        
          
      //  String file1 = getfile();
      //  drawingComponent DC1 = new drawingComponent(this);
     //   window.add(DC1);
       // window.setDefaultCloseOperation(1);
    
       
   }
   public void plotfunction1()
   {
       //Returnfilename rt=new Returnfilename();
       // rt.setFile(this.file);
      //  getfille(pWave);
   }
    public String getfile()
   {
       file = PlotProbability.this.file;
      System.out.print("filename in the getfile method"+file);
      return file;
      
   }
    
    public void AlertAction(Stage stage){
    
             Platform.runLater(new Runnable(){
           @Override
           public void run() {
           //Parent root = null;
         FXMLLoader  primaryLoader = new FXMLLoader(getClass().getResource("Patient.fxml"));  
               try {
             Parent   textAreaHolder = (Parent) primaryLoader.load();
              Scene scene = new Scene(textAreaHolder);
              stage.setScene(scene);
              stage.show();
               } catch (IOException ex) {
                   Logger.getLogger(PlotProbability.class.getName()).log(Level.SEVERE, null, ex);
               }
       
        //,530,735, true, SceneAntialiasing.BALANCED);
       

       // scene.getStylesheets().add("myStyleSheet.css");
        
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
    
    
    
    }
    public void setstageStage(Stage stage){
    classstage=stage;
    } 
    
    public Stage getstageStage(){
    return classstage;
    } 
     
}
