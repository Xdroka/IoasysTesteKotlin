package pedro.com.ioasystestekotlin.ui.home

import pedro.com.ioasystestekotlin.model.dataclass.Enterprise

interface OnItemAdapterClickListener {
    fun onItemClick(enterprise: Enterprise)
}