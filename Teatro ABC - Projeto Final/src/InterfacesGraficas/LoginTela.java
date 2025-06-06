package InterfacesGraficas;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;

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

        JLabel tituloUsuario = new JLabel("Usuário:");
        tituloUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 5, 10);
        painel.add(tituloUsuario, gbc);

        JTextField campoUsuario = new JTextField(20);
        campoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoUsuario, gbc);

        JLabel tituloSenha = new JLabel("Senha:");
        tituloSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(tituloSenha, gbc);

        JPasswordField campoSenha = new JPasswordField(20);
        campoSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridy++;
        painel.add(campoSenha, gbc);

        JCheckBox mostrarSenha = new JCheckBox("Mostrar senha");
        mostrarSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mostrarSenha.setBackground(new Color(245, 245, 245));
        gbc.gridy++;
        painel.add(mostrarSenha, gbc);
        mostrarSenha.setFocusPainted(false);

        JButton botaoLogin = new JButton("Entrar");
        botaoLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoLogin.setBackground(new Color(33, 150, 243));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFocusPainted(false);
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        painel.add(botaoLogin, gbc);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botaoCadastrar.setBackground(new Color(76, 175, 80));
        botaoCadastrar.setForeground(Color.WHITE);
        botaoCadastrar.setFocusPainted(false);
        botaoCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(5, 10, 20, 10);
        painel.add(botaoCadastrar, gbc);

        JLabel erroEntrar = new JLabel("Usuário ou senha inválidos.");
        erroEntrar.setForeground(Color.RED);
        erroEntrar.setVisible(false);
        gbc.gridy++;
        painel.add(erroEntrar, gbc);

        mostrarSenha.addActionListener(e -> {
            if (mostrarSenha.isSelected()) {
                campoSenha.setEchoChar((char) 0); // Mostrar a senha
            } else {
                campoSenha.setEchoChar('\u2022'); // Ocultar a senha (padrão: bullet •)
            }
        });

        botaoLogin.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            if (usuario.equals("admin") && senha.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            } else {
                erroEntrar.setVisible(true);
            }
        });

        botaoCadastrar.addActionListener(e -> {
            CadastroTela telaCadastro = null;
            try {
                telaCadastro = new CadastroTela();
            } catch (ParseException ex) {
            }
            if (telaCadastro != null) {
                telaCadastro.setVisible(true);
                LoginTela.this.dispose();
            }
        });

        add(painel);
        setVisible(true);
}
}
