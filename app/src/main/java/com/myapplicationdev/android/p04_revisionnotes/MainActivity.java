package com.myapplicationdev.android.p04_revisionnotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etContent;
    RadioGroup rg;
    Note note;
    Button submit, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etContent = findViewById(R.id.editTextNote);
        rg = findViewById(R.id.radioGroupStars);
        submit = findViewById(R.id.buttonInsertNote);
        view = findViewById(R.id.buttonShowList);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String content = etContent.getText().toString();
                int selectedId = rg.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                int selectedButton = Integer.parseInt(radioButton.getText().toString());
                if (etContent.getText() != null) {
                    db.insertData(content, selectedButton);
                    Toast.makeText(MainActivity.this, "added!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "not added! :'(", Toast.LENGTH_SHORT).show();
                }

            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


    }
}
