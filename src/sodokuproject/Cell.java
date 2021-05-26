/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author lenovo
 */
public class Cell 
{
   private JTextField cell ;
   private int value ;
   private boolean filled ;
   private int row , col ;
    
    public Cell(int row , int col)
    {
        cell = new JTextField(2);
        filled = false ; 
        this.row = row ;
        this.col = col ;
        
        cell.addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) { 
        try{
        if (cell.getText().length() >= 1 ){ // limit textfield to 1 characters
            e.consume();
        }
        char c=e.getKeyChar();
        if(((c<'1')||(c>'9'))&&(c!=KeyEvent.VK_BACK_SPACE))////
        {
            e.consume();
            throw new InvalidDataException("You must enter a number");
            
        }
           cell.setForeground(Color.DARK_GRAY);
        }
        catch(InvalidDataException IN)
        {
            JOptionPane.showMessageDialog(Board.f, IN.getMessage(),"",JOptionPane.WARNING_MESSAGE);
        }
    }  
    });
    }
    
    public Cell(Cell c)
    {
        this.cell = new JTextField(2);
        this.row = c.row;
        this.col = c.col;
        this.value = c.value;
        this.filled = c.filled; 
    }
    
    public void setCell(int number)
    {
        value = number;
        cell.setText(String.valueOf(number));
        filled = true ;
     
    }
    
    public void setCell_val()
    {
        String str =  cell.getText();
        value = Integer.parseInt(str);
        filled = true ;
  
    }
    
    public void ClearCell()
    {
        cell.setText("");
        filled = false;
        value = 0 ;
    }
            
            
            
    public int getValue()
    {
        return value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isFilled() {
        return filled;
    }

    public JTextField getCell() {
        return cell;
    }

    
}
