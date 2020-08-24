package com.dergon.studios.mycv.api.action.download

import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Downloads
import java.time.LocalDate

class RegisterDownload(private val downloadRepository: DownloadRepository) {

    operator fun invoke(email: String) {
        val downloads = previousDownloads(email)

        downloads.incrementDownloadCount()

        downloadRepository.put(downloads)
    }

    private fun previousDownloads(email: String) = downloadRepository.find(email) ?: Downloads(email, 0, LocalDate.now())
}

private fun Downloads.incrementDownloadCount() {
    count = count.plus(1)
}
