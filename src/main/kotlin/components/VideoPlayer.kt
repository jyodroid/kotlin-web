package components

import kotlinx.css.Color
import kotlinx.css.Display
import kotlinx.css.Float
import kotlinx.css.backgroundColor
import kotlinx.css.color
import kotlinx.css.display
import kotlinx.css.float
import kotlinx.css.marginBottom
import kotlinx.css.minWidth
import kotlinx.css.pct
import kotlinx.css.px
import kotlinx.html.js.onClickFunction
import model.Song
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h3
import styled.css
import styled.styledButton
import styled.styledDiv
import styles.PSColors
import wrappers.emailIcon
import wrappers.emailShareButton
import wrappers.reactPlayer
import wrappers.whatsAppIcon
import wrappers.whatsAppShareButton

class VideoPlayer : RComponent<VideoPlayerProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
//            we can use props to adjust CSS properties
            css {
                float = Float.left
                minWidth = 60.pct
                backgroundColor = PSColors.powderBlue
            }

            h3 {
                +"${props.song.interpreter}: ${props.song.title}"
            }

            styledDiv {
                css {
                    display = Display.flex
                    marginBottom = 10.px
                }

                emailShareButton{
                    attrs.url = props.song.videoUrl
                    emailIcon{
                        attrs.size = 32
                        attrs.round = true
                    }
                }

                whatsAppShareButton{
                    attrs.apply{
                        url = props.song.videoUrl
                        separator = "->"
                        title = "Cool Paganini Video"
                    }
                    whatsAppIcon{
                        attrs.size = 32
                        attrs.round = true
                    }
                }
            }

            styledButton {
                css{
                    float = Float.left
                    backgroundColor = if(props.unwatchedVideo) PSColors.prussianBlue else PSColors.imperialRed
                    color = Color.white
                }

                attrs{
                    onClickFunction = {props.onWatchedButtonPressed(props.song)}
                }

                if(props.unwatchedVideo) {
                    +"Mark as watched"
                }
                else {
                    +"Mark as unwatched"
                }
            }

            reactPlayer{
                attrs.url = props.song.videoUrl
            }
//            img {
//                attrs {
//                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
//                }
//            }
        }
    }

}

external interface VideoPlayerProps : RProps {
    var song: Song
    var onWatchedButtonPressed: (Song) -> Unit
    var unwatchedVideo: Boolean
}