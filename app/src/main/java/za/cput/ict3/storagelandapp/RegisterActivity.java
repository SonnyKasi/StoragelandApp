package za.cput.ict3.storagelandapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    public EditText edtfname,edtUsername,edtPassword;
    public Button btnCreate, btnBack;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        dbHelper = new DbHelper(this);
        edtfname = (EditText) findViewById(R.id.edtfname);
        edtUsername = (EditText) findViewById(R.id.edtusername);
        edtPassword = (EditText) findViewById(R.id.edtpassword);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        addUser();
        btnBack = (Button) findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
         void addUser()
        {
            btnCreate.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isInserted = dbHelper.Register(edtfname.getText().toString(),edtUsername.getText().toString(),edtPassword.getText().toString());
                            if (isInserted == true){

                                Toast.makeText(RegisterActivity.this,"Information inserted",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegisterActivity.this,"Information not inserted",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }
}

