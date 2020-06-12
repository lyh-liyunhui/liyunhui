package com.gdkj.linkedlist;

import java.util.Stack;

/**
 * Created by DELL on 2020/6/8.
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        HeroNode hero1=new HeroNode(1,"宋江","及时雨");
        HeroNode hero2=new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3=new HeroNode(3,"吴用","智多星");
        HeroNode hero4=new HeroNode(4,"林冲","豹子头");

        //加入链表
        SingleLinkedLis singleLinkedLis=new SingleLinkedLis();

 /*       singleLinkedLis.add(hero1);
        singleLinkedLis.add(hero2);
        singleLinkedLis.add(hero3);
        singleLinkedLis.add(hero4);
*/

        singleLinkedLis.addByOrder(hero1);
        singleLinkedLis.addByOrder(hero3);
        singleLinkedLis.addByOrder(hero4);
        singleLinkedLis.addByOrder(hero2);

        singleLinkedLis.list();
        System.out.println("---------------------------------");



       /* * 测试单链表的反转*/


        System.out.println("反转单链表");
        reversetList(singleLinkedLis.getHead());




       //测试修改节点的代码
        HeroNode heroNode=new HeroNode(2,"小路","玉麒麟");
        singleLinkedLis.update(heroNode);

        singleLinkedLis.list();




        /*测试一下求单链表的有效节点个数*/


        System.out.println(getLength(singleLinkedLis.getHead()));




        /*测试一下查找单链表的倒数第K个节点*/


        HeroNode res=findeLastIndexNode(singleLinkedLis.getHead(),3);

        System.out.println(res);





        /*测试查找单链表的第k个节点*/


        System.out.println("-----------------------------");
        HeroNode res1=findindexNode(singleLinkedLis.getHead(),1);

        System.out.println(res1);




        /*逆向打印单链表*/
        System.out.println("----------------------------");
        reversePrint(singleLinkedLis.getHead());
    }

    /*单链表反转*/
    public static  void  reversetList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return ;
        }


        //定义一个辅助的指针，遍历单链表
        HeroNode cur=head.next;
        HeroNode next=null;
        HeroNode reverseHead=new HeroNode(0,"","");

        /*遍历原来的链表*/
        while (cur!=null){
            next=cur.next;
            cur.next=reverseHead.next;
            reverseHead.next=cur;
            cur=next;
        }
        head.next=reverseHead.next;
    }


    /*利用栈进行逆序打印*/
    public  static void reversePrint(HeroNode head){
        if(head.next==null){
            return;
        }

        Stack<HeroNode>stack=new Stack<>();
        HeroNode cur=head.next;

        //将链表中的节点加入栈中
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        //将栈中的节点进行打印
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

















    /*查找单链表中的倒数第K个节点
    * 思路
    * 1.编写一个一个方法接受head节点，同时接收一个index
    * 2.index表示是倒数第index节点
    * 3.先把链表从头到尾遍历，得到链表的总的长度
    * 4.得到size后，我们从链表的第一个开始遍历（size-index）*/
    public static  HeroNode findeLastIndexNode(HeroNode head,int index){
        if(head.next==null){
            return null;
        }
        //第一次遍历得到链表的长度
        int size=getLength(head);
        //第二次遍历 size-index位置，就是我们倒数的第K个节点
        if(index<=0||index>size){
            return null;
        }
        //定义辅助变量,for循环
        HeroNode cur=head.next;
        for (int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }
    /*查询单链表第K个节点*/
    public  static HeroNode findindexNode(HeroNode head,int k){
        if(head.next==null){
            return null;
        }
        int size=getLength(head);
        if(k<=0||k>size){
            return null;
        }
        HeroNode cur=head;
        int count=0;
        for(int i=0;i<size;i++){
            count++;
            if(count==k){
                cur=cur.next;
                break;
            }
            cur=cur.next;
        }
        return cur;
    }


    /*计算单链表的有效个数*/
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int lenght=0;
        HeroNode cur=head.next;
        while (cur!=null){
            lenght++;
            cur=cur.next;
        }
        return lenght;
    }
}

//定义SingleLinkedList管理
class SingleLinkedLis{
    //先初始化一个头节点
    private HeroNode head=new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单链表
    public void add(HeroNode heroNode){
        //因为head节点不能动
        HeroNode temp=head;
        //遍历链表
        while (true){
            //找到链表的最后
             if(temp.next==null){
                 break;
             }
            //如果没有找到最后，就后移
            temp=temp.next;
        }
        temp.next=heroNode;
    }
    /*修改方法*/
    //修改节点信息
    public void update(HeroNode newHeroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        boolean flag=false;//标志
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==newHeroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else {
            System.out.printf("没有找到编号%d的节点",newHeroNode.no);
        }
    }

    /*删除节点*/
    public void delect(int no){
        HeroNode temp=head;
        boolean flag=false;//标志
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else {
            System.out.printf("删除的节点编号%d不存在",no);
        }
    }
    //显示链表
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return ;
        }
        //因为头节点，设置变量
        HeroNode temp=head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }
    public void addByOrder(HeroNode heroNode){
        HeroNode temp=head;
        boolean flag=false;//标志
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){
                break;
            }else if (temp.next.no==heroNode.no){

                flag=true;
                break;
            }
            temp=temp.next;
        }
        //判断flag的值
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在",heroNode.no);
        }else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
}


//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\''+ '}';
    }


}