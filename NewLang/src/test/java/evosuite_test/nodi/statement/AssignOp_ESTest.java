/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 18:39:13 GMT 2023
 */

package evosuite_test.nodi.statement;

import compiler.nodi.VarDeclOp;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import compiler.nodi.expr.Expr;
import compiler.nodi.expr.ConstOp;
import compiler.nodi.statement.CallFunOpStat;
import compiler.nodi.expr.IdInitObbOp;
import compiler.nodi.expr.Identifier;
import compiler.nodi.statement.AssignOp;
import compiler.visitors.TranslatorVisitor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AssignOp_ESTest {

  @Test
  public void test00()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      Identifier identifier0 = new Identifier("");
      arrayList0.add(identifier0);
      ArrayList<Identifier> arrayList2 = assignOp0.getListId();
      assertTrue(arrayList2.contains(identifier0));
  }

  @Test
  public void test01()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      Identifier identifier0 = new Identifier("strcat");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      assignOp0.addExpr(idInitObbOp0);
      ArrayList<Expr> arrayList2 = assignOp0.getListExpr();
      assertEquals(1, arrayList1.size());
      assertSame(arrayList1, arrayList2);
  }


  @Test
  public void test02()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      // Undeclared exception!
      try { 
        assignOp0.addId((Identifier) null);
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
  public void test03()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      // Undeclared exception!
      try { 
        assignOp0.addExpr((Expr) null);
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
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      VarDeclOp varDeclOp0 = new VarDeclOp("AssignOp", arrayList1);
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      Identifier identifier0 = new Identifier("strcat");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      idInitObbOp0.setParent(varDeclOp0);
      // Undeclared exception!
      try { 
        assignOp0.addExpr(idInitObbOp0);
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
  public void test05()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      Identifier identifier0 = new Identifier("Of+");
      assignOp0.addId(identifier0);
      try { 
        assignOp0.accept(translatorVisitor0);
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
      LinkedList<Expr> linkedList0 = new LinkedList<Expr>();
      AssignOp assignOp0 = null;
      try {
        assignOp0 = new AssignOp((List<Identifier>) null, linkedList0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.statement.AssignOp"));
      }
  }

  @Test
  public void test07()  throws Throwable  {
      LinkedList<Expr> linkedList0 = new LinkedList<Expr>();
      linkedList0.add((Expr) null);
      LinkedList<Identifier> linkedList1 = new LinkedList<Identifier>();
      AssignOp assignOp0 = null;
      try {
        assignOp0 = new AssignOp(linkedList1, linkedList0);
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
      LinkedList<Identifier> linkedList0 = new LinkedList<Identifier>();
      LinkedList<Expr> linkedList1 = new LinkedList<Expr>();
      AssignOp assignOp0 = null;
      try {
        assignOp0 = new AssignOp(linkedList0, linkedList1);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.util.LinkedList cannot be cast to java.util.ArrayList
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.statement.AssignOp"));
      }
  }

  @Test
  public void test09()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      ConstOp constOp0 = new ConstOp("b)A9|8I7DM^", "b)A9|8I7DM^");
      Identifier identifier0 = new Identifier("+");
      CallFunOpStat callFunOpStat0 = new CallFunOpStat(identifier0);
      constOp0.setParent(callFunOpStat0);
      arrayList1.add((Expr) constOp0);
      AssignOp assignOp0 = null;
      try {
        assignOp0 = new AssignOp(arrayList0, arrayList1);
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
  public void test10()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      ArrayList<Identifier> arrayList2 = assignOp0.getListId();
      assertSame(arrayList2, arrayList0);
  }

  @Test
  public void test11()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      ArrayList<Expr> arrayList2 = assignOp0.getListExpr();
      assertSame(arrayList2, arrayList1);
  }

  @Test
  public void test12()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      Identifier identifier0 = new Identifier("strcat");
      IdInitObbOp idInitObbOp0 = new IdInitObbOp(identifier0, identifier0);
      assignOp0.addExpr(idInitObbOp0);
      assignOp0.addsListExp(arrayList1);
      assertEquals(2, arrayList1.size());
      assertTrue(arrayList0.isEmpty());
  }

  @Test
  public void test13()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      Identifier identifier0 = new Identifier("strcat");
      assignOp0.addId(identifier0);
      assignOp0.addsListId(arrayList0);
      assertEquals(2, arrayList0.size());
      assertFalse(arrayList0.isEmpty());
  }

  @Test
  public void test14()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      Identifier identifier0 = new Identifier((String) null);
      arrayList0.add(identifier0);
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
  }

  @Test
  public void test15()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      String string0 = assignOp0.toString();
      assertEquals("AssignOp", string0);
  }

  @Test
  public void test16()  throws Throwable  {
      ArrayList<Identifier> arrayList0 = new ArrayList<Identifier>();
      ArrayList<Expr> arrayList1 = new ArrayList<Expr>();
      AssignOp assignOp0 = new AssignOp(arrayList0, arrayList1);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor();
      Object object0 = assignOp0.accept(translatorVisitor0);
      assertNull(object0);
  }
}
