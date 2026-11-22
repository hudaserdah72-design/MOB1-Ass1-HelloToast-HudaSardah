package com.example.hellotoast;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";

    private int count = 0;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(mainBinding.main, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout());
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT);
        }
        updateScreen();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
    }

    public void showToast(View view) {
        Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT).show();
    }

    public void showCount(View view) {
        count++;
        updateScreen();
    }

    public void showZero(View view) {
        count = 0;
        updateScreen();
    }

    private void updateScreen() {
        mainBinding.showCount.setText(String.valueOf(count));

        int countColor = count % 2 == 0 ? R.color.blue : R.color.green;
        mainBinding.buttonCount.setBackgroundColor(ContextCompat.getColor(this, countColor));

        int zeroColor = count == 0 ? R.color.gray : R.color.orange;
        mainBinding.buttonZero.setBackgroundColor(ContextCompat.getColor(this, zeroColor));
    }
}
