package controller;

import conexao.Conexao;
import entity.Comprovante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprovantesController {
    private Connection conn;

    public void ComprovanteDAO() throws SQLException {
        conn = Conexao.getConexao();
    }

    public List<Comprovante> listarComprovantesPorCpf(String cpf) {
        List<Comprovante> lista = new ArrayList<>();
        String sql = "SELECT id FROM comprovantes_compra WHERE cpf = ? ORDER BY id DESC LIMIT 1";


        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Comprovante c = new Comprovante();
                c.setId(rs.getInt("id"));
                c.setCpf(rs.getString("cpf"));
                c.setPeca(rs.getString("peca"));
                c.setSessao(rs.getString("sessao"));
                c.setArea(rs.getString("area"));
                c.setPoltronas(rs.getString("poltronas"));
                c.setValor(rs.getDouble("valor"));
                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar comprovantes: " + e.getMessage());
        }

        return lista;
    }
}
