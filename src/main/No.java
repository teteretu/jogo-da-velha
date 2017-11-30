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
  	public static void geraTabuleiro(No raiz) {
      	int linha  = 0;
      	int coluna = 0;
      	int[][] newTabuleiro = raiz.tabuleiro;
      
      	while (coluna < 3) {
            while (linha < 3) {
              
                if (raiz.tabuleiro[linha][coluna] == 0) {
                    newTabuleiro[linha][coluna] = -1;
                    No filho = new No(newTabuleiro);
                    raiz.filhos.add(filho);
                }
                newTabuleiro = raiz.tabuleiro;
              	linha++;
            }
          	linha = 0;
          	coluna++;
        }
    }
}
