package pedro.com.ioasystestekotlin.domain.usecase.searchenterprises

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pedro.com.ioasystestekotlin.application.MyApplication
import pedro.com.ioasystestekotlin.data.cache.headerMapper
import pedro.com.ioasystestekotlin.data.cache.room.repo.HeaderRoom
import pedro.com.ioasystestekotlin.data.remote.repository.enterprise.EnterpriseRepository
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class SearchEnterpriseUseCaseImpl(private val search: EnterpriseRepository,
                                  private val headerRoom: HeaderRoom) : SearchEnterpriseUseCase {

    private var jobSearch: Job = Job()

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit) {
        jobSearch = CoroutineScope(Dispatchers.IO)
                .launch {
                    val header = headerRoom.getHeader(MyApplication.EMAIL)
                    if (header == null) {
                        errorSearch(Exception("HeaderNotFound"))
                        return@launch
                    }

                    val resultApi = search.searchEnterprise(
                            query = query,
                            mapHeader = header.headerMapper()
                    )

                    if (resultApi.throwable != null || resultApi.data == null) {
                        errorSearch(resultApi.throwable as Throwable)
                        return@launch
                    }

                    searchFound(resultApi.data)
                }
    }

    override fun cancelJob() {
        jobSearch.cancel()
    }
}