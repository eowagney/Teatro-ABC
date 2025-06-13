package validadores;

import Interfaces.CriarValidador;

public class ValidarNascimento implements CriarValidador {

    @Override
    public boolean validar(String dado) {
        return dado != null && dado.matches("\\d{2}/\\d{2}/\\d{4}");
    }
}
