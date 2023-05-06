package compiler.symbolTable;

import java.util.ArrayList;
import java.util.List;

public class TypeField {

    private TypeField(){}

    public static class TypeFieldFunction extends TypeField{
        private ArrayList<String> inputParam;
        private ArrayList<String> outputParam;

        public TypeFieldFunction(){
            inputParam=new ArrayList<>();
            outputParam=new ArrayList<>();
        }

        public TypeFieldFunction (List<String> inputParam, List<String> outputParam){
            this.inputParam= (ArrayList<String>) inputParam;
            this.outputParam= (ArrayList<String>) outputParam;
        }

        public List<String> getInputParam() {
            return inputParam;
        }

        public void addInputParam(String inputParm) {
            this.inputParam.add(inputParm);
        }

        public void addsListInputParam(List<String> listInputParam) {
            this.inputParam.addAll(listInputParam);
        }

        public List<String> getOutputParam() {
            return outputParam;
        }

        public void addOutputParam(String outputParm) {
            this.outputParam.add(outputParm);
        }

        public void addsListOutputParam(List<String> listOutputParm) {
            this.outputParam.addAll(listOutputParm);
        }

        public String toString(){
            String str;
            if (inputParam==null || inputParam.isEmpty())
                str="->"+outputParam.toString();
            else
                str=inputParam.toString()+"->"+outputParam.toString();

            return str;
        }
    }

    public static class TypeFieldVar extends TypeField{
        private String type;

        public TypeFieldVar(String type){
            this.type=type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String toString(){
            return type;
        }
    }
}
