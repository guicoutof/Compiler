/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Guilherme
 */
public class Sintatico {
    private ArrayList<Token> tokens;
    private ArrayList<Token> fila;

    public Sintatico() {
    }
    
    public Sintatico(ArrayList tokens) {
    this.tokens = tokens;
    this.fila = tokens;
    }
    
    public Token getNextToken(){
        Token t = fila.get(0);
        //fila.remove(0);
        return t;
    }
    
    public void remove(){
        //Token t = fila.get(0);
        fila.remove(0);
        return ;
    }
    
    
    public Boolean buscarToken(String token){
        for(Token t: tokens){
            if(t.getLexema().equals(token))
                return true;
        }
        return false;
    }
    
    public void analisar(){
        
        Token token = getNextToken();
        if(token.getLexema().equals("program")){
            remove();
            I();
            token = getNextToken();
            if(token.getLexema().equals(";")){
                remove();
                A();
            }else{
                System.out.println("Erro falta de ;");
                A();
            }
        }else{
            System.out.println("Erro falta o token program");
            I();
            token = getNextToken();
            if(token.getLexema().equals(";")){
                remove();
                A();
            }else{
                System.out.println("Erro falta de ;");
                A();
            }
        }
    }
    

    public void I(){
        Token token = getNextToken();

        if(token.getToken().equals("IDENTIFICADOR")){
            remove();
            return;
        }
        else System.out.println("Erro falta de identificador");
    }
    
    public void A(){
        B();
        C();
        D();
    }
    
    public void B(){
        Token token = getNextToken();
        if(token.getLexema().equals("int") || token.getLexema().equals("real") || token.getLexema().equals("boolean") || token.getLexema().equals("char")){
            remove();
            I();
            E();
            B();
        }//tratar erro
        return;
    }
    
    public void E(){
        Token token = getNextToken();
        if(token.getLexema().equals(",")){
            remove();
            I();
            E();
        }//tratar erro
    }
    
    public void C(){
        Token token = getNextToken();
        if(token.getLexema().equals("procedure")){
            remove();
            I();
            F();
            token = getNextToken();
            if(token.getLexema().equals(";")){
                remove();
                A();
                token = getNextToken();
                if(token.getLexema().equals(";")){
                    remove();
                    C();
                }// tratar erro
            }else{
                System.out.println("Erro, falta ;");
            }
        }
    }
    
    public void F(){
        Token token = getNextToken();
        if(token.getLexema().equals("(")){
            remove();
            G();
            token = getNextToken();
            if(token.getLexema().equals(")")){
                remove();
                return;
            }
        }
    }
    
    public void G(){
        Token token = getNextToken();
        if(token.getLexema().equals("[")){
            remove();
            token = getNextToken();
            if(token.getLexema().equals("var")){
                remove();
                token = getNextToken();
                if(token.getLexema().equals("]")){
                    remove();
                    I();
                    E();
                    token = getNextToken();
                    if(token.getLexema().equals(":")){
                        remove();
                        H();
                        K();
                    }
                }
            }
        }
    }
    
    public void H(){
        Token token = getNextToken();
        if(token.getLexema().equals("int")||token.getLexema().equals("real")||token.getLexema().equals("boolean")||token.getLexema().equals("char")){
            remove();
        }
    }
    
    public void K(){
        Token token = getNextToken();
        if(token.getLexema().equals(";")){
            remove();
            G();
        }
    }
    
    public void D(){
        Token token = getNextToken();
        if(token.getLexema().equals("begin")){
            remove();
            L();
            token = getNextToken();
            if(token.getLexema().equals("end")){
                remove();
                return;
            }
        }
    }
    
    public void L(){
        Token token = getNextToken();
        if(token.getLexema().equals("if")){
            remove();
            P();
            L();
            Q();
            R();
        }else if(token.getLexema().equals("while")){
            remove();
            P();
            token = getNextToken();
            if(token.getLexema().equals("do")){
                remove();
            }else System.out.println("Erro falta do");
            L();
            R();
        }else if(token.getLexema().equals("begin")){
            remove();
            L();
            R();
            token = getNextToken();
            if(token.getLexema().equals("end")){
                remove();
            }else System.out.println("Erro falta end");
            R();
        }else{//tratar erro
            I();
            M();
            R();
            }
    }
    
    public void M(){
        Token token = getNextToken();
        if(token.getLexema().equals("(")){
            remove();
            M();
            token = getNextToken();
            if(token.getLexema().equals(")")){
                remove();
            }
        }else{
            O();
            if(token.getLexema().equals(":")){
                remove();
                token = getNextToken();
                if(token.getLexema().equals("=")){
                    remove();
                    P();
                }
            }
        }
    }
    
    public void O(){
        P();
    }
    
    public void Q(){
        Token token = getNextToken();
        if(token.getLexema().equals("else")){
            remove();
            L();
        }
    }
    
    public void R(){
        Token token = getNextToken();
        if(token.getLexema().equals(";")){
            remove();
            L();
        }
    }
    
    public void N(){
        P();
        J();
    }
    
    public void J(){
        Token token = getNextToken();
        if(token.getLexema().equals(",")){
            remove();
            N();
        }
    }
    
    public void P(){
        T();
        U();
    }
    
    public void T(){
        Token token = getNextToken();
        if(token.getLexema().equals("+") || token.getLexema().equals("-")){
            remove();
        }
    }
    
    public void U(){
        Token token = getNextToken();
        if(token.getLexema().equals("(")){
            remove();
            P();
            if(token.getLexema().equals(")")){
                remove();
            }
            V();
            X();
            Y();
            PP();
        }else if(token.getLexema().equals("not")){
            remove();
            U();
            V();
            X();
            Y();
            PP();
        }else if(token.getToken().equals("INTEIRO") || token.getToken().equals("REAL")){
            Z();//gambiarra
            V();
            X();
            Y();
            PP();
        }else{
            I();
            O();
            V();
            X();
            Y();
            PP();
        }
    }
    
    public void Z(){
        Token token = getNextToken();
        if(token.getToken().equals("INTEIRO") || token.getToken().equals("REAL")){
            remove();
            W();
        }
    }
    
    public void W(){
        Token token = getNextToken();
        if(token.getLexema().equals(".")){
            remove();
            Z();
        }
        Z();
    }
    
    public void V(){
        Token token = getNextToken();
        if(token.getToken().equals("*") || token.getToken().equals("div") || token.getToken().equals("and")){
            remove();
            U();
        }
    }
    
    public void X(){
        Token token = getNextToken();
        if(token.getToken().equals("+") || token.getToken().equals("-") || token.getToken().equals("or")){
            remove();
            U();
        }
    }
    
    public void Y(){
        Token token = getNextToken();
        if(token.getToken().equals("=")||token.getToken().equals("<>")||token.getToken().equals("<")
        ||token.getToken().equals("<=")||token.getToken().equals(">")||token.getToken().equals(">=")){
            remove();
            U();
        }
    }
    
    public void PP(){
        Token token = getNextToken();
        if(token.getToken().equals(",")){
            remove();
            P();
        }
    }
    
    

}
