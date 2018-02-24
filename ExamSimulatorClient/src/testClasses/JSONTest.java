package testClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lk.ijse.examsimulator.dto.Paper;
import lk.ijse.examsimulator.util.json.deserializer.PaperDeserializer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by oshan on 24-Dec-17.
 *
 * @author oshan
 */
public class JSONTest {

    public static void main(String[] args) {
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(Paper.class, new PaperDeserializer());

        Gson gson=builder.create();
//        System.out.println(System.getProperty("user.dir"));
        try(Reader reader = new InputStreamReader(JSONTest.class.getResourceAsStream("/testClasses/TestPaper.json"), "UTF-8")){

            // Parse JSON to Java
            Paper paper = gson.fromJson(reader, Paper.class);
            System.out.println(paper);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
