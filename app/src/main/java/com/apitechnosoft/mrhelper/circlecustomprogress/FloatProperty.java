package com.apitechnosoft.mrhelper.circlecustomprogress;

import android.util.Property;

/**
 * Created by Neeraj on 8/18/2017.
 */

public abstract class FloatProperty<T> extends Property<T, Float> {

    public FloatProperty(String name) {
        super(Float.class, name);
    }

    /**
     * A type-specific override of the {@link #set(Object, Float)} that is faster when dealing
     * with fields of type <code>float</code>.
     */
    public abstract void setValue(T object, float value);

    @Override
    final public void set(T object, Float value) {
        setValue(object, value);
    }

}
