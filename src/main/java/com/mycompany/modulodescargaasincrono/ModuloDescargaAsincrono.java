package com.mycompany.modulodescargaasincrono;
import Controlador.DescargaController;
import Vista.DescargaForm;

/**
 *
 * @author hectormurillo
 */
public class ModuloDescargaAsincrono {

    public static void main(String[] args) {
             DescargaForm vista = new DescargaForm();             
             DescargaController controller = new DescargaController(vista);
             controller.mostrar();
    }
}
