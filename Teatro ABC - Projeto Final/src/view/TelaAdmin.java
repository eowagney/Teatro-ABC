package view;

import dao.EstatisticasDAO;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TelaAdmin extends JFrame {

    private EstatisticasDAO dao = new EstatisticasDAO();

    public TelaAdmin() {
        setTitle("Teatro ABC - Administração");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JLabel titulo = new JLabel("Painel Administrativo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(60, 63, 65));
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 40, 10);
        painel.add(titulo, gbc);

        JButton btnIngressos = new JButton("Peça com mais e menos ingressos vendidos");
        configurarBotao(btnIngressos);
        gbc.gridy++;
        painel.add(btnIngressos, gbc);

        JButton btnOcupacao = new JButton("Sessão com maior e menor ocupação de poltronas");
        configurarBotao(btnOcupacao);
        gbc.gridy++;
        painel.add(btnOcupacao, gbc);

        JButton btnLucro = new JButton("Peça/Sessão mais e menos lucrativa");
        configurarBotao(btnLucro);
        gbc.gridy++;
        painel.add(btnLucro, gbc);

        JButton btnFaturamento = new JButton("Faturamento médio por peça (todas as áreas)");
        configurarBotao(btnFaturamento);
        gbc.gridy++;
        painel.add(btnFaturamento, gbc);

        JTextArea areaTexto = new JTextArea(15, 60);
        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 20, 10);
        painel.add(scroll, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(150, 35));
        botaoVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botaoVoltar.setBackground(new Color(76, 175, 80));
        botaoVoltar.setForeground(Color.WHITE);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(10, 10, 20, 10);
        painel.add(botaoVoltar, gbc);

        botaoVoltar.addActionListener(e -> {
            // Voltar para a tela anterior - ajuste conforme seu projeto
            dispose();
        });

        btnIngressos.addActionListener(e -> {
            Map<String, Integer> ingressos = dao.ingressosPorPeca();
            if (ingressos.isEmpty()) {
                areaTexto.setText("Nenhuma informação encontrada.");
                return;
            }
            String maisVendido = Collections.max(ingressos.entrySet(), Map.Entry.comparingByValue()).getKey();
            String menosVendido = Collections.min(ingressos.entrySet(), Map.Entry.comparingByValue()).getKey();

            StringBuilder sb = new StringBuilder();
            sb.append("Ingressos vendidos por peça:\n");
            ingressos.forEach((peca, qtd) -> sb.append(String.format("  %s: %d ingressos\n", peca, qtd)));
            sb.append("\nPeça com MAIS ingressos vendidos: ").append(maisVendido);
            sb.append("\nPeça com MENOS ingressos vendidos: ").append(menosVendido);

            areaTexto.setText(sb.toString());
        });

        btnOcupacao.addActionListener(e -> {
            Map<String, Integer> ocupacao = dao.ocupacaoPorSessao();
            if (ocupacao.isEmpty()) {
                areaTexto.setText("Nenhuma informação encontrada.");
                return;
            }
            String maiorOcupacao = Collections.max(ocupacao.entrySet(), Map.Entry.comparingByValue()).getKey();
            String menorOcupacao = Collections.min(ocupacao.entrySet(), Map.Entry.comparingByValue()).getKey();

            StringBuilder sb = new StringBuilder();
            sb.append("Ocupação de poltronas por sessão:\n");
            ocupacao.forEach((sessao, qtd) -> sb.append(String.format("  %s: %d poltronas ocupadas\n", sessao, qtd)));
            sb.append("\nSessão com MAIOR ocupação: ").append(maiorOcupacao);
            sb.append("\nSessão com MENOR ocupação: ").append(menorOcupacao);

            areaTexto.setText(sb.toString());
        });

        btnLucro.addActionListener(e -> {
            Map<String, Double> lucro = dao.lucroPorPecaSessao();
            if (lucro.isEmpty()) {
                areaTexto.setText("Nenhuma informação encontrada.");
                return;
            }
            String maisLucrativo = Collections.max(lucro.entrySet(), Map.Entry.comparingByValue()).getKey();
            String menosLucrativo = Collections.min(lucro.entrySet(), Map.Entry.comparingByValue()).getKey();

            StringBuilder sb = new StringBuilder();
            sb.append("Lucro por peça/sessão:\n");
            lucro.forEach((chave, valor) -> sb.append(String.format("  %s: R$ %.2f\n", chave, valor)));
            sb.append("\nPeça/Sessão MAIS lucrativa: ").append(maisLucrativo);
            sb.append("\nPeça/Sessão MENOS lucrativa: ").append(menosLucrativo);

            areaTexto.setText(sb.toString());
        });

        btnFaturamento.addActionListener(e -> {
            Map<String, Double> faturamento = dao.faturamentoMedioPorPeca();
            if (faturamento.isEmpty()) {
                areaTexto.setText("Nenhuma informação encontrada.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Faturamento médio por peça:\n");
            faturamento.forEach((peca, valor) -> sb.append(String.format("  %s: R$ %.2f\n", peca, valor)));

            areaTexto.setText(sb.toString());
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
