import java.util.Stack;

/**
 * This class provides a custom implementation for features of the BigInteger class.
 * All the operations like +, -, ?, *, % and all the basic operations provided by a basic
 * calculator have been implemented.
 * All these operations can be performed in any provided @base
 *
 *
 * The members of this project are Ketki Mahajan, Ameya Kasar, Sunny Bangale, Shreyash Mane
 */

public class Num implements Comparable<Num> {

    static long defaultBase = 1000000000;  // Change as needed
    long base = defaultBase;  // Change as needed
    long[] arr;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in arr[0..len-1]

    //Default constructor which sets the default values for all the class variables
    public Num() {
        this.arr = null;
        this.len = 0;
        this.base = base;
        this.isNegative = false;
    }

    /**
     * Constructor which accepts a long array and creates a new Num object with the passed array as its array
     * @param x the array to be set as the array of the new Num
     */
    public Num(long[] x) {
        this.arr = x;
        base = base;
        this.len = x.length;
    }

    /**
     * Constructor which accepts a string input and creates a Num object with the numeric value of the passed string in the base value
     * @param s which is used to create a new array
     */
    public Num(String s) {
        this(s,defaultBase);

    }
    /**
     * Constructor which creates a new Num from the passed string value for the provided base
     * @param s string value which should be used to create a num array
     * @param newBase the base which should be used to create a new object
     */
    public Num(String s, long newBase) {
        this.arr= new long[s.length()];
        int i = 0;
        if(s.charAt(0) == '-') {
            this.isNegative = true;
            for(int k = s.length()-1; k > 0; k--){
                this.arr[i] = (long) s.charAt(k) - '0';
                i++;
            }
        }
        else {
            for (int k = s.length() - 1; k >= 0; k--) {
                this.arr[i] = (long) s.charAt(k) - '0';
                i++;
            }
        }
        base = 10;
        this.len=this.arr.length;
        Num x= this.convertBase((int)newBase);
        this.arr=x.arr;
        this.len=x.len;
        base = newBase;
    }

    /**
     * Constructor which creates a new Num object by using a long value
     * @param x the value which is used to create the array
     */
    public Num(long x) {
        this(x,defaultBase);
    }

    /**
     * Constructor which creates a new Num by using a long value and the base passed
     * @param number long value to be used to form the array of the num
     * @param newBase   the base value in which the Num should be generated
     */
    public Num (long number, long newBase) {

        //this.base = 10;

        base = newBase;
        if (number < 0) {
            this.isNegative = true;
            number = Math.abs(number);
        }

        //this.arr = new long[(int) Math.pow(10, (Long.toString(number).length() + 1))];
        //this.arr = new long[Long.toString(number).length()];
        this.arr = new long[1000];
        if (number == 0) {
            this.len = 1;
            return;
        }
        int index = 0;
        while (number > 0) {
            this.arr[index++] = number % base;
            this.len++;
            number /= base;
        }
    }

    /**
     * Adds 2 Num objects and returns the result Num
     * @implNote Both the inputs should be in the same base
     * @param a first Num input
     * @param b second Num input
     * @return result of the addition
     */

    public static Num add(Num a, Num b) {
        Num answer = new Num();
        Num x = new Num();
        Num y = new Num();
        long[] result = new long[Math.max(a.len,b.len)+1];
        long size =Math.max(b.len,a.len);
        if((a.isNegative && !b.isNegative) || (!a.isNegative && b.isNegative))
        {
            if (a.len > b.len) {
                x = a;
                y = b;
                if(a.isNegative) answer.isNegative = true;
                else if(b.isNegative) answer.isNegative = false;
            } else if (a.len < b.len) {
                x = b;
                y = a;
                if(a.isNegative) answer.isNegative = false;
                else if(b.isNegative) answer.isNegative = true;
            } else if (a.len == b.len) {
                if (a.compareMagnitude(b) == 1 || a.compareMagnitude(b) == 0) {
                    x = a;
                    y = b;
                    if (a.isNegative)
                        answer.isNegative = true;
                    else if (b.isNegative)
                        answer.isNegative = false;
                } else if (a.compareMagnitude(b) == -1) {
                    x = b;
                    y = a;
                    if(a.isNegative) answer.isNegative = false;
                    else if(b.isNegative) answer.isNegative = true;
                }
            }
            answer.arr = subhelper(x.arr, y.arr, result,a.base);
        }
        if(a.isNegative && b.isNegative) {
            answer.arr = addhelper(a, b, result);
            answer.isNegative = true;
        }
        else if(!a.isNegative && !b.isNegative)
            answer.arr = addhelper(a, b, result);
        //Num answer = new Num();
        answer.arr = removeTrailingZeros(result);
        answer.len = answer.arr.length;
        answer.base = a.base;
        return answer;
    }


