package view;

import model.BaseDados;
import model.CampanhaQueimaEstoque;
import model.Compra;
import model.Gerente;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TelaProdutos extends JFrame {
	private JTable table;
	private DefaultTableModel tableModel;
	private Gerente gerente;
	private Compra compra;

	public TelaProdutos() {
		setTitle("Sistema de Supermercado");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initUI();
	}

	private void initUI() {
		// Obter a única instância do Gerente
		gerente = Gerente.getInstancia("Jose Santos", "111.111.111-11", 3200.50);
		BaseDados.inicializarBase();
		compra = new Compra();

		// Criar layout
		JPanel panel = new JPanel(new BorderLayout());

		// Criar a tabela de produtos
		tableModel = new DefaultTableModel(new Object[] { "Nome", "Código", "Preço", "Estoque", "Validade" }, 0);
		table = new JTable(tableModel);
		updateTable();

		// Botões para realizar ações
		JButton comprarButton = new JButton("Comprar Produto");
		JButton queimaEstoqueButton = new JButton("Aplicar Queima de Estoque");
		JButton exibirButton = new JButton("Exibir Produtos");

		comprarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarCompra(null);
			}
		});

		queimaEstoqueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CampanhaQueimaEstoque.aplicarCampanha();
				updateTable();
				JOptionPane.showMessageDialog(null, "Campanha de queima de estoque aplicada!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		exibirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});

		// Adicionar componentes à interface
		panel.add(new JScrollPane(table), BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(comprarButton);
		buttonPanel.add(queimaEstoqueButton);
		buttonPanel.add(exibirButton);
		exibirButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTable(); // Atualiza a tabela de produtos
			}
		});

		panel.add(buttonPanel, BorderLayout.SOUTH);
		add(panel);
	}

	// Método para atualizar a tabela de produtos
	private void updateTable() {
		tableModel.setRowCount(0); // Limpar tabela
		ArrayList<Produto> produtos = BaseDados.getProdutos();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Produto produto : produtos) {
			tableModel.addRow(new Object[] { produto.getNome(), produto.getCodBarras(), produto.getPreco(),
					produto.getEstoque(), sdf.format(produto.getValidade()) });
		}
	}

	// Método para realizar uma compra
	private void realizarCompra(JDialog compraDialog) {
	    String codigoBarras;

	    while (true) {
	        // Solicita o código de barras do produto
	        codigoBarras = JOptionPane.showInputDialog(compraDialog, "Informe o código de barras do produto:");
	        
	        // Se o usuário cancelar, fecha o diálogo
	        if (codigoBarras == null) {
	            compraDialog.dispose(); // Fecha o dialog
	            return; // Sai do método
	        }

	        Produto produto = BaseDados.buscarProduto(codigoBarras);

	        // Verifica se o produto foi encontrado
	        if (produto != null) {
	            String quantidadeStr = JOptionPane.showInputDialog(compraDialog, "Informe a quantidade do produto:");
	            
	            // Se o usuário cancelar, fecha o diálogo
	            if (quantidadeStr == null) {
	                compraDialog.dispose(); // Fecha o dialog
	                return; // Sai do método
	            }

	            try {
	                int quantidade = Integer.parseInt(quantidadeStr);
	                if (produto.reduzirEstoque(quantidade)) {
	                    compra.adicionarProduto(produto, quantidade);

	                    int resposta = JOptionPane.showConfirmDialog(compraDialog, "Deseja adicionar mais produtos?", "Adicionar Produto", JOptionPane.YES_NO_OPTION);
	                    if (resposta == JOptionPane.NO_OPTION) {
	                        // Exibe valor total da compra e aplica desconto se possível
	                        gerente.darDesconto(compra);
	                        JOptionPane.showMessageDialog(compraDialog, "Valor total da compra: R$" + compra.getValorTotal(), "Total", JOptionPane.INFORMATION_MESSAGE);
	                        compra = new Compra(); // Reinicia a compra
	                        updateTable(); // Atualiza a tabela após a compra
	                        compraDialog.dispose(); // Fecha o dialog após a compra
	                        break; // Sai do loop
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(compraDialog, "Estoque insuficiente para a quantidade solicitada!", "Erro", JOptionPane.ERROR_MESSAGE);
	                }
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(compraDialog, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(compraDialog, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TelaProdutos().setVisible(true);
			}
		});
	}
}
