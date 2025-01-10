package com.example.pedidoentrega;

import java.util.ArrayList;

/**
 * Classe Administrador herda a classe abstrata Usuario,
 * possui uma lista de carrinhos que representa os pedidos dos clientes
 * e guarda os dados do administrador.
 */
public class Administrador extends Usuario{
    private String cnpj;
    private ArrayList<Carrinho> carrinhos;

    /**
     * Construtor do Administrador.
     * @param email String- email do administrador
     * @param cnpj String - cnpj do estabelecimento
     * @param senha String - senha do administrador
     */
    public Administrador(String email, String cnpj, String senha){
        super(email,senha);
        this.cnpj = cnpj;
        carrinhos = new ArrayList<>();
    }

    /**
     * Método para adicionar carrinho na lista.
     * @param carrinho Carrinho - novo carrinho do cliente
     */
    public void adicionarCarrinho(Carrinho carrinho){
        carrinhos.add(carrinho);
    }

    /**
     * Método get para retornar a lista de carrinhos.
     * @return Carrinho - lista de carrinhos
     */
    public ArrayList<Carrinho> getCarrinhos(){
        return carrinhos;
    }

}
