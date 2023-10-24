package io.arkvaer.algorithm.primary.day5;

import io.arkvaer.algorithm.utils.AlgUtil;

import java.util.HashSet;

/**
 * @author waver
 * @date 2023/6/12 下午6:50
 */
public class BitMap1 {
    static class BitMap {
        private final long[] bits;

        public BitMap(int max) {
            bits = new long[(max + 64) >> 6];
        }

        public void add(int num) {
            // 必须使用1L
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        public boolean contains(int num) {
            return ((bits[num >> 6]) & (1L << (num & 63))) != 0;
        }
    }

    public static void main(String[] args) {
        AlgUtil.console("测试开始！");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTime = 10000000;
        for (int i = 0; i < testTime; i++) {
            int num = AlgUtil.random.nextInt(max + 1);
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    AlgUtil.console("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                AlgUtil.console("Oops!");
            }
        }
        AlgUtil.console("测试结束！");
    }
}
