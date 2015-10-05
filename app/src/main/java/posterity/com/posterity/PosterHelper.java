package posterity.com.posterity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PosterHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Poster.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PosterContract.Posters.TABLE_NAME + " (" +
                    PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + TEXT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TITLE + TEXT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_DATE + TEXT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TIME + TEXT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_LOC + TEXT_TYPE +
            " )";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PosterContract.Posters.TABLE_NAME;

    public PosterHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertRow(String imageName, String eventTitle, String eventDate, String eventTime, String eventLoc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PosterContract.Posters.COLUMN_NAME_IMAGE_NAME, imageName);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TITLE, eventTitle);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE, eventDate);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME, eventTime);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_LOC, eventLoc);
        db.insert(PosterContract.Posters.TABLE_NAME, null, values);
        db.close();
    }

    public List<List<String>> queryAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(PosterContract.Posters.TABLE_NAME, null, null, null, null, null, null);
        List<List<String>> posterData = new ArrayList<>();
        if (!c.moveToFirst()) {
            Log.d("Posterity", "Database empty");
        }
        else {
            while (!c.isAfterLast()) {
                List<String> posterRow = new ArrayList<>();
                posterRow.add(c.getString(0));
                posterRow.add(c.getString(1));
                posterRow.add(c.getString(2));
                posterRow.add(c.getString(3));
                posterRow.add(c.getString(4));
                posterData.add(posterRow);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return posterData;
    }

    public List<String> queryRow(String imageName) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + " = ?";
        String[] selectionArgs = {imageName};
        Cursor c = db.query(PosterContract.Posters.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        List<String> posterRow = new ArrayList<>();
        if (!c.moveToFirst()) {
            Log.d("Posterity", "Database empty");
        }
        else {
            posterRow.add(c.getString(0));
            posterRow.add(c.getString(1));
            posterRow.add(c.getString(2));
            posterRow.add(c.getString(3));
            posterRow.add(c.getString(4));
        }
        c.close();
        db.close();
        return posterRow;
    }

    public void deleteRow(String imageName) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + " = ?";
        String[] selectionArgs = {imageName};
        db.delete(PosterContract.Posters.TABLE_NAME, selection, selectionArgs);
        db.close();
    }
}
