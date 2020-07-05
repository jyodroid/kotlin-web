@file: JsModule("react-player")
@file: JsNonModule

package wrappers
import react.RClass
import react.RProps

//require("react-player").default;
@JsName("default")
//external val reactPlayer: RClass<dynamic> //dynamic tells the compiler to accept whatever we give it
external val reactPlayer: RClass<ReactPlayerProps> //dynamic tells the compiler to accept whatever we give it

//We can make wrappers based on element documentation so
external interface ReactPlayerProps: RProps {
    var url: String
}