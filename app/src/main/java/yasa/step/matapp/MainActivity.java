package yasa.step.matapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// a
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int zagr = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonToActivity3 = (Button) findViewById(R.id.button2);
        buttonToActivity3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent buttonToActivity3 = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(buttonToActivity3);
    }
}