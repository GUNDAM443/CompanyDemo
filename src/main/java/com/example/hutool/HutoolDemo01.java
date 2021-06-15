package com.example.hutool;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;

import javax.sound.midi.Soundbank;

/**
 * @author : pp
 * @date : Created in 2020/6/8 11:20
 */
public class HutoolDemo01 {
    public static void main(String[] args) {
        String s="adfaciorn";
        System.out.println(StrUtil.sub(s, 1, 3));
        String temple="{}是个大{}";
        System.out.println(StrUtil.format(temple, "xiaoming", "坏蛋"));

        String idCard="320982199301176130";
        System.out.println(IdcardUtil.getAgeByIdCard(idCard));
        System.out.println(IdcardUtil.getGenderByIdCard(idCard));
    }
}
