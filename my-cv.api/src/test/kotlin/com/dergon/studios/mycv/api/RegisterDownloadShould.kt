package com.dergon.studios.mycv.api

import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Downloads
import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.dergon.studios.mycv.api.action.download.model.DocType
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test
import java.time.LocalDate

class RegisterDownloadShould {

    private lateinit var downloadsRepository: DownloadRepository
    private lateinit var registerDownload: RegisterDownload

    @Test
    fun save_the_download() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, DocType.word())

        then(downloadsRepository.find(email)?.count).isGreaterThan(0)
    }

    @Test
    fun increment_the_download_count_if_there_is_a_previous_one() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, DocType.word())
        whenIRegisterADownload(email, DocType.word())

        then(downloadsRepository.find(email)?.count).isEqualTo(2)
    }

    @Test
    fun register_the_first_download_date() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, DocType.word())

        then(downloadsRepository.find(email)?.firstDownload).isEqualTo(today)
    }

    @Test
    fun save_the_doc_type() {
        givenRegisterDownloadAction()

        whenIRegisterADownload(email, DocType.word())

        then(downloadsRepository.find(email)?.docType?.name).isEqualTo(DocType.word().name)
    }

    private fun givenRegisterDownloadAction() {
        mockDownloadsRepository()
        registerDownload = RegisterDownload(downloadsRepository)
    }

    private fun whenIRegisterADownload(email: String, docType: DocType) {
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

    override fun find(userEmail: String): Downloads? {
        return downloads[userEmail]
    }

    override fun put(downloads: Downloads) {
        this.downloads[downloads.email] = downloads
    }

}
