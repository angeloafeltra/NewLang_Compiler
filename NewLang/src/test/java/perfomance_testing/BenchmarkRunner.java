package perfomance_testing;

import compiler.NewLang;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.*;
import java.security.SecureRandom;

public class BenchmarkRunner {


    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}


