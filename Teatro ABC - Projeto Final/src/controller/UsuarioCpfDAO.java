package controller;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class UsuarioCpfDAO {

  
public String buscarCpfPorLogin(String login) {
    String cpf = null;
    String sql = "SELECT cpf FROM usuarios WHERE login = ?";

    try (Connection conn = Conexao.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, login);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            cpf = rs.getString("cpf");
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar CPF: " + e.getMessage());
    }

    return cpf;
}
}