package com.example.app.bbs.app.controller

import com.example.app.bbs.app.request.ArticleRequest
import com.example.app.bbs.domain.entity.Article
import com.example.app.bbs.domain.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ArticleController {
    @Autowired
    lateinit var articleRepository: ArticleRepository

    @GetMapping("/")
    fun getArticleList(model: Model): String {
        model.addAttribute("articles", articleRepository.findAll())
        return "index"
    }

    @PostMapping("/")
    fun registerArticle(
        articleRequest: ArticleRequest
    ): String {
        articleRepository.save(
            Article(
                articleRequest.id,
                articleRequest.name,
                articleRequest.contents,
                articleRequest.articleKey,
            )
        )

        return "redirect:/"
    }
}