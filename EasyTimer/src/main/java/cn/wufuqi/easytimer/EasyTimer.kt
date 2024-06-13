package cn.wufuqi.easytimer

import cn.wufuqi.convertmainthread.ConvertMainThread
import java.util.*
import java.util.concurrent.ConcurrentHashMap

open class EasyTimer {
    /**
     * 放置定时器的全部列表
     */
    private val timerMap:MutableMap<UUID, Timer> = ConcurrentHashMap()

    /**
     *  计时器，每time毫秒执行一次回调，执行多次。
     */
    open fun schedule(time: Long, func: () -> Unit): UUID {
        //生产一个uuid
        val uuid = UUID.randomUUID()
        //创建一个timer
        val timer = Timer()
        //把定时器添加到管理列表中
        timerMap[uuid] = timer
        //执行定时器
        timer.schedule(object : TimerTask() {
            override fun run() {
                //执行定时方法
                func()
            }
        }, time, time)

        return uuid
    }

    open fun scheduleUI(time: Long, func: () -> Unit): UUID {
        return schedule(time) {
            ConvertMainThread.runOnUiThread {
                func()
            }
        }
    }


    /**
     * 计时器，在time毫秒后执行一次回调。执行一次
     */
    open fun scheduleOne(time: Long, func: () -> Unit): UUID {
        //生产一个uuid
        val uuid = UUID.randomUUID()
        //创建一个timer
        val timer = Timer()
        //把定时器添加到管理列表中
        timerMap[uuid] = timer
        //执行定时器
        timer.schedule(object : TimerTask() {
            override fun run() {
                //取消定时器
                unschedule(uuid)
                //执行定时方法
                func()
            }
        }, time)

        return uuid
    }

    open fun scheduleOneUI(time: Long, func: () -> Unit): UUID {
        return scheduleOne(time) {
            ConvertMainThread.runOnUiThread {
                func()
            }
        }
    }

    /**
     * 取消一个已经注册过的计时器
     */
    open fun unschedule(uuid: UUID) {
        timerMap[uuid]?.cancel()
        timerMap.remove(uuid)
    }

    /**
     * 取消所有正在执行的计时器。
     */
    open fun unscheduleAll() {
        timerMap.forEach {
            timerMap[it.key]?.cancel()
        }
        timerMap.clear()
    }

}