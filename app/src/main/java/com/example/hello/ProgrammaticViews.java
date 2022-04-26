package com.example.hello;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ProgrammaticViews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        //----Create a TextView------
        TextView textView = new TextView(this);

        textView.setText("This TextView is dynamically created");
        textView.setLayoutParams(params);

        //--Create A EditText------------------
        EditText editText = new EditText(this);
        editText.setLayoutParams(params);

        //----Create a CheckBox-------------
        CheckBox checkBox = new CheckBox(this);
        checkBox.setLayoutParams(params);

        //--- Create a RadioGroup---------------
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setLayoutParams(params);

        //--------Create a RadioButton----------
        RadioButton radioButton = new RadioButton(this);
        radioButton.setLayoutParams(params);

        //-----Create a Button--------
        Button button = new Button(this);
        button.setText("This Button is dynamically created");
        button.setLayoutParams(params);

        //---Add all elements to the layout
        linearLayout.addView(textView);
        linearLayout.addView(checkBox);
        linearLayout.addView(editText);
        linearLayout.addView(radioGroup);
        linearLayout.addView(radioButton);

        linearLayout.addView(button);

        //---Create a layout param for the layout-----------------
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        this.addContentView(linearLayout, layoutParams);
    }
}