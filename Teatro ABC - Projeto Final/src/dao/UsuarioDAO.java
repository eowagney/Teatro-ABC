package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import conexao.Conexao;

public class UsuarioDAO {

    public boolean cadastrarUsuario(String nome, String cpf, String telefone, int id_endereco, String nascimento, String endereco, String login, String senha) {
        String sql = "INSERT INTO usuarios (nome, cpf, telefone, id_endereco, nascimento, endereco, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {

            SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formatoEntrada.parse(nascimento);
            java.sql.Date dataSql = new java.sql.Date(data.getTime());

            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.setString(3, telefone);
            ps.setInt(4, id_endereco);
            ps.setDate(5, dataSql);
            ps.setString(6, endereco);
            ps.setString(7, login);
            ps.setString(8, senha);

            int linhasAfetadas = ps.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Erro ao converter data de nascimento: " + e.getMessage());
        }

        return false;
    }
}
// 
