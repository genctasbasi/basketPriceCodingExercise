package com.escmobile.bjss.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.escmobile.bjss.R;

/**
 * Custom stepper control to increase / decrease value (add / remove products in our case)
 * Created by genctasbasi on 25/04/15.
 */
public class Stepper extends LinearLayout implements View.OnClickListener {

    private static final int MAX_VALUE = 99;

    Button buttonDecrease;
    Button buttonIncrease;
    TextView descriptionTextView;
    TextView valueTextView;

    public Stepper(Context context) {
        super(context);
        initialize();
    }

    public Stepper(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public Stepper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.stepper, this);
        this.buttonDecrease = (Button) findViewById(R.id.stepper_decrease);
        buttonDecrease.setOnClickListener(this);

        this.buttonIncrease = (Button) findViewById(R.id.stepper_increase);
        buttonIncrease.setOnClickListener(this);

        this.descriptionTextView = (TextView) findViewById(R.id.stepper_description);
        this.valueTextView = (TextView) findViewById(R.id.stepper_value);
    }

    @Override
    public void onClick(View v) {

        int newVal = 0;
        switch (v.getId()) {

            case R.id.stepper_decrease:
                newVal = Math.max(getValue() - 1, 0);
                valueTextView.setText(String.valueOf(newVal));
                break;

            case R.id.stepper_increase:
                newVal = Math.min(getValue() + 1, MAX_VALUE);
                valueTextView.setText(String.valueOf(newVal));
                break;
        }
    }

    public String getDescription() {
        return this.descriptionTextView.getText().toString();
    }

    public void setDescription(String description) {
        this.descriptionTextView.setText(description);
    }

    public int getValue() {

        String valueStr = this.valueTextView.getText().toString();

        try {
            return Integer.parseInt(valueStr);
        } catch (Exception e) {
        }

        return 0;
    }
}
