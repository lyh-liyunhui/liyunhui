package com.gdkj.stack;

import java.util.Scanner;

/**
 * Created by DELL on 2020/6/18.
 */
public class LinkedListStackDemo {

    public static void main(String[] args) {
        LinkedListStack linkedListStack=new LinkedListStack();
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
                case "push":
                    System.out.println("请输入一个数");
                    int value=scanner.nextInt();
                    linkedListStack.push(value);
                    break;
                case "pop":
                    try {
                        int res=linkedListStack.pop();
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

class LinkedListStack{

    private HeroNode top;

    public boolean isEmpty(){
        return top==null;
    }

    public void push(int no){
        HeroNode heroNode=new HeroNode(no);
        if(top==null){
            top=heroNode;
        }else {
            heroNode.setNext(top);
            top=heroNode;
        }
    }

    public int pop(){
        if(isEmpty()){
            System.out.println("栈空");
        }
        int no=top.getNo();
        top=top.getNext();
        return no;
    }
}


class HeroNode{
    private int no;
    private HeroNode next;

    public HeroNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }
}