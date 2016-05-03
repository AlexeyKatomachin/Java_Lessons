package Chat;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by User on 03.05.2016.
 */
public class Loger
{
    private FileWriter writer;

    Loger(String fileName)
    {
        try
        {
            writer = new FileWriter(fileName, true);
        }catch (IOException e)
        {
            System.out.println("Loger error: writer is not created");
        }
    }

    public void writeLog(String str)
    {
        try
        {
            writer.write(str);
        }catch (IOException e)
        {
            System.out.println("FileWriter exception\" + e");
        }
    }

    public void closeLog()
    {
        try
        {
            writer.close();
        }catch (IOException e)
        {
            System.out.println("FileWriter exception" + e);
        }
    }

}
