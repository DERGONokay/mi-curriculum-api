package com.dergon.studios.mycv.api.action.download.model

import java.time.LocalDate

data class Downloads(val email: String, var count: Int, val firstDownload: LocalDate)