package InterfacesGraficas;

import java.awt.*;
import javax.swing.*;

public class CadastroTela extends JFrame {

    private static final long serialVersionUID = 1L;

    public CadastroTela() {
        setTitle("Cadastro de Usuário");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Criar painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // Criar painel central com tamanho máximo limitado
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(245, 245, 245));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE)); // Limitar largura máxima
        
        JLabel titulo = new JLabel("Cadastro");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(10, 10)));

        JTextField campoNome = criarCampo("Nome completo:", painel);
        JTextField campoCPF = criarCampo("CPF:", painel);
        JTextField campoTelefone = criarCampo("Telefone:", painel);
        JTextField campoEndereco = criarCampo("Endereço:", painel);
        JTextField campoNascimento = criarCampo("Data de Nascimento:", painel);
        JTextField campoUsuario = criarCampo("Usuário:", painel);
        JPasswordField campoSenha = criarSenha("Senha:", painel);

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
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!\n\n");
        });

        botaoVoltar.addActionListener(e -> {
            LoginTela loginTela = new LoginTela();
            loginTela.setVisible(true);
            this.dispose();
        });

        // Criar painéis para centralização
        JPanel westFiller = new JPanel();
        JPanel eastFiller = new JPanel();
        westFiller.setBackground(new Color(245, 245, 245));
        eastFiller.setBackground(new Color(245, 245, 245));
        
        // Adicionar os componentes ao painel principal
        mainPanel.add(westFiller, BorderLayout.WEST);
        mainPanel.add(painel, BorderLayout.CENTER);
        mainPanel.add(eastFiller, BorderLayout.EAST);

        add(mainPanel);
        setVisible(true);
    }

    private JTextField criarCampo(String labelText, JPanel painel) {
        JPanel subPainel = new JPanel();
        subPainel.setLayout(new BoxLayout(subPainel, BoxLayout.Y_AXIS));
        subPainel.setBackground(new Color(245, 245, 245));
        subPainel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(350, 30));
        campo.setPreferredSize(new Dimension(350, 30));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        subPainel.add(label);
        subPainel.add(Box.createRigidArea(new Dimension(0, 10)));
        subPainel.add(campo);
        subPainel.add(Box.createRigidArea(new Dimension(0, 10)));

        painel.add(subPainel);

        return campo;
    }

    private JPasswordField criarSenha(String labelText, JPanel painel) {
        JPanel subPainel = new JPanel();
        subPainel.setLayout(new BoxLayout(subPainel, BoxLayout.Y_AXIS));
        subPainel.setBackground(new Color(245, 245, 245));
        subPainel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField campo = new JPasswordField();
        campo.setMaximumSize(new Dimension(350, 30));
        campo.setPreferredSize(new Dimension(350, 30));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        subPainel.add(label);
        subPainel.add(Box.createRigidArea(new Dimension(0, 5)));
        subPainel.add(campo);
        subPainel.add(Box.createRigidArea(new Dimension(0, 15)));

        painel.add(subPainel);

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
