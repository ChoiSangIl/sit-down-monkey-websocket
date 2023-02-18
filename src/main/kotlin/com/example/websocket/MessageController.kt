package com.example.websocket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller


@Controller
class MessageController() {
    @MessageMapping("/send")
    @SendTo("/topic")
    fun chattingHandler(): String{
        return "test"
    }
}