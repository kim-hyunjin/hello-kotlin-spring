package com.github.hyunjin.vsgame.domain.board

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@SequenceGenerator(
    name = "CONTENT_SEQ_GENERATOR",
    sequenceName = "CONTENT_SEQ",
    initialValue = 1, allocationSize = 50
)
class Content(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTENT_SEQ_GENERATOR")
    @Column(name = "CONTENT_ID")
    var id: Long? = null,
    var description: String?,
    var photo_url: String?
) {
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    @JsonIgnore
    var board: Board? = null
        set(board) {
            board?.let {
                field = it
                if(!it.contents.contains(this)) {
                    it.contents.add(this)
                }
            }
        }
}