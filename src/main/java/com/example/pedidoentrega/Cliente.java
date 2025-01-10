package com.example.pedidoentrega;

import java.util.ArrayList;

/**
 * Classe Cliente herda a classe abstrata Usuario,
 * possui métodos para cancelar/pagar/fazer pedidos e alterar endereço.
 */
public class Cliente extends Usuario {
    private String nome;
    private String telefone;
    private Endereco endereco;
    private Carrinho carrinho;
    private ArrayList<Carrinho> meusPedidos;
    private ArrayList<Pedido<?>> pedidos;

    /**
     * Construtor do Cliente.
     * @param nome String - nome do cliente
     * @param email String - email do cliente
     * @param telefone String - telefone do cliente
     * @param senha String - senha do cliente
     * @param endereco Endereco - endereço do cliente
     */
    public Cliente(String nome, String email, String telefone, String senha, Endereco endereco){
        super(email,senha);
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        pedidos = new ArrayList<>();
        meusPedidos = new ArrayList<>();
        this.carrinho = null;
    }

    /**
     * Método para alterar o endereço do cliente.
     * @param novoEndereco Endereco - novo endereço
     */
    public void alterarEndereco(Endereco novoEndereco){
        this.endereco = novoEndereco;
        this.carrinho.setEndereco(endereco);
    }

    /**
     * Método para acessar o telefone do cliente.
     * @return retorna o telefone do cliente
     */
    public String getTelefone(){
        return telefone;
    }

    /**
     * Método para acessar o nome do cliente.
     * @return retorna o nome do cliente
     */
    public String getNome(){
        return nome;
    }

    /**
     * Método para acessar o endereço do cliente.
     * @return retorna o endereço do cliente.
     */
    public Endereco getEndereco(){
        return endereco;
    }

    /**
     * Método para acessar o carrinho do cliente.
     * @return retorna o carrinho do cliente
     */
    public Carrinho getCarrinho(){
        return carrinho;
    }

    /**
     * Método para acessar a lista de pedidos.
     * @return retorna a lista de pedidos
     */
    public ArrayList<Pedido<?>> getPedidos(){
        return pedidos;
    }

    /**
     * Método para acessar o carrinho com a lista de pedidos do cliente.
     * @return retorna o carrinho com a lista de pedidos do cliente
     */
    public ArrayList<Carrinho> getMeusPedidos() {
        return meusPedidos;
    }

    /**
     * Método para confirmar o pedido e fazer o pagamento.
     */
    public void pagarEFinalizar(){
        this.carrinho.confirmar(); //muda o status do pedido
        //adiciona na lista de pedidos do administrador e dos entregadores
        HelloApplication.display.getAdministradorSorveteria().adicionarCarrinho(this.carrinho);
        for(Entregador entregadores: HelloApplication.display.getSorveteria().getEntregadores()){
            entregadores.adicionarCarrinho(this.carrinho);
        }
        pedidos = new ArrayList<>();
    }

    /**
     * Método para cancelar o pedido.
     * @param carrinho Carrinho - carrinho com os pedidos do cliente
     */
    public void cancelar(Carrinho carrinho){
        carrinho.cancelar();
        pedidos = new ArrayList<>();
        meusPedidos.remove(carrinho);
    }

    /**
     * Método para adicionar pedido a lista de pedidos do cliente.
     * @param sorvete Sorvete - sorvete
     * @param quantidade int - quantidade de itens
     */
    public void adicionarPedido(Sorvete sorvete, int quantidade){
        if(sorvete instanceof Casquinha){
            Pedido<Casquinha> pedido = new Pedido<>((Casquinha)sorvete,quantidade,"Casquinha");
            pedidos.add(pedido);
        }
        else if(sorvete instanceof Copinho){
            Pedido<Copinho> pedido = new Pedido<>((Copinho)sorvete,quantidade,"Copinho");
            pedidos.add(pedido);
        }
        else if(sorvete instanceof Milkshake){
            Pedido<Milkshake> pedido = new Pedido<>((Milkshake)sorvete,quantidade,"Milkshake");
            pedidos.add(pedido);
        }
        else if(sorvete instanceof Picole){
            Pedido<Picole> pedido = new Pedido<>((Picole)sorvete,quantidade,"Picole");
            pedidos.add(pedido);
        }
    }

    /**
     * Método para finalizar o pedido.
     */
    public void finalizarPedido(){
        this.carrinho = new Carrinho(pedidos,endereco);
        meusPedidos.add(carrinho);
    }

    /**
     * Método para remover pedido da lista de pedidos do cliente.
     * @param pedido Pedido - pedido a ser removido
     */
    public void removerPedido(Pedido pedido){
        pedidos.remove(pedido);
    }
}
