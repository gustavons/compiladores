package trabalhoCompiladores;

public class main {

	public static void main(String[] args) {
		IParser iParser = new IParser("a     +a      ");
		iParser.parse();
	}

}
