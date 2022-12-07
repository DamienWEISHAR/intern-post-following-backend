package canard.intern.post.following.backend.controller.fixture;
//fixture = datas que l'on injecte dans un test


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.stream.Stream;

public class TraineeJsonProvider {

    private static ObjectNode traineeJsonObjectRequiredFields(){
        var objectMapper = new ObjectMapper();
        var trainee = objectMapper.createObjectNode();
        return trainee.put("lastName", "Solo")
                .put("firstName", "Han")
                .put("birthDate","1950-08-23")
                .put("email", "han.solo@faucon.fr");
    }

    public static String traineeJsonAllFieldsValid(){
        return traineeJsonObjectRequiredFields()
                .put("gender","M")
                .put("phoneNumber","+33707070707")
                .toPrettyString();
    }

    //ici on va créer un objet contenant que les required, 1 objet avec gender, 1 objet phoneNumber
    public static Stream<String> traineeJsonMissingNonRequiredField(){
        return Stream.of(
                traineeJsonObjectRequiredFields().toPrettyString(),
                traineeJsonObjectRequiredFields()
                        .put("gender","M")
                        .toPrettyString(),
                traineeJsonObjectRequiredFields()
                        .put("phoneNumber","+33707070707")
                        .toPrettyString()
        );
    }

    public static String traineeJsonInvalidBirthDate(){
        var wrongBirthDate = LocalDate.now()
                .minusYears(18L) //L car c'est un long qui est attendu
                .plusDays(1L);
        var objectMapper = new ObjectMapper();
        var trainee = objectMapper.createObjectNode();
        return trainee.put("lastName", "Solo")
                .put("firstName", "Han")
                .put("birthDate",wrongBirthDate.toString())
                .put("email", "han.solo@faucon.fr")
                .toPrettyString();
    }
}
