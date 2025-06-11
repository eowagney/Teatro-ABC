package view;

import java.awt.*;
import javax.swing.*;

public class TelaUsuario extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaUsuario(String nomeUsuario) {
        setTitle("Teatro ABC - Usuário");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JLabel saudacao = new JLabel("Olá, " + nomeUsuario + "!!!");
        saudacao.setFont(new Font("Segoe UI", Font.BOLD, 26));
        saudacao.setForeground(new Color(60, 63, 65));
        gbc.gridy = 0;
        gbc.insets = new Insets(30, 10, 40, 10);
        painel.add(saudacao, gbc);

        JButton botaoComprar = new JButton("Comprar Ingresso");
        configurarBotaoVerde(botaoComprar);
        gbc.gridy++;
        painel.add(botaoComprar, gbc);

        JButton botaoImprimir = new JButton("Imprimir Ingresso");
        configurarBotaoVerde(botaoImprimir);
        gbc.gridy++;
        gbc.insets = new Insets(15, 10, 40, 10);
        painel.add(botaoImprimir, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(150, 35));
        botaoVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botaoVoltar.setBackground(new Color(33, 150, 243)); // Azul
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        painel.add(botaoVoltar, gbc);

        botaoVoltar.addActionListener(e -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });
        
        botaoComprar.addActionListener(e -> {
            TelaReserva telaReserva = new TelaReserva(TelaUsuario.this);
            dispose();
        });

        botaoImprimir.addActionListener(e -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });

        add(painel);
        setVisible(true);
    }

    private void configurarBotaoVerde(JButton botao) {
        botao.setPreferredSize(new Dimension(300, 40));
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botao.setBackground(new Color(76, 175, 80)); // Verde
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unused")
    private void configurarBotaoAzul(JButton botao) {
        botao.setPreferredSize(new Dimension(300, 40));
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botao.setBackground(new Color(33, 150, 243)); // Azul
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
