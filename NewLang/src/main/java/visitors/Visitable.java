package main.java.visitors;

public interface Visitable {

    Object accept(Visitor v) throws Exception;
}
