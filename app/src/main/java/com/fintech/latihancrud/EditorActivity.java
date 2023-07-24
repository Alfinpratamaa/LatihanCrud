package com.fintech.latihancrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fintech.latihancrud.helper.DatabaseHelper;

public class EditorActivity extends AppCompatActivity {
    EditText nameEt, emailEt;
    Button btnSubmit;
    DatabaseHelper db; // Declare the DatabaseHelper variable.

    private String id, name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        db = new DatabaseHelper(this); // Initialize the DatabaseHelper instance.

        nameEt = findViewById(R.id.nameEt);
        emailEt = findViewById(R.id.emailEt);
        btnSubmit = findViewById(R.id.btnSubmit);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        if (id == null || id.equals("")) {
            setTitle("tambah user");
        } else {
            setTitle("edit user");
            nameEt.setText(name);
            emailEt.setText(email);
        }
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("") ){
                        save();
                    }else{
                        edit();
                    }
                }catch (Exception e) {
                    Log.e("saving ",e.getMessage());

                }
            }
        });
    }
    private void save(){
        if (String.valueOf(nameEt.getText()).equals("") || String.valueOf(emailEt.getText()).equals("")){
            Toast.makeText(this, "Lengkapi dulu..", Toast.LENGTH_SHORT).show();
        }else{
            db.insertData(nameEt.getText().toString(), emailEt.getText().toString());
            finish();
        }
    }
    private void edit(){
        if (String.valueOf(nameEt.getText()).equals("") || String.valueOf(emailEt.getText()).equals("")){
            Toast.makeText(this, "Lengkapi dulu..", Toast.LENGTH_SHORT).show();
        }else{
            db.updateData(Integer.parseInt(id),nameEt.getText().toString(),nameEt.getText().toString());
            finish();
        }
    }
}