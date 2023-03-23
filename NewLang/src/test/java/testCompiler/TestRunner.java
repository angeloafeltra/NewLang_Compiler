package testCompiler;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Iterator;

public class TestRunner {


    public static void main(String[] argv){
        Result result= JUnitCore.runClasses(new Class[]{JUnitTestSuite.class});
        Iterator var2=result.getFailures().iterator();

        while (var2.hasNext()){
            Failure failure=(Failure)var2.next();
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }



}
