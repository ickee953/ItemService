package com.github.ickee953.micros.items.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Sql("/sql/items.sql")
    @Test
    void get_RequestIsValid_ReturnedJSON() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/item"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                    {
                                        "id" : "8e96ebdb-82ea-48e4-916d-940998991a31",
                                        "title" : "test title",
                                        "description" : "test description",
                                        "categories" : []
                                    },
                                    {
                                        "id" : "b3ff8b40-ac34-499a-bad1-23ed02d2cd89",
                                        "title" : "test title 2",
                                        "description" : "test description 2",
                                        "categories": []
                                    }
                                ]
                                """)
                );

    }

}