import kotlinx.css.Color
import react.dom.*
import kotlin.browser.document

fun main() {
    render(document.getElementById("root")) {
        h1 { +"KotlinConf Explorer" }
        div {
            h3 {
                +"Videos to watch"
            }
            p {
                +"John Doe building and breaking things"
            }
            p {
                +"Jane Smith: The development process"
            }
            p {
                +"Matt Miller: The Web 7.0"
            }

            h3 {
                +"Video Watched"
            }
            p {
                +"Tom Jerry: Mouseless development"
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