package com.wesmarclothing.tinkertest;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName TestLitePalSql
 * @Date 2019/3/14 14:22
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class TestLitePalSql extends LitePalSupport implements Serializable {


    @Column(unique = true, defaultValue = "unknown")
    private String name;

//    private float price;

    private byte[] cover;

    private List<TestLitePalItemSql> songs = new ArrayList<>();

    @Column(unique = true)
    private String single;


    private List<? extends LitePalSupport> mList;

    public List<? extends LitePalSupport> getList() {
        return mList;
    }

    public void setList(List<? extends LitePalSupport> list) {
        mList = list;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public List<TestLitePalItemSql> getSongs() {
        return songs;
    }

    public void setSongs(List<TestLitePalItemSql> songs) {
        this.songs = songs;
    }


    @Override
    public String toString() {
        return "TestLitePalSql{" +
                "name='" + name + '\'' +
                ", cover=" + Arrays.toString(cover) +
                ", songs=" + songs +
                ", single='" + single + '\'' +
                '}';
    }
}
