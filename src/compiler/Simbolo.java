/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author kuca
 */
public class Simbolo {
    
    private int valor;
    private int utilizada;
    private String token;

    public Simbolo() {
    }

    public Simbolo(int valor, int utilizada, String token) {
        this.valor = valor;
        this.utilizada = utilizada;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUtilizada(int utilizada) {
        this.utilizada = utilizada;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getToken() {
        return token;
    }

    public int getUtilizada() {
        return utilizada;
    }

    public int getValor() {
        return valor;
    }
    
     
     
}
