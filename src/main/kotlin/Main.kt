import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main() {
    window.addEventListener("load", {
        window.navigator.serviceWorker
            .register("/service-worker.js")
            .then { console.log("service worker registered") }
            .catch { console.error("Service worker registration failed $it") }
    })

    render(document.getElementById("root")) {
        child(App::class) {}
    }
}