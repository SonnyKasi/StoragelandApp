package za.cput.ict3.storagelandapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    DbHelper dbHelper;
    Button button_view,button_AvailStore,button_NewClient,button_viewClientContact;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper= new DbHelper(this);
        button_AvailStore = (Button)findViewById(R.id.button_AvailStores);
        viewAllStores();
        button_view= (Button) findViewById(R.id.button_ViewClients);
        viewAll();
        button_viewClientContact = (Button)findViewById(R.id.button_ClientContact);
        viewClientContact();

        textView = findViewById(R.id.textView_UserName);
        textView.setText(getIntent().getStringExtra("message"));

        button_NewClient = (Button) findViewById(R.id.button_NewClient);
        button_NewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddClient.class);
                startActivity(intent);
            }
        });
    }

    private void viewAllStores() {
    }

    public void viewAll()
    {
        button_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=dbHelper.getAll();
                        if(res.getCount()==0){
                            showMessage("ERROR","No clients exist");
                            return;
                        }

                        StringBuffer buffer= new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("AccCode:"+res.getString(0)+"\n");
                            buffer.append("NAME:"+res.getString(1)+"\n");
                            buffer.append("SURNAME:"+res.getString(2)+"\n");
                            buffer.append("Cell No:"+res.getString(3)+"\n\n");
                            buffer.append("STORE NO:"+res.getString(5)+"\n\n");
                            buffer.append("AMOUNT:"+res.getString(6)+"\n\n");
                        }
                        showMessage("Existing Clients",buffer.toString());
                    }
                }
        );
    }

    public void viewClientContact()
    {
        button_viewClientContact.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = dbHelper.viewClientContact();
                        if (res.getCount() == 0) {
                            showMessage("ERROR", "No clients exist");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("AccCode:" + res.getString(0) + "\n");
                            buffer.append("NAME:" + res.getString(2) + "\n");
                            buffer.append("Cell No:" + res.getString(3) + "\n");
                        }
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
