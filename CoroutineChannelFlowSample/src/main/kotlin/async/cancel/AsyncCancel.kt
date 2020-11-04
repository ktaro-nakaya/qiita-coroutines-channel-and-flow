package async.cancel

import kotlinx.coroutines.*

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

            val deffer = async {
                // 2回目でキャンセル
                if (count == 2) {
                    cancel("キャンセル")
                }
                delay(500)
                launch.basic.stories[count]
            }

            println("${count + 1}回目のasyncが終わるまで待つ")
            deffer.await()
            println(deffer.getCompleted())
        }
    }

    // Coroutineが終わるまで待つ
    while (!job.isCompleted || !job.isCancelled) {
        Thread.sleep(100)
    }
}