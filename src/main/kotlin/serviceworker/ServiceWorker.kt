package serviceworker

import org.w3c.workers.InstallEvent
import org.w3c.workers.ServiceWorkerGlobalScope

const val CACHE_NAME = "paganini-sounds-v1"

val urlsToCache = arrayOf(
    "/",
    "/images/paganini.png"
)

external val self: ServiceWorkerGlobalScope

fun installServiceWorker(){
    self.addEventListener("install", { event ->
        event as InstallEvent
        event.waitUntil(
            self.caches.open(CACHE_NAME)
                .then { it.addAll(urlsToCache) }
        )
    })
}