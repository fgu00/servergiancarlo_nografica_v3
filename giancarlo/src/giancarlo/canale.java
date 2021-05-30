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
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.util.Vector;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author super
 */
public class canale implements Serializable{
        private String nome;
        private int indirizzo;
        private Vector chat=new Vector();
        private Vector categorie=new Vector();
        private ArrayList<utente>persone=new ArrayList<utente>();
        private String immagine="";
        private indirizzo a=new indirizzo();
        private Socket accedi=new Socket();
        private BufferedReader in;
        private PrintWriter out;
public String getNome(){
            return nome;
}
public String getImmagine(){
            return immagine; 
}
    public canale(String nome) throws IOException {
        this.nome = nome;
        this.immagine = immagine;
        indirizzo=a.nuovo_canale();
        chat c=new chat("generale",indirizzo);
        chat.add(c);
        immagine=""+this.nome.charAt(0);
    }
    public void nuova_chat(String nome,int tipologia,int id){
        chat c=new chat(nome,indirizzo);
        c.setTipologia(tipologia);
        c.aggiungi_utente(id);
        chat.add(c);
    }
    public void nuova_categoria(String nome,int tipologia){
        categorie ct=new categorie(nome,tipologia);
        categorie.add(ct);
    }
    public void accedi(Socket s){
      accedi=s;  
         try {
            in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
            out=new PrintWriter(accedi.getOutputStream(),true);
            azione_canale();
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void accedi_chat(int in){
        for (int i = 0; i < chat.size(); i++) {
            chat v=(chat)chat.get(i);
            if(v.indirizzo()==in){
                v.acesso(accedi);
                
                    break;
                }
            }
        }
    public int getindirizzo(){
        return indirizzo;
    }
    public void azione_canale() throws IOException{
        boolean ciclo=true;
        while(ciclo==true){
       String richiesta=in.readLine();
            String[]m=richiesta.split(":");
            int n=Integer.getInteger(m[0]);  
            switch(n){
                case 1:
                    //cambiare nome
                   nome=m[1]; 
                    break;
                case 2:
                   //cambiare immagine
                    immagine=m[1];
                    break;
                case 3:
                    //accedi chat
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
                   Thread b=new Thread(a);
                    b.start();
                    chat.setElementAt(a,posizione);
                    break; 
                case 4:
                    //accedi categoria
                    categorie c=null;
                    int posizione2=0;
                    for (int i = 0; i < categorie.size(); i++) {
                       c=(giancarlo.categorie) categorie.get(i);
                       bw.println(i+" per accedere alla categoria "+c.getNome());
                       bw.flush();
                    }
                    String ab=in.readLine();
                    c=(giancarlo.categorie) categorie.get(Integer.parseInt(ab));
                    posizione=Integer.parseInt(ab);
                    c.accedi(accedi);
                    categorie.setElementAt(ab, posizione2);
                    break;
                case 5:
                    //crea chat
                    nuova_chat(m[1],Integer.parseInt(m[2]),indirizzo);
                    break;
                case 6:
                    //creare categoria
                    nuova_categoria(m[1],indirizzo);
                    break;
                case 7:
                    //eliminare una chat
                    chat a2=null;
                     for (int i = 0; i < chat.size(); i++) {
                     a2=(chat)chat.get(i);
                     bw.println(i+" per eliminare la chat "+a2.getNome());
                     bw.flush();
                    }
                    String elimina=in.readLine();
                    chat.remove(Integer.parseInt(elimina));
                    bw.println("eliminazione completata");
                    bw.flush();
                    break;
                case 8:
                    //eliminare una categoria
                    categorie s=null;
                     for (int i = 0; i < chat.size(); i++) {
                     s=(categorie)categorie.get(i);
                     bw.println(i+" per eliminare la categoria "+s.getNome());
                     bw.flush();
                    }
                    String elimina2=in.readLine();
                    categorie.remove(Integer.parseInt(elimina2));
                    bw.println("eliminazione completata");
                    bw.flush();
                    break;
                case 9:
                   ciclo=false;
                   break;
            }
            }
    }
}


