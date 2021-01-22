package com.dergon.studios.mycv.api.controller.download

import com.dergon.studios.mycv.api.action.url.RetrieveUrl
import com.dergon.studios.mycv.api.action.url.SaveUrl
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/download/link")
@CrossOrigin(origins = ["https://damian-lisas-cv.firebaseapp.com/", "https://damian-lisas-cv.firebaseapp.com", "damian-lisas-cv.firebaseapp.com"])
class DownloadController(private val retrieveUrl: RetrieveUrl, private val saveUrl: SaveUrl) {

    @GetMapping("/{FORMAT}")
    fun getDownloadLink(@PathVariable("FORMAT") format: String): ResponseEntity<String> {
        return ResponseEntity.ok(retrieveUrl(format))
    }

    @PostMapping
    fun saveDownloadLink(@RequestBody requestBody: DownoadLinkRequest) {
        saveUrl(requestBody.format, requestBody.url)
    }
}

data class DownoadLinkRequest(@JsonProperty("format") val format: String,
                              @JsonProperty("url") val url: String)