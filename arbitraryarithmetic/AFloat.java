package arbitraryarithmetic;

import java.lang.Math;

public class AFloat{
    public String s;
    public char sign;
    public AFloat(){
        this.s = "0.0";
        this.sign = '+';
    }
    public AFloat(String s){
     try{
        if (s == null || s.isEmpty()){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(s.charAt(0)=='+' || s.charAt(0)== '-'){
            this.sign = s.charAt(0);
            s = s.substring(1);
        }else if(Character.isDigit(s.charAt(0))){
            this.sign = '+';
        }
        
    int decimal_count = 0;
    for (char c : s.toCharArray()) {
        
        if (!(Character.isDigit(c)) && decimal_count >= 1){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(c=='.'){
            decimal_count ++;
        }
    }

    if (!s.contains(".")) {
        s += ".0";
        }
        if (s.startsWith(".")) {
            s = "0" + s;
        }
        if (s.endsWith(".")) {
        s = s + "0";
        }
       this.s = s;
     }catch(IllegalArgumentException e){
        System.out.println(e.getMessage());
        System.exit(0);
     } 
    }
    public AFloat(AFloat other_copy){
        this.s = other_copy.s;
        this.sign = other_copy.sign;
    }
    public static AFloat parse(String s){
        return new AFloat(s);
    }
    private static AFloat int_to_float(String s){
        if (!s.contains(".")) {
        s += ".0";
        }
        if (s.startsWith(".")) {
            s = "0" + s;
        }
        if (s.endsWith(".")) {
        s = s + "0";
        }

        return parse(s);
    }
    private static String pad_left_zeroes(String s,int n){
        for(int i=0;i<n;i++){
            s = "0"+s;
        }
        return s;

    }
    private static String pad_right_zeroes(String s,int n){
        for(int i=0;i<n;i++){
            s = s+"0";

        }
        return s;

    }

    public static AFloat add(AFloat s1, AFloat s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AFloat s2_mod = new AFloat(s2.s);
           AFloat result = subtract(s1,s2_mod);
            return result;
        }else if(s1.sign=='-' && s2.sign=='+'){
           AFloat s1_mod = new AFloat(s1.s);
            return subtract(s2,s1_mod);
        }else if(s1.sign=='-' && s2.sign=='-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.add(s1_mod, s2_mod);
            result.sign = '-';
            return result;

        }
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        int num_digits_a = a.substring(0,a_decimal_place).length();
        int num_digits_b = b.substring(0,b_decimal_place).length();
        // Pad left zeroes and right zeroes to make equal length strings for addition
        if(num_digits_a < num_digits_b){
            int diff = num_digits_b - num_digits_a;
            a = pad_left_zeroes(a,diff);
        }else{
            int diff = num_digits_a - num_digits_b;
            b = pad_left_zeroes(b,diff);
        }

        if(num_decimals_a < num_decimals_b){
            int diff = num_decimals_b - num_decimals_a;
            a = pad_right_zeroes(a,diff);
        }else{
            int diff = num_decimals_a - num_decimals_b;
            b = pad_right_zeroes(b,diff);
        }
        // Again make them into integers
        a_decimal_place = 0;
        b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        String a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
        String b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
        int decimal_length = a.substring(a_decimal_place+1).length();
        AInteger a_integer = new AInteger(a_int);
        AInteger b_integer = new AInteger(b_int);
        AInteger sum = AInteger.add(a_integer,b_integer);
        String result = sum.s;
        int len = result.length();
        int digit_len = len - decimal_length;
        result = result.substring(0,digit_len) + "."+result.substring(digit_len);
        AFloat result_float = new AFloat(result);
        return result_float;
        


    }
    public static AFloat subtract(AFloat s1, AFloat s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AFloat s2_mod = new AFloat(s2.s);
           AFloat result = add(s1,s2_mod);
            return result;
        }else if(s1.sign=='-' && s2.sign=='+'){
           AFloat s1_mod = new AFloat(s1.s);
           AFloat result = add(s1_mod,s2);
           result.sign = '-';
           return result;
        }else if(s1.sign=='-' && s2.sign=='-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = subtract(s2_mod,s1_mod);
            
            return result;

        }
        
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        int num_digits_a = a.substring(0,a_decimal_place).length();
        int num_digits_b = b.substring(0,b_decimal_place).length();
        // Pad left zeroes and right zeroes to make equal length strings for addition
        if(num_digits_a < num_digits_b){
            int diff = num_digits_b - num_digits_a;
            a = pad_left_zeroes(a,diff);
        }else{
            int diff = num_digits_a - num_digits_b;
            b = pad_left_zeroes(b,diff);
        }

        if(num_decimals_a < num_decimals_b){
            int diff = num_decimals_b - num_decimals_a;
            a = pad_right_zeroes(a,diff);
        }else{
            int diff = num_decimals_a - num_decimals_b;
            b = pad_right_zeroes(b,diff);
        }
        // Again make them into integers
        a_decimal_place = 0;
        b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        String a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
        String b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
        int decimal_length = a.substring(a_decimal_place+1).length();
        AInteger a_integer = new AInteger(a_int);
        AInteger b_integer = new AInteger(b_int);
        AInteger difference = AInteger.subtract(a_integer,b_integer);
        String result = difference.s;
        char result_sign = difference.sign;
        int len = result.length();
        int digit_len = len - decimal_length;
        result = result.substring(0,digit_len) + "."+result.substring(digit_len);
        AFloat result_float = new AFloat(result);
        result_float.sign = result_sign;
        return result_float;
        


    }
    private static String remove_excess_zeroes_on_right(String str){
        boolean first_non_zero_decimal_digit_found = false;
        int len = str.length();
        int num_excess_zeroes_on_right = 0;
        for(int i=len-1;i>=0;i--){
            char digit = str.charAt(i);
            if(digit != '0'){
                first_non_zero_decimal_digit_found = true;
                break;
            }else{
                num_excess_zeroes_on_right ++;

            }
        }
        String processed_str = str.substring(0,len-num_excess_zeroes_on_right);
        return processed_str;
    }
    public static AFloat multiply(AFloat s1, AFloat s2){
        if(s1.sign == '-' && s2.sign == '+'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat result = AFloat.multiply(s1_mod,s2);
            result.sign = '-';
            return result;
        }else if(s1.sign == '+' && s2.sign == '-'){
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.multiply(s1,s2_mod);
            result.sign = '-';
            return result;
        }else if(s1.sign == '-' && s2.sign == '-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.multiply(s1_mod,s2_mod);
            result.sign = '+';
            return result;
        }
        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        int decimal_length = num_decimals_a + num_decimals_b;
        // Pad left zeroes and right zeroes to make equal length strings for addition
        
        // Again make them into integers
        
        String a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
        String b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
        
        AInteger a_integer = new AInteger(a_int);
        AInteger b_integer = new AInteger(b_int);
        AInteger product = AInteger.multiply(a_integer,b_integer);
        String result = product.s;
        
        int len = result.length();
        int digit_len = len - decimal_length;
        result = result.substring(0,digit_len) + "."+result.substring(digit_len);
        result = remove_excess_zeroes_on_right(result);
        result = int_to_float(result).s;
        AFloat result_float = new AFloat(result);
        
        return result_float;
        




    }
    public static class DivisionByZeroException extends ArithmeticException{
        public DivisionByZeroException(){
            super("Division by zero is not allowed.");
        }
        public DivisionByZeroException(String message){
            super(message);
        }
    }

