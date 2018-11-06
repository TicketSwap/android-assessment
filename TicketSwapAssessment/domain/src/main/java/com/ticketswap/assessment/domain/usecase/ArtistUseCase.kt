package com.ticketswap.assessment.domain.usecase

import com.ticketswap.assessment.domain.model.ArtistDomain
import io.reactivex.Single

abstract class ArtistUseCase : UseCase<String, Single<ArtistDomain>>()