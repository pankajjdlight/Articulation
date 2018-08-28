/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

/**
 *
 * @author user
 */
public class Prob
{   //public PlotWave pWave;
    static double probability;
   //PlotProbability plot2=new  PlotProbability(pWave);
    public String file="";
    //public PlotWave pWave;
   // public String file;
      /*  public Prob(PlotWave pWave)
        { super();
          this.pWave=pWave;
          this.file= pWave.ReturnFilename();
        }*/
  //      static double probability;

    Prob() {
        super();
        this.file= gettingfilename();
        
       //this.pWave=pWave;
    //   this.file=pWave.ReturnFilename();//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//*/
    
    
    
    
    
    
    
    
    
        public void setProb(double prob)
        {
            this.probability = 1- prob;
            System.out.println("proba =" + probability);
        }
        
        double getProb()
        {
            return probability;
        }
        
        
        public int probToPixel(){
            
        Double pixel = (probability*600) +100;
        int returnValue = Integer.valueOf(pixel.intValue());
       // System.out.println("returnValue"+returnValue);
        
        return returnValue;
        }
        
    public final String gettingfilename()
    {
       file = Prob.this.file;
        System.out.print("finallly gettting yupeeeeeee\n"+file);
        return file;
      //file=plot2.getfile();
        //return file;
    }

        
    }
