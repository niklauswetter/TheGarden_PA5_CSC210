import java.util.Locale;

public class Plant
{
    //Instance Variables
    private String name;
    private char symbol;

    //Methods
    public Plant(String name)
    {
        this.name=name.toLowerCase();
        this.symbol=this.name.charAt(0);
    }

}
