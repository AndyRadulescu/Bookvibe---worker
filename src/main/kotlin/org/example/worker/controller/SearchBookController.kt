package org.example.worker.controller

import org.example.worker.converter.Converter
import org.example.worker.dto.BookVibeVolumeDto
import org.example.worker.dto.GoogleSearchVolumeList
import org.example.worker.service.GoogleSearchBookService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController()
class SearchBookController(private val bookService: Converter) {
    @GetMapping("/worker/books")
    fun searchBook(@RequestParam name: String, @RequestParam page: Int = 1): Mono<List<BookVibeVolumeDto>> {
        println("Log call: /worker/books $name $page")
        return bookService.searchBook(name, page)
    }

    @GetMapping("/worker/volume/{id}")
    fun searchBookById(@PathVariable id: String): Mono<BookVibeVolumeDto> {
        println("Log call: /worker/volume/$id")
        return bookService.searchBookById(id)
    }
}
