package Objetos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VerificarCPF{

	public boolean verificar(String cpf1,String arquivos) {
	    try (BufferedReader br = new BufferedReader(new FileReader(arquivos))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            String[] dados = linha.split(" - ");
	            if (dados.length == 5) {
	                String cpf = dados[1].trim();
	                if (cpf.equals(cpf1)) {
	                    return true;
	                }
	            }
	        }
	    } catch (IOException e) {
	        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
	    }
	    return false;
	}


}