    /**
     * Subtracts 2 Num objects and returns the result Num
     * @implNote Both the inputs should be in the same base
     * @param a first Num input
     * @param b second Num input
     * @return result of the subtraction
     */
    public static Num subtract(Num a, Num b)
    {
        long[] p1=new long[Math.max(a.len,b.len)];
        long[] p2=new long[Math.max(b.len,a.len)];
        /*System.arraycopy(a.arr,0,p1,0,a.len);
        System.arraycopy(b.arr,0,p2,0,b.len);*/


//        Num x = new Num(a.arr);
//        Num y = new Num(b.arr);
        boolean lessThanZero = false;
        long[] result = new long[Math.max(a.len,b.len)+1];
        if(b.isNegative && !a.isNegative)
        {
            result = addhelper(a, b, result);
        }
        else if(a.isNegative && !b.isNegative)
        {
            result = addhelper(a, b, result);
            lessThanZero = true;
        }
        else if(!a.isNegative && !b.isNegative)
        {
            if(a.len > b.len){
                System.arraycopy(a.arr,0,p1,0,a.len);
                System.arraycopy(b.arr,0,p2,0,b.len);
            }


            else if(a.len < b.len){
                System.arraycopy(a.arr,0,p2,0,a.len);
                System.arraycopy(b.arr,0,p1,0,b.len);
                lessThanZero = true;
            }


            else if(a.len == b.len){
                if (a.compareMagnitude(b) == 1 || a.compareMagnitude(b) == 0){
                    System.arraycopy(a.arr,0,p1,0,a.len);
                    System.arraycopy(b.arr,0,p2,0,b.len);
                }else if(a.compareMagnitude(b) == -1){
                    System.arraycopy(a.arr,0,p2,0,a.len);
                    System.arraycopy(b.arr,0,p1,0,b.len);
                    lessThanZero = true;
                }
            }
            result = subhelper(p1, p2, result,a.base);
        }else if(a.isNegative && b.isNegative){
            if (a.len > b.len) {
                System.arraycopy(a.arr, 0, p1, 0, a.len);
                System.arraycopy(b.arr, 0, p2, 0, b.len);
            } else if (a.len < b.len) {
                System.arraycopy(a.arr, 0, p2, 0, a.len);
                System.arraycopy(b.arr, 0, p1, 0, b.len);
                lessThanZero = true;
            } else if (a.len == b.len) {
                if (a.compareMagnitude(b) == 1 || a.compareMagnitude(b) == 0) {
                    System.arraycopy(a.arr, 0, p1, 0, a.len);
                    System.arraycopy(b.arr, 0, p2, 0, b.len);
                } else if (a.compareMagnitude(b) == -1) {
                    System.arraycopy(a.arr, 0, p2, 0, a.len);
                    System.arraycopy(b.arr, 0, p1, 0, b.len);
                    lessThanZero = true;
                }
            }


            //result = answer.subhelper(a.arr, b.arr, result);
            result = subhelper(p1, p2, result,a.base);
            if(a.compareMagnitude(b)==1){
                lessThanZero = true;
            }
        }
        /*Num tempResult = new Num(result);
        tempResult.isNegative = lessThanZero;

        tempResult.arr = removeTrailingZeros(result);
        tempResult.len = tempResult.arr.length;
        return tempResult;*/
        Num answer = new Num();
        answer.isNegative = lessThanZero;
        answer.arr = removeTrailingZeros(result);
        answer.len = answer.arr.length;
        answer.base = a.base;
        return answer;
    }
    /**
     * Multiplies 2 Num objects and returns the result Num
     * @implNote Both the inputs should be in the same base
     * @param a first Num input
     * @param b second Num input
     * @return result of the multiplication
     */
    public static Num product(Num a, Num b) {
        long carry = 0;
        int index = 0;
        long[] result = new long[a.len + b.len];
        for (int i = 0; i < a.len; i++) {
            index = i;
            for (int j = 0; j < b.len; j++) {
                long x=result[index]+ (a.arr[i] * b.arr[j]) + carry;
                result[index] = x % a.base();
                carry = x / a.base();
                index++;
            }
            if (carry > 0) {
                result[index] = carry;
                carry=0;
            }
        }

        if (carry > 0) {
            result[index] = carry;
        }

        Num answer = new Num();
        answer.arr = removeTrailingZeros(result);
        answer.len = answer.arr.length;
        answer.base = a.base;
        answer.isNegative = a.isNegative ^ b.isNegative;
        return answer;
    }
    /**
     *  A utility method which removes unnecessary zeroes from the provided array
     *  like 00500 is returned as 005
     * @param array whose zeroes needs to be removed
     * @return a new array which no longer has extra zeroes in it
     */

