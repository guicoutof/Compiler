/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

/**
 *
 * @author Guilherme
 */
public class Token {
    private String lexema = "";
    private String token = "";
    private int linha = 0;
    private int coluna = 0;
    
    public Token(String lexema, String token,int linha,int coluna) {
        this.lexema = lexema;
        this.token = token;
        this.linha = linha;
        this.coluna = coluna;

    }

    Token() {
        
    }

    public String getLexema() {
        return lexema;
    }

    public String getToken() {
        return token;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
    
    
    
    public String exibir(){
        String msg = "";
        msg = "Lexema : " + lexema + "\t Token : " + token;
        return msg;
    }
    
    
    
    
}
