package components

import model.SongListProps
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.p

class SongList : RComponent<SongListProps, RState>() {
    override fun RBuilder.render() {
        for (song in props.songs) {
            p {
                +"${song.interpreter}: ${song.title}"
            }
        }
    }
}