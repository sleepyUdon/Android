package ca.interfaced.dockmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ca.interfaced.dockmaster.ProjectDbSchema.ProjectTable;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "projectBase.db";

    public ProjectBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ProjectTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
                        ProjectTable.Cols.UUID + ", " +
                        ProjectTable.Cols.PROJECTNAME + ", " +
                        ProjectTable.Cols.PROJECTADDRESS + ", " +
                        ProjectTable.Cols.PROJECTBOOKINGDATE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
