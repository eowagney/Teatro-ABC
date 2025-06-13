package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

import dao.ReservaDAO;

public class TelaReserva {

    private JComboBox<String> seletorPeca;
    private JComboBox<String> seletorSessao;
    private JComboBox<String> seletorArea;
    private JPanel painelPoltronasBotoes;
    private JFrame janelaPrincipal;

    private HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, Boolean>>>> estadoOcupacaoPoltronas;
    private HashMap<String, Integer> capacidadeMaximaPorArea;
    private HashMap<String, List<Integer>> numeracaoPoltronasPorArea;

    private JFrame telaUsuarioOrigem;

    public TelaReserva(JFrame telaUsuario) {
        this.telaUsuarioOrigem = telaUsuario;
        inicializarJanela();
        inicializarComponentes();
        configurarListeners();
        atualizarExibicaoDasPoltronas();
    }

    private void inicializarJanela() {
        janelaPrincipal = new JFrame("Teatro ABC - Reservar Poltrona");
        janelaPrincipal.setSize(900, 700);
        janelaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel painelConteudo = new JPanel(null);
        painelConteudo.setBackground(new Color(245, 245, 245));

        estadoOcupacaoPoltronas = new HashMap<>();
        capacidadeMaximaPorArea = new HashMap<>();
        configurarCapacidadesDasAreas();

        configurarNumeracaoDasPoltronasPorArea();

        ImageIcon imagemOriginal = new ImageIcon("D:\\Projetos\\Teatro-ABC\\Teatro ABC - Projeto Final\\MapaTeatro.png");
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(900, 270, Image.SCALE_SMOOTH);
        JLabel labelImagem = new JLabel(new ImageIcon(imagemRedimensionada));
        labelImagem.setBounds(5, 35, 870, 270);
        painelConteudo.add(labelImagem);

        JLabel tituloPagina = new JLabel("Reservar Poltrona");
        tituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPagina.setBounds(350, 0, 300, 40);
        painelConteudo.add(tituloPagina);

        configurarSeletores(painelConteudo);
        configurarPainelPoltronas(painelConteudo);
        configurarBotoes(painelConteudo);

        janelaPrincipal.add(painelConteudo);
        janelaPrincipal.setVisible(true);
    }

    private void configurarSeletores(JPanel painel) {
        seletorPeca = new JComboBox<>(new String[]{"Peça 1", "Peça 2", "Peça 3"});
        seletorPeca.setBounds(100, 330, 200, 30);
        painel.add(new JLabel("Peça:")).setBounds(50, 290 + 40, 100, 30);
        painel.add(seletorPeca);

        seletorSessao = new JComboBox<>(new String[]{"manha", "tarde", "noite"});
        seletorSessao.setBounds(385, 330, 200, 30);
        painel.add(new JLabel("Sessão:")).setBounds(320, 330, 100, 30);
        painel.add(seletorSessao);

        String[] opcoesAreas = {"Plateia A", "Plateia B", "Frisa 1", "Frisa 2", "Frisa 3", "Frisa 4", "Frisa 5", "Frisa 6", "Camarote 1", "Camarote 2", "Camarote 3", "Camarote 4", "Camarote 5", "Balcão Nobre"};
        seletorArea = new JComboBox<>(opcoesAreas);
        seletorArea.setBounds(650, 330, 200, 30);
        painel.add(new JLabel("Área:")).setBounds(610, 330, 100, 30);
        painel.add(seletorArea);
    }

    private void configurarPainelPoltronas(JPanel painel) {
        painelPoltronasBotoes = new JPanel(new GridLayout(7, 13, 5, 5));
        painelPoltronasBotoes.setBackground(new Color(245, 245, 245));
        JScrollPane rolagem = new JScrollPane(painelPoltronasBotoes);
        rolagem.setBounds(19, 390, 850, 200);
        painel.add(rolagem);
    }

    private void configurarBotoes(JPanel painel) {
        JPanel painelBotoes = new JPanel(null);
        painelBotoes.setBounds(200, 610, 500, 120);

        JButton botaoReservar = new JButton("Reservar Selecionadas");
        aplicarEstiloBotao(botaoReservar, new Color(33, 150, 243));
        botaoReservar.setBounds(20, 2, 200, 40);
        painelBotoes.add(botaoReservar);

        JButton botaoVoltar = new JButton("Voltar");
        aplicarEstiloBotao(botaoVoltar, new Color(76, 175, 80));
        botaoVoltar.setBounds(250, 2, 200, 40);
        painelBotoes.add(botaoVoltar);

        painel.add(painelBotoes);

        botaoReservar.addActionListener(e -> reservarPoltronas());
        botaoVoltar.addActionListener(e -> voltarParaTelaUsuario());
    }

