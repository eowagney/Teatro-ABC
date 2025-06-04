package Validadores;

import Interfaces.CriarValidador;

public class ValidarSenha implements CriarValidador {

    @Override
    public boolean validar(String dado) {
        return dado != null && dado.length() >= 6;
    }
}
