/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author super
 */
public class categorie {
    private String nome;
    private Vector chat=new Vector();
    private ArrayList<utente>persone=new ArrayList<utente>();
    private Socket accedi;
    private int tipologia;
    private OutputStream oi;
    private ObjectOutputStream oo;
    private PrintWriter out;
    private BufferedReader in;
    
    public categorie(String nome,int tipologia){
     this.nome=nome;
     this.tipologia=tipologia;
    }
     public String getNome(){
        return nome;
     }
  
     public void accedi(Socket accedi) throws IOException{
           this.accedi=accedi;   
            out=new PrintWriter(accedi.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
             oi = accedi.getOutputStream();
             oo = new ObjectOutputStream(oi);
             interazioni();
  } 
     public void interazioni(){
         boolean ciclo=true;
        while(ciclo==true){
             try {
                 String richiesta=in.readLine();
                 String[]m=richiesta.split(":");
                 int n=Integer.getInteger(m[0]);
                 switch(n){
                     case 1:
                         //cambio nome
                        this.nome=m[1];
                         break;
                     case 2:
                         //aggiungi chat
                          chat c=new chat(m[1]);
                           c.setTipologia(Integer.parseInt(m[2]));
                           chat.add(c);
                           break;
                     case 3:
                         //eliminare chat
                         chat.remove(Integer.parseInt(m[1]));
                         out.write("");
                         break;
                     case 4:
                         //accedere ad una chat
                         chat a = null;
                    int posizione=0;
                    for (int i = 0; i < chat.size(); i++) {
                         a=(chat)chat.get(i);
                        if(Integer.parseInt(m[1])==a.indirizzo()){
                            a.acesso(accedi);
                            posizione=i;
                        }
                    }
                    Thread b=new Thread(a);
                    b.start();
                    chat.setElementAt(a,posizione);
                    break;
                     case 5:
                         //per uscire 
                         ciclo=false;
                         break;
                 }
                 } catch (IOException ex) {  
                 Logger.getLogger(categorie.class.getName()).log(Level.SEVERE, null, ex);
             }  
     }
}
}
