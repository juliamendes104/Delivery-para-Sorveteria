package com.example.pedidoentrega;

/**
 * Classe abstrata Sorvete, guarda atributos em comum dos tipos de sorvetes
 * que herdam essa classe, como o sabor, preço e quantidade de estoque.
 */
public abstract class Sorvete {
    private String sabor;
    private static float preco;
    private Estoque estoque;

    /**
     * Construtor de Sorvete.
     * @param sabor String - sabor do sorvete
     * @param estoque Estoque - quantidade no estoque
     */
    public Sorvete(String sabor, Estoque estoque){
        this.sabor = sabor;
        this.estoque = estoque;
    }

    /**
     * Método para acessar o preço do sorvete.
     * @return retorna o preço do sorvete
     */
    public static float getPreco(){
        return preco;
    }

    /**
     * Método para alterar o preço dos itens de acordo com o tipo/tamanho.
     * @param tamanho String - tamanho/tipo do sorvete
     * @return retorna o preço do sorvete
     */
    public static float setPreco(String tamanho){
        if(tamanho.equals("Casquinha")){
            return 4.5f;
        }
        else if(tamanho.equals("Cascao")){
            return 5.5f;
        }
        else if(tamanho.equals("CopoP")){
            return 9.0f;
        }
        else if(tamanho.equals("CopoM")){
            return 11.0f;
        }
        else if(tamanho.equals("CopoG")){
            return 13.0f;
        }
        else if(tamanho.equals("Copinho P")){
            return 4.0f;
        }
        else if(tamanho.equals("Copinho M")){
            return 5.0f;
        }
        else if(tamanho.equals("Copinho G")){
            return 6.0f;
        }
        else if(tamanho.equals("PicoleAgua")){
            return 1.50f;
        }
        else if(tamanho.equals("PicoleLeite")){
            return 2.0f;
        }
        return 0;
    }

    /**
     * Método para alterar o preço do sorvete.
     * @param preco float - preço
     */
    public void setPreco(float preco){
        this.preco = preco;
    }

    /**
     * Método para acessar o sabor do sorvete.
     * @return retorna o sabor do sorvete
     */
    public String getSabor(){
        return sabor;
    }

}
