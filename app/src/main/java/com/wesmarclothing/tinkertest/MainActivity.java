package com.wesmarclothing.tinkertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.litepal.LitePal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        TestLitePalSql mTestLitePalSql = new TestLitePalSql();
//        mTestLitePalSql.setCover(new byte[]{1, 2, 3, 4, 5, 6});
//        mTestLitePalSql.setName("TestLitePalSql");
//        mTestLitePalSql.setSingle("single");
//
//
//        TestLitePalItemSql mTestLitePalItemSql = new TestLitePalItemSql();
//        mTestLitePalItemSql.setDuration(1000);
//        mTestLitePalItemSql.setName("TestLitePalItemSql");
//        mTestLitePalItemSql.setTestLitePalSql(mTestLitePalSql);
////        mTestLitePalItemSql.save();
//
//        TestLitePalItemSql mTestLitePalItemSql2 = new TestLitePalItemSql();
//        mTestLitePalItemSql2.setDuration(1000);
//        mTestLitePalItemSql2.setName("TestLitePalItemSql2");
//        mTestLitePalItemSql2.setTestLitePalSql(mTestLitePalSql);
////        mTestLitePalItemSql2.save();
//
//        ArrayList<TestLitePalItemSql> litePalItemSqls = new ArrayList<>();
//        litePalItemSqls.add(mTestLitePalItemSql);
//        litePalItemSqls.add(mTestLitePalItemSql2);
//
//        mTestLitePalSql.setSongs(litePalItemSqls);
//        mTestLitePalSql.save();


        List<? extends Number> numbers = new ArrayList<>();
        List<? super Number> number2s = new ArrayList<>();
        List<Number> number3s = new ArrayList<>();
        number2s.add(new Integer(0));
        number2s.add(new Double(0.111));
        number2s.add(new Float(0.11));

        number3s.add(new Integer(0));
        number3s.add(new Double(0.111));
        number3s.add(new Float(0.11));
        numbers = number3s;


        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("语文:1");
        linkedList.add("数学:2");
        linkedList.add("英语:3");

        form(linkedList);

        linkedList.set(0, "物理：0");
        form(linkedList);

        linkedList.add(1, "化学：4");
        form(linkedList);


        List<?> list = new ArrayList<>();
//        list = linkedList;

        boolean b = list.getClass() == linkedList.getClass();
        Logger.getGlobal().warning("class:" + b);


        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        System.out.println(c1 == c2);


        //反射
        try {
            Class<?> aClass = Class.forName("com.wesmarclothing.tinkertest.TestLitePalSql");

            //成员变量
            Field[] declaredFields = aClass.getDeclaredFields();
            Field[] fields = aClass.getFields();

            Logger.getGlobal().warning("Field:");
            for (Field f : declaredFields) {
                Logger.getGlobal().warning(f.toString());
            }
            //方法
            Method[] declaredMethods = aClass.getDeclaredMethods();
            Logger.getGlobal().warning("Method:");
            for (Method m : declaredMethods) {
                Logger.getGlobal().warning(m.toString());
            }

            Method single = aClass.getMethod("setSingle", String.class);
            if (single != null) {
                Logger.getGlobal().warning("single:" + single.toString());
            }

            //构造器
            Constructor<?>[] constructors = aClass.getDeclaredConstructors();
            Logger.getGlobal().warning("constructors:");
            for (Constructor c : constructors) {
                Logger.getGlobal().warning(c.toString());
            }

            //注解
            Annotation[] annotations = aClass.getDeclaredAnnotations();
            Logger.getGlobal().warning("Annotation:");
            for (Annotation a : annotations) {
                Logger.getGlobal().warning(a.toString());
            }

            //超类
            Type genericSuperclass = aClass.getGenericSuperclass();
            Logger.getGlobal().warning("genericSuperclass:" + genericSuperclass.toString());

            //接口
            Class<?>[] interfaces = aClass.getInterfaces();
            Logger.getGlobal().warning("interfaces:");
            for (Class c : interfaces) {
                Logger.getGlobal().warning(c.toString());
            }

            Type[] genericInterfaces = aClass.getGenericInterfaces();
            Logger.getGlobal().warning("genericInterfaces:");
            for (Type t : genericInterfaces) {
                Logger.getGlobal().warning(t.toString());
            }

            //类信息
            //判断是否是基础类型
            boolean isPrimitive = aClass.isPrimitive();
            //判断是否是集合类
            boolean isArray = aClass.isArray();
            //判断是否是注解类
            boolean isAnnotation = aClass.isAnnotation();
            //判断是否是接口类
            boolean isInterface = aClass.isInterface();
            //判断是否是枚举类
            boolean isEnum = aClass.isEnum();
            //判断是否是匿名内部类
            boolean isAnonymousClass = aClass.isAnonymousClass();
            //判断是否被某个注解类修饰
            boolean isAnnotationPresent = aClass.isAnnotationPresent(Deprecated.class);
            //获取class名字 包含包名路径
            String className = aClass.getName();
            //获取class的包信息
            Package aPackage = aClass.getPackage();
            //获取class类名
            String simpleName = aClass.getSimpleName();
            //获取class访问权限
            int modifiers = aClass.getModifiers();
            //内部类
            Class<?>[] declaredClasses = aClass.getDeclaredClasses();
            //外部类
            Class<?> declaringClass = aClass.getDeclaringClass();

            //创建对象
            Object o = aClass.newInstance();

            //调用方法
            Method setSingle = aClass.getDeclaredMethod("setSingle", String.class);
            setSingle.setAccessible(true);
            setSingle.invoke(o, "我是谁");


            Field field = aClass.getDeclaredField("single");
            field.setAccessible(true);
//            field.set(o, "我是哪个？");
            Object o1 = field.get(o);
            Logger.getGlobal().warning("single:" + o1);

        } catch (ClassNotFoundException
                | NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | InvocationTargetException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }


    }


    private void form(Collection collection) {
        collection.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                Logger.getGlobal().warning(s);
            }
        });
    }


    public void SQL(View view) {
//        List<TestLitePalSql> testLitePalSql = LitePal.where("name like ? ", "TestLitePalSql").find(TestLitePalSql.class);
//        List<TestLitePalItemSql> testLitePalItemSql = LitePal.where("name like ? ", "TestLitePalItemSql").find(TestLitePalItemSql.class);
//
////        new AlertDialog.Builder(this)
////                .setMessage(testLitePalSql == null ? "testLitePalSql is null" : testLitePalSql.size() + "")
////                .show();
////        new AlertDialog.Builder(this)
////                .setMessage(testLitePalItemSql == null ? "mTestLitePalItemSql is null" : testLitePalItemSql.size() + "")
////                .show();
//
//        TestLitePalSql first = LitePal.findFirst(TestLitePalSql.class);
//        first.setSingle("LitePal");
//        first.updateAll("name=?", "TestLitePalSql");
//
////        LitePal.deleteAll(TestLitePalItemSql.class, "name=?", "TestLitePalSql");


//        LitePal.deleteAll(TestLitePalItemSql.class, "name like ? ", "TestLitePalItemSql");

        List<TestLitePalItemSql> itemSqls = LitePal.select("name")
                .where("duration like ? ", 1000 + "")
                .find(TestLitePalItemSql.class);

        Logger.getGlobal().info(LitePal.count(TestLitePalItemSql.class) + "");
        Logger.getGlobal().info(LitePal.findFirst(TestLitePalItemSql.class).toString());
        Logger.getGlobal().info(itemSqls.toString());
        Logger.getGlobal().info(LitePal.findFirst(TestLitePalSql.class, true).toString());
    }
}
