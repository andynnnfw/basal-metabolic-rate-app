package com.example.bmr_apk;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText inputWeight, inputHeight, inputAge;
    private MaterialButtonToggleGroup toggleGender;
    private MaterialButton btnMale, btnFemale;
    private MaterialCardView resultCard;
    private TextView resultValue;
    private Button btnCalculate;

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

        inputWeight = findViewById(R.id.inputWeight);
        inputHeight = findViewById(R.id.inputHeight);
        inputAge = findViewById(R.id.inputAge);
        resultCard = findViewById(R.id.resultCard);
        resultValue = findViewById(R.id.resultValue);
        btnMale = findViewById(R.id.btnMale);
        btnFemale = findViewById(R.id.btnFemale);
        btnCalculate = findViewById(R.id.btnCalculate);
        toggleGender = findViewById(R.id.toggleGender);

        btnCalculate.setOnClickListener(v -> calculateBmr());
    }
    public void calculateBmr() {
        String weightStr = inputWeight.getText().toString().trim();
        String heightStr = inputHeight.getText().toString().trim();
        String ageStr = inputAge.getText().toString().trim();

        if (weightStr.isEmpty() || heightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);
        int age = Integer.parseInt(ageStr);
        double bmr = 0;

        int checkedId = toggleGender.getCheckedButtonId();
        double result;
        if (checkedId == R.id.btnMale) {
            if (age >= 0 && age <= 3) {
                result = (59.512 * weight) - 30.4;
            } else if (age > 3 && age <= 10) {
                result = (22.706 * weight) + 504.3;
            } else if (age > 10 && age <= 18) {
                result = (0.056 * weight + 2.800) * 239;
            } else if (age > 18 && age <= 30) {
                result = (15.057 * weight) + 692.2;
            } else if (age > 30 && age <= 60) {
                result = (11.472 * weight) + 873.1;
            } else {
                result = (11.711 * weight) + 587.7;
            }
        } else if (checkedId == R.id.btnFemale) {
            if (age >= 0 && age <= 3) {
                result = (58.317 * weight) - 31.1;
            } else if (age > 3 && age <= 10) {
                result = (20.315 * weight) + 485.9;
            } else if (age > 10 && age <= 18) {
                result = (13.384 * weight) + 692.6;
            } else if (age > 18 && age <= 30) {
                result = (14.818 * weight) + 486.6;
            } else if (age > 30 && age <= 60) {
                result = (8.126 * weight) + 845.6;
            } else {
                result = (9.082 * weight) + 658.5;
            }
        } else {
            throw new IllegalArgumentException("Idade inválida!");
        }
        resultValue.setText(String.format("%.2f kcal/dia", result));
    }
}