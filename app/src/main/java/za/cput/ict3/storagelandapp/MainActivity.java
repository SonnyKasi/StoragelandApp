package za.cput.ict3.storagelandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText, editTextPw;
    //TextView textView;
    Button button, button_Register;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText_UserName);
        button = (Button) findViewById(R.id.button_Login);
        button_Register = (Button) findViewById(R.id.button_Register);
        editTextPw = (EditText) findViewById(R.id.editText_Pasword);

        button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                String pw = editTextPw.getText().toString();
                if (str.equals("") || pw.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter your details: ", Toast.LENGTH_LONG).show();
                else {

                    Boolean checkUserPassword = dbHelper.checkUsernamePassword(str, pw);
                    if (checkUserPassword == true) {
                        Toast.makeText(MainActivity.this, "You have successfully logged in: ", Toast.LENGTH_LONG).show();
                        Intent LoginIntent = new Intent(getApplicationContext(), Home.class);
                        startActivity(LoginIntent);
                    } else {
                        Toast.makeText(MainActivity.this, "Sorry, invalid credentials: ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}






