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
    private String Language;
    private String[] SplitLanguage;
    

    public Lexico(String Language) {
        this.Language = Language;
        this.SplitLanguage = Language.split("\n");
    }
    
    public boolean isNumber (char number) {
        String fonte = ""+number;
        String regra = "[0-9]";
        Pattern p = Pattern.compile(regra);
        Matcher m = p.matcher(fonte);
        
        return m.matches();
    }
    
    public ArrayList analisar(){
        String number = "";
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
                            i++;
                        }
                        else {
                            number += atual;
                            Token token = new Token(number, "NUM",i+1,j+1);
                            tokens.add(token);
                            number = "";
                        }
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
                if(atual == '\n'){

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
