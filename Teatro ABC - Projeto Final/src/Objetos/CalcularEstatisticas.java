package objetos;

import Interfaces.CriarEstatisticas;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CalcularEstatisticas implements CriarEstatisticas {

	@Override
	public void calcualarEstatisticas(String cpf) {
            int peca1Ingressos = 0, peca2Ingressos = 0, peca3Ingressos = 0;
            double peca1Valor = 0.00, peca2Valor = 0.00, peca3Valor = 0.00;
            int sessao1Ingressos = 0, sessao2Ingressos = 0, sessao3Ingressos = 0;
            double sessao1Valor = 0.00, sessao2Valor = 0.00, sessao3Valor = 0.00;
        
            try (BufferedReader br = new BufferedReader(new FileReader("ingressos.txt"))) {
                String linha;
        
                while ((linha = br.readLine()) != null) {
                    if (linha.contains("Poltronas:") && linha.contains("Valor Total:")) {
                        String poltronasParte = linha.split("Poltronas:")[1].split("\\|")[0].trim();
                        String valorParte = linha.split("Valor Total:")[1].split("\\|")[0].trim();
        
                        String[] poltronas = poltronasParte.split(",");
                        int quantidadePoltronas = poltronas.length;
                        double valorTotal = Double.parseDouble(valorParte);
        
                        if (linha.contains("PEÇA 01")) {
                            peca1Ingressos += quantidadePoltronas;
                            peca1Valor += valorTotal;
                        } else if (linha.contains("PEÇA 02")) {
                            peca2Ingressos += quantidadePoltronas;
                            peca2Valor += valorTotal;
                        } else if (linha.contains("PEÇA 03")) {
                            peca3Ingressos += quantidadePoltronas;
                            peca3Valor += valorTotal;
                        }
        
                        if (linha.contains("MANHÃ")) {
                            sessao1Ingressos += quantidadePoltronas;
                            sessao1Valor += valorTotal;
                        } else if (linha.contains("TARDE")) {
                            sessao2Ingressos += quantidadePoltronas;
                            sessao2Valor += valorTotal;
                        } else if (linha.contains("NOITE")) {
                            sessao3Ingressos += quantidadePoltronas;
                            sessao3Valor += valorTotal;
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        
            int maxIngressos = Math.max(peca1Ingressos, Math.max(peca2Ingressos, peca3Ingressos));
            int minIngressos = Math.min(peca1Ingressos, Math.min(peca2Ingressos, peca3Ingressos));
        
            String maxVendidosPeca = (maxIngressos == peca1Ingressos) ? "PEÇA 01" :
                                     (maxIngressos == peca2Ingressos) ? "PEÇA 02" : "PEÇA 03";
            String minVendidosPeca = (minIngressos == peca1Ingressos) ? "PEÇA 01" :
                                     (minIngressos == peca2Ingressos) ? "PEÇA 02" : "PEÇA 03";
        
            int maxOcupacao = Math.max(sessao1Ingressos, Math.max(sessao2Ingressos, sessao3Ingressos));
            int minOcupacao = Math.min(sessao1Ingressos, Math.min(sessao2Ingressos, sessao3Ingressos));
        
            String maxOcupacaoSessao = (maxOcupacao == sessao1Ingressos) ? "MANHÃ" :
                                       (maxOcupacao == sessao2Ingressos) ? "TARDE" : "NOITE";
            String minOcupacaoSessao = (minOcupacao == sessao1Ingressos) ? "MANHÃ" :
                                       (minOcupacao == sessao2Ingressos) ? "TARDE" : "NOITE";
        
            float maxLucro = (float) Math.max(peca1Valor, Math.max(peca2Valor, Math.max(peca3Valor, Math.max(sessao1Valor, Math.max(sessao2Valor, sessao3Valor)))));
            float minLucro = (float) Math.min(peca1Valor, Math.min(peca2Valor, Math.min(peca3Valor, Math.min(sessao1Valor, Math.min(sessao2Valor, sessao3Valor)))));

            String maxLucroPecaSessao = "";
            String minLucroPecaSessao = "";

            if (maxLucro == peca1Valor) {
                maxLucroPecaSessao = "PEÇA 01";
            } else if (maxLucro == peca2Valor) {
                maxLucroPecaSessao = "PEÇA 02";
            } else if (maxLucro == peca3Valor) {
                maxLucroPecaSessao = "PEÇA 03";
            } else if (maxLucro == sessao1Valor) {
                maxLucroPecaSessao = "MANHÃ";
            } else if (maxLucro == sessao2Valor) {
                maxLucroPecaSessao = "TARDE";
            } else if (maxLucro == sessao3Valor) {
                maxLucroPecaSessao = "NOITE";
            }

            // Determina qual peça ou sessão foi menos lucrativa
            if (minLucro == peca1Valor) {
                minLucroPecaSessao = "PEÇA 01";
            } else if (minLucro == peca2Valor) {
                minLucroPecaSessao = "PEÇA 02";
            } else if (minLucro == peca3Valor) {
                minLucroPecaSessao = "PEÇA 03";
            } else if (minLucro == sessao1Valor) {
                minLucroPecaSessao = "MANHÃ";
            } else if (minLucro == sessao2Valor) {
                minLucroPecaSessao = "TARDE";
            } else if (minLucro == sessao3Valor) {
                minLucroPecaSessao = "NOITE";
            }
        
            double lucroMedioTotal = (peca1Valor + peca2Valor + peca3Valor) / 3.0;
        
            String nomeArquivo = "relatorio.txt";
        
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
                bw.write("Relatório do Teatro ABC:\n");
                bw.write("=========================\n");
        
                bw.write("Maior número de ingressos vendidos: " + maxVendidosPeca + " (" + maxIngressos + " ingressos)\n");
                bw.write("Menor número de ingressos vendidos: " + minVendidosPeca + " (" + minIngressos + " ingressos)\n\n");
        
                bw.write("Maior ocupação de poltronas: " + maxOcupacaoSessao + " (" + maxOcupacao + " poltronas)\n");
                bw.write("Menor ocupação de poltronas: " + minOcupacaoSessao + " (" + minOcupacao + " poltronas)\n\n");
        
                bw.write("Peça/Sessão mais lucrativa: " + maxLucroPecaSessao + " (R$ " + maxLucro + ")\n");
                bw.write("Peça/Sessão menos lucrativa: " + minLucroPecaSessao + " (R$ " + minLucro + ")\n\n");
        
                bw.write("Lucro médio total do teatro por peça: R$ " + String.format("%.2f", lucroMedioTotal) + "\n");
                bw.write("=========================\n");
        
                System.out.println("Relatório salvo no arquivo " + nomeArquivo);
            } catch (IOException e) {
                System.err.println("Erro ao salvar o relatório: " + e.getMessage());
            }
        }
	
}
