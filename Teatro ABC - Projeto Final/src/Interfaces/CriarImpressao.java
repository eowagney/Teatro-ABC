package Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CriarImpressao {

	boolean imprimir(String cpf) throws FileNotFoundException, IOException;
}
