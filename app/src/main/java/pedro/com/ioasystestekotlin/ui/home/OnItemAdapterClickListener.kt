package pedro.com.ioasystestekotlin.ui.home

import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface OnItemAdapterClickListener {
    fun onItemClick(enterprise: Enterprise)
}