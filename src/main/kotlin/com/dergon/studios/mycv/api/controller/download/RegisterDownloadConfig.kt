package com.dergon.studios.mycv.api.controller.download

import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.dergon.studios.mycv.api.action.download.infra.DownloadRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RegisterDownloadConfig {

    @Bean
    fun registerDownload(downloadRepository: DownloadRepository): RegisterDownload {
        return RegisterDownload(downloadRepository)
    }
}