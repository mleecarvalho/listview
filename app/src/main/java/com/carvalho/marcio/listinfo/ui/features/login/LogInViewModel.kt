package com.carvalho.marcio.listinfo.ui.features.login

import androidx.lifecycle.MutableLiveData
import com.carvalho.marcio.listinfo.data.ListInfoApi
import com.carvalho.marcio.listinfo.data.LogInRequest
import com.carvalho.marcio.listinfo.data.UserData
import com.carvalho.marcio.listinfo.ui.BaseViewModel
import io.reactivex.functions.Consumer

class LogInViewModel(private val api: ListInfoApi): BaseViewModel() {

    val user = MutableLiveData<UserData>()

    fun logIn(cpf: String, password: String){

        disposables.add(api.logIn(LogInRequest(cpf, password))
            .subscribe(Consumer {
            }, errorHandler))

    }

}