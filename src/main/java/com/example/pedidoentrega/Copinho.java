package com.example.pedidoentrega;

/**
 * Classe Copinho herda a classe abstrata Sorvete,
 * guarda os dados do sorvete de copinho.
 */
public class Copinho extends Sorvete{
    private String tamanho; //P, M G

    /**
     * Contrutor do Copinho.
     * @param sabor String - sabor
     * @param estoque Estoque - quantidade no estoque
     * @param tamanho String - tamanho (P,M,G)
     */
    public Copinho(String sabor, Estoque estoque, String tamanho){
        super(sabor, estoque);
        this.tamanho = tamanho;
        this.setPreco(this.setPreco(tamanho));
    }

}
