import java.util.Stack;

public class Num implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    static long base = defaultBase;  // Change as needed
    long[] arr;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in arr[0..len-1]

    public Num() {
        this.arr = null;
        this.len = 0;
        this.isNegative = false;
    }

    public Num(String s) {
        this(s,defaultBase);

    }

    public Num(String s, long newBase) {
        this.arr= new long[s.length()];
        int i = 0;
        for(int k =s.length()-1; k>=0; k--){
            this.arr[i] = (long) s.charAt(k) - '0';
            i++;
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
        this.arr = new long[100];
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
            answer.arr = subhelper(x, y, result);
        }
        if(a.isNegative && b.isNegative) {
            answer.arr = addhelper(a, b, result);
            answer.isNegative = true;
        }
        else if(!a.isNegative && !b.isNegative)
            answer.arr = addhelper(a, b, result);
        answer.len = result.length;
        return answer;
    }

    public static Num subtract(Num a, Num b)
    {
        Num answer = new Num();
        long[] result = new long[Math.max(a.len,b.len)+1];
        Num x = new Num();
        Num y = new Num();

        if(b.isNegative && !a.isNegative)
        {
            answer.arr = addhelper(a, b, result);
        }
        else if(a.isNegative && !b.isNegative)
        {
            answer.arr = addhelper(a, b, result);
            answer.isNegative = true;
        }
        else if(!a.isNegative && !b.isNegative)
        {
            if(a.len > b.len){
                x = a;
                y = b;
            }
            else if(a.len < b.len){
                x = b;
                y = a;
                answer.isNegative = true;
            }else if(a.len == b.len){
                if (a.compareMagnitude(b) == 1 || a.compareMagnitude(b) == 0){
                    x = a;
                    y = b;
                }else if(a.compareMagnitude(b) == -1){
                    x = b;
                    y = a;
                    answer.isNegative = true;
                }
            }
            answer.arr = subhelper(x, y, result);
        }else if(a.isNegative && b.isNegative){
            answer.arr = addhelper(a, b, result);
        }
        answer.len = result.length;
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
                result[index] = x % base;
                carry = x / base;
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
        //return null;
        if (n == 0) {
            return new Num(1);
        }
        Num p = power(product(a, a), n / 2);
        if (n % 2 == 0)
            return p;
        else
            return product(p, a);
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
            throw new IllegalArgumentException("Denominator cannot be 0");
        } else if (a.compareMagnitude(zero) == 0) {
            return zero; // If the numerator is 0, the answer will always be 0
        }
        //Handling egde case scenario where if the denominator is 1,
        // there is no need for any computation and the answer will always be 1
        else if (divisor.compareMagnitude(one) == 0) {
            return one;
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
        Num absNum = new Num(0);
        System.arraycopy(givenNum.arr, 0, absNum.arr, 0, givenNum.len);
        absNum.len = givenNum.len;
        return absNum;
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
        System.out.print("Base:" + this.base());
        System.out.print("       ");
        for (int i = 0; i < this.len - 1; i++)
            System.out.print(arr[i]);
        //System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Num x = new Num(26);
        Num y = new Num(5);

        System.out.println();
        Num a = add(x, y);
        System.out.println("1. Addition: " + a.toString());//a.printList();
        System.out.println();

        Num b = subtract(x, y);
        System.out.println("2. Subtraction: " + b.toString());//b.printList();
        System.out.println();

        Num c = product(x, y);
        System.out.println("3. Product: " + c.toString());//c.printList();
        System.out.println();

        Num d = divide(x, y);
        System.out.println("4. Division: " + d.toString());//d.printList();
        System.out.println();

        Num e = mod(x, y);
        System.out.println("5. Modulo: " + e.toString());//f.printList();
        System.out.println();

        int n = 5;
        Num f = power(x, n);
        System.out.println("6. Power: " + f.toString());//g.printList();
        System.out.println();

        int g = x.compareTo(y);
        System.out.println("7. Compare To: " + g);//h.printList();
        System.out.println();

        Num h = squareRoot(x);
        System.out.println("8. Square Root: " + h.toString());//e.printList();
        System.out.println();

        Num i = by2(x);
        System.out.println("9. By 2: " + i.toString());//h.printList();
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


        int ba = 5;
        Num k = x.convertBase(ba);
        System.out.println("18. ConvertBase:" + k.toString());


    }

    // Divide by 2, for using in binary search
    public static Num by2(Num a) {
        long carry = 0;
        int index = a.len - 1;
        int j = a.len - 1;
        long n = 0;
        long[] result = new long[a.len];
        while (j >= 0) {
            n = a.arr[j] + (carry * base);
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
            if(c.equals("*") || c.equals("+") || c.equals("-") || c.equals("/") || c.equals("%") || c.equals("^"))
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
                        //operandStack.push(power(val2,val1));
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

    // Evaluate an expression in infix and return resulting number
    // Each string is one of: "*", "+", "-", "/", "%", "^", "(", ")", "0", or
    // a number: [1-9][0-9]*.  There is no unary minus operator.
    public static Num evaluateInfix(String[] expr) {
        return null;
    }


    public long base() {
        return base;
    }

    // Return number equal to "this" number, in base=newBase
    public Num convertBase(int newBase) {
            int i = this.len - 1;
            Num temp =  new Num(this.arr[i],newBase);
            while (i >0){
                Num b=new Num(this.base,newBase);

                temp = add(product(temp,b),new Num(this.arr[i-1],newBase));
                /*  temp.printList();*/
                i--;
            }
            return temp;
    }


    //Helper Functions:
    public static long[] addhelper(Num x, Num y, long result[]){
        int i = 0,j = 0;
        long sum = 0;
        long carry = 0;
        int index = 0;
        while(i < x.len && j < y.len)
        {
            sum = x.arr[i] + y.arr[j] + carry;
            result[index] = sum % base;
            index++;
            carry = sum / base;
            i++;
            j++;
        }

        while(i < x.len)
        {
            sum = x.arr[i] + carry;
            result[index] = sum % base;
            index++;
            carry = sum / base;
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

    public static long[] subhelper(Num x, Num y, long result[]){
        int i = 0,j = 0;
        int index = 0;
        long diff;
        while (i < x.len && j < y.len)
        {
            if (x.arr[i] >= y.arr[j])
            {
                diff = x.arr[i] - y.arr[j];
                result[index] = diff;
            }
            else
            {   int k = 0;
                diff = (base + x.arr[i]) - y.arr[j];
                if(x.arr[i + 1] != 0) {
                    x.arr[i + 1] -= 1;
                }
                else
                {
                    k = i + 1;
                    while(x.arr[k] == 0) {
                        x.arr[k] = base - 1;
                        k++;
                    } x.arr[k] -= 1;
                }
                result[index] = diff;
            }
            index++;
            i++;
            j++;
        }
        while(i < x.len)
        {
            result[index] = x.arr[i];
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


    //Extra Functions:
    public static long convertToLong(Num a)
    {
        return 0;
    }
}
