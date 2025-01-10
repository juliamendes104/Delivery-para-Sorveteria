package com.example.pedidoentrega;

/**
 * Classe Milkshake herda a classe abstrata Sorvete,
 * guarda os dados do milkshake.
 */
public class Milkshake extends Sorvete{
    private String tamanho; //P, M G

    /**
     * Construtor do Milkshake.
     * @param sabor String - sabor do milkshake
     * @param estoque Estoque - quantidade do estoque
     * @param tamanho String - tamanho do milkshake (P, M, G)
     */
    public Milkshake(String sabor, Estoque estoque, String tamanho){
        super(sabor, estoque);
        this.tamanho = tamanho;
        this.setPreco(this.setPreco(tamanho));
    }
}
