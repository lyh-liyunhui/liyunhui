package com.gdkj.linkedlist;

/**
 * Created by DELL on 2020/6/15.
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        //测试
        System.out.println("双向链表的测试");
        //进行测试
        HeroNode2 hero1=new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2=new HeroNode2(4,"卢俊义","玉麒麟");
        HeroNode2 hero3=new HeroNode2(2,"吴用","智多星");
        HeroNode2 hero4=new HeroNode2(3,"林冲","豹子头");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);

        //查找
        doubleLinkedList.list();

 /*       //修改
        HeroNode2 newHeronode=new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkedList.update(newHeronode);
        System.out.println("修改后---------------------");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.delect(3);
        System.out.println("删除后--------------------");
        doubleLinkedList.list();*/
    }
}


class  DoubleLinkedList{


    // //先初始化一个头节点
    private HeroNode2 head=new HeroNode2(0,"","");
    //获取头节点
    public HeroNode2 getHead(){
        return head;
    }

    //显示链表
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return ;
        }
        //因为头节点，设置变量
        HeroNode2 temp=head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }


    //添加节点到单链表
    public void add(HeroNode2 heroNode){
        //因为head节点不能动
        HeroNode2 temp=head;
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
        heroNode.pre=temp;
    }


    /*修改方法*/
    //修改节点信息
    public void update(HeroNode2 newHeroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
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

        //判断当前链表是否为空
        if(head.next==null){
            System.out.println("链表为空，无法删除");
            return ;
        }
        HeroNode2 temp=head.next;
        boolean flag=false;//标志
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else {
            System.out.printf("删除的节点编号%d不存在",no);
        }
    }
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp=head.next;
        boolean flag=false;//标志
        while(true){
            if(temp.no>heroNode.no){
                break;
            }else if (temp.no==heroNode.no){

                flag=true;
                break;
            }
            temp=temp.next;
        }
        //判断flag的值
        if(flag){
            System.out.printf("准备插入的英雄编号%d已经存在",heroNode.no);
        }else{
            temp.pre.next=heroNode;
            heroNode.next=temp;
        }
    }
}

//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + ", nickname='" + nickname + '\'' + '}';
    }
}

