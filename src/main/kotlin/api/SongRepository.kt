package api

import kotlinext.js.asJsObject
import kotlinx.coroutines.await
import model.Song
import org.w3c.fetch.Response
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
    private suspend fun fetchSongsOnAPI(url: String) =
        window
            .fetch(url)
            .await()
            .json()
            .await()
            .unsafeCast<Array<Song>>()
            .toList()


    suspend fun getSongs(type: Song.Type): List<Song> {
        val url = "$endpoint?type=$type"
        return try {
            window
                .caches
                .match(url)
                .await()
                .let {
                    if (it is Response && it.ok){
                        it.json()
                            .await()
                    }else{
                        console.error("it is not ok response", it)
                        return fetchSongsOnAPI(url)
                    }
                }
                .unsafeCast<Array<Song>>()
                .asList()
        }catch (error: Error){
            console.error("error getting fetch", error)
            return fetchSongsOnAPI(url)
        }
    }
}