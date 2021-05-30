/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;
import static giancarlo.Giancarlo.bw;
import static giancarlo.Giancarlo.persone;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author super
 */
public class chat implements Runnable{
private String nome;
private ArrayList<Object>messaggi;
private int indirizzo;
private ArrayList<Integer>membri;
private String messaggio;
private Socket accedi;
private PrintWriter out;
private BufferedReader in;
private int tipologia;
private OutputStream oi;
private ObjectOutputStream oo;
private int id_canale;


    public chat(String nome,int id) {
        this.nome=nome;
        id_canale=id;
    }
    public void accedi_privata(Socket accedi) throws IOException{
         in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
        String indirizzoutente=in.readLine();
        for (int i = 0; i <membri.size(); i++) {
        if(indirizzoutente.equals(membri.get(i)))
        this.accedi=accedi;   
        }
    }
    public void accedi_publica(Socket accedi){
        this.accedi=accedi;  
    }
    public void acesso(Socket accedi){
        if(tipologia==1){
            try {
                this.accedi_privata(accedi);
            } catch (IOException ex) {
                Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
            }
              try {
            out=new PrintWriter(accedi.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
            oi = accedi.getOutputStream();
            oo = new ObjectOutputStream(oi);
             interzazioni();
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
         }else{
            this.accedi_publica(accedi);
              try {
            out=new PrintWriter(accedi.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
             oi = accedi.getOutputStream();
             oo = new ObjectOutputStream(oi);
             interzazioni();
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void setTipologia(int a){
      tipologia=a;  
    }

    @Override
    //controlla che ci siano nuovi mesaggi
    public void run() {
     while(true){
         if(!messaggio.equals(messaggi.get(messaggi.size()))){
             try {
                 stampa();
             } catch (IOException ex) {
                 Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
     }
    }
     //serve per stampare tutti i messaggi       
    public void stampa() throws IOException{
        for (int i = 0; i < messaggi.size(); i++) {
          bw.write((String) messaggi.get(i));
          bw.flush();
        }
    }
    //serve per scrivere nella chat
    public synchronized void scrivi(){
        try {  
            String messaggio=in.readLine();
            messaggi.add(messaggio);
        } catch (IOException ex) {
            Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Object> getMessaggi(){
    return null; 
    }
    public void setNome(){
    out.write(nome); 
    }
    public String getNome(){
    return nome;   
    }
    public void getIndirizzo(){
    out.write(indirizzo);  
    }
    public int indirizzo(){
    return indirizzo;   
    }
    public void aggiungi_utente(int a){
        for (int i = 0; i < persone.size(); i++) {
            if(persone.get(i).getIndirizzo()==a){
            for (int j = 0; j < persone.get(i).ncanali(); j++) {
              if(persone.get(i).indirizzo_canale(j)==id_canale){   
                membri.add(persone.get(i).getIndirizzo()); 
            }  
            }
        }
        }
    }
    
    public void elimina_utente(int a) throws IOException{
        membri.remove(a);
    }
    public void getUtente(){
        for (int i = 0; i < 10; i++) {
            System.out.println(membri.get(i));
        }
    }
    public int getTipologia() {
        return tipologia;
    }

    public chat getChat() throws CloneNotSupportedException{
    return (chat) this.clone();  
    }
    public void interzazioni(){
         boolean ciclo=true;
        while(ciclo==true){
             try {
                 String richiesta=in.readLine();
                 String[]m=richiesta.split(":");
                 int n=Integer.getInteger(m[0]);
                 switch(n){
                     case 1:
                       //per cambiare nome
                         nome=m[1];
                         break;
                     case 2:
                        //2 per scrivere un messaggio
                         scrivi();
                         break;
                     case 3:
                         //per stampare
                         stampa();
                         break;
                     case 4:
                         //per aggiungere un utente
                         aggiungi_utente(Integer.parseInt(m[1]));
                         break;
                     case 5:
                         //elimina utente
                          for (int i = 0; i < membri.size(); i++) {
                         bw.println(i+" per eliminare l'utente "+membri.get(i));
                         bw.flush();
                         }
                         String comando=in.readLine();
                         elimina_utente(Integer.parseInt(comando));
                         break;
                     case 6:
                         //per uscire
                         ciclo=true;
                         
                         
                 }} catch (IOException ex) {
                 Logger.getLogger(chat.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
    }
}
