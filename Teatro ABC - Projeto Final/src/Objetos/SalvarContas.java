package Objetos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Interfaces.CriarSalvar;

public class SalvarContas implements CriarSalvar {

	@Override
	public boolean salvar(ArrayList<Cliente> dadosClientes, String arquivoContas) {
		ArrayList<String> conteudoArquivo = new ArrayList<>();
		File arquivo = new File(arquivoContas);
	    try {
	        if (arquivo.exists()) {
	            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
	                String linha;
	                while ((linha = br.readLine()) != null) {
	                    conteudoArquivo.add(linha);
	                }
	            }
	        }
	        int clienteIndex = 0;
	        for (int i = 0; i < conteudoArquivo.size(); i++) {
	            if (conteudoArquivo.get(i).trim().isEmpty() && clienteIndex < dadosClientes.size()) {
	                conteudoArquivo.set(i, dadosClientes.get(clienteIndex).toString());
	                clienteIndex++;
	            }
	        }
	        while (clienteIndex < dadosClientes.size()) {
	            conteudoArquivo.add(dadosClientes.get(clienteIndex).toString());
	            clienteIndex++;
	        }
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
	            for (String linha : conteudoArquivo) {
	                bw.write(linha);
	                bw.newLine();
	            }
	        }
	        return true;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
