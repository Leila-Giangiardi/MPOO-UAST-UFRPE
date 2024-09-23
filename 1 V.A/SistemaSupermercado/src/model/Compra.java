package model;

import java.util.HashMap;

public class Compra {
    private HashMap<Produto, Integer> produtos;

    public Compra() {
        produtos = new HashMap<>();
    }

    // Método para adicionar produto ao carrinho
    public void adicionarProduto(Produto produto, int quantidade) {
        if (produtos.containsKey(produto)) {
            // Se já estiver no carrinho, apenas aumenta a quantidade
            produtos.put(produto, produtos.get(produto) + quantidade);
        } else {
            // Caso contrário, adiciona o produto novo ao carrinho
            produtos.put(produto, quantidade);
        }
    }

    // Método para alterar a quantidade de um produto
    public void alterarQuantidadeProduto(Produto produto, int novaQuantidade) {
        if (produtos.containsKey(produto)) {
            if (novaQuantidade <= 0) {
                // Se a nova quantidade for 0 ou negativa, remove o produto do carrinho
                produtos.remove(produto);
            } else {
                // Caso contrário, atualiza a quantidade
                produtos.put(produto, novaQuantidade);
            }
        } else {
            System.out.println("Produto não encontrado no carrinho!");
        }
    }

    // Método para obter o valor total da compra
    public double getValorTotal() {
        double total = 0;
        for (Produto produto : produtos.keySet()) {
            total += produto.getPreco() * produtos.get(produto);
        }
        return total;
    }

    // Método para obter a lista de produtos e suas quantidades
    public HashMap<Produto, Integer> getProdutos() {
        return produtos;
    }

    // Método para remover um produto do carrinho
    public void removerProduto(Produto produto) {
        produtos.remove(produto);
    }
}