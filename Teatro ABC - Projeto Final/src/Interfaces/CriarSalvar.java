package Interfaces;

import java.util.ArrayList;

import Objetos.Cliente;

public interface CriarSalvar {

	boolean salvar(ArrayList<Cliente> dadosClientes, String arquivoContas);
}
