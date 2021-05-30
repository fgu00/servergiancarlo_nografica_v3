/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import static giancarlo.Giancarlo.in;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author super
 */
public class utente {
   private String nome;
    private String password;
    private String mail;
    //da cambiare perche non so come va salvata l'immaguine
    private Object immagine;
    private int indirizzo;
    private indirizzo a;
    private ArrayList<canale>indirizzi_canali=new ArrayList<canale>();
    

    public utente(String nome, String password, String mail, Object immagine) {
        this.nome = nome;
        this.password = password;
        this.mail = mail;
        this.immagine = immagine;
        indirizzo=a.nuovo_utente();
        a=new indirizzo();
    }
    //serve per cambiare il nome del utente
    public void cambio_nome(String nome){
     this.nome=nome;   
    }
    //serve per cambiare la password del utente 
    public void cambio_password(String password){
        this.password=password;
    }
    public void cambio_mail(String mail){
        this.mail=mail;
    }
    //da rivedere perche non so come si fa 
    public void cambio_immagine(Object immagine){
        this.immagine=immagine;
    }
    //attivare questo metodo quando l'utente ha fatto l'identificazione sulla mail
    public void conta(){
        indirizzo=a.nuovo_utente();
    }

    public String getNome() {
        return nome;
    }

    public String getMail() {
        return mail;
    }

    public Object getImmagine() {
        return immagine;
    }

    public int getIndirizzo() {
        return indirizzo;
    }

    public String getPassword() {
        return password;
    }
    
    //serve per visualizzare le notifiche
    public void visualizza_notifiche(){
        
    } 
    public ArrayList<canale> getCanali(){
       return indirizzi_canali;  
    }
    public void new_canale(canale a){
        indirizzi_canali.add(a);
    }
    public void elimina_canale(canale a){
      indirizzi_canali.remove(a);
    }
    public int indirizzo_canale(int a){
       return indirizzi_canali.get(a).getindirizzo();
    }
    public int ncanali(){
        return indirizzi_canali.size();
    }
    public void azioni_utente() throws IOException{
          boolean ciclo=true;
        while(ciclo==true){
            String richiesta=in.readLine();
            String[]m=richiesta.split(":");
             int n=Integer.getInteger(m[0]);
             switch(n){
                 case 1:
                     nome=m[1];
                      break;
                 case 2:
                   password=m[1];
                     break;
                 case 3:
                     mail=m[1];
                     break;
                 case 4:
                     immagine=m[1];
                     break;
                 case 5:
                     ciclo=false;
             }
        }
        }
    }

