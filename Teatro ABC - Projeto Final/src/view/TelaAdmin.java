package view;

import controller.EstatisticaController;
import java.awt.*;
import javax.swing.*;

public class TelaAdmin extends JFrame {

    private final EstatisticaController dao = new EstatisticaController();

    @SuppressWarnings("UseSpecificCatch")
    public TelaAdmin() {
        setTitle("Teatro ABC - Administração");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        // Título
        JLabel titulo = new JLabel("Painel Administrativo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(60, 63, 65));
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 40, 10);
        painel.add(titulo, gbc);

        // Botão de estatísticas
        JButton mostrarEstatistica = new JButton("Mostrar estatísticas de venda");
        configurarBotao(mostrarEstatistica);
        gbc.gridy++;
        painel.add(mostrarEstatistica, gbc);

        // Área de texto para exibir estatísticas
        JTextArea areaTexto = new JTextArea(15, 60);
        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 20, 10);
        painel.add(scroll, gbc);

        // Botão voltar
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

        // Ação do botão voltar
        botaoVoltar.addActionListener(e -> {
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });

        mostrarEstatistica.addActionListener(e -> {
            try {
                String textoEstatisticas = dao.gerarEstatisticas();
                areaTexto.setText(textoEstatisticas);
            } catch (Exception ex) {
                areaTexto.setText("Erro ao gerar estatísticas:\n" + ex.getMessage());
            }
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
