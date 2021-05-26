/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import sodokuproject.Cell;
import sodokuproject.Const;
import sodokuproject.Grid;
import sodokuproject.SodokuSolver;
import sodokuproject.SudokuGenerator;

/**
 *
 * @author lenovo
 */
public class Buttons {
    
    private Cell[][] original;
    private Grid g ;
    private JFrame f ;
    private int currentMode;
    private SodokuSolver solver;

    public void setOriginal(Cell[][] original) {
        this.original = original;
    }

    public void setG(Grid g) {
        this.g = g;
    }

    public Buttons(Cell[][] original, Grid g) {
        this.original = original;
        this.g = g;
    }

    public Buttons() {
    }

    public Buttons(Grid g) {
        this.g = g;
    }

    public Buttons(Cell[][] original, Grid g, JFrame f, int currentMode) {//New Event
        this.original = original;
        this.g = g;
        this.f = f;
        this.currentMode = currentMode;
    }
    
    
    
    public void newEvent_func()
    {
        for(int i = 0 ; i < Const.dimension ;i++)
            {
                for(int j = 0 ; j < Const.dimension ; j++)
                {
                    g.getGrid()[i][j].getCell().setForeground(Color.DARK_GRAY);////
                    g.getGrid()[i][j].ClearCell();
                    g.getGrid()[i][j].getCell().setEditable(false);
                }
            }
         new SudokuGenerator(Const.dimension,currentMode, g.getGrid());
         for(int i = 0 ; i  < Const.dimension;i++)
        {
            for(int j = 0 ;j < Const.dimension;j++)
            {
               original[i][j] = new Cell(g.getGrid()[i][j]);
             // original[i][j] = g.getGrid()[i][j];
            }
        }
        f.add(g);
    
         solver = new SodokuSolver(original);
         
    }
    
    
    public boolean check_func()
    {
        /*this.g = g;
        this.original = original;*/
        //ExceedEntryException.checkClicks++;
        boolean flag=true;
        for(int i=0 ; i<Const.dimension ; i++)
            {
                for(int j=0 ; j<Const.dimension ; j++)
                {
                    //int i = Integer.parseInt(myText.getText());
                    
                    if (g.getGrid()[i][j].getCell().isEditable()== true && !(g.getGrid()[i][j].getCell().getText().equals("")))
                    {
                        g.getGrid()[i][j].setCell_val();////
                        
                        if(g.getGrid()[i][j].getValue() == (original[i][j].getValue()))
                        {
                            g.getGrid()[i][j].getCell().setForeground(Color.GREEN);
                        }
                        else{
                            g.getGrid()[i][j].getCell().setForeground(Color.RED);
                            flag=false;
                        }
                    }
                }
            }
        return flag;
    }
    
    public void solve_func()
    {
        for(int i=0 ; i<Const.dimension ; i++)
            {
                for(int j=0 ; j<Const.dimension ; j++)
                {
                    if (g.getGrid()[i][j].getCell().isEditable()== true)
                    {
                        g.getGrid()[i][j].getCell().setForeground(Color.DARK_GRAY);
                        g.getGrid()[i][j].setCell(original[i][j].getValue());
                    }
                }
            }
        
    }
    
    public void reset_func()
    {
        for (int i = 0 ; i<Const.dimension ; i++)
         {
            for (int k = 0 ; k<Const.dimension; k++)
            {
                 //if(!(g.getGrid()[i][k].getCell().getText().equals("")))
                   // g.getGrid()[i][k].getCell().setForeground(Color.DARK_GRAY);
             
                if (g.getGrid()[i][k].getCell().isEditable()== true){
                    g.getGrid()[i][k].ClearCell();
             
                }
            }
         }
    }
    
    public void hint_func()
    {
        
       boolean done = false;
            for(int i =0 ;i <Const.dimension;i++)
            {
                for(int j=0 ;j < Const.dimension;j++)
                {
                    if(g.getGrid()[i][j].getCell().getText().equals(""))
                    {
                        g.getGrid()[i][j].setCell(original[i][j].getValue());
                        g.getGrid()[i][j].getCell().setForeground(Color.BLUE);
                        done = true;
                        break;                        
                    }
                }
                if(done == true)
                    break ;
            }   
    }
    public void done_func()
    {
           
            for(int i =0 ; i < g.getGrid().length;i++)
             {
                for(int j=0 ; j< g.getGrid().length;j++)
                {
                
                if(!g.getGrid()[i][j].getCell().getText().equals(""))
                {
                   g.getGrid()[i][j].setCell(Integer.parseInt(g.getGrid()[i][j].getCell().getText()));
                   g.getGrid()[i][j].getCell().setEditable(false);
                }
                
                 }
            
            
             }
                 
          
    }
    
    public boolean submit_func()
    {
        /*this.g = g;
        this.original = original;*/
        //ExceedEntryException.checkClicks++;
        boolean flag=true;
        boolean empty = false;
        for(int i=0 ; i<Const.dimension ; i++)
            {
                for(int j=0 ; j<Const.dimension ; j++)
                {
                    //int i = Integer.parseInt(myText.getText());
                    if(g.getGrid()[i][j].getCell().getText().equals(""))
                        empty = true;
                    
                    if (g.getGrid()[i][j].getCell().isEditable()== true && !(g.getGrid()[i][j].getCell().getText().equals("")))
                    {
                        g.getGrid()[i][j].setCell_val();
                        
                        if(g.getGrid()[i][j].getValue() == (original[i][j].getValue()))
                        {
                            g.getGrid()[i][j].getCell().setForeground(Color.GREEN);
                        }
                        else{
                            g.getGrid()[i][j].getCell().setForeground(Color.RED);
                            flag=false;
                        }
                    }
                }
            }
        return (flag && !empty);
    }


    
}
