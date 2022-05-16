package com.example.pagingdemo.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingdemo.R
import com.example.pagingdemo.model.UserList

class UserPagingAdapter :
    PagingDataAdapter<UserList.Data, UserPagingAdapter.UserViewHolder>(COMPARATOR) {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val email = itemView.findViewById<TextView>(R.id.tv_email)
        val imageView = itemView.findViewById<ImageView>(R.id.iv_row_item)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.name.text = item.first_name
            holder.email.text = item.email
            Glide.with(holder.imageView.getContext()).load(item.avatar).centerCrop().into(holder.imageView);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return UserViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UserList.Data>() {
            override fun areItemsTheSame(oldItem: UserList.Data, newItem: UserList.Data): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserList.Data, newItem: UserList.Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}





















