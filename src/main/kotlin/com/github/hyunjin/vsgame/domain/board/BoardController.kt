package com.github.hyunjin.vsgame.domain.board

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/board")
class BoardController(
    private val service: BoardService
) {

    @GetMapping
    fun getBoards(): ResponseEntity<List<Board>> {
        return ResponseEntity(service.findAll(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getBoardById(id: Long): ResponseEntity<Board> {
        val board = service.getBoardById(id)
        return if (board != null) {
            ResponseEntity(board, HttpStatus.OK)
        } else {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun saveBoard(@RequestBody dto: BoardSaveRequest): ResponseEntity<HttpStatus> {
        return if (service.saveBoard(dto)) {
            ResponseEntity(HttpStatus.CREATED)
        }else {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}

data class BoardSaveRequest(
    val title: String,
    val writer: String?,
    val description: String?,
    val contents: List<Content>
) {
    fun toEntity() = Board(
        title = this.title,
        writer = this.writer,
        description = this.description,
        contents = this.contents
    )
}