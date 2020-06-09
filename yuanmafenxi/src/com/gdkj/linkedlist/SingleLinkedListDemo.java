package com.gdkj.linkedlist;

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

     /*   singleLinkedLis.add(hero1);
        singleLinkedLis.add(hero2);
        singleLinkedLis.add(hero3);
        singleLinkedLis.add(hero4);*/


        singleLinkedLis.addByOrder(hero1);
        singleLinkedLis.addByOrder(hero3);
        singleLinkedLis.addByOrder(hero4);
        singleLinkedLis.addByOrder(hero2);

        singleLinkedLis.list();
        System.out.println("---------------------------------");
        //测试修改节点的代码
        HeroNode heroNode=new HeroNode(2,"小路","玉麒麟");
        singleLinkedLis.update(heroNode);

        singleLinkedLis.list();
    }
}
//定义SingleLinkedList管理
class SingleLinkedLis{
    //先初始化一个头节点
    private HeroNode head=new HeroNode(0,"","");

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