package com.example.pedidoentrega;

/**
 * Classe Picole herda a classe abstrata Sorvete,
 * guarda os dados do picolé.
 */
public class Picole extends Sorvete{
    private String base; //água, leite

    /**
     * Construtor do Picolé.
     * @param sabor String - sabor do picolé
     * @param estoque Estoque - quantidade do estoque
     * @param base String - base do picolé (água ou leite)
     */
    public Picole(String sabor, Estoque estoque, String base){
        super(sabor, estoque);
        this.base = base;
        this.setPreco(this.setPreco(base));
    }
}
