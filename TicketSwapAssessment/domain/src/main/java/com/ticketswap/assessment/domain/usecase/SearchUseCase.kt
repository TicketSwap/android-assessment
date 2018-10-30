package com.ticketswap.assessment.domain.usecase

import com.ticketswap.assessment.domain.model.SearchRequestDomain
import com.ticketswap.assessment.domain.model.SearchResponseDomain
import io.reactivex.Single

abstract class SearchUseCase: UseCase<SearchRequestDomain, Single<SearchResponseDomain>>()