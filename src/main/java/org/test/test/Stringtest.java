package org.test.test;

public class Stringtest {
    public static void main(String[] args) {
//        String str = "$bjh7$55)()1";
//        int count = str.replaceAll("[^0-9]","").length();
//        System.out.println(count);

//        int count = 0;
//
//        for (int i = 0; i < str.length(); i++) {
//            if(Character.isDigit(str.charAt(i))){
//                count++;
//            }
//        }

//        System.out.println(count);

        /*
        Sum of all digits in a number
         */

//        int num = 4789;
//
//        int digit, sum = 0;
//
//        while (num > 0){
//            digit = num % 10;
//            sum += digit;
//            num /= 10;
//        }
//
//        System.out.println("Total sum: "+sum);

        /*
        Find out the MAX or MIN digit in the number
         */

        int num = 897456;
        int maxval = Integer.MIN_VALUE;
        int digit = 0;

        while (num > 0){
            digit = num % 10;

            if(digit % 2 == 0){

                if (digit > maxval){
                    maxval = digit;
                }
            }

            num /= 10;
        }

        System.out.println("The highest value is: "+ maxval);


    }
}
