package dao;

import conexao.Conexao;
import entity.Comprovante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprovanteDAO {

    @SuppressWarnings("unused")
    private Object listaComprovantes;

    @SuppressWarnings("UnnecessaryReturnStatement")
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

            System.out.println("Salvo com sucesso! ");

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao salvar comprovante: " + e.getMessage());
            return;
        }
    }

    public List<Comprovante> listarComprovantesPorCpf(String cpf) {
        List<Comprovante> lista = new ArrayList<>();
        String sql = "SELECT * FROM comprovantes_compra WHERE cpf = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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

        } catch (SQLException e) {
            System.out.println("Erro ao listar comprovantes: " + e.getMessage());
        }
        return lista;
        }
    }

