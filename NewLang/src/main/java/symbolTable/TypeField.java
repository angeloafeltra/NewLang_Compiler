package main.java.symbolTable;

import java.util.ArrayList;

public class TypeField {


    public static class TypeFieldFunction extends TypeField{
        private ArrayList<String> inputParam;
        private ArrayList<String> outputParam;

        public TypeFieldFunction(){
            inputParam=new ArrayList<String>();
            outputParam=new ArrayList<String>();
        }

        public TypeFieldFunction (ArrayList<String> inputParam, ArrayList<String> outputParam){
            this.inputParam=inputParam;
            this.outputParam=outputParam;
        }

        public ArrayList<String> getInputParam() {
            return inputParam;
        }

        public void addInputParam(String inputParm) {
            this.inputParam.add(inputParm);
        }

        public void addsListInputParam(ArrayList<String> listInputParam) {
            this.inputParam.addAll(listInputParam);
        }

        public ArrayList<String> getOutputParam() {
            return outputParam;
        }

        public void addOutputParam(String outputParm) {
            this.outputParam.add(outputParm);
        }

        public void addsListOutputParam(ArrayList<String> listOutputParm) {
            this.outputParam.addAll(listOutputParm);
        }

        public String toString(){
            String str;
            if (inputParam==null || inputParam.size()==0)
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
