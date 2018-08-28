/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;


//import static Speech.WavePanel.Prob.probability;
//import Speech.gui.MainFrame;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import static nasofx.Prob.probability;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DoD
 */



public class drawingComponent extends JComponent {
   //public PlotWave pWave;
  // public String file="untitled";
   //public PlotWave pWave1;
    public String file="";
    public String filename="";
   PlotProbability pp;
    public String ReturnFilename;
   public PlotProbability plot;
   
    /**
     *
     * @param pWave
     * @return
     */
  // public  drawingComponent() 
    // {  super();
        //this.file =RightClickEvent. 
        
      //  this.pWave = pWave;
    /**
     *
     */
   // public String  file;
    //  System.out.print("getting file name in constructor"+file);
      //return file;
      
    //  pWave1=pWave;
      
    // }
    
     
   //public drawingComponent dc;//=new drawingComponent();
   
     //System.out.print("getting file name"+file);
     
    //public drawingComponent dd=new drawingComponent();
    
        //this.pWave = pWave;
  //  PlotWave pwave=new PlotWave();
    
    //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    double  probability1;
    
//   String file= ReturnFilename();
   public String probability2;
   
   //public PlotWave pwave=new PlotWave(MainFrame mainFrame, AudioInputStream audioStreamArray);
     // PlotWave pwave=new PlotWave(MainFrame mainFrame, AudioInputStream audioStreamArray);
      
    //  PlotWave pwave=new PlotWave();
    // pwave.fileName.length();
    //   String fileNameDis = "";
    /*
    if (fileName.length() > 15) {
    try {
    fileNameDis = fileName.substring(0, 15);
    } catch (Exception er) {
    System.err.println(er);
    }
    } else {
    fileNameDis = fileName;
    }
     */  //    String filename;
    
  //  public drawingComponent() {    
    //    this.filename = pwave.PlotWave();
    //}

    
    
  /*  public drawingComponent(PlotWave pWave)
    {
    this.pWave=pWave;
    }
    */
    
    
    
    
    drawingComponent()
    {
      super();//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      //this.pp = new PlotProbability( pWave);
        //this.dc = new drawingComponent(pWave);
    }
    drawingComponent(PlotProbability plot)  
     {  super();
        //this.dc = new drawingComponent(pWave);
        this.plot=plot;
     
       this.ReturnFilename = plot.getfile();
       System.out.println("drawingcomponentfilename = "+ReturnFilename);
      
     }       
     
    // drawingComponent dc=new drawingComponent(pWave);
     
    
     
     
      
