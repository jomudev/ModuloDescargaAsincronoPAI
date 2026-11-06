

/**
 *
 * @author hectormurillo
 */
package Vista;

import Controlador.DescargaController;

import javax.swing.*;
import java.awt.*;

public class DescargaForm extends JFrame {

    private JTextField txtArchivo;
    private JTextField txtDuracion;
    private JTextField txtTiempoLimite;
    private JButton btnDescargar;
    private JButton btnCancelar;
    private final String tiempoTranscurridoInitialText = "Tiempo transcurrido: ";
    private JLabel lblTiempoTranscurrido;
    private JProgressBar progressBar;
    private JLabel lblEstado;
    private JTextArea txtLog;

    public DescargaForm() {
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Simulación de Descarga Asíncrona");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(560, 420);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.insets = new Insets(5, 5, 5, 5);
        gridBag.fill = GridBagConstraints.HORIZONTAL;

        gridBag.gridx = 0; gridBag.gridy = 0; gridBag.weightx = 0;
        panelForm.add(new JLabel("Archivo:"), gridBag);
        gridBag.gridx = 1; gridBag.weightx = 1;
        txtArchivo = new JTextField("documento.pdf");
        panelForm.add(txtArchivo, gridBag);

        gridBag.gridx = 0; gridBag.gridy = 1; gridBag.weightx = 0;
        panelForm.add(new JLabel("Duración simulada (ms):"), gridBag);
        gridBag.gridx = 1; gridBag.weightx = 1;
        txtDuracion = new JTextField("8000");
        panelForm.add(txtDuracion, gridBag);

        gridBag.gridx = 0; gridBag.gridy = 2; gridBag.weightx = 0;
        panelForm.add(new JLabel("Tiempo límite (ms):"), gridBag);
        gridBag.gridx = 1; gridBag.weightx = 1;
        txtTiempoLimite = new JTextField("5000");
        panelForm.add(txtTiempoLimite, gridBag);

        add(panelForm, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(5, 5));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        lblTiempoTranscurrido = new JLabel("Tiempo transcurrido: ");
        panelCentro.add(lblTiempoTranscurrido, BorderLayout.WEST);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        panelCentro.add(progressBar, BorderLayout.NORTH);

        txtLog = new JTextArea(8, 40);
        txtLog.setEditable(false);
        panelCentro.add(new JScrollPane(txtLog), BorderLayout.CENTER);

        lblEstado = new JLabel("Listo.");
        panelCentro.add(lblEstado, BorderLayout.SOUTH);

        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnDescargar = new JButton("Descargar");
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnDescargar);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    
    
    

    public String getNombreArchivo() { return txtArchivo.getText().trim(); }

    public int getDuracionSimulada() {
        try { return Integer.parseInt(txtDuracion.getText().trim()); }
        catch (NumberFormatException e) { return 8000; }
    }

    public int getTiempoLimite() {
        try { return Integer.parseInt(txtTiempoLimite.getText().trim()); }
        catch (NumberFormatException e) { return 5000; }
    }
    
    public void setTiempoTranscurrido(int tiempoTranscurridoMs) {
        lblTiempoTranscurrido.setText(tiempoTranscurridoInitialText + tiempoTranscurridoMs / 1000 + " s");
    }

    public JButton getBtnDescargar() { return btnDescargar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void setProgreso(int porcentaje) {
        progressBar.setValue(porcentaje);
        progressBar.setString(porcentaje + " %");
    }

    public void setEstado(String texto) { lblEstado.setText(texto); }

    public void appendLog(String texto) {
        txtLog.append(texto + "\n");
        txtLog.setCaretPosition(txtLog.getDocument().getLength());
    }

    public void habilitarDescarga(boolean habilitar) {
        btnDescargar.setEnabled(habilitar);
        btnCancelar.setEnabled(!habilitar);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }


    public void setController(DescargaController controller) {
        btnDescargar.addActionListener(e -> controller.iniciarDescarga());
        btnCancelar.addActionListener(e -> controller.cancelarDescarga());
    }
}