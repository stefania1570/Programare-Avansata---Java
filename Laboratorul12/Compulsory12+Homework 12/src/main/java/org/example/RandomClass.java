package org.example;

import Annotations.Test;

public class RandomClass {
    public RandomClass(){
        System.out.println("Constructor.");
    }
    @Test
    public void m1() { }
    public void m2() { }

    @Test
    public void m3() {
        //throw new RuntimeException("Boom");
        System.out.println("Boom");
    }
    public void m4() { }

    @Test
    public static void m5() { }
    public static void m6() { }

    @Test
    public void m7() {
       // throw new RuntimeException("Crash");
        System.out.println("Crash");
    }
    public void m8() { }

}
