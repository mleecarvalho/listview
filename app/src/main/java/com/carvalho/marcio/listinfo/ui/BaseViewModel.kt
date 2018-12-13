package com.carvalho.marcio.listinfo.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.carvalho.marcio.listinfo.R
import com.carvalho.marcio.listinfo.data.ApiError
import com.carvalho.marcio.listinfo.data.SingleLiveEvent
import com.carvalho.marcio.listinfo.data.validator.HttpStatus
import com.squareup.moshi.Moshi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import retrofit2.HttpException
import java.io.IOException

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val disposables = CompositeDisposable()
    val error = SingleLiveEvent<CharSequence>()


    protected val errorHandler = Consumer<Throwable> {
        val context = get<Context>()
        when (it) {
            is HttpException -> {

                val moshi = get<Moshi>()

                try {
                    val errorBody = it.response().errorBody()?.string() ?: ""
                    val response = moshi.adapter<ApiError>(ApiError::class.java).fromJson(errorBody)

                    customErrorHandler(context, response!!.copy(code = it.response().code()))
                } catch (e: IOException) {
                    customErrorHandler(context, ApiError(code = it.response().code()))
                }
            }
            is IOException -> error.postValue(context.getString(R.string.error_connection))
            else -> error.postValue(context.getString(R.string.error_server))
        }

    }

    // Override this method if you need a specific error handler
    protected open fun customErrorHandler(context: Context, apiError: ApiError) {
        if (apiError.code == HttpStatus.UNAUTHORIZED) {
            //TODO: tratar sessao expirada enviando o usuario para o login
        } else {
            error.postValue(context.resources.getString(R.string.error_server))
        }
    }

}