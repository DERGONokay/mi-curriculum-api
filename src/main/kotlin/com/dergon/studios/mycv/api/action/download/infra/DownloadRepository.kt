package com.dergon.studios.mycv.api.action.download.infra

import com.dergon.studios.mycv.api.action.download.model.Downloads

interface DownloadRepository {
    fun findByEmail(userEmail: String): Downloads?
    fun save(downloads: Downloads)
}
