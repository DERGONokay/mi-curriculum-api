package com.dergon.studios.mycv.api.action.download.infra

import com.dergon.studios.mycv.api.action.download.model.Downloads

interface DownloadRepository {
    fun find(userEmail: String): Downloads?
    fun put(downloads: Downloads)
}
