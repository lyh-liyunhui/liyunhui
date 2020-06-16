package com.gdkj.linkedlist;

/**
 * Created by DELL on 2020/6/16.
 */
public class josephu {

    public static void main(String[] args) {

        //测试环形链表
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        circleSingleLinkedList.addBoy(5);

        circleSingleLinkedList.showBoy();

        //测试出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

class CircleSingleLinkedList{
    //创建一个first节点，没有编号
    private Boy first=null;

    //添加小孩节点，构建成一个环形链表
    public void addBoy(int nums){
        if(nums<1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy=null;
        //使用for来创建我们的环形链表
        for(int i=1;i<=nums;i++){
            Boy boy=new Boy(i);
            if(i==1){
                first=boy;
                first.setNext(first);
                curBoy=first;
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //遍历环形链表
    public void showBoy(){
        if(first==null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy=first;
        while (true){
            System.out.printf("编号%d \n",curBoy.getNo());
            if(curBoy.getNext()==first){
                System.out.println("链表遍历完毕");
                break;
            }
            curBoy=curBoy.getNext();
        }
    }
    //根据用户的输入
    /*
    * startNo 表示从第几个开始数数
    * countNum 表示数几下
    * nums 表示有多少个在圈中
    * */
     public void countBoy(int startNO,int countNum,int nums){
        if(first==null||startNO<1||startNO>nums){
            System.out.println("参数输入有误");
            return;
        }
         //创建一个辅助指针
         Boy helper=first;
         while (true){
             if(helper.getNext()==first){
                 break;
             }
             helper=helper.getNext();
         }
         for(int j=0;j<startNO-1;j++){
             first=first.getNext();
             helper=helper.getNext();
         }
         while (true){
             if(helper==first){
                 break;
             }
             for(int j=0;j<countNum-1;j++){
                 first=first.getNext();
                 helper=helper.getNext();
             }
             System.out.printf("编号%d\n",first.getNo());
             first=first.getNext();
             helper.setNext(first);
         }
         System.out.printf("最后留在圈中的编号%d\n",first.getNo());
     }
}

//创建一个boy类，表示一个节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }


}