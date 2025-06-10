package app;

import conexao.Conexao;
import java.sql.Connection;
import view.TelaLogin;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = Conexao.getConexao();
            if (conn != null) {
                System.out.println("Conexão realizada com sucesso!");

                TelaLogin telaInicial = new TelaLogin();
                telaInicial.setVisible(true);

            } else {
                System.out.println("Erro na conexão com o banco de dados.");
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao iniciar a aplicação:");
        }

    }
}
