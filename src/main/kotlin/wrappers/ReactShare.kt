@file:JsModule("react-share")
@file:JsNonModule

package wrappers

import react.RClass
import react.RProps

@JsName("EmailIcon")
external val emailIcon: RClass<IconProps>

@JsName("EmailShareButton")
external val emailShareButton: RClass<ShareButtonProps>

@JsName("WhatsappIcon")
external val whatsAppIcon: RClass<IconProps>

@JsName("WhatsappShareButton")
external val whatsAppShareButton: RClass<WhatsAppShareButtonProps>

external interface ShareButtonProps : RProps {
    var url: String
}

external interface WhatsAppShareButtonProps : ShareButtonProps {
    var separator: String
    var title: String
}

external interface IconProps : RProps {
    var size: Int
    var round: Boolean
}
