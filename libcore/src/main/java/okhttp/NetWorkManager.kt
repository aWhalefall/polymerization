//package okhttp
//
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit.request.Request
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//
//class NetWorkManager {
//
//    /**
//     * 初始化必要对象和参数
//     */
//    fun init() {
//        // 初始化okhttp
//        val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor())
//                .build()
//
//        // 初始化Retrofit
//        retrofit = Retrofit.Builder()
//                .client(client)
//                .baseUrl(Request.HOST)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//    }
//
//    companion object {
//
//        private var mInstance: NetWorkManager? = null
//        private var retrofit: Retrofit? = null
//        @Volatile
//        private var request: Request? = null
//
//        val instance: NetWorkManager
//            get() {
//                if (mInstance == null) {
//                    synchronized(NetWorkManager::class.java) {
//                        if (mInstance == null) {
//                            mInstance = NetWorkManager()
//                        }
//                    }
//                }
//                return this!!.mInstance!!
//            }
//
//        fun getRequest(): Request? {
//            if (request == null) {
//                synchronized(Request::class.java) {
//                    request = retrofit!!.create(Request::class.java)
//                }
//            }
//            return request
//        }
//    }
//}
