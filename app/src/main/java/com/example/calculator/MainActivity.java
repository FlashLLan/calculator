package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView display;

    // declaration of buttons
    private ImageButton  buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive,
            buttonSix, buttonSeven, buttonEight, buttonNine, buttonZero;
    private ImageButton  buttonPlus, buttonMinus, buttonMultiply, buttonDivision, buttonEquals, buttonClear, buttonRoot, buttonRemove, buttonChangeSign, buttonDot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize display and buttons
        initViews();
        setupButtonListeners();
    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        display = findViewById(R.id.textView_result);

        // number buttons
        buttonOne = findViewById(R.id.button_one);
        buttonTwo = findViewById(R.id.button_two);
        buttonThree = findViewById(R.id.button_three);
        buttonFour = findViewById(R.id.button_four);
        buttonFive = findViewById(R.id.button_five);
        buttonSix = findViewById(R.id.button_six);
        buttonSeven = findViewById(R.id.button_seven);
        buttonEight = findViewById(R.id.button_eight);
        buttonNine = findViewById(R.id.button_nine);
        buttonZero = findViewById(R.id.button_zero);

        // operation buttons
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonDivision = findViewById(R.id.button_division);
        buttonEquals = findViewById(R.id.button_equals);
        buttonClear = findViewById(R.id.button_clear);
        buttonRoot = findViewById(R.id.button_root);
        buttonRemove = findViewById(R.id.button_remove);
        buttonChangeSign = findViewById(R.id.button_change_sign);
        buttonDot = findViewById(R.id.button_dot);
        buttonDot.setOnClickListener(v -> appendDot());
    }

    private void appendDot() {
        String currentDisplay = display.getText().toString();
        if (currentDisplay.isEmpty() || currentDisplay.endsWith(" ")) {
            display.append("0.");
        } else {
            // split the display content by spaces to isolate numbers and operators
            String[] parts = currentDisplay.split(" ");
            String lastPart = parts[parts.length - 1];

            // check if the last part already contains a dot
            if (!lastPart.contains(".")) {
                display.append(".");
            }
        }
    }

    private void setupButtonListeners() {
        //listeners
        buttonOne.setOnClickListener(v -> appendNumber("1"));
        buttonTwo.setOnClickListener(v -> appendNumber("2"));
        buttonThree.setOnClickListener(v -> appendNumber("3"));
        buttonFour.setOnClickListener(v -> appendNumber("4"));
        buttonFive.setOnClickListener(v -> appendNumber("5"));
        buttonSix.setOnClickListener(v -> appendNumber("6"));
        buttonSeven.setOnClickListener(v -> appendNumber("7"));
        buttonEight.setOnClickListener(v -> appendNumber("8"));
        buttonNine.setOnClickListener(v -> appendNumber("9"));
        buttonZero.setOnClickListener(v -> appendNumber("0"));


        buttonPlus.setOnClickListener(v -> performOperation("+"));
        buttonMinus.setOnClickListener(v -> performOperation("-"));
        buttonMultiply.setOnClickListener(v -> performOperation("*"));
        buttonDivision.setOnClickListener(v -> performOperation("/"));
        buttonRoot.setOnClickListener(v -> performOperation("√"));
        buttonEquals.setOnClickListener(v -> calculateResult());
        buttonClear.setOnClickListener(v -> clearDisplay());
        buttonChangeSign.setOnClickListener(v -> changeSign());
        buttonRemove.setOnClickListener(v -> removeLastCharacter());
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }


    private void changeSign() {
        String currentDisplay = display.getText().toString();
        if (currentDisplay.isEmpty() || currentDisplay.equals(getString(R.string.error))) return;

        try {
            double value = Double.parseDouble(currentDisplay);
            value *= -1; // logic to change the sign
            display.setText(formatResult(value));
        } catch (NumberFormatException e) {
            display.setText(getString(R.string.error));
        }
    }

    private void removeLastCharacter() {
        String currentDisplay = display.getText().toString();
        if (!currentDisplay.isEmpty()) {
            display.setText(currentDisplay.substring(0, currentDisplay.length() - 1));
        }
    }



    private void appendNumber(String number) {
        display.append(number);
    }

    private void performOperation(String operator) {
        if (operator.equals("√")) {
            handleSquareRoot();
        } else {
            display.append(" " + operator + " ");
        }
    }

    private void calculateResult() {
        // simple split logic to get operands and operator
        String displayContent = display.getText().toString();
        String[] tokens = displayContent.split(" ");

        if (tokens.length < 3) {
            return;
        } // don't perform calculation if there are less than 3 elements

        try{

        double op1 = Double.parseDouble(tokens[0]); // first operand
        String operation = tokens[1]; // operator
        double op2 = Double.parseDouble(tokens[2]); // second operand
        double result;

        switch (operation) {
            case "+":
                result = op1 + op2;
                break;
            case "-":
                result = op1 - op2;
                break;
            case "*":
                result = op1 * op2;
                break;
            case "/":
                if (op2 == 0) {
                    display.setText(getString(R.string.error_division_by_zero));
                    return;
                }
                result = op1 / op2;
                break;
            default:
                display.setText(getString(R.string.error));
                return;
        }

        if (result == (long) result) {
            display.setText(String.format("%d", (long) result));
        } else {
            display.setText(String.format("%s", result));
        }
    } catch (NumberFormatException e) {
        display.setText(getString(R.string.invalid_input));
    }

    }

    private void clearDisplay() {
        display.setText("");
    }

    private void handleSquareRoot() {
        String input = display.getText().toString();
        input = input.replace("√", "").trim(); // ensure no sqrt symbol is left

        try {
            double value = Double.parseDouble(input);
            if (value < 0) {
                display.setText(getString(R.string.error));
            } else {
                display.setText(String.valueOf(Math.sqrt(value)));
            }
        } catch (NumberFormatException e) {
            display.setText(getString(R.string.invalid_input));
        }
    }

}
