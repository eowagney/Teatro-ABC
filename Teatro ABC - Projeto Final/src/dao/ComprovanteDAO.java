package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import conexao.Conexao;
import entity.ComprovanteCompra;

public class ComprovanteDAO {

    public void salvarComprovante(ComprovanteCompra comprovante) {
        String sql = "INSERT INTO comprovantes_compra (cpf, peca, sessao, area, poltronas, valor) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comprovante.getCpf());
            stmt.setString(2, comprovante.getPeca());
            stmt.setString(3, comprovante.getSessao());
            stmt.setString(4, comprovante.getArea());
            stmt.setString(5, comprovante.getPoltronasComoTexto());
            stmt.setDouble(6, comprovante.getValor());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar comprovante: " + e.getMessage());
        }
    }
}

