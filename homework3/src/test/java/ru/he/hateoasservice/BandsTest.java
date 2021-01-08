package ru.he.hateoasservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.he.models.Band;
import ru.he.services.BandService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class BandsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BandService bandService;

    @BeforeEach
    public void setUp() {
        when(bandService.publish(1L)).thenReturn(publishedBand());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/bands/1/publish")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(publishedBand().getName()))
                .andExpect(jsonPath("$.description").value(publishedBand().getDescription()))
                .andExpect(jsonPath("$.state").value(publishedBand().getState()))
                .andDo(document("publish_course", responseFields(
                        fieldWithPath("name").description("Band name"),
                        fieldWithPath("description").description("Review"),
                        fieldWithPath("state").description("Band state")
                )));
    }

    private Band publishedBand() {
        return Band.builder()
                .id(1L)
                .description("Best prog band")
                .state("PUBLISHED")
                .name("Periphery")
                .build();
    }
}
