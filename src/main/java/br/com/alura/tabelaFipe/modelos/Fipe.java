package br.com.alura.tabelaFipe.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Fipe(@JsonAlias("Valor") String valorDaTabelaFipe,
				   @JsonAlias("Marca") String marca,
				   @JsonAlias("Modelo") String modelo,
				   @JsonAlias("AnoModelo") int ano,
				   @JsonAlias("Combustivel") String combustivel,
				   @JsonAlias("CodigoFipe") String codigoTabelaFipe) {
}
