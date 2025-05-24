package org.example.worker.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.worker.dto.SearchVolumeList
import org.example.worker.service.SearchBookService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController()
class SearchBookController(private val bookService: SearchBookService, private val mapper: ObjectMapper) {
    @GetMapping("/worker/books")
    fun searchBook(@RequestParam name: String, @RequestParam page: Int = 1): Mono<SearchVolumeList> {
        return bookService.searchBook(name, page)
    }

    @GetMapping("/worker/volume/{id}")
    fun searchBookById(@PathVariable id: String): Mono<SearchVolumeList> {
        return bookService.getBookByVolumeId(id)
    }
}
