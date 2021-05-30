/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import static giancarlo.Giancarlo.canali_tutti;
import static giancarlo.Giancarlo.persone;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
private utente u;
private canale c;

    public Salvataggio() throws IOException, ClassNotFoundException {
         file=new File("C:\\Users\\super\\OneDrive\\Documenti\\discosales\\salvataggio.ser");
         //file=new File("C:\\Users\\super\\Documenti\\discosales\\salvataggio.ser");
        if(file.exists()==false){
            file.createNewFile();
        }else{
           FileInputStream preout = new FileInputStream(file);
           ObjectInputStream out = new ObjectInputStream(preout); 
           String controllo=(String) out.readObject();
           if(controllo.equals("c")){
            int conta=(int) out.readObject();
               for (int i = 0; i < conta; i++) {
                   u= (utente) out.readObject();
                 persone.add(u);
               }
               int conta2=(int) out.readObject();
               for (int i = 0; i < conta2; i++) {
                    c= (canale) out.readObject();
                  canali_tutti.add(c);
               }
        }
        }
    }
    
     private void salva() throws InterruptedException{
try {
FileOutputStream preout = new FileOutputStream(file);
ObjectOutputStream out = new ObjectOutputStream(preout);
out.writeObject("c");
out.writeObject(persone.size());
    for (int i = 0; i < persone.size(); i++) {
     out.writeObject(persone.get(i));   
    }
    out.writeObject(canali_tutti.size());
for (int i = 0; i < canali_tutti.size(); i++) {
     out.writeObject(canali_tutti.get(i));   
    }
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
