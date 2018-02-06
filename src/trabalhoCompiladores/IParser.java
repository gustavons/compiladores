package trabalhoCompiladores;

public class IParser {
	public static final char EOF = (char)-1;
	public static final String SUM =  "+";
	public static final String LPAREN = "(";
	public static final String RPAREN = ")";
	public static final String PONT1 = "*";
	public static final String PONT2 = "*";	

	/*
	* Retorna o token (caractere) da vez. Caso toda a entrada
	* já tenha sido consumida, retorna EOF. Além disso, deve
	* ignorar todos os espaços em branco da entrada. Funciona
	* como um lexer simplificado.
	*/
	public char lookahead() {
	/*
	* Este método deve comparar o lookahead com um outro
	* caractere, se eles forem iguais avança para o próximo
	* caractere, caso contrário lança um erro de sintaxe.
	*/
		while (isWhitespace(peek)) nextChar();
		switch(peek) {
		case '+':
			nextChar();
			return Token.SUM;
		case '*':
			nextChar();
			return Token.MUL;
		
		case '(':
			nextChar();
			return Token.LPAREN;
		case ')':
			nextChar();
			return Token.RPAREN;
		case EOF_CHAR:
			return Token.EOF;
		default:
			if (Character.isDigit(peek)) {
				String num = "";
				do {
					num += peek;
					nextChar();
				} while( Character.isDigit(peek) );
				if ( peek != '.' ) return new Token(Tag.LIT_INT, num);
				do {
					num += peek;
					nextChar();
				} while ( Character.isDigit(peek) );
				return new Token(Tag.LIT_REAL, num);
			} else if ( isIdentifierStart(peek)  ) {
				String id = "";
				do {
					id += peek;
					nextChar();
				} while ( isIdentifierPart(peek) );
				if ( keywords.containsKey(id) )
					return keywords.get(id);
				return new Token(Tag.ID, id );
			}
		}
	
	private static boolean isWhitespace(int c) {
		switch (c) {
		case ' ': case '\t': case '\n':
			return true;
		default: return false;
		}
	}
	public boolean match(char c) {
	/*
	 * Este método deve imprimir uma mensagem de erro
	 * indicando o endereço do caractere que causou o erro
	 * e depois finalizar o programa
	 */
		
			
		if(look.tag() == t)
			return move();
		error("'" + look.lexeme() + "' inesperado");
		return null;
		
	}
	
	public void error(String s);
	/*
	 * Método que inicia a análise sintática chamando o
	 * método que representa o não-terminal inicial
	 */
	public boolean parse();
}
