package main;

import java.util.Scanner;

class Jogar {
    public final static int DIM = 3;
	public int[][] tabuleiro = new int[DIM][DIM];
	static Scanner entrada = new Scanner(System.in);
	public static int vez;
	
    public Jogar() {
	  	jogar();
	}
  
	////////////
	///jogar////
	////////////
	public void jogar() {
		vez = 1;
		zeraTabuleiro(this.tabuleiro);
		No arvore = new No(this.tabuleiro);
		
		Arvore tree = new Arvore();
		
		tree.geraTabuleiro(arvore);//gera o primeiro tabuleiro para evitar bugs
		tree.setVez(2);
		tree.gerarArvore(arvore);// gera toda a árvore do min max
		
		do {
			vez = 1;
		  	exibeTabuleiro(this.tabuleiro);
		  	arvore = max(arvore);
		    
		  	if (checaTermino(this.tabuleiro, vez)) break;
		
		  	exibeTabuleiro(this.tabuleiro);
		  	min();
		  
		}while(!checaTermino(this.tabuleiro, vez));
	}

  public void min() {//jogador
    	int linha, coluna;
	    vez++;
	    System.out.println("\n--> Jogador " + ((vez % 2) + 1));
	 
	    do {
	    	System.out.print("Linha: ");
	    	linha = entrada.nextInt();
	        linha--;
	 
	        System.out.print("Coluna: ");
	        coluna = entrada.nextInt();
	        coluna--;
	 
	        if(checaLocal(this.tabuleiro, linha, coluna) == false)
	            System.out.println("Posicao ocupada ou inexistente, escolha outra.");
	        
	    } while(checaLocal(this.tabuleiro, linha, coluna) == false);
	    
	    
	    this.tabuleiro[linha][coluna] = 1;
  }
  
  public No max(No raiz) {//máquina
	    vez++;
	    System.out.println("\n--> Jogador " + ((vez % 2) + 1));
	    
	    boolean run = true;
	    int i = 0;
	    while(run && i < DIM) {//se for a primeira iteração gere a primeira jogada
	    	for (int j = 0; j < DIM; j++) {
	    		if(this.tabuleiro[i][j] != 0) {
	    			run = false;
	    			break;
	    		}
	    	}
	    	i++;
	    }
	    if (true == run) {
	    	No filho = melhorJogada(raiz);//retorna o filho que tem a melhor jogada
	    	this.tabuleiro = Arvore.tabCopy(filho.tabuleiro);
    		return filho;
	    }
	    			
	    for (No filho : raiz.filhos){
	    	
	    	if(equals(filho.tabuleiro, this.tabuleiro)) {
                No newFilho = melhorJogada(filho);//retorna o filho que tem a melhor jogada
                this.tabuleiro = Arvore.tabCopy(newFilho.tabuleiro);
	    		return newFilho;
	    	}
	    }
	    System.out.println("\tERROR\n O sistema não detectou uma boa jogada");
	    return null;
  }
  
  public boolean equals(int tab1[][], int tab2[][]) {
	  for (int i = 0; i < DIM; i++) {
		  for (int j = 0; j < DIM; j++) {
			  if (tab1[i][j] != tab2[i][j]) 
				  return false;
			  
		  }
	  }
	  return true;
  }
  
  public static No melhorJogada(No raiz) {
	  	int maior = raiz.filhos.get(0).utility;
	  	No filhoRetorno = raiz.filhos.get(0);
	  	
	    for (No filho : raiz.filhos) {
		    if (filho.utility > maior) {
		    		maior = filho.utility;
		    		filhoRetorno = filho;
		    }
	    }
	    
	    return filhoRetorno;
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
	//retorna 0 para empate 1 terminou -1 não terminou
  	static boolean checaTermino(int tabuleiro[][], int vez) {
	    if(checaLinha(tabuleiro)) {
	        System.out.println("Jogo encerrado. Jogador " + ((vez%2)+1) + " venceu !\n");
	        return true;
	    }
	    
	    if(checaColuna(tabuleiro)) {
	    	System.out.println("Jogo encerrado. Jogador " + ((vez%2)+1) + " venceu !\n");
	        return true;
	    }
	    
	    if(checaDiagonal(tabuleiro)) {
	    	System.out.println("Jogo encerrado. Jogador " + ((vez%2)+1) + " venceu !\n");
	        return true;
	    }
	    
	    if(checaEmpate(tabuleiro)) {
	        System.out.println("Jogo encerrado. Ocorreu um empate! !\n\n");
	        return true;
	    }
	    
	    return false;
	}
  
 	
  	
	////////////
	////util////
	////////////
	
	public static void zeraTabuleiro(int tabuleiro[][]) {
	  
		int linha, coluna;
	  for(linha = 0 ; linha < DIM ; linha++) {
	      for(coluna = 0 ; coluna < DIM ; coluna++) {
	          tabuleiro[linha][coluna] = 0;
			}
		}
	}
	
	public static void exibeTabuleiro(int tabuleiro[][]) {
		
	  int linha, coluna;
	  System.out.println("");
	
	  for(linha = 0 ; linha < DIM ; linha++) {
	      for(coluna = 0 ; coluna < DIM ; coluna++) {
                  switch (tabuleiro[linha][coluna]) {
                      case 0:
                          System.out.print("    ");
                          break;
                      case 1:
                          System.out.print("  X ");
                          break;
                      default:
                          System.out.print("  O ");
                          break;
                  }
	
	          if(coluna != (DIM-1))
	              System.out.print("|");
	      }
	      System.out.println("");
	  }
	  System.out.println("");
	}
}