package Interfaces;

import java.util.ArrayList;

import Objetos.Cliente;

public interface CriarSalvarCnt {

	boolean salvar(ArrayList<Cliente> dadosClientes, String arquivoContas);
}
