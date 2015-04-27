package com.escmobile.bjss.model.data;

/**
 * Created by genctasbasi on 25/04/15.
 */
public class Eggs implements Product {
    public static final String NAME = "Eggs";
    public static final float BASE_PRICE = 2.10f;

    @Override
    public float getPrice() {
        return 0;
    }
}
