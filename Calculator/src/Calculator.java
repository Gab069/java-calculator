import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Calculator implements ActionListener{
	
	JFrame frame;
	JTextField textfield;
	JButton[] numberButtons = new JButton[10];
	JButton[] functionButtons = new JButton[9];
	JButton addButton, subButton, mulButton, divButton;
	JButton decButton, equButton, delButton, clrButton, negButton;
	JPanel panel;
	
	Font myFont = new Font("Courier Bold", Font.BOLD, 30);
	
	double num1=0, num2=0, result=0;
	char operator;
	boolean isNegative = false, operatorPressed = false;
			
	Calculator (){
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setResizable(false);
		frame.setLayout(null);
		
		textfield = new JTextField();
		textfield.setBounds(50, 25, 300, 50);
		textfield.setFont(myFont);
		textfield.setEditable(false);
		textfield.setBackground(Color.WHITE);
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("*");
		divButton = new JButton("/");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("Del");
		clrButton = new JButton("C");
		negButton = new JButton("(-)");
		
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		
		for(int i = 0; i<9; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
			
		}
		
		for(int i = 0; i<10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
			
		}
		
		equButton.setBounds(260, 420, 90, 60);
		delButton.setBounds(155, 420, 90, 60);
		clrButton.setBounds(50, 420, 90, 60);
		
		panel = new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4, 4, 10, 10));
		
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(negButton);
		panel.add(numberButtons[0]);
		panel.add(decButton);
		panel.add(divButton);

		frame.add(panel);
		frame.add(clrButton);
		frame.add(delButton);
		frame.add(equButton);
		frame.add(textfield);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		Calculator calc = new Calculator();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0; i<10; i++) {
			if(e.getSource() == numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
				operatorPressed = false;
			}
		}
		
        if (!operatorPressed) {
            if (e.getSource() == addButton || e.getSource() == subButton || 
                e.getSource() == mulButton || e.getSource() == divButton) {
                
            	if (!textfield.getText().isEmpty()) {
                    num1 = Double.parseDouble(textfield.getText());
                }
                operator = e.getActionCommand().charAt(0);
                textfield.setText("");
                operatorPressed = true;
            }
        }
		
		if(e.getSource() == equButton) {
			if (!textfield.getText().isEmpty()) {
                num2 = Double.parseDouble(textfield.getText());
			}
			if ((operator == '/' && num2 == 0) || (operator == '/' && num1 == 0)) {
	            textfield.setText("Error:Division by zero");
	            return;
			}
			
			switch(operator) {
			case'+':
				result = num1 + num2;
				break;
			case'-':
				result = num1 - num2;
				break;
			case'*':
				result = num1 * num2;
				break;
			case'/':
				result = num1 / num2;
				break;
			}
			
			String resValue = String.valueOf(result);
			if(resValue.startsWith(".")) {
				resValue = "0" + resValue;
			}
			
			textfield.setText(resValue);
			num1 = result;
		}
		
		if(e.getSource() == clrButton) {
			textfield.setText("");
		}
		
		if(e.getSource() == delButton) {
			String str = textfield.getText();
			textfield.setText("");
			for(int i = 0; i <str.length() - 1; i++) {
				textfield.setText(textfield.getText() + str.charAt(i));
			}
		}
		
		if(e.getSource() == negButton) {
			isNegative = !isNegative;
	        if (textfield.getText().isEmpty()) {
	            return;	
	        }
	        double temp = Double.parseDouble(textfield.getText());
	        temp *= -1;
	        textfield.setText(String.valueOf(temp));
		}
		
		if(e.getSource() == decButton) {
	        if (textfield.getText().isEmpty()) {
	            textfield.setText("0.");
	        } else if (!textfield.getText().contains(".")) {
	            textfield.setText(textfield.getText().concat("."));
	        }
		}
		
	}

}
