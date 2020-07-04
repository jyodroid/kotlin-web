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

class SongList : RComponent<SongListProps, SongListState>() {
    override fun RBuilder.render() {
        for (song in props.songs) {
            p {
                key = song.id.toString()
                attrs {
                    onClickFunction = onSongSelectedListener(song)
                }
                if (song == state.selectedSong) {
                    +"▶ "
                }

                +"${song.interpreter}: ${song.title}"
            }
        }
    }

    private fun onSongSelectedListener(song: Song): (event: Event) -> Unit = {
        setState {
            selectedSong = song
        }
    }
}

external interface SongListProps: RProps {
    var songs: List<Song>
}

external interface SongListState: RState {
    var selectedSong: Song?
}