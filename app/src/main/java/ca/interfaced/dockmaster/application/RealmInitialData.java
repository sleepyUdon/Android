package ca.interfaced.dockmaster.application;

import ca.interfaced.dockmaster.Model.Project;
import io.realm.Realm;

/**
 * Created by vivianechan on 2017-07-26.
 */

public class RealmInitialData implements Realm.Transaction {
    @Override
    public void execute(Realm realm) {
        Project project = new Project();

        project.setId(1);
        project.setProjectName("111 Richmond");
        project.setProjectAddress("111 Richmond Street");
        realm.insertOrUpdate(project);

        project.setId(2);
        project.setProjectName("55 Spadina");
        project.setProjectAddress("55 Spadina Avenue");
        realm.insertOrUpdate(project);

        project.setId(3);
        project.setProjectName("City Hall");
        project.setProjectAddress("110 Queen Street");
        realm.insertOrUpdate(project);


    }
}
