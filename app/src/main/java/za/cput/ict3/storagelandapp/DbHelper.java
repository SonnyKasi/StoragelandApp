package za.cput.ict3.storagelandapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Author: Sonwabo Kasi
 * Class: Part Time
 * Student number: 214293939
 * Class Description:
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Storage.DB";
    private static final String TABLE_NAME = "ClientStoreDB";
    private static final String Col_1 ="AccountCode";
    private static final String Col_2 ="Name & Surname";
    private static final String Col_3 ="Cell No";
    private static final String Col_4 ="Date";
    private static final String Col_5 ="Store No";
    private static final String Col_6 ="Amount";
    private static final String Col_7 = "Email";
    private static final String Col_8 ="Address";

    private static final String TABLE_NAME1 = "AdminTable";
    private static final String ColumnID = "Id";
    private static final String ColumnUsername = "Username";
    private static final String ColumnFirstName = "FirstName";
    private static final String ColumnPassword = "Password";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ACCOUNTCODE INTEGER PRIMARY KEY AUTOINCREMENT,NAME STRING,CELLNUM LONG,DATE,STORENUM INTEGER, AMOUNT DOUBLE,EMAIL STRING,ADDRESS STRING)");
        db.execSQL("create AdminTable" +TABLE_NAME1 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME STRING,FIRSTNAME STRING,PASSWORD)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name, String CellNo,String Date,String StoreNo,String Amount,String Address,String Email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, Name);
        contentValues.put(Col_3, CellNo);
        contentValues.put(Col_4, Date);
        contentValues.put(Col_5, StoreNo);
        contentValues.put(Col_6,Amount);
        contentValues.put(Col_7,Address);
        contentValues.put(Col_8,Email);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public Cursor viewClientContact()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select Name & CellNo from "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String AccountCode, String Name, String CellNo,String StoreNo,String Amount )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1,AccountCode);
        contentValues.put(Col_2,Name);
        contentValues.put(Col_3,CellNo);
        contentValues.put(Col_5,StoreNo);
        contentValues.put(Col_6,Amount);
        db.update(TABLE_NAME,contentValues,"AccountCode=?",new String[] {AccountCode});
        return true;
    }

    public boolean Register(String Username, String FirstName, String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(ColumnUsername,Username);
        Values.put(ColumnFirstName,FirstName);
        Values.put(ColumnPassword, Password);

        db.insert(TABLE_NAME1, null, Values);
        db.close();
        return false;
    }

    public Boolean checkUsernamePassword(String Username, String Password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[]{Username, Password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Integer deleteData(String AccountCode){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"AccountCode=?",new String[] {AccountCode});

    }


}



