package model;

import java.util.Date;

public class Produto {
    private String nome;
    private String codBarras;
    private double preco;
    private Date validade;
    private boolean perecivel;
    private int estoque;

    public Produto(String nome, String codBarras, double preco, Date validade, boolean perecivel, int estoque) {
        this.nome = nome;
        this.codBarras = codBarras;
        this.preco = preco;
        this.validade = validade;
        this.perecivel = perecivel;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public double getPreco() {
        return preco;
    }
    
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getValidade() {
        return validade;
    }

    public boolean isPerecivel() {
        return perecivel;
    }

    public int getEstoque() {
        return estoque;
    }

    public boolean reduzirEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (estoque >= quantidade) {
            estoque -= quantidade; // Reduz o estoque
            return true; // Operação bem-sucedida
        }
        return false; // Estoque insuficiente
    }

    @Override
    public String toString() {
        return nome + " (R$" + preco + ") - Estoque: " + estoque;
    }
}
