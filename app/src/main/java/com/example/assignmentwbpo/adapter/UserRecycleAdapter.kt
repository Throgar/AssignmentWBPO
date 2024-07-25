package com.example.assignmentwbpo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentwbpo.data.UserData
import com.example.assignmentwbpo.ui.EventListener
import androidx.databinding.ViewDataBinding

class UserRecycleAdapter(
    var data: ArrayList<UserData>, var mEventListener: EventListener<UserData>
) : RecyclerView.Adapter<UserRecycleAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = LayoutDataBinding.inflate(inflater, parent, false)
        return MyViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return data.size
    }

    private fun getItem(p: Int): UserData {
        return data[p]

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = with(holder.itemBinding) {
        val item = getItem(position)
        item.apply {
            imageView.loadImage(url)
        }

        root.setOnClickListener {
            mEventListener.onItemClick(position, item, it)
        }
    }

    inner class MyViewHolder(internal var itemBinding: LayoutDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

}