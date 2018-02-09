package trabalhoCompiladores;

public class IParser {
	public static final char EOF = (char)-1;

	public String input;
	public int index;

	public IParser(String i) {
		input = i;
		index = 0;
	}
	/*
	* Retorna o token (caractere) da vez. Caso toda a entrada
	* já tenha sido consumida, retorna EOF. Além disso, deve
	* ignorar todos os espaços em branco da entrada. Funciona
	* como um lexer simplificado.
	*/
	public char lookahead() {
		if (index == input.length()) {
			return EOF;
		}
		
		char c = input.charAt(index);
		while (c == '\t'|| c ==' ') {
			index++;
			c = input.charAt(index);	

		}		
		return c; 
	}
	
//	public void move() {
//		index++;
//	}
	/*
	* Este método deve comparar o lookahead com um outro
	* caractere, se eles forem iguais avança para o próximo
	* caractere, caso contrário lança um erro de sintaxe.
	*/
	public boolean match(char c) {
		char look = lookahead();
		if (look == c) {
			System.out.println(c);
			index++;
			return true;
		}
		else {
			error("'" + c + "' inesperado ");
			return false;
		}
		
		
	}
	/*
	 * Este método deve imprimir uma mensagem de erro
	 * indicando o endereço do caractere que causou o erro
	 * e depois finalizar o programa
	 */	
	public void error(String s) {
		System.err.println("Erro no endereço: " + index + ": " + s);
		System.exit(0);
	}
	/*
	 * Método que inicia a análise sintática chamando o
	 * método que representa o não-terminal inicial
	 */
	public boolean parse() {
		//input = "a*";
		E();
		if(lookahead() != EOF){
			error("expressão inválida");
		}
		return true;
	}
	public void E() {
		P();
		A();
	}
	public void A() {
		if (lookahead() == '+') {
			match('+');
			P();
			A();
		}
		
	}
	public void P() {
		F();
		G();
	}
	public void G() {
		if (lookahead() == '*') {
			match('*');
			if (match('*')) {
				P();
			}
		}
		
	}
	public void F() {
		
		if (lookahead() == '(') {
			match('(');
			E();
			match(')');
		}
		else if(lookahead() == 'a') {
			match('a');
		}
		else {
			error("expressão inválida");
		}
		
	}
	
}
