package pedro.com.ioasystestekotlin.remote.model.mapper

import pedro.com.ioasystestekotlin.domain.model.Enterprise
import pedro.com.ioasystestekotlin.remote.model.EnterpriseApi

fun List<EnterpriseApi>.convertListOfEnterprises() : List<Enterprise>{
    val listEnterprises = ArrayList<Enterprise>()
    this.forEach { listApi ->
        listEnterprises.add(
                listApi.convertEnterprise()
        )

    }
    return listEnterprises
}

fun EnterpriseApi.convertEnterprise(): Enterprise =
    Enterprise(
            name = enterprise_name ?: "",
            photo = photo ?: "",
            description = description ?: "",
            country = country ?: "",
            bussiness = enterprise_type.enterprise_type_name ?: ""
    )
