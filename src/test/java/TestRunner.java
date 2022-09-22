import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

import java.io.IOException;

public class TestRunner {

    @Test
    public void doLogin() throws ConfigurationException, IOException {
        User user = new User();
        user.loginApi();
    }

    @Test
    public void getUserData() throws IOException {
        User user = new User();
        user.GetApi();
    }

}
