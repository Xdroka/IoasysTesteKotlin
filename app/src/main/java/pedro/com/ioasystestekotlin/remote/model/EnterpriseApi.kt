package pedro.com.ioasystestekotlin.remote.model

data class EnterpriseApi(
        var enterprise_name: String? = "",
        var photo: String? = "",
        var description: String? = "",
        var country: String? = "",
        var enterprise_type: TypeEnterprise = TypeEnterprise()
)