import kotlinx.css.Position
import kotlinx.css.position
import kotlinx.css.px
import kotlinx.css.right
import kotlinx.css.top
import react.dom.div
import react.dom.h1
import react.dom.h3
import react.dom.img
import react.dom.p
import react.dom.render
import styled.css
import styled.styledDiv
import kotlin.browser.document

val unwatchedVideos = listOf(
    Song(1, "Caprice 24", "Alexandrox Xandrox", "https://www.youtube.com/watch?v=CjDz-r65xUU"),
    Song(2, "Caprice 5", "Sumina Studer", "https://www.youtube.com/watch?v=0jXXWBt5URw"),
    Song(3, "La Campanella", "Stefan Milenkovich", "https://www.youtube.com/watch?v=230RgLax-_o")
)
val watchedVideos = listOf(
    Song(4, "Allegro maestoso", "Alexandru Sura", "https://youtu.be/PsaFVLr8t4E")
)

fun main() {
    render(document.getElementById("root")) {
        h1 { +"Paganini music Explorer" }
        styledDiv {
            css{
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"Videos to watch"
            }
            for (song in unwatchedVideos){
                p {
                    +"${song.interpreter}: ${song.title}"
                }
            }

            h3 {
                +"Video Watched"
            }
            for (song in watchedVideos){
                p {
                    +"${song.interpreter}: ${song.title}"
                }
            }
        }
        div {
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
    document.bgColor = "#f1faee"
}

data class Song(val id: Int, val title: String, val interpreter: String, val videoUrl: String)