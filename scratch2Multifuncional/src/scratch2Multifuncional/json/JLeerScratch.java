/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch2Multifuncional.json;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.json.*;

/**
 *
 * @author eduardo
 */
public class JLeerScratch {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    public  static final int mclMAXPasos = 130;
    public  static final int mclMAXbytes =  400;
    
    private static final String mcsobjName="objName";
    private static final String mcschildren="children";
    private static final String mcsvariables="variables";
    public  static final String mcsvariablesred="v";
    private static final String mcsscripts="scripts";
    public  static final String mcsscriptsred="s";
    
    public  static final String mcsA1 = "a1";
    public  static final String mcsA2 = "a2";
    public  static final String mcsOUT = "out";
    
    
    private static final String mcsdigitalRead="littleBitsMulti.read";
    public  static final String mcsdigitalReadred="dR";
    private static final String mcswrite="littleBitsMulti.write";
    public  static final String mcswritered="wr";
    private static final String mcsregulator="littleBitsMulti.regulator";
    public  static final String mcsregulatorred="regu";
    private static final String mcsreadVariable="readVariable";
    public  static final String mcsreadVariablered="rV";
    private static final String mcssetVar="setVar:to:";
    public  static final String mcssetVarred="sV";
    private static final String mcswait="wait:elapsed:from:";
    public  static final String mcswaitred="w";
    
    private static final String mcsdoIfElse="doIfElse";
    public  static final String mcsdoIfElsered="IfE";
    private static final String mcsdoIf="doIf";
    public  static final String mcsdoIfred="If";
    
    private static final String mcsdoForever="doForever";
    public  static final String mcsdoForeverred="for";
    private static final String mcsdoRepeat="doRepeat";
    public  static final String mcsdoRepeatred="rep";
//    private static final String mcsdoWaitUntil="doWaitUntil";
    private static final String mcsdoUntil="doUntil";
//    public  static final String mcsdoWaitUntilred="waitUntil";
    public  static final String mcsdoUntilred="rU";
            
    private static final String mcsrandomFrom="randomFrom:to:";
    public static final String mcsrandomFromred="ran";
    //operadores
    public  static final String mcsigual="=";
    public  static final String mcsmayor=">";
    public  static final String mcsmenor="<";
    public  static final String mcsmas="+";
    public  static final String mcsmenos="-";
    public  static final String mcspor="*";
    public  static final String mcsentre="/";
    public  static final String mcsY="&";
    public  static final String mcsO="|";
    public  static final String mcsnot="not";
    public  static final String mcsnotred="!";
    public  static final String mcsmod="%";
    private  static final String mcsrounded="rounded";
    public  static final String mcsroundedred="rou";
    public  static final String mcscomputeFunction="computeFunction:of:";
    private  static final String mcssqrt="sqrt";
    public  static final String mcssqrtred="sq";
    
    
    
    //elimininar, validos
    private static final String mcsshowVariable="showVariable";
    private static final String mcshideVariable="hideVariable";
    //comienzo, eliminar validos
    private static final String mcswhenKeyPressed="whenKeyPressed";
    private static final String mcswhenGreenFlag="whenGreenFlag";
    private static final String mcswhenSensorGreaterThan="whenSensorGreaterThan";
    private static final String mcswhenIReceive="whenIReceive";
    private static final String mcswhenClicked="whenClicked";
    private static final String mcswhenSceneStarts="whenSceneStarts";
    public static final String mcsSerialInicio="\nini\n";

