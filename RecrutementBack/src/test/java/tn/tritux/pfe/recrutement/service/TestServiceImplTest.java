package tn.tritux.pfe.recrutement.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.tritux.pfe.recrutement.dto.request.TestRequest;
import tn.tritux.pfe.recrutement.dto.response.QuestionResponse;
import tn.tritux.pfe.recrutement.dto.response.TestResponse;
import tn.tritux.pfe.recrutement.entity.Question;
import tn.tritux.pfe.recrutement.entity.Test;
import tn.tritux.pfe.recrutement.entity.TestType;
import tn.tritux.pfe.recrutement.repository.TestRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@Slf4j
public class TestServiceImplTest {
    @Mock
    private TestRepository testRepository;
    @InjectMocks
    private TestServiceImpl testService;
    private Test test;
    private TestResponse testResponse;
    private TestRequest testRequest;
    private Question question;
    @BeforeEach
    void setUp(){
        question=new Question();
        question.setId(1L);
        question.setQuestion("question?");
        question.setOptions(List.of("A","B","C","D"));
        question.setReponseCorrecte("A");
        question.setNote(10);
        test=new Test();
        test.setId(1L);
        test.setDateCreation(LocalDateTime.now());
        test.setTechnologie("Java");
        test.setType(TestType.TECHNIQUE);
        test.setQuestions(Collections.singletonList(question));
        testRequest=new TestRequest();
        testRequest.setType(TestType.TECHNIQUE.name());
        testRequest.setTechnologie("Java");

        testResponse=TestResponse.builder()
                .id(test.getId())
                .dateCreation(test.getDateCreation())
                .type(test.getType().name())
                .technologie(test.getTechnologie())
                .questions(List.of(QuestionResponse.builder()
                        .id(question.getId())
                        .question(question.getQuestion())
                        .options(question.getOptions())
                        .reponseCorrecte(question.getReponseCorrecte())
                        .note(question.getNote())
                        .build()))
                .build();

    }
    @org.junit.jupiter.api.Test
    void testAjouterTest(){
        when(testRepository.save(any(Test.class))).thenReturn(test);
        TestResponse resutl=testService.ajouterTest(testRequest);
        assertNotNull(resutl);
        assertEquals(testResponse.getId(),resutl.getId());
        assertEquals(testResponse.getType(),resutl.getType());
        assertEquals(testResponse.getTechnologie(),resutl.getTechnologie());
        verify(testRepository,times(1)).save(any(Test.class));



    }
}
