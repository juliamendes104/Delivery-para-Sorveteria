package com.example.pedidoentrega;

import java.util.ArrayList;

/**
 * Classe Entregador herda a classe abstrata Usuario,
 * guarda os dados dos entregadores.
 */
public class Entregador extends Usuario {
    private String nome;
    private String telefone;
    private ArrayList<Carrinho> carrinhos;

    /**
     * Construtor do Entregador.
     * @param email String - email do entregador
     * @param senha String - senha do entregador
     * @param nome String - nome do entregador
     * @param telefone String - telefone do entregador
     */
    public Entregador(String email, String senha, String nome, String telefone){
        super(email,senha);
        this.nome = nome;
        this.telefone = telefone;
        carrinhos = new ArrayList<>();
    }

    /**
     * Método para adicionar carrinho de pedidos na lista.
     * @param carrinho Carrinho - carrinho de pedidos
     */
    public void adicionarCarrinho(Carrinho carrinho){
        carrinhos.add(carrinho);
    }

    /**
     * Método para remover carrinho de pedidos da lista.
     * @param carrinho Carrinho - carrinho de pedidos
     */
    public void removerCarrinho(Carrinho carrinho){
        carrinhos.remove(carrinho);
    }

    /**
     * Método para acessar o nome de entregador.
     * @return retorna o nome do entregador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método para acessar o telefone de entregador.
     * @return retorna o telefone do entregador
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Método para acessar a lista de carrinhos com os pedidos.
     * @return retorna a lista de carrinhos
     */
    public ArrayList<Carrinho> getCarrinhos(){
        return carrinhos;
    }

    /**
     * Método para escrever os dados dos entregadores na interface do
     * administrador com a lista de entregadores
     * @return retorna nome / email / telefone do entregador
     */
    @Override
    public String toString(){
        return (getNome() + " / " + getEmail() + " / " + getTelefone());
    }

}
