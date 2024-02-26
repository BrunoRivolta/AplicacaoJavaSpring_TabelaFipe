package br.com.alura.tabelaFipe.principal;

import java.util.Scanner;

public class Principal {
	public void exibeMenuInicial() {
		System.out.printf("%n%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");
		System.out.println("Bem vindo ao aplicativo de calculo de Tabela Fipe");
		System.out.printf("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n%n");
		System.out.println("Escolha o tipo de veiculo de acordo com o numero: ");
		System.out.println("1 - Carro");
		System.out.println("2 - Moto");
		System.out.println("3 - Caminhão");
		System.out.println("Digite a opção desejada: ");
	}

	public int capturaValorDigitadoInt() {
		Scanner leitura = new Scanner(System.in);
		int valor = leitura.nextInt();
		return valor;
	}
	public String capturaValorDigitadoString() {
		Scanner leitura = new Scanner(System.in);
		String valor = leitura.nextLine();
		return valor;
	}
}
