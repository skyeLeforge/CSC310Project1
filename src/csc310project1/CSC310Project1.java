package csc310project1;

//imports for queue, stack, linked list, and user input
//we need linked list to use queues in java
import java.util.Scanner;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;



public class CSC310Project1 {


    public static int[] RadixSort(int toSort[]){
        // create an array of queues, each one for a different digit (0-9)
        Queue[] counts = new Queue[10];
        //initalize each queue
        for (int i = 0; i < 10; i++) {
            counts[i]= new LinkedList();//is a queue but have to use linked list
        }
        
        //now start the sort
        for (int i = 2; i > 0; i--) {
        /*loop two times bwcause the instructions required
          us to do it for 2 digit numbers and use 10's for %*/
            for (int j = 0; j < toSort.length; j++) {
                /*this loop takes each value in toSort and places them in queues
                  based off the value of the place we are analyzing(tens, ones)*/
                
                //using string shenangins to get the value of the place we are analyzing 
                String temp = Integer.toString(toSort[j]);
                int sortable = Character.getNumericValue(temp.charAt(i-1));
                
                //add it to the correct queue, add in java is enqueue in python
                counts[sortable].add(toSort[j]);
            }
            
            //now add them all back to toSort
            //create an increment so we know where to add it
            int inc=0;
            
            for (int j = 0; j < 10; j++) {
                while(counts[j].size()>0){
                    toSort[inc] = (int) counts[j].remove();
                    inc++;
                }     
            }
        }      
        return toSort;
    }
    
    public static String solvePostfix(String postFix){
        //create stack
        Stack store = new Stack();
        
        //for each element in the post fix expression, do this

        for (int i = 0; i < postFix.length(); i++) {
            //get the currenet element of the expression
            char c = postFix.charAt(i);
            
            //see if it's a number or not
            if(Character.isDigit(c)){
                //if it is a number add it too the stack
                store.push(Character.toString(c));
            }else{
                // if it isn't do math with the operator and the stack elements
                // first get the elements 
                String num1string=(String) store.pop();
                String num2string=(String) store.pop();
                double num1 =  Double.parseDouble(num1string);
                double num2 = Double.parseDouble(num2string);
                double toPush = 0; // number to get put back on stack
                //then figure out the operator then do the math
                
                switch(c){
                    case '+':
                        toPush = num2+num1;
                        break;
                    case '*':
                        toPush = num2*num1;
                        break;
                    case '-':
                        toPush = num2-num1;
                        break;
                    case '/':
                        toPush = num2/num1;
                        break;
                    default:
                        System.out.println("Error! Invalid input!");
                    
                }
                
                store.push(Double.toString(toPush));
            }
                
                
            
        }
        
        return (String)store.pop();
    }
    
    public static void main(String[] args) {
        //allows the user input to be used
        Scanner in = new Scanner(System.in);
        /*
        //question 1
        //ask number of ints loooking to be sorted
        System.out.println("How many ints are you looking to sort?");
        int length = in.nextInt();
        
        //create array to store inputs and ask for inputs at the same time
        int toSort[] = new int[length];
        for(int i=1; i<=length; i++){
            System.out.println("Please enter int number "+i+":" );
            toSort[i-1]=in.nextInt();          
        }
        int sorted[] = new int[length];
        sorted=RadixSort(toSort);
        
        System.out.println("Heres the sorted list: ");
        for (int i = 0; i < length; i++) {
            System.out.print(sorted[i]+" ");
        }
        */
        //question 2
        System.out.println("Please enter your postfix notation equation: ");
        String postFix = in.nextLine();
        String ans = solvePostfix(postFix);
        System.out.println("The answer is "+ans);
        
    }
    
}
