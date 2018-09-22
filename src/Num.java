import java.util.Stack;

//Shreyash has made a test commit and push to his branch!
public class Num implements Comparable<Num> {

    static long defaultBase = 10;  // Change as needed
    static long base = defaultBase;  // Change as needed
    long[] arr;  // array to store arbitrarily large integers
    boolean isNegative;  // boolean flag to represent negative numbers
    int len;  // actual number of elements of array that are used;  number is stored in arr[0..len-1]


    //BigInteger big = new BigInteger();
    public Num() {
        this.arr = null;
        this.len = 0;
        this.isNegative = false;
    }

    //TODO Remove list usage
    public Num(long x) {
/*
        List<Long> a = new ArrayList<>();
        long n = x;
        if (n < 0) {
            this.isNegative = true;
            n = Math.abs(n);
        }
        while (n > 0) {
            a.add(n % base);
            n = n / base;
        }
        if (x == 0) {
            a.add(0L);
        }

        arr = new long[a.size()];
        int index = 0;

        if (x == 0) {
            arr[index] = 0;
        } else {
            for (long i : a) {
                arr[index] = i;
                index++;
            }
        }
        this.len = arr.length;
*/
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
    public Num(String s) {
        this.len = s.length();
        arr = new long[len];
        for(int i = len - 1 ; i >= 0; i--)
        {
            arr[len - i - 1] = (long) (s.charAt(i) - '0');
        }

    }
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
            b.isNegative = false;
            answer = add(a, b);
        }
        answer.len = result.length;
        return answer;
    }

    public static Num product(Num a, Num b) {
//        System.out.println(a+ " " + b);
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
                //temp += carry;
                result[index] = carry;
                carry=0;
            }
        }

        if (carry > 0) {
            //temp += carry;
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
        boolean flag = false;
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
            return product(a, new Num(n));
    }

    // return a%b
    public static Num mod(Num a, Num b) {

        Num quotient = divide(a,b);
        Num answer = product(quotient,b);
        Num remainder = subtract(a,answer);

        return remainder;
    }

    public static long convertToLong(Num a)
    {

       return 0;
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


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
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
        if (isNegative)
            System.out.print("-");
        for (int i = this.len - 1; i >= 0; i--)
            System.out.print(arr[i]);
        //System.out.println(Arrays.toString(arr));
    }

    // Return number to a string in base 10
    //TODO Replace 2 by base
    public String toString() {
        String res = "";
        for (int i=this.arr.length-1;i>=0;i--) res += this.arr[i];
        return res;
//        long n = 0;
//        for (int i = 0; i < arr.length; i++) {
//            n += Math.pow(2, i) * arr[i];
//        }
//        return String.valueOf(n);
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


    //UTILITY FUNCTIONS
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


    public static void main(String[] args) {
        Num x = new Num(9);
        //x.printList();
        Num y = new Num(-4);
        //y.printList();

        //System.out.println(y.compareTo(x));
        // Num z = Num.subtract(x,y);
        // z.printList();

        //Num c = Num.by2(x);
        //c.printList();

        //Num d = Num.product(x,y);
        //d.printList();

        Num p = divide(x, y);
        x.printList();
        System.out.println();
        p.printList();
        // Num kk = getAbsNum(x);
        //kk.printList();

        //Num s = Num.squareRoot(x);
        //s.printList();
        //System.out.println(x.toString());

        //String arr[] = {"2","3","1", "*", "/"};
        //System.out.println(evaluatePostfix(arr));


        /*Num w = Num.by2(x);
        w.printList();
        *///System.out.println(x.compareTo(y));
        /*
        Num x = new Num(999);
        Num y = new Num("8");
        Num z = Num.add(x, y);
        System.out.println(z);
        Num a = Num.power(x, 8);
        System.out.println(a);
        if(z != null) z.printList();*/
    }

}