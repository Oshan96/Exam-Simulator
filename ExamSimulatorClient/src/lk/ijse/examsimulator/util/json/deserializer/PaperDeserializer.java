package lk.ijse.examsimulator.util.json.deserializer;

import com.google.gson.*;
import lk.ijse.examsimulator.dto.CodingQuestion;
import lk.ijse.examsimulator.dto.MCQQuestion;
import lk.ijse.examsimulator.dto.Paper;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by oshan on 24-Dec-17.
 *
 * @author oshan
 */
public class PaperDeserializer implements JsonDeserializer<Paper> {
    @Override
    public Paper deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject jsonObject=jsonElement.getAsJsonObject();
        final String id=jsonObject.get("pid").getAsString();
        final JsonArray mcqs=jsonObject.get("mcqQuestions").getAsJsonArray();

        final ArrayList<MCQQuestion> mcqQuestions=new ArrayList<>();
        for(int i=0;i<mcqs.size();i++){
            MCQQuestion question=new MCQQuestion();
            question.setQid(mcqs.get(i).getAsJsonObject().get("qid").getAsString());
            question.setMarks(mcqs.get(i).getAsJsonObject().get("marks").getAsInt());
            question.setQuestion(mcqs.get(i).getAsJsonObject().get("question").getAsString());

            JsonArray jsonoptions=mcqs.get(i).getAsJsonObject().get("options").getAsJsonArray();
            String[] options=new String[jsonoptions.size()];
            for (int j=0;j<options.length;j++){
                options[j]=jsonoptions.get(j).getAsJsonObject().get("val").getAsString();
            }
            question.setOptions(options);
            question.setAnswer(mcqs.get(i).getAsJsonObject().get("answer").getAsString());

            mcqQuestions.add(question);
        }

        final JsonArray codingQs=jsonObject.get("codingQs").getAsJsonArray();

        final ArrayList<CodingQuestion> codingQuestions=new ArrayList<>();

        for(int i=0;i<codingQs.size();i++){
            CodingQuestion question=new CodingQuestion();
            question.setQid(codingQs.get(i).getAsJsonObject().get("qid").getAsString());
            question.setMarks(codingQs.get(i).getAsJsonObject().get("marks").getAsInt());
            question.setQuestion(codingQs.get(i).getAsJsonObject().get("question").getAsString());
            question.setAnswer(codingQs.get(i).getAsJsonObject().get("answer").getAsString());
            question.setStudentSource(codingQs.get(i).getAsJsonObject().get("studentSource").getAsString());
            codingQuestions.add(question);
        }

        final int totalMarks=jsonObject.get("totalMarks").getAsInt();

        return new Paper(
                id,
                mcqQuestions,
                codingQuestions,
                totalMarks
        );
    }
}
