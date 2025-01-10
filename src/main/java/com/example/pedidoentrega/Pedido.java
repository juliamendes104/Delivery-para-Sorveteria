package com.example.pedidoentrega;

/**
 * Classe Pedido usa Generics para poder receber qualquer
 * tipo de Sorvete (Casquinha, Milkshake, Picolé).
 * @param <T>
 */
public class Pedido<T extends Sorvete> {
    private T item;
    private int quantidade;
    private float valor;
    private String descricao;
    private Float unidade;

    /**
     * Construtor do Pedido.
     * @param item T - Casquiha/Milkshake/Picolé
     * @param quantidade int - quantidade de itens
     * @param descricao String - descrição do pedido
     */
    public Pedido(T item, int quantidade, String descricao){
        this.item = item;
        this.quantidade = quantidade;
        this.unidade = item.getPreco();
        this.setDescricao(descricao);
        this.setValor();
    }

    /**
     * Método para alterar o valor do item, conforme a quantidade pedida.
     */
    public void setValor(){
        this.valor = item.getPreco() * this.quantidade;
    }

    /**
     * Método para acessar o valor do pedido.
     * @return retorna o valor do pedido - float
     */
    public float getValor(){
        return valor;
    }

    /**
     * Método para acessar a quantidade de itens pedidos.
     * @return retorna a quantidade de itens pedidos - int
     */
    public int getQuantidade(){
        return quantidade;
    }

    /**
     * Método para acessar a descrição do pedido.
     * @return retorna a descrição do pedido
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Método para acessar o valor por unidade do pedido.
     * @return retorna o valor por unidade do pedido
     */
    public float getUnidade(){
        return unidade;
    }

    /**
     * Método para acessar o item pedido.
     * @return retorna o item pedido
     */
    public T getItem() {
        return item;
    }

    /**
     * Método para acessar a descrição do pedido.
     * @param descricao String - descrição do pedido
     */
    public void setDescricao(String descricao){
        this.descricao = descricao + " " + item.getSabor();
    }
}
