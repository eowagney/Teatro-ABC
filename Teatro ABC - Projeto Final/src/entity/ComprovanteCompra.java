package entity;

import java.util.List;


public class ComprovanteCompra {
    private String cpf;
    private String peca;
    private String sessao;
    private String area;
    private List<Integer> poltronas;
    private double valor;

    public ComprovanteCompra(String cpf, String peca, String sessao, String area, List<Integer> poltronas, double valor) {
        this.cpf = cpf;
        this.peca = peca;
        this.sessao = sessao;
        this.area = area;
        this.poltronas = poltronas;
        this.valor = valor;
    }
    public String getCpf() { return cpf; }
    public String getPeca() { return peca; }
    public String getSessao() { return sessao; }
    public String getArea() { return area; }
    public List<Integer> getPoltronas() { return poltronas; }
    public double getValor() { return valor; }

    public String getPoltronasComoTexto() {
        return String.join(",", poltronas.stream().map(String::valueOf).toArray(String[]::new));
    }
}