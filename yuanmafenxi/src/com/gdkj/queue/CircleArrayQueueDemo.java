package com.gdkj.queue;

import java.util.Scanner;

/**
 * Created by DELL on 2020/6/5.
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArray circleArray=new CircleArray(3);
        char key=' ';
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;

        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(head):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key=scanner.next().charAt(0);
            switch (key){
                case 's':
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value=scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res=circleArray.getQueue();
                        System.out.printf("取出的数据是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res=circleArray.headQueue();
                        System.out.printf("取出的数据是%d",res);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class CircleArray{
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//存放数据，模拟队列


    public CircleArray(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
    }

    /*判断队列是否满*/
    public boolean isFull(){
        return (rear+1)%maxSize == front;

    }
    /*判断队列是否为空*/
    public boolean isEmpty(){
        return rear == front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列已满，不能加入数据");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }


    /*获取队列的数据，出队列*/
    public int getQueue(){
        //队列是否为空
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }

    /*显示队列的所有数据*/
    public  void showQueue(){
        //遍历
        if (isEmpty()){
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for(int i=front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }

    }


    //求出当前队列有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    /*显示队列的头数据*/
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}