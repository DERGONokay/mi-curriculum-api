package com.dergon.studios.mycv.api.action.download

interface DownloadRepository {
    fun find(userEmail: String): Downloads?
    fun put(downloads: Downloads)
}
