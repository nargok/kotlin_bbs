package com.example.app.bbs.app.controller

import com.example.app.bbs.app.request.ArticleRequest
import com.example.app.bbs.domain.entity.Article
import com.example.app.bbs.domain.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/edit/{id}")
    fun getArticleEdit(@PathVariable id: Int, model: Model) : String {
        return if (articleRepository.existsById(id)) {
            model.addAttribute("article", articleRepository.findById(id))
            "edit"
        } else {
            "redirect:/"
        }
    }
}