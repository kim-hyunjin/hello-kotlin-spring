package com.github.hyunjin.vsgame.domain.board

import javax.persistence.*

@Entity
class Content(
    @Id @GeneratedValue
    @Column(name = "CONTENT_ID")
    var id: Long?,

    @Column(name = "BOARD_ID")
    var boardId: Long?,

    var description: String?,
    var photo_url: String?
)