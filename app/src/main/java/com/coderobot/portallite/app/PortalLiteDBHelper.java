package com.coderobot.portallite.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2014/4/29.
 */
public class PortalLiteDBHelper extends SQLiteOpenHelper {

    public static PortalLiteDBHelper DBHelper;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public PortalLiteDBHelper(Context context) {
        super(context, Define.DB_NAME, null, Define.DB_VERSION);
    }

    public static PortalLiteDBHelper getInstance(Context context) {
        if (DBHelper == null)
            DBHelper = new PortalLiteDBHelper(context);
        return DBHelper;
    }

    public static void insertCourse(Context c) {
        DBHelper = getInstance(c);
        SQLiteDatabase db = DBHelper.getWritableDatabase();
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = "CREATE TABLE IF NOT EXISTS " + Define.DB_USER_INFO + "("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "username text,"
                + "password text);";
        db.execSQL(SQL);
        SQL = "CREATE TABLE IF NOT EXISTS " + Define.DB_COURSE_TABLE + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name text, "
                + "course_id text,"
                + "year text,"
                + "semester text,"
                + "type text);";
        db.execSQL(SQL);
        SQL = "CREATE TABLE IF NOT EXISTS " + Define.DB_SCHEDULE_TABLE + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "course_id text,"
                + "room text,"
                + "day text,"
                + "time text,"
                + "year text,"
                + "semester text);";
        db.execSQL(SQL);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
