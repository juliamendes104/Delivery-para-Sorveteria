package com.example.pedidoentrega;

import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Classe para cadastrar usuários, fazer login e deslogar.
 */
public class SorveteriaSistema {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Entregador> entregadores = new ArrayList<>();
    private Estoque estoque;
    private Administrador administradorSorveteria;
    private Cliente clienteLogado;
    private Entregador entregadorLogado;
    private Administrador administradorLogado;

    /**
     * Construtor da SorveteriaSistema que cadastra oadministrador.
     * @param email String - email do administrador da sorveteria
     * @param cnpj String - cnpj da sorveteria
     * @param senha String - senha do administrador da sorveteria
     */
    public SorveteriaSistema(String email, String cnpj, String senha){
        System.out.println("Cadastrando Administrador");
        this.administradorSorveteria = new Administrador(email, cnpj, senha);
        estoque = new Estoque();
        this.clienteLogado = null;
        this.entregadorLogado = null;
        this.administradorLogado = null;
    }

    /**
     * Método para cadastrar clientes lidos no arquivo.
     * @param nome String - nome do cliente
     * @param email String - email do cliente
     * @param telefone String - telefone do cliente
     * @param senha String - senha do cliente
     * @param rua String - rua do cliente
     * @param bairro String - bairro do cliente
     * @param num String - número do cliente
     */
    public void cadastrarCliente(String nome, String email, String telefone, String senha, String rua, String bairro,String num){
        Endereco endereco = new Endereco(rua,bairro,num);
        Cliente cliente = new Cliente(nome,email,telefone,senha,endereco);
        clientes.add(cliente);
    }

    /**
     * Método para cadastrar novo cliente, verificando se a conta já não foi cadastrada,
     * e adicionando ele no arquivo.
     * @param novo Cliente - novo cliente
     * @throws Exception
     */
    public void adicionarNovoCliente(Cliente novo) throws Exception{
        for(Cliente item : clientes){
            if(item.getNome().equals(novo.getNome()) || item.getEmail().equals(novo.getEmail()) || item.getTelefone().equals(novo.getTelefone())){
                throw new Exception("Este usuário já existe.");
            }
        }
        FileWriter arquivoClientesAtual = new FileWriter("src/main/java/com/example/pedidoentrega/clientes.txt", true);
        arquivoClientesAtual.write(novo.getNome() + "#" + novo.getEmail() + "#" + novo.getTelefone() + "#" + novo.getSenha() + "#" + novo.getEndereco().getRua() + "#" + novo.getEndereco().getBairro() + "#" + novo.getEndereco().getNum() + "\n");
        arquivoClientesAtual.close();
        clientes.add(novo);
        this.clienteLogado = novo;
    }

    /**
     * Método para cadastrar entregadores lidos no arquivo.
     * @param nome String - nome do entregador
     * @param email String - email do entregador
     * @param senha String - senha do entregador
     * @param telefone String - telefone do entregador
     */
    public void cadastrarEntregador(String nome, String email, String senha, String telefone){
        Entregador entregador = new Entregador(email,senha,nome,telefone);
        entregadores.add(entregador);
    }

    /**
     * Método para cadastrar novo entregador, verificando se já não foi cadastrado,
     * e adicionando ele no arquivo.
     * @param novo Entregador - novo entregador
     * @throws Exception
     */
    public void adicionarNovoEntregador(Entregador novo) throws Exception{
        for(Entregador item : entregadores){
            if(item.getNome().equals(novo.getNome()) || item.getEmail().equals(novo.getEmail()) || item.getTelefone().equals(novo.getTelefone())){
                throw new Exception("Este usuário já existe.");
            }
        }
        FileWriter arquivoEntregadoresAtual = new FileWriter("src/main/java/com/example/pedidoentrega/entregadores.txt", true);
        arquivoEntregadoresAtual.write(novo.getNome() + "#" + novo.getEmail() + "#" + novo.getSenha() + "#" + novo.getTelefone() + "\n");
        arquivoEntregadoresAtual.close();
        entregadores.add(novo);
    }

