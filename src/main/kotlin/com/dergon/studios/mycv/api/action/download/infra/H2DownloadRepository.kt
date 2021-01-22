package com.dergon.studios.mycv.api.action.download.infra

import com.dergon.studios.mycv.api.action.download.model.Download
import org.springframework.data.repository.CrudRepository

interface H2DownloadRepository : CrudRepository<Download, String>, DownloadRepository
