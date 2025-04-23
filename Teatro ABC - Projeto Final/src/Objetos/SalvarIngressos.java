package Objetos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Interfaces.CriarSalvarIngr;

public class SalvarIngressos implements CriarSalvarIngr {

	@Override
	public boolean salvar(String cpf, String peca, String sessao, String area, String poltrona, String valor) {
		
		    String nomeArquivo = "ingressos.txt";
		    List<String> linhas = new ArrayList<>();
		    boolean atualizado = false;

		    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
		        String linha;

		        while ((linha = br.readLine()) != null) {
		            if (linha.startsWith("CPF: " + cpf) && 
		                linha.contains("Peça: " + peca) && 
		                linha.contains("Sessão: " + sessao) && 
		                linha.contains("Área: " + area)) {

		                String valorAtualStr = linha.split("Valor Total: ")[1].split(" \\|")[0];
		                double valorAtual = Double.parseDouble(valorAtualStr);
		                valorAtual += Double.parseDouble(valor);
		                linha = linha.replace("Valor Total: " + valorAtualStr, "Valor Total: " + valorAtual);
		                linha += ", " + poltrona;
		                atualizado = true;
		            }
		            linhas.add(linha);
		        }
		    } catch (FileNotFoundException e) {
		        System.out.println("Arquivo não encontrado. Será criado um novo.");
		    } catch (IOException e) {
		        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
		        return false;
		    }

		    if (!atualizado) {
		        String novaLinha = String.format("CPF: %s | Peça: %s | Sessão: %s | Área: %s | Valor Total: %s | Poltronas: %s", 
		                                        cpf, peca, sessao, area, valor, poltrona);
		        linhas.add(novaLinha);
		    }

		    try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
		        for (String linha : linhas) {
		            bw.write(linha);
		            bw.newLine();
		        }
		        return true;
		    } catch (IOException e) {
		        System.err.println("Erro ao salvar o ingresso: " + e.getMessage());
		        return false;
		    }
		}

	}



