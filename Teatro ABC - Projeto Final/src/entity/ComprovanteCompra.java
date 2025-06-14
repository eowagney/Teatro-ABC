package entity;

import java.util.List;


public class ComprovanteCompra {
    @SuppressWarnings("FieldMayBeFinal")
    private String cpf;
    @SuppressWarnings("FieldMayBeFinal")
    private String peca;
    @SuppressWarnings("FieldMayBeFinal")
    private String sessao;
    @SuppressWarnings("FieldMayBeFinal")
    private String area;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Integer> poltronas;
    @SuppressWarnings("FieldMayBeFinal")
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