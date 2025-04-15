package Objetos;
public class Ingressos extends Cliente {

    private String peca;
    private String sessao;
    private String area;
    private String poltronas;

    public Ingressos(String cpf, String peca, String sessao, String area, String poltronas) {
        super(cpf);
        this.peca = peca;
        this.sessao = sessao;
        this.area = area;
        this.poltronas = poltronas;
    }

    @Override
public String toString() {
    return "Peça: " + peca + "\nSessão: " + sessao + "\nÁrea: " + area + "\nPoltrona(s): " + poltronas;
}

}
