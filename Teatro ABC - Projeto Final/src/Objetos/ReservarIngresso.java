package Objetos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Interfaces.CriarReservar;

public class ReservarIngresso implements CriarReservar{

	@Override
	public boolean reservar(String poltrona, String arquivoPoltrona) {
		String arquivoTemporario = "temp.txt";
		File arquivo = new File(arquivoPoltrona);
        String poltronaTemporaria = "[X]";
         boolean teste = false;

        try (
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(arquivoTemporario);
            BufferedWriter bw = new BufferedWriter(fw)
        ) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] palavras = linha.split(" ");
                for (int i = 0; i < palavras.length; i++) {
                    if (palavras[i].equals(poltrona)) {
                        palavras[i] = poltronaTemporaria;
                        teste = true; 
                    }
                }
                bw.write(String.join(" ", palavras));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao processar o arquivo: " + e.getMessage());
        }
        File temp = new File(arquivoTemporario);
        if (arquivo.delete()) {
            temp.renameTo(arquivo);
        }
        return teste;
	}
}
