package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

public class TelaReserva {

    private JComboBox<String> seletorPeca;
    private JComboBox<String> seletorSessao;
    private JComboBox<String> seletorArea;
    private JPanel painelPoltronasBotoes;
    private JFrame janelaPrincipal;

    private HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, Boolean>>>> estadoOcupacaoPoltronas;
    private HashMap<String, Integer> capacidadeMaximaPorArea;
    
    // VARIÁVEL ADICIONADA PARA ARMAZENAR A TELA DE USUÁRIO DE ORIGEM
    private JFrame telaUsuarioOrigem; 
    
    public TelaReserva(JFrame telaUsuario) {
        this.telaUsuarioOrigem = telaUsuario; // AQUI: A referência da TelaUsuario é guardada
        
        janelaPrincipal = new JFrame("Teatro ABC - Reservar Poltrona");
        janelaPrincipal.setSize(900, 700);
        janelaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setResizable(false);

        JPanel painelConteudo = new JPanel(null);
        painelConteudo.setBackground(new Color(245, 245, 245));

        estadoOcupacaoPoltronas = new HashMap<>();
        capacidadeMaximaPorArea = new HashMap<>();
        configurarCapacidadesDasAreas();

        ImageIcon imagemOriginal = new ImageIcon("C:\\Users\\CEL ENG E ENERGIA\\Desktop\\TesteDeClasses\\mapa.png");
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon imagemFinal = new ImageIcon(imagemRedimensionada);
        JLabel labelImagem = new JLabel(imagemFinal);
        labelImagem.setBounds(200, 80, 510, 130);
        painelConteudo.add(labelImagem);

        JLabel tituloPagina = new JLabel("Reservar Poltrona");
        tituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPagina.setForeground(new Color(60, 63, 65));
        tituloPagina.setBounds(20, 5, 300, 40);
        painelConteudo.add(tituloPagina);

        JLabel rotuloPeca = new JLabel("Peça:");
        rotuloPeca.setBounds(50, 290, 100, 30);
        painelConteudo.add(rotuloPeca);

        seletorPeca = new JComboBox<>(new String[]{"Peça 1", "Peça 2", "Peça 3"});
        seletorPeca.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        seletorPeca.setBounds(100, 290, 200, 30);
        painelConteudo.add(seletorPeca);

        JLabel rotuloSessao = new JLabel("Sessão:");
        rotuloSessao.setBounds(320, 290, 100, 30);
        painelConteudo.add(rotuloSessao);

        seletorSessao = new JComboBox<>(new String[]{"Manhã", "Tarde", "Noite"});
        seletorSessao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        seletorSessao.setBounds(385, 290, 200, 30);
        painelConteudo.add(seletorSessao);

        JLabel rotuloArea = new JLabel("Área:");
        rotuloArea.setBounds(610, 290, 100, 30);
        painelConteudo.add(rotuloArea);

        String[] opcoesAreas = {"Plateia A", "Plateia B", "Frisa 1", "Frisa 2", "Frisa 3","Frisa 4", "Frisa 5", "Frisa 6",
                                "Camarote 1", "Camarote 2", "Camarote 3", "Camarote 4", "Camarote 5", "Balcão Nobre"};
        seletorArea = new JComboBox<>(opcoesAreas);
        seletorArea.setMaximumRowCount(3);
        seletorArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        seletorArea.setBounds(650, 290, 200, 30);
        painelConteudo.add(seletorArea);

        painelPoltronasBotoes = new JPanel(new GridLayout(7, 13, 5, 5));
        painelPoltronasBotoes.setBackground(new Color(245, 245, 245));
        
        JScrollPane rolagemPoltronas = new JScrollPane(painelPoltronasBotoes);
        rolagemPoltronas.setBounds(19, 390, 850, 200);
        rolagemPoltronas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rolagemPoltronas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelConteudo.add(rolagemPoltronas);

        JPanel painelBotoesAcao = new JPanel(null);
        painelBotoesAcao.setBackground(new Color(245, 245, 245));
        painelBotoesAcao.setBounds(200, 610, 500, 120);

        JButton botaoReservar = new JButton("Reservar Selecionadas");
        aplicarEstiloBotao(botaoReservar, new Color(33, 150, 243));
        botaoReservar.setBounds(20, 2, 200, 40);
        painelBotoesAcao.add(botaoReservar);

        JButton botaoVoltar = new JButton("Voltar");
        aplicarEstiloBotao(botaoVoltar, new Color(244, 67, 54));
        botaoVoltar.setBounds(250, 2, 200, 40);
        painelBotoesAcao.add(botaoVoltar);

        painelConteudo.add(painelBotoesAcao);

        ActionListener ouvinteSelecao = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarExibicaoDasPoltronas();
            }
        };
        seletorPeca.addActionListener(ouvinteSelecao);
        seletorSessao.addActionListener(ouvinteSelecao);
        seletorArea.addActionListener(ouvinteSelecao);

        botaoReservar.addActionListener(e -> {
            String nomePecaSelecionada = (String) seletorPeca.getSelectedItem();
            String nomeSessaoSelecionada = (String) seletorSessao.getSelectedItem();
            String nomeAreaSelecionada = (String) seletorArea.getSelectedItem();

            if (nomePecaSelecionada == null || nomeSessaoSelecionada == null || nomeAreaSelecionada == null) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Por favor, selecione Peça, Sessão e Área.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Integer> poltronasSelecionadas = new ArrayList<>();
            for (Component componente : painelPoltronasBotoes.getComponents()) {
                if (componente instanceof JButton) {
                    JButton botaoPoltrona = (JButton) componente;
                    if (botaoPoltrona.getBackground().equals(Color.GREEN)) {
                        poltronasSelecionadas.add(Integer.parseInt(botaoPoltrona.getText()));
                    }
                }
            }

            if (!poltronasSelecionadas.isEmpty()) {
                janelaPrincipal.setVisible(false);
                // AQUI: Passa a referência da TelaUsuario para a TelaCompra
                new TelaCompra(nomePecaSelecionada, nomeSessaoSelecionada, nomeAreaSelecionada, poltronasSelecionadas, this, telaUsuarioOrigem);
            } else {
                JOptionPane.showMessageDialog(janelaPrincipal, "Nenhuma poltrona selecionada para reserva.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // AQUI: Modificação do ActionListener do botão Voltar
        botaoVoltar.addActionListener(e -> {
            janelaPrincipal.dispose(); // Fecha a TelaReserva
            if (telaUsuarioOrigem != null) {
                telaUsuarioOrigem.setVisible(true); // Torna a TelaUsuario visível novamente
            }
        });

        janelaPrincipal.add(painelConteudo);
        janelaPrincipal.setVisible(true);

        atualizarExibicaoDasPoltronas();
    }

    private void configurarCapacidadesDasAreas() {
        capacidadeMaximaPorArea.put("Plateia A", 25);
        capacidadeMaximaPorArea.put("Plateia B", 99);
        for (int i = 1; i <= 6; i++) {
            capacidadeMaximaPorArea.put("Frisa " + i, 5);
        }
        for (int i = 1; i <= 5; i++) {
            capacidadeMaximaPorArea.put("Camarote " + i, 10);
        }
        capacidadeMaximaPorArea.put("Balcão Nobre", 100);
    }

    public void marcarPoltronaComoOcupada(String nomePeca, String nomeSessao, String nomeArea, int numeroPoltrona) {
        estadoOcupacaoPoltronas
                .computeIfAbsent(nomePeca, k -> new HashMap<>())
                .computeIfAbsent(nomeSessao, k -> new HashMap<>())
                .computeIfAbsent(nomeArea, k -> new HashMap<>()) 
                .put(numeroPoltrona, true);
    }

    private void marcarPoltronaComoDisponivel(String nomePeca, String nomeSessao, String nomeArea, int numeroPoltrona) {
        if (estadoOcupacaoPoltronas.containsKey(nomePeca) &&
            estadoOcupacaoPoltronas.get(nomePeca).containsKey(nomeSessao) &&
            estadoOcupacaoPoltronas.get(nomePeca).get(nomeSessao).containsKey(nomeArea)) {
            estadoOcupacaoPoltronas.get(nomePeca).get(nomeSessao).get(nomeArea).put(numeroPoltrona, false);
        }
    }

    private boolean verificarSePoltronaEstaOcupada(String nomePeca, String nomeSessao, String nomeArea, int numeroPoltrona) {
        return estadoOcupacaoPoltronas.getOrDefault(nomePeca, new HashMap<>())
                .getOrDefault(nomeSessao, new HashMap<>())
                .getOrDefault(nomeArea, new HashMap<>()) 
                .getOrDefault(numeroPoltrona, false);
    }

    public void atualizarExibicaoDasPoltronas() {
        painelPoltronasBotoes.removeAll();

        String nomePecaAtual = (String) seletorPeca.getSelectedItem();
        String nomeSessaoAtual = (String) seletorSessao.getSelectedItem();
        String nomeAreaAtual = (String) seletorArea.getSelectedItem();

        if (nomePecaAtual == null || nomeSessaoAtual == null || nomeAreaAtual == null) {
            painelPoltronasBotoes.revalidate();
            painelPoltronasBotoes.repaint();
            return;
        }

        int totalPoltronas = capacidadeMaximaPorArea.getOrDefault(nomeAreaAtual, 0);

        for (int numeroPoltrona = 1; numeroPoltrona <= totalPoltronas; numeroPoltrona++) {
            JButton botaoPoltrona = new JButton(String.valueOf(numeroPoltrona));
            botaoPoltrona.setPreferredSize(new Dimension(50, 50));
            botaoPoltrona.setFont(new Font("Segoe UI", Font.BOLD, 12));
            botaoPoltrona.setFocusPainted(false);

            boolean estaOcupada = verificarSePoltronaEstaOcupada(nomePecaAtual, nomeSessaoAtual, nomeAreaAtual, numeroPoltrona);

            if (estaOcupada) {
                botaoPoltrona.setBackground(Color.RED);
                botaoPoltrona.setForeground(Color.WHITE);
                botaoPoltrona.setEnabled(false);
            } else {
                botaoPoltrona.setBackground(Color.LIGHT_GRAY);
                botaoPoltrona.setForeground(Color.BLACK);
                botaoPoltrona.setEnabled(true);
            }

            final int numeroPoltronaParaListener = numeroPoltrona;
            botaoPoltrona.addActionListener(e -> {
                if (botaoPoltrona.getBackground().equals(Color.RED)) {
                    int confirmacao = JOptionPane.showConfirmDialog(janelaPrincipal, "Deseja desmarcar a poltrona " + numeroPoltronaParaListener + " da área " + nomeAreaAtual + "?", "Desmarcar Poltrona", JOptionPane.YES_NO_OPTION);
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        marcarPoltronaComoDisponivel(nomePecaAtual, nomeSessaoAtual, nomeAreaAtual, numeroPoltronaParaListener);
                        atualizarExibicaoDasPoltronas();
                    }
                } else {
                    if (botaoPoltrona.getBackground().equals(Color.GREEN)) {
                        botaoPoltrona.setBackground(Color.LIGHT_GRAY);
                        botaoPoltrona.setForeground(Color.BLACK);
                    } else {
                        botaoPoltrona.setBackground(Color.GREEN);
                        botaoPoltrona.setForeground(Color.WHITE);
                    }
                }
            });
            painelPoltronasBotoes.add(botaoPoltrona);
        }

        painelPoltronasBotoes.revalidate();
        painelPoltronasBotoes.repaint();
    }

    private void aplicarEstiloBotao(JButton botao, Color cor) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(150, 40));
    }

    public JFrame getJanelaPrincipal() {
        return janelaPrincipal;
    }
}