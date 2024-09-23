package model;

import java.util.ArrayList;

public class Compra {
    private static int contadorCompras = 1;
    private int idCompra;
    private ArrayList<Produto> produtos;
    private double valorTotal;

    public Compra() {
        this.idCompra = contadorCompras++;
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto.reduzirEstoque(quantidade)) {
            for (int i = 0; i < quantidade; i++) {
                produtos.add(produto);
                valorTotal += produto.getPreco();
            }
            System.out.println(quantidade + " unidades de " + produto.getNome() + " adicionadas à compra.");
        } else {
            System.out.println("Estoque insuficiente para " + produto.getNome() + ".");
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getIdCompra() {
        return idCompra;
    }

    @Override
    public String toString() {
        return "Compra #" + idCompra + " - Valor: R$" + valorTotal;
    }
}
