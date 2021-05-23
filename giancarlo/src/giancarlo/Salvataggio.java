/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author russo.salvatore
 */
public class Salvataggio {
    private ArrayList<String>SalvaLog=new ArrayList<String>();

    public Salvataggio() {
    }
    
     private void salva() throws InterruptedException{
try {System.out.println(" ");
FileOutputStream preout = new FileOutputStream(new File("salvataggio.ser"));
ObjectOutputStream out = new ObjectOutputStream(preout);
out.writeObject(SalvaLog);
out.close();
preout.close();
System.out.println("salvataggio avvenuto");
}catch (Exception e) {
System.out.println("salvataggio fallito");
}
Thread.sleep(4000);
}

}
