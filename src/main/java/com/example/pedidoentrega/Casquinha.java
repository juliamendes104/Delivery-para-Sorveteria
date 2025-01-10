package com.example.pedidoentrega;

/**
 * Classe Casquinha herda a classe abstrata Sorvete,
 * guarda os dados do sorvete do tipo casquinha.
 */
public class Casquinha extends Sorvete{
    private String tamanho; //Cascão, Casquinha

    /**
     * Construtor da Casquinha.
     * @param sabor String - sabor
     * @param estoque Estoque - quantidade no estoque
     * @param tamanho String - tamanho (casquinha, cascão)
     */
    public Casquinha(String sabor, Estoque estoque, String tamanho){
        super(sabor, estoque);
        this.tamanho = tamanho;
        this.setPreco(this.setPreco(tamanho));
    }
}
