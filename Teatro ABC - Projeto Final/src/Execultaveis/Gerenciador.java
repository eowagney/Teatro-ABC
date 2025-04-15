package Execultaveis;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gerenciador{

    //Método para verificar o login do usuário retornando uma boolean
    public static boolean contaCadastrada(String cpf1, String nascimento1, String arquivoContas) {
            try (FileReader fr = new FileReader(arquivoContas)) {
                BufferedReader br = new BufferedReader(fr);
                String linha;
                while ((linha = br.readLine()) != null){
                    String[] dados = linha.split(" - ");
                    if (dados.length == 5) { // Verifica se a linha CPF e data de nascimento.
                        String cpf = dados[1];
                        String nascimento = dados[4];
                        
                        //verificando no arquivo se esta cadastrada
                        if(cpf.equals(cpf1) && nascimento.equals(nascimento1)){
                            return true;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;   
        }
    //Método para verificar se o cpf já está cadastrado no sistema
    public static boolean cpfCadastrado(String cpf1, String arquivoContas) {
        try (FileReader fr = new FileReader(arquivoContas)) {
            BufferedReader br = new BufferedReader(fr);
            String linha;
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(" - ");
                if (dados.length == 5) { // Verifica se a linha tem nome, CPF, telefone, endereço e data de nascimento.
                    String cpf = dados[1];

                    if(cpf.equals(cpf1)){
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;   
    }
        // Método para salvar os conta no arquivo
        public static void salvarConta(ArrayList<Cliente> dadosClientes, String arquivoContas) {
            File arquivo = new File(arquivoContas);
            // Lista para armazenar o conteúdo do arquivo
            ArrayList<String> conteudoArquivo = new ArrayList<>();
             try {
                // Ler o conteúdo do arquivo existente
                if (arquivo.exists()) {
                    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            conteudoArquivo.add(linha); // Adiciona cada linha ao ArrayList
                        }
                    }
                }
                // Preencher linhas vazias com os dados dos clientes
                int clienteIndex = 0; // Índice para acessar os clientes
                for (int i = 0; i < conteudoArquivo.size(); i++) {
                    if (conteudoArquivo.get(i).trim().isEmpty() && clienteIndex < dadosClientes.size()) {
                        conteudoArquivo.set(i, dadosClientes.get(clienteIndex).toString());
                        clienteIndex++; // Avança para o próximo cliente
                    }
                }
                // Adicionar qualquer cliente restante no final do arquivo
                while (clienteIndex < dadosClientes.size()) {
                    conteudoArquivo.add(dadosClientes.get(clienteIndex).toString());
                    clienteIndex++;
                }
                // Escrever o conteúdo atualizado de volta no arquivo
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
                    for (String linha : conteudoArquivo) {
                        bw.write(linha);
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            //VALIDACAO DE CPF
            public static boolean validarCpf(String cpf2){
            long cpf = Long.parseLong(cpf2);
           
            //separando o cpf por unidade
            long cpf1 = cpf / 100, verificacao, digito1, digito2, soma = 0, divisao = 100000000, peso = 1;   
             
             //fazendo o calculo de verificacao para o achar o primeiro digito
             for(int i = 0; i < 9; i++){             
                 soma = soma + (cpf1 / divisao) * peso;
                 cpf1 = cpf1 % divisao;
                 divisao = divisao / 10; 
                 peso++;
             }
             //atualizando as variaveis
             digito1 = (soma % 11) % 10;
             cpf1 = cpf / 100;
             divisao = 100000000;
             soma = 0;
             peso = 0;
             
             // calculo para achar o segundo digito
               for(int i = 0; i < 9; i++){               
                 soma = soma + (cpf1 / divisao) * peso;
                 cpf1 = cpf1 % divisao;
                 divisao = divisao / 10;            
                 peso++;
             }
            
            //juntando todos os numeros separados junto com os dois digitos
           soma = soma + digito1 * 9;  
           digito2 = (soma % 11) % 10;
           cpf = cpf % 100;
           verificacao = digito1*10+digito2;
           
           //verificando se o cpf e valido
           if(verificacao == cpf){
            return true;
           }else{            
            return false;
           }   
        }
        // metodo para reservar poltrona 
        public static boolean reservarPoltrona(String palavra1, String aquivo) {
    
            // Caminho do arquivo temporário
            String arquivoTemporario = "temp.txt";

            // simpulo para poltroa ocupada
            String palavra2 = "[X]";

             // Variável para rastrear se houve substituição
             boolean substituido = false;
    
            try (
                FileReader fr = new FileReader(aquivo);
                BufferedReader br = new BufferedReader(fr);
                FileWriter fw = new FileWriter(arquivoTemporario);
                BufferedWriter bw = new BufferedWriter(fw)
            ) {
                String linha;
    
                // Lê o arquivo linha por linha
                while ((linha = br.readLine()) != null) {
                    // Divide a linha em palavras
                    String[] palavras = linha.split(" ");
    
                    // Substitui as palavras
                    for (int i = 0; i < palavras.length; i++) {
                        if (palavras[i].equals(palavra1)) {
                            palavras[i] = palavra2;
                            substituido = true; // Marca como substituído
                        }
                    }
                    // Escreve a linha modificada no arquivo temporário
                    bw.write(String.join(" ", palavras));
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao processar o arquivo: " + e.getMessage());
            }
    
            // Substitui o arquivo original pelo temporário
            File arquivo = new File(aquivo);
            File temp = new File(arquivoTemporario);
    
            if (arquivo.delete()) {
                temp.renameTo(arquivo);
            }
            return substituido; // Retorna true se a substituição ocorreu, caso contrário, false
        }
        // Método para salvar os ingressos no arquivo
        public static void salvarIngresso(String cpf, String peca, String sessao, String area, String poltrona, String valor) {
            String nomeArquivo = "ingressos.txt";
            List<String> linhas = new ArrayList<>();
            boolean atualizado = false;

            try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
                String linha;
                
                // Lê todas as linhas do arquivo
                while ((linha = br.readLine()) != null) {
                    // Verifica se a linha contém os mesmos dados de CPF, peça, sessão e área
                    if (linha.startsWith("CPF: " + cpf) && 
                        linha.contains("Peça: " + peca) && 
                        linha.contains("Sessão: " + sessao) && 
                        linha.contains("Área: " + area)) {
                        
                        // Extrai o valor existente da linha
                        String valorAtualStr = linha.split("Valor Total: ")[1].split(" \\|")[0];
                        double valorAtual = Double.parseDouble(valorAtualStr);

                        // Soma o novo valor ao valor existente
                        valorAtual += Double.parseDouble(valor);

                        // Atualiza a linha com o novo valor e adiciona a nova poltrona
                        linha = linha.replace("Valor Total: " + valorAtualStr, "Valor Total: " + valorAtual);
                        linha += ", " + poltrona;
                        atualizado = true;
                    }
                    // Adiciona a linha à lista (modificada ou não)
                    linhas.add(linha);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado. Será criado um novo.");
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
            // Se não foi encontrada uma linha correspondente, cria uma nova
            if (!atualizado) {
                String novaLinha = String.format("CPF: %s | Peça: %s | Sessão: %s | Área: %s | Valor Total: %s | Poltronas: %s ", 
                                                cpf, peca, sessao, area, valor, poltrona);
                linhas.add(novaLinha);
            }
            // Escreve todas as linhas de volta no arquivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))) {
                for (String linha : linhas) {
                    bw.write(linha);
                    bw.newLine();
                }
                System.out.println("Ingresso salvo/atualizado com sucesso!");
            } catch (IOException e) {
                System.err.println("Erro ao salvar o ingresso: " + e.getMessage());
            }
        }

        // método para imprimir ingresso
        public static boolean imprimirIngresso(String cpf) throws FileNotFoundException, IOException {
            try (BufferedReader br = new BufferedReader(new FileReader("Ingressos.txt"))) {
                String linha;
                boolean encontrado = false;
        
                // Criação do JFrame
                JFrame janelaImprimir = new JFrame("IMPRIMIR COMPROVANTE");
                janelaImprimir.setSize(400, 300);
                janelaImprimir.setLocationRelativeTo(null);
                janelaImprimir.setLayout(null);
        
                // JTextArea para entrada de texto
                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                JScrollPane scrollImprimir = new JScrollPane(textArea);
                scrollImprimir.setBounds(20, 20, 350, 180);
        
                // Adiciona o scroll ao JFrame
                janelaImprimir.add(scrollImprimir);
        
                // Lê todas as linhas do arquivo
                StringBuilder comprovantes = new StringBuilder(); // Para armazenar todos os comprovantes encontrados
                while ((linha = br.readLine()) != null) {
                    if (linha.startsWith("CPF: " + cpf)) {
                        encontrado = true;
                        // Formata a linha e adiciona ao comprovante
                        String textoFormatado = linha.replace(" | ", "\n").replace(" |", "\n");
                        comprovantes.append("\n---------------COMPROVANTE--------------------\n").append(textoFormatado).append("\n----------------------------------------------------------\n"); // Adiciona um espaço entre os comprovantes
                    }
                }
        
                if (encontrado) {
                    textArea.setText(comprovantes.toString());
                } else {
                    textArea.setText("Nenhum comprovante encontrado para o CPF informado.");
                }
        
                // Botão para imprimir
                JButton imprimirButton = new JButton("Imprimir");
                imprimirButton.setBounds(140, 220, 120, 30);
        
                // Adiciona ActionListener ao botão
                imprimirButton.addActionListener(e -> {
                    try {
                        // Fecha a janela
                        janelaImprimir.dispose();
        
                        // Utiliza o método print para imprimir o texto
                        boolean imprimido = textArea.print();
        
                        if (imprimido) {
                            JOptionPane.showMessageDialog(janelaImprimir, "Impressão concluída com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(janelaImprimir, "Impressão cancelada.");
                        }
        
                    } catch (PrinterException ex) {
                        JOptionPane.showMessageDialog(janelaImprimir, "Erro ao imprimir: " + ex.getMessage());
                    }
                });
        
                // Adiciona o botão ao JFrame
                janelaImprimir.setLayout(null);
                janelaImprimir.add(scrollImprimir);
                janelaImprimir.add(imprimirButton);
        
                // Exibe a janela
                janelaImprimir.setVisible(true);
                return encontrado;
            } 
            }
            public static void calcularEstatisticas() {
                // Inicializa os valores
                int peca1Ingressos = 0, peca2Ingressos = 0, peca3Ingressos = 0;
                double peca1Valor = 0.00, peca2Valor = 0.00, peca3Valor = 0.00;
                int sessao1Ingressos = 0, sessao2Ingressos = 0, sessao3Ingressos = 0;
                double sessao1Valor = 0.00, sessao2Valor = 0.00, sessao3Valor = 0.00;
            
                try (BufferedReader br = new BufferedReader(new FileReader("ingressos.txt"))) {
                    String linha;
            
                    // Lê cada linha do arquivo
                    while ((linha = br.readLine()) != null) {
                        // Verifica se a linha contém "Poltronas:" e "Valor Total:"
                        if (linha.contains("Poltronas:") && linha.contains("Valor Total:")) {
                            // Extrai a parte das poltronas e do valor total
                            String poltronasParte = linha.split("Poltronas:")[1].split("\\|")[0].trim();
                            String valorParte = linha.split("Valor Total:")[1].split("\\|")[0].trim();
            
                            // Divide as poltronas e obtém a quantidade
                            String[] poltronas = poltronasParte.split(",");
                            int quantidadePoltronas = poltronas.length;
                            double valorTotal = Double.parseDouble(valorParte);
            
                            // Acumula os valores e ingressos para peças
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
            
                            // Acumula os valores e ingressos para sessões
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
            
                // Calcula as estatísticas
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
            
               // Identificando a peça ou sessão mais lucrativa e menos lucrativa
                float maxLucro = (float) Math.max(peca1Valor, Math.max(peca2Valor, Math.max(peca3Valor, Math.max(sessao1Valor, Math.max(sessao2Valor, sessao3Valor)))));
                float minLucro = (float) Math.min(peca1Valor, Math.min(peca2Valor, Math.min(peca3Valor, Math.min(sessao1Valor, Math.min(sessao2Valor, sessao3Valor)))));

                // Variáveis para armazenar as peças ou sessões mais/menos lucrativas
                String maxLucroPecaSessao = "";
                String minLucroPecaSessao = "";

                // Determina qual peça ou sessão foi mais lucrativa
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
            
                // Salva os dados no arquivo
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
            
            //metodo para ver as estatistica
            public static void verEstatistica() throws FileNotFoundException, IOException {
                // Inicializa o BufferedReader para ler o arquivo
                try (BufferedReader br = new BufferedReader(new FileReader("relatorio.txt"))) {
                    String linha;
                    StringBuilder estatistica = new StringBuilder();
                    
                    // Lê cada linha do arquivo e adiciona ao StringBuilder
                    while ((linha = br.readLine()) != null) {
                        estatistica.append(linha).append("\n");  // Adiciona uma nova linha após cada linha lida
                    }
        
                    // Criação do JFrame para mostrar as estatísticas
                    JFrame telaEstatistica = new JFrame("ESTATÍSTICAS");
                    telaEstatistica.setSize(500, 300);
                    telaEstatistica.setLocationRelativeTo(null);
                    telaEstatistica.setLayout(null);
        
                    // Criação do JTextArea para mostrar as estatísticas
                    JTextArea textArea = new JTextArea();
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);
                    textArea.setEditable(false);
                    textArea.setText(estatistica.toString());  // Atribui o conteúdo do arquivo ao JTextArea
        
                    // Adiciona o JScrollPane que contém o JTextArea ao JFrame
                    JScrollPane scrollImprimir = new JScrollPane(textArea);
                    scrollImprimir.setBounds(20, 10, 450, 240);
        
                    // Adiciona o JScrollPane ao JFrame
                    telaEstatistica.add(scrollImprimir);
        
                    // Exibe a tela com as estatísticas
                    telaEstatistica.setVisible(true);
        
                } catch (IOException e) {
                    System.err.println("Erro ao ler o arquivo de estatísticas: " + e.getMessage());
                }
            }
                public static void main(String[] args) throws Exception {
                    JFrame telaInicial = new JFrame("Teatro ABC");

                    ImageIcon logoImg = new ImageIcon("logolonga.png"); // Carregar a imagem
                    JLabel logoImgLabel = new JLabel(logoImg);
                    logoImgLabel.setBounds(-100, -40, 700, 350); // Definir posição e tamanho da JLabel
                    telaInicial.add(logoImgLabel);
                    telaInicial.setLocationRelativeTo(null);
                    telaInicial.add(logoImgLabel);
            
                    //arquivos
                    String arquivoContas = "arquivoContas.txt";
            
                     // Criando um array list
                    ArrayList<Cliente> dadosContass = new ArrayList<Cliente>();

                     //criando butoes 
                    JButton cadastroUsuario = new JButton("CADASTRAR USUÁRIO");  ///////////////////////
                    cadastroUsuario.setBounds(130, 280, 230, 40);
                    cadastroUsuario.setFocusPainted(false);
                    cadastroUsuario.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed (ActionEvent e){
                                JFrame telaCadastro = new JFrame("Tela de Cadastro");

                                //adicionado textfield e button
                                JLabel nome = new JLabel("NOME");
                                nome.setBounds(10, 10, 200,20);
                                JTextField campoNome = new JTextField();
                                campoNome.setBounds(10, 40, 250, 20);
                                JLabel cpf = new JLabel("CPF");
                                cpf.setBounds(10, 70, 200,20);
                                JTextField campoCpf = new JTextField();
                                campoCpf.setBounds(10, 100, 250, 20);
                                JLabel tefone = new JLabel("TELEFONE");
                                tefone.setBounds(10, 130, 200,20);
                                JTextField campoTelefone = new JTextField();
                                campoTelefone.setBounds(10, 160, 250, 20);
                                JLabel endereco = new JLabel("ENDEREÇO");
                                endereco.setBounds(10, 190, 200,20);
                                JTextField campoEndereco = new JTextField();
                                campoEndereco.setBounds(10, 220, 250, 20);
                                JLabel nascimento = new JLabel("DATA DE NASCIMENTO");
                                nascimento.setBounds(10, 250, 200,20);
                                JTextField campoNacimento = new JTextField();
                                campoNacimento.setBounds(10, 280, 250, 20);
            
                               // Criando botão de Confirmar
                            JButton confirmar = new JButton("CONFIRMAR");
            
                            // Editando o botão
                            confirmar.setVisible(true);
                            confirmar.setBounds(70, 310, 150, 40);
                            confirmar.setFocusPainted(false);
            
                            // Adicionando um ouvinte de evento (ActionListener) ao botão
                            confirmar.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        // Obter os valores de nome, CPF, telefone, endereço e data de nascimento.
                                        String nome = campoNome.getText();
                                        String cpf = campoCpf.getText();
                                        String telefone = campoTelefone.getText();
                                        String endereco = campoEndereco.getText();
                                        String nascimento = campoNacimento.getText();

                                        if(validarCpf(cpf) && !cpfCadastrado(cpf, arquivoContas)){

                                        // Criar uma nova instância de Contato
                                        Cliente novoCliente = new Cliente(nome , cpf, telefone, endereco, nascimento);

                                        //adicionando 
                                        dadosContass.add(novoCliente);

                                        // Salva a nova conta no arquivo em modo de anexo
                                       salvarConta(dadosContass, arquivoContas);

                                        // Limpar os campos de texto
                                        campoNome.setText("");
                                        campoCpf.setText("");
                                        campoTelefone.setText("");
                                        campoEndereco.setText("");
                                        campoNacimento.setText("");

                                        telaCadastro.dispose();
                                        }else{
                                            if(!validarCpf(cpf)){
                                            JOptionPane.showMessageDialog(telaCadastro, "CPF Inválido");
                                            }else if(cpfCadastrado(cpf, arquivoContas)){
                                            JOptionPane.showMessageDialog(telaCadastro, "CPF Já Cadastrado");
                                            }
                                            campoCpf.setText("");
                                        }
                                    
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(telaCadastro, "Dados Inválidos");
                                        // Limpar os campos de texto
                                        campoNome.setText("");
                                        campoCpf.setText("");
                                        campoTelefone.setText("");
                                        campoEndereco.setText("");
                                        campoNacimento.setText("");
                                    }
                                }
                            });
                                //Adicionar a tela
                                telaCadastro.add(nome);
                                telaCadastro.add(campoNome);
                                telaCadastro.add(cpf);
                                telaCadastro.add(campoCpf);
                                telaCadastro.add(tefone);
                                telaCadastro.add(campoTelefone);
                                telaCadastro.add(endereco);
                                telaCadastro.add(campoEndereco);
                                telaCadastro.add(nascimento);
                                telaCadastro.add(campoNacimento);
                                telaCadastro.add(confirmar);
                                
            
                                //editando a janela de cadastro
                                telaCadastro.setSize(300, 400);
                                telaCadastro.setLayout(null);
                                telaCadastro.setVisible(true);
                                telaCadastro.setLocationRelativeTo(null);
                            }
                        });
            
                        JButton fazerLogin = new JButton("FAZER LOGIN");  /////////////////////////
                        fazerLogin.setBounds(130, 350, 230, 40);
                        fazerLogin.setFocusPainted(false);
                        fazerLogin.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed (ActionEvent e){
                                JFrame telaLogin = new JFrame("Tela de Login");
            
                        //adicionado textfield e button
                        JLabel cpf = new JLabel("CPF");
                        cpf.setBounds(10, 10, 200,20);
                        JTextField campoCpf = new JTextField();
                        campoCpf.setBounds(10, 40, 100, 20);
                        JLabel nascimento = new JLabel("DATA DE NASCIMENTO");
                        nascimento.setBounds(10, 70, 200,20);
                        JTextField campoNacimento = new JTextField();
                        campoNacimento.setBounds(10, 100, 100, 20);
            
                       // Criando botão de Confirmar
                    JButton confirmar = new JButton("CONFIRMAR");
            
                    // Editando o botão
                    confirmar.setVisible(true);
                    confirmar.setBounds(70, 150, 150, 40);
                    confirmar.setFocusPainted(false);
            
                    // Adicionando um ouvinte de evento (ActionListener) ao botão
                    confirmar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
            
                                // Obter os valores de cpf e nascimento
                                String cpf1 = campoCpf.getText();
                                String nascimento1 = campoNacimento.getText();
                                contaCadastrada(cpf1, nascimento1, arquivoContas);

                         if (contaCadastrada(cpf1, nascimento1, arquivoContas)) {
                        telaLogin.dispose();
                        telaInicial.dispose();

                        JFrame  telaUsuario = new JFrame("Tela Usuário");
                        telaUsuario.setLayout(null);

                        //botão de compra
                        JButton compra = new JButton("COMPRAR INGRESSO");
            
                            // Editando o botão
                            compra.setVisible(true);
                            compra.setBounds(60, 50, 160, 40);
                            compra.setFocusPainted(false);
            
                            //JOAO GABRIEL   //JOAO GABRIEL   //JOAO GABRIEL
                            //JOAO GABRIEL   //JOAO GABRIEL   //JOAO GABRIEL
                            //JOAO GABRIEL   //JOAO GABRIEL   //JOAO GABRIEL
                            //JOAO GABRIEL   //JOAO GABRIEL   //JOAO GABRIEL
                            //JOAO GABRIEL   //JOAO GABRIEL   //JOAO GABRIEL

                            // Adicionando um ouvinte de evento (ActionListener) ao botão
                            compra.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //CAIXA DE TEXTO DAS POLTRONAS
                                    JTextField poltronas = new JTextField();

                                    //PERSONALIZAÇÃO DA FONTE DENTRO DA TEXTFIELD
                                    Font fonte = new Font("Arial", Font.PLAIN, 20);  // Fonte Arial, estilo normal, tamanho 20
                                    poltronas.setFont(fonte);

                                    //JANELA
                                    JFrame comprandoIngresso = new JFrame("COMPRA DE INGRESSO");
                                    comprandoIngresso.setSize(750, 700);
                                    //CENTRALIZAR A FRAME INDEPENDENTE DE QUAL TELA O PROGRAMA FOR EXECUTADO
                                    comprandoIngresso.setLocationRelativeTo(null); 

                                    //ARRAY/VETOR PARA AGRUPAR AS AREAS, SESSOES E PEÇAS
                                    String[] area = { "PLATEIA A", "PLATEIA B", "FRISA 1", "FRISA 2", "FRISA 3", "FRISA 4", "FRISA 5", "CAMAROTE 1",
                                            "CAMAROTE 2", "CAMAROTE 3", "CAMAROTE 4", "CAMAROTE 5", "BALCÃO NOBRE" };
                                    String[] sessao = { "MANHÃ", "TARDE", "NOITE"};
                                    String[] peca = { "PEÇA 01", "PEÇA 02", "PEÇA 03" };

                                    //INSTANCIANDO OBJETOS
                                    //JCOMBOBOX USADO PARA AGRUPAR EM ''LISTA'' TODOS OS COMPONENTES DE AREA, SESSAO E PEÇA
                                    JComboBox<String> areaBox = new JComboBox<>(area);
                                    areaBox.setSelectedIndex(-1); 
                                    JComboBox<String> sessaoBox = new JComboBox<>(sessao);
                                    sessaoBox.setSelectedIndex(-1);
                                    JComboBox<String> pecaBox = new JComboBox<>(peca);
                                    pecaBox.setSelectedIndex(-1);

                                    //ORGANIZAR OS COMPONENTES DO COMBOBOX
                                    JPanel componentes = new JPanel();
                                    componentes.setLayout(null);
                                    pecaBox.setBounds(30, 420, 200, 30); // PEÇAS
                                    sessaoBox.setBounds(265, 420, 200, 30);// SESSÃO
                                    areaBox.setBounds(505, 420, 200, 30);// ÁREA
                                    poltronas.setBounds(205, 510, 300, 50);// CAIXA PARA INSERIR AS POLTRONAS

                                    //IMAGEM TEATRO ABC
                                    ImageIcon teatroImg = new ImageIcon("MapaTeatro.png"); //ALTERAR IMAGEM - FAZER LOGO TEATRO ABC
                                    JLabel teatroImgLabel = new JLabel(teatroImg);
                                    teatroImgLabel.setBounds(-70, -10, 900, 400);
                                    
                                    //TEXTOS
                                    JLabel pecaLabel = new JLabel("SELECIONE A PEÇA");
                                    pecaLabel.setBounds(70, 400, 200, 20);
                                    JLabel sessaoLabel = new JLabel("SELECIONE A SESSÃO");
                                    sessaoLabel.setBounds(295, 400, 200, 20);
                                    JLabel areaLabel = new JLabel("SELECIONA A ÁREA");
                                    areaLabel.setBounds(540, 400, 200, 20);
                                    JLabel poltronaLabel = new JLabel("INSIRA O NÚMERO DA POLTRONA DE ACORDO COM A IMAGEM A CIMA");
                                    poltronaLabel.setBounds(160, 440, 400, 100);

                                    //BOTÃO ''ADICIONAR INGRESSO''
                                    JButton botaoAdicionar = new JButton("ADICIONAR INGRESSO");
                                    botaoAdicionar.setBounds(90, 580, 250, 60);
                                    //BOTÃO ''FINALIZAR COMPRA''
                                    JButton botaoFinalizar = new JButton("FINALZAR COMPRA");
                                    botaoFinalizar.setBounds(390, 580, 250, 60);

                                    //ADICIONANDO COMPONENTES
                                    componentes.add(pecaLabel);
                                    componentes.add(sessaoLabel);
                                    componentes.add(areaLabel);
                                    componentes.add(areaBox);
                                    componentes.add(poltronaLabel);
                                    componentes.add(botaoAdicionar);
                                    componentes.add(botaoFinalizar);
                                    componentes.add(pecaBox);
                                    componentes.add(sessaoBox);
                                    componentes.add(poltronas);
                                    componentes.add(teatroImgLabel);
                                    
                                    botaoAdicionar.addActionListener(ec -> { 
                                        String pecaSelecionada = (String) pecaBox.getSelectedItem();
                                        String sessaoSelecionada = (String) sessaoBox.getSelectedItem();
                                        String areaSelecionada = (String) areaBox.getSelectedItem();
                                        String poltronaTexto = poltronas.getText();

                                        //BLOCO 01 - SERA EXECUTADO NORMALMENTE SEM ERROS.
                                        try { 
                                            int poltrona = Integer.parseInt(poltronaTexto);
                                        
                                            if (poltrona <= 0 || poltrona > 255) {
                                                JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona inválida, escolha um número de acordo com a \no número de poltronas existentes.");
                                                return;
                                            } else if (areaSelecionada.startsWith("PLATEIA A") && (poltrona < 1 || poltrona > 25)) {
                                                JOptionPane.showMessageDialog(comprandoIngresso, "PLATEIA A possui poltronas de 1 a 25!");
                                                return;
                                            } else if (areaSelecionada.startsWith("PLATEIA B") && (poltrona < 26 || poltrona > 125)) {
                                                JOptionPane.showMessageDialog(comprandoIngresso, "PLATEIA B possui poltronas de 26 a 125!");
                                                return;
                                            } else if (areaSelecionada.startsWith("FRISA") && (poltrona < 126 || poltrona > 155)) {
                                                JOptionPane.showMessageDialog(comprandoIngresso, "FRISA possui poltronas de 126 a 155!");
                                                return;
                                            } else if (areaSelecionada.startsWith("CAMAROTE") && (poltrona < 156 || poltrona > 205)) {
                                                JOptionPane.showMessageDialog(comprandoIngresso, "CAMAROTE possui poltronas de 156 a 205!");
                                                return;
                                            } else if (areaSelecionada.startsWith("BALCÃO NOBRE") && (poltrona < 206 || poltrona > 255)) {
                                                JOptionPane.showMessageDialog(comprandoIngresso, "BALCÃO NOBRE possui poltronas de 206 a 255!");
                                                return;
                                            }
                                            //FIM JOAO GABRIEL

                                            //VALORES DE CADA AREA
                                            Double valor = 0.00;

                                            if(poltrona > 0 && poltrona < 26){
                                                valor = 40.0;
                                            }else if(poltrona > 25 && poltrona < 126){
                                                valor = 60.0;
                                            }else if(poltrona > 125 && poltrona < 156){
                                                valor = 120.0;
                                            }else if(poltrona > 155 && poltrona < 206){
                                                valor = 80.0;
                                            }else if(poltrona > 205 && poltrona < 256){
                                                valor = 250.0;
                                            }

                                            String valorString = String.valueOf(valor);

                                            if(pecaSelecionada.equals("PEÇA 01") && sessaoSelecionada.equals("MANHÃ")){        
                                                if(reservarPoltrona(poltronaTexto, "Peca1Manha.txt")){                                                       
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);

                                            }else{
                                                JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                            }
                                            }else if(pecaSelecionada.equals("PEÇA 01") && sessaoSelecionada.equals("TARDE")){                                            
                                                if(reservarPoltrona(poltronaTexto, "Peca1Tarde.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 01") && sessaoSelecionada.equals("NOITE")){                                                
                                                if(reservarPoltrona(poltronaTexto, "Peca1Noite.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 02") && sessaoSelecionada.equals("MANHÃ")){                     
                                                if(reservarPoltrona(poltronaTexto, "Peca2Manha.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                   // salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, totalAtualizado);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 02") && sessaoSelecionada.equals("TARDE")){                      
                                                if(reservarPoltrona(poltronaTexto, "Peca2Tarde.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 02") && sessaoSelecionada.equals("NOITE")){                   
                                                if(reservarPoltrona(poltronaTexto, "Peca2Noite.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 03") && sessaoSelecionada.equals("MANHÃ")){                    
                                                if(reservarPoltrona(poltronaTexto, "Peca3Manha.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 03") && sessaoSelecionada.equals("TARDE")){                  
                                                if(reservarPoltrona(poltronaTexto, "Peca3Tarde.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 03") && sessaoSelecionada.equals("NOITE")){                                              
                                                if(reservarPoltrona(poltronaTexto, "Peca3Noite.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }
                                            poltronas.setText("");
                                            //BLOCO 02 - CAPTURAR E TRATAR ALGUM ERRO EXISTENTE
                                        } catch (NumberFormatException ex) {
                                            JOptionPane.showMessageDialog(comprandoIngresso, "Valor invalido, favor insira novamente um valor de acordo com a tabela a cima.", "VALOR INVALIDO", 0);
                                        }
                                        
                                    });

                                    //TUDO CERTO NESSA PARTE
                                    botaoFinalizar.addActionListener(ev ->{ //ADICIONANDO AÇÃO AO BOTAO FINILZAR
                                    JOptionPane.showMessageDialog(comprandoIngresso, "INGRESSO(S) ADQUIRIDO(S) COM SUCESSO", "PARABÉNS!", 1);
                                        int continuarRetornar = JOptionPane.showConfirmDialog(comprandoIngresso,  "TEM CERTEZA QUE DESEJA FINALIZAR A COMPRA?", "Confirmação", JOptionPane.YES_NO_OPTION);

                                        if(continuarRetornar == JOptionPane.YES_OPTION){ //FINALIZAR COMPRA (PODENDO ADICONAR A AÇÃO DE VOLTAR A TELE INCIAL);
                                            comprandoIngresso.dispose();
                                            try {
                                                imprimirIngresso(cpf1);
                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            } 

                                        }else if(continuarRetornar == JOptionPane.NO_OPTION){
                                            return;
                                        }    
                                    });
                                    comprandoIngresso.add(componentes); //JPANEL
                                    comprandoIngresso.setVisible(true);//MOSTRAR FRAME
                                    botaoAdicionar.setFocusPainted(false);
                                    botaoFinalizar.setFocusPainted(false);
                                }
                            });
                        //botão de imprimir
                        JButton imprimir = new JButton("IMPRIMIR");
            
                        // Editando o botão
                        imprimir.setVisible(true);
                        imprimir.setBounds(60, 100, 160, 40);
                        imprimir.setFocusPainted(false);
        
                        // Adicionando um ouvinte de evento (ActionListener) ao botão
                        imprimir.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    if(imprimirIngresso(cpf1)){
                                    } else {
                                        JOptionPane.showMessageDialog(telaLogin, "Nenhum ingresso comprado em seu CPF!");
                                    }
                                } catch (IOException e1) {                             
                                    e1.printStackTrace();
                                }
                            }
                        });

                        //fazer
                        //botão de estatísticas
                        JButton estatistica = new JButton("ESTATISTÍCA");
            
                            // Editando o botão
                            estatistica.setVisible(true);
                            estatistica.setBounds(60, 150, 160, 40);
                            estatistica.setFocusPainted(false);
            
                            // Adicionando um ouvinte de evento (ActionListener) ao botão
                            estatistica.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        calcularEstatisticas();
                                        verEstatistica();
                                    } catch (NumberFormatException ex) {
                                        
                                    } catch (FileNotFoundException e1) {
                                    e1.printStackTrace();
                                     } catch (IOException e1) {
                                        e1.printStackTrace();
                                      }
                                   
                                }
                            });

                        //add
                        telaUsuario.add(compra);
                        telaUsuario.add(imprimir);
                        telaUsuario.add(estatistica);

                        //editando janela
                        telaUsuario.setSize(300, 300);
                        telaUsuario.setVisible(true);
                        telaUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        telaUsuario.setLocationRelativeTo(null);
                    } else {
                        JOptionPane.showMessageDialog(fazerLogin,"Usuário não cadastrado!");
                        campoCpf.setText("");
                        campoNacimento.setText("");
                    }
                        }});

            //Adicionar a tela
            telaLogin.add(cpf);
            telaLogin.add(campoCpf);
            telaLogin.add(nascimento);
            telaLogin.add(campoNacimento);
            telaLogin.add(confirmar);

            //editando a janela de cadastro
            telaLogin.setSize(300, 250);
            telaLogin.setLayout(null);
            telaLogin.setVisible(true);
            telaLogin.setLocationRelativeTo(null);
        }
    });
        //Adicionar a tela
        telaInicial.add(cadastroUsuario);
        telaInicial.add(fazerLogin);

        //editando a janela principal
        telaInicial.setSize(500, 500);
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaInicial.setLayout(null);
        telaInicial.setVisible(true);
        telaInicial.setLocationRelativeTo(null);
    }
    }