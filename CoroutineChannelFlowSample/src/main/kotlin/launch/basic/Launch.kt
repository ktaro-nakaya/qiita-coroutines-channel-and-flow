package launch.basic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val stories = arrayOf(
    "赤ずきんちゃんの４行ストーリ",
    "１．赤ずきんちゃん、おばあさんの家に行く",
    "２．赤ずきんちゃん、オオカミと遭遇、おばあさんオオカミに食べられる",
    "３．赤ずきんちゃん、オオカミに食べられる",
    "４．赤ずきんちゃん、猟師に助けられる"
)

fun main() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        repeat(5) { count ->

            delay(500.toLong())

            println(stories[count])
        }
    }

    // Coroutineが終わるまで待つ
    while (!job.isCompleted) {
        Thread.sleep(100)
    }
}