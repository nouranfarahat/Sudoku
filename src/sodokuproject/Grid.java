/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author lenovo
 */
public class Grid extends JPanel implements Const
{
    private static final Font font = new Font("Verdana", Font.CENTER_BASELINE,25);
    private Cell[][] grid ;
    private JPanel gridPanel;
    private JPanel[][] miniSquarePanel;
    
    public Grid(boolean flag)
    {
        grid = new Cell[dimension][dimension];
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(miniSquareDimension,miniSquareDimension));
        miniSquarePanel = new JPanel[miniSquareDimension][miniSquareDimension];
        OrganizeGrid(flag);  
        this.add(gridPanel, BorderLayout.NORTH);
    
    }
    
    public void OrganizeGrid(boolean flag)////
    {
        for(int i =0 ; i < dimension;i++)
        {
            for(int j=0 ; j< dimension;j++)
            {
                Cell cell = new Cell(i, j);
                grid[i][j] = cell;
                grid[i][j].getCell().setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
                grid[i][j].getCell().setFont(font);
                grid[i][j].getCell().setHorizontalAlignment(JTextField.CENTER);
                grid[i][j].getCell().setPreferredSize(new Dimension(50,50));
                grid[i][j].getCell().setEditable(flag);
            }
        }
        
         Border miniSquareBorder = BorderFactory.createLineBorder(Color.BLACK , 1);
           for(int i = 0 ; i < miniSquareDimension ;i++)
          {
              for(int j=0 ; j< miniSquareDimension ; j++)
              {
                  JPanel panel = new JPanel();
                  panel.setLayout(new GridLayout(miniSquareDimension,miniSquareDimension));
                  panel.setBorder(miniSquareBorder);
                  miniSquarePanel[i][j] = panel ;
                  gridPanel.add(panel);
              }
          }
           
            for(int i = 0 ; i < dimension ;i++)
            {
              for(int j = 0 ; j< dimension;j++)
              {
                  int miniSquareI = i / miniSquareDimension;
                  int miniSquareJ = j / miniSquareDimension;
    
                  miniSquarePanel[miniSquareI][miniSquareJ].add(grid[i][j].getCell());
              }
            }
           gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK , 2));
    }

    public Cell[][] getGrid() {
        return grid;
    }
    
    public void SetGrid(Cell[][] original)
    {
        grid = original;
    }
    
}
