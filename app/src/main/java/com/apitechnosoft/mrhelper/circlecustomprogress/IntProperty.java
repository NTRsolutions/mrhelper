package com.apitechnosoft.mrhelper.circlecustomprogress;

import android.util.Property;

/**
 * Created by Neeraj on 8/18/2017.
 */

public abstract class IntProperty<T> extends Property<T, Integer> {

    public IntProperty(String name) {
        super(Integer.class, name);
    }


    public abstract void setValue(T object, int value);

    @Override
    final public void set(T object, Integer value) {
        setValue(object, value);
    }

}