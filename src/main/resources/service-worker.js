'use strict';

//In case of worker update this should be update to v2
const CACHE_NAME = "paganini-sounds-v3"
const DATA_CACHE_NAME = 'paganini-sounds-data-cache-v1';

const FILES_TO_CACHE = [
    '/',
    '/index.html',
    '/paganinisounds.js',
    '/images/paganini.png',
    '/manifest.json',
    '/install.js'
]

self.addEventListener('install', (evt) => {
    console.log('[ServiceWorker] Install');
    evt.waitUntil(
        caches.open(CACHE_NAME).then((cache) => {
            console.log('[ServiceWorker] Pre-caching offline page');
            return cache.addAll(FILES_TO_CACHE);
        })
    );

    self.skipWaiting();
});

//Clean up offline, ensure the service worker updates
self.addEventListener('activate', (evt) => {
    console.log('[ServiceWorker] Activate');

    evt.waitUntil(
        caches.keys().then((keyList) => {
            return Promise.all(keyList.map((key) => {
                if (key !== CACHE_NAME && key !== DATA_CACHE_NAME) {
                    console.log('[ServiceWorker] Removing old cache', key);
                    return caches.delete(key);
                }
            }));
        })
    );

    self.clients.claim();
});

self.addEventListener('fetch', (evt) => {
    console.log('[ServiceWorker] Fetch', evt.request.url);

    if (evt.request.url.includes('/songs')) {
        console.log('[Service Worker] Fetch (data)', evt.request.url);
        evt.respondWith(
            caches.open(DATA_CACHE_NAME).then((cache) => {
                return fetch(evt.request)
                    .then((response) => {
                        // If the response was good, clone it and store it in the cache.
                        if (response.status === 200) {
                            cache.put(evt.request.url, response.clone());
                        }
                        return response;
                    }).catch((error) => {
                        console.error("cant fetch data from network, getting from cache", error)
                        // Network request failed, try to get it from the cache.
                        return cache.match(evt.request);
                    });
            }));
        return;
    }
    evt.respondWith(
        caches.open(CACHE_NAME).then((cache) => {
            return cache.match(evt.request)
                .then((response) => {
                    return response || fetch(evt.request);
                });
        })
    );
});