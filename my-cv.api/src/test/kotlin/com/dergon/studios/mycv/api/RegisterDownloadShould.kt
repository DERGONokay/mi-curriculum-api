package com.dergon.studios.mycv.api

import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Downloads
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RegisterDownloadShould {

    private lateinit var downloadsRepository: DownloadRepository
    private lateinit var registerDownload: RegisterDownload

    @Test
    fun save_the_download() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.count).isGreaterThan(0)
    }

    @Test
    fun increment_the_download_count_if_there_is_a_previous_one() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")
        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.count).isEqualTo(2)
    }

    @Test
    fun register_the_first_download_date() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, "docx")

        then(downloadsRepository.findByEmail(email)?.firstDownload).isEqualTo(today)
    }

    @Test
    fun save_the_doc_type() {
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

    private val downloads = mutableMapOf<String, Downloads>()

    override fun findByEmail(userEmail: String): Downloads? {
        return downloads[userEmail]
    }

    override fun save(downloads: Downloads) {
        this.downloads[downloads.email] = downloads
    }

}
