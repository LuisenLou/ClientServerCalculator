package applicattion;

import java.net.*;

import java.awt.EventQueue;

import javax.swing.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class ClientChat {

	private JFrame frame;
	private JTextField title;
	private JTextField numberOne;
	private JTextField numberTwo;
	private JRadioButton radioBtn1, radioBtn2, radioBtn3;
	private ButtonGroup bg;
	private JButton btnSendButton;
	private JTextArea chatPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientChat window = new ClientChat();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientChat()  {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Title
		title = new JTextField();
		title.setBackground(new Color(192, 192, 192));
		title.setFont(new Font("Arial", Font.BOLD, 14));
		title.setText("CLIENT");
		title.setBounds(108, 11, 61, 20);
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		//Insert number1 
		numberOne = new JTextField();
		numberOne.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		numberOne.setBackground(new Color(192, 192, 192));
		numberOne.setBounds(30,50, 82, 20);
		frame.getContentPane().add(numberOne);
		numberOne.setColumns(10);
		
		
		//Insert number2
		numberTwo = new JTextField();
		numberTwo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		numberTwo.setBackground(new Color(192, 192, 192));
		numberTwo.setBounds(170,50, 82, 20);
		frame.getContentPane().add(numberTwo);
		numberTwo.setColumns(10);
		
		//Choose operator.
		bg = new ButtonGroup();
		radioBtn1 = new JRadioButton("SUMA");
		radioBtn1.setBounds(10, 100, 60, 50);
		radioBtn1.setSelected(true);
		frame.getContentPane().add(radioBtn1);
		bg.add(radioBtn1);
		
		radioBtn2 = new JRadioButton("RESTA");
		radioBtn2.setBounds(70, 100, 70, 50);
		frame.getContentPane().add(radioBtn2);
		bg.add(radioBtn2);
		
		radioBtn3 = new JRadioButton("MULTIPLICACION");
		radioBtn3.setBounds(140, 100, 140, 50);
		frame.getContentPane().add(radioBtn3);
		bg.add(radioBtn3);
		
		//Associated actionListener with radiobutton.
		radioBtn1.addActionListener(resetTextFieldListener);
		radioBtn2.addActionListener(resetTextFieldListener);
		radioBtn3.addActionListener(resetTextFieldListener);

		
		//SEND button , send Client with attributes.
		
		btnSendButton = new JButton("SEND");
		btnSendButton.setBackground(new Color(192, 192, 192));
		btnSendButton.setEnabled(false);
		btnSendButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		
		// Enable button dynamically based on input validation
        KeyAdapter validationAdapter = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                boolean enabled = checkNumber(numberOne) && checkNumber(numberTwo);
                chatPanel.setText("");
                btnSendButton.setEnabled(enabled);
            }
        };
        numberOne.addKeyListener(validationAdapter);
        numberTwo.addKeyListener(validationAdapter);
		
		
		btnSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					int number1 = conversor(numberOne);
					int number2 = conversor(numberTwo);
					Operator operator = getSelectedOperator();
					
					Operation operation = new Operation(number1, number2, operator);
					
					sendData(operation);	
					
			}
		});
		
		btnSendButton.setBounds(95, 300, 89, 23);
		frame.getContentPane().add(btnSendButton);
		btnSendButton.addActionListener(resetTextFieldListener);
		
		
		//Allows scroll chat.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(90, 150, 100, 100);
		frame.getContentPane().add(scrollPane);
		
		
		//Chat panel.
		chatPanel = new JTextArea();
		chatPanel.setFont(new Font("Monospaced", Font.PLAIN, 38));
		chatPanel.setBackground(new Color(192, 192, 192));
		chatPanel.setEditable(false);
		scrollPane.setViewportView(chatPanel);
		
		
	}
	
	

	public boolean checkNumber (JTextField string) {
		
		try {
			Integer.parseInt(string.getText());
	        return true;
			
		}catch(NumberFormatException nb) {
			JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}	
	}
	
	
	
	public int conversor (JTextField string){
		return Integer.parseInt((String)string.getText());
	}
	
	
	
	public int sendData(Operation operation) {
		
		int result = 0;
		
		 try (Socket mySocket = new Socket("localhost", 3000);
		         ObjectOutputStream objectOut = new ObjectOutputStream(mySocket.getOutputStream());
		         DataInputStream dataIn = new DataInputStream(mySocket.getInputStream())) {
			
			System.out.println("Números seleccionados" + operation.getNumber1() + " y " + operation.getNumber2());
			System.out.println("Operación elegida: " + operation.getOp());
			
			objectOut.writeObject(operation);
			
			
			
			result = dataIn.readInt();
			
			chatPanel.append(String.valueOf(result));
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error:" + e.getMessage());
			e.printStackTrace();
		} finally {
		}
		System.out.println(numberOne +
				 " ' " + numberTwo);
		return result;
		
	}
	
	
	//Select the operator depends on radioBtn selection.
	public Operator getSelectedOperator() {
		if(radioBtn1.isSelected())return Operator.SUMA;
		if(radioBtn2.isSelected())return Operator.RESTA;
		if(radioBtn3.isSelected())return Operator.MULTIPLICACION;
		return null;
	}
	
	//Reset result field if radiobutton is changed.
	ActionListener resetTextFieldListener = new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	chatPanel.setText("");
	    }
	};

}
