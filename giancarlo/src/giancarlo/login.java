/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;
import static giancarlo.Giancarlo.persone;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author super
 */
public class login {
   Scanner sc=new Scanner(System.in);
    private Socket accedi;
    private utente a;
    private boolean log ;
    private PrintWriter out;
    private BufferedReader in;

    public login() {
        accedi=new Socket();
        log=false;
        try {
            out=new PrintWriter(accedi.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
                
            
            
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void accedi(Socket clientsocket){
          accedi=clientsocket;
    }
    public void interazioni(){
        try {
            String richiesta=in.readLine();
            String[]m=richiesta.split(":");
            int n=Integer.getInteger(m[0]);
            switch(n){
                case 0:
                String nome=m[1];
                String password=m[2];
                String mail=m[3];
                this.a=new utente(nome,password,mail,"b");
                boolean acesso=false; 
                while(!acesso){
                    //da mettere la mail    
               }
                break;
                case 1:
                    //da fare quando sara implementato il salvattaggio
                    break;
                case 2:
                    //per creare un nuovo canale
                    String indirizzo=m[1];
                    String nome2=m[2];
                    canale nuovo=new canale();
                    break;
                case 3:
                    //per creare una categoria
                    String indirizzo2=m[1];
                    String indirizzo_canale=m[2];
                    String nome3=m[3];
                    categorie nuova=new categorie(nome3);
                    break;
                case 4:
                    //per creare un caht da finire
                    String indirizzo4=m[1];
                    String nome5=m[2];
                    String nome_chat=m[3];
                    String tipologia=m[4];
                    chat c=new chat(nome5);
                    break;
                case 5:
                    //per inviate una persona in un canale
                   String indirizzo5=m[1];
                    String indirizzo_2=m[2];
                    String indirizzo_canale2=m[3];
                    break;
                case 6:
                    //per invitare una persona in una chat
                    String indirizzo6=m[1];
                    String indirizzo_3=m[2];
                    String indirizzo_canale3=m[3];
                    String indirizzo_della_chat=m[4];
                    break;
                case 7:
                    //per inviare un messaggio da finire
                    String indirizzo7=m[1];
                    String indirizzo_canale4=m[2];
                    String indirizzo_chat2=m[3];
                    String messaggio=m[4];
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    public boolean accesso_eseguito(){
        return log;
    }
    public utente getUtente(){
        return a;  
    }
    
} 

