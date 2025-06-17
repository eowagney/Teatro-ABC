package view;

import conexao.Conexao;
import controller.BuscarCpfController;
import controller.IdComprovantesController;
import dao.ComprovanteDAO;
import dao.PoltronaDAO;
import entity.SessaoLogin;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import objetos.NotificacaoUtil;

public class TelaCompra {

    @SuppressWarnings("unused")
    private JFrame telaOrigem;

    private JFrame janelaPrincipal;
    private String nomePeca;
    private String nomeSessao;
    private String nomeArea;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Integer> numerosPoltronas;
    private TelaReserva telaReservaOrigem; 
    @SuppressWarnings({"FieldMayBeFinal", "unused"})
    private JFrame telaUsuarioOrigem; 


    private HashMap<String, Double> valoresIngressoPorArea;

    public TelaCompra(String peca, String sessao, String area, List<Integer> poltronas, TelaReserva telaReserva, JFrame telaOrigem) throws SQLException {
        this.nomePeca = peca;
        this.nomeSessao = sessao;
        this.nomeArea = area;
        this.numerosPoltronas = poltronas;
        this.telaReservaOrigem = telaReserva;
        this.telaUsuarioOrigem = telaOrigem;

        inicializarValoresDosIngressosPorArea();

        janelaPrincipal = new JFrame("Teatro ABC - Finalizar Compra");
        janelaPrincipal.setSize(500, 550);
        janelaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setResizable(false);

        JPanel painelConteudo = new JPanel(null); 
        painelConteudo.setBackground(new Color(245, 245, 245));

        JLabel tituloPagina = new JLabel("Finalizar Compra");
        tituloPagina.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloPagina.setForeground(new Color(60, 63, 65));
        tituloPagina.setBounds(50, 30, 300, 40);
        painelConteudo.add(tituloPagina);

        JLabel rotuloPeca = new JLabel("Peça:");
        rotuloPeca.setBounds(50, 100, 100, 30);
        painelConteudo.add(rotuloPeca);
        JLabel valorPeca = new JLabel(nomePeca);
        valorPeca.setBounds(160, 100, 200, 30);
        painelConteudo.add(valorPeca);

        JLabel rotuloSessao = new JLabel("Sessão:");
        rotuloSessao.setBounds(50, 130, 100, 30);
        painelConteudo.add(rotuloSessao);
        JLabel valorSessao = new JLabel(nomeSessao);
        valorSessao.setBounds(160, 130, 200, 30);
        painelConteudo.add(valorSessao);

        JLabel rotuloArea = new JLabel("Área:");
        rotuloArea.setBounds(50, 160, 100, 30);
        painelConteudo.add(rotuloArea);
        JLabel valorArea = new JLabel(nomeArea);
        valorArea.setBounds(160, 160, 200, 30);
        painelConteudo.add(valorArea);

        JLabel rotuloPoltronas = new JLabel("Poltrona(s):");
        rotuloPoltronas.setBounds(50, 190, 100, 30);
        painelConteudo.add(rotuloPoltronas);

        StringBuilder poltronasFormatadas = new StringBuilder();
        for (int i = 0; i < numerosPoltronas.size(); i++) {
            poltronasFormatadas.append(numerosPoltronas.get(i));
            if (i < numerosPoltronas.size() - 1) {
                poltronasFormatadas.append(", ");
            }
        }
        JLabel valorPoltronas = new JLabel(poltronasFormatadas.toString());
        valorPoltronas.setBounds(160, 190, 300, 30);
        painelConteudo.add(valorPoltronas);

        JLabel rotuloValorTotal = new JLabel("Valor Total:");
        rotuloValorTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rotuloValorTotal.setBounds(50, 230, 120, 30);
        painelConteudo.add(rotuloValorTotal);

        double valorTotalDaCompra = calcularValorTotalDaCompra();
        JLabel valorTotalDisplay = new JLabel(String.format("R$ %.2f", valorTotalDaCompra));
        valorTotalDisplay.setFont(new Font("Segoe UI", Font.BOLD, 16));
        valorTotalDisplay.setForeground(new Color(33, 150, 243));
        valorTotalDisplay.setBounds(180, 230, 200, 30);
        painelConteudo.add(valorTotalDisplay);

        JButton botaoConfirmar = new JButton("Confirmar Compra");
        aplicarEstiloBotao(botaoConfirmar, new Color(76, 175, 80));
        botaoConfirmar.setBounds(50, 280, 180, 40);
        painelConteudo.add(botaoConfirmar);

        JButton botaoVoltar = new JButton("Voltar");
        aplicarEstiloBotao(botaoVoltar, new Color(33, 150, 243));
        botaoVoltar.setBounds(250, 280, 150, 40);
        painelConteudo.add(botaoVoltar);

        botaoConfirmar.addActionListener(e -> {
            @SuppressWarnings("unused")
            SessaoLogin sessaoLogin = new SessaoLogin();
            String login = SessaoLogin.getLogin();
            BuscarCpfController usuarioCpfDAO = new BuscarCpfController();
            String cpf = usuarioCpfDAO.buscarCpfPorLogin(login);

            ComprovanteDAO comprovanteDAO = new ComprovanteDAO();
            comprovanteDAO.salvarComprovante(
                cpf,
                nomePeca,
                nomeSessao,
                nomeArea,
                poltronasFormatadas.toString(),
                valorTotalDaCompra
            );

             Connection conexao = Conexao.getConexao();  // pega conexão do banco
            
            IdComprovantesController dao = new IdComprovantesController(conexao);
            Integer idComprovante = dao.buscarIdComprovantePorCpf(cpf);

            if (idComprovante != null) {
                PoltronaDAO daoPoltrona = new PoltronaDAO(conexao);
                daoPoltrona.salvarPoltronas(idComprovante, numerosPoltronas);
                System.out.println("Poltronas salvas com sucesso.");                
            } else {
                System.out.println("Comprovante não encontrado para o CPF: " + cpf);
            }
            

            NotificacaoUtil.mostrarAvisoTemporario(janelaPrincipal, "Compra realizada com sucesso!", new Color(0, 128, 0));

            Timer timer = new Timer(1000, e2 -> {
                janelaPrincipal.dispose(); 
            });
            timer.setRepeats(false);
            timer.start();
            
            telaUsuarioOrigem.setVisible(true);
         });
        

        botaoVoltar.addActionListener(e -> {
            janelaPrincipal.dispose(); 
            telaReservaOrigem.getJanelaPrincipal().setVisible(true);
        });

        janelaPrincipal.add(painelConteudo);
        janelaPrincipal.setVisible(true);
    }

    private void inicializarValoresDosIngressosPorArea() {
        valoresIngressoPorArea = new HashMap<>();
        valoresIngressoPorArea.put("Plateia A", 40.00);
        valoresIngressoPorArea.put("Plateia B", 60.00);
        valoresIngressoPorArea.put("Frisa", 120.00); 
        valoresIngressoPorArea.put("Camarote", 80.00);
        valoresIngressoPorArea.put("Balcão Nobre", 250.00);
    }

    private double calcularValorDoIngresso(String area) {
        String areaBase = area;
        if (area.startsWith("Frisa")) {
            areaBase = "Frisa";
        } else if (area.startsWith("Camarote")) {
            areaBase = "Camarote";
        }
        return valoresIngressoPorArea.getOrDefault(areaBase, 0.00);
    }

    private double calcularValorTotalDaCompra() {
        double total = 0.0;
        for (@SuppressWarnings("unused")
        int poltrona : numerosPoltronas) {
            total += calcularValorDoIngresso(nomeArea);
        }
        return total;
    }

    private void aplicarEstiloBotao(JButton botao, Color cor) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(150, 40));
    }
}