    public static long[] removeTrailingZeros(long[] array) {
        int i = array.length - 1;
        long[] result = null;
        if (array.length > 0) {
            while (array[i] == 0 && i > 0) {
                i--;
            }
            result = new long[i + 1];
            for (int j = 0; j <= i; j++) {
                result[j] = array[j];
            }
        }
        return result;
    }

    /**
     * Computes the power operation on a Num and returns the result Num using the Power algorithm i.e Divide and Conquer strategy
     * @implNote Both the inputs should be in the same base
     * @param a first Num input
     * @param n power to which the Num should be raised
     * @return result of the power operation
     */
    public static Num power(Num a, long n) throws IllegalArgumentException{

        Num zero = new Num(0);
        if (n < 0) {
            return zero;
        }
        if (n == 0 && a.compareMagnitude(zero)==0)
        {
            throw new IllegalArgumentException("Undefined");
        }


        if (n == 0) {
            return new Num(1);
        }
        Num p = power(product(a, a), n / 2);
        p.base = a.base;
        if (n % 2 == 0)
            return p;
        else {
            p = product(p, a);

            if (a.isNegative) {
                p.isNegative = true;
            }

            return p;
        }
    }
    /**
     * Divides 2 Num objects and returns the result Num using the Binary search algorithm
     * @implNote Both the inputs should be in the same base
     * @param a first Num input
     * @param b second Num input
     * @return result of the division
     */
    public static Num divide(Num a, Num b) throws ArithmeticException {
        Num left = new Num(0);
        Num right = getAbsNum(a);
        Num divisor = getAbsNum(b);
        Num mid = new Num();
        Num one = new Num(1);
        Num zero = new Num(0);

        if (divisor.compareMagnitude(zero) == 0) {
            throw new ArithmeticException("Denominator cannot be 0");
        } else if (a.compareMagnitude(zero) == 0) {
            return zero; // If the numerator is 0, the answer will always be 0
        }
        //Handling egde case scenario where if the denominator is 1,
        // there is no need for any computation and the answer will always be 1
        else if (divisor.compareMagnitude(one) == 0) {
            right.isNegative = a.isNegative ^ b.isNegative;
            return right;

        } else {
            while (left.compareMagnitude(right) == -1) {
                //System.out.println("Divide");
                // calculate mid
                mid = by2(add(left, right));

                // if y*mid is almost equal to x , return mid
                //System.out.println(left + " : " + mid + " : " + right + " - ");
                if ((product(divisor, mid).compareMagnitude(getAbsNum(a)) <= 0) && product(divisor, add(mid, one)).compareMagnitude(getAbsNum(a)) > 0) {
                    mid.isNegative = a.isNegative ^ b.isNegative;
                    mid.base = a.base;
                    return mid;
                }

                // if y*mid is less than x, update left to mid
                if (product(divisor, mid).compareMagnitude(getAbsNum(a)) == -1) {
                    left = mid;
                } else if (product(divisor, mid).compareMagnitude(getAbsNum(a)) == 1) {   // if y*mid is more than x, update right to mid
                    right = mid;
                }
            }
        }
        mid.isNegative = a.isNegative ^ b.isNegative;
        mid.base = a.base;
        return mid;
    }
    /**
     * Takes a Num object and returns a new Num object with its isNegative flag set to false
     * @param givenNum whose absolute value is needed
     * @return Num object which has its isNegative flag set to false
     */
    public static Num getAbsNum(Num givenNum) {
        long[] tempArray = new long[givenNum.len];
        System.arraycopy(givenNum.arr, 0, tempArray, 0, givenNum.len);
        Num temp = new Num(tempArray);
        temp.isNegative = false;
        temp.base = givenNum.base;
        return temp;
    }

