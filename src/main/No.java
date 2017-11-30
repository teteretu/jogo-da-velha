package main;


import java.util.ArrayList;
import java.util.List;

class No {
	public int[][] tabuleiro = new int[MainVelha.DIM][MainVelha.DIM];

	public List<No> filhos = new ArrayList<No>();

	public No(int tabuleiro[][]) {
		this.tabuleiro = tabuleiro;
      	
	}

	/*public boolean isEmpty(int index) {
		// TODO Auto-generated method stub
	
      if (filhos.get(index) == null)
			return true;
		else
			return false;
    
	}*/
  	
}
