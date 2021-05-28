/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author super
 */
public class canale {
        private String nome;
        private int indirizzo;
        private Vector chat=new Vector();
        private Vector categorie=new Vector();
        private ArrayList<utente>persone=new ArrayList<utente>();
        private Object immagine="";
        private indirizzo a=new indirizzo();
        private Socket accedi=new Socket();
        private BufferedReader in;
        private PrintWriter out;

    public canale(String nome) throws IOException {
        this.nome = nome;
        this.immagine = immagine;
        indirizzo=a.nuovo_canale();
        chat c=new chat("generale",indirizzo);
        chat.add(c);
        immagine=this.nome.charAt(0);
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
                case 4:
                    //accedi categoria
                    categorie c=(categorie) categorie.get(Integer.parseInt(m[1]));
                    c.accedi(accedi);
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
                    chat.remove(Integer.parseInt(m[1]));
                    out.write("");
                    break;
                case 8:
                    //eliminare una categoria
                    categorie.remove(Integer.parseInt(m[1]));
                    out.write("");
                    break;
                case 9:
                   ciclo=false;
                   break;
            }
            }
    }
}


