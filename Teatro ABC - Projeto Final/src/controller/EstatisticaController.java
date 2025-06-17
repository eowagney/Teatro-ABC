package controller;

import conexao.Conexao;
import java.sql.*;
import java.util.*;

public class EstatisticaController {

    private Connection conexao;

    public EstatisticaController() {
        try {
            this.conexao = Conexao.getConexao();
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public String gerarEstatisticas() throws SQLException {
        StringBuilder resultado = new StringBuilder();

        Map<String, Integer> ingressosPorPeca = new HashMap<>();
        Map<String, Integer> ocupacaoPorSessao = new HashMap<>();
        Map<String, Double> lucroPorPecaSessao = new HashMap<>();
        Map<String, Double> mediaPorPeca = new HashMap<>();

        // INGRESSOS POR PEÇA
        String sqlIngressos = "SELECT peca, COUNT(*) AS total_ingressos " +
                "FROM poltronas_comprovante pc " +
                "JOIN comprovantes_compra cc ON pc.id_comprovante = cc.id " +
                "GROUP BY peca";

        try (PreparedStatement stmt = conexao.prepareStatement(sqlIngressos);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ingressosPorPeca.put(rs.getString("peca"), rs.getInt("total_ingressos"));
            }
        }

        // OCUPAÇÃO POR SESSÃO
        String sqlSessao = "SELECT sessao, COUNT(*) AS total_ocupacao " +
                "FROM poltronas_comprovante pc " +
                "JOIN comprovantes_compra cc ON pc.id_comprovante = cc.id " +
                "GROUP BY sessao";

        try (PreparedStatement stmt = conexao.prepareStatement(sqlSessao);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ocupacaoPorSessao.put(rs.getString("sessao"), rs.getInt("total_ocupacao"));
            }
        }

        // LUCRO POR PEÇA/SESSÃO
        String sqlLucro = "SELECT peca, sessao, SUM(valor) AS lucro_total " +
                "FROM comprovantes_compra " +
                "GROUP BY peca, sessao";

        try (PreparedStatement stmt = conexao.prepareStatement(sqlLucro);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String chave = rs.getString("peca") + " - " + rs.getString("sessao");
                lucroPorPecaSessao.put(chave, rs.getDouble("lucro_total"));
            }
        }

        // MÉDIA DE FATURAMENTO POR PEÇA
        String sqlMedia = "SELECT peca, AVG(valor) AS media_faturamento " +
                "FROM comprovantes_compra " +
                "GROUP BY peca";

        try (PreparedStatement stmt = conexao.prepareStatement(sqlMedia);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                mediaPorPeca.put(rs.getString("peca"), rs.getDouble("media_faturamento"));
            }
        }

        // PEÇA MAIS/MENOS VENDIDA
        String pecaMaisVendida = null, pecaMenosVendida = null;
        int maxIngressos = Integer.MIN_VALUE, minIngressos = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : ingressosPorPeca.entrySet()) {
            int total = entry.getValue();
            if (total > maxIngressos) {
                maxIngressos = total;
                pecaMaisVendida = entry.getKey();
            }
            if (total < minIngressos) {
                minIngressos = total;
                pecaMenosVendida = entry.getKey();
            }
        }

        // SESSÃO MAIS/MENOS OCUPADA
        String sessaoMaisOcupada = null, sessaoMenosOcupada = null;
        int maxOcupacao = Integer.MIN_VALUE, minOcupacao = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : ocupacaoPorSessao.entrySet()) {
            int total = entry.getValue();
            if (total > maxOcupacao) {
                maxOcupacao = total;
                sessaoMaisOcupada = entry.getKey();
            }
            if (total < minOcupacao) {
                minOcupacao = total;
                sessaoMenosOcupada = entry.getKey();
            }
        }

        // MAIS/MENOS LUCRATIVA
        String maisLucrativa = null, menosLucrativa = null;
        double maxLucro = Double.MIN_VALUE, minLucro = Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry : lucroPorPecaSessao.entrySet()) {
            double lucro = entry.getValue();
            if (lucro > maxLucro) {
                maxLucro = lucro;
                maisLucrativa = entry.getKey();
            }
            if (lucro < minLucro) {
                minLucro = lucro;
                menosLucrativa = entry.getKey();
            }
        }

        // RESULTADO
        resultado.append("🎭 Ingressos vendidos por peça:\n");
        ingressosPorPeca.forEach((peca, total) ->
                resultado.append("  ").append(peca).append(": ").append(total).append(" ingressos\n"));

        resultado.append("\n📌 Peça mais vendida: ").append(pecaMaisVendida)
                .append(" (").append(maxIngressos).append(" ingressos)\n");
        resultado.append("📌 Peça menos vendida: ").append(pecaMenosVendida)
                .append(" (").append(minIngressos).append(" ingressos)\n");

        resultado.append("\n🕒 Ocupação por sessão:\n");
        ocupacaoPorSessao.forEach((sessao, total) ->
                resultado.append("  ").append(sessao).append(": ").append(total).append(" poltronas\n"));

        resultado.append("\n📌 Sessão mais ocupada: ").append(sessaoMaisOcupada)
                .append(" (").append(maxOcupacao).append(" poltronas)\n");
        resultado.append("📌 Sessão menos ocupada: ").append(sessaoMenosOcupada)
                .append(" (").append(minOcupacao).append(" poltronas)\n");

        resultado.append("\n💰 Lucro por peça/sessão:\n");
        lucroPorPecaSessao.forEach((chave, lucro) ->
                resultado.append("  ").append(chave).append(": R$ ").append(String.format("%.2f", lucro)).append("\n"));

        resultado.append("\n📌 Mais lucrativa: ").append(maisLucrativa)
                .append(" (R$ ").append(String.format("%.2f", maxLucro)).append(")\n");
        resultado.append("📌 Menos lucrativa: ").append(menosLucrativa)
                .append(" (R$ ").append(String.format("%.2f", minLucro)).append(")\n");

        resultado.append("\n📊 Faturamento médio por peça:\n");
        mediaPorPeca.forEach((peca, media) ->
                resultado.append("  ").append(peca).append(": R$ ").append(String.format("%.2f", media)).append("\n"));

        return resultado.toString();
    }
}
