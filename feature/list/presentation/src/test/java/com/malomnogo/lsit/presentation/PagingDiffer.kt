package com.malomnogo.lsit.presentation

import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
fun <T : Any> pagingDiffer(): AsyncPagingDataDiffer<T> =
    AsyncPagingDataDiffer(
        diffCallback =
            object : DiffUtil.ItemCallback<T>() {
                override fun areItemsTheSame(
                    oldItem: T,
                    newItem: T,
                ) = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: T,
                    newItem: T,
                ) = oldItem == newItem
            },
        updateCallback = NoopListCallback(),
        workerDispatcher = Dispatchers.Main,
    )

private class NoopListCallback : ListUpdateCallback {
    override fun onInserted(
        position: Int,
        count: Int,
    ) = Unit

    override fun onRemoved(
        position: Int,
        count: Int,
    ) = Unit

    override fun onMoved(
        fromPosition: Int,
        toPosition: Int,
    ) = Unit

    override fun onChanged(
        position: Int,
        count: Int,
        payload: Any?,
    ) = Unit
}
