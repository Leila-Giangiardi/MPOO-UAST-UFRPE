package model;

import java.util.ArrayList;
import java.util.Calendar;

public class BaseDados {
    private static ArrayList<Produto> produtos = new ArrayList<>();
    private static Verificador verificador = new VerificadorValidadeProduto(); // Usar o verificador implementado

    // Método para inicializar a base de dados com os produtos solicitados
    public static void inicializarBase() {
        // Feijão da Serra
        Produto feijao = new Produto("Feijão da Serra", "PROD001", 9.00, getData(2025, 10, 12), false, 100);
        addProduto(feijao);

        // Laranja Formosa
        Produto laranja = new Produto("Laranja Formosa", "PROD003", 0.50, getData(2025, 8, 30), true, 100);
        addProduto(laranja);

        // Biscoito Treloso
        Produto biscoito = new Produto("Biscoito Treloso", "PROD002", 1.50, getData(2025, 2, 1), false, 100);
        addProduto(biscoito);
        
        //Arroz Urbano
        Produto arroz = new Produto("Arroz Urbano", "PROD004", 4.50, getData(2024, 9, 24), false, 100);
        addProduto(arroz);
        
        System.out.println("Base de dados inicializada com sucesso!");
    }

    // Método para adicionar um produto na base de dados
    public static boolean addProduto(Produto produto) {
        // Verifica se o produto já está cadastrado
        for (Produto p : produtos) {
            if (p.getCodBarras().equals(produto.getCodBarras())) {
                System.out.println("Produto já cadastrado!");
                return false;
            }
        }

        // Verifica se o produto está vencido antes de adicioná-lo
        if (verificador.verificarValidade(produto)) {
            System.out.println("Erro ao cadastrar produto: Produto vencido!");
            return false;
        }

        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
        return true;
    }

    // Método auxiliar para retornar uma data a partir do ano, mês e dia
    private static java.util.Date getData(int ano, int mes, int dia) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes - 1, dia);  // Lembre-se que o mês em Calendar começa em 0
        return calendar.getTime();
    }
    
    public static ArrayList<String> exibirProduto() {
        ArrayList<String> nomes = new ArrayList<>();
        for (Produto p : produtos) {
            nomes.add(p.getNome());
        }
        return nomes;
    }

    public static void exibirDetalhesProdutos() {
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
    public static Produto buscarProduto(String codBarras) {
        for (Produto p : produtos) {
            if (p.getCodBarras().equals(codBarras)) {
                return p;
            }
        }
        System.out.println("Produto não encontrado!");
        return null;
    }
    public static boolean removerProduto(String codBarras) {
        Produto produto = buscarProduto(codBarras);
        if (produto != null) {
            produtos.remove(produto);
            System.out.println("Produto removido com sucesso!");
            return true;
        }
        System.out.println("Erro ao remover produto: Produto não encontrado!");
        return false;
    }
    
    // Método para retornar todos os produtos
    public static ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    public static boolean verificarEstoque(String codBarras, int quantidade) {
        Produto produto = buscarProduto(codBarras);
        if (produto != null) {
            return produto.getEstoque() >= quantidade;
        }
        return false;
    }

    // Método para ajustar o estoque de um produto
    public static boolean ajustarEstoque(String codBarras, int quantidade) {
        Produto produto = buscarProduto(codBarras);
        if (produto != null && quantidade >= 0) {
            produto.reduzirEstoque(quantidade);
            return true;
        }
        System.out.println("Erro ao ajustar estoque: Produto não encontrado ou quantidade inválida!");
        return false;
    }
    // Demais métodos (buscarProduto, exibirDetalhesProdutos, etc.) permanecem os mesmos
}
