package com.gdkj.linkedlist;

import java.util.Stack;

/**
 * Created by DELL on 2020/6/12.
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack=new Stack<>();

        stack.add("jack");
        stack.add("tom");
        stack.add("smith");


        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
