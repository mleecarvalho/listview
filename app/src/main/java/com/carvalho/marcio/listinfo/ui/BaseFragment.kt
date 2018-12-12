package com.carvalho.marcio.listinfo.ui

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun getViewModel(): BaseViewModel

    abstract fun getBinding(): ViewDataBinding
}
