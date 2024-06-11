package com.example.calc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> numbers;
    private TextView result;
    private int resultNum = 0;
    private String action = "+";
    private Calculations calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        numbers = new ArrayList<>();
        result = findViewById(R.id.result);
        calc = new Calculations();
        setButtons();
        action = "+";
    }

    private void setButtons() {
        try {
            for (int i = 0; i < 10; i++) {
                Field idField = R.id.class.getDeclaredField("button_"+i);
                Button button = findViewById(idField.getInt(idField));
                numbers.add(button);
                int finalI = i;
                button.setOnClickListener(view -> {clickNumber(finalI);});
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        findViewById(R.id.button_reset).setOnClickListener(view -> {reset();});
        findViewById(R.id.button_plus).setOnClickListener(view -> {action("+");});
        findViewById(R.id.button_multiply).setOnClickListener(view -> {action("*");});
        findViewById(R.id.button_minus).setOnClickListener(view -> {action("-");});
        findViewById(R.id.button_devide).setOnClickListener(view -> {action("/");});
        findViewById(R.id.button_result).setOnClickListener(view -> {setResult();});
    }

    private void action(String s) {
        resultNum = calc.calculate(resultNum, Integer.valueOf((String) result.getText()), action);
        reset();
        action = s;
    }

    private void setResult() {
        action("+");
        result.setText(String.valueOf(resultNum));
        resultNum=0;
    }

    private void reset() {
        result.setText("");
    }

    private void clickNumber(int i) {
        result.setText((String) result.getText()+i);
    }

}