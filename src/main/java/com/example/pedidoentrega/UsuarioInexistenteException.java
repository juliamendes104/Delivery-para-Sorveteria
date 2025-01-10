package com.example.pedidoentrega;

/**
 * Classe para lançar exceção caso o usuário não exista.
 */
public class UsuarioInexistenteException extends Exception{
    public UsuarioInexistenteException(String mensagem){
        super(mensagem);
    }
}
