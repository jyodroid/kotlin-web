package utils

import components.SongList
import components.SongListProps
import react.RBuilder
import react.ReactElement

//Lambda with receivers
fun RBuilder.songList(handler: SongListProps.() -> Unit): ReactElement {
    return child(SongList::class) {
        this.attrs(handler)
    }
}