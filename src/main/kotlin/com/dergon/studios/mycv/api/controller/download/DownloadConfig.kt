package com.dergon.studios.mycv.api.controller.download

import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.dergon.studios.mycv.api.action.download.RetrieveAllDownloads
import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import com.dergon.studios.mycv.api.action.url.InMemoryUrlRepository
import com.dergon.studios.mycv.api.action.url.RetrieveUrl
import com.dergon.studios.mycv.api.action.url.SaveUrl
import com.dergon.studios.mycv.api.action.url.UrlRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DownloadConfig {

    @Bean
    fun registerDownload(downloadRepository: DownloadRepository): RegisterDownload {
        return RegisterDownload(downloadRepository)
    }

    @Bean
    fun retrieveAllDownloads(downloadRepository: DownloadRepository): RetrieveAllDownloads {
        return RetrieveAllDownloads(downloadRepository)
    }

    @Bean
    fun retrieveUrl(urlRepository: UrlRepository): RetrieveUrl {
        return RetrieveUrl(urlRepository)
    }

    @Bean
    fun saveUrl(urlRepository: UrlRepository): SaveUrl {
        return SaveUrl(urlRepository)
    }

    @Bean
    fun urlRepository(): UrlRepository {
        return InMemoryUrlRepository()
    }
}