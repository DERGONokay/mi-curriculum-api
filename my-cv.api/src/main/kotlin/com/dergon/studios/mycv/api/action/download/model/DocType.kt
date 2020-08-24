package com.dergon.studios.mycv.api.action.download.model

abstract class DocType(val name: String, val extension: String) {

    companion object {
        fun word(): DocType {
            return Word("WORD", "docx")
        }

        fun pdf(): DocType {
            return Pdf("PDF", "pdf")
        }
    }
}

class Word(name: String, extension: String): DocType(name, extension)
class Pdf(name: String, extension: String): DocType(name, extension)