  /*  public void gettingfilename()
    {
      //  file=pWave.ReturnFilename();
        //System.out.print("finallly gettting yupeeeeeee\n"+file);
    }
    */        
    @Override
    public void paintComponent(Graphics g){
        
        
     System.out.println("\ndrawingcomponentfilenameReadyToPrint = "+drawingComponent.this.ReturnFilename);    
      /*  String ff;
       ff = drawingComponent(pWave);
        System.out.print("finally getting file value yupee\n"+ff);
        */
        //gettingfilename();
        Graphics2D g2 = (Graphics2D) g;
        Prob object = new Prob();
      //  Prob object1=new Prob(pWave);
        double xxx = object.getProb();
        int pixel = object.probToPixel();
        System.out.println("xxxxxxxxxxxxx = "+xxx  + "  pixel = "+object.probToPixel());
        // Returnfilename rt=new Returnfilename();
         //file=rt.getFile();
         //System.out.println("xxxxpankajxxx= "+file);
    //  System.out.println("drawingcomponentfilename = ");
         
       //filename=pp.getfile();
      // double valuec = pp.valuec;
       // Returnfilename ret;
       //ret = new Returnfilename(pWave);
       //file=  ret.gettingfilename();
       //  System.out.print("getting file name in paint"+file); 
      
        
     //   System.out.print("got the file name"+file);
     // g2.setFont(new Font("Myraid pro", Font.BOLD,30)); 
      // g2.drawString(file, 370, 180);
        
        
       ///////////////////////THE HEADING///////////////////
        
         
        g2.setFont(new Font("Myraid pro", Font.BOLD,30));
        g2.drawString("Hypernasality Score", 50,30 );
        
        
        
        //////////////**********THE RECTANGULAR DIVISIONS*********/////////
        Rectangle rect1  = new Rectangle(60, 550, 100, 150); 
        //g2.setColor(new Color(0,0,255));                            //(4) BOTTOM//
        g2.setColor(new Color(17,122,101)); 
        g2.fill(rect1);
        
         Rectangle rect2  = new Rectangle(60, 400, 100, 150); 
        //g2.setColor(new Color(0,0,255));                            //(3)//
        g2.setColor(new Color(52, 152, 219));  
        g2.fill(rect2);
        
        Rectangle rect3  = new Rectangle(60,250, 100, 150); 
        g2.setColor(new Color(241, 196, 15));                         //(2)//
        //g2.setColor(Color.RED);                                   
        g2.fill(rect3);
        
        
         Rectangle rect4  = new Rectangle(60,100, 100, 150); 
        g2.setColor(new Color(231, 76, 60));                            //(1) TOP//
        //g2.setColor(Color.RED); 
        g2.fill(rect4);
        ////////////////(***********THE RECTANGULAR DIVISIONS******/////////
        
        
        
        
        ///////////THE TOP DETAILS/////////////////
        
         // Rectangle rect12  = new Rectangle(250, 80, 70, 70); 
        //g2.setColor(new Color(52, 152, 219));                            
        
        
       // g2.setColor(Color.BLACK); 
        //g2.setFont(new Font("Myraid pro", Font.BOLD,70));
          //String.parseDouble()
         //  g2.drawString( probability, 330,140 );
        // g2.setFont(new Font("Myraid pro", Font.PLAIN,25));
        g2.setColor(Color.BLACK);
        g2.drawString("Rating:", 250,175 );
        g2.setFont(new Font("Myraid pro", Font.BOLD,25));
     //   g2.drawString("FileName:", 250,225 );
        g2.setFont(new Font("Myraid pro", Font.BOLD,25));
     //   g2.drawString(drawingComponent.this.ReturnFilename, 380,225 );
        g2.setFont(new Font("Myraid pro", Font.BOLD,25));
        
        probability1=1- probability ;
        float probability3, prob5;
        int prob4;
        probability3 = (float)probability1*100;
        prob4=(int) probability3;
        prob5= probability3-prob4;
        probability3= (probability3-prob5)/100;
        
        
        probability2=String.valueOf(probability3);
        String.format("%.2f", probability1);
        System.out.println("double"+probability1);
       System.out.println("float"+probability3);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Myraid pro", Font.BOLD,40));
        g2.drawString(probability2,325, 130);
       // g2.drawString(String.format("%.3f", probability2), 325, 130);
        
        
        if(probability1<=0.25)
         
            
        {
        g2.setFont(new Font("Myraid pro", Font.PLAIN,35));
        g2.drawString("NORMAL", 370,175 );
        
        Rectangle rect121  = new Rectangle(250, 80, 70, 70); 
        g2.setColor(new Color(17,122,101));
        g2.fill(rect121);
        }else
        if(0.25<probability1 & probability1<=0.50)
        {
        g2.setFont(new Font("Myraid pro", Font.PLAIN,35));
        g2.drawString("MILD", 370,175 );
        
        Rectangle rect122  = new Rectangle(250, 80, 70, 70); 
        g2.setColor(new Color(52, 152, 219));
        g2.fill(rect122);
        }else
        if(0.50<probability1 & probability1<=0.75)
        {
        g2.setFont(new Font("Myraid pro", Font.PLAIN,35));
        g2.drawString("MODERATE", 370,175 );
       
        Rectangle rect123  = new Rectangle(250, 80, 70, 70); 
        g2.setColor(new Color(241, 196, 15));
        g2.fill(rect123);
        } else
        if(0.75<probability1 & probability1<=1.0)
        {
        g2.setFont(new Font("Myraid pro", Font.PLAIN,35));    
        g2.drawString("SEVERE", 370,175 );
        
        Rectangle rect124  = new Rectangle(250, 80, 70, 70); 
        g2.setColor(new Color(231, 76, 60));
        g2.fill(rect124);
        }
        g2.setFont(new Font("Myraid pro", Font.PLAIN,10));
        
        
        
        
        
          
          ///////////*****AXIS numbers*****////////
          
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawString("1.0",30, 105);
        g2.drawString("0.9",30, 165);
        g2.drawString("0.8",30, 225);
        g2.drawString("0.7",30, 285);
        g2.drawString("0.6",30, 345);
        g2.drawString("0.5",30, 405);
        g2.drawString("0.4",30, 465);
        g2.drawString("0.3",30, 525);
        g2.drawString("0.2",30, 585);
        g2.drawString("0.1",30, 645);
        g2.drawString("0.0",30, 705);//i have added 40 not 60    
        
        ////////////********AXIS numbers*****///////////
        
        
        
         /////////////////the AXIS//////////
        
        Point2D.Double pa = new Point2D.Double(60,100);
        Point2D.Double pb = new Point2D.Double(60,700);
        
        g2.setColor(Color.BLACK);
        Line2D.Double la = new Line2D.Double(pa,pb);
        g2.draw(la);
        
        Point2D.Double pc = new Point2D.Double(60, 700);
        Point2D.Double pd = new Point2D.Double(160,700);
        
        g2.setColor(Color.BLACK);
        Line2D.Double lb = new Line2D.Double(pc,pd);
        g2.draw(lb);
        
         Point2D.Double pe = new Point2D.Double(160, 100);
        Point2D.Double pf = new Point2D.Double(160,700);
        
        g2.setColor(Color.BLACK);
        Line2D.Double lc = new Line2D.Double(pe,pf);
        g2.draw(lc);
        
         Point2D.Double pg = new Point2D.Double(60, 100);
        Point2D.Double ph = new Point2D.Double(160,100);
        
        g2.setColor(Color.BLACK);
        Line2D.Double ld = new Line2D.Double(pg,ph);
        g2.draw(ld);
        //////////////////THE axis//////////
        
        
        
        //////////////******** THE MARKER**********///////////////
        {
             Line2D.Double L0 = new Line2D.Double(70, pixel-3, 150 , pixel-3);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.WHITE);
            g2.draw(L0);
            
            Line2D.Double L1 = new Line2D.Double(70,pixel ,150 , pixel);
            g2.setStroke(new BasicStroke(4));
            g2.setColor(new Color(44,62,80));
            g2.draw(L1);
            
             Line2D.Double L2 = new Line2D.Double(70, pixel+3, 150 ,pixel+3);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.WHITE);
            g2.draw(L2);
            
