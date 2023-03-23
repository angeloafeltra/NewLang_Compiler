package compiler.visitors.semanticVisitor;

public class Eccezioni {

    public static class MultipleDeclaration extends Exception {
        public MultipleDeclaration() {
            super("Dichiarazione Multipla");
        }
    }

    public static class InizializationError extends Exception{
        public InizializationError(){
            super("Errore nell'inizializzazione");
        }
    }

    public static class NoDeclarationError extends Exception{
        public NoDeclarationError(){
            super("Variabile non dichiarata");
        }
    }

    public static class ReturnError extends Exception{
        public ReturnError(){
            super("Il return non coincide con il tipo della funzione");
        }
    }

    public static class MultipleReturnType extends Exception{
        public MultipleReturnType(){
            super("Il body possiede piu return di tipo diverso");
        }
    }

    public static class AssignError extends Exception{
        public AssignError(){
            super("Il numero di variabili non coincide con il numero di espressioni");
        }
    }

    public static class TypeAssignError extends Exception{
        public TypeAssignError(){
            super("Il tipo della variabile non coincide con il tipo dell'espressione nell'assegnazione");
        }
    }

    public static class CallFunNumParamError extends Exception{
        public CallFunNumParamError(){
            super("Il numero di parameteri nella chiamata di funzione non coincide");
        }
    }

    public static class CallFunTypeParamError extends Exception{
        public CallFunTypeParamError(){
            super("Il tipo di parameteri nella chiamata di funzione non coincide");
        }
    }

    public static class ConditionNotValid extends Exception{
        public ConditionNotValid(){
            super("Condizione non valida");
        }
    }


    public static class ForDeclarationNotValid extends Exception{
        public ForDeclarationNotValid(){
            super("Dichiarazione del for errata");
        }
    }

    public static class WriteOpError extends Exception{
        public WriteOpError(){
            super("Errore nell'operazione di write");
        }
    }

    public static class ArithmeticOpError extends Exception{
        public ArithmeticOpError(){
            super("Operazione Artitmetica non valida");
        }
    }

    public static class RelationalOpError extends Exception{
        public RelationalOpError(){
            super("Operazione Relazionale non valida");
        }
    }

    public static class StringOpError extends Exception{
        public StringOpError() { super("Operazione su stringa non valida");}

    }

    public static class BooleanOpError extends Exception{
        public BooleanOpError() { super("Operazione booleana non valida");}

    }

    public static class MinusOpError extends Exception{
        public MinusOpError() { super("Operazione  di negazione non valida");}

    }

    public static class NotOpError extends Exception{
        public NotOpError() { super("Operazione  not non valida");}

    }

    public static class UnaryOpNotValid extends Exception{
        public UnaryOpNotValid() {super("Operazione unaria non riconosciuta");}
    }

    public static class BinaryOpNotValid extends Exception{
        public BinaryOpNotValid() {super("Operazione binaria non riconosciuta");}
    }

    public static class BodyNoTypeError extends Exception{
        public BodyNoTypeError() {super("Errore nel body, non deve restituire nessun tipo");}
    }

    public static class ForExpressionTypeError extends Exception{
        public ForExpressionTypeError() {super(("Il limite minimo e massimo devo essere di tipo integer"));}
    }

    public static class ReadError extends Exception{
        public ReadError() {super("Errore nell'operazione read");}
    }
}
