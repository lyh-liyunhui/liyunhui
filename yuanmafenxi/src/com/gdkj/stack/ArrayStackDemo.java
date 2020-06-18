package com.gdkj.stack;

import java.util.Scanner;

/**
 * Created by DELL on 2020/6/17.
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack=new ArrayStack(4);
        String key="";
        boolean loop=true;
        Scanner scanner=new Scanner(System.in);

        while (loop){
            System.out.println("show：表示显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：表示添加数据");
            System.out.println("pop：表示取出数据");
            System.out.println("请输入你的选择");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value=scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res=stack.pop();
                        System.out.printf("出栈的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayStack{
    private  int maxSize;
    private int [] stack;
    private int top=-1;

    public ArrayStack(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }

    //栈控
    public boolean ifEmpty(){
        return top==-1;
    }

    //入栈
    public void push(int value){
        if(isFull()){
            System.out.println("栈满");
            return ;
        }
        top++;
        stack[top]=value;
    }

    //出栈
    public int pop(){
        if(ifEmpty()){
           //抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if(ifEmpty()){
            System.out.println("栈空，没有数据");
        }
        for (int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
