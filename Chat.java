/**
 * Created by User on 16.02.2016.
 * <p>
 * Created by User on 13.02.2016.
 */


import com.google.gson.Gson;

import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


public class Chat_A {
    public static void main() throws IOException {
        Mail m = new Mail();
        String id, name, text, data;
        int sch = 1;

        System.out.println("Выберите действие:");
        System.out.println("1.Загрузка сообщения по id.");
        System.out.println("2.Сохранение сообщения. ");
        System.out.println("3.Просмотр истории сообщений.");
        System.out.println("4.Удаление сообщения по id.");
        System.out.println("5.Поиск в истории сообщений по автору.");
        System.out.println("6.Поиск в истории сообщщений по ключевому слову.");
        System.out.println("7.Поиск в истории сообщения по ключевому слову.");
        System.out.println("8.Просмотр истории сообщений за определенный период.");
        System.out.println("9.Добавление сообщения.");
        System.out.println("10.Выйти из чата :)");

        cs:
        while (sch > 0) {
            sch = System.in.read();
            switch (sch) {
                case (1):
                    System.out.println("Введите id");
                    id = String.valueOf(System.in.read());
                    m.load(id);
                    break;
                case (2):
                    m.save();
                    break;
                case (3):
                    m.read();
                    break;
                case (4):
                    System.out.println("Введите id");
                    id = String.valueOf(System.in.read());
                    m.del(id);
                    break;
                case (5):
                    System.out.println("Введите имя");
                    name = String.valueOf(System.in.read());
                    m.seurch(name);
                    break;
                case (6):
                    System.out.println("Введите слово");
                    text = String.valueOf(System.in.read());
                    m.seurch_txt(text);
                    break;
                case (7):
                    //?????????????
                    break;
                case (8):
                    System.out.println("Введите дату");
                    data = String.valueOf(System.in.read());
                    m.seurch_data(data);
                    break;
                case (9):
                    System.out.println("Введите id сообщения");
                    id = String.valueOf(System.in.read());
                    System.out.println("Введите имя");
                    name = String.valueOf(System.in.read());
                    System.out.println("Введите дату");
                    data = String.valueOf(System.in.read());
                    System.out.println("Введите текст");
                    text = String.valueOf(System.in.read());
                    m.make_mail(id, name, data, text);
                    break;
                case (10):
                    break cs;
            }
        }

    }
}

class Mail {
    private String id, name, text, data;
    private JsonObject personObject, personJSONData;
    private JsonReader reader = Json.createReader(new StringReader(personJSONData));
    private JsonArray personArray;
    private StringWriter stringWriter;
    private Gson gson;

        // Загрузка сообщения по id
        void load(String id){
        reader=Json.createReader(new StringReader(personJSONData));
        JsonArray personArray=reader.readArray();
        reader.close();

        for(JsonValue jsonVal:personArray)
        {
        if(id==((JsonObject)personObj).getString("id"))
        System.out.println(((JsonObject)personObj).getString("id")+" ; "
        +((JsonObject)personObj).getString("Author")
        +" ; "+((JsonObject)personObj).getString("timestamp")
        +" ; "+((JsonObject)personObj).getString("massage"));

        }
        }
        void save()
        {
        stringWriter=new StringWriter();
        JsonWriter writer=Json.createWriter(stringWriter);
        writer.writeObject(personObject);
        writer.close();
        }

        void read(){
        reader=Json.createReader(new StringReader(personJSONData));
        JsonArray personArray=reader.readArray();
        reader.close();


        for(JsonValue jsonVal:personArray){
        System.out.println(personObj.getValueType()+" - "
        +((JsonObject)personObj).getString("name"));
        }
        }


        void del(String id){
        reader=Json.createReader(new StringReader(personJSONData));
        JsonArray personArray=reader.readArray();
        reader.close();

        for(JsonValue jsonVal:personArray)if(id==((JsonObject)personObj).getString("id"));

        }


        void seurch(String name){

        }


        void seurch_txt(String text){

        }

        void seurch_data(String data){

        }

        // создание сообщения
        void make_mail(String id,String name,String text,String data){
        personObject=Json.createObjectBuilder()
        .add("id",id)
        .add("Author",name)
        .add("timestamp",data)
        .add("massage",text)
        .build();
        }
        }
