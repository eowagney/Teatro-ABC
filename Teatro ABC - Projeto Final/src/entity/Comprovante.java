package entity;

public class Comprovante {
    private int id;
    private String cpf;
    private String peca;
    private String sessao;
    private String area;
    private String poltronas;
    private double valor;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getPeca() { return peca; }
    public void setPeca(String peca) { this.peca = peca; }

    public String getSessao() { return sessao; }
    public void setSessao(String sessao) { this.sessao = sessao; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPoltronas() { return poltronas; }
    public void setPoltronas(String poltronas) { this.poltronas = poltronas; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public void setId(String string) {

        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}
