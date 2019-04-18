package com.wesmarclothing.tinkertest;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author Jack
 * @date on 2019/3/15
 * @describe TODO反射泛型
 * @org 智裳科技
 */
public class GenericTest {
    private Map<String, Float> score;

    public static void main(String[] args)
            throws Exception {
        Class<GenericTest> clazz = GenericTest.class;
        Field f = clazz.getDeclaredField("score");
        // 直接使用getType()取出Field类型只对普通类型的Field有效
        Class<?> a = f.getType();
        // 下面将看到仅输出java.util.Map
        System.out.println("score的类型是:" + a);
        // 获得Field实例f的泛型类型
        Type gType = f.getGenericType();
        // 如果gType类型是ParameterizedType对象
        if (gType instanceof ParameterizedType) {
            // 强制类型转换
            ParameterizedType pType = (ParameterizedType) gType;
            // 获取原始类型
            Type rType = pType.getRawType();
            System.out.println("原始类型是：" + rType);
            // 取得泛型类型的泛型参数
            Type[] tArgs = pType.getActualTypeArguments();
            System.out.println("泛型类型是:");
            for (int i = 0; i < tArgs.length; i++) {
                System.out.println("第" + i + "个泛型类型是：" + tArgs[i]);
            }
        } else {
            System.out.println("获取泛型类型出错！");
        }

        System.out.println("系统默认编码方式：" + System.getProperty("file.encoding"));
    }
}