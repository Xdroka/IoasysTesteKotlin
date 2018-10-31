package pedro.com.ioasystestekotlin.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pedro.com.ioasystestekotlin.databinding.EnterpriseListBinding
import pedro.com.ioasystestekotlin.model.data.Enterprise

class EnterprisesAdapter(var enterpriseList: List<Enterprise>?
) : RecyclerView.Adapter<EnterpriseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterpriseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnterpriseListBinding.inflate(inflater)
        return EnterpriseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return enterpriseList?.size ?: 0
    }

    override fun onBindViewHolder(holder: EnterpriseViewHolder, position: Int) {
        if (enterpriseList == null) return

        val enterprise = enterpriseList!![position]

        holder.binding.item?.enterprise?.value?._name = enterprise.enterprise_name
        holder.binding.item?.enterprise?.value?._description = enterprise.description
        holder.binding.item?.enterprise?.value?._country = enterprise.country
        holder.binding.item?.enterprise?.value?._bussiness = enterprise.enterprise_type


    }

}