
public class Notation {

	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		
		MyStack<String> postfixStack = new MyStack<String>(20);
		MyQueue<String> postfixQueue = new MyQueue<String>(20);
		char[] temp = infix.toCharArray();
		
		try {
			for(int i = 0; i < temp.length; i++) {
				if (temp[i] == ' ')
					continue;
				if (Character.isDigit(temp[i]))
					postfixQueue.enqueue(String.valueOf(temp[i]));
				if (temp[i] == '(')
					postfixStack.push(String.valueOf(temp[i]));
				
				if (temp[i] == '+') {
					if (!postfixStack.isEmpty()) {
						while (postfixStack.top().equals("+") || postfixStack.top().equals("-") || postfixStack.top().equals("*") || postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if (temp[i] == '-') {
					if (!postfixStack.isEmpty()) {
						while (postfixStack.top().equals("+") || postfixStack.top().equals("-") || postfixStack.top().equals("*") || postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if (temp[i] == '/') {
					if (!postfixStack.isEmpty()) {
						while (postfixStack.top().equals("*") || postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if (temp[i] == '*') {
					if (!postfixStack.isEmpty()) {
						while (postfixStack.top().equals("*") || postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if (temp[i] == ')') {
					while (!postfixStack.isEmpty() && !postfixStack.top().equals("(")) {
						postfixQueue.enqueue(postfixStack.pop());
					}
					if (postfixStack.isEmpty() || !postfixStack.top().equals("(")) {
						throw new InvalidNotationFormatException();
					}
					
					if (!postfixStack.isEmpty() && postfixStack.top().equals("(")){
						postfixStack.pop();
					}
				}
			}
			while (!postfixStack.isEmpty() && !postfixStack.top().equals("(")) {
				postfixQueue.enqueue(postfixStack.pop());
			}
		}
		catch(QueueOverflowException e) {
			e.printStackTrace();
		}
		catch(StackOverflowException d) {
			d.printStackTrace();
		}
		catch(StackUnderflowException s) {
			s.printStackTrace();
		}
		return postfixQueue.toString();
	}
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		String infix;
		String top;
		MyStack<String> infixStack = new MyStack<String>(10);
		char[] temp = postfix.toCharArray();
		
		try {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == ' ') {
					continue;
				}
				if (Character.isDigit(temp[i])) {
					infixStack.push(String.valueOf(temp[i]));
				}
				if (temp[i] == '+' || temp[i] == '-' || temp[i] == '/' || temp[i] == '*') {
					if (infixStack.size()<2) {
						throw new InvalidNotationFormatException();
					}
					else {
						top = infixStack.pop();
						infix = "("+infixStack.pop() + temp[i] + top + ")";
						infixStack.push(infix);
					}
				}
			}
			if (infixStack.size() > 1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException d) {
			d.printStackTrace();
		}
		catch(StackUnderflowException s) {
			s.printStackTrace();
		}
		return infixStack.toString();
	}
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		String f, sec;
		double result = 0;
		MyStack<String> postfixStack = new MyStack<String>(10);
		char[] temp = postfixExpr.toCharArray();
		
		try {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == ' ') 
					continue;
				if (Character.isDigit(temp[i]) || temp[i] == '(') 
					postfixStack.push(String.valueOf(temp[i]));
				else {
					if (postfixStack.size()<2) 
						throw new InvalidNotationFormatException();
					else {
						sec = postfixStack.pop();
						f = postfixStack.pop();
						result = operation(f, sec, temp[i]);
						postfixStack.push(Double.toString(result));
					}
				}
			}
			if (postfixStack.size() > 1)
				throw new InvalidNotationFormatException();
		}
		catch(StackOverflowException d) {
			d.printStackTrace();
		}
		catch(StackUnderflowException s) {
			s.printStackTrace();
		}
		
					
		return result;
	}
	
	/*Helping Functions*/
	
	private static double operation(String first, String second,char op) {
		double m, n, result = 0;
		switch(op) {
		case '+':
			m = Double.parseDouble(first);
			n = Double.parseDouble(second);
			result = m + n;
			break;
		
		case '-':
			m = Double.parseDouble(first);
			n = Double.parseDouble(second);
			result = m - n;
			break;
		
		case '*':
			m = Double.parseDouble(first);
			n = Double.parseDouble(second);
			result = m * n;
			break;
		
		case '/':
			m = Double.parseDouble(first);
			n = Double.parseDouble(second);
			result = m / n;
			break; 
		
		default:
			System.out.println("Unknown operator");
		}	
		return result;
	}

	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
		double result = 0;
		result = evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
		return result;
	}
}
