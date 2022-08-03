package com.example.newproject.ui.api.models.photo

data class PhotosResponse(
    val id: Int,
    val userFilesId: List<Int>,
    val contentType: String,
    val enableRangeProcessing: Boolean,
    val entityTag: Any,
    val fileDownloadName: String,
    val fileName: String,
    val fileProvider: Any,
    val lastModified: Any
)