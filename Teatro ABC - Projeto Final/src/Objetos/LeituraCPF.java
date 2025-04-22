package Objetos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Interfaces.CriarLeitura;

public class LeituraCPF implements CriarLeitura{

	@Override
	public boolean lerArquivo(String cpf1, String nascimento1, String arquivoContas) {
		try (FileReader fr = new FileReader(arquivoContas)) {
            BufferedReader br = new BufferedReader(fr);
            while ((br.readLine()) != null){
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  

	}
}
