package com.ticketswap.assessment.repo

abstract class BaseRepository<P, R> {
    abstract fun execute(param: P): R
}