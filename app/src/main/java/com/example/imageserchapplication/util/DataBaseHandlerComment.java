package com.example.imageserchapplication.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.imageserchapplication.pojo.Labels;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBaseHandlerComment extends SQLiteOpenHelper {
    private static final ArrayList<HashMap<String, String>> userList = new ArrayList<>();
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "requestformdb";

    // Contacts table name
    private static final String TABLE_CONTACTS = "requestform";



    // Contacts Table Columns names
    private static final String KEY_ID = "s_id";

    private static final String KEY_LABEL_ID = "Id";
    private static final String KEY_LABEL_NAME = "LabelName";
    private static final String KEY_LABEL_COMMENT = "LabelComments";



    public DataBaseHandlerComment(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LABEL_ID + " TEXT,"
                + KEY_LABEL_NAME + " TEXT," + " TEXT,"+KEY_LABEL_COMMENT + " TEXT"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);


    }
    
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        
        // Create tables again
        onCreate(db);
    }
    public void deleteRow()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS);
        db.close();
    }
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    
    public// Adding new contact
    void addContact(Labels contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


            values.put(KEY_LABEL_ID, contact.getLabel_id()); // Contact Phone
            values.put(KEY_LABEL_NAME, contact.getLabel_name()); // Contact Phone
            values.put(KEY_LABEL_COMMENT, contact.getLabel_comment()); // Contact Phone

            // Inserting Row
            db.insertWithOnConflict(TABLE_CONTACTS, null, values, SQLiteDatabase.CONFLICT_REPLACE);


            db.close(); // Closing database connection
        }





    // Getting single contact
    public Labels getCommentData( String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        KEY_LABEL_ID, KEY_LABEL_NAME,KEY_LABEL_COMMENT },  KEY_LABEL_ID+"=?",
                new String[] { id}, null, null, null, null);


        if (cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();

            Labels contact = new Labels(cursor.getString(0), cursor.getString(1), cursor.getString(2));

            if (contact != null) {
                // return contact
                return contact;
            } else {
                return null;
            }
        }
        return null;

    }

    public ArrayList<HashMap<String, String>> GetUserByUserId(String imageid){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+ TABLE_CONTACTS;
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_LABEL_ID, KEY_LABEL_NAME, KEY_LABEL_COMMENT}, KEY_LABEL_ID+ "=?",new String[]{String.valueOf(imageid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put(KEY_LABEL_ID,cursor.getString(cursor.getColumnIndex(KEY_LABEL_ID)));
            user.put(KEY_LABEL_NAME,cursor.getString(cursor.getColumnIndex(KEY_LABEL_NAME)));
            user.put(KEY_LABEL_COMMENT,cursor.getString(cursor.getColumnIndex(KEY_LABEL_COMMENT)));
            userList.add(user);
        }
        return  userList;
    }
    // Getting All Contacts
    public List<Labels> getAllContacts(String imageid) {
        List<Labels> contactList = new ArrayList<Labels>();
        // Select All Query
        String selectQuery = "SELECT * FROM "+TABLE_CONTACTS;
        
        SQLiteDatabase db = this.getWritableDatabase();
       // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_LABEL_ID, KEY_LABEL_NAME, KEY_LABEL_COMMENT}, KEY_LABEL_ID+ "=?",new String[]{String.valueOf(imageid)},null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Labels contact = new Labels();



                contact.setLabel_id(cursor.getString(0));
                contact.setLabel_name(cursor.getString(1));
                contact.setLabel_comment(cursor.getString(2));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return contactList;
        
    }

    public void deleteTable() {
        
        
        
        SQLiteDatabase db= this.getWritableDatabase();
        
        db.execSQL("DELETE FROM "+ TABLE_CONTACTS);
        db.close();
    }
    
}
