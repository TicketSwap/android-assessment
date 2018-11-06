package com.ticketswap.assessment.view.search

data class SearchAdapterItem(val id: String, val image: String?, val name: String, val type: SearchItemType)

enum class SearchItemType {
    LOCAL, NETWORK
}