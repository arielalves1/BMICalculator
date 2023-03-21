package com.nomorefat.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class variables are also called 'Fields'
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews(){
        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               double bmiResult =  calculateBmi(); // calling the method that calculates the bmi

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);
                
                if (age > 18){
                    displayResult(bmiResult);
                    
                }else{
                displayGuidance(bmiResult);
                }

                displayResult(bmiResult); // calling the method that displays the result
            }
        });
    }


    private double calculateBmi() {
    String feetText = feetEditText.getText().toString();
    String inchesText = inchesEditText.getText().toString();
    String weightText = weightEditText.getText().toString();
        String ageText = ageEditText.getText().toString();

    //Converting the 'number' Strings into 'int' variables;
        int age = Integer.parseInt(ageText);

    int feet = Integer.parseInt(feetText);
    int inches = Integer.parseInt(inchesText);
    int weight = Integer.parseInt(weightText);

    int totalInches = (feet * 12) + inches;

    // Height in Meters is the Inches multiplied by 0.0254
    double heightInMeters  = totalInches * 0.0254;

    //BMI Formula = weight in kg divided by height in meters squared

        return weight / (heightInMeters * heightInMeters);


    }


    private  void displayResult(double bmi){

        DecimalFormat myDecimalFormatter = new  DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if(bmi < 18.5){
            //Display Underweight
            fullResultString = bmiTextResult + "- You are Underweight";
        }else if (bmi > 25){
            //Display overweight
            fullResultString = bmiTextResult + "- You are Overweight";
        }else {
            //Display Healthy
            fullResultString = bmiTextResult + "- You are a Healthy weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {

        DecimalFormat myDecimalFormatter = new  DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if(maleButton.isChecked()){
            //Display Boy Guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for boys. ";
        }else if(femaleButton.isChecked()){
            //Display Girl guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for girls. ";

        }else{
            //Display general guidance
            fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range. ";

        }
        resultText.setText(fullResultString);
    }


}