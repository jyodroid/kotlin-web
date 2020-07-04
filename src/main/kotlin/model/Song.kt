package model

import react.RProps

data class Song(val id: Int, val title: String, val interpreter: String, val videoUrl: String)

external interface SongListProps: RProps{
    var songs: List<Song>
}