    public static AFloat divide(AFloat s1, AFloat s2){

        try{
            boolean is_denominator_zero = true;
            for(int i=0;i<s2.s.length();i++){
                char ch = s2.s.charAt(i);
                if(ch != '0' && ch != '.'){
                    is_denominator_zero = false;
                    break;
                }else{
                    continue;
                }
            }
            if(is_denominator_zero){
                throw new DivisionByZeroException("Division by zero error");
            }
        }catch(DivisionByZeroException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }

        boolean is_numerator_zero = true;
            for(int i=0;i<s1.s.length();i++){
                char ch = s1.s.charAt(i);
                if(ch != '0' && ch != '.'){
                    is_numerator_zero = false;
                    break;
                }else{
                    continue;
                }
            }
        if(is_numerator_zero){
            return new AFloat("0.0");
        }

        if(s1.sign == '-' && s2.sign == '+'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat result = AFloat.divide(s1_mod,s2);
            result.sign = '-';
            return result;
        }else if(s1.sign == '+' && s2.sign == '-'){
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.divide(s1,s2_mod);
            result.sign = '-';
            return result;
        }else if(s1.sign == '-' && s2.sign == '-'){
            AFloat s1_mod = new AFloat(s1.s);
            AFloat s2_mod = new AFloat(s2.s);
            AFloat result = AFloat.divide(s1_mod,s2_mod);
            result.sign = '+';
            return result;
        }

        // Actual logic of float division for two positive floating point numbers

        String a = s1.s;
        String b = s2.s;
        int a_decimal_place = 0;
        int b_decimal_place = 0;
        for(;a_decimal_place < a.length();a_decimal_place++){
            if(a.charAt(a_decimal_place) == '.'){
                break;
            }
        }
        for(;b_decimal_place < b.length();b_decimal_place++){
            if(b.charAt(b_decimal_place) == '.'){
                break;
            }
        }
        // Number always has exactly one decimal digit
        int num_decimals_a = a.substring(a_decimal_place+1).length();
        int num_decimals_b = b.substring(b_decimal_place+1).length();
        String a_int = "";
        String b_int = "";
        

        if(num_decimals_a > num_decimals_b){
            a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
            b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
            int diff_decimals = num_decimals_a - num_decimals_b;
            for(int i=0;i<diff_decimals;i++){
                b_int += "0";

            }
        }else{
            a_int = a.substring(0,a_decimal_place) + a.substring(a_decimal_place+1);
             b_int = b.substring(0,b_decimal_place) + b.substring(b_decimal_place+1);
            int diff_decimals = num_decimals_b - num_decimals_a;
            for(int i=0;i<diff_decimals;i++){
                a_int += "0";

            }
        }


        String thousand_precision_maker = "1";
        for(int i=0;i<1000;i++){
            thousand_precision_maker += "0";
        }

        

        a_int = (AInteger.multiply(new AInteger(a_int),new AInteger(thousand_precision_maker))).s;

        AInteger numerator = new AInteger(a_int);
        AInteger denominator = new AInteger(b_int);

        AInteger result = AInteger.divide(numerator,denominator);

        String result_int = result.s;
        
        String result_float = result_int.substring(0,result_int.length()-1000) + "." + result_int.substring(result_int.length()-1000);
        result_float = remove_excess_zeroes_on_right(result_float);
        AFloat quotient = int_to_float(result_float);
        return quotient;




    }
    public static void main(String[] args){
        AFloat num1 = new AFloat("1.2");
        AFloat num2 = new AFloat("200");
        AFloat num = AFloat.divide(num1,num2);
        System.out.println(num.s);

    }
}