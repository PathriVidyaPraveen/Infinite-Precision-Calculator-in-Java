import arbitraryarithmetic.AInteger;
import arbitraryarithmetic.AFloat;
// Imports both the files and corresponding functions from AInteger.java and AFloat.java


public class MyInfArith {
    // checks if the number of arguments is exactly 4 or not
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("Invalid Input");
            return;
        }
// As the input command is "java MyInfArith int add 123 234" , first argument is datatype ,s econd is operation and third and fourth are operands
        String datatype = args[0].toLowerCase();
        String operation = args[1];
        String num1 = args[2];
        String num2 = args[3];
// calls the corresponding function by using switch cases to match each operand and then outputting desired result
        switch (datatype) {
            case "int":
                AInteger int1 = new AInteger(num1);
                AInteger int2 = new AInteger(num2);
                switch (operation) {
                    case "add":
                        AInteger result_add_int = AInteger.add(int1,int2);
                        if(result_add_int.sign == '+'){
                            System.out.println(result_add_int.s);
                        }else{
                            System.out.println(result_add_int.sign + result_add_int.s);
                        }
                        break;
                    case "sub":
                       
                       AInteger result_subtract_int = AInteger.subtract(int1,int2);
                        if(result_subtract_int.sign == '+'){
                            System.out.println(result_subtract_int.s);
                        }else{
                            System.out.println(result_subtract_int.sign + result_subtract_int.s);
                        }
                        break;
                    case "mul":
                        AInteger result_multiply_int = AInteger.multiply(int1,int2);
                        if(result_multiply_int.sign == '+'){
                            System.out.println(result_multiply_int.s);
                        }else{
                            System.out.println(result_multiply_int.sign + result_multiply_int.s);
                        }
                        break;
                    case "div":
                        AInteger result_divide_int = AInteger.divide(int1,int2);
                        if(result_divide_int.sign == '+'){
                            System.out.println(result_divide_int.s);
                        }else{
                            System.out.println(result_divide_int.sign + result_divide_int.s);
                        }
                        break;
                    default:
                        System.out.println("Invalid Operation. Please try again");
                }
                break;

            case "float":
                AFloat float1 = new AFloat(num1);
                AFloat float2 = new AFloat(num2);
                switch (operation) {
                    case "add":
                        AFloat result_add_float = AFloat.add(float1,float2);
                        if(result_add_float.sign == '+'){
                            System.out.println(result_add_float.s);
                        }else{
                            System.out.println(result_add_float.sign + result_add_float.s);
                        }
                        break;
                    case "sub":
                        AFloat result_subtract_float = AFloat.subtract(float1,float2);
                        if(result_subtract_float.sign == '+'){
                            System.out.println(result_subtract_float.s);
                        }else{
                            System.out.println(result_subtract_float.sign + result_subtract_float.s);
                        }
                        break;
                    case "mul":
                        AFloat result_multiply_float = AFloat.multiply(float1,float2);
                        if(result_multiply_float.sign == '+'){
                            System.out.println(result_multiply_float.s);
                        }else{
                            System.out.println(result_multiply_float.sign + result_multiply_float.s);
                        }
                        break;
                    case "div":
                        AFloat result_divide_float = AFloat.divide(float1,float2);
                        if(result_divide_float.sign == '+'){
                            System.out.println(result_divide_float.s);
                        }else{
                            System.out.println(result_divide_float.sign + result_divide_float.s);
                        }
                        break;
                    default:
                        System.out.println("Invalid Operation.Please try again");
                }
                break;

            default:
                // this happens due to incorrect datatype.
                System.out.println("Invalid data datatype. Please try again. Use only 'int' or 'float'");
        }
    }
}