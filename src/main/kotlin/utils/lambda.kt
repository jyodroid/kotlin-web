package utils

import components.SongList
import components.SongListProps
import components.VideoPlayer
import components.VideoPlayerProps
import react.RBuilder
import react.ReactElement

//Lambda with receivers
fun RBuilder.songList(handler: SongListProps.() -> Unit): ReactElement {
    return child(SongList::class) {
        this.attrs(handler)
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}