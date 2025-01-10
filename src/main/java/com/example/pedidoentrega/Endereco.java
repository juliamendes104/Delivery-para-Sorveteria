package com.example.pedidoentrega;

/**
 * Classe para guardar os  dados do endereço dos clientes.
 */
public class Endereco {
    private String rua;
    private String bairro;
    private String num;

    /**
     * Construtor do Endereco.
     * @param rua String - rua
     * @param bairro String - bairro
     * @param num String - número
     */
    public Endereco(String rua, String bairro, String num){
        this.rua = rua;
        this.bairro = bairro;
        this.num = num;
    }

    /**
     * Método para acessar a rua.
     * @return retorna a rua
     */
    public String getRua(){
        return rua;
    }

    /**
     * Método para acessar o bairro.
     * @return retorna o bairro
     */
    public String getBairro(){
        return bairro;
    }

    /**
     * Método para acessar o número.
     * @return retorna o número
     */
    public String getNum(){
        return num;
    }

    /**
     * Método para escrever o endereço na interface.
     * @return retorna rua, bairro, numero
     */
    @Override
    public String toString() {
        return rua + ", " + bairro + ", " + num + ".";
    }
}
