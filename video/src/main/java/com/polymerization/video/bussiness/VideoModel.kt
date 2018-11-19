package com.polymerization.video.bussiness

import com.appcomponent.base.BaseModel
import com.appcomponent.utils.RxJavaUtils
import com.google.gson.internal.LinkedTreeMap
import com.polymerization.core.okhttp.NetWorkManager
import com.polymerization.video.model.FlowerBean
import com.polymerization.video.model.ItemEntity
import com.safframework.log.L
import io.reactivex.Observable
import retrofit2.Call
import java.util.*


class VideoModel(mBasePresenter: VideoPresenter) : BaseModel {

    var mViewPresenter = mBasePresenter

    override fun requestToServer() {

    }

    override fun requestToServer(args: Any) {
        val params = args as Array<*>


        NetWorkManager.creatRequest(VideoRequest::class.java)
                .getFlowerFuli(params[0].toString(),
                        params[1].toString(), params[2].toString()).enqueue(object : retrofit2.Callback<Any> {
                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        L.d("d")
                    }

                    override fun onResponse(call: Call<Any>, response: retrofit2.Response<Any>) {
                        var linkedmap = (response.body() as LinkedTreeMap<String, *>).get("showapi_res_body") as LinkedTreeMap<String, *>
                        var flowerBean = FlowerBean()

                        var flowers: MutableList<ItemEntity> = ArrayList()
                        linkedmap.values.forEach {
                            try {
                                with(it as LinkedTreeMap<*, String>) {
                                    var item = ItemEntity().clone()
                                    item.thumb = it["thumb"]
                                    item.url = it["url"]
                                    item.title = it["title"]
                                    flowers.add(item)
                                }
                            } catch (e: Exception) {
                                L.e("error" + it.toString())
                            }
                        }
                        flowerBean.flowers = flowers

                        Observable.just("1").compose(RxJavaUtils.observableToMain()).subscribe {
                            mViewPresenter.serverResponse(flowerBean)
                        }


                    }

                })

    }

    override fun setRequestType(method: Int) {

    }

    override fun cancelRequest() {

    }
}