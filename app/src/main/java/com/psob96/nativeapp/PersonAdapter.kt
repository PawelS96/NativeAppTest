package com.psob96.nativeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_person.view.*

class PersonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var personList: List<Person>

    fun display(list: List<Person>){
        personList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PersonHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun getItemViewType(position: Int): Int = R.layout.list_item_person

    override fun getItemCount(): Int = personList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PersonHolder){
            holder.bindData(personList[position])
        }
    }

    private inner class PersonHolder  constructor(v: View) : RecyclerView.ViewHolder(v) {

        private val title: TextView = v.person_name
        private val subtitle: TextView = v.person_details

        fun bindData(person: Person) {
            title.text = person.name
            subtitle.text = person.surname
        }
    }
}