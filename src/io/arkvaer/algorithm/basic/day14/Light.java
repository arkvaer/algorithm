package io.arkvaer.algorithm.basic.day14;


import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串str，只由 "X" 和 "." 两种字符构成
 * "X" 表示墙，不能放灯，也不需要点亮
 * "."表示居民点，可以放灯，需要点亮如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Light {

    // region 暴力方式
    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    public static int process(char[] str, int index, Set<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }
    // endregion

    public static int minLight2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArray = str.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < charArray.length) {
            if (charArray[index] == 'X') {
                index++;
            } else {
                lights++;
                if (index + 1 == charArray.length) {
                    break;
                } else {
                    // 当下一个节点是墙时, 直接跳过下一个节点
                    if (charArray[index + 1] == 'X') {
                        index += 2;
                    } else {
                        // 当 index+1 节点是居住点时,不管 index+2 是什么, 只需要在 index+1处放置一盏灯即可,
                        index += 3;
                    }
                }
            }
        }
        return lights;
    }


    /**
     * 	更简洁的解法
     * 	两个X之间，数一下.的数量，然后除以3，向上取整
     * 	把灯数累加
     * @param road
     * @return 最小灯数
     */
    public static int minLight3(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        char[] str = road.toCharArray();
        int cur = 0;
        int light = 0;
        for (char c : str) {
            if (c == 'X') {
                light += (cur + 2) / 3;
                cur = 0;
            } else {
                cur++;
            }
        }
        light += (cur + 2) / 3;
        return light;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            int ans3 = minLight3(test);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
