package main;

import java.util.Scanner;

class Jogar {
  public int[][] tabuleiro = new int[MainVelha.DIM][MainVelha.DIM];
  static Scanner entrada = new Scanner(System.in);
  public final static int DIM = MainVelha.DIM;
  
  public Jogar(int tabuleiro[][]) {
    	this.tabuleiro = tabuleiro;
  }
  
  public void min(int tabuleiro[][]) {//jogador
    	int linha, coluna;
	    MainVelha.vez++;
	    System.out.println("\n--> Jogador " + ((MainVelha.vez % 2) + 1));
	 
	    do {
	    	System.out.print("Linha: ");
	    	linha = entrada.nextInt();
	        linha--;
	 
	        System.out.print("Coluna: ");
	        coluna = entrada.nextInt();
	        coluna--;
	 
	        if(checaLocal(tabuleiro, linha, coluna) == false)
	            System.out.println("Posicao ocupada ou inexistente, escolha outra.");
	        
	    } while(checaLocal(tabuleiro, linha, coluna) == false);
	    
	    if((MainVelha.vez%2) != 0)
	        tabuleiro[linha][coluna] = -1;
	    else
	        tabuleiro[linha][coluna] = 1;
  }
  
  public void max(int tabuleiro[][]) {//máquina
	    MainVelha.vez++;
	    System.out.println("\n--> Jogador " + ((MainVelha.vez % 2) + 1));
	 
	    
        No raiz = new No(tabuleiro);
	    MainVelha.buscaProfundidade(raiz);
    
  }
  
  ////////////
  ///Checar///
  ////////////
	static boolean checaLocal(int tabuleiro[][], int linha, int coluna) {
	    if(linha < 0 || linha > (DIM-1) || coluna < 0 || coluna > (DIM-1) || tabuleiro[linha][coluna] != 0)
	        return false;
	    else
	        return true;
	}
	 
	static boolean checaLinha (int tabuleiro[][]) {
	    int linha, coluna, soma;
	 
	    for(linha = 0 ; linha < DIM ; linha++) {
	        soma=0;
	 
	        for(coluna = 0 ; coluna < DIM ; coluna++)
	            soma += tabuleiro[linha][coluna];
	 
	        if(soma==DIM || soma == (-1)*DIM)
	            return true;
	    }
	 
	    return false;
	}
	 
	static boolean checaColuna(int tabuleiro[][]) {
	    int linha, coluna,
	        soma;
	    
	    for(coluna = 0 ; coluna < DIM ; coluna++) {
	        soma=0;
	 
	        for(linha = 0 ; linha < DIM ; linha++)
	            soma += tabuleiro[linha][coluna];
	 
	        if(soma==DIM || soma == (-1)*DIM)
	            return true;
	    }
	    return false;
	}
	 
	static boolean checaDiagonal(int tabuleiro[][]) {
	    int linha, diagonal_principal=0, diagonal_secundaria=0;
	 
	    for(linha = 0 ; linha < DIM ; linha++) {
	        diagonal_principal += tabuleiro[linha][linha];
	        diagonal_secundaria += tabuleiro[linha][DIM-linha-1];
	    }
	 
	    if(diagonal_principal==DIM || diagonal_principal==(-1)*DIM ||
	       diagonal_secundaria==DIM || diagonal_secundaria==(-1)*DIM)
	       return true;
	 
	    return false;
	}
	 
	static boolean checaEmpate(int tabuleiro[][]) {
	    int linha, coluna;
	 
	    for(linha = 0 ; linha < DIM ; linha++)
	        for(coluna = 0 ; coluna < DIM ; coluna++)
	            if(tabuleiro[linha][coluna] == 0)
	                return false;
	 
	    return true;
	}
  
  	static boolean checaTermino(int tabuleiro[][], int vez) {
	    if(checaLinha(tabuleiro)) {
	        System.out.println("Jogo encerrado. Jogador " + ((vez%2)+1) + " venceu !\n");
	        MainVelha.exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    if(checaColuna(tabuleiro)) {
	    	System.out.println("Jogo encerrado. Jogador " + ((vez%2)+1) + " venceu !\n");
	        MainVelha.exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    if(checaDiagonal(tabuleiro)) {
	    	System.out.println("Jogo encerrado. Jogador " + ((vez%2)+1) + " venceu !\n");
	        MainVelha.exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    if(checaEmpate(tabuleiro)) {
	        System.out.println("Jogo encerrado. Ocorreu um empate! !\n\n");
	        MainVelha.exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    return false;
	}
  	/////////////
  	//Gerar tab//
  	/////////////
  	public static void geraTabuleiro(No raiz) {
      	int linha  = 0;
      	int coluna = 0;
      	int[][] newTabuleiro = new int[DIM][DIM];
      	newTabuleiro = tabCopy(raiz.tabuleiro);
      
      	while (coluna < 3) {
            while (linha < 3) {
              
                if (raiz.tabuleiro[linha][coluna] == 0) {
                    newTabuleiro[linha][coluna] = -1;
                    No filho = new No(newTabuleiro);
                    raiz.filhos.add(filho);
                    newTabuleiro = tabCopy(raiz.tabuleiro);
                    linha++;
                }
              	linha++;
            }
          	linha = 0;
          	coluna++;
        }
    }
  	
  	public static int[][] tabCopy(int[][] tab) {
  		int[][] newTabuleiro = new int[DIM][DIM];
  		for (int i = 0;i < DIM; i++) 
  			for (int j = 0;j < DIM; j++)
  				newTabuleiro[i][j] = tab[i][j];
  		
  		return newTabuleiro;
  	}
  	
}
