package Objetos;

import java.util.Objects;

public class Cliente {

    @Override
	public int hashCode() {
		return Objects.hash(cpf, endereco, nascimento, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(nascimento, other.nascimento) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}


	// nome, CPF, telefone, endereço e data de nascimento.
    //atributosS
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String nascimento;
    
    //construtor
    public Cliente(String nome, String cpf, String telefone, String endereco, String nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.nascimento = nascimento;
    }

    public Cliente(String cpf2) {
        
    }


    //metodo topString
    @Override
public String toString() {
    return nome + " - " + cpf + " - " + telefone + " - "+ endereco + " - " + nascimento;
}




}
