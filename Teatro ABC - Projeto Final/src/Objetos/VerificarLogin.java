package Objetos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerificarLogin extends LeituraCPF{

	@Override
	public boolean lerArquivo(String cpf1, String nascimento1, String arquivoContas) {
		 try (FileReader fr = new FileReader(arquivoContas)) {
             BufferedReader br = new BufferedReader(fr);
             String linha;
             while ((linha = br.readLine()) != null){
                 String[] dados = linha.split(" - ");
                 if (dados.length == 5) {
                     String cpf = dados[1];
                     String nascimento = dados[4];

                     if(cpf.equals(cpf1) && nascimento.equals(nascimento1)){
                         return true;
                     }
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
         return false;   
     }
	}

	
	

