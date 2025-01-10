package com.example.pedidoentrega;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe Estoque com métodos para seu gerenciamento.
 */
public class Estoque {
    Set<String> saboresSorvete;
    private int quantCascao;
    private int quantCasquinha;
    private int quantCopoP;
    private int quantCopoM;
    private int quantCopoG;
    private int quantCopinhoP;
    private int quantCopinhoM;
    private int quantCopinhoG;
    private int quantPicoleAgua;
    private int quantPicoleLeite;

    /**
     * Construtor do Estoque.
     */
    public Estoque(){
        saboresSorvete = new HashSet<>();
    }

    /**
     * Método para receber a quantidade do estoque.
     * @param quantCascao int - quantidade de cascão
     * @param quantCasquinha int - quantidade de casquinha
     * @param quantCopoP int - quantidade de copo P
     * @param quantCopoM int - quantidade de copo M
     * @param quantCopoG int - quantidade de copo G
     * @param quantCopinhoP int - quantidade de copinho P
     * @param quantCopinhoM int - quantidade de copinho M
     * @param quantCopinhoG int - quantidade de copinho G
     * @param quantPicoleAgua int - quantidade de picolé a base de água
     * @param quantPicoleLeite int - quantidade de picolé a base de leite
     */
    public void setQuantidade(int quantCascao, int quantCasquinha, int quantCopoP, int quantCopoM, int quantCopoG, int quantCopinhoP, int quantCopinhoM, int quantCopinhoG, int quantPicoleAgua, int quantPicoleLeite) {
        this.quantCascao = quantCascao;
        this.quantCasquinha = quantCasquinha;
        this.quantCopoP = quantCopoP;
        this.quantCopoM = quantCopoM;
        this.quantCopoG = quantCopoG;
        this.quantCopinhoP = quantCopinhoP;
        this.quantCopinhoM = quantCopinhoM;
        this.quantCopinhoG = quantCopinhoG;
        this.quantPicoleAgua = quantPicoleAgua;
        this.quantPicoleLeite = quantPicoleLeite;
    }

    /**
     * Método para acessar a quantidade de cascão.
     * @return retorna a quantidade de cascão do estoque
     */
    public int getQuantCascao() {
        return this.quantCascao ;
    }

    /**
     * Método para acessar a quantidade de casquinha.
     * @return retorna a quantidade de casquinha do estoque
     */
    public int getQuantCasquinha() {
        return quantCasquinha;
    }

    /**
     * Método para acessar a quantidade de copo P.
     * @return retorna a quantidade de copo P do estoque
     */
    public int getQuantCopoP() {
        return quantCopoP;
    }

    /**
     * Método para acessar a quantidade de copo M.
     * @return retorna a quantidade de copo M do estoque
     */
    public int getQuantCopoM() {
        return quantCopoM;
    }

    /**
     * Método para acessar a quantidade de copo G.
     * @return retorna a quantidade de copo G do estoque
     */
    public int getQuantCopoG() {
        return quantCopoG;
    }

    /**
     * Método para acessar a quantidade de copinho P.
     * @return retorna a quantidade de copinho P do estoque
     */
    public int getQuantCopinhoP() {
        return quantCopinhoP;
    }

    /**
     * Método para acessar a quantidade de copinho M.
     * @return retorna a quantidade de copinho M do estoque
     */
    public int getQuantCopinhoM() {
        return quantCopinhoM;
    }

    /**
     * Método para acessar a quantidade de copinho G.
     * @return retorna a quantidade de copinho G do estoque
     */
    public int getQuantCopinhoG() {
        return quantCopinhoG;
    }

    /**
     * Método para acessar a quantidade de picolé a base de água.
     * @return retorna a quantidade de picolé a base de água do estoque
     */
    public int getQuantPicoleAgua() {
        return quantPicoleAgua;
    }

    /**
     * Método para acessar a quantidade de picolé a base de leite.
     * @return retorna a quantidade de picolé a base de leite do estoque
     */
    public int getQuantPicoleLeite() {
        return quantPicoleLeite;
    }

    /**
     * Método para alterar a quantidade de cascão do estoque.
     * @param quantidade int - quantidade de cascão
     */
    public void setQuantCascao(int quantidade) {
        this.quantCascao += quantidade;
    }

    /**
     * Método para alterar a quantidade de casquinha do estoque.
     * @param quantidade int - quantidade de casquinha
     */
    public void setQuantCasquinha(int quantidade) {
        this.quantCasquinha += quantidade;
    }

    /**
     * Método para alterar a quantidade de copo P do estoque.
     * @param quantidade int - quantidade de copo P
     */
    public void setQuantCopoP(int quantidade) {
        this.quantCopoP += quantidade;
    }

    /**
     * Método para alterar a quantidade de copo M do estoque.
     * @param quantidade int - quantidade de copo M
     */
    public void setQuantCopoM(int quantidade) {
        this.quantCopoM += quantidade;
    }

    /**
     * Método para alterar a quantidade de copo G do estoque.
     * @param quantidade int - quantidade de copo G
     */
    public void setQuantCopoG(int quantidade) {
        this.quantCopoG += quantidade;
    }

    /**
     * Método para alterar a quantidade de copinho P do estoque.
     * @param quantidade int - quantidade de copinho P
     */
    public void setQuantCopinhoP(int quantidade) {
        this.quantCopinhoP += quantidade;
    }

    /**
     * Método para alterar a quantidade de copinho M do estoque.
     * @param quantidade int - quantidade de copinho M
     */
    public void setQuantCopinhoM(int quantidade) {
        this.quantCopinhoM += quantidade;
    }

    /**
     * Método para alterar a quantidade de copinho G do estoque.
     * @param quantidade int - quantidade de copinho G
     */
    public void setQuantCopinhoG(int quantidade) {
        this.quantCopinhoG += quantidade;
    }

    /**
     * Método para alterar a quantidade de picolé a base de água do estoque.
     * @param quantidade int - quantidade de picolé a base de água
     */
    public void setQuantPicoleAgua(int quantidade) {
        this.quantPicoleAgua += quantidade;
    }

    /**
     * Método para alterar a quantidade de picolé a base de leite do estoque.
     * @param quantidade int - quantidade de picolé a base de leite
     */
    public void setQuantPicoleLeite(int quantidade) {
        this.quantPicoleLeite += quantidade;
    }

    /**
     * Método para adicionar sabor de sorvete na lista (Set) de sabores.
     * @param sabor String - novo sabor
     */
    public void adicionarSaborSorvete(String sabor){
        saboresSorvete.add(sabor);
    }

    /**
     * Método para acessar a lista (Set) de sabores.
     * @return retorna a lista de sabores
     */
    public Set<String> getSabores(){
        return saboresSorvete;
    }
}
