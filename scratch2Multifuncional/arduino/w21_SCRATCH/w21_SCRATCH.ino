#include <JsonParser.h>

#include <avr/pgmspace.h>
#include <MemoryFree.h>

#include <EEPROM.h>

//puerto salida
#define SALIDA 9
//puerto entrada1
#define ENTRADA1 A0
//puerto entrada2
#define ENTRADA2 A1
//puerto entrada dinner
#define REGULADOR A3

//puertos entrada seleccion script
#define INTR1 4
#define INTR2 5
#define INTR3 6
#define INTR4 7

using namespace ArduinoJson::Parser;

//tamaño maximo del buffer del json a parsear
const int mclMAXBuffer=350;
//numero maximo de posiciones q puede tener el json a parsear
const int mclMAXPasos=120;

//constantes q pueden tener las acciones del json
const char mcsvariablesred[]="v";
const char mcsscriptsred[]="s";
const char mcsnamered[]="n";
const char mcsvaluered[]="v";
const char mcsA1  []="a1";
const char mcsA2  []="a2";
const char mcswritered[]="wr";
const char mcsdigitalReadred[]="dR";
const char mcsOUT []="out";
const char mcsregulatorred[]="regu";
const char mcsreadVariablered[]="rV";
const char mcssetVarred[]="sV";
const char mcswaitred[]="w";
const char mcsdoIfElsered[]="IfE";
const char mcsdoIfred[]="If";
const char mcsdoForeverred[]="for";
const char mcsdoRepeatred[]="rep";
const char mcsdoUntilred[]="rU";
const char mcsrandomFromred[]="ran";
const char mcsigual[]="=";
const char mcsmayor[]=">";
const char mcsmenor[]="<";
const char mcsmas[]="+";
const char mcsmenos[]="-";
const char mcspor[]="*";
const char mcsentre[]="/";
const char mcsY[]="&";
const char mcsO[]="|";
const char mcsnot[]="!";
const char mcsmod[]="%";
const char mcsroundedred[]="rou";
const char mcssqrt[]="sq";
const char mcsabs[]="abs";


