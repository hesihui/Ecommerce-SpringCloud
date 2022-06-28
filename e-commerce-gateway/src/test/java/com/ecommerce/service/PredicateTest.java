package com.ecommerce.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * <h1> java8 predicate 使用方法与思想</h1>
 * */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class PredicateTest {
    public static List<String> MICRO_SERVICE = Arrays.asList("nacos", "authority",
            "gateway", "feign", "ribbon", "hystrix", "e-commerce");

    /**
     * <h2> test 方法主要用于参数符不符合规则，返回值是boolean</h2>
     * */
    @Test
    public void testPredicateTest() {

        // test case： 找到每个microservice里长度大于5的字符串
        Predicate<String> letterStartWith = s -> s.length() > 5;
        MICRO_SERVICE.stream().filter(letterStartWith).forEach(System.out::println);
    }

    /**
     * <h2> and 方法等同于逻辑与 &&，存在短路的特性，需要所有条件都满足</h2>
     * */
    @Test
    public void testPredicateAnd() {
        Predicate<String> letterLengthLimit = s -> s.length() > 5;
        Predicate<String> letterStartWith = s -> s.startsWith("gate");

        MICRO_SERVICE.stream().filter(
                letterLengthLimit.and(letterStartWith)
        ).forEach(System.out::println);
    }

    /**
     * <h2> or 等同于逻辑或者||, 多个逻辑只要满足一个即可</h2>
     * */
    @Test
    public void testPredicateOr() {
        Predicate<String> letterLengthLimit = s -> s.length() > 5;
        Predicate<String> letterStartWith = s -> s.startsWith("gate");
        MICRO_SERVICE.stream().filter(
                letterLengthLimit.or(letterStartWith)
        ).forEach(System.out::println);
    }

    /**
     * <h2> 等同于逻辑非 !</h2>
     * */
    @Test
    public void testPredicateNegate() {
        Predicate<String> letterStartWith = s -> s.startsWith("gate");
        MICRO_SERVICE.stream().filter(
                letterStartWith.negate()
        ).forEach(System.out::println);
    }

    /**
     * <h2> isEqual类似于 equals(), 区别在于：先判断对象是否为NULL，
     * 如果不为NULL再使用isequal的方法进行比较</h2>
     * */
    @Test
    public void testPredicateIsEqual() {
        Predicate<String> equalGateway = s -> Predicate.isEqual("gateway").test(s);
        MICRO_SERVICE.stream().filter(equalGateway).forEach(System.out::println);
    }
}
