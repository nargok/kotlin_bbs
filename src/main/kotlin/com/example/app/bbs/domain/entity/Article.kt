package com.example.app.bbs.domain.entity

import java.util.*
import javax.persistence.*

@Entity
data class Article(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var name: String = "",
    var title: String = "",
    var contents: String = "",
    @Column(name = "article_key")
    var articleKey: String = "",
    @Column(name = "registerd_at")
    var registeredAt: Date = Date(),
    @Column(name = "updated_at")
    var updatedAt: Date = Date(),
)
