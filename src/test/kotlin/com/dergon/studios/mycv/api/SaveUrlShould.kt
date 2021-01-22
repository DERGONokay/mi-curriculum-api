package com.dergon.studios.mycv.api

import com.dergon.studios.mycv.api.action.url.InMemoryUrlRepository
import com.dergon.studios.mycv.api.action.url.SaveUrl
import com.dergon.studios.mycv.api.action.url.UnsupportedFormatException
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SaveUrlShould {

    private lateinit var saveUrl: SaveUrl
    private lateinit var urlRepository: InMemoryUrlRepository

    @Test
    fun `persist the url`() {
        givenSaveUrl()

        whenSavingTheUrl(format = "docx", url = "www.urltosave.com")

        then(urlRepository.find("docx")).isNotNull()
    }

    @Test
    fun `save only known formats`() {
        givenSaveUrl()

        assertThrows<UnsupportedFormatException> {
            whenSavingTheUrl("pepe", "www.someurl.com")
        }
    }

    private fun whenSavingTheUrl(format: String, url: String) {
        saveUrl(format, url)
    }

    private fun givenSaveUrl() {
        urlRepository = InMemoryUrlRepository()
        saveUrl = SaveUrl(urlRepository)
    }
}