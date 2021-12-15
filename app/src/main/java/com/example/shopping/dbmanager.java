package com.example.shopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbmanager extends SQLiteOpenHelper {
    private static final String dbname="Shopping";
    public dbmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     String query="create table record_data( id integer primary key autoincrement,name text,price text,quantity text,pic BLOB)";
     sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query="DROP TABLE IF EXISTS record_data";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);

    }
    public  String add_record(String name,String price,String quantity,byte[] pic)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("price",price);
        cv.put("quantity",quantity);
        cv.put("pic",pic);
        float res=sqLiteDatabase.insert("record_data",null,cv);
        if(res==-1)
            return "failed";
            else
                return "Successfully added";

    }

    public Cursor read_data()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String query="select * from record_data";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return  cursor;


    }

public void deleteItem(Data item) {
    SQLiteDatabase db = this. getWritableDatabase();
    String whereClause = "id=?";
    String whereArgs[] = {String.valueOf(item.getId())};
    db.delete("record_data", whereClause, whereArgs);
}
    public long updateItem(Data item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",item.getName());
        cv.put("price",item.getPrice());
        cv.put("quantity",item.getQuantity());
        cv.put("pic",item.getImage());
        String whereClause = "id=?";
        String whereArgs[] = {String.valueOf(item.getId())};
       // long temp=db.update("record_data", cv, "id=?", new String[]{item.getId()+""});
        long temp=db.update("record_data", cv, whereClause, whereArgs);
        db.close();

        return temp;

    }

}
