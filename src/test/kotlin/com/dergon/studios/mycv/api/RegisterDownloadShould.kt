package com.dergon.studios.mycv.api

import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Download
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RegisterDownloadShould {

    private lateinit var downloadsRepository: DownloadRepository
    private lateinit var registerDownload: RegisterDownload

    @Test
    fun `save the download`() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.count).isGreaterThan(0)
    }

    @Test
    fun `increment the download count if there is a previous one`() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")
        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.count).isEqualTo(2)
    }

    @Test
    fun `register the first download date`() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.firstDownload).isEqualTo(today)
    }

    @Test
    fun `save the doc type`() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.docType).isEqualTo("docx")
    }

    private fun givenRegisterDownloadAction() {
        mockDownloadsRepository()
        registerDownload = RegisterDownload(downloadsRepository)
    }

    private fun whenIRegisterADownload(email: String, docType: String) {
        registerDownload(email, docType)
    }

    private fun mockDownloadsRepository() {
        downloadsRepository = InMemoryDownloadRepository()

    }
    companion object {
        const val email = "example@email.com"
        val today: LocalDate = LocalDate.now()
    }
}

class InMemoryDownloadRepository : DownloadRepository {

    private val downloads = mutableMapOf<String, Download>()

    override fun findByEmail(userEmail: String): Download? {
        return downloads[userEmail]
    }

    override fun save(download: Download) {
        this.downloads[download.email] = download
    }

    override fun findAll(): List<Download> {
        return downloads.values.toList()
    }

}
