package model;

public class Gerente extends Funcionario {
    public Gerente(String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    public void darDesconto(Compra compra) {
        if (compra.getValorTotal() > 100.00) {
            double desconto = compra.getValorTotal() * 0.10;
            System.out.println("Desconto aplicado: R$" + desconto);
        } else {
            System.out.println("Desconto não permitido: Compra não atingiu o valor mínimo!");
        }
    }
}
