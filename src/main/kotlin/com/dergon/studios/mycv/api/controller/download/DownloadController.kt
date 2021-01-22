package com.dergon.studios.mycv.api.controller.download

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/download/link")
class DownloadController {

    @GetMapping
    fun getDownloadLink(): ResponseEntity<String> {
        return ResponseEntity.ok("www.cv-download-url")
    }
}