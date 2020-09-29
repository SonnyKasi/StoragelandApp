package za.cput.ict3.storagelandapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.os.Build.ID;

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
    private static final String Col_6 ="AMOUNT";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ACCOUNTCODE INTEGER PRIMARY KEY AUTOINCREMENT,NAME,CELL NUM I,DATE,STORE NUM INTEGER,AMOUNT DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertInfo(Integer AccountCode, String Name, Long CellNo,String Date,String StoreNo,Double Amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, Name);
        contentValues.put(Col_3, CellNo);
        contentValues.put(Col_4, Date);
        contentValues.put(Col_5, StoreNo);
        contentValues.put(Col_6,Amount);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData(Integer AccountCode, String Name, Long CellNo,String Date,String StoreNo,Double Amount )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1,AccountCode);
        contentValues.put(Col_2, Name);
        contentValues.put(Col_3, CellNo);
        contentValues.put(Col_4, Date);
        contentValues.put(Col_5, StoreNo);
        contentValues.put(Col_6,Amount);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[] {ID});
        return true;
    }

    public Integer deleteData(String Code){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[] {ID});

    }
}