    String[] masCompletos = new String[]{
    
    mcsdigitalRead,
    mcsregulator,
    mcsreadVariable,
    mcswrite,
    mcssetVar,
    mcswait,
    
    mcsdoIfElse,
    mcsdoIf,
    
    mcsdoForever,
    mcsdoRepeat,
//    mcsdoWaitUntil,
    mcsdoUntil,
            
    mcsrandomFrom,
    //operadores
    mcsigual,
    mcsmayor,
    mcsmenor,
    mcsmas,
    mcsmenos,
    mcspor,
    mcsentre,
    mcsY,
    mcsO,
    mcsnot,
    mcsmod,
    mcsrounded,
    mcscomputeFunction,
    mcssqrt,
    
    
    //elimininar, validos
    mcsshowVariable,
    mcshideVariable,
    //comienzo, eliminar validos
    mcswhenKeyPressed,
    mcswhenGreenFlag,
    mcswhenSensorGreaterThan,
    mcswhenIReceive,
    mcswhenClicked,
    mcswhenSceneStarts,
};
    String[] masReducidos = new String[]{

    mcsdigitalReadred,
    mcsregulatorred,
    mcsreadVariablered,
    mcswritered,
    mcssetVarred,
    mcswaitred,
    
    mcsdoIfElsered,
    mcsdoIfred,
    
    mcsdoForeverred,
    mcsdoRepeatred,
//    mcsdoWaitUntilred,
    mcsdoUntilred,
            
    mcsrandomFromred,
    //operadores
    mcsigual,
    mcsmayor,
    mcsmenor,
    mcsmas,
    mcsmenos,
    mcspor,
    mcsentre,
    mcsY,
    mcsO,
    mcsnotred,
    mcsmod,
    mcsroundedred,
    mcscomputeFunction,
    mcssqrtred,
    
    
    //elimininar, validos
    "",
    "",
    //comienzo, eliminar validos
    "",
    "",
    "",
    "",
    "",
    "",
    };
    
    //no validos, todos los demas
//    private static final String mcsbroadcast="broadcast";
//    private static final String mcsdoBroadcastAndWait="doBroadcastAndWait";
//    private static final String mcsconcatenate="concatenate:with:";
//    private static final String mcsletter="letter:of:";
//    private static final String mcsstringLength="stringLength:";

    //variables
    private static final String mcsname="name";
    public  static final String mcsnamered="n";
    private static final String mcsvalue="value";
    public  static final String mcsvaluered="v";
    private static final String mcsisPersistent="isPersistent";//eliminar
    
    private final File moFile;
    private ArrayList<JSONObject> moScripts;
    private JSONArray moVariablesGlobales;
    private List moVar;
    public static final String msVar="abcdef";
    private int mlPasos;
    
    public JLeerScratch(File poFile) throws Exception{
        moFile = poFile;
        if(masCompletos.length!=masReducidos.length){
            throw new Exception("Array de tamaños diferentes");
        }
    }
    
    private String leer() throws Exception{
        ZipInputStream zis = new ZipInputStream(new FileInputStream(moFile));
        ZipEntry entrada;
        while (null != (entrada=zis.getNextEntry()) ){
//           System.out.println(entrada.getName());
           if(entrada.getName().indexOf(".json")>=0){
               String lsResult = new String(toByteArray(zis), "UTF-8");
               return lsResult;
           }
        }

        String lsResult = "{\"s\":[[\"If\",[\"&\",[\">\",[\"rV\",\"a\"],[\"rV\",\"b\"]],[\"=\",[\"rV\",\"c\"],0]],[[\"wr\",\"out\",100],[\"sV\",\"b\",[\"%\",[\"+\",[\"rV\",\"b\"],1],5]],[\"sV\",\"a\",0]]],[\"If\",[\"&\",[\"<\",[\"dR\",\"a1\"],50],[\"=\",[\"rV\",\"c\"],1]],[[\"sV\",\"c\",0],[\"sV\",\"a\",[\"+\",[\"rV\",\"a\"],1]],[\"wr\",\"out\",0]]],[\"If\",[\">\",[\"dR\",\"a1\"],49],[[\"sV\",\"c\",1]]]],\"v\":[0,0,0]}";
        throw new Exception("Formato de archivo desconocido");
    }
    
    public void procesar() throws Exception{
//        System.out.println(leer());
        moScripts = new ArrayList<JSONObject>();
        JSONObject results = new JSONObject(leer());
        JSONArray children = results.getJSONArray(mcschildren);
        for(int i = 0 ; i < children.length(); i++){
            JSONObject loS = children.getJSONObject(i);
            try {
//                System.out.println(loS.toString());
                loS.getString(mcsobjName);//debe tener objname
                moScripts.add(loS);
            } catch (Exception ex) {
                
            }
        }
        try {  
            moVariablesGlobales = results.getJSONArray("variables");
        } catch (Exception ex) {    
        }
    }
    
