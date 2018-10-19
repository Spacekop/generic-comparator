package com.discoshiny;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public final class Fixture {
    private Fixture() {
    }

    public static List<Pojo> exampleList() {
        List<Pojo> example = new ArrayList<>();
        example.add(example2());
        example.add(example1());
        return example;
    }

    static Pojo example1() {
        Pojo example = new Pojo();
        example.setTheBusiness(1);
        example.setWaysInWhichMyLifeIsAFailure(ImmutableList.of(
                "i have an absurd face",
                "birds hate me",
                "i am not a doctor"));
        example.setNestedPojo(new NestedPojo("no birds because they hate me"));

        return example;
    }

    static Pojo example2() {
        Pojo example = new Pojo();
        example.setTheBusiness(2);
        example.setWaysInWhichMyLifeIsAFailure(ImmutableList.of(
                "never eaten a taco",
                "annoyed by certain shapes",
                "i am over 20 years old"));
        example.setNestedPojo(new NestedPojo("sparrow"));

        return example;
    }
}
