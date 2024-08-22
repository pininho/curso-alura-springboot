package br.com.caern.cathub.service;

import io.github.cdimascio.dotenv.Dotenv;

public class DadosEnv {

    public static String loadEnv(String chave) {
        Dotenv dotenv = Dotenv.load();
        String valor = dotenv.get(chave);

        return valor;
    }
}
