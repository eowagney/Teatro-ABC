package view;

import java.awt.*;
import javax.swing.*;

public class TelaAdmin  extends JFrame {
     private static final long serialVersionUID = 1L;

    public TelaAdmin() {
        setTitle("Teatro ABC - Administração");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JLabel titulo = new JLabel("Painel Administrativo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(60, 63, 65));
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 40, 10);
        painel.add(titulo, gbc);

        JButton botaoIngressos = new JButton("Peça com mais e menos ingressos vendidos");
        configurarBotao(botaoIngressos);
        gbc.gridy++;
        painel.add(botaoIngressos, gbc);

        JButton botaoOcupacao = new JButton("Sessão com maior e menor ocupação de poltronas");
        configurarBotao(botaoOcupacao);
        gbc.gridy++;
        painel.add(botaoOcupacao, gbc);

        JButton botaoLucro = new JButton("      Peça/Sessão mais e menos lucrativa      ");
        configurarBotao(botaoLucro);
        gbc.gridy++;
        painel.add(botaoLucro, gbc);

        JButton botaoFaturamento = new JButton("Faturamento médio por peça (todas as áreas)");
        configurarBotao(botaoFaturamento);
        gbc.gridy++;
        painel.add(botaoFaturamento, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(150, 35));
        botaoVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botaoVoltar.setBackground(new Color(76, 175, 80));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(40, 10, 10, 10);
        painel.add(botaoVoltar, gbc);

        botaoVoltar.addActionListener(e -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });

        add(painel);
        setVisible(true);
    }

    private void configurarBotao(JButton botao) {
        botao.setPreferredSize(new Dimension(450, 40));
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botao.setBackground(new Color(100, 181, 246));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

}
