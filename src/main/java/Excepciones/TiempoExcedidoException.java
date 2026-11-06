
package Excepciones;

/**
 *
 * @author hectormurillo
 */
public class TiempoExcedidoException extends Exception {
    private final int tiempoLimiteMs;
    private final int tiempoTranscurridoMs;
    
    public TiempoExcedidoException(String mensage, int tiempoLimiteMs, int tiempoTranscurridoMs) {
        super(mensage);
        this.tiempoLimiteMs = tiempoLimiteMs;
        this.tiempoTranscurridoMs = tiempoTranscurridoMs;
    }
    
    public int getTiempoLimiteMs() {
        return tiempoLimiteMs;
    }
    
    public int getTiempoTranscurridoMs() {
        return tiempoTranscurridoMs;
    }
        
}
