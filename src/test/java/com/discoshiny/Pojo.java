package com.discoshiny;

import java.util.List;

/**
 * Just for testing
 */
public class Pojo {
    private Integer theBusiness;
    private NestedPojo nestedPojo;
    private List<Object> waysInWhichMyLifeIsAFailure;

    public Integer getTheBusiness() {
        return theBusiness;
    }

    public void setTheBusiness(Integer theBusiness) {
        this.theBusiness = theBusiness;
    }

    public NestedPojo getNestedPojo() {
        return nestedPojo;
    }

    public void setNestedPojo(NestedPojo nestedPojo) {
        this.nestedPojo = nestedPojo;
    }

    public List<Object> getWaysInWhichMyLifeIsAFailure() {
        return waysInWhichMyLifeIsAFailure;
    }

    public void setWaysInWhichMyLifeIsAFailure(List<Object> waysInWhichMyLifeIsAFailure) {
        this.waysInWhichMyLifeIsAFailure = waysInWhichMyLifeIsAFailure;
    }
}
