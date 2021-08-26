package com.github.hyunjin.vsgame.domain.board

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BoardSaveService(
    private val boardRepository: BoardRepository,
    private val boardFindService: BoardFindService
) {
    fun save(dto: BoardSaveRequest): Board {
        return if (dto.id == null) {
            boardRepository.save(dto.toEntity())
        } else {
            boardFindService.findById(dto.id).apply {
                this.title = dto.title
                this.writer = dto.writer
                this.description = dto.description
                for (c in dto.contents) {
                    this.addContent(Content(description = c.description, photo_url = c.photo_url))
                }
            }
        }
    }
}