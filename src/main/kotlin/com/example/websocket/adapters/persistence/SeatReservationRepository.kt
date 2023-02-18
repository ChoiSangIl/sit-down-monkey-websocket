package com.example.websocket.adapters.persistence

import org.springframework.stereotype.Repository

@Repository
class SeatReservationRepository {
    fun reserveSeat(id: Int){
        SeatMemoryDatabase.reserveSeat(id)
    }

    fun cancelReservation(id: Int){
        SeatMemoryDatabase.cancelReservation(id)
    }
}