/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author super
 */
public class gestione_canali {
     private ArrayList<canale>canale=new ArrayList<canale>();
     private Socket accedi=new Socket();
     public gestione_canali(){ 
     }
     public void accedi(Socket accedi){
      this.accedi=accedi;    
     }
     public void aggiungicanale(canale a){
         canale.add(a);
     }
     public void accedi_canale(int a){
         for (int i = 0; i < canale.size(); i++) {
             if(a==canale.get(i).getindirizzo()){
                 canale.get(i).accedi(accedi);
                 break;
             }
         }
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
