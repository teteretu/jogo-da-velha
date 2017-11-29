package main;

import java.util.ArrayList;
import java.util.List;

public class No {
	//MainVelha velha = new MainVelha();
	public int[][] tabela = new int[MainVelha.DIM][MainVelha.DIM];

	public List<No> filhos = new ArrayList<No>();

	public No(int tabela[][]) {
		this.tabela = tabela;
	}

	public boolean isEmpty(int altura) {
		// TODO Auto-generated method stub
		if (filhos.get(altura) == null)
			return true;
		else
			return false;
	}
}
