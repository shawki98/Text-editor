package assign_2;

import BasicIO.*;
//* IV text editor version 1.0
// by Shawki Sha'aban, ID: 7356108
// Date: 02.18.2022 COSC 1P03 Assignment 2*//

public class IV {
    Node head[] = new Node[25];// node array
    BasicForm form;// form for user interaction
    int s; //instance variable for start
    int e; //instance variable for end
    int l; //instance variable for line
    int action; //instance variable for action
    String text; //instance variable for the strings

    //*  the main class Constructor for displaying the form and waiting for user input
    // while loop is used to run the code during the form is displayed for user to input multiple edits.
    // if and else statements are used to access the methods for each button option*//

    public IV() {
        int button = 0;
        BuildForm();

        while(true) {
            button = form.accept();
            text = form.readString("T");
            l = form.readInt("L");
            action = form.readInt("action");
            s = form.readInt("S");
            e = form.readInt("E");

            if( s ==0&&!form.readLine("S").equals("0")){
                s=-1;
            }
            if( e ==0&&!form.readLine("E").equals("0")){
                e=-1;
            }
            if (button == 0) {

                if (action == 0) {
                    form.clear("O");
                    insertLine();

                } else if (action == 1){
                      delLetter();
                }
            else if (action == 2) {
                     swapLetter();
                }
            }
            else {
                form.close();
                break;
            }
            displayAll();
        }

    }//Constructor


    //* This method is the interface for the replacement function,
    // which consist of only two if statements,
    //That uses the node method for replacing a character in the node class
    private void swapLetter() {
        if(head[l]!= null){
            if(s>=0){
                head[l]=head[l].delete(s,e).insert(StringNode(text),s);
            }
        }
    }//swapLetter


    //* This method is the interface for the deletion function, which calls
    // 2 other node methods that will be called/used into this method.
    // This method consists of nested if statements with a pair of else statements,
    // for the multiple deletion commands*/
    private void delLetter() {
        if (head[l]!= null){
            if(s>=0){
                if(s<head[l].length()){
                    if(e>s){
                        head[l]=head[l].delete(s,e);
                    }
                    else{
                        head[l]=head[l].delete(s);
                    }
                }
            }
            else{
                for (int i = l; i < 24 ; i++) {
                    head[i]=head[i+1];
                }
                head[24]=null;
            }
        }
    }//delLetter


//* This Method is the interface for the insertion function for the characters in the output
// This method is using another node method for inserting from the back in the node class,
// The method consists nested if statement for the commands and a for loop to traverse through the 25 lines.*/

    private void insertLine(){
        if (s>-1){
            if(head[l] != null){
                head[l]=head[l].insert(StringNode(text),s);
            }
        }
        else {
            for (int i = 24; i >l; i--) {
                head[i]=head[i-1];
            }
            head[l]=StringNode(text);
        }
    }//insertLine

//* This method takes the String(S) and converts it into a linked  list Node and then returns it
    private Node StringNode(String S){
        Node n = null;
        Node p ;
        for (int i = text.length()-1; i>-1 ; i--) {
            p = n;
            n= new Node (S.charAt(i),p);
        }
     return n;
    }//StringNode



//* This method is used to read the string inputs in name text field named "O" from the buildForm method
// Then displays all the input commands to the form displayed, it consists of an if statement within a for loop,
// looping throughout the node array*/

    private void displayAll(){

        for(int i = 0; i< head.length;i++){
            form.writeString("O",i + ":");

            if(head[i] != null){
                form.writeString("O", head[i].toString());
            }
            form.newLine("O");
        }
    } //DisplayAll

// the Text field form that will be displayed to the user and is what the user interacts with the Text fields,
// labels and names for each entry that the user inputs, along with the output field/*
    private void BuildForm(){

        String[] RadioLabel = {"Insert","Delete","Replace"};
        form = new BasicForm("Apply Edit","Exit");
        form.addTextField("L","Line",3,15,10);
        form.addTextField("S","Start",3,85,10);
        form.addTextField("E","End",3,160,10);
        form.addTextField("T","Text",30);
        form.addRadioButtons("action","Edit Action",false,RadioLabel);
        form.addTextArea("O","OutPut",26,40);
    }//buildForm

    public static void main(String[] args) { IV r = new IV();}
    }//main method




