package controller;

import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaController {

    @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
    public List<Integer> buscarPoltronasReservadas(String peca, String sessao, String area) {
        List<Integer> poltronasReservadas = new ArrayList<>();
        String sql = "SELECT poltronas FROM comprovantes_compra WHERE peca = ? AND sessao = ? AND area = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, peca);
            stmt.setString(2, sessao);
            stmt.setString(3, area);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String poltronasTexto = rs.getString("poltronas");
                if (poltronasTexto != null && !poltronasTexto.isEmpty()) {
                    String[] poltronas = poltronasTexto.split(",");
                    for (String p : poltronas) {
                        try {
                            poltronasReservadas.add(Integer.parseInt(p.trim()));
                        } catch (NumberFormatException e) {
                            System.out.println("Erro ao converter poltrona: " + p);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar poltronas reservadas: " + e.getMessage());
        }

        return poltronasReservadas;
    }
}
