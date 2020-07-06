package facultate.project.util;

import javax.servlet.http.HttpSession;

public class Session {
    private static Session single_instance = null;

    public HttpSession session = null;

    public static Session getInstance()
    {
        if (single_instance == null)
            single_instance = new Session();

        return single_instance;
    }
}
