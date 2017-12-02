package main;


import java.util.ArrayList;
import java.util.List;

class No {
	public int[][] tabuleiro = new int[MainVelha.DIM][MainVelha.DIM];

	public List<No> filhos = new ArrayList<No>();
  
  	public int utility;

	public No(int tabuleiro[][]) {
		this.tabuleiro = tabuleiro;
	}
  	
  	public void setUtility(int utility) {
      	this.utility += utility;
    }
  
  	public int getUtility() {
      	return this.utility;
    }
  	
  	public void generateUtility(No raiz) {
  		int soma;
  		soma = 0;
  		for (No filho : raiz.filhos)
  			soma += filho.utility;
  		
  		raiz.setUtility(soma);
  	}
}
