package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    @SuppressWarnings("unused")
    private static final String URL = null;
    @SuppressWarnings("unused")
    private static final String USUARIO = null;
    @SuppressWarnings("unused")
    private static String SENHA;

    public static Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/teatro_abc";
            String usuario = "root";
            String senha = "1234";

            conn = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados.");
        }
        return conn;
    }

   public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            throw new RuntimeException("Erro ao conectar com o banco de dados.", e);
        }
    }

}
