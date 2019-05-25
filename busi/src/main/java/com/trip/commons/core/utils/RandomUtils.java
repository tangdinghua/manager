package com.trip.commons.core.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * 随机数工具类
 *
 * @author fqh
 * @create 2016-12-27 5:57
 */
public class RandomUtils {

    /**
     * 获取长整形随机数
     * @return
     */
    public static long getLong() {
        Random random = new Random();
        return random.nextLong();
    }

    /**
     * 获取整数随机数
     * @return
     */
    public static int getInteger() {
        Random random = new Random();
        return random.nextInt() + random.nextInt();
    }

    /**
     * 获取双精度随机数
     * @return
     */
    public static double getDouble() {
        Random random = new Random();
        return random.nextInt() + random.nextDouble();
    }

    /**
     * 获取字符串随机数
     * @param num 多少位
     * @return
     */
    public static String getString(int num) {
        return RandomStringUtils.random(6, false, true);
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++)
        System.out.println(getString(6));
    }
}
