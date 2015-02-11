/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch2Multifuncional;

/**
 *
 * @author eduardo
 */
public class JDatosGeneralesP {
    private static JDatosGenerales moDatosGenerales;
    
    static{
        moDatosGenerales = new JDatosGenerales();
    }
    public static JDatosGenerales getDatosGenerales(){
        return moDatosGenerales;
    }
}
