/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;
import static giancarlo.Giancarlo.persone;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.rmi.transport.Transport;
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
    private ArrayList<utente>utenti=new ArrayList();
    gestione_canali gc=new gestione_canali();

    public login() {
        accedi=new Socket();
        log=false;
    }
    public void accedi(Socket clientsocket){
          accedi=clientsocket;
          System.out.println(accedi.getInetAddress());
          try {
            out=new PrintWriter(accedi.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(accedi.getInputStream()));
            interazioni();
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void interazioni(){
        boolean ciclo=true;
        while(ciclo==true){
        try {
            String richiesta=in.readLine();
            System.out.println(richiesta);
            String[]m=richiesta.split(":");
            System.out.println(m[0]);
            int n=Integer.parseInt(m[0]);
            System.out.println(n);
            switch(n){
                case 0:
                String nome=m[1];
                String password=m[2];
                String mail=m[3];
                this.a=new utente(nome,password,mail,"b");
                boolean acesso=false; 
                while(!acesso){
                 Properties p=new Properties();
                 p.put("mail.smtp.auth", "true");
                 p.put("mail.smpt.starttls.enable", mail);
                 //Session s=new Session;
                }
                break;
                case 1:
                    //da fare quando sara implementato il salvattaggio
                    String nomeu=m[1];
                    String passwordu=m[2];
                    String mailu=m[3];
                    String immagineu=m[4];
                    utente ut=new utente(nomeu,passwordu,mailu,immagineu);
                    utenti.add(ut);
                    break;
                case 2:
                    //per creare un nuovo canale
                    String nome2=m[1];
                    canale nuovo=new canale(nome2);
                    gc.aggiungicanale(nuovo);
                    out.write(nuovo.getindirizzo());
                    break;
                case 3:
                    //per creare una categoria
                    String indirizzo2=m[1];
                    String indirizzo_canale=m[2];
                    String nome3=m[3];
                    int tipologia3=Integer.parseInt(m[4]);
                    categorie nuova=new categorie(nome3,tipologia3);
                    break;
                case 4:
                    //per accedere ad un canale
                    gc.accedi(accedi);
                    gc.accedi_canale(Integer.parseInt(m[4]));
                   
                    break;
                case 8:
                    String indirizzo8=m[1];
                    //da mettere una volta fatto il canale dove verra inserita la chat
                    String nome8=m[2];
                    String nome_categotia=m[3];
                    String nome_chat8=m[4];
                    String tipologia8=m[5];
                    chat c1=new chat(nome_chat8);
                    c1.setTipologia(Integer.parseInt(tipologia8));
                    c1.aggiungi_utente(indirizzo8);
                    //mettere la chat dentro un canale attraverso il quale metterlo nella categoria
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
                case 9:
                    ciclo=false;
            }
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    }
    
    public boolean accesso_eseguito(){
        return log;
    }
    public utente getUtente(){
        return a;  
    }
    
} 

