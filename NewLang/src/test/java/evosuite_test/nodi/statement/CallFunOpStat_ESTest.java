/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 18:41:35 GMT 2023
 */

package evosuite_test.nodi.statement;

import compiler.visitors.TranslatorVisitor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import compiler.nodi.expr.Expr;
import compiler.nodi.expr.Identifier;
import compiler.nodi.statement.CallFunOpStat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CallFunOpStat_ESTest {

  @Test
  public void test00()  throws Throwable  {
      Identifier identifier0 = new Identifier("l0k!/<jpjQ\"!nJ");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      List<Expr> list0 = callFunOpStat0.getListExpr();
      assertNull(list0);
  }


  @Test
  public void test01()  throws Throwable  {
      Identifier identifier0 = new Identifier("ParDeclOp");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      arrayList0.add((Expr) null);
      // Undeclared exception!
      try { 
        callFunOpStat0.addsListExpr(arrayList0);
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
  public void test02()  throws Throwable  {
      Identifier identifier0 = new Identifier("e(NscRBZox[m");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      // Undeclared exception!
      try { 
        callFunOpStat0.addExpr(identifier0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.statement.CallFunOpStat"));
      }
  }

  @Test
  public void test03()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      // Undeclared exception!
      try { 
        callFunOpStat0.addExpr((Expr) null);
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
  public void test04()  throws Throwable  {
      Identifier identifier0 = new Identifier("CallFunOpStmt");
      CallFunOpStat callFunOpStat0 = null;
      try {
        callFunOpStat0 = new CallFunOpStat(identifier0, (List<Expr>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.statement.CallFunOpStat"));
      }
  }

  @Test
  public void test05()  throws Throwable  {
      CallFunOpStat callFunOpStat0 = null;
      try {
        callFunOpStat0 = new CallFunOpStat((Identifier) null, (List<Expr>) null);
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
  public void test06()  throws Throwable  {
      LinkedList<Expr> linkedList0 = new LinkedList<Expr>();
      Identifier identifier0 = new Identifier("|;wa.ArFY\"s-4_Foh:");
      CallFunOpStat callFunOpStat0 = null;
      try {
        callFunOpStat0 = new CallFunOpStat(identifier0, linkedList0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.util.LinkedList cannot be cast to java.util.ArrayList
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.statement.CallFunOpStat"));
      }
  }

  @Test
  public void test07()  throws Throwable  {
      CallFunOpStat callFunOpStat0 = null;
      try {
        callFunOpStat0 = new CallFunOpStat((Identifier) null);
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
      Identifier identifier0 = new Identifier((String) null);
      Identifier identifier1 = new Identifier((String) null);
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier1);
      identifier0.setParent(callFunOpStat0);
      CallFunOpStat callFunOpStat1 = null;
      try {
        callFunOpStat1 = new CallFunOpStat(identifier0);
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
      Identifier identifier0 = new Identifier("ParDeclOp");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      arrayList0.add((Expr) identifier0);
      // Undeclared exception!
      try { 
        callFunOpStat0.addsListExpr(arrayList0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.statement.CallFunOpStat"));
      }
  }



  @Test
  public void test10()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0, arrayList0);
      callFunOpStat0.addsListExpr(arrayList0);
      assertTrue(arrayList0.isEmpty());
  }

  @Test
  public void test11()  throws Throwable  {
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      Identifier identifier0 = new Identifier("sTY>$JobK900ZPbJHd");
      arrayList0.add((Expr) identifier0);
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0, arrayList0);
      List<Expr> list0 = callFunOpStat0.getListExpr();
      assertFalse(list0.isEmpty());
  }

  @Test
  public void test12()  throws Throwable  {
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      Identifier identifier0 = new Identifier("sTY>$JobK900ZPbJHd");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0, arrayList0);
      List<Expr> list0 = callFunOpStat0.getListExpr();
      assertEquals(0, list0.size());
  }

  @Test
  public void test13()  throws Throwable  {
      Identifier identifier0 = new Identifier("_>U^-I>wI@7");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor("_>U^-I>wI@7");
      try { 
        callFunOpStat0.accept(translatorVisitor0);
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
  public void test14()  throws Throwable  {
      Identifier identifier0 = new Identifier("#include <stdio.h>\n#include <stdlib.h>\n#include <string.h>\n#include <math.h>\n#include <unistd.h>\n#include <stdbool.h>\n#define MAXCHAR 512\n");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      Identifier identifier1 = callFunOpStat0.getIdentifier();
      assertEquals("#include <stdio.h>\n#include <stdlib.h>\n#include <string.h>\n#include <math.h>\n#include <unistd.h>\n#include <stdbool.h>\n#define MAXCHAR 512\n", identifier1.getLessema());
  }

  @Test
  public void test15()  throws Throwable  {
      Identifier identifier0 = new Identifier("e(NscRBZox[m");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      String string0 = callFunOpStat0.toString();
      assertEquals("CallFunOpStmt", string0);
  }

  @Test
  public void test16()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0, arrayList0);
      callFunOpStat0.addExpr(identifier0);
      assertEquals("", identifier0.getLessema());
  }
}
