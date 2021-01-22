package com.dergon.studios.mycv.api

import com.dergon.studios.mycv.api.action.url.InMemoryUrlRepository
import com.dergon.studios.mycv.api.action.url.RetrieveUrl
import com.dergon.studios.mycv.api.action.url.UrlRepository
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class RetrieveUrlShould {

    private lateinit var retrieveUrl: RetrieveUrl

    private lateinit var retrievedUrl: String
    private lateinit var urlRepository: UrlRepository

    @Test
    fun `retrieve the url by format`() {
        givenUrlRepository()
        givenRetrieveUrlAction()
        givenTheSavedUrl("pdf", PDF_URL)

        whenRetrievingTheUrlWith(format = "pdf")

        then(retrievedUrl).isEqualTo(PDF_URL)
    }

    @Test
    fun `return empty if the format is not supported`() {
        givenUrlRepository()
        givenRetrieveUrlAction()

        whenRetrievingTheUrlWith(format = "txt")

        then(retrievedUrl).isEqualTo(EMPTY_STRING)
    }

    private fun givenRetrieveUrlAction() {
        retrieveUrl = RetrieveUrl(urlRepository)
    }

    private fun givenUrlRepository() {
        urlRepository = InMemoryUrlRepository()
    }


    private fun givenTheSavedUrl(format: String, url: String) {
        urlRepository.save(format, url)
    }

    private fun whenRetrievingTheUrlWith(format: String) {
        retrievedUrl = retrieveUrl(format)
    }

    companion object {
        private const val EMPTY_STRING = ""
        private const val PDF_URL = "www.pdf-url.com"
    }
}