package Objetos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;

public class ImprimirEstatisticas {

	

	public void Imprimir(String cpf) {
		
		CalcularEstatisticas estatisticas = new CalcularEstatisticas();
		estatisticas.calcualarEstatisticas(cpf);
		
	    try (BufferedReader br = new BufferedReader(new FileReader("relatorio.txt"))) {
	        String linha;
	        StringBuilder estatistica = new StringBuilder();

	        while ((linha = br.readLine()) != null) {
	            estatistica.append(linha).append("\n");
	        }

	        JFrame telaEstatistica = new JFrame("Estatísticas do Teatro ABC");
	        telaEstatistica.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        telaEstatistica.setSize(600, 400);
	        telaEstatistica.setLocationRelativeTo(null);
	        telaEstatistica.setLayout(new GridBagLayout());

	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(10, 10, 10, 10);
	        gbc.fill = GridBagConstraints.BOTH;
	        gbc.weightx = 1.0;
	        gbc.weighty = 1.0;
	        gbc.gridx = 0;
	        gbc.gridy = 0;

	        JTextArea textArea = new JTextArea(estatistica.toString());
	        textArea.setLineWrap(true);
	        textArea.setWrapStyleWord(true);
	        textArea.setEditable(false);
	        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        textArea.setBackground(new Color(245, 245, 245));

	        JScrollPane scrollPane = new JScrollPane(textArea);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.setBorder(BorderFactory.createTitledBorder("Resumo Estatístico"));

	        telaEstatistica.add(scrollPane, gbc);

	        telaEstatistica.setVisible(true);

	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo de estatísticas: " + e.getMessage(),
	                "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}
}

