package com.ticketswap.assessment.repository

interface BaseRepository<P, R> {
    fun execute(params: P): R
}