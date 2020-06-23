package com.gdkj.stack;

/**
 * Created by DELL on 2020/6/18.
 */
public class Calculator {

    public static void main(String[] args) {
        String expression="30+2*6-2";
        ArrayStack2 number = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        //定义变量
        int index=0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch= ' ';
        String keepNum="";
        while (true){
            ch =expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)){
                if(!operStack.ifEmpty()){
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        //获取数据和符号，进行计算后，入栈
                        num1=number.pop();
                        num2=number.pop();
                        oper=operStack.pop();
                        res=number.cal(num1,num2,oper);

                        //计算后的数据和符号入栈
                        number.push(res);
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    operStack.push(ch);
                }
            }else {
                /*number.push(ch-48);*/
                keepNum +=ch;
                if(index==expression.length()-1){
                    number.push(Integer.parseInt(keepNum));
                }else {

                if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                    number.push(Integer.parseInt(keepNum));
                    keepNum="";
                 }
                }
            }
            index++;
            if(index>=expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序从数据和符号中取出数据，计算
        while (true){
            if(operStack.ifEmpty()){
                break;
            }
            num1=number.pop();
            num2=number.pop();
            oper=operStack.pop();
            res=number.cal(num1,num2,oper);

            number.push(res);
        }
        System.out.printf("表达式%s=%d",expression,number.pop());
    }
}

class ArrayStack2{
    private  int maxSize;
    private int [] stack;
    private int top=-1;

    public ArrayStack2(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }

    //返回栈顶的值
    public int peek(){
        return stack[top];
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

    //返回运算符的优先级
    public int priority(int oper){
        if(oper =='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val) {
        return val =='+'||val=='-'||val=='*'||val=='/';
    }
    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num1*num2;
                break;
            case '/':
                res=num2/num1;
                break;
        }
        return res;
    }
}
