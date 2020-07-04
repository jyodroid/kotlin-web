import kotlinx.css.Display
import kotlinx.css.GridTemplateColumns
import kotlinx.css.backgroundColor
import kotlinx.css.display
import kotlinx.css.gridTemplateColumns
import kotlinx.css.pct
import model.Song
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1
import react.dom.h3
import react.dom.img
import styles.PSColors
import styled.css
import styled.styledDiv
import utils.songList

val unwatchedVideos = listOf(
    Song(1, "Caprice 24", "Alexandrox Xandrox", "https://www.youtube.com/watch?v=CjDz-r65xUU"),
    Song(2, "Caprice 5", "Sumina Studer", "https://www.youtube.com/watch?v=0jXXWBt5URw"),
    Song(3, "La Campanella", "Stefan Milenkovich", "https://www.youtube.com/watch?v=230RgLax-_o")
)

val watchedVideos = listOf(
    Song(4, "Allegro maestoso", "Alexandru Sura", "https://youtu.be/PsaFVLr8t4E")
)
class App : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                backgroundColor = PSColors.prussianBlue
            }

            img {
                attrs {
                    alt = "Paganini"
                    src = "./images/paganini.jpg"
                }
            }
        }
        styledDiv {
            css {
                display = Display.grid
                gridTemplateColumns = GridTemplateColumns(30.pct, 70.pct)
            }
            styledDiv {
                h1 { +"Paganini music Explorer" }
                css {
                    backgroundColor = PSColors.honeydew
                }
                h3 {
                    +"Videos to watch"
                }
                songList { songs = unwatchedVideos }
//                Without lambda
//                child(SongList::class) {
//                    attrs.songs = unwatchedVideos
//                }

                h3 {
                    +"Video Watched"
                }
                songList { songs = watchedVideos }
            }
            styledDiv {
                css {
                    backgroundColor = PSColors.powderBlue
                }

                h3 {
                    +"John Doe: Building and breaking things"
                }

                img {
                    attrs {
                        src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                    }
                }
            }
        }
    }
}