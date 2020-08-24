package com.dergon.studios.mycv.api.action.download.model

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Downloads(@Id val email: String,
                     var count: Int,
                     var docType: String,
                     val firstDownload: LocalDate)
