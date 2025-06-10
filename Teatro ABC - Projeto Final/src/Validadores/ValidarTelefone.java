package Validadores;

import Interfaces.CriarValidador;

public class ValidarTelefone implements CriarValidador {

    @Override
public boolean validar(String dado) {
    return dado != null && dado.matches("\\(\\d{2}\\)\\d{4,5}-\\d{4}");
}
}
