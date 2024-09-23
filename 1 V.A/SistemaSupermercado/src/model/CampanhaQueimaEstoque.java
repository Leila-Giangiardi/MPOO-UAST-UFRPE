package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CampanhaQueimaEstoque {
    public static boolean aplicarCampanha() {
        Date dateAtual = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateAtual);
        cal.add(Calendar.DAY_OF_MONTH, 2);
        Date doisDiasDepois = cal.getTime();

        ArrayList<Produto> produtos = BaseDados.getProdutos();
        boolean campanhaAplicada = false;

        for (Produto produto : produtos) {
            if (produto.getValidade().before(doisDiasDepois)) {
                produto.setPreco(produto.getPreco() * 0.5);
                campanhaAplicada = true;
                System.out.println("Campanha aplicada em: " + produto.getNome());
            }
        }

        return campanhaAplicada;
    }
}
