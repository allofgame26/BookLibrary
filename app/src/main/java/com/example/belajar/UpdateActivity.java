package com.example.belajar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText title_input, author_input, pages_input;
    Button update_button;
    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input2);
        author_input = findViewById(R.id.author_input2);
        pages_input = findViewById(R.id.numberPage2);
        update_button = findViewById(R.id.updateButton);

        // memanggil dini
        getAndSetIntentData();

        //set Ttitle in Navbar Dynamic
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = title_input.getText().toString().trim();
                author = author_input.getText().toString().trim();
                pages = pages_input.getText().toString().trim();

                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                myDB.updateData(id, title, author, pages);

                finish();
            }
        });
    }

    void getAndSetIntentData (){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title")
                && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            // Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);
        } else {
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}