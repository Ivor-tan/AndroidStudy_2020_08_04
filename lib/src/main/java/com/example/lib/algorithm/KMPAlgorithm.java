package com.example.lib.algorithm;

import java.util.Arrays;

/**
 * https://www.cnblogs.com/dusf/p/kmp.html
 *
 * @desc KMP算法
 * 基本介绍：
 * （1）暴力匹配算法
 * 1）如果当前字符匹配成功（即str1[i]=str2[i]），则i++,j++，继续匹配下一个字符
 * 2）如果失败，令i=i-(j-1)，j=0，相当于每次匹配失败时，i回溯，j被转为0
 * 3）用暴力方法解决的话就会有大量的回溯，每次只移动一位，若是不匹配，移动到下一位接着判断，浪费大量时间。（不可行）
 * 4）暴力匹配实现
 * （2）KMP算法介绍
 * 1）KMP是一个解决模式串在文本串是否出现过，如果出现过，最早出现的位置就经典算法。
 * 2）Knuth-Morris-Pratt字符串查找法，简称KMP。
 * 3）KMP算法就是利用之前判断过信息，通过一个next数组，保存模式串中前后最长公共序列的长度，每次回溯时，通过next数组找到，
 * 前面匹配的位置，省去了大量的计算时间
 * 4）参考资料：https://www.cnblogs.com/ZuoAndFutureGirl/p/9028287.html
 * @Author xw
 * @Date 2019/9/27
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        // 暴力匹配
        String str1 = "ABCDE";
        String str2 = "CD";
        int index = ViolentMatch(str1.toCharArray(), str2.toCharArray());
        if (index != -1) {
            System.out.println("找到了，位置===>" + index);
        } else {
            System.out.println("没有找到！");
        }
        // KMP算法介绍
        // 字符串模板匹配值
        str1 = "BBCAABCDABCDABDABADABCDAB";
        str2 = "ABABBCBDADASF";

        /*int[] next = kmpNext("ABCDABD");
        System.out.println("next=" + Arrays.toString(next));*/
//        index = KmpSearch(str1.trim().toCharArray(), str2.trim().toCharArray(), getNext(str2));
//        index = KmpSearch(str1.trim().toCharArray(), str2.trim().toCharArray(), getNext(str2));
//        if (index != -1) {
//            System.out.println("找到了，位置===>" + index);
//        } else {
//            System.out.println("没有找到！");
//        }
        getNext1(str2);
        getNext2(str2);
        getNext3(str2);
    }

    private static int ViolentMatch(char[] s, char[] p) {
        int sLen = s.length;
        int pLen = p.length;

        int i = 0;
        int j = 0;
        while (i < sLen && j < pLen) {
            if (s[i] == p[j]) {
                //①如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
                i++;
                j++;
            } else {
                //②如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0
                i = i - j + 1;
                j = 0;
            }
        }
        //匹配成功，返回模式串p在文本串s中的位置，否则返回-1
        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    private static int KmpSearch(char[] s, char[] p, int[] next) {
        int i = 0;
        int j = 0;
        int sLen = s.length;
        int pLen = p.length;
        while (i < sLen && j < pLen) {
            //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    //next数组为重点
    private static int[] getNext1(String t) {
        char[] m = t.toCharArray();
        int[] next = new int[m.length];
        int j = 0, k = -1;
        next[0] = -1;
        while (j < m.length - 1) {
            if (k == -1 || m[j] == m[k]) {
                j++;
                k++;
                if (m[j] == m[k]) {
                    //当两个字符相同时，就跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }

            } else k = next[k];

        }
        System.out.print(t + "--------->Next[]======1111=======>" + Arrays.toString(next) + "\n");
        return next;
    }

    public static int[] getNext2(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;

        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
            System.out.print("while2=======>j--->" + j + "====>k--->" + k + "\n");

        }
        System.out.print(ps + "--------->Next[]======2222=======>" + Arrays.toString(next) + "\n");
        return next;
    }

    public static int[] getNext3(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {
                k++;
                j++;

                next[j] = k;

            } else {
                k = next[k];
            }
        }
        System.out.print(ps + "--------->Next[]======3333=======>" + Arrays.toString(next) + "\n");
        return next;
    }
}