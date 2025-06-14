package view;

import dao.ComprovanteDAO;
import entity.Comprovante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.*;
import java.util.List;

public class TelaComprovantes extends JFrame {

    @SuppressWarnings("FieldMayBeFinal")
    private JTable tabela;
    @SuppressWarnings("FieldMayBeFinal")
    private DefaultTableModel modelo;
    private List<Comprovante> listaComprovantes;

    public TelaComprovantes(String cpf) {
        setTitle("Comprovantes de Compra");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(new String[]{"ID", "Peça", "Sessão", "Área", "Poltronas", "Valor"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton btnImprimir = new JButton("Imprimir Selecionado");
        btnImprimir.addActionListener(e -> imprimirSelecionado());

        carregarComprovantes(cpf);

        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelInferior.add(btnImprimir);

        add(scroll, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void carregarComprovantes(String cpf) {
        ComprovanteDAO dao = new ComprovanteDAO();
        listaComprovantes = dao.listarComprovantesPorCpf(cpf);

        modelo.setRowCount(0); // limpa
        for (Comprovante c : listaComprovantes) {
            modelo.addRow(new Object[]{
                    c.getId(),
                    c.getPeca(),
                    c.getSessao(),
                    c.getArea(),
                    c.getPoltronas(),
                    String.format("R$ %.2f", c.getValor())
            });
        }
    }

    private void imprimirSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um comprovante para imprimir.");
            return;
        }

        Comprovante c = listaComprovantes.get(linha);
        String texto = gerarTextoComprovante(c);

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Impressão de Comprovante");

        job.setPrintable((Graphics g, PageFormat pf, int pageIndex) -> {
            if (pageIndex > 0) return Printable.NO_SUCH_PAGE;

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());

            Font font = new Font("Monospaced", Font.PLAIN, 12);
            g.setFont(font);
            int y = 20;
            for (String linhaTexto : texto.split("\n")) {
                g.drawString(linhaTexto, 10, y);
                y += 15;
            }

            return Printable.PAGE_EXISTS;
        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao imprimir: " + ex.getMessage());
            }
        }
    }

    private String gerarTextoComprovante(Comprovante c) {
        return """
               === COMPROVANTE DE COMPRA ===
               ID: """ + c.getId() + "\n"
                + "CPF: " + c.getCpf() + "\n"
                + "Peça: " + c.getPeca() + "\n"
                + "Sessão: " + c.getSessao() + "\n"
                + "Área: " + c.getArea() + "\n"
                + "Poltronas: " + c.getPoltronas() + "\n"
                + "Valor: R$ " + String.format("%.2f", c.getValor()) + "\n"
                + "==============================";
    }
}