    /**
     * Performs modulus of 2 Num objects and returns the result Num
     * @implNote Both the inputs should be in the same base
     * @param a first Num input
     * @param b second Num input
     * @return result of the modulus
     */
    public static Num mod(Num a, Num b) {

        Num quotient = divide(a,b);
        Num answer = product(quotient,b);
        Num remainder = subtract(a,answer);

        return remainder;
    }
    /**
     * Computes the square root of the provided Num
     * @param a the Num whose square root is to be computed
     * @return Num which represents the square root
     */
    public static Num squareRoot(Num a) throws  IllegalArgumentException{
        Num zero = new Num(0);
        Num one = new Num(1);
        if (zero.compareTo(a) == 0) {
            return zero;
        } else if (one.compareTo(a) == 0) {
            return one;
        } else {
            Num left = new Num(1);
            left.base = a.base;
            Num right = a;
            right.base = a.base;
            while (left.compareTo(right) == -1) {
                Num mid = by2(add(left, right));
                mid.base = a.base;
                if ((product(mid, mid).compareTo(a) == -1 || product(mid, mid).compareTo(a) == 0) && product(add(mid, one), add(mid, one)).compareTo(a) == 1) {
                    return mid;
                } else if (product(mid, mid).compareTo(a) == 1) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
        }
        throw new IllegalArgumentException("Negative Numbers not supported");
    }


    /**
     * A utitlity function which helps us to compare to Num objects
     * @param other the num object that needs to be compared with
     * @return 1 if the param is less than this, 0 if equal, else -1
     */
    public int compareTo(Num other) {
        if(!this.isNegative && !other.isNegative)
        {
            return this.compareMagnitude(other);
        }
        else if(this.isNegative && other.isNegative)
        {
            int flag = this.compareMagnitude(other);
            //System.out.println(flag);
            if(flag == -1) {
                //  System.out.println("OKAY");
                return 1;
            }
            else if(flag == 1)
                return -1;
            else
                return 0;
        }
        else if(!this.isNegative && other.isNegative)
        {
            return 1;
        }
        else if(this.isNegative && !other.isNegative)
        {
            return -1;
        }


        return 0;
    }


    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    public void printList() {
        System.out.print(this.base() + " : ");
        for (int i = 0; i < this.len; i++)
            System.out.print(arr[i] + " ");
    }



    // Divide by 2, for using in binary search
    public static Num by2(Num a) {
        long carry = 0;
        int index = a.len - 1;
        int j = a.len - 1;
        long n = 0;
        long[] result = new long[a.len];
        while (j >= 0) {
            n = a.arr[j] + (carry * a.base());
            result[index] = n / 2;
            index--;
            carry = n % 2;
            j--;
        }

        Num answer = new Num();
        answer.arr = removeTrailingZeros(result);
        answer.len = answer.arr.length;
        answer.base = a.base;
        answer.isNegative = a.isNegative;
        return answer;
    }





    // Evaluate an expression in postfix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluatePostfix(String[] expr) {

        //create a operandStack
        Stack<Num> operandStack = new Stack<>();
        // Scan all characters one by one
        for(int i = 0; i < expr.length; i++)
        {
            String c = expr[i];
            if(c.equals(" "))
                continue;
            else
            if (c.matches("[-+*/%^]"))
            {
                Num val1 = operandStack.pop();
                Num val2 = operandStack.pop();
                switch(c)
                {
                    case "*":
                        operandStack.push(product(val2,val1));
                        break;

                    case "+":
                        operandStack.push(add(val2,val1));
                        break;

                    case "-":
                        operandStack.push(subtract(val2,val1));
                        break;

                    case "/":
                        operandStack.push(divide(val2,val1));
                        break;

                    case "%":
                        operandStack.push(mod(val2,val1));
                        break;

                    case "^":
                        operandStack.push(power(val2,convertToLong(val1)));
                        break;
                }
            }
            else
            {
                Num n = new Num(c);
                operandStack.push(n);
            }
        }
        //return operandStack.pop();

        Num a = operandStack.pop();
        long result[] = a.arr;
        a.arr = removeTrailingZeros(result);
        return a;

    }

