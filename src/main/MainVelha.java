package main;

import java.util.Scanner;

public class MainVelha {
	public final static int DIM = 3;
	static Scanner entrada = new Scanner(System.in);
	public static int vez;
	static boolean continuar;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		int[][] tabuleiro = new int[DIM][DIM];
 
	    do {
	        vez=1;
	        continuar = menu();
	        if(continuar)
	            jogar(tabuleiro);
	 
	    }while(continuar);
	}
	
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
	 
	public static void jogar(int tabuleiro[][]) {
	    zeraTabuleiro(tabuleiro);
	 
	    do {
	        clear();
	        exibeTabuleiro(tabuleiro);
	        jogada(tabuleiro);
	        
	    }while(checaTermino(tabuleiro, vez) == false);
	}
	 
	 
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
	        System.out.println("Jogo encerrado. Jogador " + (vez%2)+1 + " venceu !\n");
	        exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    if(checaColuna(tabuleiro)) {
	    	System.out.println("Jogo encerrado. Jogador " + (vez%2)+1 + " venceu !\n");
	        exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    if(checaDiagonal(tabuleiro)) {
	    	System.out.println("Jogo encerrado. Jogador " + (vez%2)+1 + " venceu !\n");
	        exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    if(checaEmpate(tabuleiro)) {
	        System.out.println("Jogo encerrado. Ocorreu um empate! !\n\n");
	        exibeTabuleiro(tabuleiro);
	        return true;
	    }
	    
	    return false;
	}
	 
	static void jogada(int tabuleiro[][]) {
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
	 
	        if(checaLocal(tabuleiro, linha, coluna) == false)
	            System.out.println("Posicao ocupada ou inexistente, escolha outra.");
	        
	    } while(checaLocal(tabuleiro, linha, coluna) == false);
	    
	    if((vez%2) != 0)
	        tabuleiro[linha][coluna] = -1;
	    else
	        tabuleiro[linha][coluna] = 1;
	}
}
