package api

import kotlinx.coroutines.await
import model.Song
import kotlin.browser.window

class SongRepository {
    private val endpoint = "http://localhost:8081/songs"
//    private val requestInit = RequestInit(mode = RequestMode.NO_CORS)
// Vervose version
//suspend fun getSongs(type: Song.Type): List<Song> {
//    val url = "$endpoint?type=$type"
//    val responsePromise = window.fetch(url)
//    val response = responsePromise.await()
//    console.log("RESPONSE")
//    console.log(response)
//    val jsonPromise = response.json()
//    val json = jsonPromise.await()
//    console.log(json)
//    return json.unsafeCast<List<Song>>()
//}

    // A coroutine to fetch multiple song concurrently
//    suspend fun fetchVideos(): List<Video> = coroutineScope {
//        (1..25).map { id ->
//            async {
//                fetchVideo(id)
//            }
//        }.awaitAll()
//    }

    // compacted version
    suspend fun getSongs(type: Song.Type) =
        window
            .fetch("$endpoint?type=$type")
            .await()
            .json()
            .await()
            .unsafeCast<Array<Song>>()
            .toList()
}