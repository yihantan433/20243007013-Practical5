package com.example.practicalfive;  // 你的包名

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {

    private TextProcessor textProcessor;
    private String municipalityName;
    private String officialUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);



        textProcessor = new TextProcessor();

        // 获取数据
        Intent intent = getIntent();
        String inputName = intent.getStringExtra("municipality_name");
        municipalityName = textProcessor.processMunicipality(inputName);

        if (municipalityName == null) {
            Toast.makeText(this, "Invalid data", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // 获取 UI
        TextView nameTextView = findViewById(R.id.municipalityNameTextView);
        ImageView imageView = findViewById(R.id.municipalityImageView);
        TextView infoTextView = findViewById(R.id.infoTextView);
        Button visitButton = findViewById(R.id.visitSiteButton);

        // 设置资源
        int colorRes, imageRes, textIndex;

        switch (municipalityName) {
            case "Beijing":
                colorRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                textIndex = 0;
                officialUrl = "https://english.beijing.gov.cn/";
                break;
            case "Shanghai":
                colorRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                textIndex = 1;
                officialUrl = "https://english.shanghai.gov.cn/";
                break;
            case "Tianjin":
                colorRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                textIndex = 2;
                officialUrl = "https://en.tj.gov.cn/";
                break;
            case "Chongqing":
                colorRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                textIndex = 3;
                officialUrl = "https://english.cq.gov.cn/";
                break;
            default:
                finish();
                return;
        }

        int color = getColor(colorRes);

        nameTextView.setText(municipalityName);
        nameTextView.setBackgroundColor(color);

        imageView.setImageResource(imageRes);

        String[] municipalities = getResources().getStringArray(R.array.municipalities_array);
        infoTextView.setText(municipalities[textIndex]);
        infoTextView.setTextColor(color);

        visitButton.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));

        // 隐式 Intent
        visitButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(officialUrl));
            try {
                startActivity(browserIntent);
            } catch (android.content.ActivityNotFoundException e) {
                Toast.makeText(this, "No browser available to open the website", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
