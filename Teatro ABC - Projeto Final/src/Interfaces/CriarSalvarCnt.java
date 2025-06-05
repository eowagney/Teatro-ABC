package interfaces;

import Objetos.Cliente;
import java.util.ArrayList;

public interface CriarSalvarCnt {

	boolean salvar(ArrayList<Cliente> dadosClientes, String arquivoContas);
}
