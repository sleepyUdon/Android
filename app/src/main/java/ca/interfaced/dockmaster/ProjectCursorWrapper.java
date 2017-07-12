package ca.interfaced.dockmaster;

import android.database.Cursor;
import android.database.CursorWrapper;
import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.ProjectDbSchema.ProjectTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by vivianechan on 2017-07-03.
 */

public class ProjectCursorWrapper extends CursorWrapper {

    public ProjectCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Project getProject() {
        String uuidString = getString(getColumnIndex(ProjectTable.Cols.UUID));
        String projectName = getString(getColumnIndex(ProjectTable.Cols.PROJECTNAME));
        String projectAddress = getString(getColumnIndex(ProjectTable.Cols.PROJECTADDRESS));
        long date = getLong(getColumnIndex(ProjectTable.Cols.PROJECTBOOKINGDATE));

        Project project = new Project(UUID.fromString(uuidString));
        project.setProjectName(projectName);
        project.setProjectAddress(projectAddress);
        project.setDate(new Date(date));

        return project;
    }
}
