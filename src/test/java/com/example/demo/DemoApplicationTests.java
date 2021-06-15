package com.example.demo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.PropertyComparator;
import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Test
    public void contextLoads() {


        List<Hero> heros = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                heros.add(new Hero("hero "+ i, RandomUtil.randomInt(100)));
            }
            System.out.println("未排序的集合:");
            System.out.println(CollectionUtil.join(heros, "\r\n"));

            Collections.sort(heros,new PropertyComparator<>("hp"));
            System.out.println("根据属性 hp 排序之后：");
            System.out.println(CollectionUtil.join(heros, "\r\n"));

    }
    class Hero{
        String name;
        int hp;

        public Hero(String name, int hp) {
            super();
            this.name = name;
            this.hp = hp;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getHp() {
            return hp;
        }
        public void setHp(int hp) {
            this.hp = hp;
        }
        @Override
        public String toString() {
            return "Hero [name=" + name + ", hp=" + hp + "]";
        }

    }
}
