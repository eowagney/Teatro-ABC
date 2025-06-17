package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PoltronaDAO {

    @SuppressWarnings("FieldMayBeFinal")
    private Connection conexao;

    public PoltronaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void salvarPoltronas(int idComprovante, List<Integer> numerosPoltronas) {
        String sql = "INSERT INTO poltronas_comprovante (id_comprovante, numero_poltrona) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            for (int numero : numerosPoltronas) {
                stmt.setInt(1, idComprovante);
                stmt.setInt(2, numero);
                stmt.addBatch(); 
            }
            stmt.executeBatch(); 
        } catch (SQLException e) {
            System.err.println("Erro ao salvar poltronas: " + e.getMessage());
        }
    }
}
