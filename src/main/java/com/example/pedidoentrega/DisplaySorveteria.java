package com.example.pedidoentrega;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Classe DisplaySorveteria para fazer login, ler e atualizar arquivos.
 */
public class DisplaySorveteria {
    private final SorveteriaSistema sorveteria;

    /**
     * Construtor para ler os arquivos.
     */
    public DisplaySorveteria() {
        File arquivoAdm = new File("src/main/java/com/example/pedidoentrega/administrador.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(arquivoAdm);
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }

        //Ler a linha
        String linha = scan.nextLine();
        //Separar os elementos pelo #
        String[] campos = linha.split("#");
        //Shadowing:
        String email = campos[0];
        String cnpj = campos[1];
        String senha = campos[2];
        this.sorveteria = new SorveteriaSistema(email, cnpj, senha);

        File arquivoSabores = new File("src/main/java/com/example/pedidoentrega/sabores.txt");
        File arquivoEstoque = new File("src/main/java/com/example/pedidoentrega/estoque.txt");
        File arquivoClientes = new File("src/main/java/com/example/pedidoentrega/clientes.txt");
        File arquivoEntregadores = new File("src/main/java/com/example/pedidoentrega/entregadores.txt");
        try{
            lerSabores(arquivoSabores);
            lerEstoque(arquivoEstoque);
            lerClientes(arquivoClientes);
            lerEntregadores(arquivoEntregadores);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método para ler arquivo de sabores.
     * @param arquivoSabores File - arquivo de sabores
     * @throws Exception
     */
    public void lerSabores(File arquivoSabores) throws Exception{
        Scanner scan;
        try {
            scan = new Scanner(arquivoSabores);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de Sabores");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String sabor = scan.nextLine();
            System.out.println("Sabor " + sabor + " adicionado.");
            this.sorveteria.getEstoque().adicionarSaborSorvete(sabor);
        }
        scan.close();
    }

    /**
     * Método para ler arquivo do estoque.
     * @param arquivoEstoque File - arquivo do estoque
     * @throws Exception
     */
    public void lerEstoque(File arquivoEstoque) throws Exception{
        Scanner scan;
        try {
            scan = new Scanner(arquivoEstoque);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de Estoque");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String linha = scan.nextLine();
            //Separar os elementos pelo #
            String[] campos = linha.split("#");
            //Shadowing:
            int quantCascao = Integer.parseInt(campos[0]);
            int quantCasquinha = Integer.parseInt(campos[1]);
            int quantCopoP = Integer.parseInt(campos[2]);
            int quantCopoM = Integer.parseInt(campos[3]);
            int quantCopoG = Integer.parseInt(campos[4]);
            int quantCopinhoP = Integer.parseInt(campos[5]);
            int quantCopinhoM = Integer.parseInt(campos[6]);
            int quantCopinhoG = Integer.parseInt(campos[7]);
            int quantPicoleAgua = Integer.parseInt(campos[8]);
            int quantPicoleLeite = Integer.parseInt(campos[9]);
            this.sorveteria.iniciarEstoque(quantCascao, quantCasquinha, quantCopoP, quantCopoM, quantCopoG,
                    quantCopinhoP, quantCopinhoM, quantCopinhoG, quantPicoleAgua, quantPicoleLeite);
        }
    }

    /**
     * Método para ler arquivo de clientes.
     * @param arquivoClientes File - arquivo de clientes
     * @throws Exception
     */
    public void lerClientes(File arquivoClientes) throws Exception{
        Scanner scan;
        try {
            scan = new Scanner(arquivoClientes);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de Clientes");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String linha = scan.nextLine();
            //Separar os elementos pelo #
            String[] campos = linha.split("#");
            //Shadowing:
            String nome = campos[0];
            String email = campos[1];
            String telefone = campos[2];
            String senha = campos[3];
            String rua = campos[4];
            String bairro = campos[5];
            //int num = Integer.parseInt(campos[6]);
            String num = campos[6];
            System.out.println("Cadastrando Cliente");
            this.sorveteria.cadastrarCliente(nome, email, telefone, senha, rua, bairro, num);
        }
    }

    /**
     * Método para ler arquivo de entregadores.
     * @param arquivoEntregadores File - arquivo de entregadores
     * @throws Exception
     */
    public void lerEntregadores(File arquivoEntregadores) throws Exception{
        Scanner scan;
        try {
            scan = new Scanner(arquivoEntregadores);
        } catch (FileNotFoundException ex) {
            throw new Exception("Sem arquivo de Entregadores");
        }

        while (scan.hasNextLine()) {
            //Ler a linha
            String linha = scan.nextLine();
            //Separar os elementos pelo #
            String[] campos = linha.split("#");
            //Shadowing:
            String nome = campos[0];
            String email = campos[1];
            String senha = campos[2];
            String telefone = campos[3];
            this.sorveteria.cadastrarEntregador(nome, email, senha, telefone);
        }
    }

    /**
     * Método para acessar o sistema da sorveteria.
     * @return retorna o sistema da sorveteria
     */
    public SorveteriaSistema getSorveteria() {
        return sorveteria;
    }

    /**
     * Método para fazer o login do usuário.
     * @param e ActionEvent - logar
     * @param email String - email do usuário
     * @param senha String - senha do usuário
     * @throws UsuarioInexistenteException
     */
    public void loginGui(ActionEvent e, String email, String senha) throws UsuarioInexistenteException{
        this.sorveteria.logar(email,senha);
        loggedGui(e);
    }

    /**
     * Método para cadastrar novo cliente.
     * @param e ActionEvent - cadastrar
     * @param nome String - nome do usuário
     * @param email String - email do usuário
     * @param telefone String - telefone do usuário
     * @param senha String - senha do usuário
     * @param rua String - rua do usuário
     * @param bairro String - bairro do usuário
     * @param num String - número da residência do usuário
     * @throws Exception
     */
    public void cadastroGui(ActionEvent e, String nome, String email, String telefone, String senha, String rua, String bairro,String num) throws Exception{
        Endereco endereco = new Endereco(rua,bairro,num);
        this.sorveteria.adicionarNovoCliente(new Cliente(nome,email,telefone,senha, endereco));
        loggedGui(e);
    }

    /**
     * Método para cadastrar novo entregador.
     * @param e e ActionEvent - cadastrar
     * @param nome String - nome do entregador
     * @param email String - email do entregador
     * @param telefone String - telefone do entregador
     * @param senha String - senha do entregador
     * @throws Exception
     */
    public void cadastroGui(ActionEvent e, String nome, String email, String telefone, String senha) throws Exception{
        this.sorveteria.adicionarNovoEntregador(new Entregador(email, senha, nome, telefone));
        loggedGui(e);
    }

    /**
     * Método para entrar na interface certa (cliente, entregador, administrador)
     * após o login.
     * @param e ActionEvent login
     */
    public void loggedGui(ActionEvent e){
        if(sorveteria.getClienteLogado()!=null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("logged-in.fxml"));
            try{
                Parent root = loader.load();
                LoggeInController loggedinController = loader.getController();
                loggedinController.displayUsuario(sorveteria.getClienteLogado().getNome(),sorveteria.getClienteLogado().getEmail(),sorveteria.getClienteLogado().getTelefone());
                Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                stage.setTitle("Menu");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch(IOException er){
                System.err.println(er.getMessage());
            }

        }
        else if(sorveteria.getAdministradorLogado()!=null){
            InterfaceUtil.chanceScenes(e,"administrador-entregadores.fxml","Entregadores");
        }
        else if(sorveteria.getEntregadorLogado()!=null){
            InterfaceUtil.chanceScenes(e,"menu-entregador.fxml","Menu Entregador");
        }
    }

    /**
     * Método para deslogar da conta.
     */
    public void desloggedGui(){
        this.sorveteria.deslogar();
    }

    /**
     * Método para acessar o cliente logado.
     * @return retorna a conta do cliente logado
     */
    public Cliente clienteLogado(){
        return this.sorveteria.getClienteLogado();
    }

    /**
     * Método para acessar o entregador logado.
     * @return retorna a conta do entregador logado
     */
    public Entregador entregadorLogado(){
        return this.sorveteria.getEntregadorLogado();
    }

    /**
     * Método para acessar o administrador da sorveteria.
     * @return retorna a conta do administrador
     */
    public Administrador getAdministradorSorveteria(){
        return this.sorveteria.getAdministradorSorveteria();
    }

    /**
     * Método para atualizar o arquivo de entregadores.
     * @param items Lista de entregadores
     */
    public void atualizarArquivoEntregadores(ObservableList<Entregador> items){
        //coloco o nome original no arquivo temporário e apago o arquivo original
        try{
            File arquivoOriginal = new File("src/main/java/com/example/pedidoentrega/entregadores.txt");
            File arquivoTemporario = new File("src/main/java/com/example/pedidoentrega/entregadoresTemp.txt");

            BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivoTemporario));

            for(Entregador linha : items){
                escritor.write(linha.getNome() + "#" + linha.getEmail() + "#" + linha.getSenha() + "#" +linha.getTelefone());
                escritor.newLine();
            }
            escritor.close();

            if (arquivoOriginal.delete()) {
                System.out.println("Arquivo original excluído.");
                if (arquivoTemporario.renameTo(arquivoOriginal)) {
                    System.out.println("Arquivo temporario renomeado");
                } else {
                    System.out.println("Erro ao renomear o arquivo temporário.");
                }
            } else {
                System.out.println("Erro ao excluir o arquivo renomeado.");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para atualizar o arquivo de estoque.
     * @param tabela_estoque Tabela de estoque
     */
    public void atualizarArquivoEstoque(TableView<ItemEstoque> tabela_estoque) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("src/main/java/com/example/pedidoentrega/estoque.txt"));

            ObservableList<ItemEstoque> itensEstoque = tabela_estoque.getItems();

            for (ItemEstoque item : itensEstoque) {
                String linha = item.getQuantidade() + "#";
                escritor.write(linha);
            }

            escritor.close();

            System.out.println("Arquivo de estoque atualizado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
