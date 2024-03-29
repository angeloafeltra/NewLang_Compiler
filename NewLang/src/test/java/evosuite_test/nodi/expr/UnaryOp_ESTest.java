/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 19:03:35 GMT 2023
 */

package evosuite_test.nodi.expr;

import compiler.nodi.expr.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import compiler.visitors.TranslatorVisitor;

public class UnaryOp_ESTest {

  @Test
  public void test00()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      UnaryOp unaryOp0 = new UnaryOp("", constOp0);
      String string0 = unaryOp0.toString();
      assertEquals("", string0);
  }

  @Test
  public void test01()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("yc", "");
      UnaryOp unaryOp0 = new UnaryOp((String) null, constOp0);
      String string0 = unaryOp0.getType();
      assertNull(string0);
  }

  @Test
  public void test02()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      UnaryOp unaryOp0 = new UnaryOp("", constOp0);
      String string0 = unaryOp0.getType();
      assertEquals("", string0);
  }

  @Test
  public void test03()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("compiler.nodi.expr.UnaryOp", "compiler.nodi.expr.UnaryOp");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("compiler.nodi.expr.UnaryOp", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp("compiler.nodi.expr.UnaryOp", aritAndRelOp0);
      unaryOp0.setTipoEspressione("compiler.nodi.expr.UnaryOp");
      String string0 = unaryOp0.getTipoEspressione();
      assertEquals("compiler.nodi.expr.UnaryOp", string0);
  }

  @Test
  public void test04()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp(":!!'fs5*K$#tVG", aritAndRelOp0);
      unaryOp0.setTipoEspressione("");
      String string0 = unaryOp0.getTipoEspressione();
      assertEquals("", string0);
  }


  @Test
  public void test05()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      UnaryOp unaryOp0 = new UnaryOp("", identifier0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      try { 
        unaryOp0.accept(translatorVisitor0);
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
  public void test06()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      UnaryOp unaryOp0 = new UnaryOp("", idInitObbOp0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor("");
      try { 
        unaryOp0.accept(translatorVisitor0);
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
  public void test07()  throws Throwable  {
      UnaryOp unaryOp0 = null;
      try {
        unaryOp0 = new UnaryOp("2X05C |Sb|C?^%8!Zl", (Expr) null);
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
  public void test08()  throws Throwable  {
      Identifier identifier0 = new Identifier("?m}ZVQpsMTK]");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("?m}ZVQpsMTK]", identifier0, identifier0);
      UnaryOp unaryOp0 = new UnaryOp("?m}ZVQpsMTK]", aritAndRelOp0);
      identifier0.setParent(unaryOp0);
      UnaryOp unaryOp1 = null;
      try {
        unaryOp1 = new UnaryOp("?m}ZVQpsMTK]", identifier0);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // -1
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("java.util.Vector"));
      }
  }


  @Test
  public void test09()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("compiler.nodi.expr.UnaryOp", "compiler.nodi.expr.UnaryOp");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("compiler.nodi.expr.UnaryOp", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp("compiler.nodi.expr.UnaryOp", aritAndRelOp0);
      String string0 = unaryOp0.getType();
      assertEquals("compiler.nodi.expr.UnaryOp", string0);
  }

  @Test
  public void test10()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp(":!!'fs5*K$#tVG", aritAndRelOp0);
      Expr expr0 = unaryOp0.getExpr();
      assertSame(aritAndRelOp0, expr0);
  }

  @Test
  public void test11()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp(":!!'fs5*K$#tVG", aritAndRelOp0);
      String string0 = unaryOp0.toString();
      assertEquals(":!!'fs5*K$#tVG", string0);
  }

  @Test
  public void test12()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp(":!!'fs5*K$#tVG", aritAndRelOp0);
      String string0 = unaryOp0.getTipoEspressione();
      assertNull(string0);
  }

  @Test
  public void test13()  throws Throwable  {
      ConstOp constOp0 = new ConstOp("", "");
      AritAndRelOp aritAndRelOp0 = new AritAndRelOp("", constOp0, constOp0);
      UnaryOp unaryOp0 = new UnaryOp(":!!'fs5*K$#tVG", aritAndRelOp0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      Object object0 = unaryOp0.accept(translatorVisitor0);
      assertEquals("", object0);
  }
}
