
package scratch2Multifuncional.json;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import scratch2Multifuncional.ScratchModule;
/**
 *
 * @author eduardo
 */
public class JMotor implements Runnable {
    
    //si se esta en proceso
    boolean mbProceso;
    //salida/entrada modulo
    ScratchModule moModule;
    
    //variables
    double[] madVariables;

    //para buscar cambios en la entrada
    int a1;
    int a2;
    int regulador;
    boolean mbCambioScript = false;
    
//    //modo wait
//    boolean mbModoWait = false;
//    int mlVeces;
//    int mlVez;
//    int[] malProfundidad = new int[20];
//    int mlProfundidad=-1;
            
    //indica q script no tiene variables ni ramdon ni wait
    boolean mbScriptDinamico=true;
    //script
    String msScript;
    //script parseado
    private JSONArray moScr;
    
    public JMotor(ScratchModule poModule){
        moModule=poModule;
    }
    
    public void setScript(String psScript){
        mbCambioScript = true;
        msScript=psScript;
    }
    
    public void parar(){
        mbProceso=false;
    }
    
    @Override
    public void run(){
        mbProceso=true;
        while(mbProceso){
            try {
                loop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    private boolean comprobarDinamico(Object poInsTr) throws Exception {
        boolean loR=false;
        boolean lbContinuar = true;
        if(poInsTr instanceof JSONArray){
            JSONArray loInsTr = (JSONArray) poInsTr;
            if(loInsTr.get(0) instanceof JSONArray){
                for(int i = 0 ; i < loInsTr.length();i++){
                    loR|=(comprobarDinamico(loInsTr.get(i)));
                }
            }else{
                String lsIns = loInsTr.getString(0);
                if(lbContinuar){
                    loR=loR | lsIns.equalsIgnoreCase(JLeerScratch.mcsrandomFromred);
//                            | lsIns.equalsIgnoreCase(JLeerScratch.mcswaitred)
                            ;
                    for(int i = 1 ; i < loInsTr.length();i++){
                       loR|=(comprobarDinamico(loInsTr.get(i))); 
                    }
                }
            }
            
        }else{
            loR=loR | poInsTr.toString().equalsIgnoreCase(JLeerScratch.mcsrandomFromred);
//                    | poInsTr.toString().equalsIgnoreCase(JLeerScratch.mcswaitred)
                            ;
        }
        
        return loR;
    }
    
    private void loop() throws Exception{
        boolean lbCambio=false;
        if(mbCambioScript){
            mbScriptDinamico = false;
            JSONObject loJSON;
            //variables
            loJSON = new JSONObject(msScript);
            JSONArray loV = loJSON.getJSONArray(JLeerScratch.mcsvariablesred);
            madVariables = new double[loV.length()];
            for(int i = 0 ; i < loV.length(); i++){
                mbScriptDinamico =true;
                
                madVariables[i] = loV.getDouble(i);
                
            }
            //script
            moScr = loJSON.getJSONArray(JLeerScratch.mcsscriptsred);
            if(!mbScriptDinamico){
                mbScriptDinamico =comprobarDinamico(moScr);
            }
        }
        if(mbCambioScript || a1!=moModule.getA1() || a2!=moModule.getA2() || regulador!=moModule.getRegulador()){
            lbCambio=true;
            mbCambioScript=false;
//            mbModoWait = false;
//            mlVeces = 0;
//            mlVez = 0;
            a1=moModule.getA1();
            a2=moModule.getA2();
            regulador=moModule.getRegulador();
//            for(int i = 0 ; i < malProfundidad.length; i++){
//                malProfundidad[i]=-1;
//            }
            
        }
        
        if((mbScriptDinamico || lbCambio) 
//                && !mbModoWait 
                ){
            instruccion(moScr);
        }
//        if(mbModoWait){
//            mlVez++;
//        }
//        if(mlVez>mlVeces){
//            mbModoWait=false;
//            mlVeces=0;
//            mlVez=0;
//        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {}            
    }

    private double getValor(String psVar) throws Exception{
        for(int i = 0; i < madVariables.length;i++){
            if(JLeerScratch.msVar.charAt(i)==psVar.charAt(0)){
                return madVariables[i];
            }
        }
        throw new Exception("Variable desconocida: " + psVar);
            
    }
    private void setValor(String psVar, double pdValor) throws Exception{
        boolean lbCont=true;
        for(int i = 0; i < madVariables.length && lbCont;i++){
            if(JLeerScratch.msVar.charAt(i)==psVar.charAt(0)){
                lbCont=false;
                madVariables[i]=pdValor;
            }
        }
        if(lbCont){
            throw new Exception("Variable desconocida: " + psVar);
        }
    }    
    private double getDouble(Object poDouble){
        if(poDouble instanceof Number){
            return ((Number)poDouble).doubleValue();
        }else{
            return Double.valueOf(poDouble.toString()).doubleValue();
        }
    }
    private Object instruccion(Object poInsTr) throws Exception {
//        mlProfundidad++;
        Object loResult=null;
        boolean lbContinuar = true;
        if(poInsTr instanceof JSONArray){
            JSONArray loInsTr = (JSONArray) poInsTr;
             if(loInsTr.get(0) instanceof JSONArray){
                for(int i = 0 ; i < loInsTr.length() 
//                        && !mbModoWait
                        ; i++){
//                    malProfundidad[mlProfundidad]=i;
                    loResult=(instruccion(loInsTr.get(i)));
                }
            }else{
                String lsIns = loInsTr.getString(0);
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsO)){
                    lbContinuar = false;
                    loResult=(Boolean)instruccion(loInsTr.get(1)) | (Boolean)instruccion(loInsTr.get(2));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsY)){
                    lbContinuar = false;
                    loResult=(Boolean)instruccion(loInsTr.get(1)) & (Boolean)instruccion(loInsTr.get(2));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcssqrtred)){
                    lbContinuar = false;
                    loResult=Math.sqrt(getDouble((instruccion(loInsTr.get(1)))));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsabsred)){
                    lbContinuar = false;
                    loResult=Math.abs(getDouble((instruccion(loInsTr.get(1)))));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsdigitalReadred)){
                    lbContinuar = false;
                    if(loInsTr.getString(1).equalsIgnoreCase(JLeerScratch.mcsA1)){
                        loResult=moModule.getA1();
                    }else{
                        loResult=moModule.getA2();
                    }
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsdoIfred)){
                    lbContinuar = false;
                    if((Boolean)(instruccion(loInsTr.get(1)))){
                        instruccion(loInsTr.get(2));
                    }
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsdoIfElsered)){
                    lbContinuar = false;
                    if((Boolean)(instruccion(loInsTr.get(1)))){
                        instruccion(loInsTr.get(2));
                    }else{
                        instruccion(loInsTr.get(3));
                    }
                }
                if(lbContinuar && (lsIns.equals(JLeerScratch.mcsigual) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) == getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar && (lsIns.equals(JLeerScratch.mcsmenor) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) < getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar && (lsIns.equals(JLeerScratch.mcsmayor) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) > getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsnotred)){
                    lbContinuar = false;
                    loResult=!((Boolean)instruccion(loInsTr.get(1)));
                }

                
                
                if(lbContinuar && (lsIns.equals(JLeerScratch.mcsmas) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) + getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar  && (lsIns.equals(JLeerScratch.mcsentre) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) / getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar 
                        && (lsIns.equals(JLeerScratch.mcsmenos) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) - getDouble(instruccion(loInsTr.get(2)));
                }
                
                if(lbContinuar 
                        && (lsIns.equals(JLeerScratch.mcsmod) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) % getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar 
                        && (lsIns.equals(JLeerScratch.mcspor) ) ){
                    lbContinuar = false;
                    loResult=getDouble(instruccion(loInsTr.get(1))) * getDouble(instruccion(loInsTr.get(2)));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsroundedred)){
                    lbContinuar = false;
                    loResult=Math.round(getDouble(instruccion(loInsTr.get(1))));
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsrandomFromred)){
                    lbContinuar = false;
                    double ld1 = getDouble(instruccion(loInsTr.get(1)));
                    double ld2 = getDouble(instruccion(loInsTr.get(2)));
                    loResult=(Math.random() * (ld2- ld1)) + ld1;
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsreadVariablered)){
                    lbContinuar = false;
                    loResult=getValor(instruccion(loInsTr.get(1)).toString());
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsregulatorred)){
                    lbContinuar = false;
                    loResult=moModule.getRegulador();
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcssetVarred)){
                    lbContinuar = false;
                    setValor(instruccion(loInsTr.get(1)).toString(), getDouble(instruccion(loInsTr.get(2)) ) );
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcswaitred)){
                    lbContinuar = false;
//                    mbModoWait=true;
//                    mlVez=0;
//                    mlVeces = (int)getDouble(instruccion(loInsTr.get(1)))*10;
                    try{
                        Thread.sleep((int)(getDouble(instruccion(loInsTr.get(1)))*1000) );
                    }catch(Exception e){}
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcswritered)){
                    lbContinuar = false;
                    moModule.write(loInsTr.get(2).toString(), Double.valueOf(instruccion(loInsTr.get(2)).toString()).intValue());
                }
               
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsdoRepeatred)){
                    lbContinuar = false;
                    int lMax = (int)getDouble(instruccion(loInsTr.get(1)));
                    for(int i = 0 ; i < lMax; i++ ){
                        instruccion(loInsTr.get(2));
                    }
                }
                if(lbContinuar && lsIns.equals(JLeerScratch.mcsdoUntilred)){
                    lbContinuar = false;
                    loResult=(JLeerScratch.mcsdoUntilred);
                    while( !((Boolean)instruccion(loInsTr.get(1))) ){
                        instruccion(loInsTr.get(2));
                    }
                }
             }
            
        }else{
            loResult=poInsTr;
        }
//        mlProfundidad--;
        return loResult;
    }
    
}


//[
//  [
//      [	
//	["whenGreenFlag"], 
//	  ["doForever",
//		[
//                    ["wait:elapsed:from:", ["littleBitsMulti.regulator"]],
//                    ["doIfElse",
//
//                        ["=", ["readVariable", "aa1"], "100"],
//                        [["setVar:to:", "aa1", 0]],
//                        [["setVar:to:", "aa1", "100"]]
//                    ],
//                    ["littleBitsMulti.write", "out", ["readVariable", "aa1"]]
//                ]
//          ]
//      ]
//   ]
//
//]    