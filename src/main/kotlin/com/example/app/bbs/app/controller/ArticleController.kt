package com.example.app.bbs.app.controller

import com.example.app.bbs.app.request.ArticleRequest
import com.example.app.bbs.domain.entity.Article
import com.example.app.bbs.domain.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

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
                articleRequest.title,
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

    @PostMapping("/update")
    fun updateArticle(articleRequest: ArticleRequest) : String {
        if (!articleRepository.existsById(articleRequest.id)) {
            return "redirect:/"
        }

        val article: Article = articleRepository.findById(articleRequest.id).get()

        if (articleRequest.articleKey != article.articleKey) {
            return "redirect:/edit/${articleRequest.id}"
        }

        article.name = articleRequest.name
        article.title = articleRequest.title
        article.contents = articleRequest.contents
        article.updatedAt = Date()

        articleRepository.save(article)

        return "redirect:/"
    }

    @GetMapping("/delete/confirm/{id}")
    fun getDeleteConfirm(@PathVariable id: Int, model: Model) : String {
        if (!articleRepository.existsById(id)) {
            return "redirect:/"
        }

        model.addAttribute("article", articleRepository.findById(id).get())

        return "delete_confirm"
    }
}