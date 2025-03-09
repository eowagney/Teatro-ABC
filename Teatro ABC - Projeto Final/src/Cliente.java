public class Cliente {

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
