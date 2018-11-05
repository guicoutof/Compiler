/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;

/**
 *
 * @author kuca
 */
public class Tabela {
    
    String nome;
    ArrayList<Simbolo> tabela = new ArrayList<Simbolo>(); 

    public Tabela() {
    }

    public Tabela(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Simbolo> getTabela() {
        return tabela;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTabela(ArrayList<Simbolo> tabela) {
        this.tabela = tabela;
    }
    
    public void inserir(Simbolo simbolo){
        tabela.add(simbolo);
    }
    
    public boolean verificar(String token){
        for(Simbolo s: tabela) {
            if(s.getToken().equals(token)){
                return true;
            }
        }
        return false;        
    }
    
    public Simbolo buscar(String token){
        for(Simbolo s: tabela) {
            if(s.getToken().equals(token)){
                return s;
            }
        }
        return null;        
    }
    
       
    public void remover(Simbolo simbolo){
        for(Simbolo s: tabela) {
            if(s.getToken().equals(simbolo.getToken())){
                tabela.remove(s);
            }
        }      
    }
    public void declararTipo(String tipo){
        for(Simbolo s: tabela){
            if(s.getTipo() == null)s.setTipo(tipo);
        }
    }
}
