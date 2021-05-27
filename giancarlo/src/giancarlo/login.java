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
public class login  implements Runnable{
   Scanner sc=new Scanner(System.in);
    private Socket accedi;
    private int posizione;
    private utente a;
    private boolean log=false;
    private PrintWriter out;
    private BufferedReader in;
    private ArrayList<utente>utenti=new ArrayList();
    private gestione_canali gc=new gestione_canali();
    private InputStream i;
    private ObjectInputStream o;
    private OutputStream oi;
    private ObjectOutputStream oo;

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
              oi = accedi.getOutputStream();
             oo = new ObjectOutputStream(oi);
            log=true;
        } catch (IOException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void interazioni() throws IOException{
        boolean ciclo=true;
        while(ciclo==true){
        try {
            String richiesta=in.readLine();
            System.out.println(richiesta);
            String[]m=richiesta.split(":");
            int n=Integer.parseInt(m[0]);
            switch(n){
                case 0:
                String nome=m[1];
                String password=m[2];
                String mail=m[3];
                String immagine=""+nome.charAt(0);
                this.a=new utente(nome,password,mail,immagine);
                persone.add(a);
                oo.writeObject(a);
                break;
                case 1:
                    //da fare quando sara implementato il salvattaggio
                    System.out.println("jdjdjd");
                    Boolean esiste=false;
                    String nomeu=m[1];
                    String passwordu=m[2];
                    String mailu=m[3];
                    for (int j = 0; j < persone.size(); j++) {
                        if(nomeu.equals(persone.get(j).getNome())&&passwordu.equals(persone.get(j).getPassword())&&mailu.equals(persone.get(j).getMail())){
                            System.out.println(j);
                          a=persone.get(j);
                          posizione=j;
                          esiste=true;
                          break;
                        }
                    }
                    if(esiste==true){
                        oo.writeObject("1");
                        oo.writeObject(persone.get(posizione));
                        System.out.println("u");
                    }else{
                        oo.writeObject("0");
                        System.out.println("mlwdw");
                    }
                    break;
                case 2:
                    //per creare un nuovo canale
                    String nome2=m[1];
                    canale nuovo=new canale(nome2);
                    gc.aggiungicanale(nuovo);
                    a.new_canale(nuovo);
                    oo.writeObject(a);
                    break;
                case 3:
                    //accedi ad un canale
                    String id=m[1];
                    gc.accedi(accedi);
                    gc.accedi_canale(Integer.parseInt(id));
                    break;
                case 4:
                    //eliminare un canale
                    String id2=m[1];
                    gc.elimina_canale(Integer.parseInt(id2));
                    oo.writeObject(a);
                    break;
                case 5:
                      //uscire
                    persone.set(posizione, a);
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

    @Override
    public void run() {
       try { 
           interazioni();
       } catch (IOException ex) {
           Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
} 

