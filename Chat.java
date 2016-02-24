


import java.io.FileWriter;
import javax.json.*;
import java.io.*;
import java.util.Objects;


public class Chat_A {
    public void main() throws IOException {
        Mail m = new Mail();
        int sch = 1;
        chousTask(m, sch);

    }

    private void chousTask(Mail m, int sch) throws IOException {
        String id;
        String name;
        String text;
        String data;
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
    private JsonObject personObject;
    private JsonReader reader;
    private JsonArray personArray;
    private String fileName = "config.cfg";

    Mail() {

    }

    // Загрузка сообщения по id
    void load(String id) {
        reader = Json.createReader(new StringReader(fileName));
        JsonArray personArray = reader.readArray();
        reader.close();

        for (JsonValue jsonVal : personArray) {
            if (Objects.equals(id, personObject.getString("id"))) {
                System.out.println(personObject.getString("id") + " ; "
                        + personObject.getString("Author")
                        + " ; " + personObject.getString("timestamp")
                        + " ; " + personObject.getString("massage"));
            }

        }
    }

    void save() throws IOException {
        personArray.add(personObject);
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(personArray.toString());
        }
    }

    void read() {
        reader = Json.createReader(new StringReader(fileName));
        JsonArray personArray = reader.readArray();
        reader.close();


        for (JsonValue jsonVal : personArray) {
            System.out.println("id" + personObject.getString("id") + " ; ");
            System.out.println("Author" + personObject.getString("Author") + " ; ");
            System.out.println("timestamp" + personObject.getString("timestamp") + " ; ");
            System.out.println("massage" + personObject.getString("massage") + " ; ");
        }
    }


    void del(String id) {
        reader = Json.createReader(new StringReader(fileName));
        JsonArray personArray = reader.readArray();
        reader.close();

        for (JsonValue jsonVal : personArray) if (id == personObject.getString("id")) ;

    }


    void seurch(String name) {
        reader = Json.createReader(new StringReader(fileName));
        JsonArray personArray = reader.readArray();
        reader.close();

        for (JsonValue jsonVal : personArray) {
            if (name == personObject.getString("Author"))
                System.out.println(personObject.getString("id") + " ; "
                        + personObject.getString("Author")
                        + " ; " + personObject.getString("timestamp")
                        + " ; " + personObject.getString("massage"));

        }
    }


    void seurch_txt(String text) {

    }

    void seurch_data(String data) {
        reader = Json.createReader(new StringReader(fileName));
        JsonArray personArray = reader.readArray();
        reader.close();

        for (JsonValue jsonVal : personArray) {
            if (data == personObject.getString("timestamp"))
                System.out.println(personObject.getString("id") + " ; "
                        + personObject.getString("Author") + " ; "
                        + personObject.getString("timestamp") + " ; "
                        + personObject.getString("massage"));

        }
    }

    // создание сообщения
    void make_mail(String id, String name, String text, String data) {
        personObject = Json.createObjectBuilder()
                .add("id", id)
                .add("Author", name)
                .add("timestamp", data)
                .add("massage", text)
                .build();
    }
}
