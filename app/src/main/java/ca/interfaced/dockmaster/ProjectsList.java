package ca.interfaced.dockmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-06-30.
 */

public class ProjectsList {

    private static ProjectsList sProjectsList;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ProjectsList get(Context context) {
        if (sProjectsList == null) {
            sProjectsList = new ProjectsList(context);
        }
        return sProjectsList;
    }

    private ProjectsList(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ProjectBaseHelper(mContext)
                .getWritableDatabase();
        }

    public void addProject(Project p) {
        ContentValues values = getContentValues(p);
        mDatabase.insert(ProjectDbSchema.ProjectTable.NAME, null, values);
    }


    public List<Project> getProjects(){
        List<Project> projects = new ArrayList<>();

        ProjectCursorWrapper cursor = queryProjects(null ,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                projects.add(cursor.getProject());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return projects;
    }

    public Project getProject(UUID id) {
        ProjectCursorWrapper cursor = queryProjects(ProjectDbSchema.ProjectTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getProject();
        } finally {
            cursor.close();
        }
    }

    public void updateProject(Project project) {
        String uuidString = project.getID().toString();
        ContentValues values = getContentValues(project);

        mDatabase.update(ProjectDbSchema.ProjectTable.NAME, values,
                ProjectDbSchema.ProjectTable.Cols.UUID + " = ? ",
                new String[] {uuidString});
    }

    private ProjectCursorWrapper queryProjects(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ProjectDbSchema.ProjectTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ProjectCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Project project) {
        ContentValues values = new ContentValues();
        values.put(ProjectDbSchema.ProjectTable.Cols.UUID, project.getID().toString());
        values.put(ProjectDbSchema.ProjectTable.Cols.PROJECTNAME, project.getProjectName());
        values.put(ProjectDbSchema.ProjectTable.Cols.PROJECTADDRESS, project.getProjectAddress());
        values.put(ProjectDbSchema.ProjectTable.Cols.PROJECTBOOKINGDATE, project.getDate().getTime());
        return values;
    }
}
