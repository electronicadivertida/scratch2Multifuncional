/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratch2Multifuncional;


import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;

/**
 *
 * @author eduardo
 */
public class ModuleLoad {

    private String msPuerto;
    private SerialPort serialPort;
    
    private boolean connected = false;
    

    public ModuleLoad(String PuertoIdent) throws Exception {
   
            msPuerto = PuertoIdent;
            
    }

    public static String[] getListaPuertos(){
        return SerialPortList.getPortNames();
    }
    
    public synchronized void openPort() throws Exception{
        if(SerialPortList.getPortNames().length>0){
            serialPort = new SerialPort(msPuerto);
            serialPort.openPort();
            


            serialPort.setParams(SerialPort.BAUDRATE_9600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);

            serialPort.addEventListener(new SerialPortEventListener() {        
                public void serialEvent(SerialPortEvent event) {
                    try {
                        if(event.isRXCHAR()){//If data is available
                           if(event.getEventValue() >= 3){//Check bytes count in the input buffer
                           }
                       }
                       else if(event.isCTS()){//If CTS line has changed state
                           if(event.getEventValue() == 1){//If line is ON
                               System.out.println("CTS - ON");
                           }
                           else {
                               closePort();
                               System.out.println("CTS - OFF");
                           }
                       }
                       else if(event.isDSR()){///If DSR line has changed state
                           closePort();
                           if(event.getEventValue() == 1){//If line is ON
                               System.out.println("DSR - ON");
                           }
                           else {
                               System.out.println("DSR - OFF");
                           }
                       }
                    } catch (Throwable err) {
                        err.printStackTrace();
                    }
                }


            });
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask

            try {
                Thread.sleep(3000); // let bootloader timeout
            } catch (InterruptedException e) {
            }
            connected = true;
        }
    }

    public String getPuerto() {
        return msPuerto;
    }    
    public void setPuerto(String psPuerto) throws Exception{
        msPuerto=psPuerto;
        closePort();
        openPort();
    }
    public synchronized void closePort() throws Exception {
        connected = false;
        if (serialPort != null) {
            SerialPort loAux = serialPort;
            serialPort = null;
            loAux.removeEventListener();
            loAux.closePort();
        }
    }

    public synchronized void close() throws Exception {
        closePort();
        
    }
    public synchronized void write(int[] b) throws Exception {
        for(int i = 0; i < b.length; i++){
            serialPort.writeByte((byte)b[i]);
            System.out.print((char)b[i]);
            if(i % 20 == 0){
                Thread.sleep(300);
            }
        }
    }

    public synchronized String read() throws Exception {
        return serialPort.readString();
    }    
    public synchronized boolean isAvailable() throws Exception {
        return serialPort.getInputBufferBytesCount()>0;
    }    

    /**
     * @return the connected
     */
    public synchronized boolean isConnected() {
        return connected;
    }
    
}
