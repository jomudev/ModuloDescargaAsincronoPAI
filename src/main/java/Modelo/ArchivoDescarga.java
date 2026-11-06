package Modelo;

import Excepciones.TiempoExcedidoException;

/**
 *
 * @author hectormurillo
 */
public class ArchivoDescarga implements Runnable {
    
    
    public interface Listener {
        void onProgreso(int porcentaje, int tiempoTranscurridoMs);
        void onCompletado(String rutaDestino);
        void onError(String mensaje);
        void onTiempoExcedido(TiempoExcedidoException ex);
    }
    
    private final String nombreArchivo;
    private final int tiempoLimiteMs;
    private final int duracionSimuladaMs;
    private final Listener listener;
    private boolean cancelado = false;
    private final boolean finalizado = false;
    
    public ArchivoDescarga(String nombreArchivo, int tiempoLimiteMs, int duracionSimuladaMs, Listener listener) {
        this.nombreArchivo = nombreArchivo;
        this.tiempoLimiteMs = tiempoLimiteMs;
        this.duracionSimuladaMs = duracionSimuladaMs;
        this.listener = listener;
    }
    
    public void cancelar() {
        this.cancelado = true;
    }
    
    public boolean isFinalizado() {
        return finalizado;
    }
    
    @Override 
    public void run() {
        long inicio = System.currentTimeMillis();
        final int pasos = 100;
        int pausaPorPaso = duracionSimuladaMs / pasos;
        System.out.println(pausaPorPaso);
        try {
            for (int i = 1; i <= pasos; i++) {

                Thread.sleep(pausaPorPaso);

                if (cancelado) {
                    listener.onError("Descarga cancelada por el usuario.");
                    return;
                }

                int transcurrido = (int) (System.currentTimeMillis() - inicio);
                
                System.out.println("tiempo tanscurrido " + transcurrido + " tiempo limite " + tiempoLimiteMs);
                if (transcurrido > tiempoLimiteMs) {
                    throw new TiempoExcedidoException(
                            "Se excedió el tiempo límite de descarga.",
                            tiempoLimiteMs,
                            transcurrido);
                    
                }

                listener.onProgreso(i, transcurrido);
            }

            listener.onCompletado(nombreArchivo);

        } catch (TiempoExcedidoException ex) {
            listener.onTiempoExcedido(ex);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            listener.onError("Hilo de descarga interrumpido.");
        } catch (Exception ex) {
            listener.onError("Error inesperado: " + ex.getMessage());
        }
    }
}
