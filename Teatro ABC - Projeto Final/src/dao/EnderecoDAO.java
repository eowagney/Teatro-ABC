package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import java.sql.Statement;

import conexao.Conexao;

public class EnderecoDAO {

    public int cadastrarEndereco(JLabel ruaLabel, JLabel numeroLabel, JLabel bairroLabel, JLabel cidadeLabel, JLabel estadoLabel) {
    String sql = "INSERT INTO endereco (rua, numero, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, ruaLabel.getText().trim());
        stmt.setString(2, numeroLabel.getText().trim());
        stmt.setString(3, bairroLabel.getText().trim());
        stmt.setString(4, cidadeLabel.getText().trim());
        stmt.setString(5, estadoLabel.getText().trim());

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao cadastrar endereço: " + e.getMessage());
    }

    return -1; 
}
}