    /**
     * Método para buscar usuários na lista.
     * @param email String - email do usuário
     * @param senha String - senha do usuário
     * @return retorna a conta do usuário caso a encontre
     */
    public Usuario buscarUsuarios(String email, String senha){
        for(Cliente item: clientes){
            if(item.getEmail().equals(email) && item.getSenha().equals(senha)){
                return item;
            }
        }
        for(Entregador item: entregadores){
            if(item.getEmail().equals(email) && item.getSenha().equals(senha)){
                return item;
            }
        }
        if(administradorSorveteria.getEmail().equals(email) && administradorSorveteria.getSenha().equals(senha)){
            return administradorSorveteria;
        }

        return null;
    }

    /**
     * Método para fazer login do usuário.
     * @param email String - email do usuário
     * @param senha String - senha do usuário
     * @throws UsuarioInexistenteException
     */
    public void logar(String email, String senha) throws UsuarioInexistenteException{
        Usuario usuario = buscarUsuarios(email,senha);
        if(usuario!=null){
            if(usuario instanceof Cliente){
                this.clienteLogado = (Cliente)usuario;
            }
            if (usuario instanceof Entregador){
                this.entregadorLogado = (Entregador)usuario;
            }
            if (usuario instanceof Administrador){
                this.administradorLogado = (Administrador)usuario;
            }
            return;
        }
        throw new UsuarioInexistenteException("Usuário não existe.");
    }

    /**
     * Método para inicializar o estoque, atribuindo as quantidades.
     * @param cascao int - quantidade cascão
     * @param casquinha int - quantidade casquinha
     * @param copoP int - quantidade copo P
     * @param copoM int - quantidade copo M
     * @param copoG int - quantidade copo G
     * @param copinhoP int - quantidade copinho P
     * @param copinhoM int - quantidade copinho M
     * @param copinhoG int - quantidade copinho G
     * @param picoleAgua int - quantidade picolé a base de água
     * @param picoleLeite int - quantidade picolé a base de leite
     */
    public void iniciarEstoque(int cascao, int casquinha, int copoP, int copoM, int copoG, int copinhoP, int copinhoM, int copinhoG, int picoleAgua, int picoleLeite){
        this.estoque.setQuantidade(cascao,casquinha,copoP,copoM,copoG,copinhoP,copinhoM,copinhoG,picoleAgua,picoleLeite);
    }

    /**
     * Método para adicionar os sabores de sorvete.
     * @param sabor String - sabor
     */
    public void adicionarSaborSorvete(String sabor){
        estoque.adicionarSaborSorvete(sabor);
    }

    /**
     * Método para deslogar da conta.
     */
    public void deslogar(){
        this.clienteLogado = null;
        this.entregadorLogado = null;
        this.administradorLogado = null;
    }

    /**
     * Método para acessar o cliente logado.
     * @return retorna o cliente logado
     */
    public Cliente getClienteLogado(){
        return clienteLogado;
    }

    /**
     * Método para acessar o entregador logado.
     * @return retorna o entregador logado
     */
    public Entregador getEntregadorLogado(){
        return entregadorLogado;
    }

    /**
     * Método para acessar o administrador logado.
     * @return retorna o administrador logado
     */
    public Administrador getAdministradorLogado(){
        return administradorLogado;
    }

    /**
     * Método para acessar o estoque.
     * @return retorna o estoque
     */
    public Estoque getEstoque(){
        return estoque;
    }

    /**
     * Método para acessar a lista de entregadores.
     * @return retorna a lista de entregadores
     */
    public ArrayList<Entregador> getEntregadores() {
        return entregadores;
    }

    /**
     * Método para remover da lista de entregadores.
     * @param entregador Entregador - entregador a ser removido
     */
    public void removerEntregador(Entregador entregador){entregadores.remove(entregador);}

    /**
     * Método para acessar o administrador logado.
     * @return retorna o administrador logado
     */
    public Administrador getAdministradorSorveteria(){
        return administradorSorveteria;
    }
}
