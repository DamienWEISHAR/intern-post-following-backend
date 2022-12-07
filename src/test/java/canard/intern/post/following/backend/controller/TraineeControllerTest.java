package canard.intern.post.following.backend.controller;

import canard.intern.post.following.backend.controller.fixture.TraineeJsonProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TraineeController.class)
class TraineeControllerTest {

    //final = constante, static = 1 seule instance dans toute la classe
    final static String BASE_URL = "/api/trainees";
    final static String URL_TEMPLATE_ID = BASE_URL + "/{id}";

    @Autowired
    MockMvc mockMvc; //équivalent de PostMan, pour tester automatiquement

    @Test
    void getAll() {
        //TODO
        fail("Test not defined yet");
    }

    @Test
    void getById() throws Exception {
        int id = 2;
        mockMvc.perform(get(URL_TEMPLATE_ID, id)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                //étapes de vérification
                .andExpect(status().isOk()) //statut
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))//si c'est du JSON
               // .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // pour vérifier si la réponse a un id
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id)); // alt+entrée pour rendre plus lisible et faire des imports static
                .andExpect(jsonPath("$.id").value(id));

    }

    @Test
    void getById_KO_idNotFound(){
        //TODO
        fail("Test not defined yet");
    }

    @Test
    void getById_KO_xmlNotAcceptable() throws Exception{ //test de robustesse, pour dire qu'on n'accepte que du JSON et pas de XML
        int id = 2;
        mockMvc.perform(get(URL_TEMPLATE_ID, id)
                .accept(MediaType.APPLICATION_XML)
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable());
    }

    @Test
        //NB: check all fields in response are equals to request fields
    void create_OK_allFieldsValid() throws Exception {
        var traineeJson = TraineeJsonProvider.traineeJsonAllFieldsValid();
        mockMvc.perform(post(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(traineeJson)
                )
                .andDo(print())
                //je vérifie si c'est créé,, le type de contenu (json), et si les champs existent
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());


    }

    @ParameterizedTest //pour dire que le test a des paramètres
    @MethodSource ("canard.intern.post.following.backend.controller.fixture.TraineeJsonProvider#traineeJsonMissingNonRequiredField")
        //car on a besoin d'une méthode externe pour les datas = (packagename.Classename#methodename)
    void create_OK_missingNonRequiredField(String traineeJson) throws Exception {
        //gender, phoneNumber
        mockMvc.perform(post(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(traineeJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

    }

    @Test
    void create_KO_missingRequiredField() {
        // lastName, firstName, email, birthDate
        fail("Test not defined yet");
    }

    @Test
    void create_KO_invalidBirthDate() throws Exception {
        var traineeJson = TraineeJsonProvider.traineeJsonInvalidBirthDate();
        mockMvc.perform(post(BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(traineeJson)
        )
                .andDo(print())
                .andExpect(status().isBadRequest()); //car il retourne une erreur 400, qui est une bad Request

    }


    @Test
    void create_ko_invalid_field() {
        // email, birthDate 18 years, phoneNumber with Pattern
        fail("Test not defined yet");
    }


    @Test
    void delete() {
        //TODO
        fail("Test not defined yet");
    }

    @Test
    void update() {
        //TODO
        fail("Test not defined yet");
    }
}