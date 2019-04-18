package com.wesmarclothing.tinkertest;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName NewIoDemo
 * @Date 2019/3/15 18:37
 * @Author JACK
 * @Describe TODO
 * @Project TinkerTest
 */
public class NewIoDemo {


    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);

        }
        aFile.close();
    }
}
