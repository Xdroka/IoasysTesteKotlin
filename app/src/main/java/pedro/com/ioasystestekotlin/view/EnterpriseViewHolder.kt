package pedro.com.ioasystestekotlin.view

import android.support.v7.widget.RecyclerView
import android.widget.Toast
import pedro.com.ioasystestekotlin.databinding.EnterpriseListBinding
import pedro.com.ioasystestekotlin.viewmodel.EnterpriseListViewModel
import pedro.com.ioasystestekotlin.viewmodel.State

data class EnterpriseViewHolder(val binding: EnterpriseListBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.item = EnterpriseListViewModel()

        binding.item?.viewState?.observeForever {viewState ->
            when(viewState?.state){
                State.SUCCESS ->{
                    Toast.makeText(binding.root.context, viewState.data, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        binding.executePendingBindings()
    }
}


