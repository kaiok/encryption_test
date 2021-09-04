package com.kais.encryption;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Author liuxiankai
 * @ClassName EncryptionToDecrypt
 * @CreateTime 2021-08-30 16:14
 * @Description: 自制加密解密方式
 */
public class EncryptionToDecrypt {

    private static final int THREE = 3;
    private static final int TWO = 2;
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final int EIGHT = 8;

    public static void main(String[] args) {
        //str为原文
        String string;
        //stringBuilder为密文
        StringBuilder stringBuilder;
        StringBuilder resultString;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要传输的内容：");
        string = scanner.nextLine();
        stringBuilder = encryption(string);
        System.out.println("你获得的密文为:" + stringBuilder);
        System.out.println("我要开始解密了！");
        resultString = decrypt(stringBuilder.toString());
        System.out.println("原文为：" + resultString);
    }

    /**
     * 方法描述:加密方法,返回密文
     *
 * @param str
     * @返回值 : java.lang.StringBuilder
     * @作者 : lxk
     * 时间:2021/9/1 13:17
    */
    static StringBuilder encryption(String str) {

        //length为字符串字符个数
        int length = str.length();
        if (length == 0) {
            return new StringBuilder("字符串为空！");
        }

        //target用于存储添加4位二进制码并取反后完成拼接的字符串,并作为方法的返回值
        StringBuilder target = new StringBuilder();
        //integer位生成的随机十进制数，用于判断原8位二进制需要取反的部分
        //stringBuilderBinary用于存储随机十进制数的4位二进制代码，拼接到原字符串的二进制码后面
        int num = 0;
        StringBuilder stringBuilderBinary = null;

        //hashMap用于存储随机数生成的整数,以及整数对应的4为ASCII码
        HashMap<Integer, StringBuilder> hashMap = new HashMap<Integer, StringBuilder>(1);

        //当字符串个数为偶数时，每有一位字符串，后边便需要添加4位二进制数
        for (int i = 0; i < length; i++) {

            log.println("循环开始==================");

            //调用4为随机二进制码生成方法
            hashMap = randomToBinaryString();
            //使用Map.Entry<>分离键值,并输出map集合的键与值
            for (Map.Entry<Integer, StringBuilder> arg : hashMap.entrySet()
            ) {
                //获取十进制数值
                num = arg.getKey();
                log.println("随机生成的键值key(十进制数) = " + num);
                //获取十进制对应的二进制码字符串
                stringBuilderBinary = arg.getValue();
                log.println("键值对应的二进制码 = " + stringBuilderBinary);
            }

            //将字符串存储到字符数组中
            char[] singleChar = str.toCharArray();
            //将char字符转换为string类型
            String singleString = String.valueOf(singleChar[i]);
            log.println("该循环处理的字符为=" + singleString);
            //将原文字符串的单个字符进行处理,转换为该字符对应的二进制码
            StringBuilder singleStringBuilder = stringToBinaryString(singleString);
            log.println("该字符对应的二进制码字符串为:" + singleStringBuilder);

            //获取单个字符二进制码中对应的十进制数位
            char targetChar = singleStringBuilder.charAt(num);
            log.println("二进制码中需要进行取反的值为:" + targetChar);
            //将需要取反的二进制位删除,进行取反操作
            singleStringBuilder.delete(num, num + 1);
            if ('1' == targetChar) {
                singleStringBuilder.insert(num, "0");
            } else {
                singleStringBuilder.insert(num, "1");
            }
            log.println("取反后该位的值为:" + singleStringBuilder.charAt(num));
            //单个字符拼接随机4为二进制数
            target.append(singleStringBuilder).append(stringBuilderBinary);
            log.println("第" + (i + 1)  + "次取反循环结束==================================");
        }

        //当字符个数为奇数时，给每一个字符对应的二进制编码添加4位随机二进制码后，还需要添加4位二进制随机码
        //当字符个数位偶数时，添加完4位二进制码，取反后，按每8位二进制码分配，可分配完
        if (length % TWO != 1) {
            log.println("取反拼接后的二进制字符串为:" + target);
            log.println("返回的密文为: ");
            return binaryStringToStringBuilder(target);
        }else{
            //调用4为随机二进制码生成方法
            hashMap = randomToBinaryString();
            //使用Map.Entry<>分离键值,并输出map集合的键与值
            for (Map.Entry<Integer, StringBuilder> arg : hashMap.entrySet()
            ) {
                //获取十进制对应的二进制码字符串
                stringBuilderBinary = arg.getValue();
            }
            target.append(stringBuilderBinary);
            log.println("取反拼接后的二进制字符串为:" + target);
            log.println("返回的密文为: " + target);
            return binaryStringToStringBuilder(target);
        }
    }

