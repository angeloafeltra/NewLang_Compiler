package testCompiler;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SemanticTest.class,
        SemanticTestFunzioni.class,
        SemanticTestVariabili.class,
        SemanticTestAssegnazione.class,
        SemanticTestCallFun.class,
        SemanticTestWhile.class,
        SemanticTestRead.class,
        SemanticTestReturn.class,
        SemanticTestIf.class,
        SemanticTestAritmeticOp.class,
        SemanticTestRelationalOp.class,
        SemanticTestStringOp.class,
        SemanticTestUnaryOp.class,
        SemanticTestBooleanOp.class,
        TraductorTest.class

})
public class JUnitTestSuite extends TestCase {


}