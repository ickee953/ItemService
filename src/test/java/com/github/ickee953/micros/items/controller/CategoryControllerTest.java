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
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Sql("/sql/categories.sql")
    @Test
    void get_RequestIsValid_ReturnedJSON() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/category"))
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                    {
                                        "id" : "f393ef48-4d7a-49ed-8ced-ea99ce59a76e",
                                        "title" : "test category title",
                                        "parentCategory": null
                                    },
                                    {
                                        "id" : "21f6f129-0ec6-4d7c-b569-548e72bb0373",
                                        "title" : "test category title 2",
                                        "parentCategory": null
                                    }
                                ]
                                """)
                );
    }

}