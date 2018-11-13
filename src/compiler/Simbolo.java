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
    private String lexema;
    private String tipo = null;

    public Simbolo() {
    }

    public Simbolo(String lexema, String valor, int utilizada) {
        this.valor = valor;
        this.utilizada = utilizada;
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
    public void setLexema(String token) {
        this.lexema = token;
    }

    public void setUtilizada(int utilizada) {
        this.utilizada = utilizada;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLexema() {
        return lexema;
    }

    public int getUtilizada() {
        return utilizada;
    }

    public String getValor() {
        return valor;
    }
    
    public boolean verificaInteiro(){
        if(tipo.equals("int")){
            float x = Float.parseFloat(valor)-Integer.parseInt(valor);
            if(x!=0f)return false;
        }
        return true;
    }
     
     
}
