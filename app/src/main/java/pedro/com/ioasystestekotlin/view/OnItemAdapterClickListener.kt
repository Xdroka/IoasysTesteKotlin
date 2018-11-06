package pedro.com.ioasystestekotlin.view

import pedro.com.ioasystestekotlin.model.dataclass.Enterprise

interface OnItemAdapterClickListener {
    fun onItemClick(enterprise: Enterprise)
}