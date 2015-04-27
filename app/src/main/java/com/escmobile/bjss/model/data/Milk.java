package com.escmobile.bjss.model.data;

/**
 * Created by genctasbasi on 25/04/15.
 */
public class Milk implements Product {
    public  static final String NAME = "Milk";
    public  static final float BASE_PRICE = 1.30f;

    @Override
    public float getPrice() {
        return 0;
    }
}
