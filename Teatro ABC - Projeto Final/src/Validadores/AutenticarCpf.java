package validadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexao.Conexao;

public class AutenticarCpf {
    public boolean cpfExiste(String cpf) {
    String sql = "SELECT COUNT(*) FROM usuarios WHERE cpf = ?";
    try (Connection conn = Conexao.getConexao();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Erro ao verificar CPF: " + e.getMessage());
    }
    return false;
}

}
