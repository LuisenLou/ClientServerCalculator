package applicattion;

import java.io.Serializable;

public class Operation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int number1;
	private int number2;
	private Operator op;
	
	
	
	public Operation (int number1, int number2, Operator op) {
		this.number1 = number1;
		this.number2 = number2;
		this.op = op;
	}



	/**
	 * @return the number1
	 */
	public int getNumber1() {
		return number1;
	}



	/**
	 * @param number1 the number1 to set
	 */
	public void setNumber1(int number1) {
		this.number1 = number1;
	}



	/**
	 * @return the number2
	 */
	public int getNumber2() {
		return number2;
	}



	/**
	 * @param number2 the number2 to set
	 */
	public void setNumber2(int number2) {
		this.number2 = number2;
	}



	/**
	 * @return the op
	 */
	public Operator getOp() {
		return op;
	}



	/**
	 * @param op the op to set
	 */
	public void setOp(Operator op) {
		this.op = op;
	}
	
	
	
}
