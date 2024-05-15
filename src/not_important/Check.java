package not_important;
import java.io.IOException;

public class Check {
    public static void main(String[] args) {
        
    }
    public void copyFile(String fName1,String fName2) throws MyException,IOException{
        if(fName1.equals(fName2)){
            throw new MyException("Duplicate file name");
        }
        try{
            //copyfile
        }
        catch(Exception e){
            throw new IOException("Copy failed");
        }
    }
}
class MyException extends Exception{
    public MyException(String message){}
}
