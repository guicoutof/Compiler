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
    ArrayList<Tabela> tabelas = new ArrayList();
    private int countTabela = 0;
    private String auxIdentificador;

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
        Tabela tabela = new Tabela("global");
        tabelas.add(tabela);
        if(token.getLexema().equals("program")){
            tabelas.get(0).inserir(new Simbolo("program","",0));
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
            auxIdentificador = token.getLexema();
            remove();
            return;
        }
        else msg.add("Erro, falta de identificador proximo a linha "+token.getLinha());
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
            token = getNextToken();
            if(token.getLexema().equals(";")){
                remove();
                B();
            }
            else{
                msg.add("Erro, falta de ; na linha "+token.getLinha());
                B();
            }
        }//tratar erro
        return;
    }
    
    private void E(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(",")){
            remove();
            I();
            if(!tabelas.get(0).verificar(auxIdentificador)){
                if(!tabelas.get(0).verificar(auxIdentificador)){
                    tabelas.get(countTabela).inserir(new Simbolo(auxIdentificador,"",0));
                }else msg.add("Variavel "+auxIdentificador+" ja declarada");
            }msg.add("Variavel Global "+auxIdentificador+" ja declarada");
            E();
        }//tratar erro
    }
    
    private void C(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("procedure")){
            remove();
            token = getNextToken();
            Tabela tabela = new Tabela(token.getLexema());
            tabelas.add(tabela);
            countTabela++;
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
        if(token.getLexema().equals("var")){
            remove();
                I();
                if(!tabelas.get(0).verificar(auxIdentificador)){
                    if(!tabelas.get(countTabela).verificar(auxIdentificador)){
                        tabelas.get(countTabela).inserir(new Simbolo(auxIdentificador,"",0));
                    }else msg.add("Variavel "+auxIdentificador+" ja declarada");
                }msg.add("Variavel Global "+auxIdentificador+" ja declarada");
                E();
                token = getNextToken();
                if(emptyToken(token))return;
                if(token.getLexema().equals(":")){
                    remove();
                    H();
                    K();
                }else{//quarto if 
                    msg.add("Erro, falta : proximo a linha "+token.getLinha());
                    H();
                    K();
                }
        }else{
            I();
                E();
                token = getNextToken();
                if(emptyToken(token))return;
                if(token.getLexema().equals(":")){
                    remove();
                    H();
                    K();
                }else{//quarto if 
                    msg.add("Erro, falta : proximo a linha "+token.getLinha());
                    H();
                    K();
                }
        }
    }

    
    private void H(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("int")||token.getLexema().equals("real")||token.getLexema().equals("boolean")||token.getLexema().equals("char")){
            tabelas.get(countTabela).declararTipo(token.getLexema());
            remove();
        }else{
            msg.add("Erro, falta declarar tipo proximo a linha "+token.getLinha());
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
            }else{
                msg.add("Erro, falta end proximo a linha "+token.getLinha());
            }
        }else{
            msg.add("Erro, falta begin proximo a linha "+token.getLinha());
            L();
            if(token.getLexema().equals("end")){
                remove();
                return;
            }else{
                msg.add("Erro, falta end proximo a linha "+token.getLinha());
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
            }else msg.add("Erro, falta do proximo a linha "+token.getLinha());
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
            }else msg.add("Erro, falta end proximo a linha "+token.getLinha());
            R();
        }else{
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
            }else msg.add("Erro, falta ) proximo a linha "+token.getLinha());  
            
        }else{
            O();
            if(token.getLexema().equals(":=")){
                Simbolo s = tabelas.get(countTabela).buscar(auxIdentificador);
                if(s!=null){
                    s.setValor(token.getLexema());
                    s.setUtilizada(1);
                    if(!s.verificaInteiro()){
                        msg.add("Variavel "+auxIdentificador+" Inteiro proximo a linha "+token.getLinha()+" contem valor Real");
                    }
                }System.out.println("Simbolo nao encontrado");
                msg.add("Variavel "+auxIdentificador+" proximo a linha +"+token.getLinha()+ " nao declarada ");
                remove();
                //token = getNextToken();
                //if(emptyToken(token))return;
               // if(token.getLexema().equals("=")){
                //    remove();
                    P();
                //}else{
                //     msg.add("Erro, falta = proximo a linha "+token.getLinha());
                //     P();
                //}
            }else{
                 msg.add("Erro, falta := proximo a linha "+token.getLinha());
                 Simbolo s = tabelas.get(countTabela).buscar(auxIdentificador);
                if(s!=null){
                    s.setValor(token.getLexema());
                    s.setUtilizada(1);
                }System.out.println("Simbolo nao encontrado");
                msg.add("Variavel "+auxIdentificador+" proximo a linha +"+token.getLinha()+ " nao declarada ");
                 //if(token.getLexema().equals("=")){
                 //   remove();
                 //   P();
                //}else{
                //     msg.add("Erro, falta = proximo a linha "+token.getLinha());
                     P();
                //}
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
            token = getNextToken();
            if(emptyToken(token))return;
            if(token.getLexema().equals(")")){
                remove();
            }else msg.add("Erro, falta ) proximo a linha "+token.getLinha());
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
        }else{ if(token.getToken().equals("IDENTIFICADOR")){
            I();//gambiarra
            if(!tabelas.get(countTabela).verificar(auxIdentificador))
                msg.add("Variavel "+auxIdentificador+" proximo a linha "+token.getLinha()+" nao declarada");
            O();
            V();
            X();
            Y();
            PP();
        }

        }
    }
    
    private void Z(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getToken().equals("INTEIRO") || token.getToken().equals("REAL")){
            remove();
            //W();
        }else{
            msg.add("Erro proximo a linha "+token.getLinha());
            //W();
        }
    }
    
//    private void W(){
//        Token token = getNextToken();
//        if(emptyToken(token))return;
//        if(token.getLexema().equals(".")){
//            remove();
//        }
//        Z();
//    }
    
    private void V(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("*") || token.getLexema().equals("div") || token.getLexema().equals("and")){
            remove();
            U();
        }
    }
    
    private void X(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("+") || token.getLexema().equals("-") || token.getLexema().equals("or")){
            remove();
            U();
        }
    }
    
    private void Y(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals("=")||token.getLexema().equals("<>")||token.getLexema().equals("<")
        ||token.getLexema().equals("<=")||token.getLexema().equals(">")||token.getLexema().equals(">=")){
            remove();
            U();
        }
    }
    
    private void PP(){
        Token token = getNextToken();
        if(emptyToken(token))return;
        if(token.getLexema().equals(",")){
            remove();
            P(); 
        }
    }
    
    

}