//doubleand
prog_char string_0[] PROGMEM = "{\"s\":[[\"IfE\",[\"&\",[\">\",[\"dR\",\"a1\"],50],[\">\",[\"dR\",\"a2\"],50]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//doubleor
prog_char string_1[] PROGMEM = "{\"s\":[[\"IfE\",[\"|\",[\">\",[\"dR\",\"a1\"],50],[\">\",[\"dR\",\"a2\"],50]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//nand
prog_char string_2[] PROGMEM =  "{\"s\":[[\"IfE\",[\"!\",[\"&\",[\">\",[\"dR\",\"a1\"],50],[\">\",[\"dR\",\"a2\"],50]]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//nor
prog_char string_3[] PROGMEM =  "{\"s\":[[\"IfE\",[\"!\",[\"|\",[\">\",[\"dR\",\"a1\"],50],[\">\",[\"dR\",\"a2\"],50]]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//xor
prog_char string_4[] PROGMEM =  "{\"s\":[[\"IfE\",[\"&\",[\"|\",[\">\",[\"dR\",\"a1\"],50],[\">\",[\"dR\",\"a2\"],50]],[\"!\",[\"&\",[\">\",[\"dR\",\"a1\"],50],[\">\",[\"dR\",\"a2\"],50]]]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//inverter
prog_char string_5[] PROGMEM =  "{\"s\":[[\"wr\",\"out\",[\"-\",100,[\"dR\",\"a1\"]]]],\"v\":[]}";
//latch
prog_char string_6[] PROGMEM =  "{\"s\":[[\"IfE\",[\"&\",[\">\",[\"dR\",\"a1\"],50],[\"=\",[\"rV\",\"a\"],0]],[[\"sV\",\"a\",1],[\"IfE\",[\"=\",[\"rV\",\"b\"],100],[[\"sV\",\"b\",0]],[[\"sV\",\"b\",100]]],[\"wr\",\"out\",[\"rV\",\"b\"]]],[[\"If\",[\"<\",[\"dR\",\"a1\"],50],[[\"sV\",\"a\",0]]]]]],\"v\":[0,0]}";
//pulse
prog_char string_7[] PROGMEM =  "{\"s\":[[\"w\",[\"/\",[\"regu\"],50]],[\"IfE\",[\"=\",[\"rV\",\"a\"],100],[[\"sV\",\"a\",0]],[[\"sV\",\"a\",100]]],[\"wr\",\"out\",[\"rV\",\"a\"]]],\"v\":[100]}";
//random
prog_char string_8[] PROGMEM =  "{\"s\":[[\"w\",[\"/\",[\"dR\",\"a1\"],20]],[\"wr\",\"out\",[\"ran\",1,100]]],\"v\":[]}";
//randomnoise  
prog_char string_9[] PROGMEM =  "{\"s\":[[\"w\",[\"/\",[\"ran\",1,10],50]],[\"wr\",\"out\",[\"ran\",1,100]]],\"v\":[]}";
//threshold
prog_char string_10[] PROGMEM =  "{\"s\":[[\"IfE\",[\">\",[\"dR\",\"a1\"],[\"regu\"]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//threshold2
prog_char string_11[] PROGMEM =  "{\"s\":[[\"IfE\",[\">\",[\"dR\",\"a1\"],[\"dR\",\"a2\"]],[[\"wr\",\"out\",100]],[[\"wr\",\"out\",0]]]],\"v\":[]}";
//timeoutoffo
prog_char string_12[] PROGMEM =  "{\"s\":[[\"IfE\",[\"&\",[\">\",[\"dR\",\"a1\"],50],[\"=\",[\"rV\",\"a\"],0]],[[\"sV\",\"a\",1],[\"wr\",\"out\",0],[\"w\",[\"*\",[\"regu\"],3]],[\"wr\",\"out\",100]],[[\"If\",[\"<\",[\"dR\",\"a1\"],50],[[\"sV\",\"a\",0]]]]]],\"v\":[0]}";
//timeoutonffon
prog_char string_13[] PROGMEM =  "{\"s\":[[\"IfE\",[\"&\",[\">\",[\"dR\",\"a1\"],50],[\"=\",[\"rV\",\"a\"],0]],[[\"sV\",\"a\",1],[\"wr\",\"out\",100],[\"w\",[\"*\",[\"regu\"],3]],[\"wr\",\"out\",0]],[[\"If\",[\"<\",[\"dR\",\"a1\"],50],[[\"sV\",\"a\",0]]]]]],\"v\":[1]}";
  
 
unsigned char mlVarNum=0;
double madVariables[6];
String msVariables="abcdef";

//para buscar cambios en la entrada, entrada1
int a1 = LOW;
//para buscar cambios en la entrada, entrada2
int a2 = LOW;
//para buscar cambios en la entrada, regulador
int regulador = LOW;
//para buscar cambios en la entrada, si ha cambiado la seleccion del script
boolean mbCambioScript = false;

//Script seleccionado
int mlIntr=-1;
        
//indica q script tiene variables o ramdon o wait, por lo q se tiene q procesar el scrip en cada paso
boolean mbScriptDinamico=true;
//indica error en el paseado
boolean mbErrorParse=false;
//Script del json 
JsonValue moScr;
//JSon parseadp
JsonObject moJSON;

//variable de retorno, double
double mdAUXReturn;
//variable de retorno, boolean
unsigned char mbAUXReturn;
//variable de retorno, cadena
char* msAUXReturn;

//buffer del json a parsear
char buffer[mclMAXBuffer];
//numero maximo de posiciones q puede tener el json a parsear
JsonParser<mclMAXPasos> parser;

//indica si se es procesando entrada serie, para guardar en EEPROM
boolean mbSerial=false;
//siguiente posicion a guardar en la EEPROM
int mlPosiSerial=0;

//cuando empieza
int mlPosiSerialInicio=0;
String msSerialInicio="\nini\n";


