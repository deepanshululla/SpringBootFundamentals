package ttl.larku.dao.inmemory;

import ttl.larku.dao.jpa.JpaStudentDAO;

import java.util.ResourceBundle;

/**
 * @author whynot
 */
public class DaoFactory {

    public static StudentDAO studentDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
        String profile = bundle.getString("larku.profile.active");
        switch(profile) {
            case "dev" : return new InMemoryStudentDAO();
            case "prod" : return new JpaStudentDAO();
            default : throw new RuntimeException("Unknown profile: " + profile);
        }
    }
}
