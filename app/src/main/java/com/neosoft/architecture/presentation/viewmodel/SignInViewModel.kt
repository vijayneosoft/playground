package com.neosoft.architecture.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neosoft.architecture.domain.usecases.NetworkingUC
import com.neosoft.architecture.presentation.ui.model.SignInModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Vijay on 26/2/19.
 *
 */


class SignInViewModel(var mNetworkingUC: NetworkingUC?) : ViewModel() {

    var mDisposables = CompositeDisposable()
    var mMutableLiveData = MutableLiveData<SignInModel>()

    fun loginResponse(): LiveData<SignInModel> {
        return mMutableLiveData
    }

    /**
     * TODO
     *
     * @param username
     * @param password
     */
    fun doLoginVM(username: String, password: String) {
        mNetworkingUC?.doSignInUC(username, password)
            ?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                { result -> mMutableLiveData.setValue(SignInModel.success(result)) },
                { throwable -> mMutableLiveData.setValue(SignInModel.error(throwable)) }
            )?.let {
                mDisposables.add(it)
            }
    }


}