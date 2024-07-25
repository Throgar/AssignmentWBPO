package com.example.assignmentwbpo.ui

import android.view.View

interface EventListener<T> {
    fun onItemClick(pos: Int, item: T, view: View)
}