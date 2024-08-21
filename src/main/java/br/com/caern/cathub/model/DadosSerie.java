package br.com.caern.cathub.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Para leitura quanto escrita, usar @JsonProperty("imdbVotes") String votos
// Para buscar mais de um atributo que referencie o mesmo dado, usar @JsonAlias({"Title", "Titulo"}) String titulo
// Ctrl + Alt + O remove os imports não utilizados

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {
}

