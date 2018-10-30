package com.ticketswap.assessment.domain.usecase

abstract class UseCase<P, R> {
    abstract fun execute(param: P): R
}