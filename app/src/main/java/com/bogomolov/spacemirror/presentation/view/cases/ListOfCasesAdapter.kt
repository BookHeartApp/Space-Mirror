package com.bogomolov.spacemirror.presentation.view.cases

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bogomolov.spacemirror.R
import com.bogomolov.spacemirror.data.local.cases.ComplexCase
import com.bogomolov.spacemirror.data.local.cases.ItemData
import com.bogomolov.spacemirror.data.local.cases.SimpleCase
import com.bogomolov.spacemirror.utils.recyclerview.ItemTouchHelperAdapter

class ListOfCasesAdapter : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    inner class SimpleCaseViewHolder(view: View) : BaseViewHolder(view) {

        private val text = view.findViewById<TextView>(R.id.text_simple_case)
        private val moveUp = view.findViewById<ImageView>(R.id.move_up_simple_case)
        private val moveDown = view.findViewById<ImageView>(R.id.move_down_simple_case)
        private val delete = view.findViewById<ImageView>(R.id.delete_simple_case)

        fun bind(case: SimpleCase) {
            text.text = case.text
            moveUp.setOnClickListener { moveUpCase() }
            moveDown.setOnClickListener { moveDownCase() }
            delete.setOnClickListener { removeCase() }
        }

        private fun moveUpCase() {
            layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                cases.removeAt(currentPosition).apply {
                    cases.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDownCase() {
            layoutPosition.takeIf { it < cases.size - 1 }?.also { currentPosition ->
                cases.removeAt(currentPosition).apply {
                    cases.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        private fun removeCase() {
            cases.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class ComplexCaseViewHolder(view: View) : BaseViewHolder(view) {

        private val heading = view.findViewById<TextView>(R.id.heading_complex_case)
        private val text = view.findViewById<TextView>(R.id.text_complex_case)
        private val moveUp = view.findViewById<ImageView>(R.id.move_up_complex_case)
        private val moveDown = view.findViewById<ImageView>(R.id.move_down_complex_case)
        private val delete = view.findViewById<ImageView>(R.id.delete_complex_case)

        fun bind(case: ComplexCase) {
            heading.text = case.heading
            text.text = case.text
            moveUp.setOnClickListener { moveUpCase() }
            moveDown.setOnClickListener { moveDownCase() }
            delete.setOnClickListener { removeCase() }
        }

        private fun moveUpCase() {
            layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                cases.removeAt(currentPosition).apply {
                    cases.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDownCase() {
            layoutPosition.takeIf { it < cases.size - 1 }?.also { currentPosition ->
                cases.removeAt(currentPosition).apply {
                    cases.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        private fun removeCase() {
            cases.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    companion object {
        private const val SIMPLE_CASE_TYPE = 1
        private const val COMPLEX_CASE_TYPE = 2
    }

    var cases = mutableListOf<ItemData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SIMPLE_CASE_TYPE -> SimpleCaseViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_simple_case,
                    parent,
                    false
                )
            )
            else -> ComplexCaseViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_complex_case,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

        val currentCases = cases[position]

        if (getItemViewType(position) == SIMPLE_CASE_TYPE) {
            (holder as SimpleCaseViewHolder).bind(currentCases as SimpleCase)
        } else {
            (holder as ComplexCaseViewHolder).bind(currentCases as ComplexCase)
        }
    }

    override fun getItemCount(): Int = cases.size

    override fun getItemViewType(position: Int): Int =
        if (cases[position] is SimpleCase) {
            SIMPLE_CASE_TYPE
        } else {
            COMPLEX_CASE_TYPE
        }

    fun addCase(case: ItemData) {
        cases.add(case)
        notifyItemInserted(cases.size - 1)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        cases.removeAt(fromPosition).apply {
            cases.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        cases.removeAt(position)
        notifyItemRemoved(position)
    }
}