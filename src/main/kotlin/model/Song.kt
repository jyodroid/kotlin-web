package model

data class Song(val id: Int, val title: String, val interpreter: String, val videoUrl: String){
    enum class Type {
        WATCHED, UNWATCHED;

        override fun toString(): String {
            return when (this) {
                WATCHED -> "watched"
                UNWATCHED -> "unwatched"
            }
        }
    }
}