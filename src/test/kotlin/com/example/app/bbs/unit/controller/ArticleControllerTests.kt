package com.example.app.bbs.unit.controller

import com.example.app.bbs.app.controller.ArticleController
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class ArticleControllerTests {
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var target: ArticleController

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target).build()
    }

    @Test
    fun getArticleListTest() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/")
        )
            .andExpect(status().isOk)
            .andExpect(view().name("index"))
    }

    @Test
    fun registerArticleTest() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/")
                .param("name", "test")
                .param("title", "test")
                .param("contents", "test")
                .param("articleKey", "test")
        )
            .andExpect(status().is3xxRedirection)
            .andExpect(view().name("redirect:/"))
    }
}