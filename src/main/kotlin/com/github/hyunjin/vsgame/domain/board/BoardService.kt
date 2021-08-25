package com.github.hyunjin.vsgame.domain.board

import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class BoardService(private val repo: BoardRepository) {

    fun findAll(): List<Board> {
        return repo.findAll()
    }

    fun getBoardById(id: Long): Board? {
        val board = repo.findById(id)
        return if (board.isPresent) {
            board.get()
        } else {
            null
        }
    }

    fun saveBoard(@RequestBody dto: BoardSaveRequest): Boolean {
        val board = dto.toEntity()
        repo.save(board)
        return board.id != null
    }
}