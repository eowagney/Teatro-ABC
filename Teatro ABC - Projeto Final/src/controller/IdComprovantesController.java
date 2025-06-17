package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdComprovantesController {
    @SuppressWarnings("FieldMayBeFinal")
    private Connection conexao;

    public IdComprovantesController(Connection conexao) {
        this.conexao = conexao;
    }

    public Integer buscarIdComprovantePorCpf(String cpf) {
        String sql = "SELECT id FROM comprovantes_compra WHERE cpf = ? ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ID do comprovante: " + e.getMessage());
        }
        return null;
    }
}
