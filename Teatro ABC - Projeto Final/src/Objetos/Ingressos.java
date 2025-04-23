package Objetos;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(area, peca, poltronas, sessao);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingressos other = (Ingressos) obj;
		return Objects.equals(area, other.area) && Objects.equals(peca, other.peca)
				&& Objects.equals(poltronas, other.poltronas) && Objects.equals(sessao, other.sessao);
	}

}
