package sodokuproject;


import static java.lang.Thread.sleep;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sodokuproject.Board;

public class Timer {
    static int milli=0;
    static int second=0;
    static int minute=0;
    static int hour=0;
    static boolean state=true;
    static JLabel milliLabel;
    static JLabel secondLabel;
    static JLabel minuteLabel;
    static JLabel hourLabel;
    static public void make()
    {
        Thread t=new Thread()
        {
            public  void run(){
            
            for(;;)
            {
                
                if(state==true)
                {
                    try{
                        sleep(1);
                        if(milli>1000)
                        {
                            milli=0;
                            second++;
                        }
                        if(second>60)
                        {
                            milli=0;
                            second=0;
                            minute++;
                        }
                        if(minute>60)
                        {
                            milli=0;
                            second=0;
                            minute=0;
                            hour++;
                        }
                        
                        milliLabel.setText(" : "+milli);
                        milli++;
                        secondLabel.setText(" : "+second);
                        minuteLabel.setText(" : "+minute);
                        hourLabel.setText(""+hour);
                        
                        
                    }
                    catch(Exception e)
                    {
                        
                    }
                }
                                 
            } 
            }
        };
        t.start();
        milli=0;
        second=0;
        minute=0;
        hour=0;
        
    }
    
    static void showThetime()
    {
        JOptionPane.showMessageDialog(Board.f,"Time is "+milli+" milli "+second+" second "+
                +minute+" minute "+hour+" hour "
                    ,"Information",JOptionPane.INFORMATION_MESSAGE);
    }
}
