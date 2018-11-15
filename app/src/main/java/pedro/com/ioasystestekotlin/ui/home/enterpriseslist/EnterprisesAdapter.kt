package pedro.com.ioasystestekotlin.ui.home.enterpriseslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.EnterpriseListBinding
import pedro.com.ioasystestekotlin.presentation.model.Enterprise
import pedro.com.ioasystestekotlin.ui.ext.downloadPhoto
import pedro.com.ioasystestekotlin.ui.home.OnItemAdapterClickListener

class EnterprisesAdapter(private var enterpriseList: List<Enterprise>,
                         private val listener: OnItemAdapterClickListener
) : RecyclerView.Adapter<EnterpriseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterpriseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EnterpriseListBinding.inflate(inflater, parent, false)
        return EnterpriseViewHolder(binding)
    }

    override fun getItemCount() = enterpriseList.size //?: 0


    override fun onBindViewHolder(holder: EnterpriseViewHolder, position: Int) {
        val enterprise = enterpriseList[position]

        holder.binding.item?.enterprise?.value?.apply {
            _name = enterprise.name
            _description = enterprise.description
            _country = enterprise.country
            _bussiness = enterprise.bussiness
            _photo = enterprise.photo
        }

        holder.binding.CardView.setOnClickListener {
            listener.onItemClick(enterprise)
        }

        enterprise.photo.let { photoUrl ->
            if (photoUrl == "") {
                holder.binding.imageView.setImageResource(R.drawable.imageReport)
                return@let
            }
            holder.binding.imageView.downloadPhoto(photoUrl)
        }
    }

}