    /**
     * Returns the precedence order for the passed character
     * @param ch the character whose precedence is needed
     * @return the precedence value
     */
    static int Prec(String ch){
        switch(ch)
        {
            case "+":
            case "-":
                return 1;

            case "*":
            case "/":
            case "%":
                return 2;

            case "^":
                return 3;
        }
        return -1;
    }

    // Evaluate an expression in infix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "(", ")", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluateInfix(String[] expr) {

        String[] result = new String[expr.length];
        Stack<String> operatorStack = new Stack<>();
        int bracketCount = 0;
        int index=0;
        for (int i = 0; i<expr.length; ++i)
        {
            String c = expr[i];
            if (c.equals("(")) {
                bracketCount++;
                operatorStack.push(c);
            } else if (c.equals(")")) {
                bracketCount++;
                while (!operatorStack.isEmpty() && ! operatorStack.peek().equals("(")) {
                    result[index++] = operatorStack.pop();

                }
                if (!operatorStack.isEmpty() && !operatorStack.peek().equals("("))
                    System.out.println("Invalid");
                else{
                    operatorStack.pop();
                }
            } else if(c.matches("[-+*/%^]")){
                while (!operatorStack.isEmpty() && Prec(c) <= Prec(operatorStack.peek())){
                    result[index++]= operatorStack.pop();
                }
                operatorStack.push(c);
            }
            else
            {
                result[index++] = c;
            }

        }
        while (!operatorStack.isEmpty())
            result[index++] = operatorStack.pop();


        String[] postfixArray = new String[result.length-bracketCount];
        System.arraycopy(result,0,postfixArray, 0,result.length-bracketCount );


