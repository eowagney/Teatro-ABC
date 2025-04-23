package Objetos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Interfaces.CriarImpressao;

public class ImprimirIngresso implements CriarImpressao{

	@Override
	public boolean imprimir(String cpf) throws FileNotFoundException, IOException {
	    boolean encontrado = false;

	    try (BufferedReader br = new BufferedReader(new FileReader("Ingressos.txt"))) {
	        String linha;

	        JFrame janelaImprimir = new JFrame("Comprovante de Ingresso");
	        janelaImprimir.setSize(500, 400);
	        janelaImprimir.setLocationRelativeTo(null);
	        janelaImprimir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        janelaImprimir.setLayout(new BorderLayout());

	        JTextArea textArea = new JTextArea();
	        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        textArea.setMargin(new Insets(10, 10, 10, 10));
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setEditable(false);

	        JScrollPane scrollPane = new JScrollPane(textArea);
	        scrollPane.setBorder(BorderFactory.createTitledBorder("Comprovante(s)"));

	        StringBuilder comprovantes = new StringBuilder();
	        while ((linha = br.readLine()) != null) {
	            if (linha.startsWith("CPF: " + cpf)) {
	                encontrado = true;
	                String textoFormatado = linha.replace(" | ", "\n").replace(" |", "\n");
	                comprovantes.append("\n--------------- COMPROVANTE ---------------\n")
	                            .append(textoFormatado)
	                            .append("\n-------------------------------------------\n");
	            }
	        }

	        if (encontrado) {
	            textArea.setText(comprovantes.toString());
	        } else {
	            textArea.setText("Nenhum comprovante encontrado para o CPF informado.");
	        }

	        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	        JButton imprimirButton = new JButton("🖨 Imprimir");
	        imprimirButton.setFocusPainted(false);
	        imprimirButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

	        imprimirButton.addActionListener(e -> {
	            try {
	                boolean imprimido = textArea.print();
	                if (imprimido) {
	                    JOptionPane.showMessageDialog(null, "Impressão concluída com sucesso!");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Impressão cancelada.");
	                }
	            } catch (PrinterException ex) {
	                JOptionPane.showMessageDialog(null, "Erro ao imprimir: " + ex.getMessage());
	            }
	        });

	        bottomPanel.add(imprimirButton);

	        janelaImprimir.add(scrollPane, BorderLayout.CENTER);
	        janelaImprimir.add(bottomPanel, BorderLayout.SOUTH);
	        janelaImprimir.setVisible(true);
	    }

	    return encontrado;
	}
	}
