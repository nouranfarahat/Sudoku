/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

/**
 *
 * @author lenovo
 */
public class SodokuSolver
{
     private Cell grid[][];
    
    public SodokuSolver(Cell [][] grid)
    {
        this.grid = grid ;
        sudukuSolver();
    }
    
     public boolean isSave(int row ,int col , int num)
    {
            for( int j=0;j<grid.length;j++)
            {
                if(grid[row][j].getValue()==num)
                    return false;
            }
 
            for( int i=0;i<grid.length;i++)
            {
                if(grid[i][col].getValue() ==num)
                    return false;
            }
            
            
            int sqrt=(int)Math.sqrt(grid.length);
            int StartRowIndexInBox=row-row%sqrt;
            int StartColIndexInBox=col-col%sqrt;
 
            for(int i=StartRowIndexInBox;i<StartRowIndexInBox+sqrt;i++)
            {
                for(int j=StartColIndexInBox;j<StartColIndexInBox+sqrt;j++)
                {
                    if(grid[i][j].getValue()  ==num)
                        return false;
                }
            }
        return true;    
    }
 
    public boolean sudukuSolver()
    {
        boolean noEmptyIndex=true;
        int row=-1;
        int col=-1;
 
 
        for(int i=0;i<grid.length;i++)
        {
            for(int j=0;j<grid.length;j++)
            {
                if(grid[i][j].getValue() ==0)
                {
                    row=i;
                    col=j;
                  noEmptyIndex=false;
                  break;
                }
            }
            if(!noEmptyIndex)
                break;
        }
        if(noEmptyIndex)
            return true; 
 
        for(int x=1;x<=grid.length;x++)
        {
            if(isSave(row, col, x))
            {
                grid[row][col].setCell(x);
                if(sudukuSolver())
                { 
 
                    return true;
                }
                else 
                {
                    grid[row][col].setCell(0);
                }
            }
        }
        return false;
 
 
    }    

    public Cell[][] getIntGrid()
    {
       if(sudukuSolver())
       {
           return grid ;
       }
       return null ;  
    }
}
