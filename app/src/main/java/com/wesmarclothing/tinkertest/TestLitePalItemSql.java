package com.wesmarclothing.tinkertest;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName TestLitePalItemSql
 * @Date 2019/3/14 14:23
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class TestLitePalItemSql extends LitePalSupport {


    @Column(unique = true)
    private String name;

    private int duration;

    @Column(ignore = true)
    private String uselessField;

    private TestLitePalSql mTestLitePalSql;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUselessField() {
        return uselessField;
    }

    public void setUselessField(String uselessField) {
        this.uselessField = uselessField;
    }

    public TestLitePalSql getTestLitePalSql() {
        return mTestLitePalSql;
    }

    public void setTestLitePalSql(TestLitePalSql testLitePalSql) {
        mTestLitePalSql = testLitePalSql;
    }

    @Override
    public String toString() {
        return "TestLitePalItemSql{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", uselessField='" + uselessField + '\'' +
                ", mTestLitePalSql=" + mTestLitePalSql +
                '}';
    }
}
