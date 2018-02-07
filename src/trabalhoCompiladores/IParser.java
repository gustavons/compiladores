package trabalhoCompiladores;

public class IParser {
	public static final char EOF = (char)-1;

	public String palavra = "";
	public int count =  0;

	/*
	* Retorna o token (caractere) da vez. Caso toda a entrada
	* já tenha sido consumida, retorna EOF. Além disso, deve
	* ignorar todos os espaços em branco da entrada. Funciona
	* como um lexer simplificado.
	*/
	public char lookahead() {
		if (count == palavra.length()) {
			return EOF;
		}
		
		char c = palavra.charAt(count);
		while (c == '\t'|| c ==' ') {
			count++;
			c = palavra.charAt(count);	

		}		
		return c; 
	}
	
	public void move() {
		count++;
	}
	/*
	* Este método deve comparar o lookahead com um outro
	* caractere, se eles forem iguais avança para o próximo
	* caractere, caso contrário lança um erro de sintaxe.
	*/
	public boolean match(char c) {
		char look = lookahead();
		if (look == c) {
			System.out.println(c);
			move();
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
		System.err.println("Erro no endereço: " + count + ": " + s);
		System.exit(0);
	}
	/*
	 * Método que inicia a análise sintática chamando o
	 * método que representa o não-terminal inicial
	 */
	public boolean parse() {
		palavra = "aa";
		E();
		
		
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



