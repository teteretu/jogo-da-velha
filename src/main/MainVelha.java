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
	        if(continuar) {
	            new Jogar(tabuleiro);
	            
	        }
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
	        	   	System.out.println("");
	                System.out.println("Opcao invalida. Tente de novo!\n");
	        }
	    return false;
	}
  
}
