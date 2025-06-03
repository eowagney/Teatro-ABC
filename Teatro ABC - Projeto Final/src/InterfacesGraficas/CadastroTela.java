package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;

public class CadastroTela extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoEndereco;
    private JTextField campoNascimento;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public CadastroTela() {
        setTitle("Cadastro de Usuário");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setBackground(new Color(245, 245, 245));
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel("Cadastro");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));

        campoNome = criarCampo("Nome completo:", painel);
        campoCPF = criarCampo("CPF:", painel);
        campoTelefone = criarCampo("Telefone:", painel);
        campoEndereco = criarCampo("Endereço:", painel);
        campoNascimento = criarCampo("Data de Nascimento:", painel);
        campoUsuario = criarCampo("Usuário:", painel);
        campoSenha = criarSenha("Senha:", painel);

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
            String dados = "Nome: " + campoNome.getText() + "\n"
                    + "CPF: " + campoCPF.getText() + "\n"
                    + "Telefone: " + campoTelefone.getText() + "\n"
                    + "Endereço: " + campoEndereco.getText() + "\n"
                    + "Nascimento: " + campoNascimento.getText() + "\n"
                    + "Usuário: " + campoUsuario.getText();
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!\n\n" + dados);
        });

        botaoVoltar.addActionListener(e -> {
            LoginTela loginTela = new LoginTela();
            loginTela.setVisible(true);
            this.dispose();
        });

        add(painel);
        setVisible(true);
    }

    private JTextField criarCampo(String labelText, JPanel painel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campo = new JTextField(20);
        campo.setSize(new Dimension(10, 10));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(label);
        painel.add(campo);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        return campo;
    }

    private JPasswordField criarSenha(String labelText, JPanel painel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField campo = new JPasswordField(20);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(label);
        painel.add(campo);
        painel.add(Box.createRigidArea(new Dimension(0, 10)));

        return campo;
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
