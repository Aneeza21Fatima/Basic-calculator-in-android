package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView  txt_result;

    MaterialButton button_c, button_open_bracket, button_close_bracket, button_slash;
    MaterialButton button_7, button_8 , button_9, button_multiplication;
    MaterialButton button_4 , button_5, button_6, button_subtraction;
    MaterialButton button_1 , button_2, button_3, button_addition;
    MaterialButton button_ac , button_0, button_dot, button_equal_to;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txt_result=findViewById(R.id.solution_text);

        assign_id(button_c,R.id.button_c);
        assign_id(button_open_bracket,R.id.button_open_bracket);
        assign_id(button_close_bracket,R.id.button_close_bracket);
        assign_id(button_slash,R.id.button_slash);
        assign_id(button_multiplication,R.id.button_multiplication);
        assign_id(button_subtraction,R.id.button_subtraction);
        assign_id(button_addition,R.id.button_Addition);
        assign_id(button_7,R.id.button_7);
        assign_id(button_8,R.id.button_8);
        assign_id(button_9,R.id.button_9);
        assign_id(button_4,R.id.button_4);
        assign_id(button_5,R.id.button_5);
        assign_id(button_6,R.id.button_6);
        assign_id(button_1,R.id.button_1);
        assign_id(button_2,R.id.button_2);
        assign_id(button_3,R.id.button_3);
        assign_id(button_ac,R.id.button_ac);
        assign_id(button_0,R.id.button_0);
        assign_id(button_dot,R.id.button_dot);
        assign_id(button_equal_to,R.id.button_equal_to);

    }

    public  void assign_id(MaterialButton btn , int id ){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String button_text = button.getText().toString();
        String data_to_calculate = txt_result.getText().toString();

        if (button_text.equals("AC")) {
            clearInput();
        } else if (button_text.equals("=")) {
            evaluateExpression();
        } else if (button_text.equals("C")) {
            clearLastCharacter(data_to_calculate);
        } else {
            appendInput(button_text, data_to_calculate);
        }
    }

    private void clearInput() {
        txt_result.setText("");

    }

    private void evaluateExpression() {
        String data_to_calculate = txt_result.getText().toString();
        String result = getResult(data_to_calculate);
        if (!result.equals("Invalid expression")) {
            txt_result.setText(result);
        }
    }

    private void clearLastCharacter(String data) {
        if (data.length() > 0) {
            data = data.substring(0, data.length() - 1);
            txt_result.setText(data);
        }
    }

    private void appendInput(String button_text, String data) {
        data = data + button_text;
        txt_result.setText(data);
    }


    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String final_result = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (final_result.endsWith(".0")) {
                final_result = final_result.replace(".0", "");
            }
            return final_result;
        } catch (Exception e) {
            return "Invalid expression";
        }
    }

}