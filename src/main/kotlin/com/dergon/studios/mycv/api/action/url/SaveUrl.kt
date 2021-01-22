package com.dergon.studios.mycv.api.action.url

class SaveUrl(private val urlRepository: UrlRepository) {

    operator fun invoke(format: String, url: String) {
        if(isKnownFormat(format)){
            urlRepository.save(format, url)
        } else {
            throw UnsupportedFormatException("The specified format is not supported")
        }
    }

    private fun isKnownFormat(format: String): Boolean {
         return when (format) {
            PDF, DOCX -> true
            else -> false
        }
    }

    companion object {
        private const val PDF = "pdf"
        private const val DOCX = "docx"
    }

}

class UnsupportedFormatException(message: String) : Throwable(message)
