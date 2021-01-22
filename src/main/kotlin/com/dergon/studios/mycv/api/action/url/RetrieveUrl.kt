package com.dergon.studios.mycv.api.action.url

class RetrieveUrl(private val urlRepository: UrlRepository) {

    operator fun invoke(format: String): String {
        return urlRepository.find(format) ?: ""
    }
}
