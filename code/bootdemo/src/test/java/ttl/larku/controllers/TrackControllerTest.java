package ttl.larku.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ttl.larku.dao.inmemory.InMemoryTrackDAO;
import ttl.larku.service.TrackService;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
//@Tag("integration")
public class TrackControllerTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init() { }

    @Test
    public void testGetAllTracks() throws Exception {
        MediaType accept = MediaType.APPLICATION_JSON;
        MediaType contentType = MediaType.APPLICATION_JSON;

        //Done - - Initialize ResultActions with the result of a mockMvc call to get one track
        //     - Look at the main line projects for examples
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/tracks")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(contentType)
        );

        //Done - Add an expectation to check that the status is Ok
        actions = actions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(contentType))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(6)));

        //Done - Add an expectation to check the size of the returned collection
        actions = actions.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(6)));
        MvcResult result = actions.andReturn();
        System.out.println("result is " + result);
    }


    @Test
    public void testGetOneStudent() throws Exception {
        MediaType accept = MediaType.APPLICATION_JSON;
        MediaType contentType = MediaType.APPLICATION_JSON;
        //TODO - Initialize ResultActions with the result of a mockMvc call to get one track
        //     - Look at the main line projects for examples
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/tracks/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(contentType)
        );;

        //TODO - Add an expectation to check that the status is Ok
        actions = actions.andExpect(MockMvcResultMatchers.status().isOk());


        //TODO - Add an expectation to check the size of the returned collection
        actions = actions.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(1)));


        MvcResult result = actions.andReturn();

        System.out.println("result is " + result.getResponse().getContentAsString());
    }

    //Etc. Etc.
}
