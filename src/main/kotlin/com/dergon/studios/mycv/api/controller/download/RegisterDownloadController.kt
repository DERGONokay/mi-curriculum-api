package com.dergon.studios.mycv.api.controller.download

import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/download/register")
class DownloadController(private val registerDownload: RegisterDownload) {

    @PostMapping
    fun registerNewDownload(@RequestBody request: RegisterDownloadRequest) {
        registerDownload(request.email, request.docType)
    }
}


data class RegisterDownloadRequest(@JsonProperty("email") val email: String,
                                   @JsonProperty("docType") val docType: String)