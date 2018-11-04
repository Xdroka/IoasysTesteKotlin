package pedro.com.ioasystestekotlin.view

import android.support.v7.widget.RecyclerView
import pedro.com.ioasystestekotlin.databinding.EnterpriseListBinding
import pedro.com.ioasystestekotlin.viewmodel.EnterpriseListViewModel

data class EnterpriseViewHolder(val binding: EnterpriseListBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.item = EnterpriseListViewModel()
        binding.executePendingBindings()
    }
}


