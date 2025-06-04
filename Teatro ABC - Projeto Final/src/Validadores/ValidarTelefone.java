package Validadores;

import Interfaces.CriarValidador;

public class ValidarTelefone implements CriarValidador {

    @Override
    public boolean validar(String dado) {
        return dado != null && dado.matches("\\d{10,11}");
    }
}
