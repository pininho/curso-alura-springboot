package br.com.caern.cathub.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
