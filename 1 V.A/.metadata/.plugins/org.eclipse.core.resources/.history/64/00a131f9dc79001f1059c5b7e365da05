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
				realizarCompra();
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
	private void realizarCompra() {
		String codigoBarras;
		do {
			// Pergunta ao usuário pelo código de barras
			codigoBarras = JOptionPane.showInputDialog(this, "Informe o código de barras do produto:");
			Produto produto = BaseDados.buscarProduto(codigoBarras);

			if (produto != null) {
				String quantidadeStr = JOptionPane.showInputDialog(this, "Informe a quantidade do produto:");
				try {
					int quantidade = Integer.parseInt(quantidadeStr);

					// Tente reduzir o estoque do produto
					if (produto.reduzirEstoque(quantidade)) {
						compra.adicionarProduto(produto, quantidade); // Adiciona o produto à compra

						// Perguntar se o cliente deseja adicionar mais produtos
						int resposta = JOptionPane.showConfirmDialog(this, "Deseja adicionar mais produtos?",
								"Adicionar Produto", JOptionPane.YES_NO_OPTION);
						if (resposta == JOptionPane.NO_OPTION) {
							// Exibir valor total da compra e aplicar desconto se possível
							gerente.darDesconto(compra);
							JOptionPane.showMessageDialog(this, "Valor total da compra: R$" + compra.getValorTotal(),
									"Total", JOptionPane.INFORMATION_MESSAGE);
							compra = new Compra(); // Reiniciar a compra
							updateTable(); // Atualizar tabela após compra
							break; // Sai do loop
						}
					} else {
						// Exibir mensagem de erro se não houver estoque suficiente
						JOptionPane.showMessageDialog(this, "Estoque insuficiente para a quantidade solicitada!",
								"Erro", JOptionPane.ERROR_MESSAGE);
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "Quantidade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} while (true); // Continua perguntando até que o cliente decida não adicionar mais produtos
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
