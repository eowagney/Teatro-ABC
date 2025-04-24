package InterfacesGraficas;

import javax.swing.*;
import java.awt.*;

public class LoginTela extends JFrame {

    private static final long serialVersionUID = 1L;

    public LoginTela() {
        setTitle("Teatro ABC");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titulo = new JLabel("Bem-vindo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(60, 63, 65));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 30, 10);
        painel.add(titulo, gbc);

        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 5, 10);
        painel.add(usuarioLabel, gbc);

        JTextField campoUsuario = new JTextField(20);
        campoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoUsuario, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(senhaLabel, gbc);

        JPasswordField campoSenha = new JPasswordField(20);
        campoSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoSenha, gbc);

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoLogin.setBackground(new Color(33, 150, 243));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFocusPainted(false);
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        painel.add(botaoLogin, gbc);

        JButton botaoCadastro = new JButton("Cadastrar");
        botaoCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botaoCadastro.setBackground(new Color(76, 175, 80));
        botaoCadastro.setForeground(Color.WHITE);
        botaoCadastro.setFocusPainted(false);
        botaoCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 20, 10);
        painel.add(botaoCadastro, gbc);

        JLabel erroLabel = new JLabel("Usuário ou senha inválidos.");
        erroLabel.setForeground(Color.RED);
        erroLabel.setVisible(false);
        gbc.gridy++;
        painel.add(erroLabel, gbc);

        botaoLogin.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (usuario.equals("admin") && senha.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            } else {
                erroLabel.setVisible(true);
            }
        });

        botaoCadastro.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Tela de cadastro será aberta aqui 4444.");
            CadastroTela telaCadastro = new CadastroTela();
        });

        add(painel);
        setVisible(true);
}
}
