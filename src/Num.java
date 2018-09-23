import java.util.Stack;

public class Num implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
     long base = defaultBase;  // Change as needed
    long[] arr;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in arr[0..len-1]

    public Num() {
        this.arr = null;
        this.len = 0;
        this.isNegative = false;
    }

    public Num(long[] x) {
        this.arr = x;
        this.len = x.length;
    }

    public Num(String s) {
        this(s,defaultBase);

    }

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
        this.base=10;
        this.len=this.arr.length;
        Num x= this.convertBase((int)newBase);
        this.arr=x.arr;
        this.len=x.len;
        this.base=newBase;
    }

    //TODO Remove list usage
    public Num(long x) {
        this(x,defaultBase);
    }


    public Num (long number, long newBase) {
        this.base = newBase;
        if (number < 0) {
            this.isNegative = true;
            number = Math.abs(number);
        }

        this.arr = new long[Long.toString(number).length()];
        if (number == 0) {
            this.len = 1;
            return;
        }
        int index = 0;
        while (number > 0) {
            this.arr[index++] = number % this.base;
            this.len++;
            number /= this.base;
        }
    }

    //TODO

    public static Num add(Num a, Num b) {
        Num answer = new Num();
        Num x = new Num();
        Num y = new Num();
        long[] result = new long[Math.max(a.len,b.len)+1];
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
                    if(a.isNegative) answer.isNegative = true;
                    else if(b.isNegative) answer.isNegative = false;
                } else if (a.compareMagnitude(b) == -1) {
                    x = b;
                    y = a;
                    if(a.isNegative) answer.isNegative = false;
                    else if(b.isNegative) answer.isNegative = true;
                }
            }
            answer.arr = answer.subhelper(x.arr, y.arr, result);
        }
        if(a.isNegative && b.isNegative) {
            answer.arr = answer.addhelper(a, b, result);
            answer.isNegative = true;
        }
        else if(!a.isNegative && !b.isNegative)
            answer.arr = answer.addhelper(a, b, result);
        //Num answer = new Num();
        answer.arr = removeTrailingZeros(result);
        answer.len = answer.arr.length;
        //answer.len = result.length;
        return answer;
    }

    public static Num subtract(Num a, Num b)
    {
        Num answer = new Num();
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
            result = answer.addhelper(a, b, result);
        }
        else if(a.isNegative && !b.isNegative)
        {
            result = answer.addhelper(a, b, result);
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
            result = answer.subhelper(p1, p2, result);
        }else if(a.isNegative && b.isNegative){
            result = answer.subhelper(a.arr, b.arr, result);
            if(a.compareMagnitude(b)==1){
                lessThanZero = true;
            }
        }
        /*Num tempResult = new Num(result);
        tempResult.isNegative = lessThanZero;

        tempResult.arr = removeTrailingZeros(result);
        tempResult.len = tempResult.arr.length;
        return tempResult;*/

        answer.isNegative = lessThanZero;
        answer.arr = removeTrailingZeros(result);
        answer.len = answer.arr.length;
        return answer;
    }

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

    // Use divide and conquer
    public static Num power(Num a, long n) {

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
        if (n % 2 == 0)
            return p;
        else {
            p = product(p, a);
            if(a.isNegative)
                p.isNegative = true;
            return p;
        }
    }

    // Use binary search to calculate a/b
    public static Num divide(Num a, Num b) {
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
        return mid;
    }

    public static Num getAbsNum(Num givenNum) {
        long[] temp= givenNum.arr;
        return new Num(temp);
    }

    // return a%b
    public static Num mod(Num a, Num b) {

        Num quotient = divide(a,b);
        Num answer = product(quotient,b);
        Num remainder = subtract(a,answer);

        return remainder;
    }

    // Use binary search
    public static Num squareRoot(Num a) {
        Num zero = new Num(0);
        Num one = new Num(1);
        if (zero.compareTo(a) == 0) {
            return zero;
        } else if (one.compareTo(a) == 0) {
            return one;
        } else {
            Num left = new Num(1);
            Num right = a;
            while (left.compareTo(right) == -1) {
                Num mid = by2(add(left, right));
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


    //UTILITY FUNCTIONS
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
                // If the scanned character is an operator, pop two
                // elements from operatorStack apply the operator
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
            // If the scanned character is an operand
            // (number here),extract the number
            // Push it to the operatorStack.
            else
            {
                Num n = new Num(c);
                //push the number in stack
                operandStack.push(n);
            }
        }
        return operandStack.pop();
    }
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



    public long base() {
        return base;
    }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
        int i = this.len - 1;
        Num b=new Num(this.base,newBase);
        Num temp =  new Num(this.arr[i],newBase);
        while (i >0){
            temp = add(product(temp,b),new Num(this.arr[i-1],newBase));
            i--;
        }
        long[] resutArray = removeTrailingZeros(temp.arr);
        temp.arr = resutArray;
        temp.len = resutArray.length;
        return temp;
    }


    //Helper Functions:
    public  long[] addhelper(Num x, Num y, long result[]){
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
            result[index] = sum % base;
            index++;
            carry = sum / base;
            j++;
        }

        if(carry > 0)
        {
            result[index] = carry;
            index++;
        }
        return result;
    }

    public  long[] subhelper(long[] x, long[] y, long result[]){
        int i = 0,j = 0;
        int index = 0;
        long diff;
        while (i < x.length && j < y.length)
        {
            if (x[i] >= y[j])
            {
                diff = x[i] - y[j];
                result[index] = diff;
            }
            else
            {   int k = 0;
                diff = (base + x[i]) - y[j];
                if((i+1 < x.length)  && (x[i + 1] != 0)) {
                    x[i+1] -= 1;
                }
                else
                {

                    k = i + 1;
                    while( k < x.length && x[k] == 0) {
                        x[k] = base - 1;
                        k++;
                    }
                    x[k] -= 1;
                }
            }
            result[index] = diff;

            index++;
            i++;
            j++;
        }
        while(i < x.length)
        {
            result[index] = x[i];
            index++;
            i++;
        }
        return result;
    }

    public int compareMagnitude(Num other) {
        int flag = 0;
        if (this.len > other.len) {
            return 1;
        } else if (this.len < other.len) {
            return -1;
        } else if (this.len == other.len) {
            int i = this.len - 1, j = other.len - 1;
            while (i>=0 && j >=0) {

                if (this.arr[i] > other.arr[j]) {
                    flag = 1;
                    break;
                } else if (this.arr[i] < other.arr[j]) {
                    flag = -1;
                    break;
                } else if(this.arr[i] == other.arr[j]){
                    flag = 0;
                }
                i--;
                j--;
            }
        }
        return flag;
    }

    // Return number to a string in base 10
    //TODO Replace 2 by base
    public String toString() {
        StringBuilder resultBuiler = new StringBuilder();
        if(this.compareMagnitude(new Num(0)) == 0){
            return "0";
        }
        if (this.isNegative) {
            resultBuiler.append("-");
        }

        Num z = this;
        z = z.convertBase(10);
        for (int i = z.arr.length - 1; i >= 0; i--) {
            resultBuiler.append(z.arr[i]);
        }
        return resultBuiler.toString();

    }

    public static void main(String[] args) {
        Num x = new Num(-74);
        Num y = new Num(-26);

        Num a = add(x, y);
        System.out.println("1. Addition: " + a.toString());a.printList();
        System.out.println();

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

        int n = 5;
        Num f = power(x, n);
        System.out.println("6. Power: " + f.toString());f.printList();
        System.out.println();

        int g = x.compareTo(y);
        System.out.println("7. Compare To: " + g);
        System.out.println();

        Num h = squareRoot(x);
        System.out.println("8. Square Root: " + h.toString());h.printList();
        System.out.println();

        Num i = by2(x);
        System.out.println("9. By 2: " + i.toString());i.printList();
        System.out.println();

        System.out.println("10. INfix");

        System.out.println("11. POSTfix");
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

        System.out.println("17. Trailing Zeros" + removeTrailingZeros(x.arr));
        System.out.println();


    }

    //Extra Functions:
    public static long convertToLong(Num a)
    {
        long result = 0 ;
        for (int i = 0; i < a.arr.length; i++)
        {
            result += a.arr[i] * Math.pow(a.base, i);
        }

        if(a.isNegative)
            return -result;
        else
            return result;
    }
}
