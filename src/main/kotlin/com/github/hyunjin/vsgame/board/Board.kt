package com.github.hyunjin.vsgame.board

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Board(
    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    var id: Long?,
    @Column(nullable = false)
    var title: String,
    var writer: String?,
    var description: String?
)

interface BoardRepository: JpaRepository<Board, Long>