package com.github.hyunjin.vsgame.domain.board

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
class Board(
    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    var id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @OneToMany(targetEntity = Content::class, fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "BOARD_ID")
    var contents: List<Content>,

    var writer: String?,
    var description: String?
)

interface BoardRepository : JpaRepository<Board, Long>