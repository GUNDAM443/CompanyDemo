package com.example.demo.jvm;
import java.util.ArrayList;
/**
 * 模拟内存溢出
 * @Author pp
 * @Date 2022/7/25 18:09
 */
public class JvmTest {
    public static void main(String[] args) {
        ArrayList<Ibeacon> ibeacons = new ArrayList<>();
        while (true){
            Ibeacon ibeacon = new Ibeacon();
            ibeacons.add(ibeacon);
        }
    }
}
