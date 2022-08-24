package com.example.demo.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : pp
 * @date : Created in 2021/6/16 14:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJobTest {
    @Autowired
    private TestJob testJob;
    @Test
    public void runfirst() {
//        testJob.runfirst();
    }
}