    /**
     * 方法描述:解密方法,返回原文
     *
     * @返回值 : java.lang.StringBuilder
     * @作者 : lxk
     * 时间:2021/9/1 15:16
    */
    static StringBuilder decrypt(String string){
        //将密文转换为二进制字符串
        StringBuilder targetString = stringToBinaryString(string);
        log.println("密文对应的二进制字符串为:" + targetString);
        StringBuilder singleString = new StringBuilder();
        //获取原文字符个数
        int length = targetString.length();
        int count = length/12;
        log.println("原文字符个数为:" + count);
        //存储原文字符,并作为返回值
        StringBuilder finalString = new StringBuilder();
        //存储每8位被还原的二进制字符串
        String returnString;
        //存储8为二进制后面的4为二进制字符串
        String stringEight;
        //存储二进制字符串转换后的int
        int num;
        for (int i = 0; i < count; i++) {
            log.println("解密开始=====================");
            returnString = targetString.substring(0,8);
            stringEight = targetString.substring(8,12);
            log.println("8位二进制字符串密文为:" + returnString + "   随机4位(解密)二进制为:" + stringEight);
            StringBuilder tempString = new StringBuilder(returnString);
            log.println("String类型8位密文转换为StringBuilder后值为:" + tempString);

            num = Integer.parseInt(stringEight,2);
            log.println("四位二进制对应的十进制数为:" + num);
            char singleChar = tempString.charAt(num);
            tempString.delete(num, num + 1);
            if ('1' == singleChar) {
                tempString.insert(num, "0");
            } else {
                tempString.insert(num, "1");
            }
            targetString.delete(0,12);
            singleString = binaryStringToStringBuilder(tempString);
            log.println("该原文字符为:" + singleString);
            finalString.append(singleString);
            log.println("第" + (i + 1) + "个字符解密完成==============");
        }
        return finalString;
    }

    /**
     * 随机生成0·7十进制数，并将其转换位4位二进制码字符串
     * 存储到hashMap集合中，返回
     */
    static HashMap<Integer, StringBuilder> randomToBinaryString(){
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        int integer = random.nextInt(7);
        HashMap<Integer,StringBuilder> hashMap = new HashMap<Integer,StringBuilder>(1);

        String one = "0";
        String two = "00";
        String three = "000";
        String four = "0000";
        log.println("随机生成的数字为：" + integer);
        //当integer等于0时，返回的二进制码需要拼接0000
        if(integer == ZERO){
            result.append(four);
        }else if(integer == ONE) {
            result.append(three).append(Integer.toBinaryString(integer));
        } else if(integer <= THREE){
            result.append(two).append(Integer.toBinaryString(integer));
        }else{
            result.append(one).append(Integer.toBinaryString(integer));
        }
        //将随机生成的十进制数以及对应的4位二进制存储到hashMap集合中
        hashMap.put(integer,result);
        return hashMap;
    }

    /**
     * 字符串转二进制字符数串
     */
    static StringBuilder stringToBinaryString(String str){

        StringBuilder result = new StringBuilder();
        String singleString;
        String combine = "";
        java.lang.String head = "0";
        char[] chars = str.toCharArray();

        for (char ache:chars
        ) {
            singleString = Integer.toBinaryString(ache);
            //单个字符转换为二进制字符串时，可能不满8位，不满的则往左添0
            while((singleString.length() + combine.length()) != EIGHT){
                combine = combine.concat(head);
            }
            result.append(combine).append(singleString);
            combine = "";
        }
        return result;
    }

    /**
     * 方法描述:二进制字符串转换为字符串输出(ASCII)
     *
 * @param stringBuilder
     * @返回值 : java.lang.StringBuilder
     * @作者 : lxk
     * 时间:2021/9/1 14:35
    */
    static StringBuilder binaryStringToStringBuilder(StringBuilder stringBuilder){

        int length = stringBuilder.length();
        String string = null;

        //8位为一个ASCII字符
        int count = length/8;
        log.println("密文的字符数量为" + count);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < count; i++) {
            string = stringBuilder.substring(0,8);
            System.out.println("（对前8为先进行处理）8位二进制字符串为:" + string);

            char chars = (char) Integer.parseInt(string,2);
            log.println("8位二进制字符串对应的char字符为:" + chars);

            stringBuilder.delete(0,8);
            log.println("截取完8为二进制字符串后的值为:" + stringBuilder);
            result.append(chars);
        }
        return result;
    }

}



