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
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PosterContract.Posters.TABLE_NAME + " (" +
                    PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + TEXT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TITLE + TEXT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TIME_START_HOUR + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TIME_START_MINUTE + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TIME_END_HOUR + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_TIME_END_MINUTE + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_DATE_YEAR + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_DATE_MONTH + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_DATE_DAY + INT_TYPE + COMMA_SEP +
                    PosterContract.Posters.COLUMN_NAME_EVENT_LOCATION + TEXT_TYPE +
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

    public void insertRow(String imageName, String eventTitle, int eventTimeStartHour, int eventTimeStartMinute, int eventTimeEndHour, int eventTimeEndMinute, int eventDateYear, int eventDateMonth, int eventDateDay, String eventLocation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PosterContract.Posters.COLUMN_NAME_IMAGE_NAME, imageName);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TITLE, eventTitle);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_START_HOUR, eventTimeStartHour);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_START_MINUTE, eventTimeStartMinute);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_END_HOUR, eventTimeEndHour);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_END_MINUTE, eventTimeEndMinute);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE_YEAR, eventDateYear);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE_MONTH, eventDateMonth);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE_DAY, eventDateDay);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_LOCATION, eventLocation);
        db.insert(PosterContract.Posters.TABLE_NAME, null, values);
        db.close();
    }

    public void updateRow(String imageName, String eventTitle, int eventTimeStartHour, int eventTimeStartMinute, int eventTimeEndHour, int eventTimeEndMinute, int eventDateYear, int eventDateMonth, int eventDateDay, String eventLocation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PosterContract.Posters.COLUMN_NAME_IMAGE_NAME, imageName);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TITLE, eventTitle);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_START_HOUR, eventTimeStartHour);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_START_MINUTE, eventTimeStartMinute);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_END_HOUR, eventTimeEndHour);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_TIME_END_MINUTE, eventTimeEndMinute);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE_YEAR, eventDateYear);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE_MONTH, eventDateMonth);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_DATE_DAY, eventDateDay);
        values.put(PosterContract.Posters.COLUMN_NAME_EVENT_LOCATION, eventLocation);
        String selection = PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + " = ?";
        String[] selectionArgs = {imageName};
        db.update(PosterContract.Posters.TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }

    public List<PosterEvent> queryAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(PosterContract.Posters.TABLE_NAME, null, null, null, null, null, null);
        List<PosterEvent> posterData = new ArrayList<>();
        if (!c.moveToFirst()) {
            Log.d("Posterity", "Database empty");
        }
        else {
            while (!c.isAfterLast()) {
                PosterEvent posterEvent = new PosterEvent(c.getString(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8), c.getString(9));
                posterData.add(posterEvent);
                c.moveToNext();
            }
        }
        c.close();
        db.close();
        return posterData;
    }

    public PosterEvent queryRow(String imageName) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + " = ?";
        String[] selectionArgs = {imageName};
        Cursor c = db.query(PosterContract.Posters.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        if (!c.moveToFirst()) {
            Log.d("Posterity", "Database empty");
            c.close();
            db.close();
            return null;
        }
        else {
            c.close();
            db.close();
            return new PosterEvent(c.getString(0), c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7), c.getInt(8), c.getString(9));
        }
    }

    public void deleteRow(String imageName) {
        SQLiteDatabase db = getWritableDatabase();
        String selection = PosterContract.Posters.COLUMN_NAME_IMAGE_NAME + " = ?";
        String[] selectionArgs = {imageName};
        db.delete(PosterContract.Posters.TABLE_NAME, selection, selectionArgs);
        db.close();
    }
}
