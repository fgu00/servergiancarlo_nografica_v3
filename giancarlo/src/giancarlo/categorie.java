/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giancarlo;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

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
    
    public categorie(String nome,int tipologia){
     this.nome=nome;
     this.tipologia=tipologia;
    }
     public String getNome(){
        return nome;
     }
     public void setNome(String nome){
         this.nome=nome;
    }
     public void aggiungi_chat(chat a){
        chat.add(a);
     }
     public void accedi(Socket accedi){
      this.accedi=accedi;       
  }  
}
