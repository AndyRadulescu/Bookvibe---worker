package org.example.worker.service

import org.example.worker.dto.SearchVolumeList
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@RestController
class SearchBookService() {

    @Value("\${books.api.key}")
    private val apiKey: String? = null

    @Value("\${books.api.limit}")
    private val limit: Int = 10

    fun searchBook(bookName: String, page: Int = 1): Mono<SearchVolumeList> {
        val startIndex = 0 + (page - 1) * limit
        val upperLimit = page * limit
        return WebClient.create()
            .get()
            .uri("$GOOGLE_BOOKS_API_URL$SEARCH$bookName&orderBy=relevance&limit=$upperLimit&startIndex=$startIndex&key=$apiKey")
            .retrieve()
            .bodyToMono<SearchVolumeList>()
    }

    fun getBookByVolumeId(volumeId: String): Mono<SearchVolumeList.VolumeDto> {
        return WebClient.create()
            .get()
            .uri("$GOOGLE_BOOKS_API_URL/$volumeId")
            .retrieve()
            .bodyToMono<SearchVolumeList.VolumeDto>()
    }

    companion object {

        private const val GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes"
        private const val SEARCH = "?q="
    }
}
