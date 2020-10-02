package za.cput.ict3.storagelandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

        EditText editText;
        //TextView textView;
        Button button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            editText = (EditText) findViewById(R.id.editText_UserName);
            button = (Button) findViewById(R.id.button_Login);
           //textView = (TextView)findViewById(R.id.textView_AppName);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = editText.getText().toString();

                    Intent intent = new Intent(getApplicationContext(),Home.class);
                    intent.putExtra("message", str);
                    startActivity(intent);

                }
            });

        }
}