    public String[] getListaScripts(){
        String[] las = new String[moScripts.size()];
        int i = 0;
        for(JSONObject lo : moScripts){
            String lsNombre = lo.getString(mcsobjName);
            las[i]=lsNombre;
            i++;    
        }
        
        return las;
    }
    
    public String getScriptReducido(String psNombre) throws Exception{
        for(JSONObject lo : moScripts){
            String lsNombre = lo.getString(mcsobjName);
            if(lsNombre.equals(psNombre)){
                return getScriptReducidoInterno(lo);
            }    
        }
        throw new Exception("Script " + psNombre + " no existe");
    }
    
    private String getScriptReducidoInterno(JSONObject poScript) throws Exception {
        moVar=new ArrayList<String>();
        
        JSONArray loVarD = new JSONArray();
        JSONArray loScriptD = new JSONArray();
        
        mlPasos = 5;
        //variables
        if(moVariablesGlobales!=null){
            reducirVariables(loVarD, moVariablesGlobales);
        }
        JSONArray loVarLocal=null;
        try {  
            loVarLocal = poScript.getJSONArray(mcsvariables);
        } catch (Exception ex) {    
        }
        if(loVarLocal!=null){
            reducirVariables(loVarD, loVarLocal);
        }
        mlPasos += (moVar.size()*1); 
        //scripts
        JSONArray loScript=null;
        loScript = poScript.getJSONArray(mcsscripts);
        for(int i = 0 ; i < loScript.length(); i++){
            mlPasos++;
            boolean lbContinuar = true;
            JSONArray loS = loScript.getJSONArray(i);
            lbContinuar=loS.length()>=2;
            JSONArray loS2=null;
            if(lbContinuar){
                loS2 = loS.getJSONArray(2);
                lbContinuar=(loS2.length()>=2);
            }
            if(lbContinuar){
                JSONArray loS3 = loS2.getJSONArray(0);
                String lsInicio = loS3.getString(0);
                lbContinuar=(lsInicio.equalsIgnoreCase(mcswhenClicked)
                        || lsInicio.equalsIgnoreCase(mcswhenGreenFlag)
                        || lsInicio.equalsIgnoreCase(mcswhenIReceive)
                        || lsInicio.equalsIgnoreCase(mcswhenKeyPressed)
                        || lsInicio.equalsIgnoreCase(mcswhenSceneStarts)
                        || lsInicio.equalsIgnoreCase(mcswhenSensorGreaterThan)
                        );
            }
            if(lbContinuar){
                loS2 = loS2.getJSONArray(1);
                String lsInicio = loS2.getString(0);
                lbContinuar=(lsInicio.equalsIgnoreCase(mcsdoForever)
                        || lsInicio.equalsIgnoreCase(mcsdoRepeat)
                        || lsInicio.equalsIgnoreCase(mcsdoUntil)
                        );
            }            
            if(lbContinuar){
                loScriptD=(JSONArray) (instruccion(loS2.getJSONArray(1)));
//                for(int l =1; l < loS2.length();l++){
//                    loScriptD.put(instruccion(loS2.getJSONArray(l)));
//                }
            }
        }
        JSONObject loo=new JSONObject();
        loo.put(mcsvariablesred, loVarD);
        loo.put(mcsscriptsred, loScriptD);
        String lsR=loo.toString();
        System.out.println("Nombre:" + moFile.getName() );
        System.out.println("Núm bytes:" + String.valueOf(lsR.length()) );
        System.out.println("Núm pasos:" + String.valueOf(mlPasos) );

        if(lsR.length()>mclMAXbytes || mlPasos>mclMAXPasos){
            throw new Exception(
                    "Script bytes:"+String.valueOf(lsR.length()) + "\n"
                    + "Script instrucciones:"+String.valueOf(mlPasos) + "\n"
                    +" Script supera o el número bytes máximo " + String.valueOf(mclMAXbytes) + " o el número máximo de instrucciones " + String.valueOf(mlPasos) 
            );
        }
        
        return lsR;
    }
    private String getSus(String ps) throws Exception{
        for(int i =0 ; i < masCompletos.length;i++){
            if(masCompletos[i].equals(ps)){
                return masReducidos[i];
            }
        }
        throw new Exception("Operación incorrecta " + ps);
        
    }
    private Object instruccion(Object poInsTr) throws Exception {
        Object loR;
        boolean lbContinuar = true;
        mlPasos++;
        if(poInsTr instanceof JSONArray){
            JSONArray loResult = new JSONArray();
            JSONArray loInsTr = (JSONArray) poInsTr;
            if(loInsTr.get(0) instanceof JSONArray){
                for(int i = 0 ; i < loInsTr.length();i++){
                    loResult.put(instruccion(loInsTr.get(i)));
                }
            }else{
                mlPasos++;
                String lsIns = loInsTr.getString(0);
                if(lbContinuar && lsIns.equals(mcscomputeFunction)){
                    lbContinuar = false;
                    loResult.put(loInsTr.getString(1));
                    loResult.put(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar && lsIns.equals(mcsreadVariable)){
                    lbContinuar = false;
                    loResult.put(getSus(lsIns));
                    loResult.put(getNombreReducido(loInsTr.getString(1)));
                    mlPasos++;
                }
                if(lbContinuar && lsIns.equals(mcssetVar)){
                    lbContinuar = false;
                    loResult.put(getSus(lsIns));
                    loResult.put(getNombreReducido(loInsTr.getString(1)));
                    loResult.put(instruccion(loInsTr.get(2)));
                    mlPasos++;
                }
                if(lbContinuar){
                    loResult.put(getSus(lsIns));
                    for(int i = 1 ; i < loInsTr.length();i++){
                       loResult.put(instruccion(loInsTr.get(i))); 
                    }
                }
            }
            loR=loResult;
        }else{
            loR=getNumS(poInsTr);
            
        }
        
        return loR;
    }
    private Object getNumS(Object poInsTr){
        Object loR;
        if(poInsTr instanceof String){
            if(poInsTr.toString().indexOf(".")>=0){
                try{
                    loR=Double.valueOf(poInsTr.toString().trim());
                }catch(Exception e){
                    loR=poInsTr;
                }
            }else{
                try{
                    loR=Integer.valueOf(poInsTr.toString().trim());
                }catch(Exception e){
                    loR=poInsTr;
                }
            }
        }else{
            loR=poInsTr;
        }
        return loR;
    }

    private String getNombreReducido(String psNombre) throws Exception{
        for(int i = 0 ; i < moVar.size(); i++){
            if(moVar.get(i).equals(psNombre)){
                return String.valueOf(msVar.charAt(i));
            }
        }
        moVar.add(psNombre);
        if(moVar.size()>5){
            throw new Exception("No pueden haber mas de 6 variables");
        }
        return String.valueOf(msVar.charAt(moVar.size()-1));
    }
    //las variables siempre seran, a,b,c,d,e,f y en el mismo orden
    private JSONArray reducirVariables(JSONArray poResulr, JSONArray poV) throws Exception{
        for(int i = 0 ; i < poV.length(); i++){
            JSONObject loS = poV.getJSONObject(i);
//            JSONObject loD = new JSONObject();
//            loD.put(getNombreReducido(loS.getString(mcsname)), getNumS(loS.get(mcsvalue)));
            poResulr.put(getNumS(loS.get(mcsvalue)));
        }
        
        return poResulr;
    }
    
    
    public static long copyLarge(InputStream input, OutputStream output, byte[] buffer)
            throws IOException {
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }
    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
    
    
    public static void main(String [] pas){
        try {
            File loDir = new File("/home/eduardo/Documentos/Scratch Projects");
            String [] lasFicheros = loDir.list();
            for (String ls : lasFicheros) {
                if(ls.indexOf(".sb2")>0){
                    JLeerScratch loLeer =  new JLeerScratch(new File(loDir, ls));
                    loLeer.procesar();
                    PrintStream out = new PrintStream(new File(loDir, ls.substring(0,ls.length()-4)+".txt"));
                    try {
                        out.print(loLeer.getScriptReducido(loLeer.getListaScripts()[0]));
                    } finally {
                        out.close();
                    }
                    
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(JLeerScratch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

