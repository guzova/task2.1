/**
 * Created by GE70 on 17.09.2016.
 */
public class Exceptions extends Exception  {
    private String message;
    public Exceptions(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}