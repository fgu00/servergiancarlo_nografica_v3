/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import static giancarlo.Giancarlo.bw;
import static giancarlo.Giancarlo.in;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author super
 */
public class gestione_canali {
     private ArrayList<canale>canale=new ArrayList<canale>();
     private Socket accedi=new Socket();
     private utente a;
     public gestione_canali(){ 
     }
     public void accedi(Socket accedi,utente a){
      this.accedi=accedi;
      this.a=a;
      canale=a.getCanali();
     }
     public void aggiungicanale(canale a){
         canale.add(a);
     }
     public void accedi_canale(int b) throws IOException{
         for (int i = 0; i < canale.size(); i++) {
             bw.write(i+" per accedere al canale "+canale.get(i).getImmagine()+" "+canale.get(i).getNome());
         }
         String comando=in.readLine();
         canale.get(Integer.parseInt(comando)).accedi(accedi);  
         }
     
     public void elimina_canale(int indirizzo){
          for (int i = 0; i < canale.size(); i++) {
             if(indirizzo==canale.get(i).getindirizzo()){
                 canale.remove(i);
                 break;
             }
     }
}
     
}
