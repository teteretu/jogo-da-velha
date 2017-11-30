package main;

import java.util.Scanner;

class MainVelha {
    public final static int DIM = 3;
	static Scanner entrada = new Scanner(System.in);
	public static int vez;
	static boolean continuar;
  
	public static void main(String[] args) {
		//TODO Auto-generated method stub
		 
		int[][] tabuleiro = new int[DIM][DIM];
      	
	    do {
	        vez=1;
	        continuar = menu();
	        if(continuar)
	            jogar(tabuleiro);
	 
	    }while(continuar);
		
	}
	
		
	////////////
	///menus////
	////////////	
	static boolean menu() {
	    int opcao;
	 
	        System.out.println("\t\tJogo da Velha 1.0 - Site C Progressivo");
	        System.out.println("\n1.Jogar");
	        System.out.println("0.Sair");
	        System.out.println("\nOpcao: ");
	 
	        opcao = entrada.nextInt();
	 
	        switch(opcao) {
	           case 1:
	        	   return true;
	           case 0:
	                return false;
	           default:
	                clear();
	                System.out.println("Opcao invalida. Tente de novo!\n");
	        }
	    return false;
	}
  
	////////////
	////util////
	////////////
	public static void clear() {
	    int count=0;
	 
	    while(count != 100) {
	        System.out.println("");
	        count++;
	    }
	}
	 
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
	            if(tabuleiro[linha][coluna] == 0)
	                System.out.print("    ");
	            else
	                if(tabuleiro[linha][coluna] == 1)
	                    System.out.print("  X ");
	                else
	                    System.out.print("  O ");
	 
	            if(coluna != (DIM-1))
	                System.out.print("|");
	        }
	        System.out.println("");
	    }
	    System.out.println("");
	}
  
	////////////
	///jogar////
	////////////
	public static void jogar(int tabuleiro[][]) {
	    zeraTabuleiro(tabuleiro);
	 	Jogar jogada = new Jogar(tabuleiro);
      
	    do {
	        clear();
	        exibeTabuleiro(tabuleiro);
          	jogada.min(tabuleiro);
          	if (Jogar.checaTermino(tabuleiro, vez)) break;
          	
          	exibeTabuleiro(tabuleiro);
          	jogada.max(tabuleiro);
          
	        
	    }while(Jogar.checaTermino(tabuleiro, vez) == false);
	}
	
	////////////
	///árvore///
	////////////
	public static void buscaProfundidade(No raiz) {
		//buscaProfundidade(raiz.filhos.get(altura), altura);
      	Jogar.geraTabuleiro(raiz);
      	for (int i = 0; i < raiz.filhos.size(); i++)
      		exibeTabuleiro(raiz.filhos.get(i).tabuleiro);
		/*for (No filho : raiz.filhos) {//varre os filhos, até que não exista mais nenhum
      		buscaProfundidade(filho);
        }
		return true;
		 */
    }
}
