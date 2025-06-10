package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsuarioDAO {

   public boolean  cadastrarUsuario(String nome, String cpf, String telefone, String endereco, String nascimento, String login, String senha) {
      String sql = "INSERT INTO usuarios (nome, cpf, telefone, endereco, nascimento, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";

      try {
      PreparedStatement ps = null;

      String dataStr = nascimento;

                SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
                Date data = null;
                try {
                    data = formatoEntrada.parse(dataStr);
                } catch (ParseException e1) {
                    System.err.println("Erro ao converter a data: " + e1.getMessage());
                }

                java.sql.Date dataSql = new java.sql.Date(data.getTime());
      
         ps = Conexao.getConexao().prepareStatement(sql);
         ps.setString(1, nome);
         ps.setString(2, cpf);      
         ps.setString(3, telefone);
         ps.setString(4, endereco);
         ps.setDate(5, dataSql);
         ps.setString(6, login);
         ps.setString(7, senha);

         ps.execute();
         ps.close();
         System.out.println("Usuário cadastrado com sucesso!");

      } catch (SQLException e) {
         System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
      }
      return false;
        
    }

   }
