package com.wesmarclothing.tinkertest;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName Java8Demo
 * @Date 2019/3/16 15:03
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class Java8Demo {


    public interface DefaultInterface {
        @RequiresApi(api = Build.VERSION_CODES.N)
        default String defaultMethod() {
            return "default";
        }
    }


    @TargetApi(Build.VERSION_CODES.N)
    public static void main(String[] args) {
        Java8Demo java8Demo = null;

//        Optional
//                .of(java8Demo)
//                .flatMap((Function<Java8Demo, Optional<Integer>>) java8Demo1 -> Optional.empty())
//                .orElse(20);

        Long[] longs = new Long[20000];

        Arrays.parallelSetAll(longs, index ->(long)(Math.random()*1000000L));
        Arrays.stream(longs).limit(10).forEach(i -> {
            System.out.println("并行数据未排序：" + i);
        });

        Arrays.parallelSort(longs);

        Arrays.stream(longs).limit(10).forEach(i -> {
            System.out.println("并行数据排序：" + i);
        });
    }

}





class RepeatingAnnotations {
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }


    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void main(String[] args) {
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println(filter.value());
        }
    }
}
