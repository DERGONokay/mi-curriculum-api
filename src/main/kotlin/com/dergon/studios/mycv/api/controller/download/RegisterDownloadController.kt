package com.dergon.studios.mycv.api.controller.download

import com.dergon.studios.mycv.api.action.download.RegisterDownload
import com.dergon.studios.mycv.api.action.download.RetrieveAllDownloads
import com.dergon.studios.mycv.api.action.download.model.Download
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/download/register")
@CrossOrigin(origins = ["https://damian-lisas-cv.firebaseapp.com/", "https://damian-lisas-cv.firebaseapp.com", "damian-lisas-cv.firebaseapp.com"])
class RegisterDownloadController(private val registerDownload: RegisterDownload,
                                 private val retrieveAllDownloads: RetrieveAllDownloads) {

    @GetMapping
    fun retrieveDownloads(): ResponseEntity<List<DownloadResponse>> {
        return ResponseEntity.ok(retrieveAllDownloads().toDownloadResponses())
    }

    @PostMapping
    fun registerNewDownload(@RequestBody request: RegisterDownloadRequest) {
        registerDownload(request.email, request.docType)
    }

    private fun  List<Download>.toDownloadResponses(): List<DownloadResponse> {
        return this.map { download -> download.toDownloadResponse() }
    }

    private fun Download.toDownloadResponse(): DownloadResponse {
        return DownloadResponse(this.email, this.docType, this.count)
    }
}

data class DownloadResponse(@JsonProperty("email") val email: String,
                            @JsonProperty("type") val type: String,
                            @JsonProperty("count") val count: Int)

data class RegisterDownloadRequest(@JsonProperty("email") val email: String,
                                   @JsonProperty("docType") val docType: String)