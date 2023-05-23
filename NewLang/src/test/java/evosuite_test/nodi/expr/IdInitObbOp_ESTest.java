/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 18:54:46 GMT 2023
 */

package evosuite_test.nodi.expr;

import compiler.nodi.expr.*;
import compiler.visitors.Visitor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import compiler.visitors.TranslatorVisitor;


public class IdInitObbOp_ESTest {

  @Test
  public void test0()  throws Throwable  {
      Identifier identifier0 = new Identifier("e2nZL");
      UnaryOp unaryOp0 = new UnaryOp((String) null, identifier0);
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, unaryOp0);
      idInitObbOp0.setUserObject((Object) null);
      String string0 = idInitObbOp0.toString();
      assertEquals("", string0);
  }


  @Test
  public void test1()  throws Throwable  {
      Identifier identifier0 = new Identifier("Qfb");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      try { 
        idInitObbOp0.accept((Visitor) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.expr.IdInitObbOp"));
      }
  }


  @Test
  public void test2()  throws Throwable  {
      Identifier identifier0 = new Identifier(":EB*~]~T^Zs^\"");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      try { 
        idInitObbOp0.accept(translatorVisitor0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // compiler.nodi.expr.Identifier cannot be cast to compiler.nodi.expr.ConstOp
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.expr.IdInitObbOp"));
      }
  }



  @Test
  public void test3()  throws Throwable  {
      IdInitObbOp idInitObbOp0 = null;
      try {
        idInitObbOp0 = new IdInitObbOp((Identifier) null, (Expr) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // new child is null
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("javax.swing.tree.DefaultMutableTreeNode"));
      }
  }



  @Test
  public void test4()  throws Throwable  {
      Identifier identifier0 = new Identifier("IdInitObbOp");
      ConstOp constOp0 = new ConstOp("IdInitObbOp", "IdInitObbOp");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, constOp0);
      Expr expr0 = idInitObbOp0.getExpr();
      assertSame(constOp0, expr0);
  }

  @Test
  public void test5()  throws Throwable  {
      Identifier identifier0 = new Identifier("IdInitObbOp");
      ConstOp constOp0 = new ConstOp("IdInitObbOp", "IdInitObbOp");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, constOp0);
      Identifier identifier1 = idInitObbOp0.getId();
      assertNull(identifier1.getTipoEspressione());
  }

  @Test
  public void test6()  throws Throwable  {
      Identifier identifier0 = new Identifier("e2nZL");
      UnaryOp unaryOp0 = new UnaryOp((String) null, identifier0);
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, unaryOp0);
      String string0 = idInitObbOp0.toString();
      assertEquals("IdInitObbOp", string0);
  }

  @Test
  public void test7()  throws Throwable  {
      Identifier identifier0 = new Identifier("IdInitObbOp");
      ConstOp constOp0 = new ConstOp("IdInitObbOp", "IdInitObbOp");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, constOp0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      Object object0 = idInitObbOp0.accept(translatorVisitor0);
      assertEquals(" IdInitObbOp=", object0);
  }
}