        return evaluatePostfix(postfixArray);

    }


    /**
     * Returns the base of the current object
     * @return base value
     */
    public long base() {
        return base;
    }
    /**
     * A utility method which helps in addition of 2 Nums
     * @param x first input Num
     * @param y second input Num
     * @param result the result array which contains the result of the addition
     * @return the result array that has the answer
     */
    public static long[] addhelper(Num x, Num y, long result[]) {
        int i = 0,j = 0;
        long sum = 0;
        long carry = 0;
        int index = 0;
        while(i < x.len && j < y.len)
        {
            sum = x.arr[i] + y.arr[j] + carry;
            result[index] = sum % x.base();
            index++;
            carry = sum / x.base();
            i++;
            j++;
        }

        while(i < x.len)
        {
            sum = x.arr[i] + carry;
            result[index] = sum % x.base();
            index++;
            carry = sum / x.base();
            i++;
        }

        while(j < y.len)
        {
            sum =  y.arr[j] + carry;
            result[index] = sum % x.base;
            index++;
            carry = sum / x.base;
            j++;
        }

        if(carry > 0)
        {
            result[index] = carry;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        Num x = new Num(-10,2);
        Num y = new Num(7,2);



        Num b = subtract(x, y);
        System.out.println("2. Subtraction: " + b.toString());b.printList();
        System.out.println();

        Num d = divide(x, y);
        System.out.println("4. Division: " + d.toString());d.printList();
        System.out.println();



        Num c = product(x, y);
        System.out.println("3. Product: " + c.toString());c.printList();
        System.out.println();

        Num e = mod(x, y);
        System.out.println("5. Modulo: " + e.toString());e.printList();
        System.out.println();

        Num a = add(x, y);
        System.out.println("1. Addition: " + a.toString());a.printList();
        System.out.println();

        long n = 4;
        Num f = power(x, n);
        System.out.println("6. Power: " + f.toString());f.printList();
        System.out.println();

        int g = x.compareTo(y);
        System.out.println("7. Compare To: " + g);
        System.out.println();

        try {
            Num h = squareRoot(x);
            System.out.println("8. Square Root: " + h.toString());h.printList();
            System.out.println();
        }catch (Exception t){
            System.out.println("Negative numbers not allowed!");
        }


        Num i = by2(x);
        System.out.println("9. By 2: " + i.toString());i.printList();
        System.out.println();

        System.out.println("10. INfix");

        String expr4[] = {"2", "5", "^"};
        System.out.println("11. POSTfix " + evaluatePostfix(expr4));

        System.out.println();

        System.out.println("12. To String:" + x.toString());
        System.out.println();

        System.out.println("13. Long Constructor");
        System.out.println("14. String Constructor");
        System.out.println("15. New Constructors");
        System.out.println();

        System.out.println("16. Printlist");
        x.printList();
        System.out.println();
        System.out.println();

        System.out.println("17. Num to Long Constructor");
        System.out.println();

        System.out.println("17. Trailing Zeros " + new Num(removeTrailingZeros(x.arr)).toString());
        System.out.println();


    }
    /**
     * A utility method which helps in subtraction of 2 Nums
     * @param x first input Num
     * @param y second input Num
     * @param result the result array which contains the result of the subtraction
     * @return the result array that has the answer
     */
    public static long[] subhelper(long[] x, long[] y, long result[],long givenBase) {
        int i = 0, j = 0;
        x = removeTrailingZeros(x);
        y = removeTrailingZeros(y);
        int index = 0;
        long diff;
        while (i < x.length && j < y.length) {
            if (x[i] >= y[j]) {
                diff = x[i] - y[j];
                result[index++] = diff;
            }
            else {
                int k = 0;
                diff = (givenBase + x[i]) - y[j];
                if ((i + 1 < x.length) && (x[i + 1] != 0)) {
                    x[i + 1] -= 1;
                } else {

                    k = i + 1;
                    while (k < x.length && x[k] == 0) {
                        x[k] = givenBase - 1;
                        k++;
                    }
                    x[k] -= 1;
                }
                result[index++] = diff;

            }


            i++;
            j++;
        }
        while (i < x.length) {
            result[index] = x[i];
            index++;
            i++;
        }
        return result;
    }

    /**
     * Utility function which performs the comparison operation without considering their signs
     * @param other which needs to be compared
     * @return 0 if both are equal, 1 if this is greater, else -1
     */
    public int compareMagnitude(Num other) {
        int flag = 0;
        if (this.len > other.len) {
            return 1;
        } else if (this.len < other.len) {
            return -1;
        } else if (this.len == other.len) {
            int i = this.len - 1, j = other.len - 1;
            while (i >= 0 && j >= 0) {

                if (this.arr[i] > other.arr[j]) {
                    flag = 1;
                    break;
                } else if (this.arr[i] < other.arr[j]) {
                    flag = -1;
                    break;
                } else if (this.arr[i] == other.arr[j]) {
                    flag = 0;
                }
                i--;
                j--;
            }
        }
        return flag;
    }

    /**
     * Converts the long array into its numberic value by using the base
     * @param a Num whose long value is needed
     * @return the long value by using the array position values
     */
    public static long convertToLong(Num a) {
        long result = 0;
        for (int i = 0; i < a.arr.length; i++) {
            result += a.arr[i] * Math.pow(a.base, i);
        }

        if (a.isNegative)
            return -result;
        else
            return result;
    }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
        int i = this.len - 1;
        Num b = new Num(base, newBase);
        Num temp = new Num(this.arr[i], newBase);
        while (i > 0) {
            temp = add(product(temp, b), new Num(this.arr[i - 1], newBase));
            i--;
        }
        long[] resutArray = removeTrailingZeros(temp.arr);
        temp.arr = resutArray;
        temp.len = resutArray.length;
        return temp;
    }

    // Return number to a string in base 10
    //TODO Replace 2 by base
    public String toString() {
        StringBuilder resultBuiler = new StringBuilder();
        if (this.compareMagnitude(new Num(0)) == 0) {
            return "0";
        }
        if (this.isNegative) {
            resultBuiler.append("-");
        }

        Num z = new Num();
        long array[] = new long[this.arr.length];
        System.arraycopy(this.arr, 0, array, 0, this.arr.length);
        z.arr = array;
        z.base = this.base;
        z.len = this.arr.length;

        z = z.convertBase((int)defaultBase);
        for (int i = z.arr.length - 1; i >= 0; i--) {

            int lengthOfDigits = String.valueOf(z.arr[i]).length();
            int lengthOfBase = String.valueOf(defaultBase-1).length();

            if(lengthOfDigits < lengthOfBase && i != z.arr.length - 1)
            {
                for (int k = 0; k < lengthOfBase - lengthOfDigits; k++)
                {
                    resultBuiler.append("0");
                }
            }
            resultBuiler.append(z.arr[i]);
        }

        return resultBuiler.toString();

    }
}
