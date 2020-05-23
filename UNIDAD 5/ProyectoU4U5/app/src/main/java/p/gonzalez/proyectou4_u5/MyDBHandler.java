package p.gonzalez.proyectou4_u5;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "telefonosDB.db";
    public static final String TABLE_NAME = "Telefonos";
    public static final String COLUMN_ID = "TelefonoID";
    public static final String COLUMN_NAME = "TelefonoNombre";
    //initialize the database

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //daR ESPACIO
        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY,"+COLUMN_NAME+" TEXT )";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {}
    public String loadHandler() {
        String result="";
        String query="Select * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int result_0=cursor.getInt(0);
            String result_1=cursor.getString(1);
            result+=String.valueOf(result_0)+" "+result_1;
            System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
    public void addHandler(Telefono telefono) {
        ContentValues values=new ContentValues();
        values.put(COLUMN_ID, telefono.getId());
        values.put(COLUMN_NAME,telefono.getTelefono());
        SQLiteDatabase db=this.getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public Telefono findHandler(int id) {
        String query="Select * FROM "+TABLE_NAME+" WHERE "+COLUMN_ID+" = "
                +id;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Telefono telefono = new Telefono();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            telefono.setId(Integer.parseInt(cursor.getString(0)));
            telefono.setTelefono(cursor.getString(1));
            cursor.close();
        } else {
            telefono = null;
        }
        db.close();
        return telefono;
    }
    public boolean deleteHandler(int ID) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Telefono telefono = new Telefono();
        if (cursor.moveToFirst()) {
            telefono.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[] {
                String.valueOf(telefono.getId())
            });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    public boolean updateHandler(int ID, String telefono) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_NAME, telefono);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}