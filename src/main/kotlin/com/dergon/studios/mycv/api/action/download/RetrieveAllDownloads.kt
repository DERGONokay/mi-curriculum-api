package com.dergon.studios.mycv.api.action.download

import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.download.model.Download

class RetrieveAllDownloads(private val downloadRepository: DownloadRepository) {
    operator fun invoke(): List<Download> {
        return downloadRepository.findAll()
    }

}
