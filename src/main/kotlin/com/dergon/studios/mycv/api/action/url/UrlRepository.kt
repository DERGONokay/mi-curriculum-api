package com.dergon.studios.mycv.api.action.url

interface UrlRepository {
    fun find(format: String): String?
    fun save(format: String, url: String)
}

class InMemoryUrlRepository: UrlRepository {

    private val urls: MutableMap<String, String> = mutableMapOf()

    override fun find(format: String): String? {
        return urls[format]
    }

    override fun save(format: String, url: String) {
        urls[format] = url
    }
}

