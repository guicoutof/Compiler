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
public class Lexico {
    ArrayList<Token> tokens = new ArrayList();
    ArrayList<String> keyWords = new ArrayList();
    private String Language;
    private String[] SplitLanguage;
    

    public Lexico(String Language) {
        this.Language = Language;
        this.SplitLanguage = Language.split("\n");
        String token;
        token = "program";
        keyWords.add(token);
        token = "procedure";
        keyWords.add(token);
        token = "var";
        keyWords.add(token);
        token = "begin";
        keyWords.add(token);
        token = "end";
        keyWords.add(token);
        token = "if";
        keyWords.add(token);
        token = "then";
        keyWords.add(token);
        token = "else";
        keyWords.add(token);
        token = "while";
        keyWords.add(token);
        token = "do";
        keyWords.add(token);
    }
    
    public boolean isNumber (char number) {
        String fonte = ""+number;
        String regra = "[0-9]";
        Pattern p = Pattern.compile(regra);
        Matcher m = p.matcher(fonte);
        
        return m.matches();
    }
    
    public boolean isLetter (char letter) {
        String fonte = ""+letter;
        String regra = "[a-zA-Z]";
        Pattern p = Pattern.compile(regra);
        Matcher m = p.matcher(fonte);
        
        return m.matches();
    }
    
    public boolean isIdentifier (String word) {
        String regra = "[a-zA-Z]([a-zA-Z]|[0-9])*";
        Pattern p = Pattern.compile(regra);
        Matcher m = p.matcher(word);
        
        return m.matches();
    }
    
    public boolean isKeyWord(String word) {
        for(String t:keyWords){
            if(t.equals(word))
                return true;
        }
        return false;
    }
    
    public ArrayList analisar(){
        String number = "";
        String word = "";
        int fechamento = 0;
        for(int i=0;i<SplitLanguage.length;i++){
            for(int j=0;j<SplitLanguage[i].length()-1;j++){
                
                char atual = SplitLanguage[i].charAt(j);

                /* verifica numero */
                if(isNumber(atual)){
                    char prox = SplitLanguage[i].charAt(j+1);
                    if(isNumber(prox)){
                       number += atual;
                    }   else {
                        if(prox == '.'){
                            number += atual + ".";
                            j++;
                        }
                        else {
                            number += atual;
                            Token token = new Token(number, "NUM",i+1,j+1);
                            tokens.add(token);
                            number = "";
                        }
                    }
                }else   
                if(isLetter(atual)){
                     word +=atual;
                    char prox = SplitLanguage[i].charAt(j+1);
                    while((isLetter(prox) || isNumber(prox)) && j<SplitLanguage[i].length()-1){
                        word += prox;
                        j++;
                        prox = SplitLanguage[i].charAt(j+1);
                        
                    }
                    
                        if(isKeyWord(word)){
                            Token token = new Token(word, "PALAVRA_RESERVADA",i+1,j+1);
                            tokens.add(token);
                            word = "";
                        }else if(isIdentifier(word)){
                            Token token = new Token(word, "IDENTIFICADOR",i+1,j+1);
                            tokens.add(token);
                            word = "";
                        }else {
                            Token token = new Token(word, "ERRO_IDENTIFICADOR",i+1,j+1);
                            tokens.add(token);
                            word = "";
                        }
                    
                }else
                if(atual == '+'){
                    Token token = new Token("+", "OPSOMA",i+1,j+1);
                    tokens.add(token);
                }else
                if(atual == '-'){
                    Token token = new Token("-", "OPSUB",i+1,j+1);
                    tokens.add(token);
                }else
                if(atual == '*'){
                    Token token = new Token("*", "OPMUL",i+1,j+1);
                    tokens.add(token);
                }else
                if(atual == '/'){
                    Token token = new Token("/", "OPDIV",i+1,j+1);
                    tokens.add(token);
                }else
                if(atual == '('){
                    Token token = new Token("(", "AP",i+1,j+1);
                    tokens.add(token);
                    fechamento++;
                }else
                if(atual == ')'){
                    Token token = new Token(")", "FP",i+1,j+1);
                    tokens.add(token);
                    fechamento--;
                }else
                if(atual == ' '){

                }else
                if(atual == ';'){

                }else
                if(atual == '\r'){

                }
                else
                if(atual == '/'){
                    char prox = SplitLanguage[i].charAt(j+1);
                    if(prox == '/'){
                        i++;
                        j=0;//proxima linha
                    }

                }
                else{
                    Token token = new Token("ERRO", "CARACTER DESCONHECIDO",i,j+1);
                    tokens.add(token);
                }  
            }    
        }
        if(fechamento>0){
            Token token = new Token("ERRO", "FECHAR PARENTESES",SplitLanguage.length,SplitLanguage[SplitLanguage.length-1].length());
            tokens.add(token);}
        if(fechamento<0){
            Token token = new Token("ERRO", "ABRIR PARENTESES",SplitLanguage.length,SplitLanguage[SplitLanguage.length-1].length());
            tokens.add(token);
        }

        
        /* IMPRESSAO DOS TOKENS */
//        String msg = "";
//        for(int i=0; i < tokens.size(); i++) {
//            msg += tokens.get(i).exibir()+"\n";
//        }
//        if(fechamento>0)msg += "ERRO \tFECHAR PARENTESES";
//        if(fechamento<0)msg += "ERRO \tABRIR PARENTESES";
    return tokens;    
    }
    
    
    
    
 
    
}
