package pedro.com.ioasystestekotlin.model.data

data class Enterprise(
        val enterpriseName: String?,
        val photo: String?,
        val description: String?,
        val city: String?,
        val country: String?,
//        val enterprise_type: TypeEnterprise{
        val enterprise_type_name:String
)
