package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
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

}
