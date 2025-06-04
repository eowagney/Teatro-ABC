package Validadores;

import Interfaces.CriarValidador;

public class ValidarUsuario implements CriarValidador {

    @Override
    public boolean validar(String dado) {
        return dado != null && !dado.trim().isEmpty() && dado.length() >= 4;
    }
}
