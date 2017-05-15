package Interpreter;

/**
 * Created by illiant on 2017/5/15.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CoreFunction {
    public String getValidString(String input){
        String retStr = "";
        for(int i = 0;i < input.length() - 1;i++){
            if(input.substring(i, i + 1).equals(">") || input.substring(i, i + 1).equals("<") || input.substring(i, i + 1).equals("+")
                    || input.substring(i, i + 1).equals("-") || input.substring(i, i + 1).equals(".") || input.substring(i, i + 1).equals(",")
                    || input.substring(i, i + 1).equals("[") || input.substring(i, i + 1).equals("]")){
                retStr = retStr + input.substring(i,i + 1);
            }
        }

        return retStr;
    }

    public void func(String validString){
        ArrayList<Pointer> listOfPtr = new ArrayList<Pointer>();

        listOfPtr.add(new Pointer());
        int pc = 0;
        int dataptr = 0;
        char[] list = validString.toCharArray();
        //遍历一遍指令，创建指针数组
        for(int i = 0;i < list.length;i++){
            if(list[i] == '>'){
                listOfPtr.add(new Pointer());
            }
        }
        while(pc < validString.length()){
            char instruction = list[pc];
            switch(instruction){
                case '>' :
                    dataptr++;
                    break;
                case '<' :
                    dataptr--;
                    break;
                case '+' :
                    listOfPtr.get(dataptr).Increment();
                    break;
                case '-' :
                    listOfPtr.get(dataptr).Decrement();
                    break;
                case '.' :
                    listOfPtr.get(dataptr).outputChar();
                    break;
                case ',' :
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        String input = br.readLine();
                        listOfPtr.get(dataptr).value = (int) (input.toCharArray()[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                case '[' :
                    if(listOfPtr.get(dataptr).value == 0){
                        int bracket_nesting = 1;
                        int saved_pc = pc;

                        while(bracket_nesting == 1 && ++pc < list.length){
                            if(list[pc] == ']'){
                                bracket_nesting--;
                            }else if(list[pc] == '['){
                                bracket_nesting++;
                            }
                        }

                        if(bracket_nesting == 0){
                            break;
                        }else{
                            System.out.println("unmatched '[' at pc = " + Integer.toString(saved_pc));
                        }
                    }

                    break;

                case ']' :
                    if(listOfPtr.get(dataptr).value != 0){
                        int bracket_nesting = 1;
                        int saved_pc = pc;

                        while(bracket_nesting == 1 && pc > 0){
                            pc--;
                            if(list[pc] == '['){
                                bracket_nesting--;
                            }else if(list[pc] == ']'){
                                bracket_nesting++;
                            }
                        }

                        if(bracket_nesting == 0){
                            break;
                        }else{
                            System.out.println("unmatched ']' at pc = " + Integer.toString(saved_pc));
                        }
                    }

                    break;

                default :
                {
                    System.out.println("bad char " + instruction + "at pc = " + Integer.toString(pc));
                }
            }
            pc++;
        }
    }
}
