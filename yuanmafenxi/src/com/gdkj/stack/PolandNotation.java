package com.gdkj.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by DELL on 2020/6/22.
 */
public class PolandNotation {

    public static void main(String[] args) {

        String expression="1+((2+3)*4)-5";

        List<String> list = toInfixExpressionList(expression);

        System.out.println(list);

        List<String> parseSuffixExpreesionList = parseSuffixExpreesionList(list);

        System.out.println("后缀表达式对应的list"+parseSuffixExpreesionList);

        System.out.printf("expression=%d",calculate(parseSuffixExpreesionList));

/*
        //先定义一个表达式
        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";


        List<String>rpnList=getListString(suffixExpression);
        System.out.println(rpnList);

        int res=calculate(rpnList);

        System.out.println(res);*/
    }

    public static List<String>getListString(String suffixExpression){

        String[] split=suffixExpression.split(" ");

        List<String>list =new ArrayList<>();
        for (String ele:split){
            list.add(ele);
        }
        return  list;
    }



    //转换成后缀表达式
    public static List<String>parseSuffixExpreesionList(List<String> ls){

        Stack<String>s1=new Stack<>();//符号栈
        //Stack<String>s2=new Stack<>();//数栈
        List<String>s2=new ArrayList<>();

        //遍历 ls
        for (String item:ls){
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//消除括号
            }else {
                //当item的优先级小于等于栈顶运算符
                while (s1.size()!=0&& Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要item
                s1.push(item);
            }
        }
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2;
    }




    //方法:将中缀表达式转换成List
    public static List<String>toInfixExpressionList(String s){
        //定义一个List，存放中缀表达式对应的内容
        List<String>ls =new ArrayList<>();

        int i=0;
        String str;
        char c;
        do{
            if((c=s.charAt(i))<48||(c=s.charAt(i))>57){
                ls.add(""+c);
                i++;
            }else {
                 str="";
                while (i<s.length()&&(c=s.charAt(i))>=48&&(c=s.charAt(i))<=57){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while (i<s.length());
        return ls;
    }

    /*
    * 完成逆波兰表达式的运算
    * */
    public static  int calculate(List<String>ls){
        //创建栈
        Stack<String>stack=new Stack<>();

        for (String item:ls){
            //正则表达式
            if(item.matches("\\d+")){//匹配的是多位数
                stack.push(item);
            }else {
                int num2=Integer.parseInt(stack.pop());
                int num1=Integer.parseInt(stack.pop());
                int res=0;
                if(item.equals("+")){
                    res=num1+num2;
                }else if (item.equals("-")){
                    res=num1-num2;
                }else if (item.equals("*")){
                    res=num1*num2;
                }else if (item.equals("/")){
                    res=num1/num2;
            }else {
                throw new RuntimeException("符号出错");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
class Operation{
    private static  int ADD=1;
    private static  int SUB=1;
    private static  int MUL=1;
    private static  int DIV=1;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}