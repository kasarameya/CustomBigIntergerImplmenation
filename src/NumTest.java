import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class NumTest extends TestCase {

    @Test
    public void testAdd() {

        Num a = new Num(10);
        Num b = new Num(5);

        Num c = Num.add(a,b);
        Num ans = new Num(15);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(5);
        b = new Num(10);

        c = Num.add(a,b);
        ans = new Num(15);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(2000);
        b = new Num(3000);

        c = Num.add(a,b);
        ans = new Num(5000);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(1000000000L);
        b = new Num(800000000000000L);

        c = Num.add(a,b);
        ans = new Num(800001000000000L);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(800000000000000L);
        b = new Num(800000000000000L);

        c = Num.add(a,b);
        ans = new Num(1600000000000000L);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.add(a,b);
        ans = new Num("9223372036854775808");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-74);
        b = new Num(-26);

        c = Num.add(a,b);
        ans = new Num(-100);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-25);

        c = Num.add(a,b);
        ans = new Num(25);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-50);
        b = new Num(25);

        c = Num.add(a,b);
        ans = new Num(-25);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-25);
        b = new Num(50);

        c = Num.add(a,b);
        ans = new Num(25);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(25);
        b = new Num(-50);

        c = Num.add(a,b);
        ans = new Num(-25);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-100);
        b = new Num(0);

        c = Num.add(a,b);
        ans = new Num(-100);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(0);
        b = new Num(0);

        c = Num.add(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-50);

        c = Num.add(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-0);
        b = new Num(0);

        c = Num.add(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);





        //String Constructor

        a = new Num("10");
        b = new Num("5");

        c = Num.add(a,b);
        ans = new Num("15");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("5");
        b = new Num("10");

        c = Num.add(a,b);
        ans = new Num("15");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("2000");
        b = new Num("3000");

        c = Num.add(a,b);
        ans = new Num("5000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("1000000000");
        b = new Num("800000000000000");

        c = Num.add(a,b);
        ans = new Num("800001000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("800000000000000");
        b = new Num("800000000000000");

        c = Num.add(a,b);
        ans = new Num("1600000000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.add(a,b);
        ans = new Num("9223372036854775808");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-74");
        b = new Num("-26");

        c = Num.add(a,b);
        ans = new Num("-100");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-25");

        c = Num.add(a,b);
        ans = new Num("25");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-50");
        b = new Num("25");

        c = Num.add(a,b);
        ans = new Num("-25");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-25");
        b = new Num("50");

        c = Num.add(a,b);
        ans = new Num("25");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("25");
        b = new Num("-50");

        c = Num.add(a,b);
        ans = new Num("-25");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-100");
        b = new Num("0");

        c = Num.add(a,b);
        ans = new Num("-100");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("0");
        b = new Num("0");

        c = Num.add(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-50");

        c = Num.add(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-0");
        b = new Num("0");

        c = Num.add(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);





    }

    @Test
    public void testSubtract() {
        Num a = new Num(10);
        Num b = new Num(5);

        Num c = Num.subtract(a,b);
        Num ans = new Num(5);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(5);
        b = new Num(10);

        c = Num.subtract(a,b);
        ans = new Num(-5);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(2000);
        b = new Num(3000);

        c = Num.subtract(a,b);
        ans = new Num(-1000);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(1000000000L);
        b = new Num(800000000000000L);

        c = Num.subtract(a,b);
        ans = new Num(799999000000000L);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(800000000000000L);
        b = new Num(800000000000000L);

        c = Num.subtract(a,b);
        ans = new Num(00000000000000L);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.subtract(a,b);
        ans = new Num("9223372036854775806");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-74);
        b = new Num(-24);

        c = Num.subtract(a,b);
        ans = new Num(-50);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-25);

        c = Num.subtract(a,b);
        ans = new Num(75);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-50);
        b = new Num(25);

        c = Num.subtract(a,b);
        ans = new Num(-75);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-25);
        b = new Num(50);

        c = Num.subtract(a,b);
        ans = new Num(-75);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(25);
        b = new Num(-50);

        c = Num.subtract(a,b);
        ans = new Num(75);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-100);
        b = new Num(0);

        c = Num.subtract(a,b);
        ans = new Num(-100);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(0);
        b = new Num(0);

        c = Num.subtract(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-50);

        c = Num.subtract(a,b);
        ans = new Num(100);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-0);
        b = new Num(0);

        c = Num.subtract(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);





        //String Constructor

        a = new Num("10");
        b = new Num("5");

        c = Num.subtract(a,b);
        ans = new Num("5");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("5");
        b = new Num("10");

        c = Num.subtract(a,b);
        ans = new Num("-5");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("2000");
        b = new Num("3000");

        c = Num.subtract(a,b);
        ans = new Num("-1000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("1000000000");
        b = new Num("800000000000000");

        c = Num.subtract(a,b);
        ans = new Num("-799999000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("800000000000000");
        b = new Num("800000000000000");

        c = Num.subtract(a,b);
        ans = new Num("00000000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.subtract(a,b);
        ans = new Num("9223372036854775806");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-74");
        b = new Num("-24");

        c = Num.subtract(a,b);
        ans = new Num("-50");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-25");

        c = Num.subtract(a,b);
        ans = new Num("75");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-50");
        b = new Num("25");

        c = Num.subtract(a,b);
        ans = new Num("-75");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-25");
        b = new Num("50");

        c = Num.subtract(a,b);
        ans = new Num("-75");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("25");
        b = new Num("-50");

        c = Num.subtract(a,b);
        ans = new Num("75");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-100");
        b = new Num("0");

        c = Num.subtract(a,b);
        ans = new Num("-100");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("0");
        b = new Num("0");

        c = Num.subtract(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-50");

        c = Num.subtract(a,b);
        ans = new Num("100");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-0");
        b = new Num("0");

        c = Num.subtract(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);
    }

    @Test
    public void testProduct() {
        Num a = new Num(10);
        Num b = new Num(5);

        Num c = Num.product(a,b);
        Num ans = new Num(50);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(5);
        b = new Num(10);

        c = Num.product(a,b);
        ans = new Num(50);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(2000);
        b = new Num(3000);

        c = Num.product(a,b);
        ans = new Num(6000000);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(1000000000L);
        b = new Num(8000000000000000000L);

        c = Num.product(a,b);
        ans = new Num("8000000000000000000000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(800000000000000L);
        b = new Num(800000000000000L);

        c = Num.product(a,b);
        ans = new Num("640000000000000000000000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.product(a,b);
        ans = new Num("9223372036854775807");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-74);
        b = new Num(-24);

        c = Num.product(a,b);
        ans = new Num(1776);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-25);

        c = Num.product(a,b);
        ans = new Num(-1250);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-50);
        b = new Num(25);

        c = Num.product(a,b);
        ans = new Num(-1250);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-25);
        b = new Num(50);

        c = Num.product(a,b);
        ans = new Num(-1250);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(25);
        b = new Num(-50);

        c = Num.product(a,b);
        ans = new Num(-1250);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-100);
        b = new Num(0);

        c = Num.product(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(0);
        b = new Num(0);

        c = Num.product(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-50);

        c = Num.product(a,b);
        ans = new Num(-2500);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-0);
        b = new Num(0);

        c = Num.product(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);





        //String Constructor

        a = new Num("10");
        b = new Num("5");

        c = Num.product(a,b);
        ans = new Num("50");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("5");
        b = new Num("10");

        c = Num.product(a,b);
        ans = new Num("50");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("2000");
        b = new Num("3000");

        c = Num.product(a,b);
        ans = new Num("6000000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("1000000000");
        b = new Num("800000000000000");

        c = Num.product(a,b);
        ans = new Num("800000000000000000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("800000000000000");
        b = new Num("800000000000000");

        c = Num.product(a,b);
        ans = new Num("640000000000000000000000000000");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.product(a,b);
        ans = new Num("9223372036854775807");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-74");
        b = new Num("-24");

        c = Num.product(a,b);
        ans = new Num("1776");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-25");

        c = Num.product(a,b);
        ans = new Num("-1250");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-50");
        b = new Num("25");

        c = Num.product(a,b);
        ans = new Num("-1250");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-25");
        b = new Num("50");

        c = Num.product(a,b);
        ans = new Num("-1250");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("25");
        b = new Num("-50");

        c = Num.product(a,b);
        ans = new Num("-1250");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-100");
        b = new Num("0");

        c = Num.product(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("0");
        b = new Num("0");

        c = Num.product(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-50");

        c = Num.product(a,b);
        ans = new Num("-2500");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-0");
        b = new Num("0");

        c = Num.product(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);
    }
    /*
        @org.junit.jupiter.api.Test
        void removeTrailingZeros() {
        }

        @org.junit.jupiter.api.Test
        void power() {
        }
    */
    @Test(expected = ArithmeticException.class)
    public void testDivide() {
        Num a = new Num(10);
        Num b = new Num(5);

        Num c = Num.divide(a,b);
        Num ans = new Num(2);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(5);
        b = new Num(10);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(2000);
        b = new Num(3000);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(1000000000L);
        b = new Num(800000000000000L);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num(800000000000000L);
        b = new Num(800000000000000L);

        c = Num.divide(a,b);
        ans = new Num(1);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.divide(a,b);
        ans = new Num("9223372036854775807");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-74);
        b = new Num(-24);

        c = Num.divide(a,b);
        ans = new Num(3);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(50);
        b = new Num(-25);

        c = Num.divide(a,b);
        ans = new Num(-2);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-50);
        b = new Num(25);

        c = Num.divide(a,b);
        ans = new Num(-2);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(-25);
        b = new Num(50);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num(25);
        b = new Num(-50);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);

        /*a = new Num(-100);
        b = new Num(0);

        c = Num.divide(a,b);
        ans = new Num(-100);

        Assert.
        Assert.assertArrayEquals(ArithmeticException,c.arr);*/
/*

        a = new Num(0);
        b = new Num(0);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);
*/

        a = new Num(50);
        b = new Num(-50);

        c = Num.divide(a,b);
        ans = new Num(-1);

        Assert.assertArrayEquals(ans.arr,c.arr);

        /*a = new Num(-0);
        b = new Num(0);

        c = Num.divide(a,b);
        ans = new Num(0);

        Assert.assertArrayEquals(ans.arr,c.arr);
*/




        //String Constructor

        a = new Num("10");
        b = new Num("5");

        c = Num.divide(a,b);
        ans = new Num("2");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("5");
        b = new Num("10");

        c = Num.divide(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("2000");
        b = new Num("3000");

        c = Num.divide(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("1000000000");
        b = new Num("800000000000000");

        c = Num.divide(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);


        a = new Num("800000000000000");
        b = new Num("800000000000000");

        c = Num.divide(a,b);
        ans = new Num("1");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("9223372036854775807");
        b = new Num("1");

        c = Num.divide(a,b);
        ans = new Num("9223372036854775807");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-74");
        b = new Num("-24");

        c = Num.divide(a,b);
        ans = new Num("3");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("50");
        b = new Num("-25");

        c = Num.divide(a,b);
        ans = new Num("-2");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-50");
        b = new Num("25");

        c = Num.divide(a,b);
        ans = new Num("-2");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("-25");
        b = new Num("50");

        c = Num.divide(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("25");
        b = new Num("-50");

        c = Num.divide(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);
/*

        a = new Num("-100");
        b = new Num("0");

        c = Num.divide(a,b);
        ans = new Num("-100");

        Assert.assertArrayEquals(ans.arr,c.arr);

        a = new Num("0");
        b = new Num("0");

        c = Num.divide(a,b);
        ans = new Num("0");

        Assert.assertArrayEquals(ans.arr,c.arr);
*/

        a = new Num("50");
        b = new Num("-50");

        c = Num.divide(a,b);
        ans = new Num("-1");

        Assert.assertArrayEquals(ans.arr,c.arr);

        /*a = new Num("-0");
        b = new Num("0");

        c = Num.divide(a,b);
        ans = new Num("0");*/

        //  Assert.assertArrayEquals(ans.arr,c.arr);
    }
/*
    @org.junit.jupiter.api.Test
    void getAbsNum() {
    }

    @org.junit.jupiter.api.Test
    void mod() {
    }

    @org.junit.jupiter.api.Test
    void squareRoot() {
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
    }

    @org.junit.jupiter.api.Test
    void printList() {
    }

    @org.junit.jupiter.api.Test
    void by2() {
    }

    @org.junit.jupiter.api.Test
    void evaluatePostfix() {
    }

    @org.junit.jupiter.api.Test
    void evaluateInfix() {
    }

    @org.junit.jupiter.api.Test
    void convertBase() {
    }

    @org.junit.jupiter.api.Test
    void toString() {
    }

    @org.junit.jupiter.api.Test
    void convertToLong() {
    }*/
}