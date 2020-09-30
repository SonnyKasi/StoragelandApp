package za.cput.ict3.storagelandapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClient extends AppCompatActivity {
    DbHelper dbHelper;
    Button button_add;
    Button button_update;
    Button button_delete;
    EditText editName,editEmail,editAddress,cellNr,storeNo,occuDate,accCode,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        editName=(EditText)findViewById(R.id.editText_ClientName);
        editEmail=(EditText)findViewById(R.id.editText_email);
        occuDate=(EditText)findViewById(R.id.editText_date);
        storeNo=(EditText)findViewById(R.id.editText_storeNr);
        editAddress=(EditText)findViewById(R.id.editText_Address);
        accCode=(EditText)findViewById(R.id.editText_AccountCode);
        cellNr =(EditText)findViewById(R.id.editText_Cell);
        amount=(EditText)findViewById(R.id.editText_Amount);
        button_add= (Button) findViewById(R.id.button_Add);
        button_update=(Button) findViewById(R.id.button_Update);
        button_delete=(Button) findViewById(R.id.button_Delete);
        AddData();
        UpdateData();
        DeleteData();
    }
        public void UpdateData ()
        {
            button_update.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean IsUpdate = dbHelper.updateData(accCode.getText().toString(), editName.getText().toString(),cellNr.getText().toString(), .getText().toString());
                            if (IsUpdate == true) {
                                Toast.makeText(AddClient.this, "Data updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddClient.this, "Data not updated", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }
    }
        public void DeleteData ()
        {
            button_delete.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Integer deletedRows = dbHelper.deleteData(accCode.getText().toString());
                            if (deletedRows > 0) {
                                Toast.makeText(AddClient.this, "Data deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AddClient.this, "Data not deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

        }

        public void AddData()
        {
            button_add.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean Isinserted= dbHelper.insertData(accCode.getText().toString(),editName.getText().toString(),
                                    editAddress.getText().toString(),
                                    editEmail.getText().toString(),cellNr.getText().toString(),storeNo.getText(),
                                    occuDate.getText().toString(),amount.getText());
                            if (Isinserted==true)
                            {
                                Toast.makeText(AddClient.this, "Data inserted", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(AddClient.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        }
    }
}
