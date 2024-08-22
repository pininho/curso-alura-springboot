package br.com.caern.cathub;

import br.com.caern.cathub.model.DadosSerie;
import br.com.caern.cathub.service.ConsumoApi;
import br.com.caern.cathub.service.ConverteDados;
import br.com.caern.cathub.service.DadosEnv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

		//json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		//System.out.println(json);



	}
}
