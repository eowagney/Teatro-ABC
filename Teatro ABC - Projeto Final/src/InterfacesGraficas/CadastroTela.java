package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;

public class CadastroTela extends JFrame {

    private static final long serialVersionUID = 1L;

    public CadastroTela() {
        setTitle("Cadastro de Usuário");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titulo = new JLabel("Cadastro de Usuário");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(60, 63, 65));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 30, 10);
        painel.add(titulo, gbc);

        JLabel nomeLabel = new JLabel("Nome completo:");
        nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(nomeLabel, gbc);

        JTextField campoNome = new JTextField(20);
        campoNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoNome, gbc);

        JLabel cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(cpfLabel, gbc);

        JTextField campoCPF = new JTextField(20);
        campoCPF.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoCPF, gbc);

        JLabel telefoneLabel = new JLabel("Telefone:");
        telefoneLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(telefoneLabel, gbc);

        JTextField campoTelefone = new JTextField(20);
        campoTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoTelefone, gbc);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(enderecoLabel, gbc);

        JTextField campoEndereco = new JTextField(20);
        campoEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoEndereco, gbc);

        JLabel nascimentoLabel = new JLabel("Data de Nascimento (DD/MM/AAAA):");
        nascimentoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(nascimentoLabel, gbc);

        JTextField campoNascimento = new JTextField(20);
        campoNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoNascimento, gbc);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoCadastrar.setBackground(new Color(33, 150, 243));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFocusPainted(false);
        botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        painel.add(botaoCadastrar, gbc);

        botaoCadastrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        });

        add(painel);
        setVisible(true);
    }
}
