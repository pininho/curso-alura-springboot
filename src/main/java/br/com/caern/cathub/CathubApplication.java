package br.com.caern.cathub;

import br.com.caern.cathub.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Para obter imagens aleatórias de café
//json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
//System.out.println(json);


@SpringBootApplication
public class CathubApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CathubApplication.class, args);
	}

	@java.lang.Override
	public void run(java.lang.String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
