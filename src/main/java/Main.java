import exceptions.TondeuseException;
import services.TondeuseService;
import services.TondeuseServiceImpl;

public class Main {

	public static void main(String[] args) throws TondeuseException {
		
		TondeuseService tondeuseService = new TondeuseServiceImpl();
		//tondeuseService.startProgram("testEven.txt");
		tondeuseService.startProgram("test.txt");
	}

}
