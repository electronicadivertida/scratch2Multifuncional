/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch2Multifuncional;


import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScratchModule {

    private final JTextField moA1;
    private final JTextField moA2;
    private final JTextField moRegulador;
    private final JLabel moOUT;


    public ScratchModule(JTextField poIn1, JTextField poIn2, JTextField poRegulador, JLabel poOut2) throws Exception {
        moA1 = poIn1;
        moA2=poIn2;
        moRegulador=poRegulador;
        moOUT=poOut2;
    }
    
    public int getA1(){
        int lResult=0;
        try{
            lResult = Integer.valueOf(moA1.getText());
        }catch(Exception e){
            
        }

        if(lResult >  100){
            lResult =100;
        }
        if(lResult <  0){
            lResult =0;
        }        
        return lResult;
    }
    public int getA2(){
        int lResult=0;
        try{
            lResult = Integer.valueOf(moA2.getText());
        }catch(Exception e){
            
        }
        if(lResult >  100){
            lResult =100;
        }
        if(lResult <  0){
            lResult =0;
        }
        
        return lResult;
    }
    public int getRegulador(){
        int lResult=0;
        try{
            lResult = Integer.valueOf(moRegulador.getText());
        }catch(Exception e){
            
        }

        if(lResult >  100){
            lResult =100;
        }
        if(lResult <  0){
            lResult =0;
        }        
        return lResult;
    }
    public void write(int psValor){
        moOUT.setText(String.valueOf(psValor));
    }

    public void resetAll() {
        
    }

    public void write(String psPuerto, int pl) {
        ScratchModule.this.write(pl);
    }

    
}
