import api.SongRepository
import components.VideoPlayerProps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.FlexWrap
import kotlinx.css.Float
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.flexWrap
import kotlinx.css.float
import kotlinx.css.fontFamily
import kotlinx.css.minWidth
import kotlinx.css.pct
import model.Song
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1
import react.dom.h3
import react.dom.img
import react.setState
import styled.css
import styled.styledDiv
import styles.PSColors
import utils.songList
import utils.videoPlayer

class App : RComponent<RProps, AppState>() {

    private val songRepository = SongRepository()

    override fun AppState.init() {
        unwatchedVideos = emptyList()
        watchedVideos = emptyList()

        MainScope().launch {
            fetchSongs(Song.Type.UNWATCHED).let {
                setState {
                    unwatchedVideos = it
                }
            }

            fetchSongs(Song.Type.WATCHED).let {
                setState {
                    watchedVideos = it
                }
            }
        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                display = Display.flex
                flexWrap = FlexWrap.wrap
                backgroundColor = PSColors.prussianBlue
                fontFamily = "Lobster"
                color = Color.white
            }

            img {
                attrs {
                    alt = "Paganini"
                    src = "images/paganini.png"
                }
            }
            h1 { +"Paganini Sounds" }
        }
        styledDiv {
            css {
                display = Display.flex
                flexWrap = FlexWrap.wrap
            }
            styledDiv {
                h1 { +"Paganini music Explorer" }
                css {
                    minWidth = 40.pct
                    float = Float.left
                    backgroundColor = PSColors.honeydew
                }
                h3 {
                    +"Videos to watch"
                }
                songList {
                    songs = state.unwatchedVideos
                    selectedSong = state.currentSong
                    onSelectSong = onSongSelectedListener()
                }
//                Without lambda
//                child(SongList::class) {
//                    attrs.songs = unwatchedVideos
//                }

                h3 {
                    +"Video watched"
                }
                songList {
                    songs = state.watchedVideos
                    selectedSong = state.currentSong
                    onSelectSong = onSongSelectedListener()
                }
            }
            state.currentSong?.let {
                videoPlayer {
                    song = it
                    unwatchedVideo = it in state.unwatchedVideos
                    onWatchedButtonPressed = onWatchedButtonPressedListener()
                }
            }
        }
    }

    private fun VideoPlayerProps.onWatchedButtonPressedListener(): (Song) -> Unit {
        return { song ->
            if (song in state.unwatchedVideos) {
                setState {
                    unwatchedVideos -= song
                    watchedVideos += song
                }
            } else {
                setState {
                    unwatchedVideos += song
                    watchedVideos -= song
                }
            }
        }
    }

    private suspend fun fetchSongs(type: Song.Type): List<Song> = coroutineScope {
        withContext(Dispatchers.Default) {
            songRepository.getSongs(type)
        }
    }

    private fun onSongSelectedListener(): (Song) -> Unit {
        return { song ->
            setState {
                currentSong = song
            }
        }
    }
}

external interface AppState : RState {
    var currentSong: Song?
    var unwatchedVideos: List<Song>
    var watchedVideos: List<Song>
}