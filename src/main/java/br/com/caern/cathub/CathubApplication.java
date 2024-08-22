package br.com.caern.cathub;

import br.com.caern.cathub.model.DadosEpisodio;
import br.com.caern.cathub.model.DadosSerie;
import br.com.caern.cathub.model.DadosTemporada;
import br.com.caern.cathub.service.ConsumoApi;
import br.com.caern.cathub.service.ConverteDados;
import br.com.caern.cathub.service.DadosEnv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CathubApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CathubApplication.class, args);
	}

	@java.lang.Override
	public void run(java.lang.String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var apiKey = DadosEnv.loadEnv("API_KEY");
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&apikey=" + apiKey);
		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		json = consumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&season=1&episode=1&apikey=" + apiKey);
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for(int i = 1; i <= dados.totalTemporadas(); i++) {
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=game+of+thrones&season=" + i + "&apikey=" + apiKey);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);

		//json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);
	}
}
