package za.cput.ict3.storagelandapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    DbHelper dbHelper;
    Button button_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper= new DbHelper(this);
        button_view= (Button) findViewById(R.id.button_View);
        viewAll();
    }

    public void viewAll()
    {
        button_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=dbHelper.getAllData();
                        if(res.getCount()==0){
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer= new StringBuffer();
                        while(res.moveToNext()) {
                            buffer.append("AccCode:"+res.getString(0)+"\n");
                            buffer.append("NAME:"+res.getString(1)+"\n");
                            buffer.append("SURNAME:"+res.getString(2)+"\n");
                            buffer.append("MARKS:"+res.getString(3)+"\n\n");
                        }
                        showMessage("Data",buffer.toString());


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
