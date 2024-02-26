package br.com.alura.tabelaFipe;

import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.tabelaFipe.modelos.Fipe;
import br.com.alura.tabelaFipe.modelos.Marca;
import br.com.alura.tabelaFipe.modelos.ListaModelo;
import br.com.alura.tabelaFipe.modelos.Versoes;
import br.com.alura.tabelaFipe.principal.Principal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		ConsumoApi consumoApi = new ConsumoApi();
		ObjectMapper mapper = new ObjectMapper();

		boolean repetirConsulta = true;
		while(repetirConsulta) {
			principal.exibeMenuInicial();

			String url = null;
			boolean escolhaVeiculo = true;

			while (escolhaVeiculo) {
				int opcaoVeiculo = principal.capturaValorDigitadoInt();
				if (opcaoVeiculo == 1) {
					url = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
					escolhaVeiculo = false;
				} else if (opcaoVeiculo == 2) {
					url = "https://parallelum.com.br/fipe/api/v1/motos/marcas";
					escolhaVeiculo = false;
				} else if (opcaoVeiculo == 3) {
					url = "https://parallelum.com.br/fipe/api/v1/caminhoes/marcas";
					escolhaVeiculo = false;
				} else {
					System.out.println("Opção invalida");
				}
			}

			String respostaJsonMarcas = consumoApi.obterDados(url);

			var marcas = mapper.readValue(respostaJsonMarcas, new TypeReference<List<Marca>>() {
			});

			System.out.printf("%n%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");

			for (int i = 0; i < marcas.size(); i++) {
				System.out.printf("Codigo: %s = %s%n", marcas.get(i).numero(), marcas.get(i).marca());
			}

			System.out.printf("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n%n");

			System.out.println("Escolha a marca do seu veiculo pelo codigo:");
			int opcaoMarca = principal.capturaValorDigitadoInt();

			String urlModelo = url + "/" + opcaoMarca + "/modelos";

			String respostaJsonModelos = consumoApi.obterDados(urlModelo);

			var modelos = mapper.readValue(respostaJsonModelos, new TypeReference<ListaModelo>() {
			});


			System.out.printf("%n%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");

			for (int i = 0; i < modelos.modelo().size(); i++) {
				System.out.printf("Codigo: %s = %s%n", modelos.modelo().get(i).numero(), modelos.modelo().get(i).modelo());
			}

			System.out.printf("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n%n");

			System.out.println("Escolha o modelo do seu veiculo pelo codigo:");
			int opcaoModelo = principal.capturaValorDigitadoInt();

			String urlVersao = url + "/" + opcaoMarca + "/modelos/" + opcaoModelo + "/anos";

			String respostaJsonVersoes = consumoApi.obterDados(urlVersao);

			var versoes = mapper.readValue(respostaJsonVersoes, new TypeReference<List<Versoes>>() {
			});

			System.out.printf("%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");

			for (int i = 0; i < versoes.size(); i++) {
				System.out.printf("Codigo: %s = %s%n", versoes.get(i).numero(), versoes.get(i).versao());
			}

			System.out.printf("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n%n");

			System.out.println("Escolha a versão do seu veiculo pelo codigo:");
			String opcaoFipe = principal.capturaValorDigitadoString();

			String urlFipe = urlVersao + "/" + opcaoFipe;

			String respostaJsonFipe = consumoApi.obterDados(urlFipe);

			var fipe = mapper.readValue(respostaJsonFipe, new TypeReference<Fipe>() {
			});

			System.out.printf("%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");
			System.out.println("Dados da tabela Fipe:");
			System.out.printf("%nMarca: %s", fipe.marca());
			System.out.printf("%nModelo: %s", fipe.modelo());
			System.out.printf("%nAno: %s", fipe.ano());
			System.out.printf("%nCombustivel: %s", fipe.combustivel());
			System.out.printf("%nValor: %s", fipe.valorDaTabelaFipe());
			System.out.printf("%nCodigo Tabela Fipe: %s%n", fipe.codigoTabelaFipe());
			System.out.printf("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n%n");

			boolean selecionaRepeticao = true;

			while (selecionaRepeticao) {
				System.out.println("Deseja fazer uma nova consulta? ");
				System.out.println("Digite s = sim ou n = não: ");

				String opcaoNovaConsulta = principal.capturaValorDigitadoString();
				if (Objects.equals(opcaoNovaConsulta, "s") || Objects.equals(opcaoNovaConsulta, "S")) {
					repetirConsulta = true;
					selecionaRepeticao = false;
				} else if (Objects.equals(opcaoNovaConsulta, "n") || Objects.equals(opcaoNovaConsulta, "N")) {
					repetirConsulta = false;
					selecionaRepeticao = false;
				} else {
					selecionaRepeticao = true;
				}
			}
		}
		System.out.printf("%n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n");
		System.out.println("Tabela Fipe por Bruno Rivolta");;
		System.out.println("Obrigado por usar nossa aplicação");;
		System.out.printf("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=%n%n");
	}
}
