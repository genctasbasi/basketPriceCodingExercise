package com.escmobile.bjss.model.data;

/**
 * Created by genctasbasi on 25/04/15.
 */
public class Beans implements Product {
    public static final String NAME = "Beans";
    public static final float BASE_PRICE = 0.73f;

    @Override
    public float getPrice() {
        return 0;
    }
}