void setup(){

  pinMode(SALIDA, OUTPUT);
  pinMode(ENTRADA1, INPUT);
  pinMode(ENTRADA2, INPUT);
  pinMode(REGULADOR, INPUT);
  pinMode(INTR1, INPUT);
  pinMode(INTR2, INPUT);
  pinMode(INTR3, INPUT);
  pinMode(INTR4, INPUT);
  analogWrite(SALIDA, LOW);
  Serial.begin(9600);
  
  freeMem("In");
  delay(100);
  randomSeed(8);
}
void serialprint(String psValor){
  if(!mbErrorParse && !mbSerial)
    Serial.print(psValor);
}
void serialprintln(String psValor){
  if(!mbErrorParse && !mbSerial)
    Serial.println(psValor);
}
//comprueba si el script es dinamico (se debe comprobar en cada paso)
boolean comprobarDinamico(ArduinoJson::Parser::JsonValue poInsTr) {
boolean loR=false;
    JsonArray loArray = (JsonArray)poInsTr;
    //serialprintln("comprobarDinamico ");
    if(loArray.success()){
        //serialprintln("array");
        //serialprintln("cd->array");
      
        unsigned char num = loArray.size();;
        JsonValue item0 = loArray[0];
      
        //serialprintln("array despues analizar");
        if(item0.success()){
          for(int i = 0 ; i < num && !loR;i++){
            //serialprint("prima ");
            //serialprintln(i);
            JsonValue item = loArray[i];
            loR|=(comprobarDinamico(item));
          }
        }else{
            String lsIns = String((char *)item0);
            //serialprintln(lsIns);
            //serialprintln("cd->" + lsIns);
            loR=loR || lsIns.equalsIgnoreCase(mcsrandomFromred);
//                    || lsIns.equalsIgnoreCase(mcswaitred);
//            if(loR){
//              serialprintln("Dim.Por:");
//              serialprintln(lsIns);
//            }                    
            for(int i = 1 ; i < num && !loR;i++){
              //serialprint("secun ");
              //serialprintln(i);
              JsonValue item = loArray[i];
              loR|=(comprobarDinamico(item)); 
            }
        }
        
    }else{
        String lsIns = String((char *)poInsTr);
        //serialprintln(lsIns);
        loR=loR || lsIns.equalsIgnoreCase(mcsrandomFromred);
//                || lsIns.equalsIgnoreCase(mcswaitred);
//            if(loR){
//              serialprintln("Dim.Por:");
//              serialprintln(lsIns);
//            }      
    }
    
    return loR;
}
//devuelve valor de una variable del script
double getValor(char* psVar) {
  String lsVar=String(psVar);
  for(int i = 0; i < mlVarNum;i++){
      if(lsVar.charAt(0) == msVariables.charAt(i)){
          return madVariables[i];
      }
  }
  serialprint("READ ERR ");
  serialprintln( lsVar);
  return 0;
}

