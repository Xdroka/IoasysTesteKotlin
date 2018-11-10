package pedro.com.ioasystestekotlin.ui.enterpriseslist

import android.support.v7.widget.RecyclerView
import pedro.com.ioasystestekotlin.databinding.EnterpriseListBinding
import pedro.com.ioasystestekotlin.presentation.EnterpriseListViewModel

data class EnterpriseViewHolder(val binding: EnterpriseListBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.item = EnterpriseListViewModel()
        binding.executePendingBindings()
    }
}


