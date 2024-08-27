package br.com.caern.cathub.principal;

import br.com.caern.cathub.model.DadosEpisodio;
import br.com.caern.cathub.model.DadosSerie;
import br.com.caern.cathub.model.DadosTemporada;
import br.com.caern.cathub.service.ConsumoApi;
import br.com.caern.cathub.service.ConverteDados;
import br.com.caern.cathub.service.DadosEnv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Exemplo de uso de Stream
//List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
//
//        nomes.stream()
//                .sorted()
//                .limit(3)
//                .filter(n -> n.startsWith("N"))
//                .map(n -> n.toUpperCase())
//                .forEach(System.out::println);

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = DadosEnv.loadEnv("ENDERECO");;
    private final String API_KEY = "&apikey=" + DadosEnv.loadEnv("API_KEY");;
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


    public void exibeMenu() {
        System.out.println("Digite o nome da série para a busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }

//		temporadas.forEach(System.out::println);
//
//        for(int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for(int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));


    }

}
