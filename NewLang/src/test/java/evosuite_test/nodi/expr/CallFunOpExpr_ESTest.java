/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 18:51:10 GMT 2023
 */

package evosuite_test.nodi.expr;

import compiler.nodi.expr.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import compiler.visitors.TranslatorVisitor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CallFunOpExpr_ESTest  {

  @Test
  public void test00()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      callFunOpExpr0.setTipoEspressione("CallFunOpExpr");
      String string0 = callFunOpExpr0.getTipoEspressione();
      assertEquals("CallFunOpExpr", string0);
  }

  @Test
  public void test01()  throws Throwable  {
      Identifier identifier0 = new Identifier("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint strto_bool(char* expr);\n");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      callFunOpExpr0.setTipoEspressione("");
      String string0 = callFunOpExpr0.getTipoEspressione();
      assertEquals("", string0);
  }

  @Test
  public void test02()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      ArrayList<Expr> arrayList0 = callFunOpExpr0.getListExpr();
      assertNull(arrayList0);
  }

  @Test
  public void test03()  throws Throwable  {
      Identifier identifier0 = new Identifier("lr0ru7A2M.5M^%Es$");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      IdInitOp idInitOp0 = new IdInitOp(identifier0, identifier0);
      callFunOpExpr0.addExpr(idInitOp0);
      ArrayList<Expr> arrayList1 = callFunOpExpr0.getListExpr();
      assertSame(arrayList0, arrayList1);
  }

  @Test
  public void test04()  throws Throwable  {
      Identifier identifier0 = new Identifier("lyj");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      // Undeclared exception!
      try { 
        callFunOpExpr0.addsListExpr(arrayList0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //


          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.expr.CallFunOpExpr"));
      }
  }

  @Test
  public void test05()  throws Throwable  {
      Identifier identifier0 = new Identifier("uPyTB5l?8xc= >-wxeO");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      arrayList0.add((Expr) callFunOpExpr0);
      // Undeclared exception!
      try { 
        callFunOpExpr0.addsListExpr(arrayList0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // new child is an ancestor
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("javax.swing.tree.DefaultMutableTreeNode"));
      }
  }

  @Test
  public void test06()  throws Throwable  {
      Identifier identifier0 = new Identifier("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      // Undeclared exception!
      try { 
        callFunOpExpr0.addExpr(identifier0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.expr.CallFunOpExpr"));
      }
  }

  @Test
  public void test07()  throws Throwable  {
      Identifier identifier0 = new Identifier("lr0ru7A2M.5M^%Es$");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      // Undeclared exception!
      try { 
        callFunOpExpr0.addExpr((Expr) null);
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
      Identifier identifier0 = new Identifier("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      CallFunOpExpr callFunOpExpr1 = (CallFunOpExpr)callFunOpExpr0.clone();
      callFunOpExpr0.addExpr(callFunOpExpr1);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor("");
      try { 
        callFunOpExpr0.accept(translatorVisitor0);
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
  public void test09()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      CallFunOpExpr callFunOpExpr0 = null;
      try {
        callFunOpExpr0 = new CallFunOpExpr(identifier0, (List<Expr>) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.expr.CallFunOpExpr"));
      }
  }

  @Test
  public void test10()  throws Throwable  {
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = null;
      try {
        callFunOpExpr0 = new CallFunOpExpr((Identifier) null, arrayList0);
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
  public void test11()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      LinkedList<Expr> linkedList0 = new LinkedList<Expr>();
      CallFunOpExpr callFunOpExpr0 = null;
      try {
        callFunOpExpr0 = new CallFunOpExpr(identifier0, linkedList0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.util.LinkedList cannot be cast to java.util.ArrayList
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.expr.CallFunOpExpr"));
      }
  }

  @Test
  public void test12()  throws Throwable  {
      CallFunOpExpr callFunOpExpr0 = null;
      try {
        callFunOpExpr0 = new CallFunOpExpr((Identifier) null);
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
  public void test13()  throws Throwable  {
      Identifier identifier0 = new Identifier("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      Identifier identifier1 = callFunOpExpr0.getIdentifier();
      assertNull(identifier1.getMode());
  }

  @Test
  public void test14()  throws Throwable  {
      Identifier identifier0 = new Identifier("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      ConstOp constOp0 = new ConstOp("", "char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n");
      arrayList0.add((Expr) constOp0);
      callFunOpExpr0.addsListExpr(arrayList0);
      assertNull(callFunOpExpr0.getMode());
  }

  @Test
  public void test15()  throws Throwable  {
      Identifier identifier0 = new Identifier("lr0ru7A2M.5M^%Es$");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      String string0 = callFunOpExpr0.toString();
      assertEquals("CallFunOpExpr", string0);
  }
/*
  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      Identifier identifier0 = new Identifier("cNO.=Q03\"6");
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0);
      callFunOpExpr0.setAllowsChildren(false);
      // Undeclared exception!
      try { 
        callFunOpExpr0.addExpr(identifier0);
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // node does not allow children
         //
         verifyException("javax.swing.tree.DefaultMutableTreeNode", e);
      }
  }
*/
  @Test
  public void test17()  throws Throwable  {
      Identifier identifier0 = new Identifier("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor("");
      Object object0 = callFunOpExpr0.accept(translatorVisitor0);
      assertEquals("char* integer_to_str(int i);\nchar* real_to_str(float i);\nchar* char_to_str(char i);\nchar* bool_to_str(bool i);\nchar* str_concat(char* str1, char* str2);\nchar* read_str();\nint str_to_bool(char* expr);\n)", object0);
  }

  @Test
  public void test18()  throws Throwable  {
      Identifier identifier0 = new Identifier("lr0ru7A2M.5M^%Es$");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      IdInitOp idInitOp0 = new IdInitOp(identifier0, identifier0);
      callFunOpExpr0.addExpr(idInitOp0);
      CallFunOpExpr callFunOpExpr1 = new CallFunOpExpr(identifier0, arrayList0);
      assertNull(callFunOpExpr1.getTipoEspressione());
  }

  @Test
  public void test19()  throws Throwable  {
      Identifier identifier0 = new Identifier("lr0ru7A2M.5M^%Es$");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      ArrayList<Expr> arrayList1 = callFunOpExpr0.getListExpr();
      assertSame(arrayList1, arrayList0);
  }

  @Test
  public void test20()  throws Throwable  {
      Identifier identifier0 = new Identifier("lr0ru7A2M.5M^%Es$");
      ArrayList<Expr> arrayList0 = new ArrayList<Expr>();
      CallFunOpExpr callFunOpExpr0 = new CallFunOpExpr(identifier0, arrayList0);
      String string0 = callFunOpExpr0.getTipoEspressione();
      assertNull(string0);
  }
}
