package com.jyodroid.paganinisounds.controllers

import com.jyodroid.paganinisounds.model.Song
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RestController
class SongsController {
    val unwatchedVideos = listOf(
        Song(1, "Caprice 24", "Alexandrox Xandrox", "https://www.youtube.com/watch?v=CjDz-r65xUU"),
        Song(2, "Caprice 5", "Sumina Studer", "https://www.youtube.com/watch?v=0jXXWBt5URw"),
        Song(3, "La Campanella", "Stefan Milenkovich", "https://www.youtube.com/watch?v=230RgLax-_o")
    )

    val watchedVideos = listOf(
        Song(4, "Allegro maestoso", "Stepan Grytsay", "https://www.youtube.com/watch?v=L1JeOD0xvuc")
    )

    @CrossOrigin(value = ["http://localhost:8080"])
    @GetMapping("/songs")
    fun getSong(@RequestParam(value = "type", defaultValue = "watched") type: String): List<Song> {
        if (type == "unwatched") {
            return unwatchedVideos
        }
        return watchedVideos
    }
}