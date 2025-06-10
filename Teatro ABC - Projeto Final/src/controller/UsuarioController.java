package controller;

import java.sql.*;

public class UsuarioController {

    private final String url = "jdbc:mysql://localhost:3306/teatro_abc";
    private final String usuarioBD = "root";
    private final String senhaBD = "1234";

    public boolean autenticar(String usuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";

        try (Connection conn = DriverManager.getConnection(url, usuarioBD, senhaBD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); 

        } catch (SQLException e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
            return false;
        }
    }
}
