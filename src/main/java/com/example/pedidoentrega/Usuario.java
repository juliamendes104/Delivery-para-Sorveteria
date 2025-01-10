package com.example.pedidoentrega;

/**
 * Classe abstrata usuário com atributos em comum entre todos os usuários que herdam a classe.
 */
public abstract class Usuario {
    private String email;
    private String senha;

    /**
     * Construtor do uSUARIO.
     * @param email String - email
     * @param senha String - senha
     */
    public Usuario(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    /**
     * Método para acessar o email do usuário.
     * @return retorna o email do usuário
     */
    public String getEmail(){
        return email;
    }

    /**
     * Método para acessar a senha do usuário.
     * @return retorna a senha do usuário
     */
    public String getSenha(){
        return senha;
    }
}
