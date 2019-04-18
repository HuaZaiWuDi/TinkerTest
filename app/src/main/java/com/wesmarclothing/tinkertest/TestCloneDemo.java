package com.wesmarclothing.tinkertest;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName TestCloneDemo
 * @Date 2019/3/16 14:02
 * @Author JACK
 * @Describe TODO克隆测试类
 * @Project TinkerTest
 */
public class TestCloneDemo {


    static class Subject {
        public String name;

        public Subject() {
        }

        public Subject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class Student implements Cloneable {

        public Subject mSubject;

        public String name;


        public Student(String name, String subject) {
            mSubject = new Subject(subject);
            this.name = name;
        }


        public Subject getSubj() {
            return mSubject;
        }

        public void setSubject(Subject subject) {
            mSubject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 深拷贝：
         * 基本数据类型都是值传递，所以拷贝是传递的值，
         * 深拷贝会拷贝所有的属性,并拷贝属性指向的动态分配的内存。
         * 当对象和它所引用的对象一起拷贝时即发生深拷贝。深拷贝相比于浅拷贝速度较慢并且花销较大。
         */
        @Override
        protected Object clone() {
            return new Student(name, mSubject.name);
        }

//        /**
//         * 浅拷贝：
//         * 基本数据类型都是值传递，所以拷贝是传递的值，
//         * 引用数据类型是引用传递，所以浅拷贝导致数据修改之后影响之前的对象的数据
//         *
//         * */
//        @Override
//        protected Object clone() {
//            try {
//                return super.clone();
//            } catch (CloneNotSupportedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
    }


    public static void main(String[] args) {

        // 原始对象
        Student stud = new Student("John", "Algebra");
        System.out.println("Original Object: " + stud.getName() + " - " + stud.getSubj().getName());

        // 拷贝对象
        Student clonedStud = (Student) stud.clone();
        System.out.println("Cloned Object: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());

        // 原始对象和拷贝对象是否一样：
        System.out.println("Is Original Object the same with Cloned Object: " + (stud == clonedStud));
        // 原始对象和拷贝对象的name属性是否一样
        System.out.println("Is Original Object's field name the same with Cloned Object: " + (stud.getName() == clonedStud.getName()));
        // 原始对象和拷贝对象的subj属性是否一样
        System.out.println("Is Original Object's field subj the same with Cloned Object: " + (stud.getSubj() == clonedStud.getSubj()));

        stud.setName("Dan");
        stud.getSubj().setName("Physics");


        System.out.println("Original Object after it is updated: " + stud.getName() + " - " + stud.getSubj().getName());
        System.out.println("Cloned Object after updating original object: " + clonedStud.getName() + " - " + clonedStud.getSubj().getName());


    }

}
