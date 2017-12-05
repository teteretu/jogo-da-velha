package main;

public class Arvore {
	static final int DIM = Jogar.DIM;
	
	public Arvore(){
		
	}
	
	////////////
	///árvore///
	////////////
	
	public static int gerarArvore(No raiz) {
		int run = 0;
		
		for (No filho : raiz.filhos) {		  	
		  	run = geraTabuleiro(filho, Jogar.vez);
		  	if (run != 1) {
		  		gerarArvore(filho);
		  		raiz.generateUtility(raiz);
		  	}
		  	else { 
		  		raiz.generateUtility(raiz);
		  		return 0;
		  	}
		  	
		}
	  
		return 1;
	}
	
	/////////////
	//Gerar tab//
	/////////////
	public static int geraTabuleiro(No raiz, int vez) {
                Jogar.vez++;
		//retorna se o da vez ganhou ou n
		int linha  = 0;
		int coluna = 0;
		int end = 0;//para saber quantas posições vagas
		int[][] newTabuleiro = new int[DIM][DIM];
		newTabuleiro = tabCopy(raiz.tabuleiro);
	
		while (coluna < 3) {
			while (linha < 3) {
	      
		        if (raiz.tabuleiro[linha][coluna] == 0) {
		          	end++;
		          	if ((vez%2) != 0)
		              	newTabuleiro[linha][coluna] = -1;
		          	else
		              newTabuleiro[linha][coluna] = 1;
		          
		          	No filho = new No(newTabuleiro);
		          	raiz.filhos.add(filho);
		          	
		            newTabuleiro = tabCopy(raiz.tabuleiro);
		          
		            if (Jogar.checaTermino(filho.tabuleiro, vez)) {//end and win
		            	if (Jogar.checaEmpate(filho.tabuleiro))
		            		filho.setUtility(0);
		            	else {
			            	if ((vez%2) != 0)
			            		filho.setUtility(1);//if win, some the utility
			            	else
			            		filho.setUtility(-1);
		            	}
		            	filho.generateUtility(raiz);//some the utility of yours sons
		            	return 1;
		            }
		            
		        }
		      	linha++;
		    }//end while
	  	linha = 0;
	  	coluna++;
		}//end while
		if (end<=1)
			return 1;//não ha mais possibilidades
		
		return 0;//não terminou
	}
	
	public static int[][] tabCopy(int[][] tab) {
		int[][] newTabuleiro = new int[DIM][DIM];
		for (int i = 0;i < DIM; i++) 
			for (int j = 0;j < DIM; j++)
				newTabuleiro[i][j] = tab[i][j];
		
		return newTabuleiro;
	}
}