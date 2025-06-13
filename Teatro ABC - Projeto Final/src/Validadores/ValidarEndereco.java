package validadores;

import Interfaces.CriarValidador;

public class ValidarEndereco implements CriarValidador {

    @Override
    public boolean validar(String dado) {
        return !(dado == null || dado.trim().isEmpty());
    }
}
