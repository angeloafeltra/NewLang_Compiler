/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 19:08:27 GMT 2023
 */

package evosuite_test.nodi.statement;

import compiler.nodi.expr.IdInitObbOp;
import compiler.nodi.expr.Identifier;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import compiler.nodi.statement.ReturnOp;
import compiler.nodi.statement.Statement;
import compiler.visitors.TranslatorVisitor;


public class Statement_ESTest{

  @Test
  public void test0()  throws Throwable  {
      Statement statement0 = new Statement("");
      String string0 = statement0.toString();
      assertEquals("", string0);
  }


  @Test
  public void test1()  throws Throwable  {
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      ReturnOp returnOp0 = new ReturnOp();
      try { 
        returnOp0.accept(translatorVisitor0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.visitors.TranslatorVisitor"));
      }
  }

  @Test
  public void test2()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      ReturnOp returnOp0 = new ReturnOp(idInitObbOp0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      try { 
        returnOp0.accept(translatorVisitor0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // compiler.nodi.expr.Identifier cannot be cast to compiler.nodi.expr.ConstOp
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.visitors.TranslatorVisitor"));
      }
  }



  @Test
  public void test3()  throws Throwable  {
      ReturnOp returnOp0 = new ReturnOp();
      String string0 = returnOp0.toString();
      assertEquals("ReturnOp", string0);
  }

  @Test
  public void test4()  throws Throwable  {
      Statement statement0 = new Statement("");
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor("");
      Object object0 = statement0.accept(translatorVisitor0);
      assertNull(object0);
  }
}
