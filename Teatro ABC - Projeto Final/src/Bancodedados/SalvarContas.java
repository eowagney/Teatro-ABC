package bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalvarContas {

    public void salvar(String nome, String cpf, String endereco, String nascimento, String telefone, String usuario, String senha) {
        try {
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/teatro_abc", "root", "1234");
            Statement stmt = conexao.createStatement();

            String sql = "INSERT INTO contas (nome, cpf, endereco, nascimento, telefone, usuario, senha) " +
                         "VALUES ('" + nome + "', '" + cpf + "', '" + endereco + "', '" + nascimento + "', '" + telefone + "', '" + usuario + "', '" + senha + "')";

            stmt.executeUpdate(sql);
            System.out.println("Conta salva com sucesso!");

            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar conta.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            // Não precisa mais do Class.forName com JDBC 4+
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/teatro_abc", "root", "1234");
            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contas");

            while (rs.next()) {
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Endereço: " + rs.getString("endereco"));
                System.out.println("Nascimento: " + rs.getString("nascimento"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Usuário: " + rs.getString("usuario"));
                System.out.println("Senha: " + rs.getString("senha"));
                System.out.println("---------");
            }

            rs.close();
            stmt.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Falha na conexão! Verifique o console.");
            ex.printStackTrace();
        }
    }
}
