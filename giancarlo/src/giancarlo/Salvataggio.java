/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import static giancarlo.Giancarlo.canali_tutti;
import static giancarlo.Giancarlo.persone;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author russo.salvatore
 */
public class Salvataggio implements Runnable{
private File file;

    public Salvataggio() throws IOException {
         file=new File("C:\\Users\\super\\OneDrive\\Documenti\\discosales\\salvataggio.ser");
         //file=new File("C:\\Users\\super\\Documenti\\discosales\\salvataggio.ser");
        if(file.exists()==false){
            file.createNewFile();
        }
    }
    
     private void salva() throws InterruptedException{
try {
System.out.println(" ");
FileOutputStream preout = new FileOutputStream(new File("salvataggio.ser"));
ObjectOutputStream out = new ObjectOutputStream(preout);
out.writeObject(persone);
out.writeObject(canali_tutti);
out.flush();
out.close();
preout.close();
}catch (Exception e) {
}
}

    @Override
    public void run() {
       while(true){
           try {
               this.salva();
               Thread.sleep(5000);
           } catch (InterruptedException ex) {
               Logger.getLogger(Salvataggio.class.getName()).log(Level.SEVERE, null, ex);
           }
       } 
    }

}
