/*
 * This file was automatically generated by EvoSuite
 * Mon May 22 18:47:30 GMT 2023
 */

package evosuite_test.nodi;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import compiler.nodi.BodyOp;
import compiler.nodi.FunOp;
import compiler.nodi.ParDeclOp;
import compiler.nodi.VarDeclOp;
import compiler.nodi.expr.ConstOp;
import compiler.nodi.expr.IdInitOp;
import compiler.nodi.expr.Identifier;
import compiler.nodi.statement.ForOp;
import compiler.nodi.statement.Statement;
import compiler.symbolTable.SymbolTable;
import compiler.visitors.TranslatorVisitor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class FunOp_ESTest {

  @Test
  public void test00()  throws Throwable  {
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      Identifier identifier0 = new Identifier("m8Isr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList1 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList2 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList1, arrayList2);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, (String) null, bodyOp0);
      funOp0.addsParams(arrayList0);
      assertEquals(0, arrayList0.size());
  }

  @Test
  public void test01()  throws Throwable  {
      Identifier identifier0 = new Identifier((String) null);
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<VarDeclOp> arrayList1 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList2 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList1, arrayList2);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, (String) null, bodyOp0);
      String string0 = funOp0.getType();
      assertNull(string0);
  }

  @Test
  public void test02()  throws Throwable  {
      Identifier identifier0 = new Identifier("m08Osr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, "compiler.nodi.FunOp", bodyOp0);
      String string0 = funOp0.getType();
      assertEquals("compiler.nodi.FunOp", string0);
  }

  @Test
  public void test03()  throws Throwable  {
      Identifier identifier0 = new Identifier("m08Osr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, "compiler.nodi.FunOp", bodyOp0);
      List<ParDeclOp> list0 = funOp0.getParams();
      assertNull(list0);
  }


  @Test
  public void test04()  throws Throwable  {
      Identifier identifier0 = new Identifier("m08Osr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, "compiler.nodi.FunOp", bodyOp0);
      ArrayList<Identifier> arrayList2 = new ArrayList<Identifier>();
      ParDeclOp parDeclOp0 = new ParDeclOp("compiler.nodi.FunOp", (String) null, arrayList2);
      // Undeclared exception!
      try { 
        funOp0.addParam(parDeclOp0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.FunOp"));
      }
  }

  @Test
  public void test05()  throws Throwable  {
      Identifier identifier0 = new Identifier("08Osr3 8\"w$3");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, "rCa6_con*t", bodyOp0);
      // Undeclared exception!
      try { 
        funOp0.addParam((ParDeclOp) null);
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
      Identifier identifier0 = new Identifier((String) null);
      FunOp funOp0 = null;
      try {
        funOp0 = new FunOp(identifier0, (List<ParDeclOp>) null, (String) null, (BodyOp) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.FunOp"));
      }
  }

  @Test
  public void test07()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      LinkedList<ParDeclOp> linkedList0 = new LinkedList<ParDeclOp>();
      ArrayList<Statement> arrayList0 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList0);
      FunOp funOp0 = null;
      try {
        funOp0 = new FunOp(identifier0, linkedList0, "", bodyOp0);
        fail("Expecting exception: ClassCastException");
      
      } catch(ClassCastException e) {
         //
         // java.util.LinkedList cannot be cast to java.util.ArrayList
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.FunOp"));
      }
  }

  @Test
  public void test08()  throws Throwable  {
      FunOp funOp0 = null;
      try {
        funOp0 = new FunOp((Identifier) null, "***6&Ge(A;2;", (BodyOp) null);
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
  public void test09()  throws Throwable  {
      Identifier identifier0 = new Identifier("compiler.nodi.FunOp");
      IdInitOp idInitOp0 = new IdInitOp(identifier0, identifier0);
      ConstOp constOp0 = new ConstOp("compiler.nodi.FunOp", "compiler.nodi.FunOp");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      ForOp forOp0 = new ForOp(idInitOp0, constOp0, bodyOp0);
      identifier0.setParent(forOp0);
      FunOp funOp0 = null;
      try {
        funOp0 = new FunOp(identifier0, "compiler.nodi.FunOp", bodyOp0);
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
      Identifier identifier0 = new Identifier("");
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "", bodyOp0);
      String string0 = funOp0.getType();
      assertEquals("", string0);
  }

  @Test
  public void test11()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "", bodyOp0);
      Identifier identifier1 = funOp0.getIdentificatore();
      assertEquals("", identifier1.getLessema());
  }

  @Test
  public void test12()  throws Throwable  {
      Identifier identifier0 = new Identifier("m08Osr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, "m08Osr3 8\"~w$3", bodyOp0);
      ArrayList<ParDeclOp> arrayList2 = new ArrayList<ParDeclOp>();
      arrayList2.add((ParDeclOp) null);
      // Undeclared exception!
      try { 
        funOp0.addsParams(arrayList2);
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
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      Identifier identifier0 = new Identifier("m8Isr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList1 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList2 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList1, arrayList2);
      FunOp funOp0 = new FunOp(identifier0, "compiler.nodi.FunOp", bodyOp0);
      // Undeclared exception!
      try { 
        funOp0.addsParams(arrayList0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
          String stacktrace = ExceptionUtils.getStackTrace(e);
          assertTrue(stacktrace.contains("compiler.nodi.FunOp"));
      }
  }


  @Test
  public void test14()  throws Throwable  {
      Identifier identifier0 = new Identifier("vE?yfru");
      LinkedList<ParDeclOp> linkedList0 = new LinkedList<ParDeclOp>();
      linkedList0.add((ParDeclOp) null);
      FunOp funOp0 = null;
      try {
        funOp0 = new FunOp(identifier0, linkedList0, "vE?yfru", (BodyOp) null);
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
  public void test15()  throws Throwable  {
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      Identifier identifier0 = new Identifier("08Osr3 8\"w$3");
      ArrayList<VarDeclOp> arrayList1 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList2 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList1, arrayList2);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "1*lQBC?nkka:", bodyOp0);
      ArrayList<Identifier> arrayList3 = new ArrayList<Identifier>();
      ParDeclOp parDeclOp0 = new ParDeclOp("main2", "*45A&b", arrayList3);
      funOp0.addParam(parDeclOp0);
      List<ParDeclOp> list0 = funOp0.getParams();
      assertFalse(list0.isEmpty());
  }

  @Test
  public void test16()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "", bodyOp0);
      BodyOp bodyOp1 = funOp0.getBody();
      assertSame(bodyOp0, bodyOp1);
  }


  @Test
  public void test17()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "", bodyOp0);
      TranslatorVisitor translatorVisitor0 = new TranslatorVisitor("");
      try { 
        funOp0.accept(translatorVisitor0);
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
  public void test18()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "", bodyOp0);
      List<ParDeclOp> list0 = funOp0.getParams();
      assertEquals(0, list0.size());
  }

  @Test
  public void test19()  throws Throwable  {
      Identifier identifier0 = new Identifier("m08Osr3 8\"~w$3");
      ArrayList<VarDeclOp> arrayList0 = new ArrayList<VarDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp(arrayList0, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, "compiler.nodi.FunOp", bodyOp0);
      SymbolTable symbolTable0 = funOp0.getSymbolTable();
      funOp0.setSymbolTable(symbolTable0);
      assertEquals("compiler.nodi.FunOp", funOp0.getType());
  }

  @Test
  public void test20()  throws Throwable  {
      Identifier identifier0 = new Identifier("");
      ArrayList<ParDeclOp> arrayList0 = new ArrayList<ParDeclOp>();
      ArrayList<Statement> arrayList1 = new ArrayList<Statement>();
      BodyOp bodyOp0 = new BodyOp((List<VarDeclOp>) null, arrayList1);
      FunOp funOp0 = new FunOp(identifier0, arrayList0, "", bodyOp0);
      String string0 = funOp0.toString();
      assertEquals("FunOp", string0);
  }
}
