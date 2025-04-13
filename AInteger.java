import java.lang.Math;

public class AInteger{
    // Public String that stores the integer of arbitrary length
    public String s;
    public char sign;
    // Default constructor that initializes the value of string with 0 initially.
    public AInteger(){
        this.s = "0";
        this.sign = '+';
        
    }
    // Constructor that initializes the instance of a number as a string with passed argument
    public AInteger(String s){
            
        if (s == null || s.isEmpty()){
            throw new IllegalArgumentException("Invalid Input!!");
        }
        if(s.charAt(0)=='+' || s.charAt(0)== '-'){
            this.sign = s.charAt(0);
            s = s.substring(1);
        }else if(Character.isDigit(s.charAt(0))){
            this.sign = '+';
        }
        
    
    for (char c : s.toCharArray()) {
        if (!Character.isDigit(c)){
            throw new IllegalArgumentException("Invalid Input!!");
        }
    }
       this.s = s; 
    }
    // Copy constructor that creates an instance of AInteger
    public AInteger(AInteger other_copy){
        this.s = other_copy.s;
        this.sign = other_copy.sign;
    }
    // Static function that returns an instance of AInteger class
    public static AInteger parse(String s){
        return new AInteger(s);
    }
    public static AInteger add(AInteger s1, AInteger s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AInteger s2_mod = new AInteger(s2.s);
           return subtract(s1 , s2_mod);
        }else if(s1.sign=='-' && s2.sign=='+'){
            AInteger s1_mod = new AInteger(s1.s);
            return subtract(s2,s1_mod);
        }else if(s1.sign=='-' && s2.sign=='-'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = AInteger.add(s1_mod, s2_mod);
            result.sign = '-';
            return result;

        }
    String a = s1.s;
    String b = s2.s;
    int begin_substr = 0;
    for(;begin_substr<a.length();begin_substr++){
        if(a.charAt(begin_substr) != '0'){
            break;
        }
    }
    a = a.substring(begin_substr);
    begin_substr=0;
    for(;begin_substr<b.length();begin_substr++){
        if(b.charAt(begin_substr) != '0'){
            break;
        }
    }
    b = b.substring(begin_substr);
    int len_a = a.length();
    int len_b = b.length();

    if(len_a != len_b){
        int diff = Math.abs(len_a - len_b);
        String added_zeroes = "";
        for(int i=0;i<diff;i++){
            added_zeroes += "0";
        
        }
        if(len_a < len_b){
            a = added_zeroes + a;
        }else{
            b = added_zeroes + b;
        }
    }
    int len = a.length();
    String result = "";
    
