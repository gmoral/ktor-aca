package com.gm.data.media.dataSource

import com.gm.data.media.model.Media

interface MediaDataSource {

    suspend fun getMediaByIdAndUser(
        mediaId: Int, email: String
    ): Media?

    suspend fun getLikedMediaListOfUser(
        email: String
    ): List<Media>?

    suspend fun getBookmarkedMediaListOfUser(
        email: String
    ): List<Media>?

    suspend fun upsertMediaToUser(
        email: String, media: Media
    ): Boolean

    suspend fun deleteMediaFromUser(
        email: String, mediaId: Int
    ): Boolean


}