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
    private String valor;
    private int utilizada;
    private String token;
    private String tipo = null;

    public Simbolo() {
    }

    public Simbolo(String token, String valor, int utilizada) {
        this.valor = valor;
        this.utilizada = utilizada;
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    public void setToken(String token) {
        this.token = token;
    }

    public void setUtilizada(int utilizada) {
        this.utilizada = utilizada;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getToken() {
        return token;
    }

    public int getUtilizada() {
        return utilizada;
    }

    public String getValor() {
        return valor;
    }
    
     
     
}
