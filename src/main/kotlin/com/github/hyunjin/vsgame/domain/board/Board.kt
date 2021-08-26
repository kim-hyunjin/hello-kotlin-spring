package com.github.hyunjin.vsgame.domain.board

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@SequenceGenerator(
    name = "BOARD_SEQ_GENERATOR",
    sequenceName = "BOARD_SEQ",
    initialValue = 1, allocationSize = 50
)
class Board(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
    @Column(name = "BOARD_ID")
    var id: Long? = null,

    @Column(nullable = false)
    var title: String,
    var writer: String?,
    var description: String?
) {
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JsonIgnore
    var contents: MutableList<Content> = ArrayList()

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    lateinit var createdAt: LocalDateTime

    fun addContent(content: Content) {
        this.contents.add(content)
        if (content.board != this) {
            content.board = this
        }
    }
}

interface BoardRepository : JpaRepository<Board, Long>