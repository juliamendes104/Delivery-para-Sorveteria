package com.example.pedidoentrega;

/**
 * Classe ItemEstoque personalizada para colocar na tabela do estoque.
 */
public class ItemEstoque {
    private String item;
    private int quantidade;

    /**
     * Construtor do ItemEstoque.
     * @param item String - item
     * @param quantidade int - quantidade do item
     */
    public ItemEstoque(String item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    /**
     * Método para acessar o item.
     * @return retorna o nome do item
     */
    public String getItem() {
        return item;
    }

    /**
     * Método para acessar a quantidade do item.
     * @return retorna a quantidade do item
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Método para alterar a quantidade de itens do estoque.
     * @param quantidade int - quantidade de itens
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
