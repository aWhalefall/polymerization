package com.appcomponent.utils

import android.app.ActivityManager
import com.appcomponent.base.BaseActivity
import java.util.*

/**
 * Author: yangweichao
 * Date:   2018/11/21 5:25 PM
 * Description: activity Activity 栈管理
 */

object StackManager {


    private val activityStack = Stack<BaseActivity>()
    /**
     * 获取当前Activity
     */
    fun currentActivity(): BaseActivity {
        return activityStack.lastElement()
    }

    /**
     * 判断Activity是否在栈顶
     */
    fun isCurrentActivity(activity: BaseActivity): Boolean {
        return activity === activityStack.peek() //查看堆栈顶部的对象，但不从堆栈中移除它。
    }

    /**
     * 判断Activity是否在栈顶 -> android系统Activity管理栈
     */
    fun isSysCurrentActivity(activity: BaseActivity): Boolean {
        val manager = activity.getSystemService(BaseActivity.ACTIVITY_SERVICE) as ActivityManager
        val runningTaskInfos = manager.getRunningTasks(1)
        return if (runningTaskInfos != null) {
            runningTaskInfos[0].topActivity.className == activity.javaClass.name
        } else false
    }

    /**
     * 是否包含Activity
     */
    fun containsActivity(cls: Class<out BaseActivity>): Boolean {
        return activityStack.search(cls) != -1
    }

    /**
     * 根据class,获取Activity实例
     * // TODO: 2018/11/21  用到需要修改
     */
    fun getActivitys(cls: Class<out BaseActivity>): List<BaseActivity> {
        val activities = ArrayList<BaseActivity>()
        for (activity in activityStack) {
            if (activity.javaClass == cls) {
                activities.add(activity)
            }
        }
        return activities
    }

    /**
     * 获取前一个Activity
     */
    fun prevActivity(activity: BaseActivity): BaseActivity? {
        return if (activity != null && activityStack.size > 0 && activityStack.contains(activity) && activityStack.firstElement() !== activity) {
            activityStack[activityStack.lastIndexOf(activity) - 1]
        } else null
    }

    /**
     * 获取下一个Activity
     */
    fun nextActivity(activity: BaseActivity): BaseActivity? {
        return if (activity != null && activityStack.size > 0 && activityStack.contains(activity) && activityStack.lastElement() !== activity) {
            activityStack[activityStack.lastIndexOf(activity) + 1]
        } else null
    }

    /**
     * 结束当前Activity
     */
    @Synchronized
    fun finishActivity() {
        finishActivity(true)
    }

    @Synchronized
    fun finishActivity(styleAnimat: Boolean) {
        val activity = activityStack.lastElement()
        finishActivity(activity, styleAnimat)
    }

    /**
     * 结束一个Activity
     */
    @Synchronized
    fun finishActivity(activity: BaseActivity) {
        finishActivity(activity, true)
    }

    @Synchronized
    fun finishActivity(activity: BaseActivity, styleAnimat: Boolean) {
        if (activity != null && removeActivity(activity)) {

            activity.finish()
            if (!styleAnimat) {
                activity.overridePendingTransition(0, 0)
            }
        }
    }

    /**
     * 移除一个Activity
     */
    @Synchronized
    fun removeActivity(activity: BaseActivity): Boolean {
        if (activity != null) {
            return activityStack.remove(activity)
        }
        return false

    }


    /**
     * 结束一个Activity,根据class
     */
    @Synchronized
    fun finishActivity(cls: Class<out BaseActivity>) {
        finishActivity(cls, true)
    }

    @Synchronized
    fun finishActivity(cls: Class<out BaseActivity>, styleAnimat: Boolean) {
        for (i in activityStack.indices.reversed()) {
            if (activityStack[i].javaClass == cls) {
                finishActivity(activityStack[i], styleAnimat)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    @Synchronized
    fun finishAllActivity() {
        finishAllActivity(true)
    }

    @Synchronized
    fun finishAllActivity(styleAnimat: Boolean) {
        for (activity in activityStack) {
            if (null != activity) {
                activity.finish()
                if (!styleAnimat) {
                    activity.overridePendingTransition(0, 0)
                }
            }
        }
        activityStack.clear()
    }

    /**
     * 添加一个Activity
     */
    @Synchronized
    fun addActivity(activity: BaseActivity) {
        if (activity != null) {
            activityStack.push(activity)
            //ActivityNodeManager.INSTANCE.addChild(activity.javaClass, activity.getBundle())
        }
    }


}
