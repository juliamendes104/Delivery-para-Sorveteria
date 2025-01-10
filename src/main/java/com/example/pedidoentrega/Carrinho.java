package com.example.pedidoentrega;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Carrinho define o status do pedido.
 */
public class Carrinho {
    private float precoTotal = 0;
    private static float taxaEntrega = 5.00F;
    private static int statusPendente = 0;
    private static int statusConfirmado = 1;
    private static final int statusEntregando = 2;
    private static final int statusConcluido = 3;
    private static final int statusCancelado = 4;
    protected int status = 0;
    private String codigo;
    private Endereco endereco;

    /**
     * Construtor do carrinho, recebe uma lista de pedidos e endereço
     * @param listaPedidos ArrayList - lista de pedidos
     * @param endereco Endereco - endereço
     */
    public Carrinho(ArrayList<Pedido<?>> listaPedidos, Endereco endereco){
        Random random = new Random();
        this.codigo = "#" + String.format("%06d", random.nextInt(1000000));
        this.endereco = endereco;
        this.calcularPrecoTotal(listaPedidos);
    }

    /**
     * Método para mudar o status para Confirmado.
     */
    public void confirmar(){
        if(this.getStatus() == statusPendente){
            this.status = statusConfirmado;
        }
    }

    /**
     * Método para entregar, caso o pedido não tenha sido cancelado.
     */
    public void entregar(){
        if(this.getStatus() == statusConcluido){
            throw new IllegalArgumentException("Este pedido já foi entregue.");
        } else if(this.getStatus() == statusCancelado){
            throw new IllegalArgumentException("Impossível entregar. Este pedido já foi cancelado.");
        } else{
            this.status = statusEntregando;
        }
    }

    /**
     * Método para mudar o status para Concluído, caso seja possível.
     */
    public void concluir(){
        if(this.getStatus() == statusCancelado){
            throw new IllegalArgumentException("Impossível concluir. Este pedido já foi cancelado.");
        } else{
            if(this.getStatus() == statusConcluido){
                throw new IllegalArgumentException("Este pedido já foi concluído.");
            } else if(this.getStatus() == statusEntregando){
                this.status = statusConcluido;
            }
        }
    }

    /**
     * Método para mudar o status para Cancelado, caso seja possível.
     */
    public void cancelar(){
        if(this.getStatus() == statusConcluido){
            throw new IllegalArgumentException("Impossível cancelar. Este pedido já foi entregue.");
        } else{
            if(this.getStatus() == statusEntregando){
                throw new IllegalArgumentException("Impossível cancelar. Este pedido já está sendo entregue.");
            } else{
                this.status = statusCancelado;
            }
        }
    }

    /**
     * Método para calcular o preço total do pedido.
     * @param listaPedidos ArrayList - lista de pedidos
     */
    public void calcularPrecoTotal(ArrayList<Pedido<?>> listaPedidos){
        for(Pedido item: listaPedidos){
            this.precoTotal += item.getValor();
        }
        this.precoTotal += taxaEntrega;
    }

    /**
     * Método para acessar o status.
     * @return retorna o status
     */
    public int getStatus(){
        return status;
    }

    /**
     * Método para acessar o preço total.
     * @return retorna o preço total
     */
    public float getPrecoTotal(){
        return precoTotal;
    }

    /**
     * Método para acessar o código.
     * @return retorna o código
     */
    public String getCodigo(){
        return codigo;
    }

    /**
     * Método para acessar o endereço.
     * @return retorna o endereço
     */
    public Endereco getEndereco(){
        return endereco;
    }

    /**
     * Método para modificar o endereço.
     * @param endereco Endereco - endereço
     */
    public void setEndereco(Endereco endereco){
        this.endereco = endereco;
    }
}
