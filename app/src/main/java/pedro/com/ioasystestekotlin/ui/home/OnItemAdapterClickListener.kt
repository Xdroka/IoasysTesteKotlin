package pedro.com.ioasystestekotlin.ui.home

import pedro.com.ioasystestekotlin.domain.model.Enterprise

interface OnItemAdapterClickListener {
    fun onItemClick(enterprise: Enterprise)
}