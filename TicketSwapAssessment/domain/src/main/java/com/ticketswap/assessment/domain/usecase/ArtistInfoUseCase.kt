package com.ticketswap.assessment.domain.usecase

import com.ticketswap.assessment.domain.model.ArtistInfoDomain
import io.reactivex.Single

abstract class ArtistInfoUseCase: UseCase<String, Single<ArtistInfoDomain>>()