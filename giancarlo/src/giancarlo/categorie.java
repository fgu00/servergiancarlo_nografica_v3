/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import static giancarlo.Giancarlo.bw;
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
    private int id_canale;
    
    public categorie(String nome,int id){
     this.nome=nome;
     this.id_canale=id;
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
                          chat c=new chat(m[1],id_canale);
                           c.setTipologia(Integer.parseInt(m[2]));
                           chat.add(c);
                           break;
                     case 3:
                         //eliminare chat
                         chat ch=null;
                         for (int i = 0; i < chat.size(); i++) {
                     ch=(chat)chat.get(i);
                     bw.println(i+" per accedere alla chat "+ch.getNome());
                     bw.flush();
                    }
                         String comado=in.readLine();
                         chat.remove(Integer.parseInt(comado));
                         out.println("chat eliminata con sucesso");
                         break;
                     case 4:
                         //accedere ad una chat
                         chat a = null ;
                    int posizione=0;
                    for (int i = 0; i < chat.size(); i++) {
                     a=(chat)chat.get(i);
                     bw.println(i+" per accedere alla chat "+a.getNome());
                     bw.flush();
                    }
                    String ac=in.readLine();
                    a=(chat)chat.get(Integer.parseInt(ac));
                    posizione=Integer.parseInt(ac);
                    a.acesso(accedi);
                   Thread b=new Thread(a);
                    b.start();
                    chat.setElementAt(a,posizione);
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
