package com.dergon.studios.mycv.api.action.download.infra

import com.dergon.studios.mycv.api.action.download.model.Downloads
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository("H2DownloadRepository")
interface H2DownloadRepository : CrudRepository<Downloads, String>, DownloadRepository