    private void configurarListeners() {
        ActionListener ouvinteSelecao = e -> atualizarExibicaoDasPoltronas();
        seletorPeca.addActionListener(ouvinteSelecao);
        seletorSessao.addActionListener(ouvinteSelecao);
        seletorArea.addActionListener(ouvinteSelecao);
    }

    private void reservarPoltronas() {
        String peca = (String) seletorPeca.getSelectedItem();
        String sessao = (String) seletorSessao.getSelectedItem();
        String area = (String) seletorArea.getSelectedItem();

        List<Integer> selecionadas = new ArrayList<>();
        for (Component comp : painelPoltronasBotoes.getComponents()) {
            if (comp instanceof JButton botao && botao.getBackground().equals(Color.GREEN)) {
                selecionadas.add(Integer.valueOf(botao.getText()));
            }
        }

        if (selecionadas.isEmpty()) {
            JOptionPane.showMessageDialog(janelaPrincipal, "Nenhuma poltrona selecionada.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            janelaPrincipal.setVisible(false);
            new TelaCompra(peca, sessao, area, selecionadas, this, telaUsuarioOrigem);
        }
    }

    private void voltarParaTelaUsuario() {
        janelaPrincipal.dispose();
        if (telaUsuarioOrigem != null) telaUsuarioOrigem.setVisible(true);
    }

    public void atualizarExibicaoDasPoltronas() {
        painelPoltronasBotoes.removeAll();

        String peca = (String) seletorPeca.getSelectedItem();
        String sessao = (String) seletorSessao.getSelectedItem();
        String area = (String) seletorArea.getSelectedItem();

        if (peca == null || sessao == null || area == null) return;

        ReservaDAO dao = new ReservaDAO();
        List<Integer> reservadas = dao.buscarPoltronasReservadas(peca, sessao, area);
        List<Integer> todas = numeracaoPoltronasPorArea.getOrDefault(area, new ArrayList<>());

        for (int numero : todas) {
            JButton botao = new JButton(String.valueOf(numero));
            if (reservadas.contains(numero)) {
                botao.setEnabled(false);
                botao.setBackground(Color.RED);
            } else {
                botao.setBackground(Color.LIGHT_GRAY);
                botao.addActionListener(e -> {
                    if (botao.getBackground().equals(Color.GREEN)) {
                        botao.setBackground(Color.LIGHT_GRAY);
                    } else {
                        botao.setBackground(Color.GREEN);
                    }
                });
            }
            painelPoltronasBotoes.add(botao);
        }

        painelPoltronasBotoes.revalidate();
        painelPoltronasBotoes.repaint();
    }

    private void configurarCapacidadesDasAreas() {
        capacidadeMaximaPorArea.put("Plateia A", 25);
        capacidadeMaximaPorArea.put("Plateia B", 99);
        for (int i = 1; i <= 6; i++) capacidadeMaximaPorArea.put("Frisa " + i, 5);
        for (int i = 1; i <= 5; i++) capacidadeMaximaPorArea.put("Camarote " + i, 10);
        capacidadeMaximaPorArea.put("Balcão Nobre", 50);
    }

    private void configurarNumeracaoDasPoltronasPorArea() {
        numeracaoPoltronasPorArea = new HashMap<>();
        numeracaoPoltronasPorArea.put("Plateia A", criarIntervalo(1, 25));
        numeracaoPoltronasPorArea.put("Plateia B", criarIntervalo(26, 125));
        numeracaoPoltronasPorArea.put("Frisa 1", criarIntervalo(126, 130));
        numeracaoPoltronasPorArea.put("Frisa 2", criarIntervalo(131, 135));
        numeracaoPoltronasPorArea.put("Frisa 3", criarIntervalo(136, 140));
        numeracaoPoltronasPorArea.put("Frisa 4", criarIntervalo(141, 145));
        numeracaoPoltronasPorArea.put("Frisa 5", criarIntervalo(146, 150));
        numeracaoPoltronasPorArea.put("Frisa 6", criarIntervalo(151, 155));
        numeracaoPoltronasPorArea.put("Camarote 1", criarIntervalo(156, 165));
        numeracaoPoltronasPorArea.put("Camarote 2", criarIntervalo(166, 175));
        numeracaoPoltronasPorArea.put("Camarote 3", criarIntervalo(176, 185));
        numeracaoPoltronasPorArea.put("Camarote 4", criarIntervalo(186, 195));
        numeracaoPoltronasPorArea.put("Camarote 5", criarIntervalo(196, 205));
        numeracaoPoltronasPorArea.put("Balcão Nobre", criarIntervalo(206, 255));
    }

    private List<Integer> criarIntervalo(int inicio, int fim) {
        List<Integer> lista = new ArrayList<>();
        for (int i = inicio; i <= fim; i++) lista.add(i);
        return lista;
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