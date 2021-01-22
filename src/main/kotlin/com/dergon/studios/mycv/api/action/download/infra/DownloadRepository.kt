package com.dergon.studios.mycv.api.action.download.infra

import com.dergon.studios.mycv.api.action.download.model.Download

interface DownloadRepository {
    fun findByEmail(userEmail: String): Download?
    fun save(download: Download)
    fun findAll(): List<Download>
}