             Line2D.Double L3 = new Line2D.Double(85, pixel, 135 , pixel);
            g2.setStroke(new BasicStroke(8));
            g2.setColor(Color.BLACK);
            g2.draw(L3);
            
            
            Line2D.Double L4 = new Line2D.Double(60,pixel,160 , pixel);
            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.BLACK);
            g2.draw(L4);
            
             /*Line2D.Double L1 = new Line2D.Double(66, 5,66 , 105);
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.BLACK);
            g2.draw(L1);
            
             Line2D.Double L1 = new Line2D.Double(66, 5,66 , 105);
            g2.setStroke(new BasicStroke(4));
            g2.setColor(Color.BLACK);
            g2.draw(L1);*/
            
            
            Ellipse2D.Double E2 = new Ellipse2D.Double(193, 50, 10, 10);
            //g2.fill(E2);
            Ellipse2D.Double E3 = new Ellipse2D.Double(179, 50, 10, 10);
            //g2.fill(E3);
        }
        
        //////////***********RESULT()************////////////
        
        
        
        
        
        ////////////THE AXIS MARKS(small)///////
        
        Point2D.Double p3 = new Point2D.Double(55,100);
        Point2D.Double p4 = new Point2D.Double(65,100);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l3 = new Line2D.Double(p3,p4);
        g2.draw(l3);
        
        
        Point2D.Double p5 = new Point2D.Double(55,160);
        Point2D.Double p6 = new Point2D.Double(65,160);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l4 = new Line2D.Double(p5,p6);
        g2.draw(l4);
        
        
        
       Point2D.Double p7 = new Point2D.Double(55,220);
        Point2D.Double p8 = new Point2D.Double(65,220);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l5 = new Line2D.Double(p7,p8);
        g2.draw(l5);
        
        
        Point2D.Double p9 = new Point2D.Double(55,280);
        Point2D.Double p10 = new Point2D.Double(65,280);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l6 = new Line2D.Double(p9,p10);
        g2.draw(l6);
        
        
        
        Point2D.Double p11 = new Point2D.Double(55,340);
        Point2D.Double p12 = new Point2D.Double(65,340);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l7 = new Line2D.Double(p11,p12);
        g2.draw(l7);
        
        
        
        Point2D.Double p13 = new Point2D.Double(55,400);
        Point2D.Double p14 = new Point2D.Double(65,400);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l8 = new Line2D.Double(p13,p14);
        g2.draw(l8);
        
        
        
        Point2D.Double p15 = new Point2D.Double(55,460);
        Point2D.Double p16 = new Point2D.Double(65,460);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l9 = new Line2D.Double(p15,p16);
        g2.draw(l9);
        
        
        Point2D.Double p17 = new Point2D.Double(55, 520);
        Point2D.Double p18 = new Point2D.Double(65,520);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l10 = new Line2D.Double(p17,p18);
        g2.draw(l10);
        
        Point2D.Double p19 = new Point2D.Double(55,580);
        Point2D.Double p20 = new Point2D.Double(65,580);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l11 = new Line2D.Double(p19,p20);
        g2.draw(l11);
        
         Point2D.Double p21 = new Point2D.Double(55,640);
        Point2D.Double p22 = new Point2D.Double(65,640);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l12 = new Line2D.Double(p21,p22);
        g2.draw(l12);
        
         Point2D.Double p23 = new Point2D.Double(55,700);
        Point2D.Double p24= new Point2D.Double(65,700);
        
        g2.setColor(Color.BLACK);
        Line2D.Double l13 = new Line2D.Double(p23,p24);
        g2.draw(l13);
        ///////////////THE AXIS MARKS(small)////////
        
        
        
        
        ///////////THE SURROUNDING RECTANGLE/////////////
           
           if(pixel-75<100)
           {
               Rectangle rect51  = new Rectangle(50, 100, 120, 150);
               g2.draw(rect51);
               
           }
           else 
               if(pixel+75>700)
           {
               
           Rectangle rect52  = new Rectangle(50,pixel-75, 120, (700-(pixel-75)));
           g2.draw(rect52);
           
           }
           else
           {
               
           Rectangle rect5  = new Rectangle(50,pixel-75, 120, 150);
           g2.draw(rect5);
           
           }
        //g2.setColor(new Color(0,0,255));                            
        g2.setColor(Color.BLACK); 
        //g2.setStroke(new BasicStroke(2));
        
        
        
        ///////////THE SURROUNDING RECTANGLE/////////////
        
        
        
       
        ////////////THE PARAMETERS (mild,severe...)//////////
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawString("Severe",170, 105);
        g2.drawString("Moderate",170, 255);
        g2.drawString("Mild",170, 405);
        g2.drawString("Normal",170, 555);
        
        ////////////THE PARAMETERS (mild,severe...)//////////
       // JButton button=new JButton("Continue");
        //g2.fill((Shape) button);
        JLabel jlabel=new JLabel("Continue");
     
        
        

  
        //////////THE BAR ABOVE INDICATORS////////////
        
         Rectangle rect11  = new Rectangle(245,460, 500, 40); 
        g2.setColor(new Color(200,200,200)); 
        g2.fill(rect11);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Sherif", Font.BOLD,15));
         g2.drawString("Reading the scale.", 250,485 );
        
         //////////THE BAR ABOVE INDICATORS////////////
        
        
        


        ////////////THE INDICATORS ( Right side......)/////////////
        
        
        Rectangle rect7  = new Rectangle(250,520, 20, 50); 
        g2.setColor(new Color(231, 76, 60)); 
        g2.fill(rect7);
        
         Rectangle rect8  = new Rectangle(250,595, 20, 50); 
        g2.setColor(new Color(241, 196, 15)); 
        g2.fill(rect8);
        
        Rectangle rect9  = new Rectangle(450,520, 20, 50); 
        g2.setColor(new Color(52, 152, 219)); 
        g2.fill(rect9);
        
        Rectangle rect10  = new Rectangle(450,595, 20, 50); 
        g2.setColor(new Color(17,122,101)); 
        g2.fill(rect10);
        
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Myraid pro", Font.BOLD,14));
        g2.drawString(" SEVERE 0.75-1.0", 275,560 );
        g2.drawString(" MODERATE 0.5-0.75", 275,635 );
        g2.drawString(" MILD 0.25-0.5", 475,560 );
        g2.drawString(" NORMAL 0.0-0.25", 475,635 );
        
        ////////////THE INDICATORS ( Right side......)/////////////
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }

   
}


