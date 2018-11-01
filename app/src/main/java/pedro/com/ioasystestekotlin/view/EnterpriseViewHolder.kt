package pedro.com.ioasystestekotlin.view

import android.content.Intent
import android.support.v7.widget.RecyclerView
import pedro.com.ioasystestekotlin.databinding.EnterpriseListBinding
import pedro.com.ioasystestekotlin.model.util.ImageUtil
import pedro.com.ioasystestekotlin.viewmodel.EnterpriseListViewModel
import pedro.com.ioasystestekotlin.viewmodel.State

data class EnterpriseViewHolder(val binding: EnterpriseListBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.item = EnterpriseListViewModel()

        binding.item?.viewState?.observeForever { viewState ->
            when (viewState?.state) {
                State.SUCCESS -> {
                    val context = binding.root.context.applicationContext
                    val intent = Intent(context, AboutActivity::class.java)
                    intent.putExtra("description", viewState.data?.description)
                    intent.putExtra("photo", viewState.data?.photo)
                    intent.putExtra("enterpriseName", viewState.data?.enterprise_name)
                    context.startActivity(intent)
                }

                else -> {
                }
            }
        }

        binding.executePendingBindings()
    }
}


