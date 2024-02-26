package br.com.alura.tabelaFipe.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Versoes(@JsonAlias("codigo") String numero,
                      @JsonAlias("nome") String versao) {
}
