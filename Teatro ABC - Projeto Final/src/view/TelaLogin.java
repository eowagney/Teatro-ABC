package view;

import controller.UsuarioController;
import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import objetos.NotificacaoUtil;
import objetos.SessaoLogin;

public class TelaLogin extends JFrame {

    private static final long serialVersionUID = 1L;

    public TelaLogin() {
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
        botaoLogin.setBackground(new Color(76, 175, 80));
        botaoLogin.setForeground(Color.WHITE);
        botaoLogin.setFocusPainted(false);
        botaoLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy++;
        gbc.insets = new Insets(20, 10, 10, 10);
        painel.add(botaoLogin, gbc);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        botaoCadastrar.setBackground(new Color(33, 150, 243));
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
                campoSenha.setEchoChar((char) 0); 
            } else {
                campoSenha.setEchoChar('\u2022'); 
            }
        });

        botaoLogin.addActionListener(e -> {
            String usuario = campoUsuario.getText();
            String senha = new String(campoSenha.getPassword());

            UsuarioController usuarioController = new UsuarioController();
            
            if (usuarioController.autenticar(usuario, senha)) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Logado com sucesso!", new Color(0, 128, 0));
                SessaoLogin.setLogin(usuario);
                Timer timer = new Timer(1000, e2 -> {
                    TelaUsuario telaUsuario = new TelaUsuario(usuario);
                    TelaLogin.this.dispose();
                    telaUsuario.setVisible(true);
                });
                timer.setRepeats(false); 
                timer.start();              
            }else if(usuario.equals("admin") && senha.equals("admin")) {
                NotificacaoUtil.mostrarAvisoTemporario(this, "Logado com sucesso!", new Color(0, 128, 0));
                Timer timer = new Timer(1000, e2 -> {
                TelaAdmin telaAdmin = new TelaAdmin();
               telaAdmin.setVisible(true);
                TelaLogin.this.dispose();
                });
                timer.setRepeats(false); 
                timer.start(); 
            } else {
                erroEntrar.setVisible(true);
            }
        });

        botaoCadastrar.addActionListener(e -> {
            TelaCadastro telaCadastro = null;
            try {
                telaCadastro = new TelaCadastro();
            } catch (ParseException ex) {
            }
            if (telaCadastro != null) {
                telaCadastro.setVisible(true);
                TelaLogin.this.dispose();
            }
        });

        add(painel);
        setVisible(true);
}
}