//establece el valor de una variable del script
void setValor(char* psVar, double pdValor) {
  String lsVar=String(psVar);
    boolean lbCont=true;
    //serialprint("setValor ");
    //serialprint(lsVar);
    //serialprint(" valor ");
    //serialprint(pdValor);
    //serialprint(" ");
    for(int i = 0; i < mlVarNum && lbCont;i++){
        //serialprint(" posi ");
        //serialprint(i);
        if(lsVar.charAt(0) == msVariables.charAt(i)){
            //serialprint(" encon ");
            //serialprintln(lsVar);
            lbCont=false;
            madVariables[i]=pdValor;
        }
    }
    if(lbCont){
      serialprint("SET ERR ");
      serialprintln( lsVar);
    }
}  
//devuelve variable de retorno
double getDouble(){
      return mdAUXReturn;     
}
//devuelve variable de retorno
boolean getBool(){
      return mbAUXReturn;     
}
//devuelve variable de retorno
char* getString(){
  return msAUXReturn;
}
//proceso principal del script
void instruccion(ArduinoJson::Parser::JsonValue poInsTr) {
    boolean lbContinuar = true;
    /*serialprintln("instr");
    */
    JsonArray loArray = poInsTr;
    if(loArray.success()){
       //serialprintln("array");
        unsigned char num = loArray.size();
        JsonArray item0 = loArray[0];
        //serialprintln("array despues analizar ");
        //serialprint("array ");
        //serialprintln((int)num);
                
        if(item0.success()){
          //serialprint("arr o ");
          for(int i = 0 ; i < num;i++){
            //serialprintln(i);
            instruccion(loArray[i]);
          }
        }else{
            String lsIns = String((char *)((JsonValue)loArray[0]));
            //serialprintln(lsIns);
            if(lbContinuar & lsIns.equals(mcsO)){
                lbContinuar = false;
                instruccion(loArray[1]);
                boolean lb1=getBool();
                instruccion(loArray[2]);
                boolean lb2=getBool();
                mbAUXReturn=lb1 || lb2;
            }
            if(lbContinuar && lsIns.equals( mcsY)){
                //serialprint(lsIns);
                lbContinuar = false;
                instruccion(loArray[1]);
                boolean lb1=getBool();
                instruccion(loArray[2]);
                boolean lb2=getBool();
                mbAUXReturn=lb1 && lb2;
                //serialprint(String(lb1));
                //serialprint(" ");
                //serialprint(String(lb2));
                //serialprint("=");
                //serialprintln(String(moAUXNum->valuebool));            
              }
            if(lbContinuar && lsIns.equals(mcsnot)){
                lbContinuar = false;
                instruccion(loArray[1]);
                boolean lb1=!getBool();
                mbAUXReturn=lb1;
                //serialprintln(lsIns + String(lb1) + "=" + String(moAUXNum->valuebool));
            }
            if(lbContinuar && (lsIns.equals(mcsigual) ) ){
                lbContinuar = false;
                //serialprintln("=antes");
                instruccion(loArray[1]);
                double lb1=getDouble();
                //serialprintln("=desp 1");
                instruccion(loArray[2]);
                double lb2=getDouble();
                mbAUXReturn=lb1 == lb2;
                //serialprint(String(lb1));
                //serialprint(lsIns);
                //serialprint(String(lb2));
                //serialprint("=");
                //serialprintln(String(mbAUXReturn));                  
            }
            if(lbContinuar && (lsIns.equals(mcsmenor) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                mbAUXReturn=lb1 < lb2;
            }
            if(lbContinuar && (lsIns.equals(mcsmayor) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                mbAUXReturn=lb1 > lb2;
            }
            if(lbContinuar && lsIns.equals(mcsdigitalReadred)){
                //serialprint(lsIns);
                lbContinuar = false;
                //serialprint(" ");
                String lsPuerto = String((char*)loArray[1]);
                //serialprint(lsPuerto);
                if(lsPuerto.equals(mcsA1)){
                  //serialprint(" A1 ");
                  //serialprintln( String(ENTRADA1));
                    mdAUXReturn=(readAnalogPort(ENTRADA1));
                }else{
                  //serialprint(" A2 ");
                  //serialprintln(String(ENTRADA2));
                    mdAUXReturn=(readAnalogPort(ENTRADA2));
                }
                //serialprintln(mdAUXReturn);
            }
            if(lbContinuar && lsIns.equals(mcsregulatorred)){
                lbContinuar = false;                    
                //serialprint(" REGU ");
                mdAUXReturn=(readAnalogPort(REGULADOR));
            }            
            if(lbContinuar && lsIns.equals(mcswritered)){
                lbContinuar = false;
                instruccion(loArray[2]);
                int lb1 = (int)getDouble();
                writeAnalogPort(SALIDA, lb1);
                //serialprintln(String(lsIns) + "=" + String(lb1));
            }
            if(lbContinuar && lsIns.equals(mcsdoIfred)){
                lbContinuar = false;
                instruccion(loArray[1]);
                //serialprint(getBool());
                if( getBool()){
                  //serialprintln(" 2");
                  instruccion(loArray[2]);
                }
            }
            if(lbContinuar && lsIns.equals(mcsdoIfElsered)){
                lbContinuar = false;
                instruccion(loArray[1]);
                //serialprint(getBool());
                if(getBool()){
                    //serialprintln(" 2");
                    instruccion(loArray[2]);
                }else{
                    //serialprintln(" 3");
                    instruccion(loArray[3]);
                }
            }
            if(lbContinuar && lsIns.equals(mcssqrt)){
                lbContinuar = false;
                instruccion(loArray[1]);
                mdAUXReturn=sqrt(getDouble());
            }
            if(lbContinuar && lsIns.equals(mcsabs)){
                lbContinuar = false;
                instruccion(loArray[1]);
                mdAUXReturn=abs(getDouble());
            }
            if(lbContinuar && (lsIns.equals(mcsmas) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                mdAUXReturn=lb1+lb2;
            }
            if(lbContinuar  && (lsIns.equals(mcsentre) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                if(lb2==0.0){
                  mdAUXReturn=0;
                }else{
                  mdAUXReturn=lb1 / lb2;
                }
/*                
                serialprint(String(lb1));
                serialprint(lsIns);
                serialprint(String(lb2));
                serialprint("=");
                serialprintln(String(mdAUXReturn));
*/            
            }
            if(lbContinuar && (lsIns.equals(mcsmenos) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                mdAUXReturn=lb1 - lb2;
            }
            
            if(lbContinuar && (lsIns.equals(mcsmod) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                mdAUXReturn=(int)lb1 % (int)lb2;
            }
            if(lbContinuar && (lsIns.equals(mcspor) ) ){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                instruccion(loArray[2]);
                double lb2=getDouble();
                mdAUXReturn=lb1 * lb2;
            }
            if(lbContinuar && lsIns.equals(mcsroundedred)){
                lbContinuar = false;
                instruccion(loArray[1]);
                double lb1=getDouble();
                mdAUXReturn=round(lb1);
            }
            if(lbContinuar && lsIns.equals(mcsrandomFromred)){
                lbContinuar = false;
                instruccion(loArray[1]);
                double ld1 = getDouble();
                instruccion(loArray[2]);
                double ld2 = getDouble();
                double lran=random(100);
                mdAUXReturn=((lran * (ld2- ld1)) + ld1)/100;
                /*
                serialprint(String(ld1));
                serialprint(lsIns);
                serialprint(String(ld2));
                serialprint(" ");
                serialprint(String(lran));
                serialprint("=");
                serialprintln(String(mdAUXReturn));
                */
            }
            if(lbContinuar && lsIns.equals(mcsreadVariablered)){
                lbContinuar = false;
                //serialprint("read ");
                //serialprint(String((char*)loArray[1]));
                instruccion(loArray[1]);
                char* lsVar = getString(); 
                mdAUXReturn= getValor(lsVar);
                //serialprint("read ");
                //serialprint(lsVar);
                //serialprint(" ");
                //serialprintln(mdAUXReturn);
                
            }
            if(lbContinuar && lsIns.equals(mcssetVarred)){
                lbContinuar = false;
                //serialprint("write ");
                //serialprintln(String((char*)loArray[1]));
                instruccion(loArray[1]);
                char* lsName = getString();
                instruccion(loArray[2]);
                double lb = getDouble();
                setValor(lsName, lb);
                //serialprint(String((char*)loArray[1]));
                //serialprint("=");
                //serialprintln((double)loArray[2]);
            }

            if(lbContinuar && lsIns.equals(mcswaitred)){
                lbContinuar = false;
                instruccion(loArray[1]);
                /*serialprint("wait=");
                serialprintln(getDouble());
                */
                delay((int)(getDouble()*1000) );
                
            }
           
            if(lbContinuar && lsIns.equals(mcsdoRepeatred)){
                lbContinuar = false;
                instruccion(loArray[1]);
                int lMax = (int)getDouble();
                for(int i = 0 ; i < lMax; i++ ){
                    instruccion(loArray[2]);
                    instruccion(loArray[1]);
                    lMax = (int)getDouble();
                }
            }
            if(lbContinuar && lsIns.equals(mcsdoUntilred)){
                lbContinuar = false;
                instruccion(loArray[1]);
                while( !(getBool() )){
                    instruccion(loArray[2]);
                    instruccion(loArray[1]);
                }
            }
         }
    }else{
      
      mdAUXReturn=poInsTr;
      msAUXReturn=poInsTr;
      mbAUXReturn=((double)poInsTr)>0;
      //serialprint("H ");
      //serialprint(msAUXReturn);
      //serialprint(" ");
      //serialprintln(mdAUXReturn);
    }

}

void loop()
{
  //lee puertos entrada
  boolean lbCambio=false;
  int lintr1=digitalRead(INTR1);
  int lintr2=digitalRead(INTR2);
  int lintr3=digitalRead(INTR3);
  int lintr4=digitalRead(INTR4);
  
  int lintr=lintr1 + (lintr2*2) + (lintr3*2*2) + (lintr4*2*2*2);
  int la1 = readAnalogPort(ENTRADA1);
  int la2 = readAnalogPort(ENTRADA2);
  int lr = readAnalogPort(REGULADOR);
//  lintr=14;
  mbCambioScript=lintr!=mlIntr;
  

  //si hay un camvio de script lo parsea
  if(mbCambioScript){
      mbErrorParse=false;
      mbScriptDinamico = false;

      mlIntr=lintr;
      
      //serialprintln(getScript(mlIntr));
      
      //freeMem("antes parser");
      moJSON = parser.parse(getScript(mlIntr));
      freeMem("Desp parser");
      if (!moJSON.success()){
        serialprintln("parse ERR");
        mbErrorParse=true;
        return;
      }

      
      //serialprintln("parse OK");
      //variables
      
      JsonArray loV = moJSON[mcsvariablesred]; 
      mlVarNum=loV.size();
      mbScriptDinamico=(mlVarNum>0);
      /*
      serialprintln(mlVarNum);
      */
      for (int i=0; i<mlVarNum; i++)
      {
        madVariables[i] = loV[i];
        //serialprint(i);
        //serialprint("=");
        //serialprintln(madVariables[i]);
      }      
      
      //script
      moScr = moJSON[mcsscriptsred]; 
      if(!mbScriptDinamico){
          mbScriptDinamico =comprobarDinamico(moScr);
      }
      //freeMem("Desp scri");
      
  }  

  //si hay algun cambio en los puertos de entrada los pone modulares y los visualiza para depuracion
  if(mbCambioScript || a1!=la1 || a2!=la2 || regulador!=lr){
      lbCambio=true;
      mbCambioScript=false;
      a1=la1;
      a2=la2;
      regulador=lr;
      /**/
      if(!mbErrorParse){
        Serial.println("E1:" +String(a1));
        Serial.println("E2:" +String(a2));
        Serial.println("reg:" +String(lr));
        Serial.println("sel:" +String(mlIntr));
        Serial.println("din:" +String(mbScriptDinamico));
        freeMem("loop");
      }
      
      /*for (int i=0; i<mlVarNum; i++) {
        serialprint(i);
        serialprint("=");
        serialprintln(madVariables[i]);
      } */ 
      
  }

  //si script dinamico o algun cambio de puerto de entrada, y parseado correctamente
  if((mbScriptDinamico || lbCambio) && !mbErrorParse){
      instruccion(moScr);
  }

  //si hay error parse siembre salida alta  
  if (mbErrorParse) {
    writeAnalogPort(SALIDA, 100);
  }
  
  //proceso de entrada serie, para guardar en eeprom
  while (Serial.available()) {
    char inChar = (char)Serial.read(); 
    //serialprint(inChar);  

    //secuencia inicio
    if(!mbSerial){
      if (inChar == msSerialInicio.charAt(mlPosiSerialInicio)) {
        mlPosiSerialInicio++;
        if(mlPosiSerialInicio>=msSerialInicio.length()){
          //serialprintln("serial");  
          mbSerial = true;
          mlPosiSerialInicio=0;
          inChar = (char)Serial.read();
          if(inChar=='2'){
            mlPosiSerial=mclMAXBuffer+1;
          }else{
            mlPosiSerial=0;
          }
        }
      }else{
        mlPosiSerialInicio=0;
      }
    }else{
      EEPROM.write(mlPosiSerial, inChar);
      mlPosiSerial++;
      //caracter fin \n
      if (inChar == '\n' && mbSerial) {
        Serial.println("Load OK");
        mbSerial = false;
        mlPosiSerial=0;
        mlIntr=-1;//se recarge
      } else if (mlPosiSerial >= (mclMAXBuffer*2+1)){
        Serial.println("Err pasado");
        mlPosiSerial = 0;
        mbSerial = false;
      }
    }
    
  }

  delay(15);
}


int readAnalogPort(int plPin) {
  int value;
  value = analogRead(plPin);
  if (value == 1022) value = 1023;
  value = (value / 1023.0)  * 100.0;//cuidado , el valor de un int no supera los 32000, por lo q value(1023) * 100 > 32000 y da errores
  
  if(value>98){
    value = 100;
  } else if (value<3){
    value = 0;
  }
  return value;
}
void writeAnalogPort(int plPin, int value) {
  serialprint("wr");
  serialprintln(String(value));
  
  if(value>98){
    value = 100;
  } else if (value<3){
    value = 0;
  }  
  value = value * (255.0/100.0) ; //cuidado , el valor de un int no supera los 32000, por lo q 1023 * value(100) > 32000 y da errores
  
  analogWrite(plPin, value);
}
//aJsonStream*
char* getScript(int plScript){
  prog_char* ls;
  boolean lbEEPROM=false;
  int lPosi=0;
  switch (plScript) {
    case 0:
    ls=string_0;
    break;
    case 1:
    ls=string_1;
    break;
    case 2:
    ls=string_2;
    break;
    case 3:
    ls=string_3;
    break;
    case 4:
    ls=string_4;
    break;
    case 5:
    ls=string_5;
    break;
    case 6:
    ls=string_6;
    break;
    case 7:
    ls=string_7;
    break;
    case 8:
    ls=string_8;
    break;
    case 9:
    ls=string_9;
    break;
    case 10:
    ls=string_10;
    break;
    case 11:
    ls=string_11;
    break;
    case 12:
    ls=string_12;
    break;
    case 13:
    ls=string_13;
    break;
//va por default    
//    case 14:
//    //leemos eeprom
//    lbEEPROM=true;
//    lPosi=0;
//    break;
    case 15:
    //leemos eeprom
    lbEEPROM=true;
    lPosi=mclMAXBuffer+1;
    break;
    default:
    //leemos eeprom
    lbEEPROM=true;
    lPosi=0;
  }
  if(lbEEPROM){
    for(int i=0; i < mclMAXBuffer;i++){
      buffer[i]=0;
    }
    char c;
    for(int i=0; i < mclMAXBuffer;i++){
      c = EEPROM.read(lPosi+i);
      if(c==0 || c=='\n') break;
      buffer[i]=c;
      //serialprint(String(c));
      //serialprint(String(lPosi+i));
    } 
  }else{
  //serialprintln(strlen_P(ls));
  //for(int i = 0 ; i < strlen_P(ls);i++){
  //  serialprint((char)pgm_read_byte_near(ls+i));
  //}
  //serialprintln();
  //return aJsonStream(&(string_table[plScript]));
  strcpy_P(buffer, ls); // Necessary casts and dereferencing, just copy.
  }

  return buffer;//(char*)pgm_read_word(&(string_table[plScript]));
}



void freeMem(char* message) {
  serialprint(String(message));
  serialprint("=");
  serialprintln(String(freeMemory()));
}



