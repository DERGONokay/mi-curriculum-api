package com.dergon.studios.mycv.api.action.download

import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Download
import java.time.LocalDate

class RegisterDownload(private val downloadRepository: DownloadRepository) {

    operator fun invoke(email: String, docType: String) {
        val downloads = previousDownloads(email, docType)

        downloads.incrementDownloadCount()

        downloadRepository.save(downloads)
    }

    private fun previousDownloads(email: String, docType: String): Download
            = downloadRepository.findByEmail(email) ?: Download(email, 0, docType, LocalDate.now())
}

private fun Download.incrementDownloadCount() {
    count = count.plus(1)
}
