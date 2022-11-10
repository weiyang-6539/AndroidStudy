package com.wyang.study.ui.fragment_second.rv

import com.wyang.study.databinding.FragmentContactsBinding
import com.wyang.study.ui.base.BaseFragment

class ContactsFragment :BaseFragment<FragmentContactsBinding>(){
    override fun getViewBinding(): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(layoutInflater)
    }

    override fun initialize() {

    }
}