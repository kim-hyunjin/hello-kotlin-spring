package com.github.hyunjin.vsgame.domain.board

data class BoardSaveRequest(
    val id: Long?,
    val title: String,
    val writer: String?,
    val description: String?,
    val contents: List<ContentSaveRequest>
) {
    fun toEntity(): Board {
        val board = Board(
            id = this.id,
            title = this.title,
            writer = this.writer,
            description = this.description
        )
        for (c in this.contents) {
            board.addContent(Content(description = c.description, photo_url = c.photo_url))
        }
        return board
    }
}

data class ContentSaveRequest(
    val description: String,
    val photo_url: String?
)