package view;

import controller.AutenticarCpfController;
import dao.EnderecoDAO;
import dao.UsuarioDAO;
import entity.Usuario;
import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import objetos.NotificacaoUtil;
import validadores.ValidarCpf;
import validadores.ValidarEndereco;
import validadores.ValidarNascimento;
import validadores.ValidarNome;
import validadores.ValidarSenha;
import validadores.ValidarTelefone;
import validadores.ValidarUsuario;

public class TelaCadastro extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaCadastro() throws ParseException {
        setTitle("Cadastro de Usuário");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(245, 245, 245));

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(245, 245, 245));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));

        JLabel titulo = new JLabel("Cadastro");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel painelNome = criarPainelCampo("Nome Completo: ");
        JTextField campoNome = new JTextField();
        configurarCampoTexto(campoNome);
        painelNome.add(campoNome);
        painel.add(painelNome);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelCPF = criarPainelCampo("CPF: ");
        MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
        cpfMask.setPlaceholderCharacter('_');
        JFormattedTextField campoCPF = new JFormattedTextField(cpfMask);
        configurarCampoTexto(campoCPF);
        campoCPF.setToolTipText("Digite seu CPF no formato: 000.000.000-00.");
        painelCPF.add(campoCPF);
        painel.add(painelCPF);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelTelefone = criarPainelCampo("Telefone: ");
        MaskFormatter telefoneMask = new MaskFormatter("(##)#####-####");
        telefoneMask.setPlaceholderCharacter('_');
        JFormattedTextField campoTelefone = new JFormattedTextField(telefoneMask);
        configurarCampoTexto(campoTelefone);
        campoTelefone.setToolTipText("Digite seu telefone no formato: (00)00000-0000.");
        painelTelefone.add(campoTelefone);
        painel.add(painelTelefone);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelEndereco = criarPainelCampo("Endereço: ");

        JPanel painelEnderecoCampos = new JPanel();
        painelEnderecoCampos.setLayout(new GridLayout(2, 3, 10, 5));

        JTextField campoRua = new JTextField();
        configurarCampoTexto(campoRua);
        campoRua.setToolTipText("Digite o nome da rua");

        JTextField campoNumero = new JTextField();
        configurarCampoTexto(campoNumero);
        campoNumero.setToolTipText("Digite o número");

        JTextField campoBairro = new JTextField();
        configurarCampoTexto(campoBairro);
        campoBairro.setToolTipText("Digite o bairro");

        JTextField campoCidade = new JTextField();
        configurarCampoTexto(campoCidade);
        campoCidade.setToolTipText("Digite a cidade");

        JTextField campoEstado = new JTextField();
        configurarCampoTexto(campoEstado);
        campoEstado.setToolTipText("Digite a sigla do estado (ex: SP, RJ)");

        painelEnderecoCampos.add(new JLabel("Rua:"));
        painelEnderecoCampos.add(new JLabel("Número:"));
        painelEnderecoCampos.add(new JLabel("Bairro:"));
        painelEnderecoCampos.add(new JLabel("Cidade:"));
        painelEnderecoCampos.add(new JLabel("Estado:"));
        painelEnderecoCampos.add(new JLabel(""));

        painelEnderecoCampos.add(campoRua);
        painelEnderecoCampos.add(campoNumero);
        painelEnderecoCampos.add(campoBairro);
        painelEnderecoCampos.add(campoCidade);
        painelEnderecoCampos.add(campoEstado);

        painelEndereco.add(painelEnderecoCampos);

        painel.add(painelEndereco);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        String enderecoCompleto = campoRua.getText().trim() + ", " +
                          campoNumero.getText().trim() + " - " +
                          campoBairro.getText().trim() + " - " +
                          campoCidade.getText().trim() + " - " +
                          campoEstado.getText().trim();

        JPanel painelNascimento = criarPainelCampo("Data de Nascimento: ");
        MaskFormatter nascimentoMask = new MaskFormatter("##/##/####");
        nascimentoMask.setPlaceholderCharacter('_');
        JFormattedTextField campoNascimento = new JFormattedTextField(nascimentoMask);
        campoNascimento.setToolTipText("Digite sua data de nascimento no formato: dd/mm/aaaa.");
        configurarCampoTexto(campoNascimento);
        painelNascimento.add(campoNascimento);
        painel.add(painelNascimento);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelUsuario = criarPainelCampo("Usuário: ");
        JTextField campoUsuario = new JTextField();
        configurarCampoTexto(campoUsuario);
        painelUsuario.add(campoUsuario);
        painel.add(painelUsuario);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel painelSenha = criarPainelCampo("Senha: ");
        JPasswordField campoSenha = new JPasswordField();
        configurarCampoTexto(campoSenha);
        painelSenha.add(campoSenha);
        painel.add(painelSenha);

        JCheckBox mostrarSenha = new JCheckBox("Mostrar senha");
        mostrarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mostrarSenha.setBackground(new Color(245, 245, 245));
        mostrarSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
        mostrarSenha.setFocusPainted(false);
        painel.add(mostrarSenha);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));

        mostrarSenha.addActionListener(e -> {
            if (mostrarSenha.isSelected()) {
                campoSenha.setEchoChar((char) 0);
            } else {
                campoSenha.setEchoChar('•');
            }
        });

        JButton botaoCadastrar = new JButton("Cadastrar");
        configurarBotao(botaoCadastrar, new Color(76, 175, 80));

        JButton botaoVoltar = new JButton("Voltar");
        configurarBotao(botaoVoltar, new Color(33, 150, 243));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(245, 245, 245));
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        painelBotoes.add(botaoCadastrar);
        painelBotoes.add(botaoVoltar);

        painel.add(painelBotoes);

        botaoCadastrar.addActionListener(e -> {
            ValidarNome vNome = new ValidarNome();
            ValidarCpf vCpf = new ValidarCpf();
            ValidarTelefone vTelefone = new ValidarTelefone();
            ValidarEndereco vEndereco = new ValidarEndereco();
            ValidarNascimento vNascimento = new ValidarNascimento();
            ValidarUsuario vUsuario = new ValidarUsuario();
            char[] senha = campoSenha.getPassword();
            String senhaStr = new String(senha);
            ValidarSenha vsSenha = new ValidarSenha();
            AutenticarCpfController autenticarCpf = new AutenticarCpfController();

            if (!vNome.validar(campoNome.getText())) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Nome inválido. Por favor, corrija.", new Color(211, 47, 47));
                campoNome.setText("");
                campoNome.requestFocus();
            } else if (!vCpf.validar(campoCPF.getText())) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "CPF inválido. Por favor, corrija.", new Color(211, 47, 47));
                campoCPF.setText("");
                campoCPF.requestFocus();
            } else if (autenticarCpf.cpfExiste(campoCPF.getText())) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "CPF já cadastrado. Por favor, utilize outro.", new Color(211, 47, 47));
                campoCPF.setText("");
                campoCPF.requestFocus();
                
            }else if (!vTelefone.validar(campoTelefone.getText())) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Telefone inválido. Por favor, corrija.", new Color(211, 47, 47));
                campoTelefone.setText("");
                campoTelefone.requestFocus();
            } else if (!vEndereco.validar(enderecoCompleto)) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Endereço inválido. Por favor, corrija.", new Color(211, 47, 47));
                campoRua.setText("");
                campoNumero.setText("");    
                campoBairro.setText("");
                campoCidade.setText("");
                campoEstado.setText("");
            } else if (!vNascimento.validar(campoNascimento.getText())) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Data de nascimento inválida. Por favor, corrija.", new Color(211, 47, 47));
                campoNascimento.setText("");
                campoNascimento.requestFocus();
            } else if (!vUsuario.validar(campoUsuario.getText())) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Usuário inválido. Por favor, corrija.", new Color(211, 47, 47));
                campoUsuario.setText("");
                campoUsuario.requestFocus();
            } else if (!vsSenha.validar(senhaStr)) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Senha inválida. Por favor, corrija.", new Color(211, 47, 47));
                campoSenha.setText("");
                campoSenha.requestFocus();
            } else {
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                int idEndereco = enderecoDAO.salvarEndereco(
                    new JLabel(campoRua.getText().trim()),
                    new JLabel(campoNumero.getText().trim()),
                    new JLabel(campoBairro.getText().trim()),
                    new JLabel(campoCidade.getText().trim()),
                    new JLabel(campoEstado.getText().trim())
                );

                String enderecoAtualizado = campoRua.getText().trim() + ", " +
                        campoNumero.getText().trim() + " - " +
                        campoBairro.getText().trim() + " - " +
                        campoCidade.getText().trim() + " - " +
                        campoEstado.getText().trim();

                Usuario u = new Usuario();
                u.setNome(campoNome.getText());
                u.setCpf(campoCPF.getText());
                u.setTelefone(campoTelefone.getText());
                u.setId_endereco(idEndereco);
                u.setNascimento(campoNascimento.getText());
                u.setEndereco(enderecoAtualizado);
                u.setLogin(campoUsuario.getText());
                u.setSenha(senhaStr);

                boolean sucesso = new UsuarioDAO().salvarUsuario(
                    u.getNome(),
                    u.getCpf(),
                    u.getTelefone(),
                    u.getId_endereco(),
                    u.getNascimento(),
                    u.getEndereco(),
                    u.getLogin(),
                    u.getSenha()
                );

                if (sucesso) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Usuário cadastrado com sucesso!", new Color(0, 128, 0));
                Timer timer = new Timer(1000, e2 -> {
                    this.dispose();
                    new TelaLogin().setVisible(true);
                });
                timer.setRepeats(false); 
                timer.start();
                }       else {
                         NotificacaoUtil.mostrarAvisoTemporario(this, "Falha no cadastro", new Color(211, 47, 47));
                            }
                        }
                    });

        botaoVoltar.addActionListener(e -> {
            TelaLogin loginTela = new TelaLogin();
            loginTela.setVisible(true);
            this.dispose();
        });

        JPanel westFiller = new JPanel();
        JPanel eastFiller = new JPanel();
        westFiller.setBackground(new Color(245, 245, 245));
        eastFiller.setBackground(new Color(245, 245, 245));

        painelPrincipal.add(westFiller, BorderLayout.WEST);
        painelPrincipal.add(painel, BorderLayout.CENTER);
        painelPrincipal.add(eastFiller, BorderLayout.EAST);

        add(painelPrincipal);
        setVisible(true);
    }

    private JPanel criarPainelCampo(String labelText) {
        JPanel painelCampo = new JPanel();
        painelCampo.setLayout(new BoxLayout(painelCampo, BoxLayout.Y_AXIS));
        painelCampo.setBackground(new Color(245, 245, 245));
        painelCampo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCampo.add(label);
        painelCampo.add(Box.createRigidArea(new Dimension(0, 5)));

        return painelCampo;
    }

    private void configurarCampoTexto(JComponent campo) {
        campo.setMaximumSize(new Dimension(350, 30));
        campo.setPreferredSize(new Dimension(350, 30));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void configurarBotao(JButton botao, Color cor) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setPreferredSize(new Dimension(120, 35));
    }
}
