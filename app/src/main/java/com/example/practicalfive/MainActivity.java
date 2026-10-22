package com.example.practicalfive;  // 你的包名

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText municipalityEditText;
    private TextProcessor textProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textProcessor = new TextProcessor();
        municipalityEditText = findViewById(R.id.municipalityEditText);

        municipalityEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    handleInput();
                    return true;
                }
                return false;
            }
        });
    }

    private void handleInput() {
        String input = municipalityEditText.getText().toString();
        String result = textProcessor.processMunicipality(input);

        if (result == null) {
            if (input.trim().isEmpty()) {
                municipalityEditText.setError("Please enter a municipality name");
            } else {
                municipalityEditText.setError("Invalid municipality. Try Beijing, Shanghai, Tianjin, or Chongqing");
            }
        } else {
            Intent intent = new Intent(MainActivity.this, Municipality.class);
            intent.putExtra("municipality_name", result);
            startActivity(intent);
        }
    }
}
