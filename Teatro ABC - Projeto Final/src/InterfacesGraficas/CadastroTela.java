package InterfacesGraficas;

import Validadores.ValidarCpf;
import Validadores.ValidarEndereco;
import Validadores.ValidarNascimento;
import Validadores.ValidarNome;
import Validadores.ValidarSenha;
import Validadores.ValidarTelefone;
import Validadores.ValidarUsuario;
import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class CadastroTela extends JFrame {

    private static final long serialVersionUID = 1L;

    public CadastroTela() throws ParseException {
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
        JTextField campoEndereco = new JTextField();
        configurarCampoTexto(campoEndereco);
        painelEndereco.add(campoEndereco);
        painel.add(painelEndereco);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

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
        configurarBotao(botaoCadastrar, new Color(33, 150, 243));

        JButton botaoVoltar = new JButton("Voltar");
        configurarBotao(botaoVoltar, new Color(76, 175, 80));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(245, 245, 245));
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
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

            if (!vNome.validar(campoNome.getText())) {
                JOptionPane.showMessageDialog(this, "Nome inválido. Por favor, corrija.");
                campoNome.setText("");
                campoNome.requestFocus();
            } else if (!vCpf.validar(campoCPF.getText())) {
                JOptionPane.showMessageDialog(this, "CPF inválido. Por favor, corrija.");
                campoCPF.setText("");
                campoCPF.requestFocus();
            } else if (!vTelefone.validar(campoTelefone.getText())) {
                JOptionPane.showMessageDialog(this, "Telefone inválido. Por favor, corrija.");
                campoTelefone.setText("");
                campoTelefone.requestFocus();
            } else if (!vEndereco.validar(campoEndereco.getText())) {
                JOptionPane.showMessageDialog(this, "Endereço inválido. Por favor, corrija.");
                campoEndereco.setText("");
                campoEndereco.requestFocus();
            } else if (!vNascimento.validar(campoNascimento.getText())) {
                JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Por favor, corrija.");
                campoNascimento.setText("");
                campoNascimento.requestFocus();
            } else if (!vUsuario.validar(campoUsuario.getText())) {
                JOptionPane.showMessageDialog(this, "Usuário inválido. Por favor, corrija.");
                campoUsuario.setText("");
                campoUsuario.requestFocus();
            } else if (!vsSenha.validar(senhaStr)) {
                JOptionPane.showMessageDialog(this, "Senha inválida. Por favor, corrija.");
                campoSenha.setText("");
                campoSenha.requestFocus();
            } else {
                // Todos os campos são válidos.
                // Aqui você pode adicionar o código para salvar o usuário no banco de dados.
            }
        });

        botaoVoltar.addActionListener(e -> {
            LoginTela loginTela = new LoginTela();
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
 