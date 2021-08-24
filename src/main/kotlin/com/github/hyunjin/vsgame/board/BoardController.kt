package com.github.hyunjin.vsgame.board

import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
class BoardController(
    val repo: BoardRepository
) {

    @GetMapping
    fun getBoards(): ResponseEntity<List<Board>> {
        return ResponseEntity(repo.findAll(), HttpStatus.OK)
    }

    @PostMapping
    fun saveBoard(@RequestBody dto: BoardSaveRequest): ResponseEntity<HttpStatus> {
        val newBoard = Board(id = null, title = dto.title, writer = dto.writer, description = dto.description)
        repo.save(newBoard)
        return if (newBoard.id != null) {
            ResponseEntity(HttpStatus.CREATED)
        } else {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}

data class BoardSaveRequest(
    val title: String,
    val writer: String?,
    val description: String?
)