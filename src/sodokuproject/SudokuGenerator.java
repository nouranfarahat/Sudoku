/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sodokuproject;

import java.util.Random;

/**
 *
 * @author lenovo
 */
public class SudokuGenerator {
       private Cell[][] mat; 
       private int N; 
       private int SRN; 
       private int K; 
         public SudokuGenerator(int N, int K ,Cell[][] grid ) 
	{ 
	                this.N = N; 
	                this.K = K; 
                                 double SRNd = Math.sqrt(N); 
	                SRN = (int)SRNd; 
                                  mat = grid; 
                                  fillValues();
	}
        public void fillValues() 
	{ 
		fillDiagonal(); 

		fillRemaining(0, SRN); 
                removeKDigits(); 
	} 

	public void fillDiagonal() 
	{ 

		for (int i = 0; i<N; i=i+SRN) 
			fillBox(i, i); 
	} 

	public boolean unUsedInBox(int rowStart, int colStart, int num) 
	{ 
		for (int i = 0; i<SRN; i++) 
			for (int j = 0; j<SRN; j++) 
				if (mat[rowStart+i][colStart+j].getValue()==num) 
					return false; 

		return true; 
	} 
    public    void fillBox(int row,int col) 
	{ 
		int num; 
		for (int i=0; i<SRN; i++) 
		{ 
			for (int j=0; j<SRN; j++) 
			{ 
				do
				{ 
					num = randomGenerator(N); 
				} 
				while (!unUsedInBox(row, col, num)); 

				mat[row+i][col+j].setCell(num); 
			} 
		} 
	} 

	public int randomGenerator(int num) 
	{ 
		 Random random=new Random();
                                 int number=random.nextInt(num)+1;
                                return  number;
	} 

	public boolean CheckIfSafe(int i,int j,int num) 
	{ 
		return (unUsedInRow(i, num) && 
				unUsedInCol(j, num) && 
				unUsedInBox(i-i%SRN, j-j%SRN, num)); 
	} 

	boolean unUsedInRow(int i,int num) 
	{ 
		for (int j = 0; j<N; j++) 
		if (mat[i][j].getValue() == num) 
	       return false; 
		return true; 
	} 

	public boolean unUsedInCol(int j,int num) 
	{ 
		for (int i = 0; i<N; i++) 
			if (mat[i][j].getValue() == num) 
				return false; 
		return true; 
	} 

	public boolean fillRemaining(int i, int j) 
	{ 
		if (j>=N && i<N-1) 
		{ 
			i = i + 1; 
			j = 0; 
		} 
 

		if (i < SRN) 
		{ 
			if (j < SRN) 
				j = SRN; 
		} 
		else if (i < N-SRN) 
		{ 
			if (j==(int)(i/SRN)*SRN) 
				j = j + SRN; 
		} 
		else
		{ 
			if (j == N-SRN) 
			{ 
				i = i + 1; 
				j = 0; 
				if (i>=N) 
					return true; 
			} 
		} 

		for (int num = 1; num<=N; num++) 
		{ 
			if (CheckIfSafe(i, j, num)) 
			{ 
				mat[i][j].setCell(num); 
				if (fillRemaining(i, j+1)) 
					return true; 

				mat[i][j].setCell(0); 
			} 
		} 
		return false; 
	} 

	public void removeKDigits() 
	{ 
		int count = K; 
		while (count != 0) 
		{ 
			int cellId = randomGenerator(N*(N-1)); 

			int i = (cellId/N); 
			int j = cellId%9; 
			if (j != 0) 
				j = j - 1; 
                          
			if (mat[i][j].getValue() != 0) 
			{ 
				count--; 
				mat[i][j].ClearCell(); 
                                mat[i][j].getCell().setEditable(true);
			} 
		}  
	}
        
    
}
