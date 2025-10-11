package org.example.worker.service

import org.example.worker.dto.GoogleSearchVolumeList
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono

@RestController
class GoogleSearchBookService() {

    @Value("\${books.api.key}")
    private val apiKey: String? = null

    @Value("\${books.api.limit}")
    private val limit: Int = 10

    fun searchBook(bookName: String, page: Int = 1): Mono<GoogleSearchVolumeList> {
        val startIndex = 0 + (page - 1) * limit
        val upperLimit = page * limit
        return WebClient.create()
            .get()
            .uri("$GOOGLE_BOOKS_API_URL$SEARCH$bookName&orderBy=relevance&limit=$upperLimit&startIndex=$startIndex&key=$apiKey")
            .retrieve()
            .bodyToMono<GoogleSearchVolumeList>()
    }

    fun getBookByVolumeId(volumeId: String): Mono<GoogleSearchVolumeList.VolumeDto> {
        return WebClient.create()
            .get()
            .uri("$GOOGLE_BOOKS_API_URL/$volumeId")
            .retrieve()
            .bodyToMono<GoogleSearchVolumeList.VolumeDto>()
    }

    companion object {

        private const val GOOGLE_BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes"
        private const val SEARCH = "?q="
    }
}
