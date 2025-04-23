package Execultaveis;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Objetos.Cliente;
import Objetos.ImprimirEstatisticas;
import Objetos.ImprimirIngresso;
import Objetos.ReservarIngresso;
import Objetos.SalvarContas;
import Objetos.SalvarIngressos;
import Objetos.VerificarCPF;
import Objetos.VerificarLogin;
import Objetos.ValidadorCpf;



public class Gerenciador{
	
         
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
                                        String nome = campoNome.getText();
                                        String cpf = campoCpf.getText();
                                        String telefone = campoTelefone.getText();
                                        String endereco = campoEndereco.getText();
                                        String nascimento = campoNacimento.getText();

                                        ValidadorCpf validaCpf = new ValidadorCpf();
                                        VerificarCPF verificaCpf = new VerificarCPF();
                                        
                                        if(validaCpf.validar(cpf) && !verificaCpf.verificar(cpf, "arquivoContas.txt")){

                                        Cliente novoCliente = new Cliente(nome , cpf, telefone, endereco, nascimento);
                                        SalvarContas conta = new SalvarContas();
                                        
                                        dadosContass.add(novoCliente);

                                       conta.salvar(dadosContass, arquivoContas);

                                        campoNome.setText("");
                                        campoCpf.setText("");
                                        campoTelefone.setText("");
                                        campoEndereco.setText("");
                                        campoNacimento.setText("");

                                        telaCadastro.dispose();
                                        }else{
                                            if(!validaCpf.validar(cpf)){
                                            JOptionPane.showMessageDialog(telaCadastro, "CPF Inválido");
                                            }else if(verificaCpf.verificar(cpf, "arquivoContas.txt")){
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
            
                    JButton confirmar = new JButton("CONFIRMAR");
            
                    confirmar.setVisible(true);
                    confirmar.setBounds(70, 150, 150, 40);
                    confirmar.setFocusPainted(false);
            
                    confirmar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	
                        	VerificarLogin validarLogin = new VerificarLogin();
                            String cpf1 = campoCpf.getText();
                            String nascimento1 = campoNacimento.getText();

                         if (validarLogin.lerArquivo(cpf1, nascimento1, arquivoContas)) {
                        telaLogin.dispose();
                        telaInicial.dispose();

                        JFrame  telaUsuario = new JFrame("Tela Usuário");
                        telaUsuario.setLayout(null);

                        JButton compra = new JButton("COMPRAR INGRESSO");
            
                            compra.setVisible(true);
                            compra.setBounds(60, 50, 160, 40);
                            compra.setFocusPainted(false);
            
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

                                            ReservarIngresso ingresso = new ReservarIngresso();
                                            SalvarIngressos reserva = new SalvarIngressos();
                                            String valorString = String.valueOf(valor);

                                            if(pecaSelecionada.equals("PEÇA 01") && sessaoSelecionada.equals("MANHÃ")){        
                                                if(ingresso.reservar(poltronaTexto, "Peca1Manha.txt")){                                                       
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);

                                            }else{
                                                JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                            }
                                            }else if(pecaSelecionada.equals("PEÇA 01") && sessaoSelecionada.equals("TARDE")){                                            
                                                if(ingresso.reservar(poltronaTexto, "Peca1Tarde.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 01") && sessaoSelecionada.equals("NOITE")){                                                
                                                if(ingresso.reservar(poltronaTexto, "Peca1Noite.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 02") && sessaoSelecionada.equals("MANHÃ")){                     
                                                if(ingresso.reservar(poltronaTexto, "Peca2Manha.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                   // salvarIngresso(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, totalAtualizado);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 02") && sessaoSelecionada.equals("TARDE")){                      
                                                if(ingresso.reservar(poltronaTexto, "Peca2Tarde.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 02") && sessaoSelecionada.equals("NOITE")){                   
                                                if(ingresso.reservar(poltronaTexto, "Peca2Noite.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 03") && sessaoSelecionada.equals("MANHÃ")){                    
                                                if(ingresso.reservar(poltronaTexto, "Peca3Manha.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 03") && sessaoSelecionada.equals("TARDE")){                  
                                                if(ingresso.reservar(poltronaTexto, "Peca3Tarde.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
                                                }else{
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona ocupada, por favor escolha outro assento.");
                                                }
                                            }else if(pecaSelecionada.equals("PEÇA 03") && sessaoSelecionada.equals("NOITE")){                                              
                                                if(ingresso.reservar(poltronaTexto, "Peca3Noite.txt")){
                                                    JOptionPane.showMessageDialog(comprandoIngresso, "Poltrona Adicionada!" + "\nPeça: " + 
                                                    pecaSelecionada + "\nSessão: " + sessaoSelecionada + "\nÁrea: " + areaSelecionada + "\nPoltrona: " + poltrona + "\nValor da Poltrona: " + valor);
                                                    poltronas.setText("");                                              
                                                    reserva.salvar(cpf1, pecaSelecionada, sessaoSelecionada, areaSelecionada, poltronaTexto, valorString);
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
                                    
                                    ImprimirEstatisticas estatisticas = new ImprimirEstatisticas();
                                    
                                    botaoFinalizar.addActionListener(ev ->{ //ADICIONANDO AÇÃO AO BOTAO FINILZAR
                                    JOptionPane.showMessageDialog(comprandoIngresso, "INGRESSO(S) ADQUIRIDO(S) COM SUCESSO", "PARABÉNS!", 1);
                                        int continuarRetornar = JOptionPane.showConfirmDialog(comprandoIngresso,  "TEM CERTEZA QUE DESEJA FINALIZAR A COMPRA?", "Confirmação", JOptionPane.YES_NO_OPTION);

                                        if(continuarRetornar == JOptionPane.YES_OPTION){ //FINALIZAR COMPRA (PODENDO ADICONAR A AÇÃO DE VOLTAR A TELE INCIAL);
                                            comprandoIngresso.dispose();
                                            estatisticas.Imprimir(cpf1); 

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
                            	ImprimirIngresso ingresso = new ImprimirIngresso();
                                try {
                                    if(ingresso.imprimir(cpf1)){
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
                                	ImprimirEstatisticas estatisticas = new ImprimirEstatisticas();
                                    try {
                                        estatisticas.Imprimir(cpf1);
                                    } catch (NumberFormatException ex) {
                                    	ex.printStackTrace();
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