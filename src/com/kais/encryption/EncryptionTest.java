package com.kais.encryption;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.kais.encryption.EncryptionToDecrypt.*;

/**
 * @Author liuxiankai
 * @ClassName EncryptionTest
 * @CreateTime 2021-09-01 13:12
 * @Description: 测试类
 */
public class EncryptionTest {

    /**
     * 加密方法encryption测试
     */
    @Test
    public void test04(){
        StringBuilder stringBuilder = encryption("a1");
        System.out.println(stringBuilder);
    }


    /**
     * 方法描述:解密方法,传入密文,返回原文
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/1 15:50
     */
    @Test
    public void test07(){
        StringBuilder result = decrypt("a1");
        System.out.println("原文为:" + result);
    }

    /**
     * 将随机生成的0~7之间的整数转换为二进制字符串测试类
     */
    @Test
    public void test(){
        Integer integer = null;
        StringBuilder stringBuilder = null;
        HashMap<Integer,StringBuilder> hashMap = new HashMap<Integer,StringBuilder>(1);
        hashMap = randomToBinaryString();

        //使用Map.Entry<>分离键值,并输出hashMap集合的键与值
        for (Map.Entry<Integer, StringBuilder> arg:hashMap.entrySet()
        ){
            System.out.println(arg.getKey() + "<--键，键值-->" + arg.getValue());
            integer = arg.getKey();
            stringBuilder = arg.getValue();
        }

        System.out.println(integer);
        System.out.println(stringBuilder);
    }
    /**
     * 将字符串转换为二进制字符串测试类
     */
    @Test
    public void test02(){
        java.lang.String str = "3";

        System.out.println("Integer.toBinaryString(3) = " + Integer.toBinaryString(3));

        StringBuilder end = stringToBinaryString(str);
        System.out.println("Integer.toBinaryString(string) = " + end);
        System.out.println(str.length());

    }

    /**
     * 方法描述:获取stringBuilder某个区间元素位置
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/1 9:15
     */
    @Test
    public void test03(){

        char chars = (char) Integer.parseInt("00110001",2);
        System.out.println(chars);
    }

    /**
     * 方法描述:二进制字符串转换为字符(ASCII)
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/1 14:05
    */
    @Test
    public void test06(){

//        String string = "01110111";
//        int num = Integer.parseInt(string,2);
//        System.out.println(num);
//        char chars = (char) num;
//        System.out.println(chars);
//
//        String result = "";
//        result = Integer.toHexString(123);
//        System.out.println(result);

        StringBuilder stringBuilder = new StringBuilder("0100001001011011");
        StringBuilder stringBuilder1 = binaryStringToStringBuilder(stringBuilder);
        System.out.print(stringBuilder1);

    }

    /**
     * 方法描述:十进制数转8位二进制数
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/1 14:05
     */
    @Test
    public void test08(){
        char chars = '1';
        System.out.println(Integer.toBinaryString('*'));

    }

    /**
     * 方法描述:字符串转ASCII二进制字符串
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/3 9:55
    */
    @Test
    public void test09(){
        String string = "$";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder = stringToBinaryString(string);
        System.out.println(stringBuilder);



    }

    /**
     * 方法描述:字符串转16进制
     *
     * @返回值 : void
     * @作者 : lxk
     * 时间:2021/9/3 9:59
    */
    @Test
    public void test10(){
        String string = "ABCDEF";
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder stringBuilder = new StringBuilder("");
        byte[] bs = string.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            stringBuilder.append(chars[bit]);
            bit = bs[i] & 0x0f;
            stringBuilder.append(chars[bit]);
            System.out.println(stringBuilder);
        }
        System.out.println(stringBuilder);
        System.out.println(stringBuilder.toString().trim());
    }


}
