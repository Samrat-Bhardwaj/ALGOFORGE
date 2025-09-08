import java.util.Scanner;
class Main {
    public static int factorial(int n){
        int res = 1;

        for(int mul=1; mul<=n; mul++){
            res = res*mul;
        }

        return res;
    }

    public static int findNCR(int n, int r){
        int nFact = factorial(n);
        int rFact = factorial(r);
        int nMrFact = factorial(n-r);

        int ncr = nFact/(rFact * nMrFact);

        return ncr;
    }

    public static int binaryToDecimal(int num){
        int currBase = 1;
        int decimalNum = 0;

        while(num > 0){
            int lastDigit = num%10;

            decimalNum += lastDigit*currBase;
            currBase *= 2;
            num /= 10;
        }

        return decimalNum;
    }

    public static int elementsGreaterThanX(int[] arr, int x){
        // write your code here
        int n = arr.length;
        int count = 0;

        for(int i=0; i<n; i++){
            int ele = arr[i];
            if(ele > x){
                count++;
            }
        }

        return count;
    }

    public static int spanOfArray(int[] arr){
        int n = arr.length;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            int ele = arr[i];

            max = Math.max(max, ele);
            // if(ele > max){
            //     max = ele;
            // }
            min = Math.min(min, ele);
            // if(ele < min){
            //     min = ele;
            // }
        }

        int span = max - min;
        return span;
    }

    public static int findTarget(int[] arr, int tar){
        int n = arr.length;

        for(int i=0; i<n; i++){
            int ele = arr[i];

            if(ele == tar){
                return i;
            }
        }

        return -1;
    }

    public static void printBuildings(int[] heights){
        int n = heights.length;

        int maxHeight = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            int currBuildingHeight = heights[i];

            maxHeight = Math.max(maxHeight, currBuildingHeight);
        }

        int total_number_of_lines = maxHeight;
        int currFloor = maxHeight;

        while(currFloor > 0){
            // go to every building and check if currFloor for that building exists or not
            for(int i=0; i<n; i++){
                int currBuildingHeight = heights[i];

                if(currFloor <= currBuildingHeight){ // currFloor exists
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            // prepare for next line
            System.out.println();
            currFloor--;
        }
    }

    public static int[] sumOfTwoArrays(int[] arr1, int[] arr2){
        int n1 = arr1.length;
        int n2 = arr2.length;

        int resSize = Math.max(n1, n2) + 1;

        int[] res = new int[resSize];

        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = res.length - 1;

        int carry = 0;

        while(k >= 0){
            int csum = 0;

            if(i >= 0){
                csum += arr1[i];
            }
            if(j >= 0){
                csum += arr2[j];
            }
            csum += carry;

            if(csum > 9){
                csum = csum % 10;
                carry = 1;
            } else {
                carry = 0;
            }

            res[k] = csum;

            i--;
            j--;
            k--;
        }

        return res;
    }






























    public static int[] takeArrayInput(){
        Scanner scn = new Scanner(System.in);

        System.out.println("What should be the size of array?");

        int size = scn.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter values for your array");

        for(int idx=0; idx < size; idx++){
            int input = scn.nextInt();
            arr[idx] = input;
        }

        return arr;
    }

    public static void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        // int n = scn.nextInt();
        // int r = scn.nextInt();

        // int ncr = findNCR(n,r);
        // System.out.println(ncr);

        // int num = scn.nextInt();
        // System.out.println(binaryToDecimal(num));

        int[] arr1 = takeArrayInput();
        int[] arr2 = takeArrayInput();

        int[] res = sumOfTwoArrays(arr1, arr2);
        printArray(res);

        // int x = scn.nextInt();
        // printBuildings(arr);
    }
}