package pedro.com.ioasystestekotlin.model.data

data class Enterprise(
        var enterprise_name: String? = "",
        var photo: String? = "",
        var description: String? = "",
        var country: String? = "",
        var enterprise_type: TypeEnterprise = TypeEnterprise()
)