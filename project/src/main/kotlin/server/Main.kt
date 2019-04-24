package server

import io.javalin.Javalin

fun main() {
    val app = Javalin.create().start(9090)
    app.get("/") { ctx ->
        ctx.result("Hello World")
    }
}