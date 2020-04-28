package dockerController;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


/**
 *
 * @author francesco
 */
public class TestDockerImageRunner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestDockerImage.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}
