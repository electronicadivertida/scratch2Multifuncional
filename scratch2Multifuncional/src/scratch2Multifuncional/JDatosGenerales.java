/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch2Multifuncional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import scratch2Multifuncional.json.JLeerScratch;

/**
 *
 * @author eduardo
 */
public class JDatosGenerales {
    private JSONObject moJSon;
    private static final String mcsultfichero="ultfichero";
    private static final String mcsFile="scratch2Multifuncional.json";
    
    public JDatosGenerales(){
        try{
            moJSon = new JSONObject(new String(JLeerScratch.toByteArray(new FileInputStream(mcsFile)), "UTF-8"));
        }catch(Throwable e){
            moJSon = new JSONObject();
        }        
    }
    public String getUltFichero(){
        return moJSon.getString(mcsultfichero);
    }
    public void setUltFichero(String psUlt){
        moJSon.put(mcsultfichero, psUlt);
        try {
            guardar();
        } catch (Exception ex) {
            Logger.getLogger(JDatosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void guardar() throws Exception{
        File loFile = new File(mcsFile);
        PrintStream out = new PrintStream(loFile);
        try {
            out.print(moJSon.toString());
        } finally {
            out.close();
        } 
    }
}
