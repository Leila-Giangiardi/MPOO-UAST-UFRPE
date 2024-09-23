package model;

public class Gerente extends Funcionario {
    // Única instância do gerente
    private static Gerente instancia;

    // Construtor privado para impedir a criação de outras instâncias
    private Gerente(String nome, String cpf, double salario) {
        super(nome, cpf, salario);
    }

    // Método para retornar a única instância do gerente
    public static Gerente getInstancia(String nome, String cpf, double salario) {
        if (instancia == null) {
            instancia = new Gerente(nome, cpf, salario);
        }
        return instancia;
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