    int carry=0;
    for(int i=len-1;i>=0;i--){
        int digit1 = (int)(a.charAt(i))-48;
        int digit2 = (int)(b.charAt(i))-48;
        int digit = digit1+digit2+carry;
        if(digit > 9){
            carry = 1;
            char digit_char = (char)(digit-10+48);
            result = digit_char + result;
        }else{
            carry = 0;
            char digit_char = (char)(digit+48);
            result = digit_char + result;
        }
    }
    if(carry == 1){
        result = "1" + result;
    }
    int remove_prefixing_zeroes=0;
    for(;remove_prefixing_zeroes<result.length();remove_prefixing_zeroes++){
        if(result.charAt(remove_prefixing_zeroes) != '0'){
            break;
        }
    }
    result = result.substring(remove_prefixing_zeroes);
    if(result.equals("")){
        result="0";
    }
    AInteger added_num = new AInteger(result);
    return added_num;

    
    }
    public static boolean compare(String a,String b){
        if(a.length() > b.length()){
            return true;
        }
        if(b.length() > a.length()){
            return false;
        }
        int n = a.length();
        
        for(int i=0;i<n;i++){
            int digit1 = (int)(a.charAt(i)) - 48;
            int digit2 = (int)(b.charAt(i)) - 48;
            if(digit1 > digit2){
                return true;
            }else if(digit1 < digit2){
                return false;
            }
        }
        return false;
        // returns true if a is greater than b and false otherwise
    }
    public static String string_subtract(String a,String b){
        // a is always greater than b
        int carry = 0;
        int n= a.length();
        int diff = a.length() - b.length();
        for(int i=0;i<diff;i++){
            b = "0"+b;
        }
        String result = "";
        for(int i=n-1;i>=0;i--){
            int digit1 = (int)(a.charAt(i)) - 48;
            int digit2 = (int)(b.charAt(i)) - 48;
            int digit = digit1+carry - digit2;
            if(digit < 0){
                char digit_char = (char)(digit+10+48);
                result = digit_char + result;
                carry = -1;
            }else{
                char digit_char = (char)(digit+48);
                result = digit_char + result;
                carry = 0;
            }

        }
        return result;

    }

    public static AInteger subtract(AInteger s1,AInteger s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = AInteger.add(s1,s2_mod);
            return result;
        }else if(s1.sign=='-' && s2.sign=='+'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = AInteger.add(s1_mod,s2_mod);
            result.sign = '-';
            return result;
        }else if(s1.sign=='-' && s2.sign=='-'){
            AInteger s2_mod = new AInteger(s2.s);
            AInteger s1_mod = new AInteger(s1.s);
            return AInteger.subtract(s2_mod,s1_mod);
        }
    String a = s1.s;
    String b = s2.s;
    int begin_substr = 0;
    for(;begin_substr<a.length();begin_substr++){
        if(a.charAt(begin_substr) != '0'){
            break;
        }
    }
    a = a.substring(begin_substr);
    begin_substr=0;
    for(;begin_substr<b.length();begin_substr++){
        if(b.charAt(begin_substr) != '0'){
            break;
        }
    }
    b = b.substring(begin_substr);
    if(b.equals(a)){
        return new AInteger("0");
    }
    if(b.length()< a.length() ){
        String result = string_subtract(a,b);
        AInteger result_obj = new AInteger(result);
        result_obj.sign = '+';
        return result_obj;
    }
    if(a.length() < b.length() ){
        String result = string_subtract(b,a);
        AInteger result_obj = new AInteger(result);
        result_obj.sign = '-';
        return result_obj;
    }
    if(compare(a,b)){
         String result = string_subtract(a,b);
        AInteger result_obj = new AInteger(result);
        result_obj.sign = '+';
        return result_obj;
    }
    if(compare(b,a)){
        String result = string_subtract(b,a);
        AInteger result_obj = new AInteger(result);
        result_obj.sign = '-';
        return result_obj;
    }
    return new AInteger("0");
    
    }

    public static AInteger multiply(AInteger s1,AInteger s2){
        if(s1.sign=='+' && s2.sign=='-'){
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = multiply(s1,s2_mod);
            result.sign='-';
            return result;
        }else if(s1.sign=='-' && s2.sign=='+'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger result = multiply(s1_mod,s2);
            result.sign='-';
            return result;
        }else if(s1.sign=='-' && s2.sign=='-'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = multiply(s1_mod,s2_mod);
            result.sign='+';
            return result;
        }
        String a = s1.s;
        String b = s2.s;
        int begin_substr = 0;
    for(;begin_substr<a.length();begin_substr++){
        if(a.charAt(begin_substr) != '0'){
            break;
        }
    }
    a = a.substring(begin_substr);
    begin_substr=0;
    for(;begin_substr<b.length();begin_substr++){
        if(b.charAt(begin_substr) != '0'){
            break;
        }
    }
    b = b.substring(begin_substr);
    if(a.equals("") || b.equals("")){
        return new AInteger("0");
    }
    int len_b = b.length();
    int len_a = a.length();
    
    AInteger result = new AInteger("0");
    // String[] str_to_be_added = new String[len_b];
    for(int i=0;i<len_b;i++){
        int multiply_digit = (int)(b.charAt(len_b-1-i)) - 48;
        AInteger str = new AInteger("0");
        for(int j=0;j<multiply_digit;j++){
            str = AInteger.add(str,new AInteger(a));
        }
        //str_to_be_added[i] = str;
        String string = str.s;
        for(int k=0;k<i;k++){
            string += "0";
        }
        AInteger added_string = new AInteger(string);
        result = AInteger.add(result,added_string);
    }


    result.sign = '+';
    return result;
        
    }
    public static class DivisionByZeroException extends ArithmeticException{
        public DivisionByZeroException(){
            super("Division by zero is not allowed.");
        }
        public DivisionByZeroException(String message){
            super(message);
        }
    }


    public static AInteger divide(AInteger s1,AInteger s2){
        if(s1.sign=='-' && s2.sign=='+'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger result = divide(s1_mod,s2);
            result.sign = '-';
            return result;
        }else if(s1.sign=='+' && s2.sign=='-'){
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = divide(s1,s2_mod);
            result.sign = '+';
            return result;
        }else if(s1.sign=='-' && s2.sign=='-'){
            AInteger s1_mod = new AInteger(s1.s);
            AInteger s2_mod = new AInteger(s2.s);
            AInteger result = divide(s1_mod,s2_mod);
            result.sign = '+';
            return result;
        }
        String a = s1.s;
        String b = s2.s;
        int begin_substr = 0;
        for(;begin_substr<a.length();begin_substr++){
            if(a.charAt(begin_substr) != '0'){
                break;
            }
        }
        a = a.substring(begin_substr);
        begin_substr = 0;
        for(;begin_substr < b.length();begin_substr++){
            if(b.charAt(begin_substr) != '0'){
                break;
            }
        }
        b = b.substring(begin_substr);
        if(b.equals("")){
            throw new DivisionByZeroException("Invalid !! Division by zero is not possible. Terminating...");

        }
        if(a.equals("")){
            return new AInteger("0");

        }
        if(a.equals(b)){
            return new AInteger("1");
        }
        
        AInteger remainder = new AInteger("0");
        String quotient = "";
        for(int i=0;i<a.length();i++){
            remainder = new AInteger(remainder.s.equals("0") ? "" + a.charAt(i) : remainder.s + a.charAt(i));

            int digit = 0;
            while(!compare(AInteger.multiply(new AInteger(b),new AInteger(String.valueOf(digit+1))).s , remainder.s)){
                digit++;
            }
            quotient += String.valueOf(digit);
            AInteger subtracted = AInteger.multiply(new AInteger(b),new AInteger(String.valueOf(digit)));
            remainder = AInteger.subtract(remainder,subtracted);
        
        if (!remainder.s.equals("0")) {
                int rem_start = 0;
                while (rem_start < remainder.s.length() - 1 && remainder.s.charAt(rem_start) == '0') {
                    rem_start++;
                }
                remainder.s = remainder.s.substring(rem_start);
            }
        }
        begin_substr = 0;
        for(;begin_substr<quotient.length();begin_substr++){
            if(quotient.charAt(begin_substr) != '0'){
                break;
            }
        }
        quotient = quotient.substring(begin_substr);
        if(quotient.equals("")){
            quotient = "0";
        }
        AInteger result = new AInteger(quotient);
        return result;
}
   

    public static void main(String[] args){
        AInteger num1 = new AInteger("8792726365283060579833950521677211");
        AInteger num2 = new AInteger("493835253617089647454998358");
        AInteger num = AInteger.divide(num1,num2);
        String number = num.s;
        System.out.println(number);

    }


}