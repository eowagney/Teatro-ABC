package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReservaDAO {
    private Connection conn;

    public ReservaDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/teatro_abc", "root", "1234");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public List<Integer> buscarPoltronasReservadas(String peca, String sessao, String area) {
        List<Integer> poltronasReservadas = new ArrayList<>();
        String sql = "SELECT poltrona FROM comprovantes WHERE peca = ? AND sessao = ? AND area = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, peca);
            stmt.setString(2, sessao);
            stmt.setString(3, area);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String[] poltronas = rs.getString("poltrona").split(",");
                for (String p : poltronas) {
                    poltronasReservadas.add(Integer.valueOf(p.trim()));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar poltronas reservadas: " + e.getMessage());
        }

        return poltronasReservadas;
    }
}

