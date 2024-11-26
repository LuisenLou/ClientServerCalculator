package applicattion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server implements Runnable{

	private JFrame frame;
	private JTextField title;
	private JTextArea chatPanel;
	private JButton resetButton;

	
	
	public Server() {
        frame = new JFrame("Servidor");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame = new JFrame();
		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		//Title
		title = new JTextField();
		title.setBounds(95, 11, 74, 20);
		title.setBackground(new Color(192, 192, 192));
		title.setFont(new Font("Arial", Font.BOLD, 14));
		title.setText("SERVER");
		frame.getContentPane().add(title);
		title.setColumns(10);
		
		//Allows scroll chat.
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		scrollPane.setBounds(21, 52, 242, 237);
		frame.getContentPane().add(scrollPane);
		
		
		//Chat panel.
		chatPanel = new JTextArea();
		chatPanel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		chatPanel.setBackground(new Color(192, 192, 192));
		scrollPane.setViewportView(chatPanel);
		
		//RESET button .

		resetButton = new JButton("RESET");
		resetButton.setBackground(new Color(192, 192, 192));
		resetButton.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		resetButton.setBounds(95, 300, 89, 23);
		frame.getContentPane().add(resetButton);
		resetButton.addActionListener(e -> chatPanel.setText(""));
		
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frame.setVisible(true);
					
					Thread t = new Thread(window);
					
					t.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
		

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (ServerSocket server = new ServerSocket(3000)){
			
			while(true) {
				Socket mySocket = server.accept();
				ObjectInputStream objectIn = new ObjectInputStream(mySocket.getInputStream());
				DataOutputStream dataOut = new DataOutputStream(mySocket.getOutputStream());
				
				Operation operation = (Operation)objectIn.readObject();
				
				
				int result = calculator(operation);
				
				chatPanel.append("Número 1: " + operation.getNumber1() + ". Número 2: "+ operation.getNumber2() + ". Resultado: " + result + "\n");
				
				dataOut.writeInt(result);
				
				mySocket.close();
			}
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException cls) {
			cls.printStackTrace();
		}
		
	}
	
	
	public int calculator(Operation operation) {
		int number1 = operation.getNumber1();
		int number2 = operation.getNumber2();
		Operator operator = operation.getOp();
		
		int result = 0;
		
		switch(operator) {
			case Operator.SUMA:
				result = number1 + number2;
				break;
			case Operator.RESTA:
				if(number1<number2) {
					chatPanel.append("Número 1 es más alto que Número 2 por lo que se invierten...");
					result = number2 - number1;
				}else {
					result = number1 - number2;
				}
				break;
			case Operator.MULTIPLICACION:
				result = number1 * number2;
				break;
			default: chatPanel.append("Error fatídico");
		
		}
		
		return result;
	}

}
