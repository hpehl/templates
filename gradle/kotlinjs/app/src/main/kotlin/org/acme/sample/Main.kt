package org.acme.sample

import kotlinx.browser.document
import kotlinx.html.a
import kotlinx.html.dom.append
import kotlinx.html.h1
import kotlinx.html.p

external fun require(name: String): dynamic

fun main() {

    require("normalize.css/normalize.css")

    with(document.body!!) {
        append.h1 {
            +"Welcome to Kotlin/JS!"
        }
        append.p {
            +"Fancy joining this year's "
            a("https://kotlinconf.com/") {
                +"KotlinConf"
            }
            +"?"
        }
    }
}
