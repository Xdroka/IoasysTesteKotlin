package pedro.com.ioasystestekotlin.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import pedro.com.ioasystestekotlin.model.data.Enterprise

class EnterprisesAdapter(var enterpriseList: List<Enterprise>?
) : RecyclerView.Adapter<EnterpriseViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EnterpriseViewHolder {
        TODO()
    }

    override fun getItemCount(): Int {
        return enterpriseList?.size ?: 0
    }

    override fun onBindViewHolder(enterpriseViewHolder: EnterpriseViewHolder, position: Int) {
        val enterprise = enterpriseList?.get(position)

    }

}