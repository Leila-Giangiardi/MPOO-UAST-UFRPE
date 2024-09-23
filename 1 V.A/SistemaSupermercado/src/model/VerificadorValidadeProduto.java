package model;

import java.util.Calendar;
import java.util.Date;

public class VerificadorValidadeProduto implements Verificador {
    @Override
    public boolean verificarValidade(Produto produto) {
        Date dateAtual = new Date(System.currentTimeMillis());
        Calendar calB = Calendar.getInstance();
        calB.setTime(dateAtual);
        dateAtual = calB.getTime();

        // Verifica se o produto está vencido
        return produto.getValidade().before(dateAtual);
    }
}
