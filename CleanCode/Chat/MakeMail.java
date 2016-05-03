package Chat;



/**
 * Created by User on 14.03.2016.
 */

import java.util.Date;
import java.sql.Timestamp;

public class MakeMail
{
    private String id;
    private String author;
    private long timestamp;
    private String message;

    public MakeMail (String id, String author,long timestamp, String message)
    {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
        this.author = author;
    }

    public String getId()
    {
        return id;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getMessege()
    {
        return message;
    }

    public String getTime()
    {
        long ms = timestamp;
        Date tempData = new Date(ms);
        Timestamp temptime = new Timestamp(tempData.getTime());
        return tempData.toString();
    }

    public Date getData()
    {
        long ms = timestamp;
        return  new Date(ms);
    }

    @Override
    public String toString()
    {
        return "" +
                "id: " + id + ' ' +
                getTime() +
                "" + author +
                ": " + message;
    }


}
