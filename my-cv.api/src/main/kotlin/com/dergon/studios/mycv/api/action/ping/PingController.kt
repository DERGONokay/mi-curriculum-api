package com.dergon.studios.mycv.api.action.ping

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
class PingController {

    @GetMapping
    fun ping(): String {
        return "The API it's working just fine!"
    }
}