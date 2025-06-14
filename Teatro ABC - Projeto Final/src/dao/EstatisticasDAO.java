package dao;

import conexao.Conexao;
import java.sql.*;
import java.util.*;

public class EstatisticasDAO {

    public Map<String, Integer> ingressosPorPeca() {
        Map<String, Integer> mapa = new HashMap<>();
        String sql = "SELECT peca, SUM(quantidade_poltronas) AS totalIngressos FROM comprovantes_compra GROUP BY peca";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

                String poltronasStr = rs.getString("poltronas"); // ex: "A1,A2,A3"
                @SuppressWarnings("unused")
                int quantidade = poltronasStr == null || poltronasStr.isEmpty() ? 0 : poltronasStr.split(",").length;
                
            while (rs.next()) {
                mapa.put(rs.getString("peca"), rs.getInt("totalIngressos"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ingressos por peça: " + e.getMessage());
        }
        return mapa;
    }

    public Map<String, Integer> ocupacaoPorSessao() {
        Map<String, Integer> mapa = new HashMap<>();
        String sql = "SELECT sessao, SUM(quantidade_poltronas) AS totalPoltronas FROM comprovantes_compra GROUP BY sessao";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

                String poltronasStr = rs.getString("poltronas"); // ex: "A1,A2,A3"
                @SuppressWarnings("unused")
                int quantidade = poltronasStr == null || poltronasStr.isEmpty() ? 0 : poltronasStr.split(",").length;

            while (rs.next()) {
                mapa.put(rs.getString("sessao"), rs.getInt("totalPoltronas"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar ocupação por sessão: " + e.getMessage());
        }
        return mapa;
    }

    public Map<String, Double> lucroPorPecaSessao() {
        Map<String, Double> mapa = new HashMap<>();
        String sql = "SELECT CONCAT(peca, ' / ', sessao) AS chave, SUM(valor) AS totalValor FROM comprovantes_compra GROUP BY peca, sessao";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

                String poltronasStr = rs.getString("poltronas"); // ex: "A1,A2,A3"
                @SuppressWarnings("unused")
                int quantidade = poltronasStr == null || poltronasStr.isEmpty() ? 0 : poltronasStr.split(",").length;

            while (rs.next()) {
                mapa.put(rs.getString("chave"), rs.getDouble("totalValor"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar lucro por peça/sessão: " + e.getMessage());
        }
        return mapa;
    }

    public Map<String, Double> faturamentoMedioPorPeca() {
        Map<String, Double> mapa = new HashMap<>();
        String sql = "SELECT peca, AVG(valor) AS mediaValor FROM comprovantes_compra GROUP BY peca";
        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
                String poltronasStr = rs.getString("poltronas"); // ex: "A1,A2,A3"
                @SuppressWarnings("unused")
                int quantidade = poltronasStr == null || poltronasStr.isEmpty() ? 0 : poltronasStr.split(",").length;


            while (rs.next()) {
                mapa.put(rs.getString("peca"), rs.getDouble("mediaValor"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar faturamento médio por peça: " + e.getMessage());
        }
        return mapa;
    }
}
