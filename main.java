/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author tb
 */

class nfa{
    BufferedReader br;
    StringTokenizer st,stp;
    String input="";
    String tok[]=new String[10];
    String nfa[][]=new String[50][3];
    int totok=0,in=0,fi=0,index=0,tokens=0,state=0,start=0,finish=0;
    nfa(){
        br=new BufferedReader(new InputStreamReader(System.in));
    }
    
    void input() throws IOException{
        input=br.readLine();
        st= new StringTokenizer(input,"()");
        int i=0;
        tokens=st.countTokens();
        while(st.hasMoreTokens()){
            tok[i++]=st.nextToken();
        }
        for( i=0;i<nfa.length;i++){
            nfa[i][0]=nfa[i][1]=nfa[i][2]="-";
        }
    }
    void analyse(){
        for(int i =0;i<tokens;i++){
            if(tok[i].equals("*")||tok[i].equals("+")){
                epsilon(i);
                continue;            
        }
            if(tok[i].contains("+")){
                plus(tok[i]);
        }
            else{
                concat(tok[i]);
            }
            
        }
        print();
    }
    
    void plus(String reg){
        start=state;
        nfa[index][0]=""+(++state);
        nfa[index++][1]=""+state;
        finish=state;
    }
    
    void concat(String reg){
        start=state;
        while(reg.length()>0){
            int a=Integer.parseInt(""+reg.charAt(0));
            nfa[index++][a]=""+(++state);
            reg=reg.substring(1);
        }
        finish=state;
    }
    
    void epsilon(int i){
        switch (tok[i]) {
            case "*":
                nfa[finish][2]=""+start;
                nfa[finish][2]=""+(++state);
                nfa[start][2]=""+(++finish);
                index=state;
                break;
            case "+":
                nfa[finish][2]=""+start;
                break;
        }
    }
    
    void print(){
        System.out.println("\t0\t1\t\u03B5\n____________________________");
        for(int i=0;i<=state;i++){
            String s1="|\t",s2="\t",s3="\t";
            if(!nfa[i][0].equals("-"))
                s1="|\tq";
            if(!nfa[i][1].equals("-"))
                s2="\tq";
            if(!nfa[i][2].equals("-"))
                s3="\tq";
            System.out.println("q"+i+s1+nfa[i][0]+s2+nfa[i][1]+s3+nfa[i][2]);
        }
    }
}

public class main {
    public static void main(String args[]) throws IOException{
        nfa obj=new nfa();
        obj.input();
        obj.analyse();
    }
}