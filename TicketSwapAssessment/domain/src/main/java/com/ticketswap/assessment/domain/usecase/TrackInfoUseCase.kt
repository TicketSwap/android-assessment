package com.ticketswap.assessment.domain.usecase

import com.ticketswap.assessment.domain.model.TrackInfoDomain
import io.reactivex.Single

abstract class TrackInfoUseCase: UseCase<String, Single<TrackInfoDomain>>()