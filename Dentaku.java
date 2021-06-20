import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
    double num1 = 0, num2=0, result = 0;
    char operator;
    final int BTNWIDTH = 60;
    final int BTNHEIGHT = 50;
    final int BTNGAP = 8;

    void setUpFrame(){
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(3*BTNGAP+4*BTNWIDTH+24,4*BTNGAP+6*BTNHEIGHT+50+4);
        frame.setLayout(null);
        textfield = new JTextField();
        textfield.setBounds(4, 4, 3*BTNGAP+4*BTNWIDTH, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
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
                textfield.setText(textfield.getText().concat(String.valueOf(number)));
            });
            btn.setFont(myFont);
            btn.setFocusable(false);
            numberBtns[i] = btn;
        }
    }

    void assignFunctionBtnListeners(){
        addition.addActionListener((e)->{
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        });
        subtraction.addActionListener((e)->{
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        });
        mult.addActionListener((e)->{
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        });
        divide.addActionListener((e)->{
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        });
        equal.addActionListener((e)->{
            num2 = Double.parseDouble(textfield.getText());
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
            }
            textfield.setText(String.valueOf(result));
            num1=result;
        });
        clear.addActionListener((e)->{
            textfield.setText("");
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
