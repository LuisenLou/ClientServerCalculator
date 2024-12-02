package applicattion;

import java.net.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		JLabel labelNumber1 = new JLabel("Inserte número:");
		labelNumber1.setFont(new Font("Arial", Font.BOLD, 11));
		labelNumber1.setBounds(30,30, 95, 20);
		frame.getContentPane().add(labelNumber1);
		
		numberOne = new JTextField();
		numberOne.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		numberOne.setBackground(new Color(192, 192, 192));
		numberOne.setBounds(30,50, 82, 20);
		frame.getContentPane().add(numberOne);
		numberOne.setColumns(10);	
		
		
		//Insert number2
		JLabel labelNumber2 = new JLabel("Inserte número:");
		labelNumber2.setFont(new Font("Arial", Font.BOLD, 11));
		labelNumber2.setBounds(170,30, 95, 20);
		frame.getContentPane().add(labelNumber2);
		
		numberTwo = new JTextField();
		numberTwo.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		numberTwo.setBackground(new Color(192, 192, 192));
		numberTwo.setBounds(170,50, 82, 20);
		frame.getContentPane().add(numberTwo);
		numberTwo.setColumns(10);
		
		//Choose operator.
		JLabel operator = new JLabel("SELECCIONE LA OPERACIÓN:");
		operator.setFont(new Font("Arial", Font.BOLD, 11));
		operator.setBounds(50, 80, 200, 20);
		frame.getContentPane().add(operator);
		
		bg = new ButtonGroup();
		radioBtn1 = new JRadioButton("SUMA");
		radioBtn1.setBounds(10, 100, 60, 40);
		radioBtn1.setSelected(true);
		frame.getContentPane().add(radioBtn1);
		bg.add(radioBtn1);
		
		radioBtn2 = new JRadioButton("RESTA");
		radioBtn2.setBounds(70, 100, 70, 40);
		frame.getContentPane().add(radioBtn2);
		bg.add(radioBtn2);
		
		radioBtn3 = new JRadioButton("MULTIPLICACION");
		radioBtn3.setBounds(140, 100, 140, 40);
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
		addValidationListeners();
		
		btnSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSendButtonAction();
			}
		});
		
		btnSendButton.setBounds(90, 300, 89, 23);
		frame.getContentPane().add(btnSendButton);
		btnSendButton.addActionListener(resetTextFieldListener);
		
		//Allows scroll chat.
		
		JLabel result = new JLabel("RESULTADO:");
		result.setFont(new Font("Arial", Font.BOLD, 18));
		result.setBounds(80, 110, 150, 100);
		frame.getContentPane().add(result);
		
		
		//Chat panel.
		chatPanel = new JTextArea();
		chatPanel.setFont(new Font("Monospaced", Font.BOLD, 30));
		chatPanel.setBounds(85, 190, 100, 50);
		chatPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		chatPanel.setBackground(new Color(192, 192, 192));
		chatPanel.setEditable(false);
		frame.getContentPane().add(chatPanel);
		
	}
	
	
	
	// Validates the input fields and enables or disables the send button accordingly
	private void validateAndToggleSendButton() {
	    boolean enabled = checkNumber(numberOne) && checkNumber(numberTwo);
	    btnSendButton.setEnabled(enabled);
	}
	
	
	
	// Adds validation listeners to the input fields
	private void addValidationListeners() {
	    KeyAdapter validationAdapter = new KeyAdapter() {
	        @Override
	        public void keyReleased(KeyEvent e) {
	            validateAndToggleSendButton();
	        }
	    };

	    numberOne.addKeyListener(validationAdapter);
	    numberTwo.addKeyListener(validationAdapter);
	}
	
	
	
	// Checks if the text entered in a JTextField is a valid number
	public boolean checkNumber (JTextField string) {
		
		try {
			if(!string.getText().equals("")) {
				Integer.parseInt(string.getText());
				return true;
			}else if(string.getText().equals("")) {
				return false;
			}else throw new NumberFormatException();
			
		}catch(NumberFormatException nb) {
			JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	
	
	// Handles the send button action
	public void handleSendButtonAction() {
	    try {
	        int number1 = Integer.parseInt((String)numberOne.getText());
	        int number2 = Integer.parseInt((String)numberTwo.getText());
	        Operator operator = getSelectedOperator();

	        Operation operation = new Operation(number1, number2, operator);

	        sendData(operation);
	    } catch (IllegalArgumentException ex) {
	    	JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(frame, "Error inesperado.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	
	
	// Sends the operation data to the server and returns the result
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
		}
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
