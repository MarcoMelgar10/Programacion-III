package AplicacionImagen.operaciones;

import AplicacionImagen.excepcion.ExepcionImagen;
import AplicacionImagen.Imagen;

/**
 * Toda operacion a realizar debe implementar esta clase
 */
public interface OperacionImagen {
    void hacer(Imagen imagen, ParametrosOperacion parametros)throws ExepcionImagen;
}
