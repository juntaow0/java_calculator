import javax.swing.*;
import java.awt.*;
import java.util.*;

class Calculator{
    JFrame frame;
    JTextField textfield;
    JButton[] numberBtns = new JButton[10];
    JButton[] functionBtns = new JButton[10];
    JButton addition, subtraction, mult, divide, equal;
    JButton clear, clearEntry, backspace;
    JButton sign, decimal;
    JPanel panel;
    Font myFont = new Font("Monospaced", Font.BOLD,22);
    Stack<Double> numStack;
    double num1 = 0, num2=0, result = 0;
    char operator;
    boolean assignNumber = true;
    boolean numMissing = true;
    final int BTNWIDTH = 60;
    final int BTNHEIGHT = 50;
    final int BTNGAP = 8;

    void setUpFrame(){
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(3*BTNGAP+4*BTNWIDTH+24,4*BTNGAP+6*BTNHEIGHT+50+4);
        frame.setLayout(null);
        frame.setResizable(false);
        textfield = new JTextField();
        textfield.setBounds(4, 4, 3*BTNGAP+4*BTNWIDTH, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setHorizontalAlignment(SwingConstants.RIGHT);
        textfield.setText(String.valueOf(num1));
        frame.add(panel);
        frame.add(textfield);
        frame.setVisible(true);
    }

    void setUpPanel(){
        panel = new JPanel();
        panel.setBounds(4, 62, 3*BTNGAP+4*BTNWIDTH, 4*BTNGAP+5*BTNHEIGHT);
        panel.setLayout(new GridLayout(5,4,8,8));

        panel.add(clearEntry);
        panel.add(clear);
        panel.add(backspace);
        panel.add(equal);

        panel.add(numberBtns[7]);
        panel.add(numberBtns[8]);
        panel.add(numberBtns[9]);
        panel.add(divide);

        panel.add(numberBtns[4]);
        panel.add(numberBtns[5]);
        panel.add(numberBtns[6]);
        panel.add(mult);

        panel.add(numberBtns[1]);
        panel.add(numberBtns[2]);
        panel.add(numberBtns[3]);
        panel.add(subtraction);

        panel.add(sign);
        panel.add(numberBtns[0]);
        panel.add(decimal);
        panel.add(addition);
    }

    void createButtons(){
        addition = new JButton("+");
        subtraction = new JButton("-");
        mult = new JButton("*");
        divide = new JButton("/");
        equal = new JButton("=");
        clear = new JButton("C");
        backspace = new JButton("<-");
        sign = new JButton("+/-");
        decimal = new JButton(".");
        clearEntry = new JButton("CE");

        functionBtns[0] = addition;
        functionBtns[1] = subtraction;
        functionBtns[2] = mult; 
        functionBtns[3] = divide;
        functionBtns[4] = equal; 
        functionBtns[5] = clear;
        functionBtns[6] = backspace;
        functionBtns[7] = sign;
        functionBtns[8] = decimal;
        functionBtns[9] = clearEntry;

        for (var btn:functionBtns){
            btn.setFont(myFont);
            btn.setFocusable(false);
            btn.setMargin(new Insets(0,0,0,0));
        }
        assignFunctionBtnListeners();

        for (int i=0;i<10;i++){
            var btn = new JButton(String.valueOf(i));
            final int number = i;
            btn.addActionListener((e)->{
                if (assignNumber){
                    textfield.setText("");
                    assignNumber = false;
                    numMissing = false;
                }
                textfield.setText(textfield.getText().concat(String.valueOf(number)));
            });
            btn.setFont(myFont);
            btn.setFocusable(false);
            numberBtns[i] = btn;
        }
    }

    void assignFunctionBtnListeners(){
        addition.addActionListener((e)->{
            if (!numMissing){
                numStack.push(Double.parseDouble(textfield.getText()));
                assignNumber = true;
                numMissing = true;
            }
            operator = '+';
        });
        subtraction.addActionListener((e)->{
            if (!numMissing){
                numStack.push(Double.parseDouble(textfield.getText()));
                assignNumber = true;
                numMissing = true;
            }
            operator = '-';
        });
        mult.addActionListener((e)->{
            if (!numMissing){
                numStack.push(Double.parseDouble(textfield.getText()));
                assignNumber = true;
                numMissing = true;
            }
            operator = '*';

        });
        divide.addActionListener((e)->{
            if (!numMissing){
                numStack.push(Double.parseDouble(textfield.getText()));
                assignNumber = true;
                numMissing = true;
            }
            operator = '/';
        });
        equal.addActionListener((e)->{
            System.out.println("assgin: "+assignNumber+" numMissing: "+numMissing);
            if (numStack.empty()){
                if (assignNumber && numMissing){
                    System.out.println("case 1");
                }else if (!numMissing){
                    System.out.println("case 2");
                    num1 = Double.parseDouble(textfield.getText());
                }
            }
            else{
                num1 = numStack.pop();
                if (assignNumber && numMissing){
                    System.out.println("case 3");
                    num2 = num1;
                    numMissing =false;
                }else if (!assignNumber){
                    System.out.println("case 4");
                    num2 = Double.parseDouble(textfield.getText());
                }
            }

            System.out.println("num1: "+num1);
            System.out.println("num2: "+num2);
            
            switch(operator){
                case '+':
                    result = num1+num2;
                    break;
                case '-':
                    result = num1-num2;
                    break;
                case '*':
                    result = num1*num2;
                    break;
                case '/':
                    result = num1/num2;
                    break;
                default:
                    result = num2;
            }
            textfield.setText(String.valueOf(result));
            //numStack.push(result);
            assignNumber = true;
        });
        clear.addActionListener((e)->{
            textfield.setText("0");
            num1 = 0;
            num2 = 0;
            result = 0;
            assignNumber = true;
            numMissing = true;
            operator = ' ';
        });
        backspace.addActionListener((e)->{
            String temp = textfield.getText();
            temp = temp.substring(0, temp.length()-1);
            textfield.setText(temp);
        });
        sign.addActionListener((e)->{
            double temp = Double.parseDouble(textfield.getText());
            temp*=-1;
            textfield.setText(String.valueOf(temp));
        });
        decimal.addActionListener((e)->{
            textfield.setText(textfield.getText().concat("."));
        });
        clearEntry.addActionListener((e)->{
            textfield.setText("");
        });
    }

    Calculator(){
        numStack = new Stack<>();
        createButtons();
        setUpPanel();
        setUpFrame(); 
    }
}

public class Dentaku {
    public static void main(String[] args) {
        // Create the frame on the event dispatching thread
        System.out.println("Starting calculator...");
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Calculator();
            }
        });
    }
}
