package com.ticketswap.assessment.interceptor

import com.ticketswap.assessment.repo.UserAuthenticatedRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
        private val authenticatedRepository: UserAuthenticatedRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
            chain.request().let { r ->
                try {
                    val respo = authenticatedRepository.execute(Unit).blockingGet()
                    respo.token?.let {
                        r.newBuilder().addHeader("Authorization", it).build()
                    } ?: chain.request()

                } catch (e: Exception) {
                    e.printStackTrace()
                    chain.request()
                }
            }.let {
                chain.proceed(it)
            }
}