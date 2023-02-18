package com.example.websocket.adapters.endpoint

import com.example.websocket.adapters.persistence.SeatReservationRepository
import org.springframework.messaging.core.AbstractMessagingTemplate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ReservationController (
    val seatReservationRepository: SeatReservationRepository,
    val simpleMessagingTemplate: SimpMessagingTemplate
){
    @RequestMapping("/api/v1/reservation/{id}")
    fun reservationSeat(@PathVariable("id") id: Int){
        try{
            seatReservationRepository.reserveSeat(id)
            simpleMessagingTemplate.convertAndSend("/seat", Seat(id, true))
            println("좌석번호 $id 예약 되었습니다")
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    @RequestMapping("/api/v1/reservation/cancel/{id}")
    fun cancelReservation(@PathVariable("id") id: Int){
        seatReservationRepository.cancelReservation(id)
        simpleMessagingTemplate.convertAndSend("/seat", Seat(id, false))
        println("좌석번호 $id 예약 취소 되었습니다")
    }
}

data class Seat(
    val id: Int,
    val hasReservation: Boolean
)