/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public class Sintatico {
    private ArrayList<Token> tokens;
    private ArrayList<Token> fila;
    ArrayList<String> msg = new ArrayList();

    public Sintatico() {
    }
    
    public Sintatico(ArrayList tokens) {
    this.tokens = tokens;
    this.fila = tokens;
    }

    public ArrayList<String> getMsg() {
        return msg;
    }
    
    public Token getNextToken(){
        Token t = null;
        if(!fila.isEmpty()){
            t = fila.get(0);
        }
        return t;
    }
    
    public void remove(){
        if(!fila.isEmpty())
        fila.remove(0);
        return;
    }
    
    
    public Boolean buscarToken(String token){
        for(Token t: tokens){
            if(t.getLexema().equals(token))
                return true;
        }
        return false;
    }
    
    public Boolean emptyToken(Token t){
        if(t == null){
            msg.add("FIM PILHA");
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
            if(emptyToken(token))return;
            if(token.getLexema().equals(";")){
                remove();
                A();
            }else{
                msg.add("Erro falta de ; proximo a linha "+token.getLinha());
                A();
            }
        }else{
            msg.add("Erro falta o token program proximo a linha"+token.getLinha());
            I();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals(";")){
                remove();
                A();
            }else{
                msg.add("Erro falta de ; proximo a linha "+token.getLinha());
                A();
            }
        }
        msg.add("CONSTRUIDO COM SUCESSO");
    }
    

    private void I(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals("IDENTIFICADOR")){
            remove();
            return;
        }
        else msg.add("Erro falta de identificador proximo a linha "+token.getLinha());
        return;
    }
    
    private void A(){
        B();
        C();
        D();
    }
    
    private void B(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("int") || token.getLexema().equals("real") || token.getLexema().equals("boolean") || token.getLexema().equals("char")){
            remove();
            I();
            E();
            B();
        }//tratar erro
        return;
    }
    
    private void E(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(",")){
            remove();
            I();
            E();
        }//tratar erro
    }
    
    private void C(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("procedure")){
            remove();
            I();
            F();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals(";")){
                remove();
                A();
                token = getNextToken();
                if(emptyToken(token))return;
                if(token.getLexema().equals(";")){
                    remove();
                    C();
                }else{
                    msg.add("Erro, falta ; proximo a linha "+token.getLinha());
                    C();
                }
            }else{
                msg.add("Erro, falta ; proximo a linha "+token.getLinha());
                A();
                token = getNextToken();
                if(emptyToken(token))return;
                if(token.getLexema().equals(";")){
                    remove();
                    C();
                }else{
                    msg.add("Erro, falta ; proximo a linha "+token.getLinha());
                    C();
                }
            }
        }
    }
    
    private void F(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("(")){
            remove();
            G();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals(")")){
                remove();
            }else {
                msg.add("Erro, falta ) proximo a linha "+token.getLinha());
            }
        }
    }
    
    private void G(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("[")){
            remove();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals("var")){
                remove();
                token = getNextToken();
                if(emptyToken(token))return;
                if(token.getLexema().equals("]")){
                    remove();
                    I();
                    E();
                    token = getNextToken();
                    if(emptyToken(token))return;
                    if(token.getLexema().equals(":")){
                        remove();
                        H();
                        K();
                    }else{
                        msg.add("Erro, falta : proximo a linha "+token.getLinha());
                        H();
                        K();
                    }
                }else{
                msg.add("Erro, falta ] proximo a linha "+token.getLinha());
            }
            }else{
                msg.add("Erro, falta var proximo a linha "+token.getLinha());
            }
        }else{
            msg.add("Erro, falta [ proximo a linha "+token.getLinha());
        }
    }
    
    private void H(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("int")||token.getLexema().equals("real")||token.getLexema().equals("boolean")||token.getLexema().equals("char")){
            remove();
        }
    }
    
    private void K(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(";")){
            remove();
            G();
        }
    }
    
    private void D(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("begin")){
            remove();
            L();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals("end")){
                remove();
                return;
            }
        }
    }
    
    private void L(){
        Token token = getNextToken();
        if(emptyToken(token))return;
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
            if(emptyToken(token))return;
            if(token.getLexema().equals("do")){
                remove();
            }else msg.add("Erro falta do");
            L();
            R();
        }else if(token.getLexema().equals("begin")){
            remove();
            L();
            R();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals("end")){
                remove();
            }else msg.add("Erro falta end");
            R();
        }else{//tratar erro
            I();
            M();
            R();
            }
    }
    
    private void M(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("(")){
            remove();
            M();
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals(")")){
                remove();
            }
        }else{
            O();
            if(token.getLexema().equals(":")){
                remove();
                token = getNextToken();
                if(emptyToken(token))return;
                if(token.getLexema().equals("=")){
                    remove();
                    P();
                }
            }
        }
    }
    
    private void O(){
        P();
    }
    
    private void Q(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("else")){
            remove();
            L();
        }
    }
    
    private void R(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(";")){
            remove();
            L();
        }
    }
    
    private void N(){
        P();
        J();
    }
    
    private void J(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(",")){
            remove();
            N();
        }
    }
    
    private void P(){
        T();
        U();
    }
    
    private void T(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("+") || token.getLexema().equals("-")){
            remove();
        }
    }
    
    private void U(){
        Token token = getNextToken();
        if(emptyToken(token))return;
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
    
    private void Z(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals("INTEIRO") || token.getToken().equals("REAL")){
            remove();
            W();
        }
    }
    
    private void W(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(".")){
            remove();
            Z();
        }
        Z();
    }
    
    private void V(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals("*") || token.getToken().equals("div") || token.getToken().equals("and")){
            remove();
            U();
        }
    }
    
    private void X(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals("+") || token.getToken().equals("-") || token.getToken().equals("or")){
            remove();
            U();
        }
    }
    
    private void Y(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals("=")||token.getToken().equals("<>")||token.getToken().equals("<")
        ||token.getToken().equals("<=")||token.getToken().equals(">")||token.getToken().equals(">=")){
            remove();
            U();
        }
    }
    
    private void PP(){
        //msg.add("CONSTRUÍDO COM SUCESSO ");
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals(",")){
            remove();
            P(); 
        }
        //msg.add("CONSTRUÍDO COM SUCESSO ");
    }
    
    

}
