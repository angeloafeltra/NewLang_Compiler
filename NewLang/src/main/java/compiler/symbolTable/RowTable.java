package compiler.symbolTable;

public class RowTable {

    private String symbol;
    private Object kind;
    private TypeField type;
    private String propreties;


    public RowTable(String symbol, Object kind, TypeField type, String propreties){
        this.symbol=symbol;
        this.kind=kind;
        this.type=type;
        this.propreties=propreties;

    }

    public String getSymbol() {
        return symbol;
    }

    public Object getKind() {
        return kind;
    }

    public TypeField getType() {
        return type;
    }

    public String getPropreties() {
        return propreties;
    }

    @Override
    public String toString() {
        String typeString;
        if (type.getClass()== TypeField.TypeFieldFunction.class)
            typeString=((TypeField.TypeFieldFunction) type).toString();
        else
            typeString=((TypeField.TypeFieldVar) type).toString();
        return "Symbol: "+symbol+", Kind: "+kind.toString()+", Type: "+typeString+", Propreties: "+propreties;
    }
}
