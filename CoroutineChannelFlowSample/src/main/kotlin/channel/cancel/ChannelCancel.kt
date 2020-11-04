package channel.cancel


import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val stories = arrayOf(
    "赤ずきんちゃんの４行ストーリ",
    "１．赤ずきんちゃん、おばあさんの家に行く",
    "２．赤ずきんちゃん、オオカミと遭遇、おばあさんオオカミに食べられる",
    "３．赤ずきんちゃん、オオカミに食べられる",
    "４．赤ずきんちゃん、猟師に助けられる"
)

fun main() = runBlocking {
    // Channel生成（Stringのみを送受信できる）
    val channel = Channel<String>()

    // 非同期処理の起動
    launch {
        stories.forEach {

            // Channelに文字列を送信
            channel.send(it)
            println("送信：$it")
            // 1行待ち合わせ
            delay(500)

            // 途中で中断
            if (stories.indexOf(it) == 2) {
                channel.cancel()
            }
        }
    }

    // Channelに５回送信されるため５回繰り返す
    repeat(5) {
        // Channelから受信
        val story = channel.receive()
        println("受信：$story")
    }
    println("おわり")
}
