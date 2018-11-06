package pedro.com.ioasystestekotlin.view

import pedro.com.ioasystestekotlin.model.data.Enterprise

interface OnItemAdapterClickListener {
    fun onItemClick(enterprise: Enterprise)
}