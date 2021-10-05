import java.io.File;

public class PA5Main {

    public static void main(String[] args) throws Exception {
	    File input = new File(args[0]);
        CommandHandler commandHandler = new CommandHandler(input);
        Garden garden = new Garden(commandHandler.getGardenRow(),commandHandler.getGardenCol());
        commandHandler.setGarden(garden);
        commandHandler.execute();
    }
}
