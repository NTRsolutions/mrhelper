package com.apitechnosoft.mrhelper.circlecustomprogress;

import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Neeraj on 8/18/2017.
 */

public class KeyFrameInterpolator implements Interpolator {

    private TimeInterpolator interpolator;
    private float[] fractions;


    public static KeyFrameInterpolator easeInOut(float... fractions) {
        KeyFrameInterpolator interpolator = new KeyFrameInterpolator(Ease.inOut());
        interpolator.setFractions(fractions);
        return interpolator;
    }

    public KeyFrameInterpolator(TimeInterpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void setFractions(float... fractions) {
        this.fractions = fractions;
    }

    @Override
    public synchronized float getInterpolation(float input) {
        if (fractions.length > 1) {
            for (int i = 0; i < fractions.length - 1; i++) {
                float start = fractions[i];
                float end = fractions[i + 1];
                float duration = end - start;
                if (input >= start && input <= end) {
                    input = (input - start) / duration;
                    return start + (interpolator.getInterpolation(input)
                            * duration);
                }
            }
        }
        return interpolator.getInterpolation(input);
    }
}
