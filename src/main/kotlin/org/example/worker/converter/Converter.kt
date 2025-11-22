package org.example.worker.converter

import org.example.worker.dto.BookVibeVolumeDto
import org.example.worker.dto.GoogleSearchVolumeList
import org.example.worker.dto.GoogleSearchVolumeList.VolumeDto.VolumeInfoDto.ImageLinks
import org.example.worker.service.GoogleSearchBookService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class Converter(
    private val bookService: GoogleSearchBookService
) {
    fun searchBook(bookName: String, page: Int = 1): Mono<List<BookVibeVolumeDto>> {
        return bookService.searchBook(bookName, page).map { it.items.map { el -> convertToBookVibeDto(el) } }
    }

    fun searchBookById(id: String): Mono<BookVibeVolumeDto> {
        return bookService.getBookByVolumeId(id).map { convertToBookVibeDto(it) }
    }

    private fun convertToBookVibeDto(it: GoogleSearchVolumeList.VolumeDto): BookVibeVolumeDto {
        return BookVibeVolumeDto(
            id = it.id,
            title = it.volumeInfo.title,
            subtitle = it.volumeInfo.subtitle,
            authors = it.volumeInfo.authors ?: emptyList(),
            publisher = it.volumeInfo.publisher,
            publishedDate = it.volumeInfo.publishedDate,
            description = it.volumeInfo.description,
            industryIdentifiers = it.volumeInfo.industryIdentifiers ?: emptyList(),
            pageCount = it.volumeInfo.pageCount ?: 0,
            categories = it.volumeInfo.categories,
            averageRating = it.volumeInfo.averageRating,
            ratingCount = it.volumeInfo.ratingCount,
            maturityRating = it.volumeInfo.maturityRating,
            imageLink = setImageLink(it.volumeInfo.imageLinks),
            language = it.volumeInfo.language

        )
    }

    private fun setImageLink(imageLinks: ImageLinks?): String {
        return imageLinks?.large ?: imageLinks?.extraLarge ?: imageLinks?.medium ?: imageLinks?.small
        ?: imageLinks?.thumbnail ?: ""
    }
}
