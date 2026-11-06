

/**
 *
 * @author hectormurillo
 */
package Controlador;

import Excepciones.TiempoExcedidoException;
import Modelo.ArchivoDescarga;
import Vista.DescargaForm;

public class DescargaController implements ArchivoDescarga.Listener {

    private final DescargaForm vista;
    private ArchivoDescarga modelo;
    private Thread hiloDescarga;

    public DescargaController(DescargaForm vista) {
        this.vista = vista;
        this.vista.setController(this);
    }

    public void mostrar() {
        vista.setVisible(true);
    }

    public void iniciarDescarga() {
        String archivo = vista.getNombreArchivo();
        int duracion = vista.getDuracionSimulada();
        int tiempoLimite = vista.getTiempoLimite();

        if (archivo.isEmpty()) {
            vista.mostrarError("Debe indicar un nombre de archivo.");
            return;
        }

        vista.habilitarDescarga(false);
        vista.setProgreso(0);
        vista.setEstado("Descargando...");
        vista.appendLog(">> Iniciando descarga de: " + archivo);
        vista.appendLog("   Duración simulada: " + duracion
                + " ms | Límite: " + tiempoLimite + " ms");

        modelo = new ArchivoDescarga(archivo, tiempoLimite, duracion, this);
        hiloDescarga = new Thread(modelo, "Hilo-Descarga");
        hiloDescarga.start();
    }

    public void cancelarDescarga() {
        if (modelo != null) {
            modelo.cancelar();
            vista.appendLog("   Cancelación solicitada por el usuario.");
        }
    }

    @Override
    public void onProgreso(int porcentaje, int tiempoTranscurridoMs) {
        vista.setProgreso(porcentaje);
        vista.setEstado("Descargando... " + porcentaje + " %");
        vista.setTiempoTranscurrido(tiempoTranscurridoMs);
    }

    @Override
    public void onCompletado(String nombreArchivo) {
        vista.setProgreso(100);
        vista.setEstado("Descarga completada.");
        vista.appendLog("✔ Descarga finalizada: " + nombreArchivo);
        vista.habilitarDescarga(true);
        vista.mostrarInfo("Descarga completada correctamente.");
    }

    @Override
    public void onError(String mensaje) {
        vista.setEstado("Descarga interrumpida.");
        vista.appendLog("✘ " + mensaje);
        vista.habilitarDescarga(true);
    }
    
    @Override
    public void onTiempoExcedido(TiempoExcedidoException ex) {
        vista.setEstado("Tiempo límite excedido.");
        vista.appendLog(String.format(
                "⏱ TiempoExcedidoException: %s (límite=%d ms, transcurrido=%d ms)",
                ex.getMessage(),
                ex.getTiempoLimiteMs(),
                ex.getTiempoTranscurridoMs()));
        vista.habilitarDescarga(true);
        vista.mostrarError(String.format(
                "Se excedió el tiempo límite de descarga.%n"
                        + "Límite: %d ms%n"
                        + "Transcurrido: %d ms",
                ex.getTiempoLimiteMs(),
                ex.getTiempoTranscurridoMs()));
    }
}