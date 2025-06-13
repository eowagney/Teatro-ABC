package dao;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ComprovanteDAO {

    public void salvarComprovante(String cpf, String peca, String sessao, String area, String poltronas, double valor) {
        String sql = "INSERT INTO comprovantes_compra (cpf, peca, sessao, area, poltronas, valor) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, peca);
            stmt.setString(3, sessao);
            stmt.setString(4, area);
            stmt.setString(5, poltronas);
            stmt.setDouble(6, valor);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar comprovante: " + e.getMessage());
        }
    }
}
