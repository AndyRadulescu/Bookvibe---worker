package org.example.worker.controller

import org.example.worker.dto.SearchVolumeList
import org.example.worker.service.SearchBookService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController()
class SearchBookController(private val bookService: SearchBookService) {
    @GetMapping("/worker/books")
    fun searchBook(@RequestParam name: String, @RequestParam page: Int = 1): Mono<SearchVolumeList> {
        println("Log call: /worker/books $name $page")
        return bookService.searchBook(name, page)
    }

    @GetMapping("/worker/volume/{id}")
    fun searchBookById(@PathVariable id: String): Mono<SearchVolumeList> {
        println("Log call: /worker/volume/$id")
        return bookService.getBookByVolumeId(id)
    }
}
