package com.kailin.architecture_model.architecture


import androidx.lifecycle.*
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

abstract class ArchitectureViewModel : ViewModel(), LifecycleObserver {

    val throwableLiveData = MutableLiveData<Throwable>()
    val showLoading = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    protected fun rxOnComplete() {
        showLoading.postValue(false)
    }

    protected fun rxOnSubscribe(disposable: Disposable) {
        showLoading.postValue(true)
        compositeDisposable.add(disposable)
    }

    protected fun <T> rxOptimization(observable: Observable<T>, onNext: Consumer<T>) {
        observable.subscribeOn(Schedulers.newThread())
                .doOnComplete { rxOnComplete() }
                .doOnSubscribe { rxOnSubscribe(it) }
                .subscribe(onNext, Consumer { throwableLiveData.postValue(it) })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestroy() {
        compositeDisposable.dispose()
    }
}
