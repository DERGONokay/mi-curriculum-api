package com.dergon.studios.mycv.api

import com.dergon.studios.mycv.api.action.download.RetrieveAllDownloads
import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Download
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RetrieveAllDownloadsShould {

    private lateinit var retrieveAllDownloads: RetrieveAllDownloads

    private lateinit var downloadRepository: DownloadRepository
    private lateinit var retrievedDownloads: List<Download>

    @Test
    fun `retrieve all existing downloads`() {
        givenRetrieveAllDownloads()
        givenPreviousDownloads()

        whenRetrievingAllDownloads()

        then(retrievedDownloads.count()).isEqualTo(3)
    }

    private fun givenPreviousDownloads() {
        downloadRepository.save(Download("user@email.com", 3, "pdf", LocalDate.now()))
        downloadRepository.save(Download("user2@email.com", 6, "docx", LocalDate.now()))
        downloadRepository.save(Download("user3@email.com", 1, "pdf", LocalDate.now()))
    }

    private fun givenRetrieveAllDownloads() {
        downloadRepository = InMemoryDownloadRepository()
        retrieveAllDownloads = RetrieveAllDownloads(downloadRepository)
    }

    private fun whenRetrievingAllDownloads() {
        retrievedDownloads = retrieveAllDownloads()
    }
}