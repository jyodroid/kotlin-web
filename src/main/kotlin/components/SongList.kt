package components

import kotlinx.html.js.onClickFunction
import model.Song
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.p
import react.setState

class SongList : RComponent<SongListProps, RState>() {
    override fun RBuilder.render() {
        for (song in props.songs) {
            p {
                key = song.id.toString()
                attrs {
                    onClickFunction = {
                        props.onSelectSong(song)
                    }
                }
                if (song == props.selectedSong) {
                    +"▶ "
                }

                +"${song.interpreter}: ${song.title}"
            }
        }
    }
}

external interface SongListProps: RProps {
    var songs: List<Song>
    var selectedSong: Song?
    var onSelectSong: (Song) -> Unit
}